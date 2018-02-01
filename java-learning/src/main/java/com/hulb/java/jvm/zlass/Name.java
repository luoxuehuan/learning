package com.hulb.java.jvm.zlass;

/**
 * @author hulb
 * @date 2018/1/25 下午2:35
 */
public class Name {

    static int count = 0;
    static {
        count++;
        System.out.println("Name Class Loaded! count = [" + count + "]" );
    }

    public Name() {
        System.out.println("Name Constructor called!");
    }
}
