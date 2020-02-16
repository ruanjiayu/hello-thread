package com.fun.uncle.hello.thread.pc;

/**
 * @Description: 测试生产者消费者问题2:信号灯法，标志位解决
 * @Author: Xian
 * @CreateDate: 2020/2/16  18:26
 * @Version: 0.0.1-SNAPSHOT
 */
public class TestPC2 {
    public static void main(String[] args) {
        TV tv = new TV();
        new Player(tv, "小红表演").start();
//        new Player(tv, "小橙表演").start();
//        new Player(tv, "小黄表演").start();
        new Watcher(tv).start();

    }
}

/**
 * 生产者-----演员
 */
class Player extends Thread {
    private TV tv;

    private Boolean tag = true;
    public Player(TV tv, String name) {
        super.setName(name);
        this.tv = tv;
    }

    @Override
    public void run() {
        while (tag){
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                tv.play(Thread.currentThread().getName() + i);
            }
            tag = false;
        }
    }
}

/**
 * 消费者---->观众
 */
class Watcher extends Thread {
    private TV tv;

    private Boolean tag = true;

    public Watcher(TV tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        while (tag) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tv.watch();
        }
    }

    public void setTag(Boolean tag) {
        this.tag = tag;
    }
}

class TV {


    String voice;
    /**
     * 演员表演，观众等待 true
     * 观众观看，演员等待 false
     */
    boolean flag = true;

    /**
     * 表演
     *
     * @param voice
     */
    public synchronized void play(String voice) {
        if (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("【演员表演了】" + voice);
        this.voice = voice;
        this.flag = !this.flag;
        //通知观众观看
        this.notifyAll();
    }

    /**
     * 观众观看
     */
    public synchronized void watch() {
        if (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("【观看了】" + this.voice);

        //通知演员更新节目
        this.notifyAll();
        this.flag = !this.flag;
    }
}