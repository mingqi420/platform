package com.mingqi.thread;

/**
 * @author limingqi
 * @version 1.0
 * @description TODO
 * @date 2022年03月09日15:51
 */
public class ThreadNosharedata extends Thread{
    private int count=5;
    public ThreadNosharedata(String name){
        super();
        this.setName(name);
    }
    public void run(){
        super.run();
        while (count>0){
            count--;
            System.out.println("由线程"+Thread.currentThread().getName()
            +"计算count的值为："+count);
        }
    }

    public static void main(String[] args) {
        ThreadNosharedata A = new ThreadNosharedata("A");
        ThreadNosharedata B = new ThreadNosharedata("B");
        ThreadNosharedata C = new ThreadNosharedata("C");
        A.start();
        B.start();
        C.start();

    }
}
