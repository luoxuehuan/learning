package com.dtwave.dipper.asset.rule;

import com.dtwave.dipper.asset.entity.RuleEntity;
import com.dtwave.dipper.asset.entity.RuleResult;
import com.dtwave.dipper.asset.util.HiveMetaUtils;
import com.dtwave.dipper.asset.util.MathUtils;
import com.dtwave.dipper.asset.util.TableTimeUtil;
import org.apache.hadoop.hive.metastore.api.Table;
import org.apache.spark.sql.SparkSession;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hulb
 * @date 2020/3/2 上午11:09
 */
public class TableRuleJava {


    public static RuleResult compute(SparkSession spark, RuleEntity ruleEntity) {

        //ruleEntity.

        return new RuleResult();
    }


    public void test(String str1){

    }

    public void test(String str1,String str2){

    }

    public static void main(String[] args) throws Exception{
        SparkSession spark = SparkSession
                .builder()
                .appName("MegrezApp")
                .enableHiveSupport()
                .master("local[2]")
                .getOrCreate();
        spark.sparkContext().setLogLevel("error");


        // spark.sql("select * from quality_one").show(10)
        //根据时间字段计算最近N天的记录条数
        Integer N = 7;

        List<Long> valueList = new ArrayList<>();
        String dbName = "default";
        //String tableName = "quality_one";
        String tableName = "quality_all";
        String fullTableName = dbName+"."+tableName;
        long[] longList = new long[N];
        //val valueList:List[Int] = new util.ArrayList[Int]()

        HiveMetaUtils hiveMetaUtils = new HiveMetaUtils("thrift://localhost:9083");


        /**
        for (int x = 1; x < N; x++) {
            //计算新增记录条数的
            String result = TableTimeUtil.timeRangePreviousDays("YYYY-MM-dd HH:mm:ss", "create_time", x);
            System.out.println(result);
            Long size = spark.sql("select * from "+fullTableName+" where " + result).count();
            System.out.println("size" + size);
            longList[x]=size;
        }
*/

        // 由表名和数据库名称获取table对象(能获取列、表信息)
        Table table = hiveMetaUtils.getTable(dbName, tableName);
        //判断是否是分区表
        long totalSize = 0L;
        if(hiveMetaUtils.judgePartitionTable(table)){
            totalSize = hiveMetaUtils.computePartitionTableSize(hiveMetaUtils.hiveMetaStoreClient,dbName,tableName, TableTimeUtil.getTimeStampBefore(N),TableTimeUtil.getTimeStampBefore(-10));
        }else{
            totalSize = hiveMetaUtils.computeNonPartitionTableSize(table);
        }
        System.out.println(tableName+ "存储量为："+totalSize);
        //计算存储量的

        //计算方差
        Double fc = MathUtils.variance(longList);
        //计算最大值
        MathUtils.getMaxValue(longList);
        //计算最小值
        MathUtils.getMinValue(longList);
        //计算平均值
        MathUtils.getAvgValue(longList);
        //计算总和
        MathUtils.getSumValue(longList);

        System.out.println("方差是："+fc);
        spark.stop();

    }


}
