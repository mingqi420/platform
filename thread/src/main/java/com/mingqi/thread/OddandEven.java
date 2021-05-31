package com.mingqi.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lidefu
 * @date 2021年05月31日 10:55
 * 两个线程交替打印奇偶数
 */
public class OddandEven {
    private Object object=new Object();
    private Integer i=0;
    public void odd(){
        while (i<100) {
            synchronized (object) {
                if (i % 2 == 1) {
                    System.out.println( Thread.currentThread().getName()+"奇数" + i);
                    i++;
                    object.notify();
                } else {
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    public void Even(){
        while (i<100) {
            synchronized (object) {
                if (i % 2 == 0) {
                    System.out.println(Thread.currentThread().getName()+"打印偶数" + i);
                    i++;
                    object.notify();
                } else {
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        final OddandEven oddandEven=new OddandEven();

        Thread threadodd=new Thread(new Runnable() {
            public void run() {
                oddandEven.odd();
            }
        },"奇数线程");
        Thread threadEven=new Thread(new Runnable() {
            public void run() {
                oddandEven.Even();
            }
        },"偶数线程");
        threadodd.start();
        threadEven.start();
    }
}

/**
 * 两个线程交替打印奇偶数
 */
class OddandEvenCondition{

    private Lock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();
    private Integer i=0;
    public void odd(){
        while (i<100){
            lock.lock();
            try {
                if(i%2==1){
                    System.out.println("奇数："+i);
                    i++;
                    condition.signal();
                }
                else {
                    condition.wait();
                }
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            finally {
                lock.unlock();
            }
        }
    }
    public void Even(){
        while (i<100){
            lock.lock();
            try {
                if(i%2==0){
                    System.out.println("偶数："+i);
                    i++;
                    condition.signal();
                }
                else {
                    condition.wait();
                }
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            finally {
                lock.unlock();
            }
        }
    }
}
