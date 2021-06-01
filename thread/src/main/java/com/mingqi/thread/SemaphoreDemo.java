package com.mingqi.thread;

import java.util.concurrent.Semaphore;

/**
 * @author lidefu
 * @date 2021年06月01日 21:03
 * Semaphore用于控制对某组资源的访问权限
 */
public class SemaphoreDemo {
    static class Machine implements Runnable{
        private int num;
        private Semaphore semaphore;
        public Machine(int num,Semaphore semaphore){
            this.num=num;
            this.semaphore=semaphore;
        }
        public void run(){
            try{
                semaphore.acquire();//请求机器
                System.out.println("工人"+this.num+"正在使用机器");
                Thread.sleep(1000);
                System.out.println("工人"+this.num+"使用完机器，已释放机器");
                semaphore.release();

            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int worker=8;
        Semaphore semaphore=new Semaphore(3);
        for(int i=0;i<worker;i++){
            new Thread(new Machine(i,semaphore)).start();
        }
    }
}
