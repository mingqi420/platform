package com.mingqi.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author limingqi
 * @version 1.0
 * @description TODO
 * @date 2021年06月13日上午11:02
 */
public class AtomicDemo {
    static AtomicInteger n;
    static AtomicStampedReference<Integer> n2;

    public static void main(String[] args) throws InterruptedException {
        int j=0;
        int j1=0;
        while (j<100){
            n=new AtomicInteger(0);
            Thread t1=new Thread(){
                public void run(){
                    for(int i=0;i<1000;i++){
                        n.getAndIncrement();
                    }
                }
            };
            Thread t2=new Thread(){
                public void run(){
                    for(int i=0;i<1000;i++){
                        /**
                         * CAS原理：使用unsafe.getAndAddInt(this, valueOffset, 1)
                         * 根据当前值和地址偏移量获取JAVA中堆中的逾期值，
                         * compareAndSwapInt 方法比较当前值v1和预期值v5是否相同
                         * 相同 则说明没有其他线程更改过该值，当前值v1=预期值v5+偏移量v4,返回true
                         * 不相同，则说明有线程修改过该值，当前值v1=预期值v5 返回false。
                         */
                        n.getAndIncrement();
                    }
                }
            };
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            System.out.println("n的最终值是："+n);
            j++;

        }
    }
}
