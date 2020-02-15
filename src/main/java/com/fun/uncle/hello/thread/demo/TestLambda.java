package com.fun.uncle.hello.thread.demo;

/**
 * @Description:
 * @Author: Xian
 * @CreateDate: 2020/2/15  23:17
 * @Version: 0.0.1-SNAPSHOT
 */
public class TestLambda {

    public static void main(String[] args) {
        ILike i = () ->{
            System.out.println("我是lambda");
        };
        i.lambda();

    }
}



@FunctionalInterface
interface ILike{
    void lambda();
    default void haha(){
        System.out.println("haha");
    };
}
