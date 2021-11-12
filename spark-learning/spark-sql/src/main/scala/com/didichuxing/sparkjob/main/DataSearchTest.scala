package com.didichuxing.sparkjob.main

import com.didichuxing.sparkjob.exec.ExecBucket
import com.didichuxing.sparkjob.util.{JsonUtils, SparkUtils}
import org.apache.spark.sql.SparkSession
import org.apache.spark.storage.StorageLevel

import java.util
import scala.collection.JavaConversions._

/**
  * 美团 24K*15.5
  * 网易 19K*16
  * 不知道啥公司 25K*15
  */
object DataSearchTest {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder
      .appName("DataProfile")
      .enableHiveSupport
      .master("local[2]")
      .getOrCreate

    val taskConfigString = JsonUtils.parseToJsonString(MockData.getTaskConfig())
    val task = JsonUtils.parseToTask(taskConfigString)
    spark.sparkContext.setLogLevel("error")
    //两种结构 全部铺平
    // 按字段聚合结构
    // key1 = column
    // key2 = min or max
    //铺平结构 key = 字段_min
    //val task = MockData.getTaskConfig
    val dataAnalyse = task.getDataAnalyseCreateDTO
    /**
      * 第一步 数据读取模块
      * 第二步 解析orc读统计信息模块
      * 第三步 SQL生成模块(需要重新算的指标进行生成 生成多条SQL执行)
      * 第四步 计算模块
      * 第五步 计算结果综合模块（一个Map结构 存储的是;字段_指标_value Any结构 转String吧）
      * 第六步 结果发送回地图或存储到ES中
      */
    val partitionDataFrame = spark.sql(s"select * from ${dataAnalyse.getStorageName} where ${dataAnalyse.getPartitionName}")
    partitionDataFrame.persist(StorageLevel.MEMORY_AND_DISK).createTempView("tempTable")
    val sqlList = SparkUtils.getScalaExecSqlList("tempTable", dataAnalyse)
    val distributionList = SparkUtils.getScalaExecDisList("tempTable", dataAnalyse)

    import scala.collection.JavaConverters._
    val javaMap = new util.HashMap[java.lang.String, java.lang.String]()
    val dataDistribution = new util.HashMap[String, util.HashMap[String,String]]()
    for (j <- 0 to sqlList.size() - 1) {
      val sqlExecResultDataFrame = spark.sql(sqlList.get(j).sql)
      sqlExecResultDataFrame.show()
      val resultMapList = SparkUtils.getDataMap(sqlExecResultDataFrame)
      if(sqlList.get(j).function.getFunctionLevel.equals(3)){
        //统计类多行 枚举值 转换结构
        val distribution = new util.HashMap[String, String]()
        for (row <- resultMapList) {
          val javaRow = row.asJava
          val key = javaRow.get(sqlList.get(j).columnName)
          val value = javaRow.get(sqlList.get(j).columnName+"_count")
          distribution.put(key,value)
        }
        dataDistribution.put(sqlList.get(j).columnName+"_distribution",distribution)
      }else{
        for (row <- resultMapList) {
          val javaRow = row.asJava
          // row.toMap[String,Object].asJava
            javaMap.putAll(javaRow)
        }
      }
    }
    //println(javaMap)
    println(dataDistribution)
    val map = transFromMapGroupByColumnName(javaMap)

    for (j <- 0 to distributionList.size() - 1) {
      val column = distributionList.get(j).columnName
      val min = map.get(column).get("min")
      val max = map.get(column).get("max")
      val minDouble = min.toDouble
      val maxDouble = max.toDouble
      ExecBucket.bucket(spark,"tempTable",column,minDouble,maxDouble)
    }


    JsonUtils.parseToJsonObject(map)
    JsonUtils.parseToJsonObject(dataDistribution)
  }


  def transFromMapGroupByColumnName(javaMap: util.HashMap[String, String]):util.HashMap[String, util.HashMap[String, String]] = {
    val result = new util.HashMap[String, util.HashMap[String, String]]
    for(map <- javaMap){
      val key = map._1
      val value = map._2
      val functionAndColumn = key.split("_exec_result_")
      val functionName = functionAndColumn.apply(0)
      val columnName = functionAndColumn.apply(1)
      if(result.containsKey(columnName)){
        result.get(columnName).put(functionName,value)
      }else{
        val temp = new util.HashMap[String, String]
        temp.put(functionName,value)
        result.put(columnName,temp)
      }
    }
    result
  }


}
