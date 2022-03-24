package com.mingqi.volatiles;

/**
 * @author limingqi
 * @version 1.0
 * @description TODO
 * @date 2022年03月24日14:46
 */
public class RunThread extends Thread{
    volatile private boolean isRunning=true;

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
    public void run(){
        System.out.println("进入run方法了");
        while (isRunning){
            System.out.println("运行中running");
        }
        System.out.println("线程被终止，离开run方法");
    }
}
