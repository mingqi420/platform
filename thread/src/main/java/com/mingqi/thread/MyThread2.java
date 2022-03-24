package com.mingqi.thread;

public class MyThread2 extends Thread  {
    //int i=10000;
    public void run(){
       // System.out.println("实现了Runnable接口的run方法。");
       // super.run();
/*        for(Integer i=0;i<100000;i++) {
            if (this.isInterrupted()) {
                System.out.println("打印i值：" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }*/
       /* System.out.println("run begin");
        try {
            Thread.sleep(50000000);
        } catch (InterruptedException e) {
            System.out.println("在Sleep中被停止，进入Catch:"+this.isInterrupted());
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());*/

        int count=0;
        long beginTime=System.currentTimeMillis();

        for(int i=0;i<500000000;i++){
            //放弃当前的CPU资源，将它让给其他任务去占用CPU执行的时间
            Thread.yield();
            count=count+i;
        }
        long endTime=System.currentTimeMillis();
        System.out.println("计算用时:"+(endTime-beginTime)+"毫秒");

    }

    public static void main(String[] args) throws InterruptedException {
        MyThread2 myThread2=new MyThread2();
        Thread thread=new Thread(myThread2,"myThread2");
       //设置为守护进程
        //thread.setDaemon(true);
        thread.start();
        thread.setPriority(10);
       // Thread.sleep(5000);
        //调用interrupt方法 并不能停止线程。
       // thread.interrupt();
        //
      //  System.out.println("是否停止1："+Thread.interrupted())
    //、、System.out.println("是否停止2："+Thread.interrupted());

    }
}
