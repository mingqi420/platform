package com.mingqi.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author limingqi
 * @version 1.0
 * @description TODO
 * @date 2021年06月13日上午11:02
 */
public class AtomicDemo {
    static AtomicInteger n;

    public static void main(String[] args) throws InterruptedException {
        int j=0;
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
