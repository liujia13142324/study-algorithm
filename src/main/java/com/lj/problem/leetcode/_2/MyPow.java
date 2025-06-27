package com.lj.problem.leetcode._2;

import com.lj.study.common.aop.annotation.PrintLog;

/**
 * 快速幂
 */
public class MyPow {
    
    static int count;
    
    
    public double myPow(double x, int n) {
        
        return n >= 0 ? quickMul(x, n) : 1/quickMul(x, -n);
    }
    
    /**
     * 快速幂
     * @param x
     * @param n
     * @return
     */
    private double quickMul(double x, int n) {
        if (n == 0) {
            return 1;
        }
        double y = quickMul(x, n/2);
//        count++;
        return n % 2 == 0 ? y*y : y*y*x;
    }
    
    // 没什么优化
    /*public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }else if (n == 1) {
            return x;
        }else if (n == -1) {
            return 1/x;
        }
    
        // 2147483646，和遍历的复杂度一样
        count++;
        return myPow(x, n/2) * myPow(x, n - n/2);
    }*/
    
    public static void main(String[] args) {
        System.out.println(new MyPow().myPow(1, -2147483648) + " --> " + count);
        int i = -2147483648;
        System.out.println(i + "---> " + (-i));
//        System.out.println(new MyPow().myPow(2,10));
//        System.out.println(Math.pow(0.00001, Integer.MAX_VALUE));
    }
}
