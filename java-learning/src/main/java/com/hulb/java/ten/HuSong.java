package com.hulb.java.ten;

import lombok.Data;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hulb
 * @date 2020/4/17 下午2:36
 */
public class HuSong {

    /**
     *
     */
    public static void main(String[] args)throws Exception {
        int a = 1;
        Double d = Double.valueOf(a);
        System.out.println(d);
        int c= 1588130198;
        System.out.println(c);


        double percent = 0;
        BigDecimal allBd = new BigDecimal(89);
        percent = new BigDecimal(34).divide(allBd, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100.00)).doubleValue();
        System.out.println(percent);

        List<Person>  personList = getPersons();
        Map<Integer,Person> giftMap = new HashMap<>();
        Map<Integer,Person> successGift = new HashMap<>();
        personList.stream()
                .forEach(person -> {
                    giftMap.put(person.getId(),person);
                });
        for(int i=0;i<personList.size();i++){
            Person person = personList.get(i);
            System.out.print(person.getName());
            int num = 0;
            //for(int time=1;time<=maxCount;time++){
            do {
                num = (int)(Math.random()*giftMap.size());
            } while (successGift.containsKey(num) || num == person.getId());
                //if(num == person.getPreferGift()){
                   // System.out.print(" TIME "+ time +" Yeah Yeah Yeah");
                    //break;
                //}
          //  }
            System.out.println("-----抽中------>"+personList.get(num).getName()+"的礼物："+personList.get(num).getGift());
            successGift.put(num,null);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static List<Person> getPersons() throws IOException {
        List<Person> personList = new ArrayList<>();
        List<String> gifts = FileUtils.readLines(new File("/Users/hulb/data/gift.txt"));
        for(int i=0;i<gifts.size();i++){
            String p = gifts.get(i);
            String id = p.split("\t")[0];
            String name = p.split("\t")[1];
            String gift = p.split("\t")[2];
            Person person = new Person();
            person.setId(Integer.valueOf(id));
            person.setName(name);
            person.setGift(gift);
            personList.add(person);
        }
        return personList;
    }

    private static Map<Integer,String> getGifts() {
        List<Person> personList = new ArrayList<>();
        for(int i=0;i<=30;i++){
            Person person = new Person();
            person.setId(i);
            person.setName("name"+i);
            person.setGift("gift"+i);
            person.setPreferGift(10-i);
            personList.add(person);
        }
        return null;
    }
}

@Data
class Person{
    private Integer id;
    private String name;
    private String gift;
    private Integer preferGift;
    private String getGiftFrom;
    private String getGiftName;
    private Integer weight;
}

@Data
class Gift{
    private Integer id;
    private String giftName;
}
