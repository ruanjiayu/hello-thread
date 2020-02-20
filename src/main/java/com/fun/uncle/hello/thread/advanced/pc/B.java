package com.fun.uncle.hello.thread.advanced.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 线程之间的通信问题: 生产者和消费者问题! 等待唤醒,业务,通知唤醒
 * 生产者与消费者之间的关系
 * 判断等待，业务，通知
 * 判断需要将if修改成while防止出现虚假唤醒
 * @Author: Xian
 * @CreateDate: 2020/2/20  10:21
 * @Version: 0.0.1-SNAPSHOT
 */
public class B {
    public static void main(String[] args) {
        Data2 data = new Data2();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }

}

/**
 * 资源类
 * // 判断等待,业务,通知
 */
class Data2 {
    private int number = 0;

    private final  Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    // +1
    public void increment() throws InterruptedException {
        lock.lock();
        try {
            // 判断等待
            while (number != 0) {
                condition.await();
            }
            // 业务
            number++;
            System.out.println(Thread.currentThread().getName() + "===>" + number);
            // 通知
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    // -1
    public  void decrement() throws InterruptedException {
        lock.lock();
        try {
            while (number == 0) {
                //等待
                condition.await();
            }
            //业务
            number--;
            System.out.println(Thread.currentThread().getName() + "===>" + number);
            //通知
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
