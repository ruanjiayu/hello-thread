package com.fun.uncle.hello.thread.syn;

/**
 * @Description: 不安全的取钱。
 * 两个人同时去银行取钱，账户
 * @Author: Xian
 * @CreateDate: 2020/2/16  13:11
 * @Version: 0.0.1-SNAPSHOT
 */
public class UnsafeBank {

    public static void main(String[] args) {
        Account account = new Account("老婆本", 100);
        Drawing you = new Drawing(account, 50);
        Drawing girlFriend = new Drawing(account, 80);

        new Thread(you, "xian").start();
        new Thread(girlFriend, "fan").start();

    }

}

/**
 * 账户
 */
class Account{
    private String name;
    private int money;

    public Account(String name, int money) {
        this.name = name;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}

class Drawing implements Runnable{
    /**
     * 账户
     */
    private Account account;

    /**
     * 取了多少钱
     */
    private int drawingMoney;

    /**
     * 现在手上有多少钱
     */
    private int nonMoney;

    public Drawing(Account account, int drawingMoney) {
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    @Override
    public void run() {
        synchronized (Account.class) {
            // 判断卡里面余额是否足够
            if (account.getMoney() - drawingMoney <= 0) {
                System.out.println(Thread.currentThread().getName() + "账户余额不足，不能取钱");
                return;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 卡内余额 = 余额 - 你去的钱
            account.setMoney(account.getMoney() - drawingMoney);
            // 手上的钱
            nonMoney = nonMoney + drawingMoney;
            System.out.println(account.getName() + "账户余额为" + account.getMoney());
            System.out.println(Thread.currentThread().getName() + "拿到手上有" + nonMoney);

        }

    }
}
