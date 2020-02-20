package com.fun.uncle.hello.thread.advanced.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Description:
 * @Author: Xian
 * @CreateDate: 2020/2/20  16:00
 * @Version: 0.0.1-SNAPSHOT
 */
public class TestCallable {

    public static void main(String[] args) {
        // 如何启动线程
        // new Thead(new Runnable()).start();

        MyThread thread = new MyThread();
        FutureTask futureTask = new FutureTask(thread);
        FutureTask futureTask2 = new FutureTask(thread);
        new Thread(futureTask, "A").start();
        new Thread(futureTask2, "B").start();
        new Thread(futureTask, "c").start(); // 同一个对象只会执行一次，有缓存

        try {
            // 获取callable的返回值，可能会阻塞。使用异步通信来处理
            Integer i = (Integer) futureTask.get();
            System.out.println(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}

class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("call");
        // 耗时的操作
        return 1024;
    }
}