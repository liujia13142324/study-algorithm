package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.Arrays;

/**
 * 1911. 最大交替子序列和
 * 中等
 * 提示
 * 一个下标从 0 开始的数组的 交替和 定义为 偶数 下标处元素之 和 减去 奇数 下标处元素之 和 。
 *
 * 比方说，数组 [4,2,5,3] 的交替和为 (4 + 5) - (2 + 3) = 4 。
 * 给你一个数组 nums ，请你返回 nums 中任意子序列的 最大交替和 （子序列的下标 重新 从 0 开始编号）。
 *
 * 一个数组的 子序列 是从原数组中删除一些元素后（也可能一个也不删除）剩余元素不改变顺序组成的数组。比方说，[2,7,4] 是 [4,2,3,7,2,1,4] 的一个子序列（加粗元素），但是 [2,4,2] 不是。
 *
 * 示例 1：
 * 输入：nums = [4,2,5,3]
 * 输出：7
 * 解释：最优子序列为 [4,2,5] ，交替和为 (4 + 5) - 2 = 7 。
 *
 * 示例 2：
 * 输入：nums = [5,6,7,8]
 * 输出：8
 * 解释：最优子序列为 [8] ，交替和为 8 。
 *
 * 示例 3：
 * 输入：nums = [6,2,1,2,4,5]
 * 输出：10
 * 解释：最优子序列为 [6,1,5] ，交替和为 (6 + 5) - 1 = 10 。
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 * ~s
 */
public class MaxAlternatingSum {

    @Test
    public void test() {
        System.out.println(maxAlternatingSum(new int[]{4,2,5,3}));
    }

    // 正推
    public long maxAlternatingSum3(int[] nums) {
        long [] dp = {0, Integer.MIN_VALUE/2};
        for (int num: nums) {
            long tmp = dp[0];
            dp[0] = Math.max(dp[0], dp[1] - num);
            dp[1] = Math.max(dp[1], tmp + num);
        }
        return dp[1];
    }

    // 倒推
    public long maxAlternatingSum2_(int[] nums) {
        long [] dp = new long[2];
        for (int i = nums.length - 1; i >= 0; i--) {
            long tmp = dp[0];
            dp[0] = Math.max(dp[0], dp[1] + nums[i]);
            dp[1] = Math.max(dp[1], tmp - nums[i]);
        }
        return dp[0];
    }

    public long maxAlternatingSum2(int[] nums) {
        long [][] cache = new long[2][nums.length];
        Arrays.fill(cache[0], Integer.MIN_VALUE / 2);
        Arrays.fill(cache[1], Integer.MIN_VALUE / 2);
        return dfs2(0, 0, nums, cache);
    }

    private long dfs2(int j, int i, int[] nums, long[][] cache) {
        if (i == nums.length) {
            return 0;
        }
        if (cache[j][i] != Integer.MIN_VALUE / 2) {
            return cache[j][i];
        }
        cache[j][i] = Math.max(dfs2(j , i + 1, nums, cache), (1 - 2 * j) * nums[i] + dfs2(j ^ 1, i + 1, nums, cache));
        return cache[j][i];
    }


    public long maxAlternatingSum_(int[] nums) {
        long [][] cache = new long[2][nums.length];
        Arrays.fill(cache[0], Integer.MIN_VALUE / 2);
        Arrays.fill(cache[1], Integer.MIN_VALUE / 2);
        return dfs_(0, 0, nums, cache);
    }

    private long dfs_(int j, int i, int[] nums, long[][] cache) {
        if (i == nums.length) {
            return 0;
        }
        if (cache[j][i] != Integer.MIN_VALUE / 2) {
            return cache[j][i];
        }
        for (int k = i; k < nums.length; k++) {
            int tmp = (1 - 2 * j) * nums[k];
            cache[j][i] = Math.max(cache[j][i], Math.max(tmp, tmp + dfs_(j^1, k + 1, nums, cache)));
        }
        return cache[j][i];
    }

    public long maxAlternatingSum(int[] nums) {
        return dfs(0, 0, nums);
    }

    private long dfs(int j, int i, int[] nums) {
        if (i == nums.length) {
            return 0;
        }
        long max = Integer.MIN_VALUE;
        for (int k = i; k < nums.length; k++) {
            int tmp = (1 - 2 * j) * nums[k];
            max = Math.max(max, Math.max(tmp, dfs(j^1, k + 1, nums) + tmp));
        }
        return max;
    }
}
