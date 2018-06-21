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
import java.util
import java.util.UUID

import com.alibaba.fastjson.JSON

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.Trigger
import org.apache.spark.sql.types.{DataTypes, StructField, StructType}
import scala.collection.mutable


object ContinuousStructuredKafkaWordCount {
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

    val getValueByFieldFromJsonString: ((String, String) => String) =
      (body: String, field: String) => {
        try {
          val jsonObject = JSON.parseObject(body)
          jsonObject.getString(field)
        } catch {
          case _: Exception => ""
        }
      }

    val timeSt: ((String, String) => Long) =
      (body: String, field: String) => {
        try {
          //val jsonObject = JSON.parseObject(body)
          System.currentTimeMillis()
          //          jsonObject.getString(field)
        } catch {
          case _: Exception => 1L
        }
      }

    spark.sqlContext.udf.register("getValueByFieldFromJsonString", getValueByFieldFromJsonString)
    //spark.sqlContext.udf.register("timeSt", timeSt)
    // val input1 = lines.flatMap(_.split(",")).groupBy("value").count()

    //    val input1 = input.toDF("body")
    //      .selectExpr("getValueByFieldFromJsonString(body,'proName')")
    //
    //
    //      println(input.schema.fields)

    //    val transform = input.map(row => {
    //      row.get(0)
    //    })
    import spark.implicits._

    val schema = StructType(mutable.Seq(
      StructField("_id", DataTypes.StringType),
      StructField("orderId", DataTypes.StringType),
      StructField("proName", DataTypes.StringType),
      StructField("amount", DataTypes.StringType),
      StructField("orderTime", DataTypes.StringType)
    ))

    val lines = spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "mq250:9092,mq221:9092,mq164:9092")
      .option("subscribe", "orders")
      .option("groupId", "groupID0234")
      .load()
      .selectExpr("CAST(offset AS STRING)", "CAST(value AS STRING)")// 没有转换则是字节数组
      .toDF("offset", "value")
      .selectExpr("getValueByFieldFromJsonString(value,'proName')",
        "getValueByFieldFromJsonString(value,'_id')")
      .toDF("value", "id")
    // .withWatermark("timestamp", "10 seconds")

    //spark.sql("set spark.sql.crossJoin.enabled=true")
    //val staticTable = spark.sql("select * from source")
    //staticTable.show()
    //val out = lines.join(staticTable, lines("id") === staticTable("source_key"), "inner")
    val output = lines.selectExpr("value").writeStream
      .format("kafka")
      .outputMode("append")
      .option("kafka.bootstrap.servers", "mq250:9092,mq221:9092,mq164:9092")
      .option("topic", "result")
      .option("checkpointLocation", checkpointLocation)

      .trigger(Trigger.Continuous("1 second")) // only change in query
      //.trigger( Trigger.ProcessingTime("5 seconds")) // only change in query
      .start()

    output.awaitTermination()
  }

}

// scalastyle:on println

/**
  *
  *


{"_id":9974,"orderId":9974,"proName":"prt_upsert_37_______9974","amount":1,"orderTime":1529406542754}
{"_id":9975,"orderId":9975,"proName":"prt_upsert_37_______9975","amount":1,"orderTime":1529406542754}
{"_id":9976,"orderId":9976,"proName":"prt_upsert_37_______9976","amount":1,"orderTime":1529406542755}
{"_id":9977,"orderId":9977,"proName":"prt_upsert_37_______9977","amount":1,"orderTime":1529406542755}
{"_id":9978,"orderId":9978,"proName":"prt_upsert_37_______9978","amount":1,"orderTime":1529406542755}
{"_id":9979,"orderId":9979,"proName":"prt_upsert_37_______9979","amount":1,"orderTime":1529406542755}
{"_id":9980,"orderId":9980,"proName":"prt_upsert_37_______9980","amount":1,"orderTime":1529406542758}
{"_id":9981,"orderId":9981,"proName":"prt_upsert_37_______9981","amount":1,"orderTime":1529406542760}
{"_id":9982,"orderId":9982,"proName":"prt_upsert_37_______9982","amount":1,"orderTime":1529406542761}
{"_id":9983,"orderId":9983,"proName":"prt_upsert_37_______9983","amount":1,"orderTime":1529406542761}
{"_id":9984,"orderId":9984,"proName":"prt_upsert_37_______9984","amount":1,"orderTime":1529406542761}
{"_id":9985,"orderId":9985,"proName":"prt_upsert_37_______9985","amount":1,"orderTime":1529406542761}
{"_id":9986,"orderId":9986,"proName":"prt_upsert_37_______9986","amount":1,"orderTime":1529406542761}
{"_id":9987,"orderId":9987,"proName":"prt_upsert_37_______9987","amount":1,"orderTime":1529406542761}
{"_id":9988,"orderId":9988,"proName":"prt_upsert_37_______9988","amount":1,"orderTime":1529406542761}
{"_id":9989,"orderId":9989,"proName":"prt_upsert_37_______9989","amount":1,"orderTime":1529406542762}
{"_id":9990,"orderId":9990,"proName":"prt_upsert_37_______9990","amount":1,"orderTime":1529406542762}
{"_id":9991,"orderId":9991,"proName":"prt_upsert_37_______9991","amount":1,"orderTime":1529406542762}
{"_id":9992,"orderId":9992,"proName":"prt_upsert_37_______9992","amount":1,"orderTime":1529406542762}
{"_id":9993,"orderId":9993,"proName":"prt_upsert_37_______9993","amount":1,"orderTime":1529406542762}
{"_id":9994,"orderId":9994,"proName":"prt_upsert_37_______9994","amount":1,"orderTime":1529406542762}
{"_id":9995,"orderId":9995,"proName":"prt_upsert_37_______9995","amount":1,"orderTime":1529406542762}


{"proName":"2","_id":"1"}
{"proName":"4","_id":"1"}
{"proName":"5","_id":"1"}
{"proName":"7","_id":"1"}
{"proName":"8","_id":"1"}
{"proName":"9","_id":"1"}
{"proName":"0","_id":"1"}

bin/kafka-console-producer.sh --broker-list localhost:9092 --topic orders


bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic orders
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic result


  kafka-console-consumer.sh --bootstrap-server mq250:9092,mq221:9092,mq164:9092  --topic result

  */

