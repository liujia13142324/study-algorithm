package com.lj.problem.leetcode._3;

import org.junit.Test;

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

    @Test
    public void test1() {
        maxProfit2(2, new int[]{2,4,1});
    }

    /**
     * debug 一下 maxProfit2(2, new int[]{2,4,1}); 就知道了
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit3(int k, int[] prices) {
        int[] hold = new int[prices.length + 1];
        Arrays.fill(hold, Integer.MIN_VALUE);
        int ans = 0;
        int pre;

        // 遍历的方向和状态转移的方向相同，每次遍历一轮后，要重新初始化“零值”
        for (int j = 1; j < k + 2; j++) {
            ans = 0;
            pre = Integer.MIN_VALUE;
            for (int i = 1; i < prices.length + 1; i++) {
                int tmp = ans;
                ans = Math.max(ans, pre + prices[i - 1]);
                pre = hold[i];
                hold[i] = Math.max(hold[i-1], tmp - prices[i - 1]);
            }
        }
        return ans;
    }


    public int maxProfit3_1(int k, int[] prices) {
        int[][] dp = new int[2][k + 2];
        for (int i = 0; i < k + 2; i++) {
            dp[1][i] = Integer.MIN_VALUE;
        }

        // 这种遍历的方向和状态转移的方向应该要相反，才能直接这么写，
        for (int i = 1; i < prices.length + 1; i++) {
            for (int j = k + 1; j >= 1; j--) {
                dp[1][j] = Math.max(dp[1][j], dp[0][j] - prices[i - 1]);
                dp[0][j] = Math.max(dp[0][j], dp[1][j - 1] + prices[i - 1]);
            }
        }

        return dp[0][k + 1];
    }


    public int maxProfit2(int k, int[] prices) {
        int[][][] dp = new int[2][k + 2][prices.length + 1];
        for (int i = 0; i < prices.length + 1; i++) {
            // 这样可以不要，debug 一下 maxProfit2(2, new int[]{2,4,1}); 就知道了
//            dp[0][0][i] = Integer.MIN_VALUE;
            dp[1][0][i] = Integer.MIN_VALUE;
        }
        for (int i = 0; i < k + 2; i++) {
            dp[1][i][0] = Integer.MIN_VALUE;
        }

        // 这一块按行遍历，还是按列遍历都是可以的，
        // 下面这种是按照行遍历，遍历顺序和状态转移的顺序相同，状态从左上（“上依赖”仅限hold=1数组，dp[0][j][i] = Math.max(dp[0][j][i - 1], dp[1][j - 1][i - 1] + prices[i - 1]);）往右传递，只要左上初始值正确即可
        // 如果按照列遍历，遍历顺序和状态转移的顺序相反，状态也是从左上往右传递，只要左上初始值正确即可
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
