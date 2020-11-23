package com.dtwave.dipper.asset.sql

import org.apache.spark.sql.SparkSession

/**
  * 计算表和给定时间范围内的条数
  */
object RecordCompute {

  /**
    * 计算某个表某个时间条件下的记录
    * @param spark
    * @param tableName
    * @param timeRange
    * @return
    */
  def countRecord(spark:SparkSession,tableName:String,timeRange:String): Long = {
    //计算
    val count = spark.sql(s"select 1 from $tableName where $timeRange").count()
    //返回
    count
  }


  /**
    * 计算某个字段的最小值
    * 计算某个字段的最大值
    *
    *
    * 计算某个表存储量的最小值
    * 计算某个表存储量的最大值
    *
    */



}
