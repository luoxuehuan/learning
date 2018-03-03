package com.hulb.java.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author hulb
 * @date 2018/3/3 下午3:30
 */
public class TwinsLock implements Lock {

    public static void main(String[] args) {
        System.out.println("1");
    }

    /**
     * 方法获取锁，随后调用unlock()方法释放锁，而同一时刻只能有两个线程同时获取到锁。
     */
    private final Sync sync = new Sync(3);

    /**
     * 一个自定义的同步器。以共享式获取同步状态，同步器会先计算出获取后的同步状态，然后通过CAS确保状态
     * 的正确设置。
     */
    private static final class Sync extends AbstractQueuedSynchronizer {

        Sync(int count) {
            if (count <= 0) {
                throw new IllegalArgumentException("count must larger than zero");
            }
            setState(count);
        }

        /**
         * 状态大于0的时候，表示获取到锁。
         * @param reduceCount
         * @return
         */
        @Override
        public int tryAcquireShared(int reduceCount) {
            for (; ; ) {
                int current = getState();
                int newCount = current - reduceCount;
                if (compareAndSetState(current, newCount)) {
                    return newCount;
                }
            }
        }

        @Override
        public boolean tryReleaseShared(int returnCount) {
            for (; ; ) {
                int current = getState();
                int newCount = current + returnCount;
                if (compareAndSetState(current, newCount)) {
                    return true;
                }
            }
        }
    }


    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
