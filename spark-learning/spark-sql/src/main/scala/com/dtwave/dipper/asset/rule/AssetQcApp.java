package com.dtwave.dipper.asset.rule;

import com.dtwave.dipper.asset.entity.RuleEntity;
import com.dtwave.dipper.asset.entity.RuleResult;
import org.apache.spark.sql.SparkSession;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hulb
 * @date 2020/3/4 上午10:05
 */
public class AssetQcApp {

    public static void main(String[] args) throws Exception{

        SparkSession spark = SparkSession
                .builder()
                .appName("AssetQcApp")
                .enableHiveSupport()
                .master("local[2]")
                .getOrCreate();
        spark.sparkContext().setLogLevel("error");

        List<RuleEntity> ruleEntityList = new ArrayList<>();
        testAddRule(ruleEntityList);

        List<RuleResult> ruleResultList = new ArrayList<>();
        for(RuleEntity ruleEntity:ruleEntityList){
            RuleResult ruleResult;
            if(ruleEntity.getRuleType() == 1){
                ruleResult = TableRuleJava.compute(spark,ruleEntity);
            }else{
                ruleResult = FieldRuleJava.compute(spark,ruleEntity);
            }
            ruleResultList.add(ruleResult);
        }
        //写出日志结果 Json格式
        writeResultLog(ruleResultList,"path");

        //选择程序退出方式 如果有强规则且未通过，则进行阻塞操作。
        boolean block = true;

        if(block){
            //程序以错误运行的方式退出
            System.exit(1);
        }else{
            //程序正常结束
            spark.stop();
        }
    }

    /**
     * 测试使用
     * 各种情况
     * @param ruleEntityList
     */
    private static void testAddRule(List<RuleEntity> ruleEntityList) {
        RuleEntity ruleEntity = new RuleEntity();

        ruleEntityList.add(ruleEntity);

    }

    /**
     * 将程序运行结果写出到特定目录，并上传汇报到master中
     * @param ruleResultList
     * @param path
     */
    private static void writeResultLog(List<RuleResult> ruleResultList, String path) {

    }

}
