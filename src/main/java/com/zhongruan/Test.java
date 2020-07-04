package com.zhongruan;

public class Test {
    public static void main(String[] args) {
        String name = Thread.currentThread().getName();
        System.out.println(name);
        Thread1 thread1=new Thread1();
        thread1.setName("小黄");
        thread1.start();
        Thread1 thread2=new Thread1();
        thread2.setName("xiaohua");
        thread2.start();
        Thread2 t2=new Thread2();
        Thread t3=new Thread(t2);
        t3.start();
    }
}
