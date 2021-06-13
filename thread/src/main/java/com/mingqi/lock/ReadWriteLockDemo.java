package com.mingqi.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author limingqi
 * @version 1.0
 * @description TODO
 * @date 2021年06月13日下午2:05
 */
public class ReadWriteLockDemo {
    private Map<String,Object>map= new HashMap<String, Object>();
    //创建一个读写锁实例
    private ReadWriteLock readWriteLock=new ReentrantReadWriteLock();
    //创建一个读锁
    private Lock r=readWriteLock.readLock();
    //创建一个写锁
    private Lock w=readWriteLock.writeLock();

    /**
     * 读操作
     * @param key
     * @return
     */
    private Object get(String key){
        r.lock();
        System.out.println(Thread.currentThread().getName()+"读操作开始执行。。");
        try{
            Thread.sleep(3000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        try{
            return map.get(key);
        }
        finally {
            r.unlock();
            System.out.println(Thread.currentThread().getName() + "读操作执行完成......");
        }

    }

    /**
     * 写操作
     * @param key
     * @param value
     */
    public void put(String key,Object value){
        try {
            w.lock();
            System.out.println(Thread.currentThread().getName() + "写操作开始执行......");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
        } finally {
            w.unlock();
            System.out.println(Thread.currentThread().getName() + "写操作执行完成......");
        }
    }

    public static void main(String[] args) {
        final ReadWriteLockDemo d = new ReadWriteLockDemo();
        d.put("key1", "value1");
        new Thread(new Runnable() {
            public void run() {
                d.get("key1");
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                d.get("key1");
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                d.get("key1");
            }
        }).start();
    }
}
