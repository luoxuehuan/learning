package com.dtwave.dipper.asset

import org.apache.spark.sql.SparkSession


/**
  * 美团 24K*15.5
  * 网易 19K*16
  * 不知道啥公司 25K*15
  */
object SparkTest {

  def main(args: Array[String]): Unit = {


    val spark = SparkSession.builder.appName("AssetQcApp").enableHiveSupport.master("local[2]").getOrCreate
    spark.sparkContext.setLogLevel("error")

    spark.sql("show databases").show(10)
    //spark.sql("select count(1) from my_test_0326.test_table").show(10)


    import spark.implicits._
    var dataBase = "data_search_test"
    var tableName = "test_table_test"
    var start = 0
    var end = 10

    if (args.length > 2) {
      dataBase = args.apply(0)
      tableName = args.apply(1)
      start = Integer.valueOf(args.apply(2))
      end = Integer.valueOf(args.apply(3))
    } else {
      println("请输入 1.数据库asset_qc_test 2.tableName 3.size ")
      println(s"使用系统默认值 ： $dataBase ，$tableName ，$start")
    }
    println("1.生成模拟数据 表,包含string时间字段,Date类型字段,数字类型字段，字符串类型字段，毫秒时间字段,秒时间字段,ds分区字段")
    spark.sql(s"create database if not EXISTS $dataBase")
    spark.sql(s"use $dataBase")
    for (si <- start to end) {
      val gname = tableName + "_" + si
      print(gname)
      val sql =
        s"""
           |CREATE TABLE IF NOT EXISTS $gname
           |(
           |    id  BIGINT COMMENT '用户ID'
           |    , name    STRING COMMENT '学生姓名'
           |    , age    BIGINT COMMENT '年龄'
           |    , weight    BIGINT COMMENT '体重'
           |    , mTimeStamp BIGINT COMMENT '秒级时间戳'
           |    , millTimeStamp BIGINT COMMENT '毫秒级时间戳'
           |    , stringTime STRING COMMENT '字符串型自定义时间字段'
           |    , date_col  DATE COMMENT 'Date类型时间字段'
           |)
           |COMMENT '数据质量测试专用'
           |PARTITIONED BY ( ds STRING )
           |STORED AS TEXTFILE
           |""".stripMargin

      print(sql)
      spark.sql(sql)
    }
    val gname = tableName + "_" + start
    spark.sql(s"select * from $gname").show(10)
    println("2.生成20天的模拟数据，每天10条数据")
  }

}
