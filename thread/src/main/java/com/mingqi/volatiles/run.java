package com.mingqi.volatiles;

/**
 * @author limingqi
 * @version 1.0
 * @description TODO  程序开始后根本停不下来，主要原因是main Thread一直处理while循环导致程序不能继续执行后面的的代码
 * 解决方法是使用多线程。
 * @date 2022年03月24日14:11
 */
public class run {
    public static void main(String[] args) throws InterruptedException {

        PrintString printString=new PrintString();
        printString.printString();
        System.out.println("我要停止他：stopThreadName="+Thread.currentThread().getName());
        printString.setContinuePrint(false);
    }
}

/**
 * 实现了Runnable接口的多线程,但当以下代码运行在-server服务器模式
 * 中的64bit的JVM上时，会出现死循环，解决的办法是使用volatile关键字
 * 他的作用是强制从公共堆栈中取得变量的值，而不是从线程私有数据栈中取得变量的值
 */
class run2{
    public static void main(String[] args) throws InterruptedException {
      PrintStringImplementsRunnable printStringImplementsRunnable=new PrintStringImplementsRunnable();
      new Thread(printStringImplementsRunnable).start();
      Thread.sleep(2000);
        System.out.println("我要停止他，stopThread="+Thread.currentThread().getName());
        printStringImplementsRunnable.setContinuePrint(false);
    }
}
class run3 {
    public static void main(String[] args) throws InterruptedException {
        RunThread thread=new RunThread();
        thread.start();
        Thread.sleep(1000);
        thread.setRunning(false);
        System.out.println("已经被赋值为false");
    }
}
class run4{
    public static void main(String[] args) {
        volatileTestThread[] threads=new volatileTestThread[100];
        for(int i=0;i<100;i++){
            threads[i]=new volatileTestThread();
        } for(int i=0;i<100;i++){
            threads[i].start();
        }

    }
}

/**
 * 原子类在没有锁的情况下做到线程安全
 */
class run5{
    public static void main(String[] args) {
        AddCountThread[] threads=new AddCountThread[100];
        for(int i=0;i<100;i++){
            threads[i]=new AddCountThread();
            threads[i].setName("线程"+i);

        } for(int i=0;i<100;i++){
            threads[i].start();
        }

    }
}

/**
 * 原子性在有逻辑性的情况下输出结果也具有随机性
 */
class run6 {
    public static void main(String[] args) throws InterruptedException {
        actomicIntergerNoSafe service=new actomicIntergerNoSafe();
        actomicNosafeThread[] threads=new actomicNosafeThread[5];
        for(int i=0;i<threads.length;i++){
            threads[i]=new actomicNosafeThread(service);
        }
        for(int i=0;i<threads.length;i++){
            threads[i].start();
        }
        Thread.sleep(1000);
        System.out.println(service.atomicLong.get());
    }
}

/**
 * 以-server服务器模式运行此项目，出现死循环
 * 得到这个结果是个线程之间的数据没有可视性造成的
 */
class run7{
    public static void main(String[] args) throws InterruptedException {
        Service service =new Service();
        ThreadA a= new ThreadA(service);
        a.start();
        Thread.sleep(1000);
        ThreadB b = new ThreadB(service);
        b.start();
        System.out.println("已经发起停止命令");
    }


}
