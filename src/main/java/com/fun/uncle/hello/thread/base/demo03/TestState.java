package com.fun.uncle.hello.thread.base.demo03;

/**
 * @Description: 观察线程的状态。thread.getState()
 * @Author: Xian
 * @CreateDate: 2020/2/16  10:55
 * @Version: 0.0.1-SNAPSHOT
 */
public class TestState {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("【子线程完成】");
        });

        Thread.State state = thread.getState();
        // NEW
        System.out.println("【目前子线程的状态】" + state);
        // 将线程启动就绪
        thread.start();
        state = thread.getState();
        // Runnable
        System.out.println(state);

        while (state != Thread.State.TERMINATED) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            state = thread.getState();
            System.out.println("【运行的状态】" + state);
        }
    }
}
