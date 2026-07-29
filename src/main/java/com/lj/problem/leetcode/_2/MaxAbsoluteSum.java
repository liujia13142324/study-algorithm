package com.lj.problem.leetcode._2;

/**
 * 1749. 任意子数组和的绝对值的最大值
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个整数数组 nums 。一个子数组 [numsl, numsl+1, ..., numsr-1, numsr] 的 和的绝对值 为 abs(numsl + numsl+1 + ... + numsr-1 + numsr) 。
 *
 * 请你找出 nums 中 和的绝对值 最大的任意子数组（可能为空），并返回该 最大值 。
 *
 * abs(x) 定义如下：
 *
 * 如果 x 是负整数，那么 abs(x) = -x 。
 * 如果 x 是非负整数，那么 abs(x) = x 。
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,-3,2,3,-4]
 * 输出：5
 * 解释：子数组 [2,3] 和的绝对值最大，为 abs(2+3) = abs(5) = 5 。
 * 示例 2：
 *
 * 输入：nums = [2,-5,1,-4,3,-2]
 * 输出：8
 * 解释：子数组 [-5,1,-4] 和的绝对值最大，为 abs(-5+1-4) = abs(-8) = 8 。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 */
public class MaxAbsoluteSum {

    public int maxAbsoluteSum2(int[] nums) {
        int[][] dp = new int[2][nums.length];
        dp[0][0] = dp[1][0] = nums[0];
        int ans = Math.abs(nums[0]);
        for (int i = 1; i < nums.length; i++) {

            dp[0][i] = Math.max(dp[0][i - 1], 0) + nums[i];
            dp[1][i] = Math.min(dp[1][i - 1], 0) + nums[i];

            ans = Math.max(ans, Math.max(dp[0][i], -dp[1][i]));
        }
        return ans;
    }

    public int maxAbsoluteSum(int[] nums) {
        int[][] dp = new int[2][nums.length];
        dp[0][0] = dp[1][0] = nums[0];
        int ans = Math.abs(nums[0]);
        for (int i = 1; i < nums.length; i++) {

            dp[0][i] = Math.max(dp[0][i - 1] + nums[i], nums[i]);
            dp[1][i] = Math.min(dp[1][i - 1] + nums[i], nums[i]);

            ans = Math.max(ans, Math.max(Math.abs(dp[0][i]), Math.abs(dp[1][i])));
        }
        return ans;
    }
}
