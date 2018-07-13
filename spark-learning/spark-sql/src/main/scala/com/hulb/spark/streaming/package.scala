package com.hulb.spark


/**
  * 针对 流计算编程 的一些问题 及 思考
  *
  * 1.如何指定 offset 进行消费。如何自己保存offset（包括消费的进度） 以便下次恢复时,可以从上次的位置开始消费。
  *   如果自己维护的话 使用zk好还是mysql 或者redis 好。
  *
  * 2.流 - 流的join 时 watermark 的设置。对于没有join上的数据该如何处理。
  *
  * 3.静态流- 动态流。 join 时。如果静态流，需要周期性更新，如果做到数据更新时，对应用立即可见，不会在某个  时间点，使用老的数据。
  *
  * 4.多流（2个静态流，3个动态流）join  是否能够稳定。
  *
  * 5.输出到外部存储如何做到 （能够支持，）高效 （不开启太多的"数据库"连接）。   且尽可能得做到 撤回，更新等语义。
  *
  * 6.如果使用传统的spark Streaming 进行编程。如何自己控制，在程序里，利用数据结构，进行 "join" 操作。
  *
  * 7.
  *
  *
  *
  */
package object streaming {

}
