package com.dtwave.dipper.asset.entity;

import lombok.Data;

/**
 * 每个规则的规则计算结果
 * @author hulb
 * @date 2020/3/3 上午10:14
 */
@Data
public class RuleResult {

    /**
     * 是否阻塞
     */
    private boolean block;
    /**
     * 是否需要告警
     */
    private boolean alert;
    /**
     * 该规则计算初始值
     */
    private String ruleResultValue;

    /**
     * 该规则计算与比较值对比值
     */
    private String ruleCompareValue;

    /**
     * 该规则运行开始时间
     */
    private String ruleComputeStartTime;
    /**
     * 该规则运行结束时间
     */
    private String ruleComputeEndTime;
}
