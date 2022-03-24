package com.mingqi.thread;

/**
 * @author limingqi
 * @version 1.0
 * @description TODO
 * @date 2022年03月09日16:00
 */
public class ThreadShareData extends Thread{
   private int count=5;
  synchronized public void run(){
       super.run();
       // 不要用循环语句，因为使用同步后其他线程就得不到运算机会了，一直有一个新城进行减法运算
       count--;
       System.out.println("由线程"+Thread.currentThread().getName()
               +"计算count的值为："+count);
   }

    public static void main(String[] args) {
        ThreadShareData threadShareData=new ThreadShareData();
        Thread A = new Thread(threadShareData,"A");
        Thread B = new Thread(threadShareData,"B");
        Thread C = new Thread(threadShareData,"C");
        Thread D = new Thread(threadShareData,"D");
        Thread E = new Thread(threadShareData,"E");
        A.start();
        B.start();
        C.start();
        D.start();
        E.start();
    }
}
