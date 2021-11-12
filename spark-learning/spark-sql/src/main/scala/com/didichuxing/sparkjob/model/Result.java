package com.didichuxing.sparkjob.model;

import lombok.Data;

import java.util.Map;

@Data
public class Result {


    /**
     * 分析ID
     */
    private Long analyseId;

    /**
     * 字段计算值
     */
    private Map<String,String> columnStatResultMap;

    /**
     * 数据分布
     */
    private Map<String,Map<String,String>> dataDistMap;


}
