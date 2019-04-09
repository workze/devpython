package com.ze.rxjava;
import io.reactivex.*;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.swagger.models.auth.In;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Client {

    Client() throws InterruptedException {
        Flowable<Integer> flowable = Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> flowableEmitter) throws Exception {

            }
        },BackpressureStrategy.BUFFER);
        flowable.flatMap(new Function<Integer, Publisher<?>>() {
            @Override
            public Publisher<String> apply(Integer integer) throws Exception {
                return null;
            }
        });

        

    }


    public static void main(String[] args) throws InterruptedException {
        new Client();
    }
}
