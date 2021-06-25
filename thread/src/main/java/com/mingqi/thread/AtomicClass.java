package com.mingqi.thread;

/**
 * @author lidefu
 * @date 2021年06月07日 14:58
 */
public class AtomicClass {
    static int n=0;
    public static void main(String[] args) throws InterruptedException {
        int j=0;
        while (j<100){
            n=0;
            Thread t1=new Thread(){
                public void run(){
                    for(int i=0;i<1000;i++){
                        n++;
                       //
                    }
                    System.out.println(Thread.currentThread().getName()+"n的值是："+n);
                }

            };
            Thread t2=new Thread(){
                public void run(){
                    for(int i=0;i<1000;i++){
                        n++;
                        //System.out.println(Thread.currentThread().getName()+"n的值是："+n);
                    }
                    System.out.println(Thread.currentThread().getName()+"n的值是："+n);
                }
            };
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            System.out.println("n的最终值是："+n);
            j++;
        }
    }
}
