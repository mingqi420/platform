package com.mingqi.thread;

/**
 * @author limingqi
 * @version 1.0
 * @description suspend和resume方法的使用
 * @date 2022年03月18日14:07
 */
public class suspend_resume extends Thread{
    private long i=0;

    public long getI() {
        return i;
    }

    public void setI(long i) {
        this.i = i;
    }
    public void run(){
        while (true){
            i++;
        }
    }

    public static void main(String[] args) {
        try{
            suspend_resume thread=new suspend_resume();
            thread.start();
            Thread.sleep(5000);
            /**
             * 线程的确被暂停了，而且还会恢复成运行状态
             */
            // A段
            thread.suspend();
            System.out.println("A="+System.currentTimeMillis()+"，i="+thread.getI());
            Thread.sleep(5000);
            System.out.println("A="+System.currentTimeMillis()+"，i="+thread.getI());
            //B段
            Thread.sleep(5000);
            //C段
            thread.suspend();
            System.out.println("B="+System.currentTimeMillis()+"，i="+thread.getI());
            Thread.sleep(5000);
            System.out.println("A="+System.currentTimeMillis()+"，i="+thread.getI());


        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
