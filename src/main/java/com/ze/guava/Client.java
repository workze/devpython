package com.ze.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.*;
import sun.security.provider.certpath.Vertex;

import java.util.List;

public class Client {
    public static void main(String[] args) {
        Cache<String, String> cache = CacheBuilder.newBuilder().build();
        cache.put("name","wang");
        cache.getIfPresent("name");

        ImmutableList<String> list = ImmutableList.<String>builder()
                .add("a")
                .add("b")
                .build();
//        Multiset multiset = Multiset;
        Multimap<String,Object> multimap = ArrayListMultimap.create();

        Multiset<String> multiset = HashMultiset.create();
        multiset.add("abc");
        multiset.add("abc");
        multiset.add("111");
        multiset.count("abc");  //2
        System.out.println();

        Table<String, String, Integer> table = HashBasedTable.create();
        table.put("rest","home",1);
    }
}
