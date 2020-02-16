package com.fun.uncle.hello.thread.demo03;

import com.fun.uncle.hello.thread.demo01.TestThread04;

/**
 * @Description: 模拟网络延迟: 放大问题的发生性。记住每个对象都有一个锁，sleep不会释放锁
 * @Author: Xian
 * @CreateDate: 2020/2/16  10:10
 * @Version: 0.0.1-SNAPSHOT
 */
public class TestSleep implements Runnable {

    /**
     * 票数
     */
    private int ticketNums = 10;

    @Override
    public void run() {
        while (true) {
            if (ticketNums <= 0) {
                break;
            }
            // 模拟延迟
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "--拿到了第" + ticketNums-- + "票");
        }
    }

    public static void main(String[] args) {
        TestThread04 testThread04 = new TestThread04();
        new Thread(testThread04, "小红").start();
        new Thread(testThread04, "小橙").start();
        new Thread(testThread04, "小黄").start();
    }
}
