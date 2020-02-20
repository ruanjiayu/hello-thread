package com.fun.uncle.hello.thread.advanced.add;

import java.util.concurrent.CountDownLatch;

/**
 * @Description: 进行计数的递减
 * @Author: Xian
 * @CreateDate: 2020/2/20  16:39
 * @Version: 0.0.1-SNAPSHOT
 */
public class TestCountDownLatch {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + " GO OUT");
                // -1 操作
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        try {
            // 判断是否等于0.如果等于0，那么进行下面的操作
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("close door");
    }
}
