package com.fun.uncle.hello.thread.advanced.unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Description: java.util.ConcurrentModificationException 并发修改异常
 * @Author: Xian
 * @CreateDate: 2020/2/20  14:48
 * @Version: 0.0.1-SNAPSHOT
 */
public class TestList {
    public static void main(String[] args) {
        // 并发下ArrayList 不安全

//        List<String> list = new ArrayList<>();
        /**
         * 解决方案:
         * 1. List<String> list = new Vector<>();   方法同步
         * 2. List<String> list = Collections.synchronizedList(new ArrayList<>());  同步方法块
         * 3. List<String> list = new CopyOnWriteArrayList<>();   lock锁 推荐使用
         */
//        List<String> list = new Vector<>();
//        List<String> list = Collections.synchronizedList(new ArrayList<>());
        // CopyOnWrite 写入时复制 简称 COW 计算机程序设计领域的一种优化策略
        // 采用了读写分离的模式，也就是说先COPY一份原来的数据，在进行修改，修改好了以后将其覆盖

        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list);
            }).start();
        }
    }
}
