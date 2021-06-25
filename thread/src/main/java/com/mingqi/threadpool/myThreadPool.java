package com.mingqi.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author limingqi
 * @version 1.0
 * @description TODO 四大线程池
 * @date 2021年06月21日下午8:41
 */
public class myThreadPool {
    public static void main(String[] args) {
       // myThreadPool.newCachedThreadPooltest();
        myThreadPool.newFixedThreadPooltest();
    }
    /**
     * 创建一个可根据需要创建新线程的线程池，但是在以前构造的线程可用时候将重新使用他们，对于很多短期异步的任务的程序
     * 而言，这些线程池通常可以提高程序性能。调用execute将重用以前构造的线程（如果线程可用）。如果现有线程没有可用的，
     * 则撞见一个新线程并添加到线程池中，终止并从缓存中移除那些已有60秒未被使用的线程。因此，长时间保持空闲的线程池不会
     * 使用任何资源
     * 1)工作线程的创建数量几乎没有限制(其实也有限制的,数目为Interger. MAX_VALUE)
     * 2)空闲的工作线程会自动销毁，有新任务会重新创建
     * 在使用CachedThreadPool时，一定要注意控制任务的数量，否则，由于大量线程同时运行，很有会造成系统瘫痪。
     */
    public static void newCachedThreadPooltest(){
        ExecutorService threadPool= Executors.newCachedThreadPool();
        while (true){
            threadPool.execute(new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"正在运行。。。。。");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    }
    /**创建一个可重用固定线程数的线程池，以共享的无界队列方式来运行这些线程，在任一点，在大多数nThreads线程
     * 会处于处理任务的活动状态，如果在所有线程处于活动状态时候提交附加任务，则在有可用线程之前，附加任务将在
     * 队列中等待。如果在关闭前的执行期间由于失败而导致任一线程终止，那么一个新线程将代替他执行后续任务（如果需要）
     * 在某个线程被显式的被关闭前，线程池中将一直存在
     *
     * 该方法创建一个指定工作线程数量的线程池。每当提交一个任务就创建一个工作线程，如果工作线程数量达到线程池初始的最大数，则将提交的任务存入到池队列中。
     * 优点：具有线程池提高程序效率和节省创建线程时所耗的开销。
     * 缺点：在线程池空闲时，即线程池中没有可运行任务时，它不会释放工作线程，还会占用一定的系统资源
     */
    public static void newFixedThreadPooltest(){
        ExecutorService threadPool=Executors.newFixedThreadPool(10);
        while (true){
            threadPool.execute(new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"正在运行。。。。。");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    }

    /**
     * 创建一个线程池，它可安排在给定延迟后运行命令或者定期地执行。
     */
    public static void newScheduledThreadPooltest(){
        ScheduledExecutorService scheduledThreadPool= Executors.newScheduledThreadPool(3);
        scheduledThreadPool.schedule(new Runnable(){
            public void run() {
                System.out.println("延迟三秒");
            }
        }, 3, TimeUnit.SECONDS);
        scheduledThreadPool.scheduleAtFixedRate(new Runnable(){
            public void run() {
                System.out.println("延迟 1 秒后每三秒执行一次"); }
        },1,3, TimeUnit.SECONDS);
    }

    /**
     * 该方法创建一个单线程化的Executor，即只创建唯一的工作者线程来执行任务，它只会用唯一的工作线程来执行任务，
     * 保证所有任务按照指定顺序(FIFO, LIFO,优先级)执行。如果这个线程异常结束，会有另一个取代它，保证顺序执行。
     * 单工作线程最大的特点是可保证顺序地执行各个任务，并且在任意给定的时间不会有多个线程是活动的。
     * 这个线程
     * 池可以在线程死后（或发生异常时）重新启动一个线程来替代原来的线程继续执行下去！
     */
   public static void newSingleThreadExecutortest(){
       ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
       for (int i = 0; i < 10; i++) {
           final int index = i;
           singleThreadExecutor.execute(new Runnable() {
               public void run() {
                   try {
                       System.out.println(index);
                       Thread.sleep(2000);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
           });
       }
   }

}
