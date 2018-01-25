package com.netty.demo.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Executors01 {
    public static void main(String[] args) {
        final ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
            public void run() {
                System.out.println("Hello Multi Thread");
            }
        });

    }
}
