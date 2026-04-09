package com.lj.problem.leetcode._3;

import java.util.Arrays;

/**
 * 123. 买卖股票的最佳时机 III
 * 困难
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 *
 * 示例 1:
 * 输入：prices = [3,3,5,0,0,3,1,4]
 * 输出：6
 * 解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 *      随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 * 示例 2：
 * 输入：prices = [1,2,3,4,5]
 * 输出：4
 * 解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例 3：
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这个情况下, 没有交易完成, 所以最大利润为 0。
 * 示例 4：
 *
 * 输入：prices = [1]
 * 输出：0
 *
 * 提示：
 * 1 <= prices.length <= 105
 * 0 <= prices[i] <= 105
 */
public class MaxProfit2 {

    public int maxProfit(int[] prices) {
        return maxProfit3(2, prices);
    }

    private int maxProfit3(int k, int[] prices) {
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

    private int maxProfit3_1(int k, int[] prices) {
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

}
