package com.dtwave.dipper.asset.util;


import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.time.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * create at  2018/10/24 9:42
 * 单位转换工具 用于单位的转换和处理，一般不涉及数据值的调整
 * 1. 转换存储单位 这里GB实际上就是GiB（radix=1024） 统一使用二进制转换方式
 * 2. 转换日期单位
 *
 * @author : cheng.lc
 */
public class UnitConvertor {
    private static Integer radix = 1024;
    private static final ZoneId zoneId = ZoneId.systemDefault();
    private static final ZoneOffset offset = OffsetDateTime.now().getOffset();

    public static class StorUnit {
        public static String B = "";
        public static String KB = "K";
        public static String MB = "M";
        public static String GB = "G";
        public static String TB = "T";
        public static String PB = "P";
        public static String EB = "E";

        static Map<Integer, String> dict = new HashMap<>();

        static {
            dict.put(0, B);
            dict.put(1, KB);
            dict.put(2, MB);
            dict.put(3, GB);
            dict.put(4, TB);
            dict.put(5, PB);
            dict.put(6, EB);
        }
    }

    /**
     * 处理成MB数值
     *
     * @param val
     * @return
     */
    public static Long toRawVal(Long val) {
        if (val == null) {
            return 0L;
        }
        return val ;
    }

    /**
     * 转成GB数据 原始为Byte情况
     * @param val
     * @return
     */
    public static Long toGBVal(Long val) {
        if (val == null) {
            return 0L;
        }
        return val /(1024*1024*1024);
    }

    /**
     * 截短存储体积大小的工具方法 不能自定义目标单位
     * 先统一规范到最小单位，然后再进行处理
     *
     * @param val 待处理数值
     * @return 处理后的数值
     */
    public static String storageUnitShorter(Long val) {
        if (val == null || val < 0) {
            return null;
        }
        long value = val;
        int cycle = 0;
        //最高保留5位
        while (value > radix) {
            value = value / radix;
            cycle++;
        }
        return value + StorUnit.dict.get(cycle) + "B";
    }

    /**
     * 获取存储量
     *
     * @param val
     * @return
     */
    public static Long storageVal(Long val) {
        if (val == null || val < 0) {
            return null;
        }
        long value = val;
        //最高保留5位
        while (value > radix) {
            value = value / radix;
        }
        return value;
    }

    /**
     * 获取存储量单位
     *
     * @param val
     * @return
     */
    public static String storageUnit(Long val) {
        if (val == null || val < 0) {
            return null;
        }
        long value = val;
        int cycle = 0;
        //最高保留5位
        while (value > radix) {
            value = value / radix;
            cycle++;
        }
        return StorUnit.dict.get(cycle) + "B";
    }

    /**
     * 记录条数转换成带单位的
     * @param val
     * @return
     */
    public static UnitValue numUnitShorter2(Double val) {
        UnitValue unitValue = new UnitValue();
        if (val < 1000) {
            unitValue.setValue(BigDecimal.valueOf(val));
            unitValue.setUnit("条");
            return unitValue;
        } else {
            unitValue.setValue(BigDecimal.valueOf(val / 10000).setScale(2, BigDecimal.ROUND_HALF_UP));
            unitValue.setUnit("万条");
            return unitValue;
        }
    }

    public static UnitValue toCustomUnitShorter2(Double val, String type) {
        UnitValue unitValue = new UnitValue();

        if ("条".equals(type)) {
            unitValue.setValue(BigDecimal.valueOf(val));
            unitValue.setUnit("条");
            return unitValue;
        } else {
            unitValue.setValue(BigDecimal.valueOf(val / 10000).setScale(2, BigDecimal.ROUND_HALF_UP));
            unitValue.setUnit("万条");
            return unitValue;
        }
    }

    /**
     * 截短存储体积大小的工具方法 不能自定义目标单位
     * 先统一规范到最小单位，然后再进行处理
     *
     * @param input 待处理数值
     * @return 处理后的数值
     */
    public static String storageUnitShorter(final String input) {
        //不支持小数和空值
        if (StringUtils.isEmpty(input) || input.contains(".")) {
            return null;
        }
        String val = input.toUpperCase();
        if (val.endsWith("B")) {
            val = val.substring(0, val.length() - 1);
            if (val.length() < 4 && !NumberUtils.isDigits(val)) {
                return input.toUpperCase();
            }
        }
        Long value = getStorageByteVal(val);
        if (value == null) {
            return null;
        }
        return storageUnitShorter(value);
    }

    /**
     * 获取存储空间占用量的Byte数值 数值部分不支持小数
     *
     * @param val 待处理数据，含单位。(不含单位则直接作为byte量来进行转换)
     * @return 转换后的数值，失败错误均为null
     */
    private static Long getStorageByteVal(String val) {
        if (NumberUtils.isDigits(val)) {
            return Long.valueOf(val);
        }

        String value = val.substring(0, val.length() - 1);
        String match = val.substring(val.length() - 1);
        //此时表示数据中存在另一个非数字字符
        if (!NumberUtils.isDigits(value)) {
            return null;
        }
        Long base = Long.valueOf(value);
        //转换所有的单位大小写
        int unitLevel = 1;
        for (Map.Entry<Integer, String> stringEntry : StorUnit.dict.entrySet()) {
            if (stringEntry.getValue().equals(match)) {
                unitLevel = stringEntry.getKey();
                break;
            }
        }
        BigDecimal decimal = new BigDecimal(radix);
        long cast = decimal.pow(unitLevel).longValue();

        return base * cast;
    }

    /**
     * 使用JDK8方式处理，将日期定位到一天的开头。用于时序的按天显示和同一天数据归纳
     * 常量放入类型中 (测试处理时间消耗是传统Date的70%)
     *
     * @param date 待处理日期
     * @return
     */
    public static Date toDateStart(Date date) {
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        LocalDate localDate = localDateTime.toLocalDate();
        return Date.from(localDate.atStartOfDay().toInstant(offset));
    }

    /**
     * 使用JDK8方式处理，将日期定位到一个月的开头。用于时序的按周显示和同一周数据归纳
     *
     * @param date 待处理日期
     * @return 时间结果
     */
    public static Date toWeekStart(Date date) {
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        int dayOfWeek = localDateTime.getDayOfWeek().getValue();
        localDateTime = localDateTime.minusDays(dayOfWeek - 1);
        LocalDate localDate = localDateTime.toLocalDate();
        return Date.from(localDate.atStartOfDay().toInstant(offset));
    }

    /**
     * 使用JDK8方式处理，将日期定位到一个月的开头。用于时序的按月显示和同一个月数据归纳
     *
     * @param date 待处理日期
     * @return 时间结果
     */
    public static Date toMonthStart(Date date) {
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        localDateTime = localDateTime.withDayOfMonth(1);
        LocalDate localDate = localDateTime.toLocalDate();
        return Date.from(localDate.atStartOfDay().toInstant(offset));
    }


    /**
     * 使用传统设值方式 将日期定位到一天的开头  基准测试使用
     *
     * @param date 待处理日期
     * @return
     */
    @Deprecated
    public static Date toDateStartOld(Date date) {
        Date date1 = new Date(date.getTime());
        date1.setHours(0);
        date1.setMinutes(0);
        date1.setSeconds(0);
        return date1;
    }


    public static void main(String[] args) {
        System.out.println(storageUnitShorter(12345536678L));
        System.out.println(storageUnitShorter2(12345536678L).toString());
        System.out.println(storageUnitShorter("10240"));
        System.out.println(storageUnitShorter("1TB"));
        System.out.println(storageUnitShorter("1124808Kb"));
        System.out.println(storageUnitShorter("65536MB"));
        System.out.println(storageUnitShorter(Long.MAX_VALUE - 100));
        System.out.println(storageUnitShorter2(Long.MAX_VALUE - 100).toString());
        System.out.println(storageUnitShorter2(0L).toString());
        System.out.println(storageUnitShorter(16384L));
        System.out.println(storageUnitShorter2(-16384L));
    }

    @Data
    public static class UnitValue {
        private String unit;
        private BigDecimal value;

        @Override
        public String toString() {
            return this.getValue() + this.getUnit();
        }
    }

    /**
     * 转换成指定单位存储量
     * @param size  字节数量
     * @param type
     * @return
     */
    public static UnitValue toCustomUnitStorage(Long size, String type) {
        UnitValue unitValue = new UnitValue();

        switch (type) {
            case "B":
                unitValue.setUnit("B");
                unitValue.setValue(BigDecimal.valueOf(size));
                break;
            case "KB":
                unitValue.setUnit("KB");
                unitValue.setValue(BigDecimal.valueOf(size.doubleValue() / Math.pow(2, 10)).setScale(2, BigDecimal.ROUND_HALF_UP));
                break;
            case "MB":
                unitValue.setUnit("MB");
                // 1024 * 1024
                unitValue.setValue(BigDecimal.valueOf(size.doubleValue() / Math.pow(2, 20)).setScale(2, BigDecimal.ROUND_HALF_UP));
                break;
            case "GB":
                unitValue.setUnit("GB");
                // 1024 * 1024 * 1024
                unitValue.setValue(BigDecimal.valueOf(size.doubleValue() / Math.pow(2, 30)).setScale(2, BigDecimal.ROUND_HALF_UP));
                break;
            case "TB":
                unitValue.setUnit("TB");
                // 1024 * 1024 * 1024 * 1024
                unitValue.setValue(BigDecimal.valueOf(size.doubleValue() / Math.pow(2, 40)).setScale(2, BigDecimal.ROUND_HALF_UP));
                break;
            case "PB":
                unitValue.setUnit("PB");
                unitValue.setValue(BigDecimal.valueOf(size.doubleValue() / Math.pow(2, 50)).setScale(2, BigDecimal.ROUND_HALF_UP));
                break;
            case "EB":
                unitValue.setUnit("EB");
                unitValue.setValue(BigDecimal.valueOf(size.doubleValue() / Math.pow(2, 60)).setScale(2, BigDecimal.ROUND_HALF_UP));
                break;
            default:
                break;
        }

        return unitValue;
    }

    /**
     * 截短存储体积大小的工具方法 不能自定义目标单位
     * 先统一规范到最小单位，然后再进行处理
     *
     * @param val 待处理数值
     * @return 处理后的数值
     */
    public static UnitValue storageUnitShorter2(Long val) {
        val = Math.abs(val);
        UnitValue unitValue = new UnitValue();
        long value = val;
        int cycle = 0;
        //最高保留5位
        while (value > radix) {
            value = value / radix;
            cycle++;
        }
        unitValue.setValue(BigDecimal.valueOf(value));
        unitValue.setUnit(StorUnit.dict.get(cycle) + "B");
        return unitValue;
    }


    /**
     * 转换成指定单位存储量
     * @param size  字节数量
     * @param type
     * @return
     */
    public static BigDecimal toMonitorUnitStorage(Long size, String type) {

        switch (type) {
            case "B":
                return BigDecimal.valueOf(size);
            case "KB":
                return BigDecimal.valueOf(size.doubleValue() / Math.pow(2, 10)).setScale(2, BigDecimal.ROUND_HALF_UP);
            case "MB":
                // 1024 * 1024
                return BigDecimal.valueOf(size.doubleValue() / Math.pow(2, 20)).setScale(2, BigDecimal.ROUND_HALF_UP);
            case "GB":
                // 1024 * 1024 * 1024
                return BigDecimal.valueOf(size.doubleValue() / Math.pow(2, 30)).setScale(2, BigDecimal.ROUND_HALF_UP);
            case "TB":
                // 1024 * 1024 * 1024 * 1024
                return BigDecimal.valueOf(size.doubleValue() / Math.pow(2, 40)).setScale(2, BigDecimal.ROUND_HALF_UP);
            case "PB":
                return BigDecimal.valueOf(size.doubleValue() / Math.pow(2, 50)).setScale(2, BigDecimal.ROUND_HALF_UP);
            case "EB":
                return BigDecimal.valueOf(size.doubleValue() / Math.pow(2, 60)).setScale(2, BigDecimal.ROUND_HALF_UP);
            default:
                break;
        }
        return new BigDecimal(0);
    }
}
