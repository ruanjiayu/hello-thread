package com.fun.uncle.hello.thread.demo03;

/**
 * @Description: 测试stop。
 * 1. 建议线程正常停止 ----->利用次数,不建议死循环
 * 2. 建议使用标志位 -----> 设置标志位
 * 3. 不要使用stop和destroy等过时或者JDK不建议使用的方法
 * @Author: Xian
 * @CreateDate: 2020/2/16  9:57
 * @Version: 0.0.1-SNAPSHOT
 */
public class TestStop implements Runnable{
    // 标志位
    private boolean flag = true;

    @Override
    public void run() {
        int i = 0;
        while (flag) {
            System.out.println("【子线程】: " + i++);
        }
    }

    /**
     *  设置终止线程的方法
     */
    public void stop() {
        this.flag = false;
    }

    public static void main(String[] args) {
        TestStop testStop = new TestStop();
        new Thread(testStop).start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        testStop.stop();
    }
}
