package com.leetcode;

import com.sun.corba.se.spi.orbutil.threadpool.Work;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class UserLike {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        //System.out.println("user is "+n);
        List<Integer> userLikes = new ArrayList<>(n);
        for(int i=0; i<n; i++){
            userLikes.add(sc.nextInt());
        }
        //System.out.println(userLikes.toString());
        int q = sc.nextInt();
        for(int i=0; i<q; i++){
            int l = sc.nextInt();
            int r = sc.nextInt();
            int k = sc.nextInt();
            List<Integer> s = userLikes.subList(l-1,r);
            System.out.println(count(s,k));
        }

    }

    static int count( List<Integer> list, int k){
        int count = 0;
        for(Integer i:list){
            if(i == k){
                count++;
            }
        }
        return count;
    }

/*
5
1 2 3 3 5
3
1 2 1
2 4 5
3 5 3
 */

}
