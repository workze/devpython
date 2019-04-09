package com.ze.fanxing;

import com.alibaba.fastjson.JSONObject;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Date;
import java.util.Observable;
import java.util.logging.Logger;

public class Test<T> {

  static Logger logger = Logger.getLogger(Test.class.getName());

  public static void main(String[] args) throws NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException {
    Date date = new Date();
    new Test<Date>().showType(date);

  }

  public <T> void showType(T t) throws NoSuchMethodException, IllegalAccessException, InstantiationException, ClassNotFoundException {
    // Method[] methods = Test.class.getDeclaredMethods();
    // Method m = Test.class.getDeclaredMethod("showType",Object.class);
    // m.getTypeParameters();
    // System.out.println((Class<T>)m.getParameterTypes()[0]);
    // Class<T> entityClass = (Class<T>) ((ParameterizedType) (getClass().getGenericSuperclass().getActualTypeArguments()[0]);
    // artype = getClass().getTypeParameters();
    // ParameterizedType p = (ParameterizedType)type;
    // Class a = (Class<T>) p.getActualTypeArguments()[0];
    Class clss = t.getClass();
    System.out.println(t.getClass());
    Class<?> cls = Class.forName(t.getClass().getName());

    JSONObject.parseObject("",clss);
    Retrofit retrofit = new Retrofit.Builder().build();
    retrofit.create(clss);
    //System.out.println(t.getClass().newInstance());
  }
}
