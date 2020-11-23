//package com.hulb.spark.sql
//
//import net.sf.cglib.proxy.Enhancer
//import org.apache.spark.rdd.HadoopRDD
//import org.apache.spark.sql.SparkSession
//
//object SqlHdfsTest1 {
//  def main(args: Array[String]): Unit = {
//    //测试spark sql读写文件鉴权
//    //使用动态代理截取到spark 使用hadoop api
//    val spark = SparkSession
//      .builder()
//      .appName("Spark Hive Example")
//      .master("local")
//      .enableHiveSupport()
//      .getOrCreate()
//
//    val cglibProxy = new CglibProxy1
//
//    val enhancer = new Enhancer() //主要的增强类
//    enhancer.setSuperclass(HadoopRDD.getClass)//设置父类，被增强的类
//
//    enhancer.setCallback(cglibProxy) //回调对象
//    enhancer.create()
//
//    import spark.sql
//    sql(
//      """
//        |INSERT INTO TABLE sink SELECT source_key,source_value FROM source UNION SELECT * FROM source_1
//      """.stripMargin)
//    spark.stop()
//  }
//
//}
