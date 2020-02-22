package com.fun.uncle.hello.thread.advanced.function;

import java.util.function.Consumer;

/**
 * @Description: Consumer(消费者接口),只有输入值,没有返回值。使用了接口accept
 * @Author: Xian
 * @CreateDate: 2020/2/22  16:59
 * @Version: 0.0.1-SNAPSHOT
 */
public class TestConsumer {
    public static void main(String[] args) {
        Consumer<String> consumer = (str)->{
            System.out.println(str);
        };



        consumer.accept("你好! world");
    }
}
