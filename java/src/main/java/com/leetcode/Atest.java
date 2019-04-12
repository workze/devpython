package com.leetcode;

import java.util.*;

public class Atest {

    static List<Integer> positive = new ArrayList<>();
    static Set<Integer> positiveSet = new HashSet<>();
    static List<Integer> negative = new ArrayList<>();
    static Set<Integer> negativeSet = new HashSet<>();
    static List<List<Integer>> result = new LinkedList<>();

    static Map<Integer, String> toMap(Integer[] arr) {
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; i < arr.length; j++) {
                map.put(arr[i]+arr[j], String.valueOf(i)+String.valueOf(j));
            }
        }
        return map;
    }

    // i = 0, j=1- n-1

    static void addToResult(int a, int b, int c) {
        List<Integer> list = new LinkedList<>();
        list.add(a);
        list.add(b);
        list.add(c);
        result.add(list);
    }

    public List<List<Integer>> threeSum(int[] nums) {

        List<Integer> zero = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                if (!zero.contains(0)) {
                    zero.add(0);
                }
            } else if (nums[i] > 0) {
                positiveSet.add(nums[i]);
            } else {
                negativeSet.add(nums[i]);
            }
        }

        Integer[] po = (Integer[]) positiveSet.toArray();
        Integer[] ng = (Integer[]) negativeSet.toArray();
        Map<Integer,String> poMap = toMap(po);
        Map<Integer,String> ngMap = toMap(ng);

        // one po, tow ng
        for (int p:po) {
            if(ngMap.containsKey(0-p)){
                int i = Integer.parseInt(ngMap.get(0-p).substring(0,1));
                int j = Integer.parseInt(ngMap.get(0-p).substring(1,2));
                addToResult(p, ng[i], ng[j]);
            }
        }
        // one ng, two po


        // with 0

        if (zero.size() > 0) {
            for (int p : positive) {
                if (negativeSet.contains(0 - p)) {
                    addToResult(0, p, 0 - p);
                }
            }
        }
        // not with 0, with one posi


        return null;
    }


    /**

     2 . 3 . 4
     0
     -1 . -2 . -5

     */
}

