package com.fun.uncle.hello.thread.advanced.add;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Description: 加法计数器
 * @Author: Xian
 * @CreateDate: 2020/2/20  17:00
 * @Version: 0.0.1-SNAPSHOT
 */
public class TestCyclicBarrier {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("【已经集合完成7颗龙珠，召唤神龙!】");
        });

        for (int i = 0; i < 7; i++) {
            // 循环体内的变量在一次循环后就会被释放，下一次进来就是另一个变量。所以可以使用final
            final int temp = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "收集" + temp + "龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
