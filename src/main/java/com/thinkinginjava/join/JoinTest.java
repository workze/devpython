package com.thinkinginjava.join;

public class JoinTest {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("main start");
        new Thread(new First()).join();
        System.out.println("main end");
    }
}
