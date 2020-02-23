package com.fun.uncle.hello.thread.advanced.testVolatile;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 测试volatile不是原子性
 * Unsafe是一个很特殊的类，是来进行原子性操作的类
 * @Author: fan
 * @DateTime: 2020/2/23 6:40 下午
 * @Version: 0.0.1-SNAPSHOT
 */
public class TestAtomic {

    private volatile static AtomicInteger num = new AtomicInteger();

    private static ReentrantLock lock = new ReentrantLock();

    public static void add() {
        num.getAndAdd(1);
//        lock.lock();
//        try {
//            num++;
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            lock.unlock();
//        }

    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                for (int i1 = 0; i1 < 1000; i1++) {
                    add();
                }
            }).start();
        }

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + " " + num);
    }
}
