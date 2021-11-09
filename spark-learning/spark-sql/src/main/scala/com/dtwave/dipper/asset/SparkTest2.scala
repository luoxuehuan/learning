package com.dtwave.dipper.asset

import org.apache.spark.sql.SparkSession
import org.joda.time.DateTime



/**
  * 美团 24K*15.5
  * 网易 19K*16
  * 不知道啥公司 25K*15
  */
object SparkTest2 {

  def main(args: Array[String]): Unit = {


    val spark = SparkSession.builder.appName("AssetQcApp").enableHiveSupport.master("local[2]").getOrCreate
    spark.sparkContext.setLogLevel("error")

    import spark.implicits._
    var dataBase = "data_search_test"
    var tableName = "test_table_0"
    var startDay = 0
    var endDay = 4
    var recordEveryDay = 10

    if(args.length > 4){
      dataBase = args.apply(0)
      tableName = args.apply(1)
      startDay = Integer.valueOf(args.apply(2))
      endDay =Integer.valueOf(args.apply(3))
      recordEveryDay = Integer.valueOf(args.apply(4))
    }else{
      println("请输入 1.数据库asset_qc_test 2.tableName 3.startDay 4.endDay 5.recordEveryDay ")
      println(s"使用系统默认值 ： $dataBase ，$tableName ，$startDay，$endDay，$recordEveryDay")
    }
    println("1.生成模拟数据 表,包含string时间字段,Date类型字段,数字类型字段，字符串类型字段，毫秒时间字段,秒时间字段,ds分区字段")
    spark.sql(s"create database if not EXISTS $dataBase")
    spark.sql(s"use $dataBase")
    val names = tableName.split(",")
    val size = names.size
    for(si <- 0 to size-1){
      val gname = names.apply(si)
      println("开始,表："+names.apply(si)+" 的数据生成")
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
           |COMMENT '数据探查测试专用'
           |PARTITIONED BY ( ds STRING )
           |STORED AS orc
           |""".stripMargin
      spark.sql(sql)

      for( date <- startDay to endDay){
        val list = new java.util.ArrayList[TestDataClass]
        val stringTime = getDayBeforeByFormat(date,"yyyyMMdd");
        for(i <- 0 to recordEveryDay){
          val age = scala.util.Random.nextInt(100);
          var name = "name"+i;
          val weight = scala.util.Random.nextInt(200)
          val mTimeStamp = System.currentTimeMillis()/1000;
          val millTimeStamp = System.currentTimeMillis();
          val date_col = getDayBeforeByFormat(date,"yyyy-MM-dd");
          if(i == 2 || i == 3 ){
            val d = TestDataClass.apply(i,null,age,weight,mTimeStamp,millTimeStamp,null,date_col)
            list.add(d)
          }else{
            if(i == 4){
              //全英文，无数字
              name ="english"
            }
            val d = TestDataClass.apply(i,name,age,weight,mTimeStamp,millTimeStamp,stringTime,date_col)
            list.add(d)
          }
        }
        spark.createDataset(list).toDF("id","name","age","weight","mTimeStamp","millTimeStamp","stringTime","date_col")
          .createOrReplaceTempView(s"data_asset_temp_$stringTime")
        spark.sql(s"select * from data_asset_temp_$stringTime").show(10)
        val sql  =
          s"""
             |insert into $gname partition(ds = '$stringTime')
             |select id,name,age,weight,mTimeStamp,millTimeStamp,stringTime,date_col from data_asset_temp_$stringTime
             |""".stripMargin
        println("执行的sql: \n"+sql)
        spark.sql(sql)
      }
    }
    println("2.生成20天的模拟数据，每天10条数据")
  }

  /**
    * 返回指定format的N前的日期字符串
    *
    * @return
    */
  def getDayBeforeByFormat(days: Integer, format: String): String = {
    val dateTime = new DateTime()
    dateTime.minusDays(days).toString(format)
  }


}

case class TestDataClass(id:Int,name:String,age:Int,weight:Int,mTimeStamp:Long,millTimeStamp:Long,stringTime:String,date_col:String)

