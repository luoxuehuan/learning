package com.dtwave.dipper.asset.util;

import com.dtwave.dipper.asset.entity.CompareEntity;
import com.dtwave.dipper.asset.enums.ValueUnitEnum;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * 对比工具
 * 基础类型
 * <p>
 * 1.基础值
 * 2.对比值
 * 3.对比对象类型（是否是固定值 如果是固定值）
 * 4.对比对象类型
 * 5.数据增幅类型
 * 6.比较方式
 *
 * @author hulb
 * @date 2020/3/3 下午5:51
 */
public class ValueCompareUtil {

    public static void main(String[] args) {
        //设置好比较对象实体类
        CompareEntity compareEntity = new CompareEntity();

        //1 是否是固定值 固定值没有趋势类型比较
        compareEntity.setCompareObject(1);
        //1:大于2:小于3:介于4:等于5:不等于6:大于等于7:小于等于'
        compareEntity.setCompareType(2);
        //监控类型 1:绝对值2:上升3:下降',
        compareEntity.setTrendType(1);
        compareEntity.setFirstValue("52");
        compareEntity.setSecondValue("30");
        //统计周期内得到的计算值
        compareEntity.setBaseValue("51");
        //规则对比对象得到的固定值或者计算得到的对比值
        compareEntity.setCompareValue("101");

        Boolean result = cal(compareEntity);
        System.out.println(result);

        //进行比较计算
        compute(compareEntity);
    }

    public static Boolean cal(CompareEntity rule) {
        switch (rule.getCompareObject()) {
            // 固定值
            case 1:
                // 前一天
            case 2:
                // 上一个工作日
            case 3:
                // 上周同期
            case 4:
                // 最近七日平均
                // 最近30天平均
            case 5:
            case 6:
                return valueTableCheckCompareObject(rule);
            default:
                return false;
        }
    }

    /**
     * 真正的计算方法， 结果为通过或未通过
     *
     * @param compareEntity
     * @return
     */
    private static boolean compute(CompareEntity compareEntity) {
        return false;
    }


    /**
     * 表监控比较
     *
     * @param rule
     * @return
     */
    public static Boolean valueTableCheckCompareObject(final CompareEntity rule) {
        Long valuePercent;
        if (rule.getCompareObject() == 1) {
            valuePercent = Long.valueOf(rule.getBaseValue());
        } else {
            valuePercent = theDividePercent(rule.getBaseValue(), rule.getCompareValue());
        }

        if (rule == null || valuePercent == null) {
            //log.info("待处理的参数为空 value{}", valuePercent);
            return false;
        }

        Integer trendType = rule.getTrendType();
        Long value;
        switch (trendType) {
            // 绝对值
            case 1:
                value = Math.abs(valuePercent);
                //log.info("告警规则趋势绝对值,规则Id:{}",rule.getId());
                break;
            // 上升
            case 2:
                value = valuePercent;
                //log.info("告警规则趋势上升,规则Id:{}",rule.getId());
                break;
            // 下降
            case 3:
                if (valuePercent < 0) {
                    value = Math.abs(valuePercent);
                } else {
                    value = -valuePercent;
                }
                //log.info("告警规则趋势下降,规则Id:{}",rule.getId());
                break;
            default:
                return false;
        }

        String firstValue = rule.getFirstValue();
        Long floor = NumberUtils.toLong(firstValue, 0L);

        String secondValue = rule.getSecondValue();
        Long max = NumberUtils.toLong(secondValue, Long.MAX_VALUE - 1);

        return getCompare(rule, value, floor, max);
    }


    public static Boolean compareWithOneValue(final CompareEntity rule) {
        Long valuePercent = theDividePercent(rule.getBaseValue(), rule.getCompareValue());


        if (rule == null || valuePercent == null) {
            //log.info("待处理的参数为空 value{}", valuePercent);
            return false;
        }

        Integer trendType = rule.getTrendType();
        Long value;
        switch (trendType) {
            // 绝对值
            case 1:
                value = Math.abs(valuePercent);
                //log.info("告警规则趋势绝对值,规则Id:{}",rule.getId());
                break;
            // 上升
            case 2:
                value = valuePercent;
                //log.info("告警规则趋势上升,规则Id:{}",rule.getId());
                break;
            // 下降
            case 3:
                if (valuePercent < 0) {
                    value = Math.abs(valuePercent);
                } else {
                    value = -valuePercent;
                }
                //log.info("告警规则趋势下降,规则Id:{}",rule.getId());
                break;
            default:
                return false;
        }

        String firstValue = rule.getFirstValue();
        Long floor = NumberUtils.toLong(firstValue, 0L);

        String secondValue = rule.getSecondValue();
        Long max = NumberUtils.toLong(secondValue, Long.MAX_VALUE - 1);

        return getCompare(rule, value, floor, max);
    }


    /**
     * 字段
     * Long值比较器，内部根据趋势 类型和判断逻辑比较是否符合，不符合的返回false
     * <p>
     * 仅对 compareType处理 对比方式 1:大于2:小于3:介于4:等于5:不等于
     *
     * @param rule  规则，用于获取待处理的数据信息
     * @param value 基准值 可以是存储量 也可以是整数化之后的分数
     * @return
     */
    public static Boolean valueCheckField(final CompareEntity rule, final Long value) {
        if (rule == null || value == null) {
            //log.info("待处理的参数为空 value{}", value);
            return false;
        }

        // 页面%符号 需要计算 比如 前一天使用日期最早的总量
        String firstValue = rule.getFirstValue();
        Long floor = NumberUtils.toLong(firstValue, 0L);

        String secondValue = rule.getSecondValue();
        Long max = NumberUtils.toLong(secondValue, Long.MAX_VALUE - 1);

        return getCompare(rule, value, floor, max);
    }

    /**
     * 表固定值比较
     *
     * @param rule
     * @param valueStorage
     * @return
     */
    public static Boolean CheckTableFixedValue(final CompareEntity rule, final Long valueStorage) {
        if (rule == null || valueStorage == null) {
            // log.info("待处理的参数为空 value{}", valueStorage);
            return false;
        }
        String firstValue = rule.getFirstValue();
        Long floor = NumberUtils.toLong(firstValue, 0L);
        //log.info("floor 规则ID:{},floor{}", rule.getId(), floor);

        String secondValue = rule.getSecondValue();
        Long max = NumberUtils.toLong(secondValue, Long.MAX_VALUE - 1);
        // log.info("max 规则ID:{},max{}", rule.getId(), max);

        Integer valueUnit = rule.getValueUnit();
        if (null == valueUnit) {
            //log.info("固定值比较时单位为null, ruleId{}", rule.getId());
            return false;
        }
        Long value;
        if (!ValueUnitEnum.STRIP.getType().equals(valueUnit)) {
            // 固定值没有 但需要转换单位 条/KB/MB/GB/TB
            BigDecimal bigDecimal = UnitConvertor.toMonitorUnitStorage(valueStorage,
                    Objects.requireNonNull(ValueUnitEnum.getNameByType(valueUnit)));
            value = bigDecimal.longValue();
        } else {
            value = valueStorage;
        }
        //log.info("value 规则ID:{},value{}", rule.getId(), value);

        return getCompare(rule, value, floor, max);
    }

    /**
     * 比较大于小于等于
     *
     * @param rule
     * @param value
     * @param floor
     * @param max
     * @return
     */
    public static Boolean getCompare(CompareEntity rule, Long value, Long floor, Long max) {
        Integer compareType = rule.getCompareType();
        if (null == compareType) {
            ///log.warn("待处理的参数比较类型为空 ruleId{}", rule.getId());
            return false;
        }
        switch (compareType) {
            //大于
            case 1:
                return value > floor;
            //小于
            case 2:
                return value < floor;
            //介于
            case 3:
                return value > floor && value < max;
            //等于
            case 4:
                return value.equals(floor);
            //不等于
            case 5:
                return !value.equals(floor);
            //大于等于
            case 6:
                return value >= floor;
            //小于等于
            case 7:
                return value <= floor;
            default:
                return false;
        }
    }


    /**
     * 做除法计算 增长百分比
     *
     * @param numerator
     * @param denominator
     * @return
     */
    public static Long theDividePercent(String numerator, String denominator) {
        if (numerator == null || denominator == null) {
            //log.info("日志对应的两天表历史记录未能获取,表ID:{}", t.getTableId());
            return -1L;
        }
        Double numeratorValue = Double.valueOf(numerator);
        BigDecimal b1 = BigDecimal.valueOf(numeratorValue);
        BigDecimal b2 = null;
        if (denominator.equals(0L)) {
            if (numeratorValue > 0) {
                return 100L;
            }
//            }else {
//                BigDecimal bigDecimal = new BigDecimal(numerator);
//                int i = bigDecimal.abs().intValue();
//                return i > defaultValue;
//            }
        } else {
            Double denominatorValue = Double.valueOf(numerator);
            b2 = BigDecimal.valueOf(denominatorValue);
        }

        BigDecimal decimalResult = b1.subtract(b2).divide(b2, 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
        Long doubleValue = decimalResult.longValue();
        // 页面%符号 需要计算 比如 前一天使用日期最早的总量
        long dayNewSize = b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).longValue();
        // return checkValueCheckCompareObject( t, dayNewSize);
        return doubleValue;
    }


}
