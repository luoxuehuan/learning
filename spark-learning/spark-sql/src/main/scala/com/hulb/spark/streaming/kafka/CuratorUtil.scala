package com.hulb.spark.streaming.kafka

object CuratorUtil {

  def main(args: Array[String]): Unit = {
    import org.apache.curator.RetryPolicy
    import org.apache.curator.retry.RetryOneTime
    val retryPolicy = new RetryOneTime(1000)

    import org.apache.curator.framework.CuratorFrameworkFactory
    val framework = CuratorFrameworkFactory.newClient("127.0.0.1:2181", retryPolicy)
    framework.start

    //framework.create.forPath("/curator", "hello".getBytes)
    val data = new String(framework.getData().forPath("/curator"));
    println(data)

  }

}
