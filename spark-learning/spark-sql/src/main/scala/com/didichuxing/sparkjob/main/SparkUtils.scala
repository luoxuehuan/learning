package com.didichuxing.sparkjob.main

import com.didichuxing.sparkjob.model.{DataAnalyseCreateDTO, FunctionEnum}
import org.apache.commons.lang3.StringUtils
import org.apache.spark.sql.DataFrame

import scala.collection.JavaConversions._
import scala.collection.mutable


object SparkUtils {

  /**
    * DataFrame转List<Map<String,Object>>结构
    *
    * @param dataframe
    * @return
    */
  def getDataMap(dataframe: DataFrame): mutable.Buffer[Map[String, String]] = {
    //get schema list from dataframe
    val schemaList = dataframe.schema.map(_.name).zipWithIndex
    //val map = new util.HashMap[java.lang.String, Object]()
    val listMap = dataframe.rdd.map(row =>
      //here rec._1 is column name and rce._2 is index
      schemaList.map(
        rec => (rec._1, String.valueOf(row(rec._2)))).toMap
    ).collect
    listMap.toBuffer
    //    map.put("1","2")
    //    dataframe.rdd.collect().map(row =>
    //      schemaList.foreach(
    //        rec =>  map.put(rec._1,row(rec._2))
    //    )


    //print 展示
    //    listMap.foreach(println)
    //    listMap
  }

  /**
    * 创建SQL list 工具
    */

  def getExecSqlList(): java.util.ArrayList[String] = {
    val sqlArray = new java.util.ArrayList[String]
    sqlArray
  }


  def getFunctionColumn(columnName: String, functionName: String): String = {

    //针对count distinct 要做额外处理
    //    val functionColumn =
    //      s"""$functionName($columnName) as exec_result_$functionName$columnName""".stripMargin

    //根据functionName进行不同的组装
    // 1.你为啥进群？ 2. 看下进群须知。（看完觉得不适合你的话、可以尽早退群）


    val functionColumn =
      s"""$functionName($columnName) as $functionName"""+"""_exec_result_""" + s"""$columnName""".stripMargin

    functionColumn
  }


  def getFunctionColumnSqlFromFunctionEnum(columnName: String, function: FunctionEnum, tableName: String): String = {

    //针对count distinct 要做额外处理
    //    val functionColumn =
    //      s"""$functionName($columnName) as exec_result_$functionName$columnName""".stripMargin

    //根据functionName进行不同的组装

    val columnResult = StringUtils.replaceAll(function.getFunctionTemplate, "columnName", columnName)
    //println(columnResult)

    var sqlResult =
      s"""select $columnResult as ${function.getFunctionName}_exec_result_""" + s"""$columnName from $tableName """.stripMargin

    if(function.getFunctionLevel == 3){
      sqlResult =
        s"""select $columnResult from $tableName """.stripMargin
    }
    //println(sqlResult)
    if (StringUtils.isNoneEmpty(function.getFunctionWhere)) {
      val whereResult = StringUtils.replaceAll(function.getFunctionWhere, "columnName", columnName)
      sqlResult = sqlResult + " where " + whereResult;
    }
    println(sqlResult)
    sqlResult
  }

  /**
    * 获得执行SQL 执行并get出指标值
    *
    * 1.可基于统计信息得到结果的函数(是否开启统计模式)
    * 2.可合并成一个函数
    * 3.不可合并到统一sql的,同字段内可合并函数
    * 4.必须单独执行函数
    *
    *
    * 1.记录数 count
    * 2.唯一值 uniqueCount
    * 3.最小值 min
    * 4.最大值 max
    * 5.平均值 avg
    * 6.标准差 standardDeviation
    * 7.null值数 nullCount
    * 8.零值数 zeroCount
    *
    * --- 基础统计
    * 记录数count
    * 空值数count is null
    * 空值率count is null / count
    * 最小值min
    * 最大值max
    * --- 高级统计
    * 唯一值 count(distinct(age))
    * 平均值 avg(age)
    * 标准差 sqrt(var_pop(age))
    * 非零最小值 min is not null
    * 非零最大值 max is not null
    * 数据分布
    * 直方图 等距分布 、 等频分布
    * 枚举值 group by count distinct limit 100
    *
    * @param tempTableName
    * @return 类型(单行数据) 或多行数据？ sql
    */
  def getScalaExecSqlListOld(tempTableName: String, config: DataAnalyseCreateDTO): java.util.List[String] = {
    val sqlList = new java.util.ArrayList[String]()
    val functionColumnList = new java.util.ArrayList[String]()
    println(tempTableName)
    val basicFuncArray = Array("min", "max", "avg", "count")

    val analyseColumnConfigList = config.getAnalyseColumnConfigList
    for (i <- 0 to analyseColumnConfigList.size() - 1) {
      val columnConfig = analyseColumnConfigList.get(i)
      //      if(columnConfig.getBaseStatistics == 1){
      //        val columnName = columnConfig.getColumnName
      //        for(functionName <- basicFuncArray){
      //          //是否是可合并function
      //
      //          //是否是可统计stat
      //          val functionColumn = getFunctionColumn(columnName,functionName)
      //          functionColumnList.add(functionColumn)
      //        }
      //      }

      if (columnConfig.getBaseStatistics == 1) {
        val columnName = columnConfig.getColumnName
        val functionIdList = FunctionEnum.getFunctionListByLevel(1)

        for (functionId <- functionIdList) {
          //是否是可合并function
          val function = FunctionEnum.getFunctionListByFunctionId(functionId)
          //是否是可统计stat
          val resultSql = getFunctionColumnSqlFromFunctionEnum(columnName, function, tempTableName)
          //functionColumnList.add(functionColumn)

          sqlList.add(resultSql)
        }
      }

      if (columnConfig.getAdvancedStatistics == 1) {
        val columnName = columnConfig.getColumnName
        val functionIdList = FunctionEnum.getFunctionListByLevel(2)
        for (functionId <- functionIdList) {
          //是否是可合并function
          val function = FunctionEnum.getFunctionListByFunctionId(functionId)
          //是否是可统计stat
          val resultSql = getFunctionColumnSqlFromFunctionEnum(columnName, function, tempTableName)
          //functionColumnList.add(functionColumn)
          sqlList.add(resultSql)
        }
      }
    }
    sqlList
  }


  /**
    * 获得执行的sql list
    *
    * @param tempTableName
    * @param config
    * @return
    */
  def getScalaExecSqlList(tempTableName: String, config: DataAnalyseCreateDTO): java.util.List[ExecSql] = {
    val sqlList = new java.util.ArrayList[ExecSql]()
    val analyseColumnConfigList = config.getAnalyseColumnConfigList
    for (i <- 0 to analyseColumnConfigList.size() - 1) {
      val columnConfig = analyseColumnConfigList.get(i)
      if (columnConfig.getBaseStatistics == 1) {
        val columnName = columnConfig.getColumnName
        val functionIdList = FunctionEnum.getFunctionListByLevel(1)
        for (functionId <- functionIdList) {
          //是否是可合并function
          val function = FunctionEnum.getFunctionListByFunctionId(functionId)
          //是否是可统计stat
          val resultSql = getFunctionColumnSqlFromFunctionEnum(columnName, function, tempTableName)
          sqlList.add(ExecSql.apply(resultSql,columnName, function))
        }
      }
      if (columnConfig.getAdvancedStatistics == 1) {
        val columnName = columnConfig.getColumnName
        val functionIdList = FunctionEnum.getFunctionListByLevel(2)
        for (functionId <- functionIdList) {
          //是否是可合并function
          val function = FunctionEnum.getFunctionListByFunctionId(functionId)
          //是否是可统计stat
          val resultSql = getFunctionColumnSqlFromFunctionEnum(columnName, function, tempTableName)
          //functionColumnList.add(functionColumn)
          sqlList.add(ExecSql.apply(resultSql,columnName, function))
        }
      }

      val supportType = Seq("string","bigint")
      //需要判断字段类型 能否枚举
      if(columnConfig.getColumnType !=null && supportType.contains(columnConfig.getColumnType.toLowerCase) ){
        if (columnConfig.getDataDistribution == 1) {
          val columnName = columnConfig.getColumnName
          val functionIdList = FunctionEnum.getFunctionListByLevel(3)
          for (functionId <- functionIdList) {
            //是否是可合并function
            val function = FunctionEnum.getFunctionListByFunctionId(functionId)
            //是否是可统计stat
            val resultSql = getFunctionColumnSqlFromFunctionEnum(columnName, function, tempTableName)
            //functionColumnList.add(functionColumn)
            sqlList.add(ExecSql.apply(resultSql,columnName, function))
          }
        }
      }else{
        //字段类型无法识别 不进行统计
        println("字段类型无法识别 不进行统计")
      }

    }
    sqlList
  }


  /**
    * 获得执行的sql list
    *
    * @param tempTableName
    * @param config
    * @return
    */
  def getScalaExecDisList(tempTableName: String, config: DataAnalyseCreateDTO): java.util.List[ExecSql] = {
    val sqlList = new java.util.ArrayList[ExecSql]()
    val analyseColumnConfigList = config.getAnalyseColumnConfigList
    for (i <- 0 to analyseColumnConfigList.size() - 1) {
      val columnConfig = analyseColumnConfigList.get(i)
      val supportType = Seq("bigint","int")
      //需要判断字段类型 能否枚举
      if(columnConfig.getColumnType !=null && supportType.contains(columnConfig.getColumnType.toLowerCase) ){
        if (columnConfig.getDataDistribution == 1) {
          val columnName = columnConfig.getColumnName
          val functionIdList = FunctionEnum.getFunctionListByLevel(4)
          for (functionId <- functionIdList) {
            //是否是可合并function
            val function = FunctionEnum.getFunctionListByFunctionId(functionId)
            //是否是可统计stat
            //val resultSql = getFunctionColumnSqlFromFunctionEnum(columnName, function, tempTableName)
            //functionColumnList.add(functionColumn)
            sqlList.add(ExecSql.apply("",columnName, function))
          }
        }
      }else{
        //字段类型无法识别 不进行统计
        println("字段类型无法识别 不进行统计")
      }

    }
    sqlList
  }

}


case class ExecSql(sql:String,columnName:String,function:FunctionEnum)