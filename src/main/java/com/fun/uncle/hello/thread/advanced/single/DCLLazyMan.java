package com.fun.uncle.hello.thread.advanced.single;

/**
 * @Description: DCL懒汉式单例模式
 * 使用双层检测模式
 * @Author: fan
 * @DateTime: 2020/2/23 8:12 下午
 * @Version: 0.0.1-SNAPSHOT
 */
public class DCLLazyMan {

    private DCLLazyMan() {
        System.out.println(Thread.currentThread().getName() + " OK");
    }

    private volatile static DCLLazyMan dclLazyMan;

    public static DCLLazyMan getInstance() {
        if (dclLazyMan == null) {
            synchronized (DCLLazyMan.class) {
                if (dclLazyMan == null) {
                    dclLazyMan = new DCLLazyMan(); // 不是一个原子性操作。
                    /**
                     * 1. 分配内存空间
                     * 2. 执行构造方法，初始化对象
                     * 3. 把这个对象指向这个空间
                     *
                     * 132会有可能出现。需要避免这样的情况，所以我们使用了volatile来禁止指令重排
                     */

                }
            }
        }

        return dclLazyMan;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                DCLLazyMan.getInstance();
            }).start();
        }
    }
}
