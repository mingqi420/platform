package com.mingqi.thread;

import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author lidefu
 * @date 2021年06月01日 21:18
 * CyclicBarrier实现让一组线程等待至某个状态之后再全部同时执行。
 * CyclicBarrier底层是三个线程同时启动
 *
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        new Thread(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName()+"：准备...");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"启动完毕："+new Date().getTime());
            }
        },"线程1").start();
        new Thread(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName()+"：准备...");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"启动完毕："+new Date().getTime());
            }
        },"线程2").start();
        new Thread(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName()+"：准备...");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"启动完毕："+new Date().getTime());
            }
        },"线程3").start();
    }
}
