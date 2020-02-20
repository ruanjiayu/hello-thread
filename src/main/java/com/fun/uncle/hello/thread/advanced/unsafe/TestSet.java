package com.fun.uncle.hello.thread.advanced.unsafe;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Description: java.util.ConcurrentModificationException
 * @Author: Xian
 * @CreateDate: 2020/2/20  15:18
 * @Version: 0.0.1-SNAPSHOT
 */
public class TestSet {
    public static void main(String[] args) {
        // 并发不安全 底层是hashmap,它的add相当于使用了map.put(); 所以set可以去重
//        Set<Object> set = new HashSet<>();

        /**
         * 解决方案:
         * 1. Set<String> set = Collections.synchronizedSet(new HashSet<>()); synchronized来进行锁
         * 2. Set<String> set = new CopyOnWriteArraySet<>(); 使用lock锁 推荐使用
         */
//        Set<String> set = Collections.synchronizedSet(new HashSet<>());
        Set<String> set = new CopyOnWriteArraySet<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 5));
            System.out.println(set);
            }).start();
        }
    }
}
