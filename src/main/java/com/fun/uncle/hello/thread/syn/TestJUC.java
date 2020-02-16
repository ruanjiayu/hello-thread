package com.fun.uncle.hello.thread.syn;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Description: 线程安全的
 * @Author: Xian
 * @CreateDate: 2020/2/16  14:11
 * @Version: 0.0.1-SNAPSHOT
 */
public class TestJUC {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                list.add(Thread.currentThread().getName());
            }).start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("【入库】" + list.size() + "条");
    }
}

