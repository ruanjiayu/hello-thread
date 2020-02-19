package com.fun.uncle.hello.thread.base.demo01;

/**
 * @Description: 创建线程方式一： 集成thread类, 重写run(), 调用start来开启线程。线程开启不一定立刻执行而是由CPU控制。不建议使用,因为java的OOP单继承原则
 * @Author: Xian
 * @CreateDate: 2020/2/15  16:52
 * @Version: 0.0.1-SNAPSHOT
 */
public class TestThread01 extends Thread{

    /**
     * run 方法线程体
     */
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("【子线程】" + i);
        }
    }

    public static void main(String[] args) {
        // 创建一个线程对象
        TestThread01 testTread01 = new TestThread01();
        // 调用start()方法开启线程
        testTread01.start();
        for (int i = 0; i < 5000; i++) {
            System.out.println("【主线程】" + i);
        }
    }
}
