package com.lj.problem.leetcode._2;

import java.util.Arrays;

/**
 * 122. 买卖股票的最佳时机 II
 * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
 *
 * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。然而，你可以在 同一天 多次买卖该股票，但要确保你持有的股票不超过一股。
 *
 * 返回 你能获得的 最大 利润 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：prices = [7,1,5,3,6,4]
 * 输出：7
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4。
 * 随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6 - 3 = 3。
 * 最大总利润为 4 + 3 = 7 。
 * 示例 2：
 *
 * 输入：prices = [1,2,3,4,5]
 * 输出：4
 * 解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4。
 * 最大总利润为 4 。
 * 示例 3：
 *
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这种情况下, 交易无法获得正利润，所以不参与交易可以获得最大利润，最大利润为 0。
 *
 *
 * 提示：
 *
 * 1 <= prices.length <= 3 * 104
 * 0 <= prices[i] <= 104
 */
public class MaxProfit2 {

    public int maxProfit2__(int[] prices) {
        int[][] dp = new int[2][prices.length];
        dp[1][0] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            dp[0][i] = Math.max(dp[1][i-1] + prices[i], dp[0][i-1]);
            dp[1][i] = Math.max(dp[1][i-1], dp[0][i-1] - prices[i]);
        }
        return dp[0][prices.length-1];
    }

    public int maxProfit2_(int[] prices) {
        int[][] cache = new int[2][prices.length];
        // 用 -1 会误判，因为 -1 也是合法利润
        for (int [] c: cache) Arrays.fill(c, Integer.MIN_VALUE);
        return dfs(prices.length - 1, 0, prices, cache);
    }

    private int dfs(int i, int hold, int[] prices, int[][] cache) {
        if (i == 0) {
            return hold == 0 ? 0 : -prices[i];
        }

        if (cache[hold][i] != Integer.MIN_VALUE) return cache[hold][i];

        if (hold == 0) {
            cache[0][i] = Math.max(dfs(i - 1, 1, prices, cache) + prices[i], dfs(i - 1, 0, prices, cache));
        }else {
            cache[1][i] = Math.max(dfs(i - 1, 1, prices, cache), dfs(i - 1, 0, prices, cache) - prices[i]);
        }
        return cache[hold][i];
    }

    public int maxProfit2(int[] prices) {
        return dfs(prices.length - 1, 0, prices);
    }

    private int dfs(int i, int hold, int[] prices) {
        if (i == 0) {
            return hold == 0 ? 0 : -prices[i];
        }
        if (hold == 0) {
            return Math.max(dfs(i - 1, 1, prices) + prices[i], dfs(i - 1, 0, prices));
        }else {
            return Math.max(dfs(i - 1, 1, prices), dfs(i - 1, 0, prices) - prices[i]);
        }
    }



        public int maxProfit(int[] prices) {
        return Math.max(dfs(1, 0, 0, prices), dfs(1, 1, -prices[0], prices));
    }

    private int dfs(int i, int hold, int sum, int[] prices) {
        if (i == prices.length) {
            return sum;
        }
        if (hold == 0) {
            return Math.max(dfs(i + 1, 0, sum, prices), dfs(i + 1, 1, sum - prices[i], prices));
        }else {
            return Math.max(dfs(i + 1, 0, sum + prices[i], prices), dfs(i + 1, 1, sum, prices));
        }
    }
}
