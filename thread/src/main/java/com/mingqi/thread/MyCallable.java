package com.mingqi.thread;



import java.util.concurrent.Callable;

public class MyCallable implements Callable {
    private String string="";
    public MyCallable(String s){
        string=s;
        System.out.println("string的值是："+string);
    }
    public Object call() throws Exception {
        return Thread.currentThread().getName()+ " ；"+string+"实现了Callable接口";
    }
}
