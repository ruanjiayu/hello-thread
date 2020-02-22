package com.fun.uncle.hello.thread.advanced.function;

import java.util.function.Predicate;

/**
 * @Description: predicate(断定形接口) 使用了test方法,返回true和false
 * @Author: Xian
 * @CreateDate: 2020/2/22  16:51
 * @Version: 0.0.1-SNAPSHOT
 */
public class TestPredicate {
    public static void main(String[] args) {
        Predicate<String> predicate = (o) -> {
            if (o.equals("hello")) {
                return true;
            }
            return false;
        };

        System.out.println(predicate.test("111"));
    }
}
