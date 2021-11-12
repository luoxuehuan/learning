package com.didichuxing.sparkjob


/**
  *
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

  */
package object main {

  val EXEC_RESULT  = "_exec_result_"
}
