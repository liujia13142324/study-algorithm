package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.Arrays;

/**
 * 2786. 访问数组中的位置使分数最大
 * 给你一个下标从 0 开始的整数数组 nums 和一个正整数 x 。
 * 你 一开始 在数组的位置 0 处，你可以按照下述规则访问数组中的其他位置：
 *
 * 如果你当前在位置 i ，那么你可以移动到满足 i < j 的 任意 位置 j 。
 * 对于你访问的位置 i ，你可以获得分数 nums[i] 。
 * 如果你从位置 i 移动到位置 j 且 nums[i] 和 nums[j] 的 奇偶性 不同，那么你将失去分数 x 。
 * 请你返回你能得到的 最大 得分之和。
 *
 * 注意 ，你一开始的分数为 nums[0] 。
 *
 * 示例 1：
 * 输入：nums = [2,3,6,1,9,2], x = 5
 * 输出：13
 * 解释：我们可以按顺序访问数组中的位置：0 -> 2 -> 3 -> 4 。
 * 对应位置的值为 2 ，6 ，1 和 9 。因为 6 和 1 的奇偶性不同，所以下标从 2 -> 3 让你失去 x = 5 分。
 * 总得分为：2 + 6 + 1 + 9 - 5 = 13 。
 *
 * 示例 2：
 * 输入：nums = [2,4,6,8], x = 3
 * 输出：20
 * 解释：数组中的所有元素奇偶性都一样，所以我们可以将每个元素都访问一次，而且不会失去任何分数。
 * 总得分为：2 + 4 + 6 + 8 = 20 。
 *
 * 提示：
 *
 * 2 <= nums.length <= 105
 * 1 <= nums[i], x <= 106
 */
public class MaxScore2 {

    @Test
    public void test() {
        System.out.println(maxScore(new int[]{8,50,65,85,8,73,55,50,29,95,5,68,52,79}, 74));
    }

    public long maxScore_1(int[] nums, int x) {
       long[] dp = new long [2];
       int parity = nums[0] & 1;
        dp[parity] = nums[0];
        dp[parity ^ 1] = nums[0] - x;
        for (int i = 1; i < nums.length; i++) {
            if ((nums[i] & 1) == 0) {
                dp[0] = Math.max(dp[0] + nums[i], dp[1] + nums[i] - x);
            }else {
                dp[1] = Math.max(dp[1] + nums[i], dp[0] + nums[i] - x);
            }
        }

        return Math.max(dp[0], dp[1]);
    }

    public long maxScore(int[] nums, int x) {
        long [][]cache = new long[2][nums.length];
        Arrays.fill(cache[0], Integer.MIN_VALUE / 2);
        Arrays.fill(cache[1], Integer.MIN_VALUE / 2);
        return Math.max(dfs(x, 0, nums.length - 1, nums, cache), dfs(x, 1, nums.length - 1, nums, cache));
    }

    private long dfs(int x, int j, int i, int[] nums, long[][] cache) {
        if (i == 0) {
            return (nums[i] & 1) == j ? nums[i] : nums[i] - x;
        }

        if (cache[j][i] != Integer.MIN_VALUE / 2) {
            return cache[j][i];
        }

        if (j != (nums[i] & 1)) {
            cache[j][i] = dfs(x, j, i - 1, nums, cache);
        }else {
            cache[j][i] = Math.max(dfs(x, j, i - 1, nums, cache) + nums[i], dfs(x, j^1, i - 1, nums, cache) + nums[i] - x);
        }

        return cache[j][i];
    }
}
