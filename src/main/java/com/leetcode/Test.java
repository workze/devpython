package com.leetcode;

public class Test {

    public static boolean Find(int target, int [][] array) {
        if (array == null || array.length == 0) {
            return false;
        }
        int R = array.length;
        System.out.println("row:" + R);
        int C = array[0].length;
        System.out.println("col:" + C);
        return false;
    }

    static boolean find_once_at_ij(int[][] array, int i, int j, int v){
        if(v < array[i][j]){
            return false;
        }
        if(v == array[i][j]){
            return true;
        }
        return false;

    }

    static boolean find_in_row(int[] array, int start, int end, int v){
        return false;
    }

    public static void main(String[] args) {
        int[][] array = new int[2][2];
        array[0][0] = 1;
        array[0][1] = 2;
        array[1][0] = 3;
        array[1][1] = 4;
        Find(1, array);
    }
/**
 *
 * 1  2  3
 * 2  3  4
 * 3  4  6
 *
 *
 *
 * 0,0   0,1   0,2   0,C
 *
 * 1,0   1,1   1,2   1,C
 *
 * i,0   i,j   i,j+1
 *
 *
 * R,0               R,C
 *
 * *
 * 5
  */



}
