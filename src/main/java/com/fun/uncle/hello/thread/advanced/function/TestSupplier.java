package com.fun.uncle.hello.thread.advanced.function;

import java.util.function.Supplier;

/**
 * @Description: 供给型接口。没有参数，只有返回值。使用了get
 * @Author: Xian
 * @CreateDate: 2020/2/22  17:05
 * @Version: 0.0.1-SNAPSHOT
 */
public class TestSupplier {
    public static void main(String[] args) {
//        Supplier<String> supplier = new Supplier<String>() {
//            @Override
//            public String get() {
//                return "我是生产者";
//            }
//        };
        Supplier<String> supplier = ()->{return "我是生产者";};

        System.out.println(supplier.get());


    }
}
