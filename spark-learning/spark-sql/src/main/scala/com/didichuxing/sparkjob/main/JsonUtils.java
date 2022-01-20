package com.didichuxing.sparkjob.main;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.didichuxing.sparkjob.model.Task;
import com.hulb.support.http.*;


import java.util.Base64;
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

    public static void parseToJsonObject(HashMap<String, Object> row){
        String rowJsonString = JSONObject.toJSONString(row);
        System.out.println(rowJsonString);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("analyseTaskId","B2-A0-19-F3-66-85-10.161.168.169");
        jsonObject.put("result",rowJsonString);

        //返回数据
        //报告ID 必须带上
        Response<JSONObject> response = new Request("http://10.161.168.169:8080/dataMap/api/admin/send_stat_info")
                .body(jsonObject.toJSONString())
                .POST();

        //上报信息
    }

    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("analyseTaskId","0A-4F-3F-B0-11-4F-10.161.168.169");
        jsonObject.put("result","{\"dataRangeDistribution\":{\"data_size_m\":{\"2.878954475E7\":\"267\",\"1.15158179E8\":\"10\",\"5.75790895E7\":\"59\",\"2.878954475E8\":\"6\",\"5.4700135025E8\":\"1\",\"5.182118055E8\":\"4\",\"8.636863425E7\":\"14\",\"3.7426408175E8\":\"1\",\"4.3184317125E8\":\"4\",\"1.727372685E8\":\"7\",\"0.0\":\"7688\",\"3.45474537E8\":\"3\",\"4.8942226075E8\":\"1\",\"5.75790895E8\":\"1\",\"1.4394772375E8\":\"10\"}},\"basic\":{\"task_name\":{\"var_pop\":\"9.764653258535982E28\",\"min\":\"(收投干预）下沉市场资产活跃干预（区县维度）-202111131000\",\"avg\":\"9.471608265106928E13\",\"max\":\"（调度线上化）下沉市场基石2.5工作量监控（区县）-system-advance-202111130013\",\"null_count_percent\":\"null\",\"not_null_max\":\"（调度线上化）下沉市场基石2.5工作量监控（区县）-system-advance-202111130013\",\"only\":\"8675\",\"count\":\"9065\",\"not_null_min\":\"(收投干预）下沉市场资产活跃干预（区县维度）-202111131000\",\"null_count\":\"0\"},\"data_size_m\":{\"var_pop\":\"6.630525789694991E14\",\"avg\":\"5614667.706290242\",\"min\":\"0\",\"max\":\"575790895\",\"null_count_percent\":\"0.0\",\"not_null_max\":\"575790895\",\"count\":\"8076\",\"only\":\"3619\",\"not_null_min\":\"0\",\"null_count\":\"0\"}},\"dataValueDistribution\":{\"task_name\":{\"品牌词挖掘\":\"27\",\"hk_test3\":\"15\",\"query_pose_passed\":\"16\",\"综处-实际扣车监控\":\"15\",\"bill_check\":\"24\"},\"data_size_m\":{\"0\":\"329\",\"1\":\"51\",\"2\":\"46\",\"null\":\"989\",\"920\":\"46\"}}}\n");

        //返回数据
        //报告ID 必须带上
        Response<JSONObject> response = new Request("http://10.161.168.169:8080/dataMap/api/admin/send_stat_info")
                .body(jsonObject.toJSONString())
                .POST();
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
