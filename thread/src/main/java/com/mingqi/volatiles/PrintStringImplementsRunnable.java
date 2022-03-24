package com.mingqi.volatiles;

/**
 * @author limingqi
 * @version 1.0
 * @description TODO
 * @date 2022年03月24日14:18
 */
public class PrintStringImplementsRunnable implements Runnable{
    private boolean isContinuePrint=true;

    public boolean isContinuePrint() {
        return isContinuePrint;
    }

    public void setContinuePrint(boolean continuePrint) {
        isContinuePrint = continuePrint;
    }
    public void printString() throws InterruptedException {
        while (isContinuePrint){
            System.out.println("run printString method ThreadName="+Thread.currentThread().getName());
            Thread.sleep(1000);
        }
    }
    @Override
    public void run() {
        try {
            printString();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
