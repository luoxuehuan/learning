//package com.hulb.spark.streaming.kafka
//
//import org.apache.kafka.clients.ClientUtils
//
//import scala.collection.mutable.ArrayBuffer
//
//object GetOffsetShellWrap {
//
//  //在主函数添加一个参数map
//  def main(args: Array[String],map: ArrayBuffer[String]): Unit = {
//    //对参数的解析
////    val parser = new OptionParser
////    val brokerListOpt = parser.accepts("broker-list", "REQUIRED: The list of hostname and port of the server to connect to.")
////      .withRequiredArg
////      .describedAs("hostname:port,...,hostname:port")
////      .ofType(classOf[String])
////    val topicOpt = parser.accepts("topic", "REQUIRED: The topic to get offset from.")
////      .withRequiredArg
////      .describedAs("topic")
////      .ofType(classOf[String])
////    val partitionOpt = parser.accepts("partitions", "comma separated list of partition ids. If not specified, it will find offsets for all partitions")
////      .withRequiredArg
////      .describedAs("partition ids")
////      .ofType(classOf[String])
////      .defaultsTo("")
////    val timeOpt = parser.accepts("time", "timestamp of the offsets before that")
////      .withRequiredArg
////      .describedAs("timestamp/-1(latest)/-2(earliest)")
////      .ofType(classOf[java.lang.Long])
////    val nOffsetsOpt = parser.accepts("offsets", "number of offsets returned")
////      .withRequiredArg
////      .describedAs("count")
////      .ofType(classOf[java.lang.Integer])
////      .defaultsTo(1)
////    val maxWaitMsOpt = parser.accepts("max-wait-ms", "The max amount of time each fetch request waits.")
////      .withRequiredArg
////      .describedAs("ms")
////      .ofType(classOf[java.lang.Integer])
////      .defaultsTo(1000)
////
////    if(args.length == 0)
////      CommandLineUtils.printUsageAndDie(parser, "An interactive shell for getting consumer offsets.")
////
////    val options = parser.parse(args : _*)
////
////    CommandLineUtils.checkRequiredArgs(parser, options, brokerListOpt, topicOpt, timeOpt)
//
//    　　//获取参数的值
//    val clientId = "GetOffsetShell"
//    val brokerList = options.valueOf(brokerListOpt)
//    ToolsUtils.validatePortOrDie(parser, brokerList)
//    val metadataTargetBrokers = ClientUtils.parseBrokerList(brokerList)
//    val topic = options.valueOf(topicOpt)
//    var partitionList = options.valueOf(partitionOpt)
//    var time = options.valueOf(timeOpt).longValue
//    val nOffsets = options.valueOf(nOffsetsOpt).intValue
//    val maxWaitMs = options.valueOf(maxWaitMsOpt).intValue()
//
//    val topicsMetadata = ClientUtils.fetchTopicMetadata(Set(topic), metadataTargetBrokers, clientId, maxWaitMs).topicsMetadata
//    if(topicsMetadata.size != 1 || !topicsMetadata(0).topic.equals(topic)) {
//      System.err.println(("Error: no valid topic metadata for topic: %s, " + " probably the topic does not exist, run ").format(topic) +
//        "kafka-list-topic.sh to verify")
//      System.exit(1)
//    }
//    val partitions =
//      if(partitionList == "") {
//        topicsMetadata.head.partitionsMetadata.map(_.partitionId)
//      } else {
//        partitionList.split(",").map(_.toInt).toSeq
//      }
//    //遍历每个主分区
//    partitions.foreach { partitionId =>
//      val partitionMetadataOpt = topicsMetadata.head.partitionsMetadata.find(_.partitionId == partitionId)
//      partitionMetadataOpt match {
//        case Some(metadata) =>
//          metadata.leader match {
//            case Some(leader) =>
//              val consumer = new SimpleConsumer(leader.host, leader.port, 10000, 100000, clientId)
//              val topicAndPartition = TopicAndPartition(topic, partitionId)
//              val request = OffsetRequest(Map(topicAndPartition -> PartitionOffsetRequestInfo(time, nOffsets)))
//              val offsets = consumer.getOffsetsBefore(request).partitionErrorAndOffsets(topicAndPartition).offsets
//              //把获取到的offset进行存储
//              map += "%s:%d:%s".format(topic, partitionId, offsets.mkString(","))
//            case None => System.err.println("Error: partition %d does not have a leader. Skip getting offsets".format(partitionId))
//          }
//        case None => System.err.println("Error: partition %d does not exist".format(partitionId))
//      }
//    }
//  }
//}