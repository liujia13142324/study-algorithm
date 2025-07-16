package com.lj.problem.leetcode._2;

import org.junit.Test;

/**
 * 3201. 找出有效子序列的最大长度 I
 *
 * 给你一个整数数组 nums。
 * nums 的子序列 sub 的长度为 x ，如果其满足以下条件，则称其为 有效子序列：
 * (sub[0] + sub[1]) % 2 == (sub[1] + sub[2]) % 2 == ... == (sub[x - 2] + sub[x - 1]) % 2
 * 返回 nums 的 最长的有效子序列 的长度。
 * 一个 子序列 指的是从原数组中删除一些元素（也可以不删除任何元素），剩余元素保持原来顺序组成的新数组。
 *
 * 示例 1：
 * 输入： nums = [1,2,3,4]
 * 输出： 4
 * 解释：
 * 最长的有效子序列是 [1, 2, 3, 4]。
 *
 * 示例 2：
 * 输入： nums = [1,2,1,1,2,1,2]
 * 输出： 6
 * 解释：
 * 最长的有效子序列是 [1, 2, 1, 2, 1, 2]。
 *
 * 示例 3：
 * 输入： nums = [1,3]
 * 输出： 2
 * 解释：
 * 最长的有效子序列是 [1, 3]。
 *
 * 提示：
 *
 * 2 <= nums.length <= 2 * 105
 * 1 <= nums[i] <= 107
 */
public class MaximumLength {

    @Test
    public void test() {
        System.out.println(maximumLength(new int[]{1,3}));
        System.out.println(maximumLength(new int[]{1,5,9,4,2}));
        System.out.println(maximumLength(new int[]{1,2,1,1,2,1,2}));
        System.out.println(maximumLength(new int[]{1,8,4,2,4}));
        System.out.println(maximumLength(new int[]{2,13,26,70,76}));
    }

    public int maximumLength(int[] nums) {
        int c1 = 0, c2 = 0, c3 = 0;
        int pre = 0;
        int tmp;
        for (int num : nums) {
            tmp = num & 1;
            // 奇数
            if (tmp == 1) {
                c1++;
            } else {
                // 偶数
                c2++;
            }

            if (c3 == 0 || (tmp ^ (pre & 1)) == 1) {
                c3++;
                pre = num;
            }

        }
        return Math.max(c3, Math.max(c1, c2));
    }

    @Test
    public void test2() {
        System.out.println((2 & 1) ^ (3 & 1));
        System.out.println((2 & 1) ^ (4 & 1));
    }


}
