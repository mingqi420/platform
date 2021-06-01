package com.mingqi.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author lidefu
 * @date 2021年06月01日 20:51
 * CountDownLatch这个类能够使一个线程等待其他线程完成各自的工作后再执行。
 * CountDownLatch是通过一个计数器来实现的，计数器的初始值为线程的数量。
 * 每当一个线程完成了自己的任务后，计数器的值就会减1。当计数器值到达0时，它表示所有的线程已经完成了任务，然后在闭锁上等待的线程就可以恢复执行任务。
 *
 */
public class CountDown {
    private Integer i=0;
    private CountDownLatch countDownLatch=new CountDownLatch(1);

    private void odd(){
        while(i<100){
            if(i%2==1){
                System.out.println("奇数"+i);
                i++;
                countDownLatch.countDown();
            }
            else{
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void even(){
        while(i<100){
            if(i%2==0){
                System.out.println("偶数"+i);
                i++;
                countDownLatch.countDown();
            }
            else{
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        final CountDown countDown=new CountDown();
        Thread thread1=new Thread(new Runnable() {
            public void run() {
                countDown.odd();
            }
        },"奇数线程");
        Thread thread2=new Thread(new Runnable() {
            public void run() {
                countDown.even();
            }
        },"偶数线程");
        thread1.start();
        thread2.start();
    }
}
