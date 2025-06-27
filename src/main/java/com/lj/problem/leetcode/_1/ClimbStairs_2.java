package com.lj.problem.leetcode._1;

import com.lj.study.common.bean.A;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：2
 * 解释：有两种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶
 * 2. 2 阶
 * 示例 2：
 *
 * 输入：n = 3
 * 输出：3
 * 解释：有三种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶 + 1 阶
 * 2. 1 阶 + 2 阶
 * 3. 2 阶 + 1 阶
 */
public class ClimbStairs_2 {

    public static void main(String[] args) {
        System.out.println(climbStairs(35));
    }

    private static int climbStairs(int n) {
        int m = 0;
        int result = 0;
        while (m <= n) {
            long tmp = c2(n, m);
            result += tmp;
            n--;
            m++;
        }
        return result;
    }

    /**
     * 16 ms
     */
    public static int c2(int n, int m) {
        return factorial2(n).divide(factorial2(m).multiply( factorial2(n - m) )).intValue();
    }

    public static BigDecimal factorial2(int n) {
        BigDecimal result = BigDecimal.ONE;
        for (int i = 1; i <= n; i++) {
            result = result.multiply(BigDecimal.valueOf(i));
        }
        return result;
    }

    /**
     * 0 ms
     */
    public static int c(int n, int m){
        if (m == 0 || n == m) {
            return 1;
        }

        int z = Math.max(n - m, m);
        int k = Math.min(n - m, m);

        long [] factors1 = new long[n - z];

        for (int i = 0, start = z + 1; start <= n; i++, start++) {
            factors1[i] = start;
        }

        long[] remains = new long[k];
        Arrays.fill(remains, 1);

        for (int i = 2; i <= k; i++) {
            boolean flag = false;
            for (int j = 0; j < factors1.length; j++) {
                if (factors1[j] % i == 0) {
                    flag = true;
                    factors1[j] /= i;
                    break;
                }
            }
            if (!flag) {
                remains[i-1] = i;
            }
        }

        long result = 1;
        for (long j : factors1) {
            result *= j;
        }

        for (long j : remains) {
            result /= j;
        }

        return (int) result;
    }

    public static long factorial(long n) {
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    @Test
    public void testFactorial() {
        System.out.println(c2(11, 4));
//        System.out.println(factorial(13));
    }
}
