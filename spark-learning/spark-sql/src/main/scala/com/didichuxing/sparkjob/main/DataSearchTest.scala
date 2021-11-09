package com.didichuxing.sparkjob.main

import com.didichuxing.sparkjob.model.{AnalyseColumnConfig, DataAnalyseCreateDTO}
import com.didichuxing.sparkjob.util.SparkUtils
import org.apache.spark.ml.feature.Bucketizer
import org.apache.spark.sql.SparkSession

import java.util
import scala.collection.mutable
import scala.collection.immutable
import scala.collection.JavaConversions._
import scala.collection.JavaConverters.mapAsJavaMapConverter

/**
  * 美团 24K*15.5
  * 网易 19K*16
  * 不知道啥公司 25K*15
  */
object DataSearchTest {

  def getTestDataAnalyse():DataAnalyseCreateDTO = {
    val dataAnalyseCreateDTO = new DataAnalyseCreateDTO
    dataAnalyseCreateDTO.setStorageName("data_search_test.test_table_0")
    val  columnConfig = new AnalyseColumnConfig
    columnConfig.setColumnName("age");
    columnConfig.setBaseStatistics(1)
    columnConfig.setAdvancedStatistics(1)

    val  columnConfig2 = new AnalyseColumnConfig
    columnConfig2.setColumnName("name");
    columnConfig2.setBaseStatistics(1)
    columnConfig2.setAdvancedStatistics(1)
    dataAnalyseCreateDTO.getAnalyseColumnConfigList.add(columnConfig)
    dataAnalyseCreateDTO.getAnalyseColumnConfigList.add(columnConfig2)
    dataAnalyseCreateDTO
  }

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder
      .appName("AssetQcApp")
      .enableHiveSupport
      .master("local[2]")
      .getOrCreate
    spark.sparkContext.setLogLevel("error")

    val tableName = "data_search_test.test_table_0"
    val partitionName = "ds=20211102"
    val fieldName = "age";

    //两种结构 全部铺平
    // 按字段聚合结构
    // key1 = column
    // key2 = min or max
    val columnStatMap  = new mutable.HashMap[String,mutable.HashMap[String,Any]]

    //铺平结构 key = 字段_min
    val onwRowStatMap  = new mutable.HashMap[String,Any]
    val dataAnalyse = getTestDataAnalyse()


    /**
      * 第一步 数据读取模块
      *
      *
      * 第二步 解析orc读统计信息模块
      *
      *
      * 第三步 SQL生成模块(需要重新算的指标进行生成 生成多条SQL执行)
      *
      *
      * 第四步 计算模块
      *
      *
      * 第五步 计算结果综合模块（一个Map结构 存储的是;字段_指标_value Any结构 转String吧）
      *
      *
      * 第六步 结果发送回地图或存储到ES中
      *
      *
      */
    val partitionDataFrame = spark.sql(s"select * from $tableName where $partitionName")
    partitionDataFrame.cache().createTempView("tempTable")
    //SparkUtils.getDataMap(partitionDataFrame)
    val sqlList = SparkUtils.getScalaExecSqlList("tempTable",dataAnalyse)
    //val allResult = new java.util.ArrayList[Map[String, Any]];
   // val allResultMap = java.util.ArrayList[java.util.HashMap[String, Any]];
    import scala.collection.JavaConverters._
    val javaMap = new util.HashMap[String,Any]()
    for(j <- 0 to sqlList.size()-1){
        val sqlExecResultDataFrame = spark.sql(sqlList.get(j))
        sqlExecResultDataFrame.show()
        val resultMapList = SparkUtils.getDataMap(sqlExecResultDataFrame)
        for(row <- resultMapList){
          val javaRow = row.asJava
         // row.toMap[String,Object].asJava
          javaMap.putAll(javaRow)
        }

    }
    println(javaMap)


    /**
      * min max avg count
      */
    //spark.sql(s"select count(age),max(age),min(age),avg(age) from $tableName where $partitionName").show(10)

    /**
      * 空值数
      */
    //spark.sql(s"select count(1) from $tableName where $partitionName and name is null").show(10)

    /**
      * 什么字段类型,支持什么计算需要维护一下。
      *
      * 唯一值
      * 非零最小值
      * 非零最大值
      */
    //spark.sql(s"select min($fieldName),max($fieldName) from $tableName where $partitionName and $fieldName != 0").show(10)
    //spark.sql(s"select $fieldName,count($fieldName) as count from $tableName where $partitionName group by $fieldName having count = 2").show(10)

    /**
      * 方差标准差
      */
    //spark.sql(s"select var_pop(age),sqrt(var_pop(age)) from $tableName where $partitionName").show(10)
    //spark.sql(s"select count(distinct $fieldName) from $tableName where $partitionName").show(10)

    /**
      * 唯一值分布[count(distinct(age))]
      */
   // val pop = spark.sql(s"select var_pop(age),sqrt(var_pop(age)) from $tableName where $partitionName").collect().apply(0).get(0)
    //println(pop)


    /**
      * 数据范围分布
      */

  }


  def bucket(spark:SparkSession,tableName:String,partitionName:String) = {
    val ageDataFrame = spark.sql(s"select age from $tableName where $partitionName")
    var feature = "age"
    var feature_new = "age_bucketizer"
    //分箱点[前闭后开]

    //得到min和max之后,再进行分桶。bigint.
    //分桶怎么确定呢？ 根据最小值和最大值确定吗？min 和 max
    var splits: Array[Double] = Array(Double.MinValue, 60, Double.MaxValue)
    //数据预处理
    var dataset = ageDataFrame.select("age")
    //特征分桶
    var transform = new Bucketizer()
      .setInputCol(feature) //待变换的特征
      .setOutputCol(feature_new) //变换后的特征名称
      .setSplits(splits) //分箱点[前闭后开]
      .setHandleInvalid("skip") //无效条目的处理方式[跳过]
      .transform(dataset)
    //show
    transform.show()

  }

}
