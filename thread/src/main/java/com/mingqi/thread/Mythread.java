package com.mingqi.thread;

import java.beans.IntrospectionException;

/**
 * Thread 类本质上是实现了Runnable 接口的一个实例，代表一个线程的实例。启动
 * 线程的唯一方法是通过Thread类的start实例方法，start方法是一个native方法
 * 他将启动一个新线程并执行run方法。
 */
public class Mythread extends Thread {
    public void run(){
        System.out.println("继承Thread类run方法运行中");
        //System.out.println(Thread.currentThread().getName());
        try{
            for(int i=0;i<10;i++){
                int time=(int)Math.random()*1000;
                Thread.sleep(time);
                System.out.println("run="+Thread.currentThread().getName());
            }
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        Mythread mythread=new Mythread();
        mythread.setName("mythread");
        mythread.start();
        try{
            for(int i=0;i<10;i++){
                int time=(int)Math.random()*1000;
                Thread.sleep(time);
                System.out.println("run="+Thread.currentThread().getName());
            }
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("运行结束");
    }
}

