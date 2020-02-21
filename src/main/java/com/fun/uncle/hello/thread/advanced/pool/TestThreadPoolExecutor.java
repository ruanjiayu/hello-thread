package com.fun.uncle.hello.thread.advanced.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 自己定义线程池
 * 自己定义线程池，需要注意7大参数
 *
 * 4大拒绝策略
 * new ThreadPoolExecutor.AbortPolicy()  // 一旦超过了(最大线程数 + 可以等待的线程数目),还有线程进入，不处理这个线程，抛出异常
 * new ThreadPoolExecutor.CallerRunsPolicy() // 一旦超过了(最大线程数 + 可以等待的线程数目),还有线程进入，不处理这个线程，让其返回，由让这个线程诞生的线程来进行相关的操作
 * new ThreadPoolExecutor.DiscardPolicy() //一旦超过了(最大线程数 + 可以等待的线程数目),还有线程进入，不处理这个线程, 直接丢弃线程
 * new ThreadPoolExecutor.DiscardOldestPolicy() // 一旦超过了(最大线程数 + 可以等待的线程数目),还有线程进入，尝试去和最早的线程竞争，如果竞争失败，会将其丢失
 * @Author: Xian
 * @CreateDate: 2020/2/21  14:41
 * @Version: 0.0.1-SNAPSHOT
 */
public class TestThreadPoolExecutor {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2, //核心
                5, //最大可以执行线程数
                10, //等待时间
                TimeUnit.SECONDS, //时间单位
                new LinkedBlockingDeque<>(3),  //超出最大线程数目，可以等待的线程数目
                Executors.defaultThreadFactory(), // 创建线程的工厂
                new ThreadPoolExecutor.AbortPolicy() // 一旦超过了(最大线程数 + 可以等待的线程数目),还有线程进入，不处理这个线程，抛出异常
                );

        try {
            for (int i = 0; i < 10; i++) {
                threadPoolExecutor.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "OK");
                });
            }
        } finally {
                threadPoolExecutor.shutdown();
        }

    }
}
