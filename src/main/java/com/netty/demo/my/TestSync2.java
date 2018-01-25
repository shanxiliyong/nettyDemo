package com.netty.demo.my;

public class TestSync2 implements  Runnable{

    int b = 100;
    synchronized void m1()throws  Exception{
        System.out.println("m1 called");
        b=1000;
        Thread.sleep(500);  //6
        System.out.println("m1 called end b="+b);
    }

    synchronized void m2()throws  Exception{
        System.out.println("m2 called");
        Thread.sleep(250);  //5
        b=2000;
        System.out.println("m2 called end b ="+b);
    }


    public static void main(String[] args) throws Exception {
        final TestSync2 tt = new TestSync2();
        Thread t = new Thread(tt);  //1
        t.start();  //2
        tt.m2(); //3
        System.out.println("main thread b="+tt.b);  //4
    }

    public void run() {

            try {
                m1();
            } catch (Exception e) {
                e.printStackTrace();
            }

    }
}
