package com.thinkinginjava.mutithread;

public class Worker implements Runnable{
    private static int count = 0;
    private final int id;
    public Worker() {
         id = count++;
    }

    @Override
    public void run() {
        System.out.println("this is worker " + id );
        for (int i = 0; i < 10; i++) {
            System.out.println("worker "+id+" "+i);
        }
        Thread.yield();
    }
}
