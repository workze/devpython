package com.ze.annotation;

//import lombok.Data;


public class Customer {
    @CustomAnnotation
    String customer = "customer str";

    public static void main(String[] args) {
        System.out.println("hi");
    }
}
