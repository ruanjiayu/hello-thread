package com.fun.uncle.hello.thread.demo03;

/**
 * @Description: 测试守护线程
 * 1. 线程分为用户线程和daemon线程
 * 2. 虚拟机必须保证用户线程执行完毕
 * 3. 虚拟机不用等待daemon线程执行完毕(GC线程)
 * 4. daemon如下GC线程，监控内存，操作日志
 * @Author: Xian
 * @CreateDate: 2020/2/16  11:28
 * @Version: 0.0.1-SNAPSHOT
 */
public class TestDaemon {

    public static void main(String[] args) {
        God god = new God();
        You you = new You();

        Thread godThread = new Thread(god);
        // 默认为false。需要手动设置成true
        godThread.setDaemon(true);
        godThread.start();

        Thread youThread = new Thread(you);
        youThread.start();

    }

}

/**
 * 上帝是daemon
 */
class God implements Runnable {

    @Override
    public void run() {
        while (true) {
            System.out.println("【上帝一直在看着你】");
        }
    }
}

/**
 * 你是用户线程
 */
class You implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 36500; i++) {
            System.out.println("【开心活着】");
        }
        System.out.println("=======goodbye! world!========");
    }
}
