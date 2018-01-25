package com.netty.demo.threadlocal;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class ThreadLoaclDemo1  implements Callable<String>{

    public static void main(String[] args)  {
        final ThreadLoaclDemo1 demo1 =
                new ThreadLoaclDemo1();

        FutureTask<String> f = new FutureTask<String>(demo1);
        final Thread thread = new Thread(f);
        thread.start();

    }





     public String call() throws Exception {
         StringBuilder sb = new StringBuilder("hello nihao");
         return sb.toString();

 }
}