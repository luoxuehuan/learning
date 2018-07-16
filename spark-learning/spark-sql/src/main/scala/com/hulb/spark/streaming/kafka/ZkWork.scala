package com.hulb.spark.streaming.kafka


import java.util
import java.util.List

import org.apache.zookeeper.ZooDefs.Ids
import org.apache.zookeeper.{CreateMode, WatchedEvent, Watcher, ZooKeeper}

object ZkWork {
  val TIME_OUT = 5000000
  var zooKeeper: ZooKeeper = _

  def watcher = new Watcher() {
    def process(event: WatchedEvent) {
      println(s"[ ZkWork ] process : " + event.getType)
    }
  }

  /** ***************************************************************************************************************
    * 基础方法
    * 连接zk,创建znode,更新znode
    */
  def connect() {
    println(s"[ ZkWork ] zk connect")
    zooKeeper = new ZooKeeper("mq250:2181,mq164:2181,mq221:2181", TIME_OUT, watcher)
  }

  def znodeCreate(znode: String, data: String) {
    println(s"[ ZkWork ] zk create /$znode , $data")
    zooKeeper.create(s"/$znode", data.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT)
  }


  def znodeDataSet(znode: String, data: String) {
    println(s"[ ZkWork ] zk data set /$znode")
    zooKeeper.setData(s"/$znode", data.getBytes(), -1)
  }

  /** ***************************************************************************************************************
    * 工作方法
    * 获得znode数据
    * 判断znode是否存在
    * 更新znode数据
    */
  def znodeDataGet(znode: String): Array[String] = {
    connect()
    println(s"[ ZkWork ] zk data get /$znode")
    try {
      new String(zooKeeper.getData(s"/$znode", true, null), "utf-8").split(",")
    } catch {
      case _: Exception => Array()
    }
  }

  def znodeIsExists(znode: String): Boolean ={
    connect()
    println(s"[ ZkWork ] zk znode is exists /$znode")
    zooKeeper.exists(s"/$znode", true) match {
      case null => false
      case _ => true
    }
  }

  def znodeIsCountChild(znode: String): util.List[String] ={
    connect()
    println(s"[ ZkWork ] zk znode is exists /$znode")
    val childs = zooKeeper.getChildren(s"/$znode", true)
    childs
  }

  def offsetWork(znode: String, data: String) {
    connect()
    println(s"[ ZkWork ] offset work /$znode")
    zooKeeper.exists(s"/$znode", true) match {
      case null => znodeCreate(znode, data)
      case _ => znodeDataSet(znode, data)
    }
    println(s"[ ZkWork ] zk close★★★")
    zooKeeper.close()
  }


}