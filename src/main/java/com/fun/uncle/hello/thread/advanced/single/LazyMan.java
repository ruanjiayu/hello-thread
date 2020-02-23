package com.fun.uncle.hello.thread.advanced.single;

/**
 * @Description: 懒汉式单例
 *  缺点：线程不安全
 *  优点：不会一开始浪费内存
 * @Author: fan
 * @DateTime: 2020/2/23 8:06 下午
 * @Version: 0.0.1-SNAPSHOT
 */
public class LazyMan {

    private LazyMan() {
        System.out.println(Thread.currentThread().getName() + " OK");
    }

    private static LazyMan lazyMan;

    public static LazyMan getInstance() {
        if (lazyMan == null) {
            lazyMan = new LazyMan();
        }
        return lazyMan;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                LazyMan.getInstance();
            }).start();
        }
    }
}
