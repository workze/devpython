package com.thinkinginjava.mutithread;

import io.swagger.models.auth.In;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExecTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService service = Executors.newCachedThreadPool();

        List<Future<Integer>> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add(service.submit(new CallableWorker()));
        }
        System.out.println(list);
        for (Future<Integer> item: list
             ) {
            System.out.println(item.get());
        }
        System.out.println("main done");
        service.shutdown();
//        ExecutorService es = Executors.newCachedThreadPool();
//        ExecutorService es = Executors.newSingleThreadExecutor();
//        for (int i = 0; i < 10; i++) {
//            es.execute(new Worker());
//        }
//        es.shutdown();
    }


}
