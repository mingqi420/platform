package com.mingqi.thread;

/**
 * 银行类
 */
public class Bank {
    /**
     * 1、在ThreadLocal类中定义了一个ThreadLocalMap
     * 2、每一个Thread都有一个ThreadLocalMap类型的变量threadLocals
     * 3、threadLocals内部有一个Entry，Entry的key是ThreadLocal对象实例、value就是共享变量的副本
     * 4、ThreadLocal的get方法就是根据ThreadLocal对象实例获取共享变量副本
     * 5、ThreadLocal的set方法就是ThreadLocal对象的实例保存共享变量副本。
     */
    ThreadLocal<Integer> t = new ThreadLocal<Integer>() {
        protected Integer initialValue() {
            return 0;
        }
    };

    /**
     * ThreadLocal 提供县城局部变量，即为使用相同变量的每个线程 维护一个该变量的副本，当某些数据
     * 是以线程为为作用域并且不同线程具有不同的数据副本的时候，就可以考虑采用ThreadLocal，比如
     * 数据库连接Connection，每个请求处理线程都需需要，但又不相互影响，就是使用ThreadLocal 实现的
     * @return
     */
    public Integer get() {
        return t.get();
    }

    public void set() {
        t.set(t.get() + 10);
    }

    public static void main(String[] args) {
        Bank bank = new Bank();
        Transfer transfer = new Transfer(bank);
        Thread t1 = new Thread(transfer,"李明奇");
        Thread t2 = new Thread(transfer,"李德付");
        t1.start();
        t2.start();
    }
}

/**
 * 转账类
 */
class Transfer implements Runnable{
     Bank bank;
     public  Transfer(Bank bank){
         this.bank=bank;
     }
     public void run(){
         for(int i=0;i<10;i++){
             bank.set();
             System.out.println(Thread.currentThread().getName()+""+bank.get());
         }
     }
    }

