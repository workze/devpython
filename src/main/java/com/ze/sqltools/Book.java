package com.ze.sqltools;

/**
 * Created by ZE-C on 2017/9/3.
 */
public class Book {
    String name;
    int price;
    int int_v = 0;
    String author = "jobs";
    String nullstr = null;


    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
