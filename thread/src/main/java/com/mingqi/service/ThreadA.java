package com.mingqi.service;

/**
 * @author limingqi
 * @version 1.0
 * @description TODO
 * @date 2022年03月22日16:33
 */
public class ThreadA extends Thread{
    private threadService service;
    public ThreadA(threadService service){
       this.service=service;
   }
    @Override
    public void run() {
        try {
           // service.printA();
           // service.print(new Object());
            service.methodA();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
