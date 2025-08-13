package com.lj.problem.leetcode._2;

import org.junit.Test;

/**
 * 2461. 长度为 K 子数组中的最大和 1553
 * 给你一个整数数组 nums 和一个整数 k 。请你从 nums 中满足下述条件的全部子数组中找出最大子数组和：
 *
 * 子数组的长度是 k，且
 * 子数组中的所有元素 各不相同 。
 * 返回满足题面要求的最大子数组和。如果不存在子数组满足这些条件，返回 0 。
 *
 * 子数组 是数组中一段连续非空的元素序列。
 *
 * 示例 1：
 * 输入：nums = [1,5,4,2,9,9,9], k = 3
 * 输出：15
 * 解释：nums 中长度为 3 的子数组是：
 * - [1,5,4] 满足全部条件，和为 10 。
 * - [5,4,2] 满足全部条件，和为 11 。
 * - [4,2,9] 满足全部条件，和为 15 。
 * - [2,9,9] 不满足全部条件，因为元素 9 出现重复。
 * - [9,9,9] 不满足全部条件，因为元素 9 出现重复。
 * 因为 15 是满足全部条件的所有子数组中的最大子数组和，所以返回 15 。
 *
 *  示例 2：
 * 输入：nums = [4,4,4], k = 3
 * 输出：0
 * 解释：nums 中长度为 3 的子数组是：
 * - [4,4,4] 不满足全部条件，因为元素 4 出现重复。
 * 因为不存在满足全部条件的子数组，所以返回 0 。
 *
 * 提示：
 *
 * 1 <= k <= nums.length <= 105
 * 1 <= nums[i] <= 105
 */
public class MaximumSubarraySum {


    @Test
    public void test() {
        System.out.println(maximumSubarraySum(new int[]{1,5,4,2,9,9,9}, 3));
        System.out.println(maximumSubarraySum(new int[]{4,4,4}, 3));
    }

    public long maximumSubarraySum(int[] nums, int k) {
        long sum = 0, ans = 0;
        int l = 0;
        int[] map = new int[100001];

        for (int r = 0; r < nums.length; r++) {
            // 先移左边， 先移右边的话，可能会超出 k，这时候 nums[r] 可能和 nums[j] 冲突
            while (map[nums[r]] > 0) {
                map[nums[l]]--;
                sum -= nums[l];
                l++;
            }

            map[nums[r]]++;
            sum += nums[r];

            // 判断是否达窗口大小
            if (r - l + 1 == k) {
                ans = Math.max(ans, sum);
                // 移动左边，不移动的话，下次肯定超了，除非再加一个没什么用的分支，看下面 demo
                map[nums[l]]--;
                sum -= nums[l];
                l++;
            }

            // demo
//            if (r - l + 1 == k) {
//                ans = Math.max(ans, sum);
//            } else if (r - l + 1 > k) {
//                map[nums[l]]--;
//                sum -= nums[l];
//                ans = Math.max(ans, sum);
//                l++;
//            }

        }

        return ans;
    }

}
