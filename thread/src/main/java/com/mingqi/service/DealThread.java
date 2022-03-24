package com.mingqi.service;

/**
 * @author limingqi
 * @version 1.0
 * @description 多线程的死锁
 * @date 2022年03月22日17:27
 */
public class DealThread implements Runnable {
    public String username;
    public Object lock1=new Object();
    public Object lock2=new Object();
    public void setFlag(String username){
        this.username=username;
    }
    @Override
    public void run() {
      if(username.equals("a")){
          synchronized (lock1){
              System.out.println("username="+username);
              try {
                  Thread.sleep(3000);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              synchronized (lock2){
                  System.out.println("按lock1->lock2 顺序执行了");
              }
          }
      }
      if(username.equals("b")){
          System.out.println("username="+username);
          try {
              Thread.sleep(3000);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
          synchronized (lock1){
              System.out.println("按lock2->lock1的顺序执行了");
          }
      }

    }

    public static void main(String[] args) throws InterruptedException {
        DealThread t1=new DealThread();
        t1.setFlag("a");
        Thread thread1=new Thread(t1);
        thread1.start();
        Thread.sleep(100);
        t1.setFlag("b");
        Thread thread2=new Thread(t1);
        thread2.start();
    }
}
