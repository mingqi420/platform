package com.mingqi.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author limingqi
 * @version 1.0
 * @description TODO 可重入锁
 * 线程可以进入它已经拥有的锁的同步代码块
 * @date 2021年06月13日下午2:02
 */
public class ReentrantLockDemo {
    public static void main(String[] args) {
        ReentrantLock lock=new ReentrantLock();
        for(int i=1;i<=3;i++){
            lock.lock();
        }
        for(int i=1;i<=3;i++){
            try {

            } finally {
                lock.unlock();
            }
        }
    }
}
