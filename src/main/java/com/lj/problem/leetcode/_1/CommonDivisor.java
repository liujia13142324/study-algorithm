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
        System.out.println(Arrays.toString(new CommonDivisor().commonDivisor(12, 24)));
    }
    
}
