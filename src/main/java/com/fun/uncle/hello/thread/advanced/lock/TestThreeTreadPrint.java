package com.fun.uncle.hello.thread.advanced.lock;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 三个线程循环打印
 * @Author: Summer
 * @DateTime: 2021/4/22 5:54 下午
 * @Version: 0.0.1-SNAPSHOT
 */
public class TestThreeTreadPrint {

    //num=1就是线程1执行，num=2就是线程2执行，num=3就是线程3执行
    private int num = 1;
    private Lock lock = new ReentrantLock();//创建锁对象
    private Condition condition1 = lock.newCondition();//条件1
    private Condition condition2 = lock.newCondition();//条件2
    private Condition condition3 = lock.newCondition();//条件3


    public void sub1() {
        try {
            lock.lock();//开启锁
            while (num != 1){//这里jdk源码里推荐用while，因为有可能出现虚假唤醒，所以要再次确认
                try {
                    condition1.await();//条件1线程等待，并释放锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //运行到这里，说明num=1
            num=2;
            Thread.sleep(1000);
            System.out.println("线程1运行完毕");
            condition2.signal();//唤醒条件2线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();//释放锁
        }

    }


    public void sub2() {
        try {
            lock.lock();//开启锁
            while (num !=2) {//这里jdk源码里推荐用while，因为有可能出现虚假唤醒，所以要再次确认
                try {
                    condition2.await();//条件2线程等待，并释放锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //如果线程执行到这里，说明num=2
            num=3;
            Thread.sleep(1000);
            System.out.println("线程2运行完毕");
            condition3.signal();//唤醒条件3线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();//释放锁
        }

    }

    public void sub3() {
        try {
            lock.lock();//开启锁
            while (num !=3) {//这里jdk源码里推荐用while，因为有可能出现虚假唤醒，所以要再次确认
                try {
                    condition3.await();//条件3线程等待，并释放锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //如果线程执行到这里，说明num=3
            num=1;
            Thread.sleep(1000);
            System.out.println("线程3运行完毕");
            condition1.signal();//唤醒条件1线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();//释放锁
        }

    }

    @Test
    public void test() {
        TestThreeTreadPrint resource = new TestThreeTreadPrint();
        //第一个线程
        new Thread(() -> {
            while (true) {
                resource.sub1();
            }
        }).start();
        //第二个线程
        new Thread(() -> {
            while (true) {
                resource.sub2();
            }

        }).start();
        //第三个线程
        new Thread(() -> {
            while (true) {
                resource.sub3();
            }

        }).start();

        while (true) {

        }
    }

}
