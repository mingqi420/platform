package com.mingqi.volatiles;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author limingqi
 * @version 1.0
 * @description 原子类在具有逻辑的情况下也具有随机性
 * 打印顺序出错了应该是每加一次100再加一一次，出现这种情况的原因是addAndGet（）方法是原子的
 * 但是方法和方法之间的调用却不是原子的，解决这样的问题必须要用同步（在方法前面增加synchronized）
 * @date 2022年03月24日15:29
 */
public class actomicIntergerNoSafe {
    public static AtomicLong atomicLong=new AtomicLong();
  synchronized   public void addNum(){
        System.out.println(Thread.currentThread().getName()+"加了100之后的值是="+atomicLong.addAndGet(100));
        atomicLong.addAndGet(1);
    }
}
class actomicNosafeThread extends Thread{
    private actomicIntergerNoSafe actomicIntergerNoSafe;
    public actomicNosafeThread(actomicIntergerNoSafe actomicIntergerNoSafe){
        this.actomicIntergerNoSafe=actomicIntergerNoSafe;
    }
    public void run(){
        actomicIntergerNoSafe.addNum();
    }
}
class Service{
    private boolean isContinueRun=true;
    public void runMethod(){
        while (isContinueRun){
            System.out.println("运行中----");
        }
        System.out.println("stoped！！！");
    }
    public void stopMethod(){
        isContinueRun=false;
    }
}
class ThreadA extends Thread{
    private Service service;

    public ThreadA(Service service) {
       // super(name);
        this.service = service;
    }
    public void run(){
        service.runMethod();
    }
}
class ThreadB extends Thread{
    private Service service;

    public ThreadB( Service service) {
       // super(target);
        this.service = service;
    }
    public void run(){
        service.stopMethod();
    }
}
