package com.lj.problem.leetcode._2;

import java.util.Arrays;

/**
 * 309. 买卖股票的最佳时机含冷冻期
 *
 * 给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格 。
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 示例 1:
 * 输入: prices = [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 *
 * 示例 2:
 * 输入: prices = [1]
 * 输出: 0
 *
 * 提示：
 * 1 <= prices.length <= 5000
 * 0 <= prices[i] <= 1000
 */
public class MaxProfit3 {


    public int maxProfit2(int[] prices) {
        int[] dp = new int[2];
        dp[1] = -prices[0];
        int pre = 0;
        for (int i = 1; i < prices.length; i++) {
            int tmp = dp[0];
            dp[0] = Math.max(dp[0], dp[1] + prices[i]);
            dp[1] = Math.max(dp[1], pre - prices[i]);
            pre = tmp;
        }

        return dp[0];
    }

    public int maxProfit(int[] prices) {
        int[][] cache = new int[2][prices.length];
        for (int[] tmp: cache) {
            Arrays.fill(tmp, Integer.MIN_VALUE);
        }

        return dfs(prices.length - 1, 0, prices, cache);
    }

    /**
     * 1  2  4
     * 0  1  3
     * -1 -1 -3
     *
     * 不含冷冻期
     * 1,  2,  3,  0,  2
     * 0   1   2   2   4
     * -1  -1  -1  2
     *
     * 含冷冻期
     * 1,  2,  3,  0,  2
     * 0   1   2   2   3
     * -1  -1  -1  1
     * @return
     */
    private int dfs(int i, int hold, int[] prices, int[][] cache) {
        if (i < 0) {
            return hold == 0 ? 0 : Integer.MIN_VALUE;
        }

        if (cache[hold][i] != Integer.MIN_VALUE) return cache[hold][i];

        if (hold == 0) {
            cache[0][i] = Math.max(dfs(i - 1, 0, prices, cache), dfs(i - 1, 1, prices, cache) + prices[i]);
        }else {
            cache[1][i] = Math.max(dfs(i - 1, 1, prices, cache), dfs(i - 2, 0, prices, cache) - prices[i]);
        }

        return cache[hold][i];
    }

}
