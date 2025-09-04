package com.lj.problem.leetcode._2;

import org.junit.Test;

/**
 * 3652. 按策略买卖股票的最佳时机
 *
 * 给你两个整数数组 prices 和 strategy，其中：
 * prices[i] 表示第 i 天某股票的价格。
 * strategy[i] 表示第 i 天的交易策略，其中：
 * -1 表示买入一单位股票。
 * 0 表示持有股票。
 * 1 表示卖出一单位股票。
 *
 * 同时给你一个 偶数 整数 k，你可以对 strategy 进行 最多一次 修改。一次修改包括：
 * 选择 strategy 中恰好 k 个 连续 元素。
 * 将前 k / 2 个元素设为 0（持有）。
 * 将后 k / 2 个元素设为 1（卖出）。
 * 利润 定义为所有天数中 strategy[i] * prices[i] 的 总和 。
 *
 * 返回你可以获得的 最大 可能利润。
 *
 * 注意： 没有预算或股票持有数量的限制，因此所有买入和卖出操作均可行，无需考虑过去的操作。
 *
 * 示例 1：
 * 输入： prices = [4,2,8], strategy = [-1,0,1], k = 2
 * 输出： 10
 * 解释：
 * 修改	策略	利润计算	利润
 * 原始	[-1, 0, 1]	(-1 × 4) + (0 × 2) + (1 × 8) = -4 + 0 + 8	4
 * 修改 [0, 1]	[0, 1, 1]	(0 × 4) + (1 × 2) + (1 × 8) = 0 + 2 + 8	10
 * 修改 [1, 2]	[-1, 0, 1]	(-1 × 4) + (0 × 2) + (1 × 8) = -4 + 0 + 8	4
 * 因此，最大可能利润是 10，通过修改子数组 [0, 1] 实现。
 *
 * 示例 2：
 * 输入： prices = [5,4,3], strategy = [1,1,0], k = 2
 * 输出： 9
 * 解释：
 * 修改	策略	利润计算	利润
 * 原始	[1, 1, 0]	(1 × 5) + (1 × 4) + (0 × 3) = 5 + 4 + 0	9
 * 修改 [0, 1]	[0, 1, 0]	(0 × 5) + (1 × 4) + (0 × 3) = 0 + 4 + 0	4
 * 修改 [1, 2]	[1, 0, 1]	(1 × 5) + (0 × 4) + (1 × 3) = 5 + 0 + 3	8
 * 因此，最大可能利润是 9，无需任何修改即可达成。
 *
 *
 * 提示：
 * 2 <= prices.length == strategy.length <= 105
 * 1 <= prices[i] <= 105
 * -1 <= strategy[i] <= 1
 * 2 <= k <= prices.length
 * k 是偶数
 */
public class MaxProfit {

    @Test
    public void test() {
        System.out.println(maxProfit(new int[]{4,2,8}, new int[]{-1,0,1}, 2));
        System.out.println(maxProfit(new int[]{5,4,3}, new int[]{1,1,0}, 2));
        System.out.println(maxProfit(new int[]{5,8}, new int[]{-1,-1}, 2));
        System.out.println(maxProfit(new int[]{8,5}, new int[]{0,0}, 2));
        System.out.println(maxProfit(new int[]{5,14,16,9}, new int[]{-1,0,0,-1}, 2));

        System.out.println(maxProfit2(new int[]{4,2,8}, new int[]{-1,0,1}, 2));
        System.out.println(maxProfit2(new int[]{5,4,3}, new int[]{1,1,0}, 2));
        System.out.println(maxProfit2(new int[]{5,8}, new int[]{-1,-1}, 2));
        System.out.println(maxProfit2(new int[]{8,5}, new int[]{0,0}, 2));
        System.out.println(maxProfit2(new int[]{5,14,16,9}, new int[]{-1,0,0,-1}, 2));
    }

    // 增量计算滑窗
    public long maxProfit2(int[] prices, int[] strategy, int k) {
        long total = 0, tmpMax = 0, max;
        int r = 0, l = 0;
        while (r < k) {
            total += (long) prices[r] * strategy[r];
            if (r <= k / 2 - 1) {
                tmpMax -= (long) prices[r] * strategy[r];
            }else if (r >= k / 2) {
                tmpMax += (long) prices[r] * -(strategy[r] - 1);
            }
            r++;
        }
        max = Math.max(tmpMax, 0);
        while (r < prices.length) {
            total += (long) prices[r] * strategy[r];
            // 最右边 1
            tmpMax += (long) prices[r] * -(strategy[r] - 1);
            tmpMax += (long) prices[l] * strategy[l];
            tmpMax -= prices[(r+l+1)/2];
            max = Math.max(tmpMax, max);
            l++;
            r++;
        }
        return total + max;
    }

    // 增量计算滑窗
    public long maxProfit(int[] prices, int[] strategy, int k) {
        long ans = 0;
        for (int i = 0; i < prices.length; i++) {
            ans += (long) prices[i] * strategy[i];
        }
        int r = 0, l = 0;
        long sum = ans;
        while (r < k) {
            if (r - l <= k / 2 - 1 && strategy[r] != 0) {
                sum -= (long) prices[r] * strategy[r];
            }else if (r - l >= k / 2 && strategy[r] != 1) {
                sum += (long) prices[r] * (Math.abs(strategy[r]) + 1);
            }
            r++;
        }
        ans = Math.max(ans, sum);
        while (r < strategy.length) {
            // 最右边 1
            if (strategy[r] != 1) {
                sum += (long) prices[r] * (Math.abs(strategy[r]) + 1);
            }
            // 恢复老 l
            if (strategy[l] != 0) {
                sum += (long) prices[l] * strategy[l];
            }
            // 中间的 1->0
            sum -= prices[(r+l+1)/2];
            ans = Math.max(sum, ans);
            r++;
            l++;
        }
        return ans;
    }

}
