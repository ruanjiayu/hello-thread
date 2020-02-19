package com.fun.uncle.hello.thread.base.pool;

import java.util.concurrent.*;

/**
 * @Description:
 * @Author: Xian
 * @CreateDate: 2020/2/16  19:05
 * @Version: 0.0.1-SNAPSHOT
 */
public class TestPool {

    public static void main(String[] args) {


        ExecutorService service = Executors.newFixedThreadPool(3);
//        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
//        ExecutorService service = new ThreadPoolExecutor(10, 10, 2, TimeUnit.SECONDS, workQueue);

        service.execute(new MyThread());
        service.execute(new MyThread());
        service.execute(new MyThread());
        service.execute(new MyThread());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.shutdownNow();

    }
}

class MyThread implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
