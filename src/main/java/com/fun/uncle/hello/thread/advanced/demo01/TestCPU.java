package com.fun.uncle.hello.thread.advanced.demo01;

/**
 * @Description: 获取CPU的核心数目
 * @Author: Xian
 * @CreateDate: 2020/2/19  20:42
 * @Version: 0.0.1-SNAPSHOT
 */
public class TestCPU {

    public static void main(String[] args) {
        // 获取CPU的核心数
        System.out.println("【CPU】" + Runtime.getRuntime().availableProcessors());
    }
}
