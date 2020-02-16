package com.fun.uncle.hello.thread.pc;

/**
 * @Description: 实现生产者与消费者模型，采用了缓存区方法来进行解决:管程法
 * @Author: Xian
 * @CreateDate: 2020/2/16  15:31
 * @Version: 0.0.1-SNAPSHOT
 */
public class TestPC {

    public static void main(String[] args) {
        SyncContainer syncContainer = new SyncContainer();
        new Producer(syncContainer).start();
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

    public Producer(SyncContainer container) {
        this.container = container;
    }

    /**
     * 生产
     */
    @Override
    public void run() {
        for (int i = 1; i < 40; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("生产了第" + i + "只鸡");
            container.push(new Chicken(i));
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
        for (int i = 1; i < 40; i++) {
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
        if (count == chickens.length) {
            //通知消费者消费。生产者等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //如果没有满，那么继续丢入产品
        chickens[count] = chicken;
        count++;

        // 可以通知消费者消费了
        this.notifyAll();

    }

    /**消费者消费产品*/
    public synchronized Chicken pop() {
        // 判断能否消费
        if (count == 0) {
            //等待生产者生产，消费者消费
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        count--;
        Chicken chicken = chickens[count];
        // 通知生产者生产
        this.notifyAll();
        return chicken;
    }



}