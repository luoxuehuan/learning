package com.hulb.spark.sql

import org.apache.spark.rdd.HadoopRDD
import net.sf.cglib.proxy.Enhancer
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.execution.datasources.FileFormatWriter

object SqlHdfsTest {
  def main(args: Array[String]): Unit = {
    //测试spark sql读写文件鉴权
    //使用动态代理截取到spark 使用hadoop api
    val spark = SparkSession
      .builder()
      .appName("Spark Hive Example")
      .master("local")
      .enableHiveSupport()
      .getOrCreate()

    import com.hulb.spark.sql.CglibProxy
    val cglibProxy = new CglibProxy

    val enhancer = new Enhancer() //主要的增强类
   // enhancer.setSuperclass(HadoopRDD.getClass)//设置父类，被增强的类

    enhancer.setCallback(cglibProxy) //回调对象
    enhancer.create()

    import spark.sql
    sql(
      """
        |INSERT INTO TABLE sink SELECT source_key,source_value FROM source UNION SELECT * FROM source_1
      """.stripMargin)


    spark.sql(
      """
        |SELECT source_1_key from ( SELECT * FROM source_1) t
      """.stripMargin).explain(true)

    spark.sql(
      """
        |SELECT source_1_value FROM source_1
      """.stripMargin).explain(true)
    spark.stop()
  }

}
