package com.dtwave.dipper.dubhe.plugin.flink.kafka;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author hulb
 * @date 2019-02-02 14:20
 */
public class ThreadTest {

    public static void main(String[] args) {

        List<Map<String, Object>> mapList = new ArrayList<>();
        for (int i = 0; i < mapList.size(); i++) {
            Map<String, Object> stringMap = mapList.get(i);
            Object value = stringMap.get("key");
        }


        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 20; i++) {
            final int j = i;
            executorService.submit(() -> {
                System.out.println("xxxx" + j);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

}
