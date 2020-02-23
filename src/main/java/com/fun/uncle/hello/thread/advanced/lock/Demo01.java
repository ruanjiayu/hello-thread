package com.fun.uncle.hello.thread.advanced.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 测试可重入锁
 * 就是指只要拥有了一个锁就相当于获得了里面的同样的锁
 * @Author: fan
 * @DateTime: 2020/2/23 8:54 下午
 * @Version: 0.0.1-SNAPSHOT
 */
public class Demo01 {

    public static void main(String[] args) {
        Phone2 phone2 = new Phone2();
        new Thread(() -> {
            phone2.call();
        }).start();

        new Thread(()->{
            phone2.sms();
        }).start();
    }
}

class Phone2 {
    public synchronized void sms() {
        System.out.println(Thread.currentThread().getName() + "sms");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sms OK");
//        call();

    }

    public synchronized void call() {
        System.out.println(Thread.currentThread().getName() + "call");
//        try {
//            TimeUnit.SECONDS.sleep(2);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        sms();
    }
}
