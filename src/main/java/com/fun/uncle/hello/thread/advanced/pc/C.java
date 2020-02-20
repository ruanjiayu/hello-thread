package com.fun.uncle.hello.thread.advanced.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 线程间的通信。producer和consumer之间相互转换
 * A调用完调用B，B调用完调用C，C调用完调用A
 * @Author: Xian
 * @CreateDate: 2020/2/20  11:03
 * @Version: 0.0.1-SNAPSHOT
 */
public class C {

    public static void main(String[] args) {
        Data3 data3 = new Data3();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data3.printA();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data3.printB();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data3.printC();
            }
        }, "C").start();

    }
}

/**
 * 资源类
 * 1. 不管怎么样，先直接上锁
 */
class Data3 {

    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();
    /**
     * 1A 2B 3C
     */
    private int number = 1;

    public void printA() {
        lock.lock();
        // 1. 上锁
        try {
            // 判断等待
            while (number != 1) {
                conditionA.await();
            }
            // 业务
            System.out.println(Thread.currentThread().getName() + "==>AAAA");
            number = 2;
            // 通知
            conditionB.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }

    public void printB() {
        lock.lock();
        try {
            // 判断等待
            while (number != 2) {
                conditionB.await();
            }
            // 业务
            System.out.println(Thread.currentThread().getName() + "===>BBBB");
            number = 3;
            //通知
            conditionC.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void printC() {
        lock.lock();
        try {
            // 判断等待
            while (number != 3) {
                conditionC.await();
            }
            // 业务
            System.out.println(Thread.currentThread().getName() + "====>cccc");
            number = 1;
            // 通知
            conditionA.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
