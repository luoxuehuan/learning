package com.dtwave.dipper.asset.streaming

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// scalastyle:off println
import java.util.UUID

import com.alibaba.fastjson.JSON
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.Trigger


/**
  * 需求
  *
  * 显示高息资产余额当前值，较月初，较年初变化值。
  *
  * 5个动态表
  * rsdata.S00_LNFM02
  * rsdata.s12_limit_info
  * rsdata.S12_BUSINESS_INFO
  * rsdata.s12_business_duebill
  * rsdata.s12_ind_info
  *
  * 3个静态表（每个有更新一次.需要重新读取）
  *
  * RDS.S12_SYSTEM_PARAMETER
  * RDS.T03_CRDT_CARD_ACCT_INFO
  * RDS.E06_GXZC_LM_LY_DATA
  *
  *
  * 计算日期、计算时间、当前实时余额、
  * 【较月初】实时增长金额、
  * 【较年初】实时增长金额、
  * 个贷类实时余额、
  * 信用卡实时余额、
  * 融类实时余额
  *
  *
  * 模拟5个流
  * 3个静态周期表
  *
  *
  * 【较年初】实时增长金额：分析 当前总的 sum - 年初sum
  *
  *
  * TODO 动态A join 静态B  生成临时表C 临时表C 写入到mysql中 。再用临时表C 再 join 静态表D 。生成临时表E。
  *
  */
object MutiJoinDynamicData {
  def main(args: Array[String]): Unit = {

    val checkpointLocation =
      if (args.length > 3) args(3) else "/tmp/temporary-" + UUID.randomUUID.toString

    val spark = SparkSession
      .builder
      .master("local[3]")
      .appName("ContinuousStructuredKafkaWordCount")
      .enableHiveSupport()
      .getOrCreate()
    spark.sparkContext.setLogLevel("ERROR")

    val jsonFrom: ((String, String) => String) =
      (body: String, field: String) => {
        try {
          val jsonObject = JSON.parseObject(body)
          jsonObject.getString(field)
        } catch {
          case _: Exception => ""
        }
      }
    spark.sqlContext.udf.register("jsonFrom", jsonFrom)

    /**

    {
  "your-fist-ss-job": {
    "desc": "测试",
    "strategy": "spark",
    "algorithm": [],
    "ref": [
    ],
    "compositor": [
      {
        "name": "ss.sources",
        "params": [
          {
            "format": "kafka9",
            "outputTable": "test",
            "kafka.bootstrap.servers": "127.0.0.1:9092",
            "topics": "test",
            "path": "-"
          },
          {
            "format": "com.databricks.spark.csv",
            "outputTable": "sample",
            "header": "true",
            "path": "/Users/allwefantasy/streamingpro/sample.csv"
          }
        ]
      },
      {
        "name": "ss.sql",
        "params": [
          {
            "sql": "select city as value from test left join sample on  CAST(test.value AS String) == sample.name",
            "outputTableName": "test3"
          }
        ]
      },
      {
        "name": "ss.outputs",
        "params": [
          {
            "mode": "append",
            "format": "kafka8",
            "metadata.broker.list":"127.0.0.1:9092",
            "topics":"test2",
            "inputTableName": "test3",
            "checkpoint":"/tmp/ss-kafka/",
            "path": "/tmp/ss-kafka-data"
          }
        ]
      }
    ],
    "configParams": {
    }
  }
}





      */


    spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "mq250:9092,mq221:9092,mq164:9092")
      .option("subscribe", "orders")
      .option("groupId", "groupID0234")
      .load()
      .selectExpr("CAST(offset AS STRING)", "CAST(value AS STRING)")// 没有转换则是字节数组
      .toDF("offset", "value")
      .createOrReplaceTempView("source_orders")

      spark.sql(
        """
          |select jsonFrom(value,'proName') as orders_value,jsonFrom(value,'_id') as orders_id from source_orders
        """.stripMargin).createOrReplaceTempView("orders")


//      .selectExpr("jsonFrom(value,'proName')",
//        "jsonFrom(value,'_id')")
//      .toDF("orders_value", "orders_id").createOrReplaceTempView("orders")

    val lines2 = spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "mq250:9092,mq221:9092,mq164:9092")
      .option("subscribe", "test_click")
      .option("groupId", "group-id-2")
      .load()
      .selectExpr("CAST(offset AS STRING)", "CAST(value AS STRING)")// 没有转换则是字节数组
      .toDF("offset", "value")
      .selectExpr("jsonFrom(value,'proName')",
        "jsonFrom(value,'_id')")
      .toDF("value", "id").createOrReplaceTempView("test_click")
    // .withWatermark("timestamp", "10 seconds")

    spark.sql("set spark.sql.crossJoin.enabled=true")
    //TODO 1.如何刷新这个动态表
    val staticTable = spark.sql("select * from source")
    staticTable.createOrReplaceTempView("source")
    staticTable.show()

    //TODO 2.如何JOIN多个表 （5个动态，3个静态）

    /**
      * 这里注意join的限制。2.0 开始可以动态join 静态
      * 2.2开始可以动态join 动态。
      */
    //val out = lines.join(staticTable, lines("id") === staticTable("source_key"), "inner")


    //val out2 = spark.sql("select * from test_click join source on test_click.id = source.source_key")
    spark.sql("select * from test_click join orders on test_click.id = orders.orders_id")
      .createOrReplaceTempView("temp_join_result")


    val resultOutput = spark.sql("select * from temp_join_result join source on temp_join_result.id = source.source_key")



    //TODO 3.高效写回到mysql中。 且保证exactly-once 语义。
//    val output = out.selectExpr("value").writeStream
//      .format("kafka")
//      .outputMode("append")
//      .option("kafka.bootstrap.servers", "mq250:9092,mq221:9092,mq164:9092")
//      .option("topic", "result")
//      .option("checkpointLocation", checkpointLocation)
//
//      //.trigger(Trigger.Continuous("1 second")) // only change in query
//      .trigger( Trigger.ProcessingTime("1 seconds")) // only change in query
//      //.start()


    val output2 = resultOutput.selectExpr("concat(id, orders_id) as value").writeStream
      .format("kafka")
      .outputMode("append")
      .option("kafka.bootstrap.servers", "mq250:9092,mq221:9092,mq164:9092")
      .option("topic", "result")
      .option("checkpointLocation", checkpointLocation)

      //.trigger(Trigger.Continuous("1 second")) // only change in query
      .trigger( Trigger.ProcessingTime("1 seconds")) // only change in query
      .start()

   // output.awaitTermination()
    output2.awaitTermination()
  }

}

/**
  *
  *
  * kafka-console-consumer.sh --bootstrap-server mq250:9092,mq221:9092,mq164:9092  --topic orders
  */
// scalastyle:on println

