package com.fun.uncle.hello.thread.advanced.unsafe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description:
 * @Author: Xian
 * @CreateDate: 2020/2/20  15:33
 * @Version: 0.0.1-SNAPSHOT
 */
public class TestMap {
    public static void main(String[] args) {
        //  map 在工作中不是这样使用的
        // 默认等价于 new HashMap<>(16, 0.75);
//        Map<String, String> map = new HashMap<>();

        /**
         * 解决方案
         * 1.  Map<String, String> map = Collections.synchronizedMap(new HashMap<>());
         * 2.   Map<String, String> map = new ConcurrentHashMap<>();
         */

//        Map<String, String> map = Collections.synchronizedMap(new HashMap<>());
        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()-> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 5));
                System.out.println(map);
            },String.valueOf(i)).start();
        }

    }
}
