package com.hulb.java.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @author hulb
 * @date 2018/3/3 下午3:51
 */
public class SleepUtils {

    public static final void second(long seconds) {
        try {
            Thread.sleep(1000*seconds);
        } catch (InterruptedException e) {

        }
    }


}
