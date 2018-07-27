package com.dtwave.dipper.dubhe.plugin.flink.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.Random;


/**
 * 模拟数据生成
 *
 *数据格式：
 * {"orderId":18,"proName":"prt3","amount":1,"orderTime":1512974707597}
 * {"orderId":19,"proName":"prt4","amount":1,"orderTime":1512974708601}
 * {"orderId":20,"proName":"prt0","amount":1,"orderTime":1512974709607}
 *
 * @author hulb
 * @date 2017/12/11 下午2:43
 */
public class KafkaDataSimlationOrder extends BaseSimlation{
    private static Logger log = LoggerFactory.getLogger(KafkaDataSimlationOrder.class);
    private KafkaProducer<String,String> producer;


    private final DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    @Before
    public void init() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "mq250:9092,mq221:9092,mq164:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("request.timeout.ms", 45000);
        props.put("buffer.memory", 33554432);
        props.put("compression.type", "snappy");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(props);
    }




    @Test
    public void process() throws Exception {
        for (int i = 1; i < 10000; i++) {
            Order order=new Order();
            String id  = getRandom(1,3);
            order.set_id(Long.valueOf(id));
            order.setOrderId(Integer.toUnsignedLong(i));
            order.setProName("prt_order_upsert_37_______"+i);
            order.setAmount(1);
            long currentTime=System.currentTimeMillis();
            order.setOrderTime(currentTime);
            String adsContent = new ObjectMapper().writeValueAsString(order);
            System.out.println(adsContent);
            //ProducerRecord<String,String> adsProduceRecord = new ProducerRecord<>("spark_kafka_1", adsContent);
            ProducerRecord<String,String> adsProduceRecord = new ProducerRecord<>("orders", adsContent);
            producer.send(adsProduceRecord, new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e != null) {
                        System.out.println("failed to send the record,"+ e.getMessage());
                    }else{
                        System.out.println("send successfully");
                    }
                }
            });
            Thread.sleep(2000L);
        }
    }

    @After
    public void destroyDown() {
        producer.close();
        log.info("program is over....");
    }

    String getDateStr(long ms){
        LocalDateTime ldt = LocalDateTime.ofInstant(Instant.ofEpochMilli(ms), ZoneId.systemDefault());
        return  ldt.format(formatter);
    }


    public static String getRandom(int min, int max){
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return String.valueOf(s);

    }
}
