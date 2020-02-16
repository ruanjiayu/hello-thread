package com.fun.uncle.hello.thread.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: ReentrantLook 可重入锁。Lock是显示锁，JVM花费的时间更少来调度线程，具有更好的拓展性。
 * 优先使用: lock > 同步块 > 同步方法
 * @Author: Xian
 * @CreateDate: 2020/2/16  14:55
 * @Version: 0.0.1-SNAPSHOT
 */
public class TestLock {
    public static void main(String[] args) {
        TestLock2 testLock2 = new TestLock2();
        new Thread(testLock2, "小红").start();
        new Thread(testLock2, "小橙").start();
        new Thread(testLock2, "小黄").start();
    }

}


class TestLock2 implements Runnable {
    int tickNums = 10;

    /**
     *可重复锁
     */
    private final ReentrantLock lock = new ReentrantLock();
    @Override
    public void run() {
        while (true) {
            lock.lock();
            try{
                if (tickNums > 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "获取了第" + tickNums-- + "票");
                }
                else {
                    break;
                }
            }finally {
                // 注意一定要在最后释放锁
                lock.unlock();
            }
        }
    }
}