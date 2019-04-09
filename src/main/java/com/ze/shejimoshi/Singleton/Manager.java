package com.ze.shejimoshi.Singleton;

public class Manager {

    private Manager(){}

    private static class Inner{
        private final static Manager manager = new Manager();
    }

    public static int getOtherField(){
        return 1;
    }

    public static Manager getManager(){
        return Inner.manager;
    }

    public static void main(String[] args) {
        Manager.getOtherField();
        System.out.println();
        Manager.getManager();
    }
}
