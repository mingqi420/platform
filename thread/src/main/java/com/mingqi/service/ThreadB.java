package com.mingqi.service;

/**
 * @author limingqi
 * @version 1.0
 * @description TODO
 * @date 2022年03月22日16:35
 */
public class ThreadB extends Thread{
    private threadService service;
    public ThreadB(threadService service){
        this.service=service;
    }
    @Override
    public void run() {
        try {
           // service.print(new Object());
            service.methodB();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
