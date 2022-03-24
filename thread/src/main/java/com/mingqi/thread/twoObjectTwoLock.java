package com.mingqi.thread;

/**
 * @author limingqi
 * @version 1.0
 * @description 两个线程分别访问同一个类的两个不同实例的相同名称的同步方法
 * @date 2022年03月21日14:55
 */
public class twoObjectTwoLock {
    public static void main(String[] args) {


    HasSefPrivateNum num1=new HasSefPrivateNum();
    HasSefPrivateNum num2=new HasSefPrivateNum();
    ThreadA threadA=new ThreadA(num1);
    threadA.start();
    ThreadB threadB=new ThreadB(num2);
    threadB.start();
    }
}
class ThreadA extends Thread{
    private HasSefPrivateNum hasSefPrivateNum;
    public ThreadA(HasSefPrivateNum num){
        this.hasSefPrivateNum=num;
    }
    public void run(){
        try {
            hasSefPrivateNum.addI("a");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class ThreadB extends Thread{
    private HasSefPrivateNum hasSefPrivateNum;
    public ThreadB(HasSefPrivateNum num){
        this.hasSefPrivateNum=num;
    }
    public void run(){
        try {
            hasSefPrivateNum.addI("b");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class HasSefPrivateNum{
    private int num=0;

    /**
     * 同步方法addI，此方法会被顺序调用
     * @param username
     * @throws InterruptedException
     */
    synchronized public void addI(String username) throws InterruptedException {
        if(username.equals("a")){
            num=100;
            System.out.println("a set over");
            Thread.sleep(2000);
        }
        else{
            num=200;
            System.out.println("b set over!");
        }
        System.out.println("UserName:"+username+",Num:"+num);
    }
}
class MyObject{
    synchronized public void methodA() throws InterruptedException {
        System.out.println("begin methodA threadName="+Thread.currentThread().getName());
        Thread.sleep(5000);
        System.out.println("A end entTime="+System.currentTimeMillis());
    }
    public void methodB() throws InterruptedException {
        System.out.println("begin methodB threadName="+Thread.currentThread().getName());
        Thread.sleep(5000);
        System.out.println("B end entTime="+System.currentTimeMillis());}
}
class ThreadC extends Thread{
    private MyObject object;
    public ThreadC(MyObject object){
        this.object=object;
    }
    public void run(){
        try {
            object.methodA();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class ThreadD extends Thread{
    private MyObject object;
    public ThreadD(MyObject object){
        this.object=object;
    }
    public void run(){
        try {
            object.methodB();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        MyObject object=new MyObject();
        ThreadC c = new ThreadC(object);
        c.setName("A");
        ThreadD d = new ThreadD(object);
        d.setName("B");
        c.start();
        d.start();
    }
}
class PublicVar{
    public String userName="A";
    public String password="AA";
    synchronized public void setValue(String userName,String pwd) throws InterruptedException {
        this.password=pwd;
        Thread.sleep(5000);
        this.userName=userName;
        System.out.println("setValue method thread name="+Thread.currentThread().getName()
        +" username="+userName+" password="+password);
    }
    public void getValue(){
        System.out.println("getValue thread name="+Thread.currentThread().getName()
        +" username="+userName+" password="+password);
    }
}
class ThreadE extends Thread{
    private PublicVar publicVar;
    public ThreadE(PublicVar publicVar){
        this.publicVar=publicVar;
    }
    public void run(){
        try {
            publicVar.setValue("E","EE");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 出现脏读的情况
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        PublicVar publicVar=new PublicVar();
        ThreadE e = new ThreadE(publicVar);
        e.start();
        Thread.sleep(2000);
        publicVar.getValue();
    }
}
/**
 * 重入锁 当一个线程得到一个对象锁后，再次请求此对象锁时是可以再次得到该对象的
 * 锁的，这也证明在一个synchronized方法/块的内部调用本类的其他synchronized
 * 方法/块时，是永远可以得到锁的
 */
class Service{
    synchronized public void service1(){
        System.out.println("service1");
        service2();
    }
    synchronized public void service2(){
        System.out.println("service2");
        service3();
    }
    synchronized public void service3(){
        System.out.println("service3");
    }
}
class ThreadF extends Thread{
    public void run(){
        Service service=new Service();
        service.service1();
    }
}
class Main{
    public int i=10;
    synchronized public void operateImainmethod(){
        try{
            i--;
            System.out.println("main print i="+i);
            Thread.sleep(1000);
            this.operateImainmethod();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
class ThreadG extends Thread{
    public void run(){
        Sub sub=new Sub();
        try {
            sub.operateISubmethod();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class Sub extends Main{
    synchronized public void operateISubmethod() throws InterruptedException {
        while (i>0){
            i--;
            System.out.println("sub print i="+i);
            Thread.sleep(100);
            this.operateImainmethod();
        }
    }

    /**
     *
     * 当存在父子类集成关系时，子类是完全可以通过可冲入锁调用父类的同步方法的
     * @param args
     */
    public static void main(String[] args) {
        ThreadG threadG=new ThreadG();
        threadG.start();
    }
}
/**
 * 出现异常，锁自动释放
 */
