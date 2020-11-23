package com.dtwave.dipper.asset.entity;

import lombok.Data;

/**
 * @author hulb
 * @date 2020/3/3 下午6:03
 */

@Data
public class CompareEntity {

    /**
     * '监控类型 1:绝对值2:上升3:下降',
     */
    private Integer trendType;

    /**
     * 对比方式 1:大于2:小于3:介于4:等于5:不等于6:大于等于7:小于等于'
     */
    private Integer compareType;

    /**
     * 对比对象类型 1:固定值2:前一天3:上一工作日4:上周同期5:最近七日平均6:最近30天平均
     */
    private Integer compareObject;

    /**
     * 阈值下限
     */
    private String firstValue;
    /**
     * 阈值上限
     */
    private String secondValue;

    /**
     * 值的单位
     */
    private Integer valueUnit;

    /**
     * 等待计算的值 基础值
     */
    private String baseValue;

    /**
     * 等待计算的值 对比值
     */
    private String compareValue;

    /**
     * 最终计算结果
     */
    private boolean passOrNot;


}
