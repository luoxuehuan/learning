package com.dtwave.dipper.asset.sql

import org.apache.spark.sql.SparkSession

object SparkSqlExecutor {

  def executor(spark:SparkSession,sql:String):String={
    val coll = spark.sql(sql).collect()
      val value = coll.apply(0).get(0)
      if(value!=null){
        value.toString
      }else{
        "0"
      }



  }

}
