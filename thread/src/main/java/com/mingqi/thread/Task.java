package com.mingqi.thread;

/**
 * @author limingqi
 * @version 1.0
 * @description 模拟实行一些比较耗时的任务
 * @date 2022年03月21日16:59
 */
public class Task {
    private String getData1;
    private String getData2;
    public  void doLongTimeTask() throws InterruptedException {
        System.out.println("begin Task");
        Thread.sleep(3000);
        synchronized(this) {
            getData1 = "长时间任务从后台返回值1，ThreadName=" +
                    Thread.currentThread().getName();
            getData2 = "长时间任务从后台返回值2，ThreadName=" +
                    Thread.currentThread().getName();
        }
        System.out.println(getData1);
        System.out.println(getData2);
        System.out.println("end task");
    }
    public void printNum(){
        System.out.println("synchronized 方法快输出1-100 begin");
        synchronized (this){
            for(int i=0;i<100;i++){
                System.out.println("synchronized threadName="+Thread.currentThread().getName()+
                        " i="+(i+1));
            }
        }
        System.out.println("synchronized 方法快输出1-100 end");
        for(int i=0;i<100;i++){
            System.out.println("Nosynchronized threadName="+Thread.currentThread().getName()+
                    " i="+(i+1));
        }
    }
}
class CommonUtils{
    public static long beginTime1;
    public static long endTime1;
    public static long beginTime2;
    public static long endTime2;

}
class Mythread1 extends Thread{
    private Task task;
    public Mythread1(Task task)
    {
        this.task=task;
    }
    public void run(){
        CommonUtils.beginTime1=System.currentTimeMillis();
        try {
            task.doLongTimeTask();
            task.printNum();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CommonUtils.endTime1=System.currentTimeMillis();
    }
}
class MyThreadB extends Thread{
    private Task task;
    public MyThreadB(Task task)
    {
        this.task=task;
    }
    public void run(){
        CommonUtils.beginTime2=System.currentTimeMillis();
        try {
            task.doLongTimeTask();
            task.printNum();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CommonUtils.endTime2=System.currentTimeMillis();
    }
}
class Run{
    public static void main(String[] args) throws InterruptedException {
        Task task=new Task();
        Mythread1 mythread1=new Mythread1(task);
        mythread1.start();
        MyThreadB myThreadB=new MyThreadB(task);
        myThreadB.start();
        Thread.sleep(10000);
        long beginTime=CommonUtils.beginTime1;
        if(CommonUtils.beginTime2<CommonUtils.beginTime1){
            beginTime=CommonUtils.beginTime2;
        }
        long endTime=CommonUtils.endTime1;
        if(CommonUtils.endTime2>CommonUtils.endTime1){
            endTime=CommonUtils.endTime2;

        }
        System.out.println("耗时："+((endTime-beginTime)/1000)+"秒");
    }
}

/**
 *
 * 验证synchronized代码块时锁定当前对象
 */
class Task1{
  synchronized public void otherMethod(){
       System.out.println("--------------------------");
   }
   public void doLongTimeTask() throws InterruptedException {
       synchronized (this){
           for (int i=0;i<10000;i++){
               System.out.println("synchronized线程名称："+Thread.currentThread().getName()+
                       " i="+(i+1));
               Thread.sleep(10);
           }
       }
   }
}
class ThreadL extends Thread{
    private Task1 task1;
    public ThreadL(Task1 task1){
        this.task1=task1;
    }
    public void run(){
        try {
            task1.doLongTimeTask();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class ThreadM extends Thread{
    private Task1 task1;
    public ThreadM(Task1 task1){
        this.task1=task1;
    }
    public void run(){
        task1.otherMethod();
    }

    public static void main(String[] args) throws InterruptedException {
        Task1 task1=new Task1();
        ThreadL aa=new ThreadL(task1);
        aa.start();
        Thread.sleep(100);
        ThreadM tt=new ThreadM(task1);
        tt.start();
    }
}
/**
 * 锁非this对象：如果在一个类中由很多各synchronized方法，这时虽然能实现同步，但会收到阻塞
 * 所以影响运行效率；但是如果使用同步代码块锁非this对象，则synchronized（非this）代码中de
 * 程序与同步方法是异步的，不和其他锁this同步方法抢this锁，则可大大提高效率。
 */