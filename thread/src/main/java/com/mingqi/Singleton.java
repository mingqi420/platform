package com.mingqi;

/**
 * @author limingqi
 * @version 1.0
 * @description TODO 双重校验的单例
 * @date 2021年06月13日下午2:58
 */
public class Singleton {
    private volatile static Singleton instance = null;
    private Singleton() {

    }
   public static Singleton getInstance() {
       if (instance == null) {
           synchronized (Singleton.class) {
               if (instance == null)
                   instance = new Singleton();
           }
       }
       return instance;
   }
}
