package com.mingqi.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lidefu
 * @date 2021年05月31日 10:15
 * 模拟多个客户端同事售卖电影票,线程不安全
 */
public class Ticket implements Runnable {
    private Integer ticketNum = 100;

    public void run() {
        while (true) {
            if (ticketNum > 0) {
                //模拟出票时间
                try {
                    Thread.sleep(1000);
                    // System.out.println("正在出票中，请稍后");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //打印进程号和票号
                String threadName = Thread.currentThread().getName();
                System.out.println("线程" + threadName + "售票：" + ticketNum--);
            }

        }

    }
    public static void main(String[] args) {
        Ticket ticket=new Ticket();
        Thread thread1=new Thread(ticket,"窗口1");
        Thread thread3=new Thread(ticket,"窗口3");
        Thread thread2=new Thread(ticket,"窗口2");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

    /**
     * 模拟多个窗口售卖电影票
     * 使用同步代码块达到线程安全
     */
class Ticketsafe implements Runnable{
        private Integer ticketNum=100;
        Object object=new Object();

        public void run() {
            while (true) {
                synchronized (object) {
                    if (ticketNum > 0) {
                        //模拟出票时间
                        try {
                            Thread.sleep(1000);
                            // System.out.println("正在出票中，请稍后");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //打印进程号和票号
                        String threadName = Thread.currentThread().getName();
                        System.out.println("线程" + threadName + "售票：" + ticketNum--);
                    }

                }
            }
    }

        public static void main(String[] args) {
            Ticketsafe ticketsafe=new Ticketsafe();
            //Ticket ticket=new Ticket();
            Thread thread1=new Thread(ticketsafe,"窗口1");
            Thread thread3=new Thread(ticketsafe,"窗口3");
            Thread thread2=new Thread(ticketsafe,"窗口2");
            thread1.start();
            thread2.start();
            thread3.start();
        }
    }

/**
 * 模拟多个窗口售票
 * 用同步方法实现线程安全
 */
class Ticketsafe1 implements Runnable{
        private Integer ticketNum=100;
        public void run() {
            sellTicket();
        }
        private synchronized void sellTicket(){
            while (true) {
                if (ticketNum > 0) {
                    //模拟出票时间
                    try {
                        Thread.sleep(1000);
                        // System.out.println("正在出票中，请稍后");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //打印进程号和票号
                    String threadName = Thread.currentThread().getName();
                    System.out.println("线程" + threadName + "售票：" + ticketNum--);
                }

            }
        }
    public static void main(String[] args) {
        Ticketsafe1 ticketsafe1=new Ticketsafe1();
        //Ticket ticket=new Ticket();
        Thread thread1=new Thread(ticketsafe1,"窗口1");
        Thread thread3=new Thread(ticketsafe1,"窗口3");
        Thread thread2=new Thread(ticketsafe1,"窗口2");
        thread1.start();
        thread2.start();
        thread3.start();
    }
    }

/**
 * 模拟多个窗口售票
 * 使用同步锁实现线程安全
 */
class Ticketsafe3 implements Runnable{
        private Integer ticketNum=100;
        //定义锁对象，构造函数参数为线程是否公平获取锁  true 公平    false 不公平 即有某个线程独占，默认是false
        Lock lock=new ReentrantLock();
        public void run() {
            //枷锁
            lock.lock();
            while (true) {
                try {
                    if (ticketNum > 0) {
                        //模拟出票时间
                        try {
                            Thread.sleep(1000);
                            // System.out.println("正在出票中，请稍后");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //打印进程号和票号
                        String threadName = Thread.currentThread().getName();
                        System.out.println("线程" + threadName + "售票：" + ticketNum--);
                    }
                }
                finally {
                    //释放锁
                    lock.unlock();
                }


            }
        }
    }
       /* synchronized是java内置关键字，在jvm层面，Lock是个java类；
        synchronized无法判断是否获取锁的状态，Lock可以判断是否获取到锁；
        synchronized会自动释放锁(a 线程执行完同步代码会释放锁 ；b 线程执行过程中发生异常会释放锁)，Lock需在finally中手工释放锁（unlock()方法释放锁），否则容易造成线程死锁；
        用synchronized关键字的两个线程1和线程2，如果当前线程1获得锁，线程2线程等待。如果线程1阻塞，线程2则会一直等待下去，而Lock锁就不一定会等待下去，如果尝试获取不到锁，线程可以不用一直等待就结束了；*/
