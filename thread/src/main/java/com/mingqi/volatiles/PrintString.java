package com.mingqi.volatiles;

/**
 * @author limingqi
 * @version 1.0
 * @description volatile关键字
 * @date 2022年03月24日14:08
 */
public class PrintString {
    private boolean isContinuePrint=true;

    public boolean isContinuePrint() {
        return isContinuePrint;
    }

    public void setContinuePrint(boolean continuePrint) {
        isContinuePrint = continuePrint;
    }
    public void printString() throws InterruptedException {
        while (isContinuePrint){
            System.out.println("run printString method threadName="+Thread.currentThread().getName());
            Thread.sleep(1000);
        }
    }
}
