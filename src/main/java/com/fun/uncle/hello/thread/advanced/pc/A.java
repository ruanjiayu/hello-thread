package com.fun.uncle.hello.thread.advanced.pc;


/**
 * @Description: 线程之间的通信问题: 生产者和消费者问题! 等待唤醒,业务,通知唤醒
 * 生产者与消费者之间的关系
 * 判断等待，业务，通知
 * 判断需要将if修改成while防止出现虚假唤醒
 * @Author: Xian
 * @CreateDate: 2020/2/20  10:21
 * @Version: 0.0.1-SNAPSHOT
 */
public class A {
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }

}

/**
 * 资源类
 * // 判断等待,业务,通知
 */
class Data {
    private int number = 0;

    // +1
    public synchronized void increment() throws InterruptedException {
        // 判断等待
        while (number != 0) {
            this.wait();
        }
        // 业务
        number++;
        System.out.println(Thread.currentThread().getName() + "===>" + number);
        // 通知
        this.notifyAll();
    }

    // -1
    public synchronized void decrement() throws InterruptedException {
        while (number == 0) {
            //等待
            this.wait();
        }
        //业务
        number--;
        System.out.println(Thread.currentThread().getName() + "===>" + number);
        //通知
        this.notifyAll();
    }
}
