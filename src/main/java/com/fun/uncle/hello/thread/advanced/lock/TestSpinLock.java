package com.fun.uncle.hello.thread.advanced.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Description: 实现自己的自旋锁
 * @Author: fan
 * @DateTime: 2020/2/23 9:16 下午
 * @Version: 0.0.1-SNAPSHOT
 */
public class TestSpinLock {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    /**
     * 加锁
     */
    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "===> myLock");

        // 自旋锁
        while (!atomicReference.compareAndSet(null, thread)) {

        }
    }


    /**
     * 解锁
     */
    public void myUnLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "===> myUnLock");
        atomicReference.compareAndSet(thread, null);
    }

    public static void main(String[] args) {
        TestSpinLock lock = new TestSpinLock();

        new Thread(() -> {
            lock.myLock();
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.myUnLock();
            }
        }, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            lock.myLock();
            try {
            }
            finally {
                lock.myUnLock();
            }
        }, "t2").start();


    }
}
