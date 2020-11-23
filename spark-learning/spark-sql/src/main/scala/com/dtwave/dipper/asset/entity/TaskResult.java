package com.dtwave.dipper.asset.entity;

import lombok.Data;

import java.util.List;

/**
 * 整个质量任务task的计算结果
 * @author hulb
 * @date 2020/3/3 上午10:15
 */
@Data
public class TaskResult {

    private boolean block;
    private boolean alert;
    private List<RuleResult> ruleResultList;
    private String startTime;
    private String endTime;

}
