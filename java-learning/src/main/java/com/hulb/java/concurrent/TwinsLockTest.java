package com.hulb.java.concurrent;

import java.util.concurrent.locks.Lock;

/**
 * @author hulb
 * @date 2018/3/3 下午3:47
 */
public class TwinsLockTest {

    public static void main(String[] args) {
        final Lock lock = new TwinsLock();
        class Worker extends Thread {
            @Override
            public void run() {
                while (true) {
                    lock.lock();
                    try {
                        SleepUtils.second(1);
                        System.out.println("::"+Thread.currentThread().getName());
                        SleepUtils.second(1);
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }
        // 启动10个线程
        for (int i = 0; i < 10; i++) {
            Worker w = new Worker();
            w.setDaemon(true);
            w.start();

        }

        // 每隔1秒换行
        for (int i = 0; i < 10; i++) {
            SleepUtils.second(1);
            System.out.println("------------------"+i);
        }
    }
    /**


     打印结果：

     ::Thread-1
     ::Thread-0
     ------------------0
     ------------------1
     ::Thread-0
     ::Thread-1
     ------------------2
     ------------------3
     ::Thread-1
     ::Thread-0
     ------------------4
     ------------------5
     ::Thread-0
     ::Thread-1
     ------------------6
     ------------------7
     ::Thread-1
     ::Thread-0
     ------------------8
     */
}





