package com.fun.uncle.hello.thread.advanced.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 创建线程池的三大方法。我们一般不将其使用
 * @Author: Xian
 * @CreateDate: 2020/2/21  14:23
 * @Version: 0.0.1-SNAPSHOT
 */
public class TestExecutors {
    public static void main(String[] args) {
        // 创建单个线程
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();

        // 创建一个固定大小的线程池
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);
//        // 创建一个可以伸缩，遇强则强，遇弱则弱
        ExecutorService threadPool = Executors.newCachedThreadPool();

        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + " OK ");
                });
            }
        } finally {
                threadPool.shutdown();
        }
    }
}
