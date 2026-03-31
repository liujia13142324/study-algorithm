package com.lj.problem.leetcode._3;

import java.util.Arrays;

/**
 * 188. 买卖股票的最佳时机 IV
 * 困难
 * 给你一个整数数组 prices 和一个整数 k ，其中 prices[i] 是某支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。也就是说，你最多可以买 k 次，卖 k 次。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 *
 * 示例 1：
 * 输入：k = 2, prices = [2,4,1]
 * 输出：2
 * 解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。

 * 示例 2：
 * 输入：k = 2, prices = [3,2,6,5,0,3]
 * 输出：7
 * 解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 *      随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
 *
 * 提示：
 * 1 <= k <= 100
 * 1 <= prices.length <= 1000
 * 0 <= prices[i] <= 1000
 */
public class MaxProfit {

    public int maxProfit2(int k, int[] prices) {
        int[][][] dp = new int[2][k + 2][prices.length + 1];
        for (int i = 0; i < prices.length + 1; i++) {
            dp[0][0][i] = Integer.MIN_VALUE;
            dp[1][0][i] = Integer.MIN_VALUE;
        }
        for (int i = 0; i < k + 2; i++) {
            dp[1][i][0] = Integer.MIN_VALUE;
        }

        for (int j = 1; j < k + 2; j++) {
            for (int i = 1; i < prices.length + 1; i++) {
                dp[0][j][i] = Math.max(dp[0][j][i - 1], dp[1][j - 1][i - 1] + prices[i - 1]);
                dp[1][j][i] = Math.max(dp[1][j][i - 1], dp[0][j][i - 1] - prices[i - 1]);
            }
        }

        return dp[0][k + 1][prices.length];
    }


    public int maxProfit(int k, int[] prices) {
        int[][][] cache = new int[2][k + 1][prices.length];
        for (int[][] t1: cache) {
            for (int[] t2: t1) Arrays.fill(t2, Integer.MIN_VALUE);
        }

        return dfs(0, k, prices.length - 1, prices, cache);
    }

    private int dfs(int hold, int k, int i, int[] prices, int[][][] cache) {
        if (k < 0) return Integer.MIN_VALUE;
        if (i < 0) return hold == 0 ? 0 : Integer.MIN_VALUE;

        if (cache[hold][k][i] != Integer.MIN_VALUE) return cache[hold][k][i];

        if (hold == 0) {
            cache[hold][k][i] = Math.max(dfs(0, k, i-1, prices, cache), dfs(1, k-1, i-1, prices, cache) + prices[i]);
        }else {
            cache[hold][k][i] = Math.max(dfs(1, k, i-1, prices, cache), dfs(0, k, i-1, prices, cache) - prices[i]);
        }

        return cache[hold][k][i];
    }
}
