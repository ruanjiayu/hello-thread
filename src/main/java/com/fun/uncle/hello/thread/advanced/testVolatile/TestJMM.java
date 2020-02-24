package com.fun.uncle.hello.thread.advanced.testVolatile;

import java.util.concurrent.TimeUnit;

/**
 * @Description: volatile具有3个特性
 * 1. 可见性
 * 2. 禁止指令重排(依靠了内存屏障)
 * 3. 不能保证原子性
 * @Author: fan
 * @DateTime: 2020/2/23 6:23 下午
 * @Version: 0.0.1-SNAPSHOT
 */
public class TestJMM {

    /**
     * 不加volatile 程序会死循环
     * 加了volatile 可以保证可见性
     */
    private volatile static int num = 0;

    public static void main(String[] args) {

        // 线程1 对于主内存的变化不知道,因为使用了类似匿名内部类，相当于重新写了一个属性
        new Thread(()->{
            while (num == 0) {

            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        num = 1;
        System.out.println(num);
    }
}
