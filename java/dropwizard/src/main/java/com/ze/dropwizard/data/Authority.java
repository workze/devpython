package com.ze.dropwizard.data;

/**
 * Created by ZE-C on 2017/12/16.
 */
public enum  Authority {
    PUBLIC("public"),PRIVATE("private");

    private String name;

    private Authority(String n){
        name = n;
    }

    @Override
    public String toString() {
        return name;
    }
}
