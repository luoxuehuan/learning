package com.dtwave.dipper.asset.sql

import org.apache.spark.sql.SparkSession

/**
  * 计算分区的存储量
  */
object StorageCompute {

  /**
    * 计算某个表某个时间条件分区的存储量
    * @param spark
    * @param tableName
    * @param timeRange
    * @return
    */
  def countRecord(spark:SparkSession,tableName:String,timeRange:String): Long = {

    //根据给定分区范围，获得有哪些分区。
    //如果是非分区表。直接获取整个表的存储量。

    //计算
    val count = spark.sql(s"select 1 from $tableName where $timeRange").count()
    //返回
    count
  }
}
