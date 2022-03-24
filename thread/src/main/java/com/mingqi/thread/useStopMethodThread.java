package com.mingqi.thread;

/**
 * @author limingqi
 * @version 1.0
 * @description TODO
 * @date 2022年03月18日11:06
 */
public class useStopMethodThread extends Thread {
    private int i = 1;

    @Override
    public void run() {
        super.run();
        try {
            while (true) {
                i++;
                System.out.println("i=" + i);
                Thread.sleep(100000000);
            }
        } catch (InterruptedException e) {
            System.out.println("异常：" + e.getMessage());
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        try {
            useStopMethodThread thread=new useStopMethodThread();
            thread.start();
            Thread.sleep(5000);
            thread.stop();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}
