package com.dtwave.dipper.asset.entity;

import lombok.Data;

/**
 * @author hulb
 * @date 2020/3/3 上午10:36
 */
@Data
public class TableConfig {
    private String dbName;
    private String tableName;
    private String partitionField;
    private String timeField;
    private String computeField;

    /**
     * 时间字段时间格式
     *
     *
     */
    private String timeFieldTimeFormat;
    /**
     * 字段正则表达式
     */
    private String regexExpr;

    /**
     * -1表示所有
     */
    private Integer timeRange;
}
