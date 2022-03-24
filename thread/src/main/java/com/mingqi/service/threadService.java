package com.mingqi.service;

import javax.xml.ws.Service;
import java.sql.SQLOutput;

/**
 * @author limingqi
 * @version 1.0
 * @description 非synchronized this锁
 * @date 2022年03月22日16:09
 */
public class threadService {
    private  String username;
    private String  password;
    public void setUsernamePassword(String username,String password) throws InterruptedException {
        String string=new String();
        synchronized (string){
            System.out.println("线程名称是："+Thread.currentThread().getName()+"在"+
                    System.currentTimeMillis()+"进入同步块");
            this.username=username;
            Thread.sleep(3000);
            this.password=password;
            System.out.println("线程名称是："+Thread.currentThread().getName()+"在"+
                    System.currentTimeMillis()+"离开同步块");
        }

    }

    /**
     * 类锁
     * 类锁可以对类中的所有对象实例起作用
     * @throws InterruptedException
     */
    synchronized public static void printA() throws InterruptedException {
        System.out.println("begin-----synchronized修饰静态方法printA，线程名称："+Thread.currentThread().getName());
        Thread.sleep(5000);
        System.out.println("end-----synchronized修饰静态方法printA,线程名称："+Thread.currentThread().getName());
    }
    synchronized public static void printB() throws InterruptedException {
        System.out.println("begin-----synchronized修饰静态方法printB，线程名称："+Thread.currentThread().getName());
        Thread.sleep(5000);
        System.out.println("end-----synchronized修饰静态方法printB,线程名称："+Thread.currentThread().getName());
    }

    /**
     * 对象锁
     * @throws InterruptedException
     */
    synchronized public static void printC() throws InterruptedException {
        System.out.println("begin-----synchronized修饰静态方法printC，线程名称："+Thread.currentThread().getName());
        Thread.sleep(5000);
        System.out.println("end-----synchronized修饰静态方法printC,线程名称："+Thread.currentThread().getName());
    }
    public static void print(Object s) throws InterruptedException {
        synchronized (s){
            while (true) {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(1000);
            }
        }
    }
    /**
     * 无限等待与解决
     */
    Object object1=new Object();
    public void methodA() throws InterruptedException {
      synchronized (object1) {
          System.out.println("methodA begin");
          boolean isContinueRun = true;
          Thread.sleep(1);
          while (isContinueRun) {

          }
          System.out.println("methodA end");
      }
    }
    Object object2=new Object();
    public void methodB() throws InterruptedException {
        synchronized (object2) {
            System.out.println("methodB begin");
            System.out.println("methodB end");
            Thread.sleep(1);
        }
    }
}

