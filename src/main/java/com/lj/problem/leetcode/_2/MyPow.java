package com.lj.problem.leetcode._2;

import com.lj.study.common.aop.annotation.PrintLog;
import org.junit.Test;

/**
 *
 * 50. 快速幂
 * 实现 pow(x, n) ，即计算 x 的整数 n 次幂函数（即，xn ）。
 *
 * 示例 1：
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 *
 * 示例 2：
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100

 * 示例 3：
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2-2 = 1/22 = 1/4 = 0.25
 *
 * 提示：
 *
 * -100.0 < x < 100.0
 * -231 <= n <= 231-1
 * n 是一个整数
 * 要么 x 不为零，要么 n > 0 。
 * -104 <= xn <= 104
 */
public class MyPow {
    
    static int count;

    @Test
    public void test() {
        System.out.println(myPow2(2, 7));
    }
    public double myPow2(double x, int n) {
        return dfs(x, n);
    }

    private double dfs(double x, int n) {
        if (n == 0) {
            return 1;
        }

        return x * dfs(x, n/2) * dfs(x, n/2);
    }





    public double myPow(double x, int n) {
//        Math.pow(x, n);
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
