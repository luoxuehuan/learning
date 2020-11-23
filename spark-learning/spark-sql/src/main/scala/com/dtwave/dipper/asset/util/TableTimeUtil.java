package com.dtwave.dipper.asset.util;

import com.dtwave.dipper.asset.entity.TableConfig;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;

/**
 * 本身值：
 * 昨日/最近7天/最近30天/最近60天/截止昨日（所有）
 * <p>
 * <p>
 * 对比值：
 * <p>
 * 前一天
 * 上周同期
 * 最近7日平均
 * 最近30日平均
 *
 * @author hulb
 * @date 2020/3/2 下午1:51
 */
public class TableTimeUtil {

    public static String TIME_FORMAT_DAY = "yyyyMMdd";
    public static String TIME_FORMAT_DAY_AND_LINK = "yyyy-MM-dd";
    public static String TIME_FORMAT_DAY_AND_HOUR = "yyyy-MM-dd HH:mm:ss";


    /**
     * TODO 优化成一天的起始时间到一天的结束时间
     * 根据分区字段分区时间格式,获得时间范围
     *
     */
    public static String timeRangePreviousDays(TableConfig tableConfig) {
        if (tableConfig.getTimeRange() == null || tableConfig.getTimeRange() < 0) {
            return "1=1";
        }

        /**
         * 例如计算前一日的分区的条数
         * 则分区表达式 - 2 获得前一天
         * 分区表达式 格式化后- 1 即今天
         */
        DateTime date = getYesterDay();
        String start = date.minusDays(tableConfig.getTimeRange()).toString(tableConfig.getTimeFieldTimeFormat());
        String end = date.minusDays(tableConfig.getTimeRange() - 1).toString(tableConfig.getTimeFieldTimeFormat());
        String result = tableConfig.getTimeField() + " >= '" + start + "' and " + tableConfig.getTimeField() + " < '" + end + "'";
        return result;
    }

    /**
     * TODO 优化成一天的起始时间到一天的结束时间
     * 根据分区字段分区时间格式,获得时间范围
     */
    public static String timeRangePreviousDays(String timeFormat,
                                               String timeField,
                                               Integer daysBefore) {
        /**
         * 例如计算前一日的分区的条数
         * 则分区表达式 - 2 获得前一天
         * 分区表达式 格式化后- 1 即今天
         */
        DateTime date = getYesterDay();
        String start = date.minusDays(daysBefore).toString(timeFormat);
        String end = date.minusDays(daysBefore - 1).toString(timeFormat);
        String result = timeField + " >= '" + start + "' and " + timeField + " < '" + end + "'";
        return result;
    }

    public static DateTime getYesterDay() {
        DateTime dateTime = DateTime.parse(DateTime.now().toString("YYYY-MM-dd"));
        return dateTime.minusDays(1);
    }

    public static DateTime toDateTime(String time, String timeFormat) {
        //DateTimeFormatter dateTimeFormatter = new DateTimeFormatter(timeFormat);
        DateTime dateTime = DateTime.parse("2020-03-02");
        System.out.println(dateTime.toString());
        return dateTime;
    }


    /**
     * 在分区过滤时可以使用
     * @param days
     * @return
     */
    public static Integer getTimeStampBefore(Integer days) {
        DateTime dateTime = DateTime.parse(DateTime.now().toString("YYYY-MM-dd"));
        long timestamp = dateTime.minusDays(days).toDate().getTime() / 1000;
        return Integer.valueOf(String.valueOf(timestamp));
    }


    public static void main(String[] args) {
        DateTime dateTime = DateTime.parse(DateTime.now().toString("YYYY-MM-dd"));
        System.out.println(dateTime.toString("YYYY-MM-dd HH:mm:ss"));
        System.out.println(dateTime.toDate().getTime());
        long timestamp = dateTime.toDate().getTime() / 1000;
        System.out.println(timestamp);
    }

}
