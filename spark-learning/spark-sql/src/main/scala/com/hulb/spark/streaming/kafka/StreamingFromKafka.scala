//package com.hulb.spark.streaming.kafka
//
//import kafka.serializer.StringDecoder
//import org.apache.log4j.{Level, Logger}
//import org.apache.spark.SparkConf
//import org.apache.spark.rdd.RDD
//import org.apache.spark.streaming.kafka._
//import org.apache.spark.streaming.{Seconds, StreamingContext}
//import org.apache.spark.streaming.kafka.KafkaUtils
//import org.apache.spark.streaming.kafka.OffsetRange
//import org.apache.log4j.{Level, Logger}
//import org.I0Itec.zkclient.ZkClient
//import org.I0Itec.zkclient.exception.ZkMarshallingError
//import org.I0Itec.zkclient.serialize.ZkSerializer
//import kafka.utils.ZkUtils
//import kafka.utils.ZKGroupTopicDirs
//import org.apache.spark.streaming.dstream.InputDStream
//import kafka.common.TopicAndPartition
//import kafka.message.MessageAndMetadata
//import kafka.api.OffsetRequest
//import kafka.api.PartitionOffsetRequestInfo
//import kafka.consumer.SimpleConsumer
//import kafka.api.TopicMetadataRequest
//
//
///**
//  * 在初始化 kafka stream 的时候，查看 zookeeper 中是否保存有 offset，有就从该 offset 进行读取，没有就从最新/旧进行读取。
//  * 在消费 kafka 数据的同时，将每个 partition 的 offset 保存到 zookeeper 中进行备份
//  */
//object StreamingFromKafka {
//  val groupId = "logs"
//  val topic = "orders"
//
//  val zkClient = new ZkClient("mq250:2181,mq164:2181,mq221:2181", 60000, 60000, new ZkSerializer {
//
//    override def serialize(data: Object): Array[Byte] = {
//      try {
//        return data.toString().getBytes("UTF-8")
//      } catch {
//        case e: ZkMarshallingError => return null
//
//      }
//    }
//
//    override def deserialize(bytes: Array[Byte]): Object = {
//      try {
//        return new String(bytes, "UTF-8")
//      } catch {
//        case e: ZkMarshallingError => return null
//      }
//    }
//  })
//  val topicDirs = new ZKGroupTopicDirs("spark_streaming_test", topic)
//  val zkTopicPath = s"${topicDirs.consumerOffsetDir}"
//
//  def main(args: Array[String]): Unit = {
//    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
//    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)
//    val sparkConf = new SparkConf().setAppName("DirectKafkaWordCount")
//
//    sparkConf.setMaster("local[4]")
//    sparkConf.set("spark.streaming.kafka.maxRatePerPartition", "2")
//    sparkConf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
//    val ssc = new StreamingContext(sparkConf, Seconds(2))
//
//    //kafka参数
//    val kafkaParams = Map("metadata.broker.list" -> "mq250:9092,mq221:9092,mq164:9092",
//      "group.id" -> groupId,
//      "zookeeper.connect" -> "mq250:2181,mq221:2181,mq164:2181",
//      "auto.offset.reset" -> kafka.api.OffsetRequest.SmallestTimeString)
//
//    //创建 stream 时使用的 topic 名字集合
//    val topics = Set(topic)
//    //查询该路径下是否子节点（默认有子节点为我们自己保存不同 partition 时生成的）
//    val children = zkClient.countChildren(s"${topicDirs.consumerOffsetDir}")
//    var kafkaStream: InputDStream[(String, String)] = null
//    //如果 zookeeper 中有保存 offset，我们会利用这个 offset 作为 kafkaStream 的起始位置
//    var fromOffsets: Map[TopicAndPartition, Long] = Map()
//
//    if (children > 0) {
//      //---get partition leader begin----
//      // 如果 kafka 上的 offset 已经过期，那么就会报 OffsetOutOfRange 的异常，因为之前保存在 zk 的 offset 已经 topic 中找不到了。
//      // 所以我们需要在 从 zk 找到 offset 的这种情况下增加一个判断条件，如果 zk 中保存的 offset 小于当前 kafka topic 中最小的 offset，则设置为 kafka topic 中最小的 offset。
//      // 从 kafka上获取offset的时候，需要寻找对应的leader，从leader来获取 offset.
//      // 以下为获取不同 partition 的 leader 相关代码
//      val topicList = List(topic)
//
//      //得到该topic的一些信息，比如broker,partition分布情况
//      val req = new TopicMetadataRequest(topicList, 0)
//
//      // brokerList的host 、brokerList的port、过期时间、过期时间
//      val getLeaderConsumer = new SimpleConsumer("localhost", 9092, 10000, 10000, "OffsetLookup")
//
//      //TopicMetadataRequest   topic broker partition 的一些信息
//      val res = getLeaderConsumer.send(req)
//      val topicMetaOption = res.topicsMetadata.headOption
//      val partitions = topicMetaOption match {
//        case Some(tm) =>
//          tm.partitionsMetadata.map(pm => (pm.partitionId, pm.leader.get.host)).toMap[Int, String]
//        case None =>
//          Map[Int, String]()
//      }
//
//      //如果保存过 offset，还应该和 kafka 上最小的 offset 做对比，不然会报 OutOfRange 的错误
//      for (i <- 0 until children) {
//        val partitionOffset = zkClient.readData[String](s"${topicDirs.consumerOffsetDir}/${i}")
//        val tp = TopicAndPartition(topic, i)
//        //---additional begin-----
//        val requestMin = OffsetRequest(Map(tp -> PartitionOffsetRequestInfo(OffsetRequest.EarliestTime, 1))) // -2,1
//
//        //最小offset
//        val consumerMin = new SimpleConsumer(partitions(i), 9092, 10000, 10000, "getMinOffset")
//        val curOffsets = consumerMin.getOffsetsBefore(requestMin).partitionErrorAndOffsets(tp).offsets
//        var nextOffset = partitionOffset.toLong
//        if (curOffsets.length > 0 && nextOffset < curOffsets.head) {
//          //如果下一个offset小于当前的最小offset，从最小offset开始消费
//          nextOffset = curOffsets.head
//        }
//        //---additional end-----
//        //将不同 partition 对应的 offset 增加到 fromOffsets 中
//        fromOffsets += (tp -> nextOffset)
//        // TODO 是否可以不需要这一行。fromOffsets += (tp -> partitionOffset.toLong) //将不同 partition 对应的 offset 增加到 fromOffsets 中
//      }
//      //这个会将 kafka 的消息进行 transform，最终 kafak 的数据都会变成 (topic_name, message) 这样的 tuple
//      val messageHandler = (mmd: MessageAndMetadata[String, String]) => (mmd.topic, mmd.message()) //这个会将 kafka 的消息进行 transform，最终 kafak 的数据都会变成 (topic_name, message) 这样的 tuple
//      kafkaStream = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder, (String, String)](ssc, kafkaParams, fromOffsets, messageHandler)
//
//    } else {
//      //如果未保存，根据 kafkaParam 的配置使用最新或者最旧的 offset
//      kafkaStream = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](ssc, kafkaParams, topics)
//    }
//
//    //得到该 rdd 对应 kafka 的消息的 offset
//    var offsetRanges = Array[OffsetRange]()
//    kafkaStream.transform { rdd =>
//      offsetRanges = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
//      rdd
//    }.foreachRDD {
//      rdd => {
//        rdd.map(_._2).foreachPartition { element => element.foreach {
//          println
//        }
//        }
//        for (o <- offsetRanges) {
//          //处理,消费完后, 将该 partition 的 offset 保存到 zookeeper
//          ZkUtils.updatePersistentPath(zkClient, s"${topicDirs.consumerOffsetDir}/${o.partition}", o.fromOffset.toString)
//        }
//      }
//    }
//    ssc.start()
//    ssc.awaitTermination()
//
//  }
//}
//
