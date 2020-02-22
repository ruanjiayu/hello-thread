package com.fun.uncle.hello.thread.advanced.stream;


import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Description: 测试一下流式表达式
 * 使用一行代码来实现
 * 现在有5个用户!刷选
 * 1. ID 必须是偶数
 * 2. 年龄必须大于23岁
 * 3. 用户名转为大写字母
 * 4. 用户名字母倒着排序
 * 5. 只输出1个用户
 * @Author: Xian
 * @CreateDate: 2020/2/22  17:15
 * @Version: 0.0.1-SNAPSHOT
 */
public class TestStream {
    public static void main(String[] args) {
        User a = new User(1, "a", 21);
        User b = new User(2, "b", 22);
        User c = new User(3, "c", 23);
        User d = new User(4, "d", 24);
        User e = new User(5, "e", 25);
        User f = new User(6, "f", 26);

        List<User> list = Arrays.asList(a, b, c, d, e, f);


        list.stream()
                .filter((u) -> u.getId() % 2 == 0)
                .filter((u) -> u.getAge() > 23)
                .map((user)->{ user.setName(user.getName().toUpperCase()); return user; })
//                .sorted(Comparator.comparing(User::getName))
                .sorted((u1, u2)-> u2.getName().compareTo(u1.getName()))
                .limit(1)
                .forEach(System.out::println);


    }
}
