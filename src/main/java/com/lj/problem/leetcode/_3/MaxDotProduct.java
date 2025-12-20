package com.lj.problem.leetcode._3;

import java.util.Arrays;

/**
 * 1458. 两个子序列的最大点积
 * 给你两个数组 nums1 和 nums2 。
 * 请你返回 nums1 和 nums2 中两个长度相同的 非空 子序列的最大点积。
 * 数组的非空子序列是通过删除原数组中某些元素（可能一个也不删除）后剩余数字组成的序列，但不能改变数字间相对顺序。比方说，[2,3,5] 是 [1,2,3,4,5] 的一个子序列而 [1,5,3] 不是。
 *
 * 示例 1：
 * 输入：nums1 = [2,1,-2,5], nums2 = [3,0,-6]
 * 输出：18
 * 解释：从 nums1 中得到子序列 [2,-2] ，从 nums2 中得到子序列 [3,-6] 。
 * 它们的点积为 (2*3 + (-2)*(-6)) = 18 。
 *
 * 示例 2：
 * 输入：nums1 = [3,-2], nums2 = [2,-6,7]
 * 输出：21
 * 解释：从 nums1 中得到子序列 [3] ，从 nums2 中得到子序列 [7] 。
 * 它们的点积为 (3*7) = 21 。
 *
 * 示例 3：
 * 输入：nums1 = [-1,-1], nums2 = [1,1]
 * 输出：-1
 * 解释：从 nums1 中得到子序列 [-1] ，从 nums2 中得到子序列 [1] 。
 * 它们的点积为 -1 。
 *
 *
 * 提示：
 * 1 <= nums1.length, nums2.length <= 500
 * -1000 <= nums1[i], nums2[i] <= 100
 *
 *
 * 点积：
 * 定义 a = [a1, a2,…, an] 和 b = [b1, b2,…, bn] 的点积为：
 * \mathbf{a}\cdot \mathbf{b} = \sum_{i=1}^n a_ib_i = a_1b_1 + a_2b_2 + \cdots + a_nb_n
 * 这里的 Σ 指示总和符号。
 */
public class MaxDotProduct {

    public int maxDotProduct_(int[] nums1, int[] nums2) {
        int minusVal = checkIsMinus(nums1, nums2);
        if (minusVal < 0) return minusVal;
        int[] dp = new int[nums2.length + 1];

        for (int num: nums1) {
            int pre = 0;
            for (int j = 1; j <= nums2.length; j++) {
                int tmp = dp[j];
                dp[j] = Math.max(num * nums2[j-1] + pre, Math.max(dp[j], dp[j - 1]));
                pre = tmp;
            }
        }

        return dp[nums2.length];
    }

    public int maxDotProduct2_(int[] nums1, int[] nums2) {
        int[] dp = new int[nums2.length + 1];
        Arrays.fill(dp, Integer.MIN_VALUE);
        for (int num: nums1) {
            int pre = Integer.MIN_VALUE;
            for (int j = 1; j <= nums2.length; j++) {
                int tmp = dp[j];
                dp[j] = Math.max(num * nums2[j - 1] + Math.max(pre, 0), Math.max(dp[j], dp[j-1]));
                pre = tmp;
            }
        }
        return dp[nums2.length];
    }

    public int maxDotProduct2(int[] nums1, int[] nums2) {
        int[][] cache = new int[nums1.length][nums2.length];
        for (int[] tmp: cache) Arrays.fill(tmp, Integer.MIN_VALUE);
        return dfs2(nums1.length-1, nums2.length-1, nums1, nums2, cache);
    }

    private int dfs2(int i, int j, int[] nums1, int[] nums2, int[][] cache) {
        if (i < 0 || j < 0) {
            return Integer.MIN_VALUE;
        }
        if (cache[i][j] != Integer.MIN_VALUE) return cache[i][j];
        int val1 = nums1[i] * nums2[j] + Math.max(dfs2(i - 1, j - 1, nums1, nums2, cache), 0);
        cache[i][j] = Math.max(val1, Math.max(dfs2(i - 1, j, nums1, nums2, cache), dfs2(i, j - 1, nums1, nums2, cache)));
        return cache[i][j];
    }


    public int maxDotProduct(int[] nums1, int[] nums2) {
        int minusVal = checkIsMinus(nums1, nums2);
        if (minusVal < 0) return minusVal;
        int[][] cache = new int[nums1.length][nums2.length];
        for (int[] tmp: cache) Arrays.fill(tmp, -1);
        return dfs(nums1.length-1, nums2.length-1, nums1, nums2, cache);
    }

    private int checkIsMinus(int[]nums1, int[] nums2) {
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        for (int num : nums1) {
            max1 = Math.max(max1, num);
            min1 = Math.min(min1, num);
        }
        for (int num : nums2){
            max2 = Math.max(max2, num);
            min2 = Math.min(min2, num);
        }

        if (max1 < 0 && min2 > 0) {
            return max1 * min2;
        }

        if (max2 < 0 && min1 > 0) {
            return max2 * min1;
        }
        return 0;
    }

    private int dfs(int i, int j, int[] nums1, int[] nums2, int[][] cache) {
        if (i < 0 || j < 0) {
            return 0;
        }
        if (cache[i][j] != -1) return cache[i][j];
        // 同号才能都选
        int val1 = Integer.MIN_VALUE;
        if((nums1[i] | Integer.MAX_VALUE) == (nums2[j] | Integer.MAX_VALUE)) {
            val1 = nums1[i] * nums2[j] + dfs(i - 1, j - 1, nums1, nums2, cache);
        }
        cache[i][j] = Math.max(val1, Math.max(dfs(i - 1, j, nums1, nums2, cache), dfs(i, j - 1, nums1, nums2, cache)));
        return cache[i][j];
    }

}
