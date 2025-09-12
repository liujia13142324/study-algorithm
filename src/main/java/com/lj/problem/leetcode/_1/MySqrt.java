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

  /*  private static final int SQRT_INT_MAX = (int) Math.sqrt(Integer.MAX_VALUE);

    public int mySqrt2(int x) {
        if (x <= 1) {return x;}
        return lowBounce2(0, SQRT_INT_MAX, Math.min(x, SQRT_INT_MAX) + 1) - 1;
    }

    int lowBounce2(int l, int r, int target){
        int mid = 0;
        while (l <= r) {
            mid = (l + r) / 2;
            int tmp =  mid * mid;
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
*/

    public int mySqrt(int x) {
        if (x <= 1) {return x;}
        return lowBounce(0, x, ((long) x) + 1) - 1;
    }

    int lowBounce(int l, int r, long target){
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
