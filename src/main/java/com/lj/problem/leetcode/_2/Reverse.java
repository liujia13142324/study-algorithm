package com.lj.problem.leetcode._2;

import org.junit.Test;

/**
 * 7. 整数反转
 * 中等
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 *
 *
 * 示例 1：
 * 输入：x = 123
 * 输出：321
 *
 * 示例 2：
 * 输入：x = -123
 * 输出：-321

 * 示例 3：
 * 输入：x = 120
 * 输出：21

 * 示例 4：
 * 输入：x = 0
 * 输出：0
 *
 * 提示：
 *
 * -231 <= x <= 231 - 1
 */
public class Reverse {

    @Test
    public void test() {
        System.out.println(9 * 1000000000);
    }

    public int reverse(int x) {
        if (x == 0) {
            return 0;
        }
        int tmp = x;
        while (x % 10 == 0) {
            x /= 10;
        }
        int[] nums = new int[10];
        int i = 0;
        int mod;
        while (x != 0) {
            mod = x % 10;
            nums[i++] = mod;
            x /= 10;
        }
        if (i == 10 && nums[0] > 2) {
            return 0;
        }

        int ans = 0;
        int factor = 1;
        while (i >= 0) {
            ans += nums[i--] * factor;
            factor *= 10;
        }

        return (ans ^ tmp) >= 0 ? ans : 0;
    }

}
