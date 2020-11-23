package com.dtwave.dipper.asset.streaming.kafka

import com.alibaba.fastjson.JSON
import javax.servlet.http.{HttpServletRequest, HttpServletResponse}
import org.apache.curator.framework.CuratorFrameworkFactory
import org.apache.curator.retry.RetryNTimes
import org.apache.kafka.common.TopicPartition
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.{CanCommitOffsets, HasOffsetRanges, KafkaUtils, OffsetRange}
import org.apache.spark.{SparkConf, SparkContext, TaskContext}
import org.apache.spark.streaming.{Milliseconds, Seconds, StreamingContext}
import org.spark_project.jetty.server.{Request, Server}
import org.spark_project.jetty.server.handler.{AbstractHandler, ContextHandler}

//import com.dtwave.dipper.dubhe.plugin.flink.kafka.Order;


//case class Order(_id: Long,
//                 orderId: Long,
//                 proName: String,
//                 amount: Integer,
//                 orderTime: Long)

/**
  * @author hulb
  */
object kafkaZookeeperStreaming {

  /** ***************************************************************************************************************
    * todo zookeeper 实例化，方便后面对zk的操作 目前有些bug 可能会连不上zk
    */
  val zk = ZkWork

  def main(args: Array[String]): Unit = {

    /** ***************************************************************************************************************
      * todo 输入参数
      */
    //val Array(output, topic, broker, group, sec) = args
    val output = "/tmp/streaming/"
    val topic = "topic_partition_4"
    val broker = "mq250:9092,mq221:9092,mq164:9092"
    val group = "group-1"
    val sec = "2000"
    val security = false

    /** ***************************************************************************************************************
      */
    val spark = SparkSession
      .builder
      .master("local[3]")
      .appName("kafkaZookeeperStreaming")
      //.enableHiveSupport()
      .getOrCreate()

    val sc = spark.sparkContext
    val ssc = new StreamingContext(sc, Milliseconds(sec.toInt))
    daemonHttpServer(5566,ssc)
   // daemonHttpServer2(5567,ssc)
    sc.setLogLevel("WARN")

    /**
      * 使用curator来进行zk操作
      */


    //topic 这里是一个数组。可以同时订阅多个topic。
    val topics = Array(topic)

    var kafkaParams = Map[String, Object](
      "bootstrap.servers" -> broker,
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> group,
      //选择使用最新的offset
      "auto.offset.reset" -> "latest",
      //关闭自动提交
      "enable.auto.commit" -> (false: java.lang.Boolean),
      //default:1048576
      "max.partition.fetch.bytes" -> (26221440: java.lang.Integer),
      //default:60000
      "request.timeout.ms" -> (90000: java.lang.Integer),
      //default:30000
      "session.timeout.ms" -> (60000: java.lang.Integer)
    )

    // 如果开启了安全,则要进行其他的设置
    if (security) {
      kafkaParams += ("security.protocol" -> "SASL_PLAINTEXT")
      kafkaParams += ("sasl.mechanism" -> "GSSAPI")
      kafkaParams += ("sasl.kerberos.service.name" -> "kafka")
      System.setProperty("java.security.auth.login.config", "/opt/conf/kerberos/kafka-jaas.conf")
      System.setProperty("java.security.krb5.conf", "/opt/conf/kerberos/krb5.conf")
    }

    //val staticTable = spark.sql("select * from source")
   // staticTable.show()

    /** ***************************************************************************************************************
      * todo 2 - 判断zk中是否有保存过该计算的偏移量
      * 如果没有保存过,使用不带偏移量的计算,在计算完后保存
      * 精髓就在于KafkaUtils.createDirectStream这个地方
      * 默认是KafkaUtils.createDirectStream[String, String](ssc, PreferConsistent, Subscribe[String, String](topics, kafkaParams))，不加偏移度参数
      * 实在找不到办法，最后啃了下源码。有个consumerStrategy消费者策略，看看里面是个什么套路；
      *
      * 原来可以执行topic，和offsets消费偏移度，这下派上用场了
      *
      */
    // TODO 如果某个分区挂掉了怎么调整 ????Leader切换了！ 以及zk上的保存的怎么调整？
    // TODO 如果保存过 offset，还应该和 kafka 上最小的 offset 做对比，不然会报 OutOfRange 的错误

    var start = true;
    //val stream =  KafkaUtils.createDirectStream[String, String](ssc, PreferConsistent, Subscribe[String, String](topics, kafkaParams))
    val stream = if (zk.znodeIsExists(s"${topic}_offset/$group")) {
      //TODO 这里只有第一次启动的时候会去调用。之后不会调用。
      var newOffset: Map[TopicPartition, Long] = Map()
      val childCount = zk.znodeIsCountChild(s"${topic}_offset/$group").size()
      for (i <- 0 until childCount) {
        val nor = zk.znodeDataGet(s"${topic}_offset/$group/$i")
        newOffset += (new TopicPartition(nor(0).toString, nor(1).toInt) -> nor(2).toLong)
        //val newOffset = Map(new TopicPartition(nor(0).toString, nor(1).toInt) -> nor(2).toLong)//创建以topic，分区为k 偏移度为v的map

//        println(
//          s"""
//             |--------------------------------------------------------------------
//             |topic ${nor(0).toString},Partition ${nor(1).toInt},offset ${nor(2).toLong}
//             |zk中取出来的kafka偏移量★★★ $newOffset
//             |--------------------------------------------------------------------
//          """.stripMargin)
      }
      // TODO 注意kafka 内部会自己覆盖groupId 导致之前设置的groupId出问题。需要覆盖这个方法。val groupId = "spark-executor-" + originalGroupId
      // TODO 要把保存的offset 和 earliest 和latest对比，反之outOfRange
      println(newOffset)
      KafkaUtils.createDirectStream[String, String](ssc, PreferConsistent, Subscribe[String, String](topics, kafkaParams, newOffset))

    } else {
      println(
        s"""
           |--------------------------------------------------------------------
           |第一次计算,没有zk偏移量记录
           |手动创建一个偏移量文件 ${topic}offset 默认从0号分区 0偏移度开始计算
           |--------------------------------------------------------------------
        """.stripMargin)
      zk.znodeCreate(s"${topic}_offset", s"$topic,0,0")
      zk.znodeCreate(s"${topic}_offset/$group", s"$topic,0,0")
      zk.znodeCreate(s"${topic}_offset/$group/0", s"$topic,0,0")
      val nor = zk.znodeDataGet(s"${topic}_offset/$group")
      val newOffset = Map(new TopicPartition(nor(0).toString, nor(1).toInt) -> nor(2).toLong)
      println(newOffset)
      KafkaUtils.createDirectStream[String, String](ssc, PreferConsistent, Subscribe[String, String](topics, kafkaParams, newOffset))
    }

    //TODO 拿到流后会有一个坑 。没有进行任何处理直接 foreach RDD ？？？order 。window。
    /**
      * 如果不用这些算子。是能直接拿到offset的信息。
      *
      *
      * 不能用算子 ，该怎么办？？？
      */

    /**
      * 将流中的值取出来,用于计算,目前测试只做print
      */
    val lines = stream.map(_.value())

    //lines.flatMap(line => line.split(":")).print()
    // lines.map(line => JSON.parseObject(lines,java.lang.String.getC))
    lines.print()

    /**
      * 计算成功后保存偏移度
      */
    stream.foreachRDD {
      rdd =>
        val offsetRanges = rdd.asInstanceOf[HasOffsetRanges].offsetRanges

        //TODO rdd -> DF -> SQL // kafka 数据是json的。 直接转为df 方便。log4j 如何打json日志。

        rdd.foreachPartition {
//          val retryPolicy = new RetryNTimes(5,1000)
//          val framework = CuratorFrameworkFactory.newClient("127.0.0.1:2181", retryPolicy)
//          framework.start
          iter =>

            //println(iter)
            val o: OffsetRange = offsetRanges(TaskContext.get.partitionId)
            println(
              s"""
                 |--------------------------------------------------------------------
                 |[ kafkaZookeeperStreaming ]  topic: ${o.topic},partition: ${o.partition},
                 |fromOffset 开始偏移量: ${o.fromOffset} untilOffset 结束偏移量: ${o.untilOffset}
                 |需要保存的偏移量,供下次读取使用
                 |--------------------------------------------------------------------
               """.stripMargin)

           // framework.setData().forPath(s"${o.topic}_offset/$group/${o.partition}",s"${o.topic},${o.partition},${o.untilOffset}".getBytes)
           zk.offsetWork(s"${o.topic}_offset/$group/${o.partition}", s"${o.topic},${o.partition},${o.untilOffset}")
        }

        //TODO 该RDD 异步提交?offset =====？如果还没异步提交。1，，消费完。，2，，，，第二条的时候挂了。那2 是不是又要消费一次了？？？？
        //TODO 那重复的数据怎么处理？ ？？如何解决重复问题？
        //TODO 这里的异步提交只是添加到一个提交队列中。 会有另外的方法判断队列里有没有需要commit的offset 。然后执行commit操作。
        // 1. 不丢？。。处理重复？
        // key 是null、3个topic。数据没用打散。？？那设置的规则是什么？  。。。如果打散出问题了，分区内业务有序？？？？【保证某个人的业务数据，插入到一个topic】
        // 业务逻辑顺序？ inset update 。delete。
        //stream.asInstanceOf[CanCommitOffsets].commitAsync(offsetRanges)
    }

    ssc.start()
    ssc.awaitTermination()
  }

  //构建Map
  def setFromOffsets(list: List[(String, Int, Long)]): Map[TopicPartition, Long] = {
    var fromOffsets: Map[TopicPartition, Long] = Map()
    for (offset <- list) {
      val tp = new TopicPartition(offset._1, offset._2)//topic和分区数
      fromOffsets += (tp -> offset._3)           // offset位置
    }
    fromOffsets
  }

  /****
    * 负责启动守护的jetty服务
    * @param port 对外暴露的端口号
    * @param ssc Stream上下文
    */
  def daemonHttpServer(port:Int,ssc: StreamingContext)={
    val server=new Server(port)
    val context = new ContextHandler();
    context.setContextPath( "/close" );
    context.setHandler( new CloseStreamHandler(ssc) )
    server.setHandler(context)
    server.start()
  }

  /****
    * 负责启动守护的jetty服务
    * @param port 对外暴露的端口号
    * @param ssc Stream上下文
    */
  def daemonHttpServer2(port:Int,ssc: StreamingContext)={
    val server=new Server(port)
    val restartContext = new ContextHandler();
    restartContext.setContextPath( "/restart" );
    restartContext.setHandler( new RestartStreamHandler(ssc) )
    server.setHandler(restartContext)
    server.start()
  }

  /*** 负责接受http请求来优雅的关闭流
    * @param ssc  Stream上下文
    */
  class CloseStreamHandler(ssc:StreamingContext) extends AbstractHandler {
    override def handle(s: String, baseRequest: Request, req: HttpServletRequest, response: HttpServletResponse): Unit ={
      println("开始关闭......")
      ssc.stop(false,true)//优雅的关闭
      response.setContentType("text/html; charset=utf-8");
      response.setStatus(HttpServletResponse.SC_OK);
      val out = response.getWriter();
      out.println("close success");
      baseRequest.setHandled(true);
      println("关闭成功.....")
    }
  }

  /*** 负责接受http请求来优雅的关闭流
    * @param ssc  Stream上下文
    */
  class RestartStreamHandler(ssc:StreamingContext) extends AbstractHandler {
    override def handle(s: String, baseRequest: Request, req: HttpServletRequest, response: HttpServletResponse): Unit ={
      println("开始重启......")
      ssc.start//优雅的关闭
      response.setContentType("text/html; charset=utf-8");
      response.setStatus(HttpServletResponse.SC_OK);
      val out = response.getWriter();
      out.println("restart success");
      baseRequest.setHandled(true);
      println("重启成功.....")
    }
  }

}