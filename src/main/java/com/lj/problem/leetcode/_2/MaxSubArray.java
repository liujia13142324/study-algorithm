package com.lj.problem.leetcode._2;

import org.junit.Test;

/**
 * 53. 最大子数组和
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 子数组
 * 是数组中的一个连续部分。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 *
 * 示例 2：
 * <p>
 * 输入：nums = [1]
 * 输出：1
 *
 * 示例 3：
 * 输入：nums = [5,4,-1,7,8]
 * 输出：23
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * <p>
 * <p>
 * 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
 */
public class MaxSubArray {

    @Test
    public void test1() {
        System.out.println(maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        System.out.println(maxSubArray(new int[]{1}));
        System.out.println(maxSubArray(new int[]{5,4,-1,7,8}));
//        System.out.println(maxSubArray(new int[]{1, 2, -1}));
//        System.out.println(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        System.out.println(maxSubArray(new int[]{-2,-1}));
    }

    public int maxSubArray1(int[] nums) {
        int[] sums = new int[nums.length];
        int len = nums.length;
        sums[0] = nums[0];
        int min = nums[0];
        int ans = nums[0];
        for (int i = 1; i < len; i++) {
            sums[i] = nums[i] + sums[i - 1];
            ans = Math.max(ans, Math.max(sums[i], sums[i] - min));
            min = Math.min(min, sums[i]);
        }

        return ans;
    }


    public int maxSubArray(int[] nums) {
        int max = -10000;
        int pre = 0;
        for (int i = 0; i < nums.length; i++) {
            pre = Math.max(pre + nums[i], nums[i]);
            max = Math.max(max, pre);
        }
        return max;
    }

}
