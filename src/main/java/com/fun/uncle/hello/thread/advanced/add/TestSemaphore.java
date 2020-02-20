package com.fun.uncle.hello.thread.advanced.add;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Description: semaphore信号量问题。我们一般来进行限流处理
 * @Author: Xian
 * @CreateDate: 2020/2/20  17:35
 * @Version: 0.0.1-SNAPSHOT
 */
public class TestSemaphore {
    public static void main(String[] args) {
        // 线程数量: 可以进行限流。我们将其想象成停车位
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "抢到车位");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + "释放车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }).start();
        }
    }
}
