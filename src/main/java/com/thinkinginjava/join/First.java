package com.thinkinginjava.join;

import java.util.concurrent.TimeUnit;

public class First implements Runnable{

    @Override
    public void run() {
        System.out.println("first is running...");
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("first is done.");
    }
}
