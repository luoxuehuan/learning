package com.dtwave.dipper.asset.entity;

import lombok.Data;

/**
 * @author hulb
 * @date 2020/3/3 上午9:12
 */
@Data
public class RuleEntity {

    private String ruleName;
    private Integer ruleType;
    private Integer tableId;
    private String tableName;
    private Integer fieldMonitorType;
    private Integer trendType;




}
