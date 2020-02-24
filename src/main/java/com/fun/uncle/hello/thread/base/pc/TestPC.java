package com.fun.uncle.hello.thread.base.pc;

/**
 * @Description: 实现生产者与消费者模型，采用了缓存区方法来进行解决:管程法
 * @Author: Xian
 * @CreateDate: 2020/2/16  15:31
 * @Version: 0.0.1-SNAPSHOT
 */
public class TestPC {

    public static void main(String[] args) {
        SyncContainer syncContainer = new SyncContainer();
        new Producer(syncContainer, "小红").start();
        new Producer(syncContainer, "小橙").start();
        new Producer(syncContainer, "小黄").start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Consumer(syncContainer).start();
    }
}

/**
 * 生产者
 */
class Producer extends Thread{
    SyncContainer container;

    public Producer(SyncContainer container, String name) {
        this.container = container;
        super.setName(name);
    }

    /**
     * 生产
     */
    @Override
    public void run() {
        for (int i = 1; i < 10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            container.push(new Chicken(i));
//            System.out.println(this.getName() + "生产了第" + i + "只鸡");
        }
    }
}

/**
 * 消费者
 */
class Consumer extends Thread{
    SyncContainer container;

    public Consumer(SyncContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 1; i < 20; i++) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("消费了第" + container.pop().id + "只鸡");
        }
    }
}

/**
 * 产品
 */
class Chicken{
    /**产品编号**/
    int id;

    public Chicken(int id) {
        this.id = id;
    }
}

/**
 * 缓冲区
 */
class SyncContainer{

    /**定义容器大小*/
    Chicken[] chickens = new Chicken[10];
    /**容器计数器*/
    int count = 0;

    /**生产者放入产品*/
    public synchronized void push(Chicken chicken) {
        System.out.println(Thread.currentThread().getName() + "【开启】" + count);
        while (count == chickens.length) {
            //通知消费者消费。生产者等待
            try {
                System.out.println(Thread.currentThread().getName() + "【停止】");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "【我要开始生产了】" + count);
        // 关键的一步，注意wait一旦被激活是在原来的位置上继续下去运行，而不是重新开始运行线程
        //如果没有满，那么继续丢入产品
        chickens[count] = chicken;
        count++;

        // 可以通知消费者消费了
        this.notifyAll();

    }

    /**消费者消费产品*/
    public synchronized Chicken pop() {
        // 判断能否消费
        while (count == 0) {
            //等待生产者生产，消费者消费
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("目前的数量" + count);
        count--;
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Chicken chicken = chickens[count];
        // 通知生产者生产
        this.notifyAll();
        return chicken;
    }



}