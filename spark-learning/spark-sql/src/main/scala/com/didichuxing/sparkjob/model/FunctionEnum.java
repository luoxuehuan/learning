package com.didichuxing.sparkjob.model;

import org.apache.spark.sql.sources.In;

import java.util.ArrayList;
import java.util.List;

/**
 * _____基础统计
 * 记录数
 * 空值数
 * 空值率
 * 最小值
 * 最大值
 * _____高级统计
 * 唯一值
 * 平均值
 * 标准差
 * 非零最小值
 * 非零最大值
 * 数据分布
 * 直方图
 * 枚举值
 */
public enum FunctionEnum {

    COUNT(101, "记录数", "count","1",1,"count(columnName)","",1),
    NULL_COUNT(102, "记录数", "null_count","2",1,"count(columnName)","columnName is null",1),
    NULL_COUNT_PERCENT(103, "记录数", "null_count_percent","2",1,"count(columnName)/count(1)","columnName is null",1),
    MIN(104, "最小值", "min","1",1,"min(columnName)","",1),
    MAX(105, "最小值", "max","1",1,"max(columnName)","",1),


    ONLY(201, "最小值", "only","2",2,"count(distinct columnName)","",1),
    AVG(202, "最小值", "avg","2",2,"avg(columnName)","",1),
    VAR_POP(203, "最小值", "avg","2",2,"var_pop(columnName)","",1),
    NOT_NULL_MIN(204, "最小值", "not_null_min","2",2,"min(columnName)","columnName is not null",1),
    NOT_NULL_MAX(205, "最小值", "not_null_max","2",2,"max(columnName)","columnName is not null ",1),


    ;




    /**
     * 函数名称
     */
    private  Integer functionId;

    /**
     * 函数名称
     */
    private  String functionDesc;



    private  String functionName;

    /**
     * 函数类型
     */
    private  Integer functionLevel;

    /**
     * 函数级别
     */
    private  Integer type;

    /**
     * 是否orc
     */
    private  Integer orcStat;

    /**
     * 函数模板
     */
    private String functionTemplate;



    private String functionWhere;

    FunctionEnum(Integer functionId, String functionDesc, String functionName, String type, Integer functionLevel, String functionTemplate, String functionWhere,Integer orcStat) {
        this.functionDesc = functionDesc;
        this.functionId = functionId;
        this.functionDesc = functionName;
        this.functionName = type;
        this.functionLevel = functionLevel;
        this.functionTemplate = functionTemplate;
        this.orcStat = orcStat;
        this.functionWhere = functionWhere;
    }

    public Integer getFunctionId() {
        return functionId;
    }

    public String getFunctionName() {
        return functionDesc;
    }

    public String getFunctionTemplate() {
        return functionTemplate;
    }

    public String getFunctionWhere() {
        return functionWhere;
    }

    public Integer getFunctionLevel() {
        return functionLevel;
    }

    public static String getName(long tagId) {
        for (FunctionEnum tag : FunctionEnum.values()) {
            if (tag.getFunctionId() == tagId) {
                return tag.getFunctionName();
            }
        }
        return "";
    }

    /**
     * 根据级别获取函数列表
     * @param functionId
     * @return
     */
    public static FunctionEnum getFunctionListByFunctionId(Integer functionId) {
        for (FunctionEnum functionEnum : FunctionEnum.values()) {
            if (functionEnum.getFunctionId().equals(functionId) ) {
                return functionEnum;
            }
        }
        return null;
    }



    /**
     * 根据级别获取函数列表
     * @param functionLevel
     * @return
     */
    public static List<Integer> getFunctionListByLevel(Integer functionLevel) {
        List<Integer> functionIdList = new ArrayList<>();
        for (FunctionEnum functionEnum : FunctionEnum.values()) {
            if (functionEnum.getFunctionLevel().equals(functionLevel) ) {
                functionIdList.add(functionEnum.getFunctionId());
            }
        }
        return functionIdList;
    }

    public Integer getType() {
        return type;
    }
}
