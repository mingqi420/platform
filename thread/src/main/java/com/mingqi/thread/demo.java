package com.mingqi.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class demo {
    public static void main(String[] args) {
        //继承了Thread类创建线程
        Mythread mythread=new Mythread();
        mythread.start();
        //实现Runnable接口创建线程
        //启动MyThread2 需要首先实例化一个Thread，并传入自己的MyThread2实例
        MyThread2 myThread2=new MyThread2();
        Thread thread=new Thread(myThread2,"myThread2");
        thread.start();
        // 线程池创建线程
        //有返回值得任务必须实现Callable接口，类似的 无返回值的任务必须实现Runnable接口
        //执行Callable任务后，可以获取一个Future接口，在该对象上调用get就可以获取到Callable
        //任务返回的Object对象了，再结合线程池的ExecutorService就可以实现传说中有返回结果的多线程了
        //创建一个线程池
        ExecutorService pool= Executors.newFixedThreadPool(10);
        //创建多个有返回值的任务
        List<Future> list=new ArrayList<Future>();
        for(int i=0;i<10;i++){
            Callable callable=new MyCallable(i+"");
            Future f=pool.submit(callable);
            list.add(f);
        }
        pool.shutdown();
        for (Future f:list){
            //从Future 对象上获取任务的返回值并输出到控制台
            try {
                System.out.println("返回值是"+f.get().toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        //创建多个无返回值任务的线程
        ExecutorService pool1= Executors.newFixedThreadPool(10);
        while(true){
            pool1.execute(new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"正在运行");
                    try{
                        Thread.sleep(3000);
                    }
                    catch (InterruptedException e){
                        System.out.println(e.getMessage());
                    }
                }
            });
        }

    }
}
