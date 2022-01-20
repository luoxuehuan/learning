package com.hulb

import org.apache.hudi.QuickstartUtils._

import scala.collection.JavaConversions._
import org.apache.spark.sql.SaveMode._
import org.apache.hudi.DataSourceReadOptions._
import org.apache.hudi.DataSourceWriteOptions._
import org.apache.hudi.config.HoodieWriteConfig._
import org.apache.iceberg.Schema
import org.apache.iceberg.types.Types
import org.apache.iceberg.catalog.TableIdentifier
import org.apache.iceberg.spark.SparkSchemaUtil
import org.apache.spark.sql.SparkSession


/**
  * Created with IntelliJ IDEA.
  *
  * @author：luoxuehuan
  * @date： 2021/12/28 7:30 下午
  * @description：
  * @modifiedBy：
  * @version: 1.0
  */
object IcebergSparkTest {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession
      .builder()
      .master("local")
      .enableHiveSupport()
      .getOrCreate()

    import org.apache.iceberg.catalog.TableIdentifier
    val name = TableIdentifier.of("default", "person")


    val schema = new Schema(
      Types.NestedField.required(1, "id", Types.IntegerType.get()),
      Types.NestedField.required(2, "name", Types.StringType.get()),
      Types.NestedField.required(3, "age", Types.IntegerType.get())
    );

    import org.apache.iceberg.PartitionSpec;
    val spec = PartitionSpec.unpartitioned

    import org.apache.iceberg.hive.HiveCatalog;

    val catalog = new HiveCatalog(spark.sparkContext.hadoopConfiguration);


    val table = catalog.createTable(name, schema, spec);

    //val data = Seq((1, "a"), (2, "b"), (3, "c")).toDF("id", "data")
    val data = spark.sql("select 1,'ab',10").toDF("id","name","age");
    val schema2 = SparkSchemaUtil.convert(data.schema)

   // data.write.


    spark.sql("select * from default.person").show(10)

  }




}
