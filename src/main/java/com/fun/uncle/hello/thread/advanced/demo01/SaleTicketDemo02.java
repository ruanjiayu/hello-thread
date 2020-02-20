package com.fun.uncle.hello.thread.advanced.demo01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: Lock基本的卖票例子
 * 真正的多线程开发
 * 线程就是一个单独的资源类，没有任何附属的操作
 * 1. 属性 方法
 * @Author: Xian
 * @CreateDate: 2020/2/20  9:35
 * @Version: 0.0.1-SNAPSHOT
 */
public class SaleTicketDemo02 {
    public static void main(String[] args) {
        Ticket2 ticket = new Ticket2();
        // 并发: 多线程操作同一个资源类，把资源类丢入线程
        new Thread(() -> { for (int i = 0; i < 60; i++) { ticket.sale(); } }, "A").start();
        new Thread(() -> { for (int i = 0; i < 60; i++) { ticket.sale(); } }, "B").start();
        new Thread(() -> { for (int i = 0; i < 60; i++) { ticket.sale(); } }, "C").start();
    }
}

/**
 * 资源类 OOP
 * 1. new ReentrantLock();
 * 2. lock.lock();
 * 3. finally => lock.unlock; // 解锁
 */
class Ticket2 {
    /**
     * 属性
     */
   private int number = 50;

   private Lock lock = new ReentrantLock();
    /**
     * 方法
     */
   public void  sale() {
       // 加锁
       lock.lock();
       try {
           if (number > 0) {
               System.out.println(Thread.currentThread().getName() + "抢到一张票,剩余" + --number +"张");
           }
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           //解锁
           lock.unlock();
       }
   }
}



