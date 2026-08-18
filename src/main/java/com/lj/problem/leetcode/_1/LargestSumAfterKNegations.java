package com.lj.problem.leetcode._1;

import java.util.Arrays;

/**
 * 1005. K 次取反后最大化的数组和
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个整数数组 nums 和一个整数 k ，按以下方法修改该数组：
 *
 * 选择某个下标 i 并将 nums[i] 替换为 -nums[i] 。
 * 重复这个过程恰好 k 次。可以多次选择同一个下标 i 。
 *
 * 以这种方式修改数组后，返回数组 可能的最大和 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [4,2,3], k = 1
 * 输出：5
 * 解释：选择下标 1 ，nums 变为 [4,-2,3] 。
 * 示例 2：
 *
 * 输入：nums = [3,-1,0,2], k = 3
 * 输出：6
 * 解释：选择下标 (1, 2, 2) ，nums 变为 [3,1,0,2] 。
 * 示例 3：
 *
 * 输入：nums = [2,-3,-1,5,-4], k = 2
 * 输出：13
 * 解释：选择下标 (1, 4) ，nums 变为 [2,3,-1,5,4] 。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 104
 * -100 <= nums[i] <= 100
 * 1 <= k <= 104
 */
public class LargestSumAfterKNegations {

    public int largestSumAfterKNegations(int[] nums, int k) {
        Arrays.sort(nums);
        int sum = 0;
        for (int num: nums) {
            sum += num;
        }
        int maxVal = -10000;
        int minIdx = 0;
        for (; k > 0; k--) {
            if (minIdx < nums.length - 1) {
                if (-nums[minIdx] > nums[minIdx + 1]) {
                    sum = sum - 2 * nums[minIdx];
                    maxVal = Math.max(nums[minIdx], nums[minIdx + 1]);
                    minIdx++;
                } else {
                    return (k & 1) == 0 ? sum : (sum - 2 * nums[minIdx]);
                }
            } else {
                // 说明全是负数，现在的最小值，是原来的最大值
                return (k & 1) == 0 ? sum : (sum - 2 * maxVal);
            }
        }

        return sum;
    }
}
