package com.thinkinginjava.join;

import java.util.concurrent.TimeUnit;

public class Second implements Runnable{

    @Override
    public void run() {
        System.out.println("second is running...");
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("second is done.");
    }
}
