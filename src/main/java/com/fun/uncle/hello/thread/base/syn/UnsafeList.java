package com.fun.uncle.hello.thread.base.syn;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 线程不安全集合。记住synchronized锁的是对象
 * 因为存在一种情况，就是两个插入list的线程插入了同一个位置，导致数据的覆盖
 * @Author: Xian
 * @CreateDate: 2020/2/16  13:32
 * @Version: 0.0.1-SNAPSHOT
 */
public class UnsafeList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                synchronized (list) {
                    list.add(Thread.currentThread().getName());
                }
            }).start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("【存放了多少个线程】" + list.size());
    }
}
