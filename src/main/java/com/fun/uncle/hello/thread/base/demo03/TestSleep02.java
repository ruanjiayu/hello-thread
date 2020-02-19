package com.fun.uncle.hello.thread.base.demo03;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Description: 模拟倒计时。记住每个对象都有一个锁，sleep不会释放锁。
 * @Author: Xian
 * @CreateDate: 2020/2/16  10:13
 * @Version: 0.0.1-SNAPSHOT
 */
public class TestSleep02  {

    public static void main(String[] args) {
        // 获取当前时间
        LocalDateTime localDateTime = LocalDateTime.now();
        while (true) {
            try {
                Thread.sleep(1000);
                System.out.println(localDateTime.format(DateTimeFormatter.ISO_DATE_TIME));
                localDateTime = LocalDateTime.now();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 模拟倒计时
    public static void tenDown() {
        int num = 10;
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(num--);
            if(num <= 0) {
                break;
            }
        }
    }
}
