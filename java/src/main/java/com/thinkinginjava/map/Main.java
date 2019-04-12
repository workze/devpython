package com.thinkinginjava.map;

import org.apache.commons.lang3.RandomUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class Main {
  public static void main(String[] args) throws InterruptedException {


    Map<String,Integer> singleMap = new ConcurrentHashMap<>();
    Map<String,HashMap<String,Integer>> multiMap = new HashMap<>();
    ExecutorService service = new ThreadPoolExecutor(10,10,0L,TimeUnit.SECONDS,new LinkedBlockingDeque<>(),new ThreadPoolExecutor.AbortPolicy());
    long begin = System.currentTimeMillis();
    for (int i = 0; i < 5000000; i++) {
      service.execute(()->{
        singleMap.put("str"+RandomUtils.nextInt(),10);
//        if(multiMap.get(Thread.currentThread().getName()) == null){
//          multiMap.put(Thread.currentThread().getName(),new HashMap<>());
//        }
//        multiMap.get(Thread.currentThread().getName()).put("str"+RandomUtils.nextInt(),10);
      });
    }
    service.shutdown();
    while(!service.isTerminated()){
      TimeUnit.MICROSECONDS.sleep(10);
    }
    System.out.println(System.currentTimeMillis() - begin);

//    long begin = System.currentTimeMillis();
//    int a = 0;
//    String s = "";
//    for (int i = 0; i < 102400; i++) {
//      String str = "";
//    }
//    long period = System.currentTimeMillis() - begin;
//    System.out.println(period);

//    System.out.println("hi");
//    long begin = System.currentTimeMillis();
//    Map<String,Integer> map = new HashMap<>(400*10000);
//    long init = System.currentTimeMillis() - begin;
//    int N = 200 * 10000;
//    for(int i=0; i< N; i++){
//      map.put("string"+i,i);
//    }
//    long period = System.currentTimeMillis() - begin;
//    System.out.println(period);
//    System.out.println(init);
  }
}
