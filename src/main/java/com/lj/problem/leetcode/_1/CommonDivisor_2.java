package com.lj.problem.leetcode._1;

import org.junit.Test;

import java.util.*;

/**
 * 求全部的公约数
 */
public class CommonDivisor_2 {

    public static void main(String[] args) {

    }

    public static Integer[] commonDivisor(int a, int b) {
        // 辗转相除法，获取最大公约数
        return allDivisor(maxCommonDivisor(a, b));
    }


    public static int maxCommonDivisor(int a, int b) {
        int max = Math.max(a, b);
        int min = Math.min(a, b);
        int mod;

        do {
            mod = max % min;
            if (mod != 0) {
                max = min;
                min = mod;
            }
        }while (mod != 0);
        return min;
    }

    public static Integer[] allDivisor(int a) {
        if (a == 1) {
            return new Integer[]{1};
        }

        Set<Integer> divisors = new HashSet<>();
        divisors.add(1);
        divisors.add(a);
        int end = a/2;

        for (int i = 2; i <= end; i++) {
            if (a % i == 0) {
                divisors.add(i);
                end = a / i;
                divisors.add(end);
            }
        }
        return divisors.toArray(new Integer[divisors.size()]);
    }

    @Test
    public void test() {
        System.out.println(Arrays.toString(allDivisor(4)));
//        System.out.println(maxCommonDivisor(17, 14));
    }
    
}
