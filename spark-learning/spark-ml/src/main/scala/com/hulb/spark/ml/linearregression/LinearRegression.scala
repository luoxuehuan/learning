package com.hulb.spark.ml.linearregression

import com.hulb.spark.config.Constant
import org.apache.spark.sql.SparkSession

/**
  * Created by hulb on 17/4/8.
  */
object LinearRegression {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local").getOrCreate()
    val sc = spark.sparkContext
    sc.setLogLevel("warn")

    /**
      * 第一步,读数据
      */
    val data_path = Constant.data_prefix + Constant.linear_regression
    val data = sc.textFile(data_path)


  }

}
