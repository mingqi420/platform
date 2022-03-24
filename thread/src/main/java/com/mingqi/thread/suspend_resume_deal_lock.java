package com.mingqi.thread;

/**
 * @author limingqi
 * @version 1.0
 * @description TODO
 * @date 2022年03月18日14:33
 */
class SynchronizedObject{
    synchronized public void printString(){
        System.out.println("begin");
        if(Thread.currentThread().getName().equals("a")){
            System.out.println("a线程永远 suspend了");
            Thread.currentThread().suspend();
        }
        System.out.println("end");
    }
}
public class suspend_resume_deal_lock {
    public static void main(String[] args) throws InterruptedException {
        final SynchronizedObject object=new SynchronizedObject();
        Thread thread1=new Thread(){
            public void run(){
                object.printString();
            }
        };
        thread1.setName("a");
        thread1.start();
        Thread.sleep(1000);
        Thread thread2=new Thread(){
            public void run(){
                System.out.println("线程b——————————begin");
                System.out.println("b线程启动了，但是进入不了printString方法");
                System.out.println("因为printString方法被a线程锁定并永远suspend(挂起)了");
                object.printString();
                System.out.println("线程b——————————end");
            }
        };
        thread2.setName("b");
        thread2.start();
    }
}
