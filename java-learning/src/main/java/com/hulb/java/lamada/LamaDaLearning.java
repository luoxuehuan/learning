package com.hulb.java.lamada;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author hulb
 * @date 2018/6/22 下午3:35
 */
public class LamaDaLearning {

    public static void main(String[] args) {
        list2list();
        list2map();
    }

    public static void list2list() {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(2);
        List<Integer> integerList1 = integerList
                .stream()
                .map(in -> in + 1)
                .collect(Collectors.toList());

        integerList1.stream().forEach(System.out::println);
    }


    public static void list2map() {
        List<Abc> integerList = new ArrayList<>();

        //FIXME key重复会报错 但for循环不会报错
        Map<String, String> map = integerList
                .stream()
                .collect(Collectors.toMap(Abc::getA, Abc::getB));

        map.forEach(System.out::println);
    }
}


class Abc {
    private String a;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    private String b;
}