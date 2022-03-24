package com.mingqi.service;

/**
 * @author limingqi
 * @version 1.0
 * @description TODO
 * @date 2022年03月22日16:37
 */
public class APP {
    public static void main(String[] args) {
        threadService threadService=new threadService();
        //Thread

        ThreadA threadA=new ThreadA(threadService);
        threadA.setName("A");
        threadA.start();
        ThreadB threadB=new ThreadB(threadService);
        threadB.setName("B");
        threadB.start();
        /*
        ThreadC threadC=new ThreadC(threadService);
        threadC.setName("C");
        threadC.start();

         */
       // String a="a";
      //  String b="a";
       // System.out.println(a==b);
    }
}
