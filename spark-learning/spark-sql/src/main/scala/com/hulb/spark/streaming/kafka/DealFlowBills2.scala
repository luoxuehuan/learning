package com.hulb.spark.streaming.kafka

import org.apache.kafka.common.TopicPartition
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.{HasOffsetRanges, KafkaUtils, OffsetRange}
import org.apache.spark.{SparkConf, SparkContext, TaskContext}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object DealFlowBills2 {

  /** ***************************************************************************************************************
    * todo zookeeper 实例化，方便后面对zk的操作，zk的代码很简单，这里就不贴了，基本照抄官方api
    */
  val zk = ZkWork
  def main(args: Array[String]): Unit = {

    /** ***************************************************************************************************************
      * todo 输入参数
      */
    //val Array(output, topic, broker, group, sec) = args
    val output = "/tmp/streaming/"
    val topic = "deal_flow"
    val broker = "mq250:9092,mq221:9092,mq164:9092"
    val group = "group-1"
    val sec = "3"

    /** ***************************************************************************************************************
      * todo spark套路
      */
    val conf = new SparkConf().setAppName("DealFlowBills2").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val ssc = new StreamingContext(sc, Seconds(sec.toInt))

    sc.setLogLevel("WARN")

    /** ***************************************************************************************************************
      * todo 1 - 准备kafka参数
      */
    val topics = Array(topic)
    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> broker,
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> group,
      "auto.offset.reset" -> "latest",
      "enable.auto.commit" -> (false: java.lang.Boolean)
    )

    /** ***************************************************************************************************************
      * todo 2 - 判断zk中是否有保存过该计算的偏移量
      * 如果没有保存过,使用不带偏移量的计算,在计算完后保存
      * 精髓就在于KafkaUtils.createDirectStream这个地方
      * 默认是KafkaUtils.createDirectStream[String, String](ssc, PreferConsistent, Subscribe[String, String](topics, kafkaParams))，不加偏移度参数
      * 实在找不到办法，最后啃了下源码。有个consumerStrategy消费者策略，看看里面是个什么套路；
      *
      * 原来可以执行topic，和offsets消费偏移度，这下派上用场了
      *
      *
      *
      */
    val stream = if (zk.znodeIsExists(s"${topic}offset")) {
      val nor = zk.znodeDataGet(s"${topic}offset")
      val newOffset = Map(new TopicPartition(nor(0).toString, nor(1).toInt) -> nor(2).toLong)//创建以topic，分区为k 偏移度为v的map
      println(s"[ DealFlowBills2 ] --------------------------------------------------------------------")
      println(s"[ DealFlowBills2 ] topic ${nor(0).toString}")
      println(s"[ DealFlowBills2 ] Partition ${nor(1).toInt}")
      println(s"[ DealFlowBills2 ] offset ${nor(2).toLong}")
      println(s"[ DealFlowBills2 ] zk中取出来的kafka偏移量★★★ $newOffset")
      println(s"[ DealFlowBills2 ] --------------------------------------------------------------------")
      KafkaUtils.createDirectStream[String, String](ssc, PreferConsistent, Subscribe[String, String](topics, kafkaParams, newOffset))
    } else {
      println(s"[ DealFlowBills2 ] --------------------------------------------------------------------")
      println(s"[ DealFlowBills2 ] 第一次计算,没有zk偏移量文件")
      println(s"[ DealFlowBills2 ] 手动创建一个偏移量文件 ${topic}offset 默认从0号分区 0偏移度开始计算")
      println(s"[ DealFlowBills2 ] --------------------------------------------------------------------")
      zk.znodeCreate(s"${topic}offset", s"$topic,0,0")
      val nor = zk.znodeDataGet(s"${topic}offset")
      val newOffset = Map(new TopicPartition(nor(0).toString, nor(1).toInt) -> nor(2).toLong)
      KafkaUtils.createDirectStream[String, String](ssc, PreferConsistent, Subscribe[String, String](topics, kafkaParams, newOffset))
    }

    /** ***************************************************************************************************************
      * todo 3 - 业务代码部分
      * 将流中的值取出来,用于计算
      */
    val lines = stream.map(_.value())
    lines.print()

    val result = lines
//    val result = lines
//      .filter(_.split(",").length == 21)
//      .map {
//        mlines =>
//          val line = mlines.split(",")
//          (line(3), s"${line(4)},${line(2)}")
//      }
//      .groupByKey()
//      .map {
//        case (k, v) =>
//          val result = v
//            .flatMap {
//              fmlines =>
//                fmlines.split(",").toList.zipWithIndex
//            }
//            .groupBy(_._2)
//            .map {
//              case (v1, v2) =>
//                v2.map(_._1)
//            }
//          (k, result)
//      }

    /** ***************************************************************************************************************
      * todo 4 - 保存偏移度部分
      * 计算成功后保存偏移度
      */
    stream.foreachRDD {
      rdd =>
        val offsetRanges = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
        rdd.foreachPartition {
          iter =>
            val o: OffsetRange = offsetRanges(TaskContext.get.partitionId)
            println(s"[ DealFlowBills2 ] --------------------------------------------------------------------")
            println(s"[ DealFlowBills2 ]  topic: ${o.topic}")
            println(s"[ DealFlowBills2 ]  partition: ${o.partition} ")
            println(s"[ DealFlowBills2 ]  fromOffset 开始偏移量: ${o.fromOffset} ")
            println(s"[ DealFlowBills2 ]  untilOffset 结束偏移量: ${o.untilOffset} 需要保存的偏移量,供下次读取使用★★★")
            println(s"[ DealFlowBills2 ] --------------------------------------------------------------------")
            // 写zookeeper
            zk.offsetWork(s"${o.topic}offset", s"${o.topic},${o.partition},${o.untilOffset}")

          // 写本地文件系统
          // val fw = new FileWriter(new File("/home/hadoop1/testjar/test.log"), true)
          // fw.write(offsetsRangerStr)
          // fw.close()
        }
    }

    /** ***************************************************************************************************************
      * todo 5 - 最后结果操作部分
      */
    result.saveAsTextFiles(output + s"/output/" + "010")

    /** ***************************************************************************************************************
      * todo spark streaming 开始工作
      */
    ssc.start()
    ssc.awaitTermination()

  }
}