package com.fun.uncle.hello.thread.lock;

/**
 * @Description: 死锁:多个线程抱着对象需要的资源，形成僵持
 * @Author: Xian
 * @CreateDate: 2020/2/16  14:29
 * @Version: 0.0.1-SNAPSHOT
 */
public class DeadLock {
    public static void main(String[] args) {
        Makeup girl01 = new Makeup(0, "灰姑娘");
        Makeup girl02 = new Makeup(1, "白雪公主");

        girl01.start();
        girl02.start();
    }
}

/**
 * 口红
 */
class Lipstick{

}

/**
 * 镜子
 */
class Mirror {

}

class Makeup extends Thread{
    static Lipstick lipstick = new Lipstick();
    static Mirror mirror = new Mirror();
    /**选择*/
    int choice;
    /**使用化妆品的人*/
    String name;

    Makeup(int choice, String name) {
        this.choice = choice;
        this.name = name;
    }

    private void makeup() throws InterruptedException {
        // 选择一，先获得口红在获取镜子
        if (choice == 0) {
            synchronized (lipstick) {
                System.out.println(this.name + "【获得口红】");
                Thread.sleep(1000);
//                synchronized (mirror) {
//                    System.out.println(this.name + "【获取镜子】");
//                    Thread.sleep(2000);
//                }
            }
            synchronized (mirror) {
                System.out.println(this.name + "【获取镜子】");
                Thread.sleep(2000);
            }
        }
        // 选择二，先获取镜子在获取口红
        else {
            synchronized (mirror) {
                System.out.println(this.name + "【获取镜子】");
                Thread.sleep(2000);
//                synchronized (lipstick) {
//                    System.out.println(this.name + "【获取口红】");
//                    Thread.sleep(1000);
//                }
            }
            synchronized (lipstick) {
                System.out.println(this.name + "【获取口红】");
                Thread.sleep(1000);
            }
        }


    }


    @Override
    public void run() {
        try {
            makeup();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
