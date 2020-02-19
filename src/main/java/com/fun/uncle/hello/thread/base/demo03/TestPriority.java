package com.fun.uncle.hello.thread.base.demo03;

/**
 * @Description: 设置线程的优先级。new Thread().setPriority()。范围是1~10.有可能优先级高的不会优先执行，看CPU调度。
 * @Author: Xian
 * @CreateDate: 2020/2/16  11:12
 * @Version: 0.0.1-SNAPSHOT
 */
public class TestPriority {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "--->" + Thread.currentThread().getPriority());
        MyPriority myPriority = new MyPriority();

        Thread t1 = new Thread(myPriority, "t1");
        Thread t3 = new Thread(myPriority, "t3");
        Thread t5 = new Thread(myPriority, "t5");
        Thread t8 = new Thread(myPriority, "t8");
        Thread t10 = new Thread(myPriority, "t10");

        t1.setPriority(Thread.MIN_PRIORITY);
        t1.start();

        t3.setPriority(3);
        t3.start();

        t5.start();

        t8.setPriority(8);
        t8.start();

        t10.setPriority(Thread.MAX_PRIORITY);
        t10.start();
    }
}

class MyPriority implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "-->" + Thread.currentThread().getPriority());
    }
}
