package com.hulb.java.lamada;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hulb
 * @date 2018/6/22 下午3:35
 */
public class LamaDaLearning {

    public static void main(String[] args){
        list2list();
    }

    public static void list2list(){
        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(2);
        List<Integer> integerList1 = integerList
                .stream()
                .map(in -> {return in+1;})
                .collect(Collectors.toList());

        integerList1.stream().forEach(System.out::println);
    }
}
