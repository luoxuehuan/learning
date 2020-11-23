package com.dtwave.dipper.asset.sql

import java.util

import org.apache.spark.sql.SparkSession


/**
  *
  *
  * 1.模拟一个表
  * 4个普通字段
  * bigint
  *
  *
  *
  * 1个分区字段
  *
  *
  *
  * 30个分区的数据，每个分区10条数据。
  *
  *
  *
  *
  *
  *
  *
  *
  * create table quality_all(id string,age bigint,name string,score bigint,create_time string ) PARTITIONED by (ds string);
  **
  *
  *create table quality_one(id string,age bigint,name string,score bigint ,create_time string)
  **
  *
  *spark-sql> desc quality;
  *id	string	NULL
  *age	bigint	NULL
  *name	string	NULL
  *score	bigint	NULL
  *ds	string	NULL
  *# Partition Information
  *# col_name	data_type	comment
  *ds	string	NULL
  **
  *
  *insert into quality_all partition (ds=20200228) select '1',20,'bob',99,'2020-02-28 00:00:00';
*insert into quality_all partition (ds=20200227) select '1',20,'bob',99,'2020-02-27 00:00:00';
*insert into quality_all partition (ds=20200226) select '1',20,'bob',99,'2020-02-26 00:00:00';
*insert into quality_all partition (ds=20200225) select '1',20,'bob',99,'2020-02-25 00:00:00';
*insert into quality_all partition (ds=20200224) select '1',20,'bob',99,'2020-02-24 00:00:00';
*insert into quality_all partition (ds=20200223) select '1',20,'bob',99,'2020-02-23 00:00:00';
*insert into quality_all partition (ds=20200222) select '1',20,'bob',99,'2020-02-22 00:00:00';
  **
 *insert into quality_one select '1',21,'bob1',99,'2020-02-22 00:00:00';
*insert into quality_one select '2',22,'bob2',99,'2020-02-28 00:00:00';
*insert into quality_one select '3',23,'bob3',99,'2020-02-27 00:00:00';
*insert into quality_one select '3',23,'bob3',99,'2020-02-27 00:00:00';
*insert into quality_one select '4',24,'bob4',99,'2020-02-26 00:00:00';
*insert into quality_one select '4',24,'bob',99,'2020-02-26 00:00:00';
*insert into quality_one select '5',25,'bob5',99,'2020-02-25 00:00:00';
*insert into quality_one select '6',26,'bob6',99,'2020-02-24 00:00:00';
*insert into quality_one select '7',27,'bob7',99,'2020-02-23 00:00:00';
*insert into quality_one select '8',28,'bob8',99,'2020-02-22 00:00:00';
  **
 *select * from quality_all;
  *
 *
  * 表级规则
  *
  * Step1
  *  获取统计周期内的数据值[能不能直接拿之前的统计信息做计算]
  *   1.1 获取时间周期（1.时间范围  2. 时间列表 （循环多条sql计算,单条sql 做case when） 3. 单独一个时间）
  *   1.2 获取计算方式
  *   1.3 获取计算公式
  *   1.4 获取计算结果
  *
  *
  * Step2
  *   获取对比对象的数据值
  *
  * Step3
  *   对比
  *
  *
  *
  *
  * 1.新增记录条数（业务数据记录的创建时间）
  *   方差波动 昨日/最近7天/最近30天/最近60天/截止昨日
  *   波动率
  *   平均值
  *   最大值
  *   最小值
  *   总和
  *
  *   思路：
  *   1.要有业务创建时间
  *   2.要有该业务时间最小的时间
  *   3.要有该业务时间最大的时间
  *   4.计算业务时间每一天的记录条数
  *   5.
  *
  * 2.新增存储量
  *   方差波动
  *   波动率
  *   平均值
  *   最大值
  *   最小值
  *   总和
  *
  * 3.总存储量
  *   波动
  *
  * 4.表规范性
  *   表命名
  *   字段命名不规范数
  *   字段命名不规范率
  *
  */
object TableRule {


  /**
    * test入口
    * @param args
    */
  def main(args: Array[String]): Unit = {

    val spark = SparkSession
      .builder()
      .appName(s"MegrezApp")
      .enableHiveSupport()
      .master("local[2]")
      .getOrCreate()

    spark.sparkContext.setLogLevel("error")

   // spark.sql("select * from quality_one").show(10)
    //根据时间字段计算最近N天的记录条数
    val N = 7;

    val valueList: util.ArrayList[Long] = new util.ArrayList[Long]()
    //val valueList:List[Int] = new util.ArrayList[Int]()
    for ( x <- 1 to N){
      //print(x)
      val result = TimeRangeUtil.timeRangePreviousDays("YYYY-MM-dd HH:mm:ss","create_time",x)
      println(result)

      val size = spark.sql(s"select * from quality_one where $result").count()
      println("size"+size)
      valueList.add(size)

      //MathUtils.standardDiviation(valueList)
      //获得一个size的列表并计算方差
    }










  }

}
