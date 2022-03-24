package com.mingqi.service;

/**
 * @author limingqi
 * @version 1.0
 * @description TODO
 * @date 2022年03月22日16:36
 */
public class ThreadC extends Thread{
    private threadService service;
    public ThreadC(threadService service){
        this.service=service;
    }
    @Override
    public void run() {
        try {
            service.printC();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
