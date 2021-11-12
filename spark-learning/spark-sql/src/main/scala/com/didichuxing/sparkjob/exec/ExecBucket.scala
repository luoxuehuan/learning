package com.didichuxing.sparkjob.exec

import com.didichuxing.sparkjob.util.SparkUtils
import org.apache.spark.ml.feature.Bucketizer
import org.apache.spark.sql.SparkSession

import scala.collection.mutable.ArrayBuffer
import scala.collection.JavaConversions._
import scala.collection.JavaConverters.collectionAsScalaIterableConverter

object ExecBucket {

  /**
    * 只有数字类型可以进行分箱操作
    * @param spark
    * @param tableName
    * @param partitionName
    */
  def bucket(spark:SparkSession,tableName:String,columnName:String,min:Double,max:Double) = {
    val ageDataFrame = spark.sql(s"select $columnName from $tableName")
    //分箱点[前闭后开]
    //得到min和max之后,再进行分桶。bigint.
    //分桶怎么确定呢？ 根据最小值和最大值确定吗？min 和 max
    //val splitArray: Array[Double] = Array(Double.MinValue, 60, Double.MaxValue)
    val list = getRange(min,max,5);
    //数据预处理
    val dataset = ageDataFrame.select(columnName)
    val outputCol = columnName+"_exec_result_bucket";
    //特征分桶
    val transform = new Bucketizer()
      .setInputCol(columnName) //待变换的特征
      .setOutputCol(outputCol) //变换后的特征名称
      .setSplits(list) //分箱点[前闭后开]
      .setHandleInvalid("skip") //无效条目的处理方式[跳过]
      .transform(dataset)
    //show
    transform.show()

    transform.createTempView("temp_bucket")
    val result = spark.sql(s"select $outputCol,count(1) as count from temp_bucket group by $outputCol order by $outputCol")
    //取的map之后 与array进行转换
    val mapResult = SparkUtils.getDataMap(result)
      for (row <- mapResult) {
        val bucket = row.get(outputCol)
        val bucketIndex = bucket.get.toDouble.toInt
        val bucketIndexToString = list.apply(bucketIndex)+ " ----- " + list.apply(bucketIndex+1)
        val count = row.get("count")

        println(bucketIndexToString + " : "+ count)
        // row.toMap[String,Object].asJava
      }
  }


  /**
    * 根据min 和 max 获取区间
    * @param min
    * @param max
    * @param size
    * @return
    */
  def getRange(min:Double,max:Double,size:Integer):Array[Double] = {
    val splitList = new java.util.ArrayList[Double]()
    splitList.add(Double.MinValue)
    if(max > min){
      splitList.add(min)
      val length = max-min
      val rangeSize = length / size
      for(i <- 1 to size-1){
        val value = min + rangeSize * i
        splitList.add(value)
      }
      splitList.add(max)
    }else{
      splitList.add(0D)
    }
    splitList.add(Double.MaxValue)
    val splitArray: Array[Double]  = new Array[Double](splitList.size())
    for(i <- 0 to splitList.size()-1){
      splitArray(i) = splitList.get(i)
    }
    splitArray.foreach(println)
    splitArray
  }

  def main(args: Array[String]): Unit = {
    val list = getRange(100,100,5)
    list.foreach(println)
  }
}

