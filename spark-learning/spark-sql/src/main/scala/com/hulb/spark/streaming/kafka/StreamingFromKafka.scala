package com.hulb.spark.streaming.kafka

import kafka.serializer.StringDecoder
import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.kafka._
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.kafka.OffsetRange
import org.apache.log4j.{Level, Logger}
import org.I0Itec.zkclient.ZkClient
import org.I0Itec.zkclient.exception.ZkMarshallingError
import org.I0Itec.zkclient.serialize.ZkSerializer
import kafka.utils.ZkUtils
import kafka.utils.ZKGroupTopicDirs
import org.apache.spark.streaming.dstream.InputDStream
import kafka.common.TopicAndPartition
import kafka.message.MessageAndMetadata
import kafka.api.OffsetRequest
import kafka.api.PartitionOffsetRequestInfo
import kafka.consumer.SimpleConsumer
import kafka.api.TopicMetadataRequest

object StreamingFromKafka {
  val groupId = "logs"
  val topic = "streaming"

  val zkClient = new ZkClient("localhost:9999", 60000, 60000, new ZkSerializer {
    override def serialize(data: Object): Array[Byte] = {
      try {
        return data.toString().getBytes("UTF-8")
      } catch {
        case e: ZkMarshallingError => return null

      }
    }

    override def deserialize(bytes: Array[Byte]): Object = {
      try {
        return new String(bytes, "UTF-8")
      } catch {
        case e: ZkMarshallingError => return null
      }
    }
  })
  val topicDirs = new ZKGroupTopicDirs("spark_streaming_test", topic)
  val zkTopicPath = s"${topicDirs.consumerOffsetDir}"

  def main(args: Array[String]): Unit = {
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)
    val sparkConf = new SparkConf().setAppName("DirectKafkaWordCount")

    sparkConf.setMaster("local[*]")
    sparkConf.set("spark.streaming.kafka.maxRatePerPartition", "2")
    sparkConf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    val ssc = new StreamingContext(sparkConf, Seconds(2))

    val kafkaParams = Map("metadata.broker.list" -> "localhost:9092",
      "group.id" -> groupId,
      "zookeeper.connect" -> "localhost:9999",
      "auto.offset.reset" -> kafka.api.OffsetRequest.SmallestTimeString)

    val topics = Set(topic)
    val children = zkClient.countChildren(s"${topicDirs.consumerOffsetDir}")
    var kafkaStream: InputDStream[(String, String)] = null
    var fromOffsets: Map[TopicAndPartition, Long] = Map()

    if (children > 0) {
      //---get partition leader begin----
      val topicList = List(topic)
      val req = new TopicMetadataRequest(topicList, 0) //得到该topic的一些信息，比如broker,partition分布情况
      val getLeaderConsumer = new SimpleConsumer("localhost", 9092, 10000, 10000, "OffsetLookup") // brokerList的host 、brokerList的port、过期时间、过期时间
      val res = getLeaderConsumer.send(req) //TopicMetadataRequest   topic broker partition 的一些信息
      val topicMetaOption = res.topicsMetadata.headOption
      val partitions = topicMetaOption match {
        case Some(tm) =>
          tm.partitionsMetadata.map(pm => (pm.partitionId, pm.leader.get.host)).toMap[Int, String]
        case None =>
          Map[Int, String]()
      }
      for (i <- 0 until children) {
        val partitionOffset = zkClient.readData[String](s"${topicDirs.consumerOffsetDir}/${i}")
        val tp = TopicAndPartition(topic, i)
        //---additional begin-----
        val requestMin = OffsetRequest(Map(tp -> PartitionOffsetRequestInfo(OffsetRequest.EarliestTime, 1))) // -2,1
        val consumerMin = new SimpleConsumer(partitions(i), 9092, 10000, 10000, "getMinOffset")
        val curOffsets = consumerMin.getOffsetsBefore(requestMin).partitionErrorAndOffsets(tp).offsets
        var nextOffset = partitionOffset.toLong
        if (curOffsets.length > 0 && nextOffset < curOffsets.head) { //如果下一个offset小于当前的offset
          nextOffset = curOffsets.head
        }
        //---additional end-----
        fromOffsets += (tp -> nextOffset)
        fromOffsets += (tp -> partitionOffset.toLong) //将不同 partition 对应的 offset 增加到 fromOffsets 中
      }
      val messageHandler = (mmd: MessageAndMetadata[String, String]) => (mmd.topic, mmd.message()) //这个会将 kafka 的消息进行 transform，最终 kafak 的数据都会变成 (topic_name, message) 这样的 tuple
      kafkaStream = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder, (String, String)](ssc, kafkaParams, fromOffsets, messageHandler)

    } else {
      kafkaStream = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](ssc, kafkaParams, topics)
    }
    var offsetRanges = Array[OffsetRange]()
    kafkaStream.transform { rdd =>
      offsetRanges = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
      rdd
    }.foreachRDD {
      rdd => {
        rdd.map(_._2).foreachPartition { element => element.foreach {
          println
        }
        }
        for (o <- offsetRanges) {
          ZkUtils.updatePersistentPath(zkClient, s"${topicDirs.consumerOffsetDir}/${o.partition}", o.fromOffset.toString)
        }
      }
    }
    ssc.start()
    ssc.awaitTermination()

  }
}

