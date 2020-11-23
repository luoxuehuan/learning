package com.dtwave.dipper.asset.function;

import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.api.java.UDF2;

/**
 * @author hulb
 * @date 2020/3/3 下午2:50
 */
public class RegexFunction implements UDF2<String, String, Integer> {

    @Override
    public Integer call(String column, String regex) {
        if (column.matches(regex)) {
            return 0;
        } else {
            return 1;
        }
    }
}
