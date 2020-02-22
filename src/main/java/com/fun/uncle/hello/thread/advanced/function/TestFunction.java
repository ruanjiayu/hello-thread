package com.fun.uncle.hello.thread.advanced.function;

import java.util.function.Function;

/**
 * @Description: Function 功能型接口
 * @Author: Xian
 * @CreateDate: 2020/2/22  16:42
 * @Version: 0.0.1-SNAPSHOT
 */
public class TestFunction {
    public static void main(String[] args) {
        Function<String, Integer> function = (o) -> {
            return Integer.valueOf(o);
        };
        System.out.println(function.apply("123456"));

    }
}
