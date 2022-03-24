package com.mingqi.volatiles;

/**
 * @author limingqi
 * @version 1.0
 * @description volatile虽然增加了实例变量在多个
 * 线程之间的可见性，但它不具备同步性，那么也就不具备原子性
 * @date 2022年03月24日15:02
 */
public class volatileTestThread extends Thread {
    volatile public static int count;
   synchronized private static void addCount(){
        for(int i=0;i<100;i++){
            count++;
        }
        System.out.println("count="+count);
    }
    public void run(){
        addCount();
    }

}
