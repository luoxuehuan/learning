package com.dtwave.dipper.asset.sql

import org.joda.time.DateTime

/**
  * 时间格式工具
  *
  * 获取前一天
  * 获取七天内平均
  * 获取30天内平均
  *
  */
object TimeRangeUtil {

  /**
    * 根据分区字段分区时间格式,获得时间范围
    */
  def timeRangePrevious(timeFormat: String,
                        timeField: String,
                        partitionField: String): String = {
    /**
      * 例如计算前一日的分区的条数
      * 则分区表达式 - 2 获得前一天
      * 分区表达式 格式化后- 1 即今天
      */

    "1=1";
  }

  /**
    * 根据分区字段分区时间格式,获得时间范围
    */
  def timeRangePrevious7(timeFormat: String,
                        timeField: String,
                        partitionField: String): String = {
    /**
      * 例如计算前一日的分区的条数
      * 则分区表达式 - 2 获得前一天
      * 分区表达式 格式化后- 1 即今天
      *
      */

    "1=1";
  }

  /**
    * 根据分区字段分区时间格式,获得时间范围
    */
  def timeRangePrevious30(timeFormat: String,
                         timeField: String,
                         partitionField: String): String = {
    /**
      * 例如计算前一日的分区的条数
      * 则分区表达式 - 2 获得前一天
      * 分区表达式 格式化后- 1 即今天
      */
    val date = getYesterDay
    val start = date.minusDays(30).toString(timeFormat)
    val end = date.minusDays(1).toString(timeFormat)
    val result = s"$timeField >= $start and $timeField <= $end"
    result;
  }


  /**
    * TODO 优化成一天的起始时间到一天的结束时间
    * 根据分区字段分区时间格式,获得时间范围
    */
  def timeRangePreviousDays(timeFormat: String,
                          timeField: String,
                          daysBefore:Int): String = {
    /**
      * 例如计算前一日的分区的条数
      * 则分区表达式 - 2 获得前一天
      * 分区表达式 格式化后- 1 即今天
      */
    val date = getYesterDay
    val start = date.minusDays(daysBefore).toString(timeFormat)
    val end = date.minusDays(daysBefore-1).toString(timeFormat)
    val result = s"$timeField >= '$start' and $timeField < '$end'"
    result;
  }


  /**
    * 获得昨日
    * @return
    */
  def getYesterDay():DateTime={
    DateTime.parse(DateTime.now.toString("YYYY-MM-dd")).minusDays(1)
  }

  /**
    * 对比对象的时间数据
    */





  /**
    * 统计周期的时间数据
    */


}
