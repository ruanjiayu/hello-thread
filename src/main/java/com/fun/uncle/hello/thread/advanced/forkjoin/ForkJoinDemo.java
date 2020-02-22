package com.fun.uncle.hello.thread.advanced.forkjoin;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @Description:
 * @Author: Xian
 * @CreateDate: 2020/2/22  22:15
 * @Version: 0.0.1-SNAPSHOT
 */
public class ForkJoinDemo extends RecursiveTask<Long> {

    private Long start;

    private Long end;
    /**
     * 这个是JDK7的表达方法
     */
    private Long temp = 1_0000L;

    public ForkJoinDemo(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if ((end - start) < temp) {
            Long sum = 0L;
            for (Long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            //forkJoin
            Long mid = (start + end) /2;
            ForkJoinDemo task1 = new ForkJoinDemo(start, mid);
            task1.fork();// 拆分任务,把任务压入线程队列
            ForkJoinDemo task2 = new ForkJoinDemo(mid + 1, end);
            task2.fork();// 拆分任务,把任务压入线程队列
            return task1.join() + task2.join();
        }
    }
}
