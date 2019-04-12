package com.thinkinginjava.mutithread;

public class ThreadBasic {
    public static void main(String[] args) {
        System.out.println("hello");

        for (int i=0; i<10; i++){
            new Thread(new Worker()).start();
        }
        System.out.println("main is done.");

    }
}
