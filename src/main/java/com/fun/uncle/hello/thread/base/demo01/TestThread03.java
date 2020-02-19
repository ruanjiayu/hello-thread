package com.fun.uncle.hello.thread.base.demo01;

/**
 * @Description: 推荐使用，灵活方便，方便同一个对象被多个线程使用。实现Runnable接口。
 * @Author: Xian
 * @CreateDate: 2020/2/15  21:52
 * @Version: 0.0.1-SNAPSHOT
 */
public class TestThread03 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("【子线程】" + i);
        }
    }

    public static void main(String[] args) {

        // 创建一个线程对象
        TestThread03 testTread03 = new TestThread03();
        // 调用start()方法开启线程
        new Thread(testTread03).start();
        for (int i = 0; i < 5000; i++) {
            System.out.println("【主线程】" + i);
        }
    }
}
