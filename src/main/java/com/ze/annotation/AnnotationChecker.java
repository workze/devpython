package com.ze.annotation;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;

public class AnnotationChecker {

    private final static ConcurrentHashMap<String,Field[]> fieldsMap = new ConcurrentHashMap<String, Field[]>();

    public static void check(Object object) throws IllegalAccessException {
        Class<? extends Object> clazz = object.getClass();
        Field[] fields = null;
        fields = clazz.getDeclaredFields();
        for (Field field: fields) {
            field.setAccessible(true);
            Object value = field.get(object);
            System.out.println(value);
            CustomAnnotation annotation = field.getAnnotation(CustomAnnotation.class);
            field.getAnnotations();
            System.out.println(annotation.desc());

        }
    }

    public static void main(String[] args) throws IllegalAccessException {
        System.out.println("hi");
        check(new Customer());
    }
}
