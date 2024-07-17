package com.lj.problem.leetcode._1;


/**
 * 求两个数的最大公约数
 */
public class Gcd {
    public int gcd(int a, int b) {
        
        int max = 1;
        
        for (int i = 2, k = Math.min(a, b); i <= k; i++) {
            if (a % i == 0 && b % i == 0 && i > max) {
                max = i;
            }
        }
        
        return max;
    }
    
    public static void main(String[] args) {
        System.out.println(new Gcd().gcd(12, 24));
    }
}
