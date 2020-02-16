package com.fun.uncle.hello.thread.syn;

/**
 * @Description: 不安全的买票。模拟网络延迟: 放大问题的发生性。记住每个对象都有一个锁，sleep不会释放锁
 * @Author: Xian
 * @CreateDate: 2020/2/16  12:55
 * @Version: 0.0.1-SNAPSHOT
 */
public class UnsafeBuyTicket {
    public static void main(String[] args) {
        BuyTicket buyTicket = new BuyTicket();

        Thread t1 = new Thread(buyTicket, "小红");
        Thread t2 = new Thread(buyTicket, "小橙");
        Thread t3 = new Thread(buyTicket, "小黄");

        t1.start();
        t2.start();
        t3.start();
    }

}


class BuyTicket implements Runnable {

    // 票
    private int ticketNums = 10;

    private Boolean flag = true;

    @Override
    public void run() {
        // 买票
        while (flag) {
            buy();
        }
    }

    /** synchronized 同步方法，锁的是this*/
    private synchronized void buy() {
        if (ticketNums <= 0) {
            flag = false;
            return;
        }
        try {
            // 模拟网络延迟和业务的相关操作，放大问题发生的可能性
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "买到了第" + ticketNums-- + "票");
    }
}