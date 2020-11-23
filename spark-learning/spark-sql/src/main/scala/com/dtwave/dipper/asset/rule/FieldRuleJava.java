package com.dtwave.dipper.asset.rule;

import com.dtwave.dipper.asset.entity.RuleEntity;
import com.dtwave.dipper.asset.entity.RuleResult;
import com.dtwave.dipper.asset.entity.TableConfig;
import com.dtwave.dipper.asset.function.RegexFunction;
import com.dtwave.dipper.asset.sql.SparkSqlExecutor;
import com.dtwave.dipper.asset.sql.SqlGenerator;
import com.dtwave.dipper.asset.util.TableTimeUtil;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;


/**
 *
 * 字段规范性：
 *
 *
 *
 * @author hulb
 * @date 2020/3/3 上午9:30
 */
public class FieldRuleJava {


    /**
     * 计算空值率
     */
    public static void computeNullPercent(SparkSession spark,TableConfig tableConfig){

        //计算空值个数
        long nullValue = computeNullCount(spark,tableConfig);
        //计算总个数
        long allValue = computeAllCount(spark,tableConfig);

        //除法工具 计算百分率
       // Double percent =nullValue / allValue;

    }

    /**
     * 计算空值个数
     */

    public static long computeNullCount(SparkSession spark,TableConfig tableConfig){
        //spark.sql("select count(1) from quality_one where age is null");
        long nullCount = spark.sql("select * from quality_one where age is null and "
                + TableTimeUtil.timeRangePreviousDays(tableConfig)).count();
        return nullCount;
    }

    /**
     * 计算总个数
     */
    public static long computeAllCount(SparkSession spark,TableConfig tableConfig){
        long allCount = spark.sql("select * from quality_one where "
                + TableTimeUtil.timeRangePreviousDays(tableConfig)).count();
        return allCount;
    }

    /**
     * 计算重复值个数
     *
     * 有几个值重复 2
     * 重复了几个值 3+4
     * 重复值个数 7-2 = 5
     * spark.sql("select sum(count)-count(1) as distinct_count from (select age,count(age) as count from quality_one where group by age) where count>1").show();
     */
    public static Long computeDistinctCount(SparkSession spark,TableConfig tableConfig) throws Exception{
        String sql = SqlGenerator.generatorDistinctCount(tableConfig.getTableName(),tableConfig.getComputeField(),"");
        String value = SparkSqlExecutor.executor(spark,sql);
        Long allCount = Long.valueOf(value);
        return allCount;
    }

    /**
     * 计算重复值率
     */
    public static Double computeDistinctPercent(SparkSession spark,TableConfig tableConfig) throws Exception {
        Long all = computeAllCount(spark,tableConfig);
        Long distinctCount = computeDistinctCount(spark,tableConfig);
        return chufa(distinctCount,all);
    }

    public static Double chufa(Long a ,Long b){
        Double d = 0.22;
        return d;
    }

    /**
     * 计算唯一值率
     */
    public static Double computeOnlyPercent(SparkSession spark,TableConfig tableConfig) throws Exception {
        Long all = computeAllCount(spark,tableConfig);
        Long count = computeOnlyCount(spark,tableConfig);
        return chufa(count,all);
    }

    /**
     * 计算唯一值个数
     */
    public static Long computeOnlyCount(SparkSession spark,TableConfig tableConfig) throws Exception{
        String sql = SqlGenerator.generatorOnly(tableConfig.getTableName(),tableConfig.getComputeField(),"");
        String value = SparkSqlExecutor.executor(spark,sql);
        Long onlyCount = Long.valueOf(value);
        return onlyCount;
    }


    /**
     * 计算不符合正则表达式率
     */
    /**
     * 计算唯一值率
     */
    public static Double computeNonRegexPercent(SparkSession spark,TableConfig tableConfig) throws Exception {
        Long all = computeAllCount(spark,tableConfig);
        Long count = computeNonRegexCount(spark,tableConfig);
        return chufa(count,all);
    }


    /**
     * 计算不符合正则表达式个数
     */
    public static Long computeNonRegexCount(SparkSession spark,TableConfig tableConfig) throws Exception{
        String sql = SqlGenerator.generatorRegex(tableConfig.getTableName(),tableConfig.getComputeField(),tableConfig.getRegexExpr(),"");
        String value = SparkSqlExecutor.executor(spark,sql);
        Long onlyCount = Long.valueOf(value);
        return onlyCount;
    }



    /**
     * 计算方差波动
     */
    public static String computeVariance(SparkSession spark,TableConfig tableConfig) throws Exception{
        String sql = SqlGenerator.generatorNormalMathCompute(tableConfig.getTableName(),
                tableConfig.getComputeField(),
                TableTimeUtil.timeRangePreviousDays(tableConfig),"var_pop");
        String value = SparkSqlExecutor.executor(spark,sql);
        System.out.println("计算方差波动"+value);
        return value;
    }


    /**
     * 计算平均值
     */
    public static String computeAvg(SparkSession spark,TableConfig tableConfig) throws Exception{
        String sql = SqlGenerator.generatorNormalMathCompute(tableConfig.getTableName(),
                tableConfig.getComputeField(),TableTimeUtil.timeRangePreviousDays(tableConfig),"avg");
        String value = SparkSqlExecutor.executor(spark,sql);
        System.out.println("平均值为"+value);
        return value;
    }

    /**
     * 计算最小值
     */
    public static String computeMin(SparkSession spark,TableConfig tableConfig) throws Exception{
        String sql = SqlGenerator.generatorNormalMathCompute(tableConfig.getTableName(),
                tableConfig.getComputeField(),TableTimeUtil.timeRangePreviousDays(tableConfig),"min");
        String value = SparkSqlExecutor.executor(spark,sql);
        System.out.println("计算最小值"+value);
        return value;
    }

    /**
     * 计算最大值
     */
    public static String computeMax(SparkSession spark,TableConfig tableConfig) throws Exception{
        String sql = SqlGenerator.generatorNormalMathCompute(tableConfig.getTableName(),
                tableConfig.getComputeField(),TableTimeUtil.timeRangePreviousDays(tableConfig),"max");
        String value = SparkSqlExecutor.executor(spark,sql);
        System.out.println("计算最大值"+value);
        return value;
    }

    /**
     * 计算总和
     */
    public static String computeSum(SparkSession spark,TableConfig tableConfig) throws Exception{
        String sql = SqlGenerator.generatorNormalMathCompute(tableConfig.getTableName(),
                tableConfig.getComputeField(),TableTimeUtil.timeRangePreviousDays(tableConfig),"sum");
        String value = SparkSqlExecutor.executor(spark,sql);
        System.out.println("计算总和"+value);
        return value;
    }

    public static RuleResult compute(SparkSession spark, RuleEntity ruleEntity) {
        return new RuleResult();
    }

    public static void main(String[] args) throws Exception {
        SparkSession spark = SparkSession
                .builder()
                .appName("MegrezApp")
                .enableHiveSupport()
                .master("local[2]")
                .getOrCreate();
        spark.sparkContext().setLogLevel("error");

        spark.sqlContext().udf().register("no_match_count",new RegexFunction(), DataTypes.IntegerType);

        TableConfig tableConfig = new TableConfig();
        tableConfig.setDbName("default");
        tableConfig.setTableName("quality_one");
        tableConfig.setComputeField("age");
        tableConfig.setTimeField("create_time");
        tableConfig.setTimeRange(-1);
        tableConfig.setTimeFieldTimeFormat("YYYY-MM-dd HH:mm:ss");
        tableConfig.setRegexExpr("/(^-?[0-9][0-9]*(.[0-9]+)?)$/");
        tableConfig.setRegexExpr("[a-zA-Z]+");

        //computeDistinctCount(spark,tableConfig);
        //computeOnlyCount(spark,tableConfig);
       // computeNonRegexCount(spark,tableConfig);
//        computeVariance(spark,tableConfig);
//        computeAvg(spark,tableConfig);
//        computeMin(spark,tableConfig);
//        computeMax(spark,tableConfig);
//        computeSum(spark,tableConfig);


        computeAvgNDays(spark,tableConfig,7);

        /**
         * 如果要计算最近N天平均
         */
    }

    /**
     * 计算最小值
     */
    public static String computeAvgNDays(SparkSession spark,TableConfig tableConfig,Integer days) throws Exception{
        //计算7天平均的最大值
        for(int i=0;i<days;i++){
            tableConfig.setTimeRange(i);
            String value = computeAvg(spark,tableConfig);
            System.out.println(days + "  value :"+value);
        }
        return "value";
    }

}
