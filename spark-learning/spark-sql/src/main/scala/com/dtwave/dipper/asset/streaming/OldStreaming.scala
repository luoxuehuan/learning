package com.dtwave.dipper.asset.streaming

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Minutes, Seconds, StreamingContext}


/**
  * 使用老的Streaming方式实现流计算编程
  */
object OldStreaming {

  def main(args: Array[String]): Unit = {

    /**
      * mq250:2181,group-1 orders 2
      */
   // val Array(zkQuorum, group, topics, numThreads) = args
    val zkQuorum = "mq250:2181"
    val group = "group-1"
    val topics = "orders"
    val numThreads = "1"


    /**
      * 自己维护offset
      *
      * 同时读取kafka 和 hive。
      *
      * 自己解析kafka 固定消息格式。
      *
      * 自己做join操作
      *
      * 自己做sink操作。
      */
    val sparkConf = new SparkConf()
      .setMaster("local[2]")
      .setAppName("KafkaWordCount")

    val ssc = new StreamingContext(sparkConf, Seconds(2))

    val topicMap = topics.split(",").map((_, numThreads.toInt)).toMap
    val lines:DStream[String] = KafkaUtils.createStream(ssc, zkQuorum, group, topicMap).map(_._2)
    val words = lines.flatMap(_.split(" "))
    val wordCounts = words.map(x => (x, 1L))
      .reduceByKeyAndWindow(_ + _, _ - _, Minutes(10), Seconds(2), 2)
    wordCounts.print()

    ssc.start()
    ssc.awaitTermination()

  }

}
