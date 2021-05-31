package com.mingqi.thread;

/**
 * Thread 类本质上是实现了Runnable 接口的一个实例，代表一个线程的实例。启动
 * 线程的唯一方法是通过Thread类的start实例方法，start方法是一个native方法
 * 他将启动一个新线程并执行run方法。
 */
public class Mythread extends Thread {
    public void run(){
        System.out.println("继承Thread类run方法运行中");
        System.out.println(Thread.currentThread().getName());
    }
}

