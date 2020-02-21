package com.fun.uncle.hello.thread.advanced.bq;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Description: ArrayBlockingQueue 使用的是先进先出策略
 * @Author: Xian
 * @CreateDate: 2020/2/21  10:52
 * @Version: 0.0.1-SNAPSHOT
 */
public class TestArrayBlockingQueue {
    public static void main(String[] args) {
        /**
         * 报错型
         */
//        test1();
        /**
         * 不报错型
         */
//        test2();

        /**
         * 等待阻塞形，插入数据的时候无返回值
         */
//        try {
//            test3();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        /**
         * 超时等待
         */
        try {
            test4();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 抛出异常
     */
    public static void test1() {
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(3);

        System.out.println(arrayBlockingQueue.add("a"));
        System.out.println(arrayBlockingQueue.add("b"));
        System.out.println(arrayBlockingQueue.add("c"));
        System.out.println("【插入满了会报错】java.lang.IllegalStateException");
//        System.out.println(arrayBlockingQueue.add("a"));

        System.out.println("================");
        // 查看队首是什么元素
        System.out.println(arrayBlockingQueue.element());
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());

        System.out.println("【数据没有了会报错】java.util.NoSuchElementException");
        System.out.println(arrayBlockingQueue.remove());

    }

    /**
     * 不会抛出异常
     */
    public static void test2() {
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(3);

        System.out.println(arrayBlockingQueue.offer("a"));
        System.out.println(arrayBlockingQueue.offer("b"));
        System.out.println(arrayBlockingQueue.offer("c"));
        // 返回false
        System.out.println(arrayBlockingQueue.offer("d"));

        System.out.println("================");
        // 查看队首是什么元素
        System.out.println(arrayBlockingQueue.peek());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        // 返回null
        System.out.println(arrayBlockingQueue.poll());
    }


    /**
     * 一直等待
     */
    public static void test3() throws InterruptedException {
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(3);

        arrayBlockingQueue.put("a");
        arrayBlockingQueue.put("b");
        arrayBlockingQueue.put("c");

//        arrayBlockingQueue.put("d"); //会一直阻塞，等待上面的位置释放

        System.out.println("================");
        // 查看队首是什么元素
        System.out.println(arrayBlockingQueue.take());
        System.out.println(arrayBlockingQueue.take());
        System.out.println(arrayBlockingQueue.take());
        // 返回null
        System.out.println(arrayBlockingQueue.take());
    }

    /**
     * 超时等待
     */
    public static void test4() throws InterruptedException {
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(3);

        arrayBlockingQueue.offer("a");
        arrayBlockingQueue.offer("b");
        arrayBlockingQueue.offer("c");

        arrayBlockingQueue.offer("d", 2, TimeUnit.SECONDS); // 等待两秒，不行就退出

        System.out.println("================");
        // 查看队首是什么元素
        System.out.println(arrayBlockingQueue.poll(2, TimeUnit.SECONDS));
        System.out.println(arrayBlockingQueue.poll(2, TimeUnit.SECONDS));
        System.out.println(arrayBlockingQueue.poll(2, TimeUnit.SECONDS));
        // 返回null
        System.out.println(arrayBlockingQueue.poll(2, TimeUnit.SECONDS));
    }
}

