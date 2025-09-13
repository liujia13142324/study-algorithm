package com.lj.problem.leetcode._1;

import org.junit.Test;

/**
 * 69. x 的平方根

 * 提示
 * 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
 * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
 *
 * 示例 1：
 * 输入：x = 4
 * 输出：2
 *
 * 示例 2：
 * 输入：x = 8
 * 输出：2
 * 解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
 *
 *
 * 提示：
 * 0 <= x <= 231 - 1
 */
public class MySqrt {

    @Test
    public void test() {
        System.out.println((int) Math.sqrt(Integer.MAX_VALUE));
        System.out.println(mySqrt(2147395599));
    }

    private static final int SQRT_INT_MAX = (int) Math.sqrt(Integer.MAX_VALUE);

    /**
     * 闭区间
     */
    public int mySqrt4(int x) {
        if (x <= 1) {return x;}
        int l = 0;
        int r = Math.min(SQRT_INT_MAX, x);
        while (l <= r) {
            int mid = (l + r) >>> 1;
            if (mid * mid <= x) {
                l = mid + 1;
            }else {
                r = mid - 1;
            }
        }
        return r;
    }

    /**
     * 开区间
     * 循环不变量，0 的平方一定 <= x，不需要在二分区间中。注意二分区间只需要包含那些尚未确定平方是否 <= x 的数。
     *
     */
    public int mySqrt3(int x) {
        if (x <= 1) {return x;}
//        int l = -1;
        // 0 和 -1 都可以
        int l = 0;
        int r = Math.min(SQRT_INT_MAX, x) + 1;
        while (l + 1 < r) {
            int mid = (l + r) >>> 1;
            if (mid * mid <= x) {
                l = mid;
            }else {
                r = mid;
            }
        }
        return l;
    }

    public int mySqrt2(int x) {
        if (x <= 1) {return x;}
        return lowerBound2(0, SQRT_INT_MAX + 1, x);
    }

    int lowerBound2(int l, int r, int target){
        while (l + 1 < r) {
            int mid = (l + r) >>> 1;
            if (mid * mid > target) {
                r = mid;
            }else {
                l = mid;
            }
        }
        return l;
    }

    public int mySqrt(int x) {
        if (x <= 1) {return x;}
        return lowerBound(0, x, ((long) x) + 1) - 1;
    }

    int lowerBound(int l, int r, long target){
        int mid = 0;
        while (l <= r) {
            mid = (l + r) / 2;
            long tmp = (long) mid * mid;
            if (target == tmp) {
                return mid;
            }else if (target > tmp) {
                l = mid + 1;
            }else {
                r = mid - 1;
            }
        }
        return l;
    }
}
