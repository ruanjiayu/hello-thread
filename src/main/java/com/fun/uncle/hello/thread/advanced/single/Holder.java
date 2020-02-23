package com.fun.uncle.hello.thread.advanced.single;

/**
 * @Description: 静态内部类的方式来进行
 * @Author: fan
 * @DateTime: 2020/2/23 8:24 下午
 * @Version: 0.0.1-SNAPSHOT
 */
public class Holder {

    private Holder() {
        System.out.println(Thread.currentThread().getName() + " OK");
    }

    public static Holder getInstance() {
        return InnerClass.HOLDER;
    }

    public static class InnerClass{
        private static final Holder HOLDER = new Holder();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                getInstance();
            }).start();
        }
    }
}
