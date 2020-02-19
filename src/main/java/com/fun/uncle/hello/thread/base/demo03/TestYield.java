package com.fun.uncle.hello.thread.base.demo03;

/**
 * @Description: 测试礼让线程
 * 1. 礼让线程让当前正在执行的线程暂停，但【不阻塞】
 * 2. 将线程从运行状态转为就绪状态
 * 3. 让CPU重新调度，礼让不一定成功
 * @Author: Xian
 * @CreateDate: 2020/2/16  10:28
 * @Version: 0.0.1-SNAPSHOT
 */
public class TestYield {
    public static void main(String[] args) {
        MyYield myYield = new MyYield();
        new Thread(myYield, "a").start();
        new Thread(myYield, "b").start();
    }


}

class MyYield implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "【开始】");
        Thread.yield(); // 礼让线程，让线程回到就绪状态，让CPU重新调度，礼让不一定成功
        System.out.println(Thread.currentThread().getName() + "【结束】");
    }
}