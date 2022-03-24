package com.mingqi.volatiles;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author limingqi
 * @version 1.0
 * @description 原子类示例
 * @date 2022年03月24日15:20
 */
public class AddCountThread extends Thread{
  private AtomicInteger count=new AtomicInteger();
  public void run(){
      for(int i=0;i<10000;i++){
          System.out.println(count.incrementAndGet());
          System.out.println(Thread.currentThread().getName());
      }
  }
}
