package com.fun.uncle.hello.thread.advanced.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;

/**
 * @Description:
 * @Author: Xian
 * @CreateDate: 2020/2/22  22:30
 * @Version: 0.0.1-SNAPSHOT
 */
public class Test {
    public static void main(String[] args) {
//        test1(); //11.7s
//        test2(); // 10.3S
        test3(); // 0.4s
    }

    /**
     * 普通程序员
     */
    public static void test1() {
        long start = System.currentTimeMillis();
        Long sum = 0L;
        for (Long i = 1L; i <= 10_0000_0000; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("sum=" + sum + "时间:" + (end - start));
    }

    /**
     * 中级程序员 使用forkJoin
     */
    public static void test2() {
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinDemo task = new ForkJoinDemo(0L, 10_0000_0000L);
        forkJoinPool.execute(task); // 无返回结果。submit有返回结果
        try {
            System.out.println("sum=" + task.get());
            long end = System.currentTimeMillis();
            System.out.println("时间:" + (end - start));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * Stream并行流。高级程序员
     */
    public static void test3() {
        long start = System.currentTimeMillis();
        Long sum = 0L;
        sum = LongStream.rangeClosed(0L, 10_0000_0000).parallel().reduce(0 /**如果这里填写4, 表示在前面数字的基础上，每个数字加4来进行操作*/, Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("sum=" + sum + "时间:" + (end - start));
    }


}
