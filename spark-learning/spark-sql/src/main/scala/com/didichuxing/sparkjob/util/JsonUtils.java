package com.didichuxing.sparkjob.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.didichuxing.sparkjob.model.Task;
import com.hulb.support.http.*;


import java.util.HashMap;
import java.util.Map;

/**
 * @author didi
 */
public class JsonUtils {

    public static Task parseToTask(String input){
        Task task = JSONObject.parseObject(input,Task.class);
       return task;

        //返回数据
        //报告ID 必须带上
//        Response response = new Request("http://localhost:8080/dataMap/api/admin/send_stat_info")
//                .body(rowJsonString)
//                .POST();

        //上报信息
    }

    public static void parseToJsonObject(HashMap<String, HashMap<String,String>> row){
        String rowJsonString = JSONObject.toJSONString(row);
        System.out.println(rowJsonString);

        //返回数据
        //报告ID 必须带上
//        Response response = new Request("http://localhost:8080/dataMap/api/admin/send_stat_info")
//                .body(rowJsonString)
//                .POST();

        //上报信息
    }


    public static String parseToJsonString(Object object){
        String rowJsonString = JSONObject.toJSONString(object);
       return rowJsonString;

        //返回数据
        //报告ID 必须带上
//        Response response = new Request("http://localhost:8080/dataMap/api/admin/send_stat_info")
//                .body(rowJsonString)
//                .POST();

        //上报信息
    }
}
