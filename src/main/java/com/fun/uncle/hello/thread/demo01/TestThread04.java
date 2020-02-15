package com.fun.uncle.hello.thread.demo01;

/**
 * @Description: 例二:多个线程同时操作同一个对象，例如购买货车票。发现问题:多个线程操作同一个资源的情况下，线程不安全
 * @Author: Xian
 * @CreateDate: 2020/2/15  21:59
 * @Version: 0.0.1-SNAPSHOT
 */
public class TestThread04 implements Runnable{
    /**票数*/
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
