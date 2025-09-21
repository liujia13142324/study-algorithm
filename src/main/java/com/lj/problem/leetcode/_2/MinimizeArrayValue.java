package com.lj.problem.leetcode._2;

import org.junit.Test;

/**
 * 2439. 最小化数组中的最大值
 * 给你一个下标从 0 开始的数组 nums ，它含有 n 个非负整数。
 *
 * 每一步操作中，你需要：
 *
 * 选择一个满足 1 <= i < n 的整数 i ，且 nums[i] > 0 。
 * 将 nums[i] 减 1 。
 * 将 nums[i - 1] 加 1 。
 * 你可以对数组执行 任意 次上述操作，请你返回可以得到的 nums 数组中 最大值 最小 为多少。
 *
 *
 *
 * 示例 1：
 * 输入：nums = [3,7,1,6]
 * 输出：5
 * 解释：
 * 一串最优操作是：
 * 1. 选择 i = 1 ，nums 变为 [4,6,1,6] 。
 * 2. 选择 i = 3 ，nums 变为 [4,6,2,5] 。
 * 3. 选择 i = 1 ，nums 变为 [5,5,2,5] 。
 * nums 中最大值为 5 。无法得到比 5 更小的最大值。
 * 所以我们返回 5 。
 *
 * 示例 2：
 * 输入：nums = [10,1]
 * 输出：10
 * 解释：
 * 最优解是不改动 nums ，10 是最大值，所以返回 10 。
 *
 *
 * 提示：
 * n == nums.length
 * 2 <= n <= 105
 * 0 <= nums[i] <= 109
 */
public class MinimizeArrayValue {

    @Test
    public void test() {
//        System.out.println(minimizeArrayValue(new int[]{3, 7, 1, 6}));
        System.out.println(minimizeArrayValue2(new int[]{13,13,20,0,8,9,9}));

    }

    /**
     * 这个最快
     */
    public int minimizeArrayValue3(int[] nums) {
        long ans = 0;
        long s = 0;
        for (int i = 0; i < nums.length; i++) {
            s += nums[i];
            // 这个等价上取整 TODO 向上取整原理了解
            ans = Math.max(ans, (s + i) / (i + 1));
        }
        return (int) ans;

    }

    public int minimizeArrayValue2(int[] nums) {
        int ans = Integer.MIN_VALUE;
        long s = 0;
        for (int i = 0; i < nums.length; i++) {
            s += nums[i];
            ans = Math.max(ans, (int)Math.ceil(s / (double) (i + 1)));
        }
        return ans;
    }


    public int minimizeArrayValue(int[] nums) {
        long[] sum = new long[nums.length];
        int max = nums[0];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
            sum[i] = sum[i - 1] + nums[i];
        }
        int min = (int) Math.ceil(sum[sum.length - 1] / (double) nums.length);

        if (max == min) {
            return min;
        }

        max++;
        min--;

        while (min + 1 < max) {
            int mid = (min + max) >>> 1;
            if (check(nums, mid, sum)) {
                max = mid;
            }else {
                min = mid;
            }
        }

        return max;
    }

    private boolean check(int[] nums, int mid, long[] sum) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > mid && Math.ceil(sum[i] / (double) (i + 1)) > mid ) {
                return false;
            }
        }
        return true;
    }
}
