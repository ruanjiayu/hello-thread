package com.fun.uncle.hello.thread.advanced.rw;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description: 测试读写锁 (独占锁 写锁) (共享锁 读锁)
 *  读 - 读   能共存
 *  读 - 写  不能共存
 *  写 - 写 不能共存
 * @Author: Xian
 * @CreateDate: 2020/2/21  9:52
 * @Version: 0.0.1-SNAPSHOT
 */
public class TestReadWriteLock {
    public static void main(String[] args) {
//        MyCache myCache = new MyCache();
        MyCacheLock myCache = new MyCacheLock();

        for (int i = 1; i < 6; i++) {
            final int temp = i;
            new Thread(() -> {
                myCache.put(String.valueOf(temp), temp);
            }, String.valueOf(temp)).start();
        }

        for (int i = 1; i < 6; i++) {
            final int temp = i;
            new Thread(() -> {
                myCache.get(String.valueOf(temp));
            }, String.valueOf(temp)).start();
        }

    }

}

/**
 * 自己定义缓存
 */
class MyCache {

    private volatile Map<String, Object> map = new HashMap<>();

    /**
     * 存
     *
     * @param key
     * @param value
     */
    public void put(String key, Object value) {
        System.out.println(Thread.currentThread().getName() + "写入============" + key);
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + "写入完成");
    }

    public void get(String key) {
        System.out.println(Thread.currentThread().getName() + "读取--------" + key);
        map.get(key);
        System.out.println(Thread.currentThread().getName() + "读完成");
    }
}

class MyCacheLock {

    private volatile Map<String, Object> map = new HashMap<>();

    private ReadWriteLock lock = new ReentrantReadWriteLock();
    /**
     * 存
     * 写入的时候，只能一个线程操作
     * @param key
     * @param value
     */
    public void put(String key, Object value) {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "写入=============" + key);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * 读取的时候可以多个线程操作
     * @param key
     */
    public void get(String key) {
        lock.readLock().lock();

        try {
            System.out.println(Thread.currentThread().getName() + "读取-------------");
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName() + "读完成" + o);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }
}