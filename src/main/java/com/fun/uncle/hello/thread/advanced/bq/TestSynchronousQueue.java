package com.fun.uncle.hello.thread.advanced.bq;

import sun.util.locale.provider.TimeZoneNameUtility;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 同步队列
 * 和其他的blockQueue不一样，SynchronousQueue 不存储元素,所以peek得到的结果永远是null
 * put了一个元素，必须从里面先taker取出来，否则不能在put进入，有点像信号量。
 * @Author: Xian
 * @CreateDate: 2020/2/21  11:36
 * @Version: 0.0.1-SNAPSHOT
 */
public class TestSynchronousQueue {
    public static void main(String[] args) {
        SynchronousQueue synchronousQueue = new SynchronousQueue();

        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName() + " put 1");
                synchronousQueue.put("1");
                System.out.println(Thread.currentThread().getName() + " put 2");
                synchronousQueue.put("2");
                System.out.println(Thread.currentThread().getName() + " put 3");
                synchronousQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + "取出了" + synchronousQueue.take());
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + "取出了" + synchronousQueue.take());
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + "取出了" + synchronousQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }, "B").start();
    }

}
