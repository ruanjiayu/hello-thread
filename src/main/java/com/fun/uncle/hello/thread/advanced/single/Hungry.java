package com.fun.uncle.hello.thread.advanced.single;

/**
 * @Description: 饿汉式单例
 * 好处：线程安全，缺点是一开始就加载浪费内存。
 * @Author: fan
 * @DateTime: 2020/2/23 7:57 下午
 * @Version: 0.0.1-SNAPSHOT
 */
public class Hungry {

    private Hungry() {

    }

    private final static Hungry HUNGRY = new Hungry();

    public static Hungry getInstance() {
        return HUNGRY;
    }
}
