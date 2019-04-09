package com.thinkinginjava.mutithread;

import java.util.concurrent.Callable;

public class CallableWorker implements Callable<Integer> {
    private static int count = 0;
    private final int id;

    public CallableWorker() {
        this.id = count++;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getId());

        System.out.println("[" + id + "]:" + "hello");
        return id;
    }
}
