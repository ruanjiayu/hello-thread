package com.fun.uncle.hello.thread.base.demo03;

/**
 * @Description: join合并线程，其他线程阻塞，待此线程执行完成后，再执行其他线程。可以想象成插队
 * @Author: Xian
 * @CreateDate: 2020/2/16  10:40
 * @Version: 0.0.1-SNAPSHOT
 */
public class TestJoin implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            System.out.println("【VIP】在插队" + i);
        }
    }

    public static void main(String[] args) {
        TestJoin testJoin = new TestJoin();
        Thread childThread = new Thread(testJoin);

        childThread.start(); // 注意要将子线程变成就绪状态
        for (int i = 0; i < 500; i++) {
            if (i == 100) {
                try {
                    childThread.join(); //main线程会被阻塞
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("【主线程】" + i);
        }


    }
}
