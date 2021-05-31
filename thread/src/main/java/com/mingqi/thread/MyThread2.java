package com.mingqi.thread;

public class MyThread2 implements Runnable {
    public void run(){
        System.out.println("实现了Runnable接口的run方法。");
        System.out.println(Thread.currentThread().getName());
    }
}
