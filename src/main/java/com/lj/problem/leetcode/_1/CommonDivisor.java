package com.lj.problem.leetcode._1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 求全部的公约数
 */
public class CommonDivisor {
    
    public Integer[] commonDivisor(int a, int b) {
    
        List<Integer> list = new ArrayList<>();
        list.add(1);
        
        for (int i = 2, k = Math.min(a, b); i <= k; i++) {
            if (a % i == 0 && b % i == 0) {
                list.add(i);
            }
        }
        
        return list.toArray(new Integer[list.size()]);
    }
    
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new CommonDivisor().commonDivisor(4, 4)));
        System.out.println(Arrays.toString(CommonDivisor_2.commonDivisor(4, 4)));

        for (int i = 1; i < 100; i++) {
            for (int j = 1; j < 100; j++) {
                Integer[] i1 = new CommonDivisor().commonDivisor(i, j);
                Integer[] i2 = CommonDivisor_2.commonDivisor(i, j);
                Arrays.sort(i1);
                Arrays.sort(i2);
                System.out.println( i + ", " + j + ": " + Arrays.equals(i1, i2));;
            }
        }
    }
}
