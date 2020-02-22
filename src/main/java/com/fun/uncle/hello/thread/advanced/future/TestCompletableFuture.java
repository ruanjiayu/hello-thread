package com.fun.uncle.hello.thread.advanced.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 异步请求
 * @Author: Xian
 * @CreateDate: 2020/2/22  23:32
 * @Version: 0.0.1-SNAPSHOT
 */
public class TestCompletableFuture {
    public static void main(String[] args) throws Exception {
        // 发起异步一个请求。无返回值
//        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()->{
//            System.out.println("start sleep");
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("sleep OK");
//        });
//
//        System.out.println("hello world");
//        completableFuture.get(); //会阻塞在这里，直到异步请求完成
//        System.out.println("two thousand year");
//        TimeUnit.SECONDS.sleep(2);

        /**
         * 发起一个异步请求,有返回值。
         */
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "=> supplyAsync");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int i = 10 / 0;
            return 200;
        });
        System.out.println(completableFuture.whenComplete((t, u) -> {
            System.out.println("【t】=>" + t);  // 返回正确的结果
            System.out.println("【u】=>" + u); // u是错误的信息
        }).exceptionally((e) -> {
            System.out.println("【e】=>" + e.getMessage());
            return 500;
        }).get());
        System.out.println("hello xufan");
//        System.out.println("【请求返回的值】=>" + completableFuture.get());
// 会阻塞在这里，直到异步请求完成,但是在这里不能获取异常错误码，所以将其挪到上面get
        System.out.println("two thousand year");
    }
}
