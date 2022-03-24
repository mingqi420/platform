package com.mingqi.thread;

/**
 * @author limingqi
 * @version 1.0
 * @description TODO
 * 在将任何数据类型作为同步锁时需要注意的是，是否有多个线程同时持有锁对象如果同时持有相同的锁对象，则这些线程之间就是同步的；
 * 如果分别获得锁对象，则这些线程之间就是异步的。
 * @date 2022年03月24日10:44
 */
public class setNewStringTwoLock {
    public static void main(String[] args) throws InterruptedException {
        Myservice myservice=new Myservice();
        ThreadOne threadOne=new ThreadOne(myservice);
        threadOne.setName("A");
        ThreadTwo threadTwo=new ThreadTwo(myservice);
        threadTwo.setName("B");
        threadOne.start();
        //Thread.sleep(50);// 50毫秒后 B线程获得的锁是456没如果注释掉该行，则为同步执行
        threadTwo.start();
    }
}
class Myservice{
    private String lock="123";
    public void testMethod() throws InterruptedException {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName()+"开始："+System.currentTimeMillis());
            lock="456";
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName()+"结束："+System.currentTimeMillis());
        }
    }
    public void serviceMethodA(userInfo userInfo) throws InterruptedException {
      synchronized (userInfo) {
          System.out.println(Thread.currentThread().getName()+"begin!");
          userInfo.setUsername("limingqi");
          Thread.sleep(3000);
          System.out.println(Thread.currentThread().getName()+"end!time=" + System.currentTimeMillis());
      }
    }
}
class ThreadOne extends Thread{
    private Myservice myservice;
    private userInfo userInfo;
    public ThreadOne(Myservice myservice){
        this.myservice=myservice;
    }
    public ThreadOne(Myservice myservice,userInfo userInfo){
        this.myservice=myservice;
        this.userInfo=userInfo;
    }
    public void run(){
        try {
            //myservice.testMethod();
            myservice.serviceMethodA(userInfo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class ThreadTwo extends Thread{
    private Myservice myservice;
    private userInfo userInfo;
    public ThreadTwo(Myservice myservice){
        this.myservice=myservice;
    }
    public ThreadTwo(Myservice myservice,userInfo userInfo){
        this.myservice=myservice;
        this.userInfo=userInfo;
    }
    public void run(){
        try {
           // myservice.testMethod();
            myservice.serviceMethodA(userInfo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class userInfo{
    private String username;
    private String password;

/*    public userInfo(String username,String password){
        this.password=password;
        this.username=username;
    }*/
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static void main(String[] args) throws InterruptedException {
        //只要对象不变，即使对象的属性被改变，运行结果也是同步的
        Myservice myservice=new Myservice();
        userInfo userInfo=new userInfo();
        ThreadOne threadOne=new ThreadOne(myservice,userInfo);
        threadOne.setName("threadOne");
        threadOne.start();
        Thread.sleep(50);
        ThreadOne threadTwo=new ThreadOne(myservice,userInfo);
        threadTwo.setName("threadTwo");
        threadTwo.start();
    }
}

