package com.dtwave.dipper.asset.sql

object SqlGenerator {

  def generatorDistinctCount(tableName:String,fieldName:String,timeRange:String):String={
    val sql =
      s"""
         |select sum(count)-count(1) as distinct_count
         |from
         |(select $fieldName,count($fieldName) as count from $tableName where group by $fieldName)
         |where count>1""".stripMargin
    println(sql)
    sql
  }


  def generatorOnly(tableName:String,fieldName:String,timeRange:String):String={
    val sql =
      s"""
         |select count(1) as only_count
         |from
         |(select $fieldName,count($fieldName) as count from $tableName where group by $fieldName)
         |where count=1""".stripMargin
    println(sql)
    sql
  }

  def generatorRegex(tableName:String,fieldName:String,regex:String,timeRange:String):String={
    val sql =
      s"""
         |select count(1) as count
         |from
         |(select no_match_count($fieldName,'$regex') as match from $tableName)
         |where match = 1""".stripMargin

    println(sql)
    sql
  }

  def generatorNormalMathCompute(tableName:String,fieldName:String,timeRange:String,mathFunction:String):String={
    val sql =
      s"""
         |select $mathFunction($fieldName) as match from $tableName where $timeRange
         |""".stripMargin
    println(sql)
    sql
  }

}
