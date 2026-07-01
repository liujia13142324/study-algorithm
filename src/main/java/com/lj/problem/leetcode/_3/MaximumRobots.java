package com.lj.problem.leetcode._3;

import org.junit.Test;

/**
 * 2398. 预算内的最多机器人数目
 * 困难
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 你有 n 个机器人，给你两个下标从 0 开始的整数数组 chargeTimes 和 runningCosts ，两者长度都为 n 。第 i 个机器人充电时间为 chargeTimes[i] 单位时间，花费 runningCosts[i] 单位时间运行。再给你一个整数 budget 。
 *
 * 运行 k 个机器人 总开销 是 max(chargeTimes) + k * sum(runningCosts) ，其中 max(chargeTimes) 是这 k 个机器人中最大充电时间，sum(runningCosts) 是这 k 个机器人的运行时间之和。
 *
 * 请你返回在 不超过 budget 的前提下，你 最多 可以运行的 连续 的机器人数目为多少。
 *
 *
 *
 * 示例 1：
 *
 * 输入：chargeTimes = [3,6,1,3,4], runningCosts = [2,1,3,4,5], budget = 25
 * 输出：3
 * 解释：
 * 可以在 budget 以内运行所有单个机器人或者连续运行 2 个机器人。
 * 选择前 3 个机器人，可以得到答案最大值 3 。总开销是 max(3,6,1) + 3 * sum(2,1,3) = 6 + 3 * 6 = 24 ，小于 25 。
 * 可以看出无法在 budget 以内连续运行超过 3 个机器人，所以我们返回 3 。
 * 示例 2：
 *
 * 输入：chargeTimes = [11,12,19], runningCosts = [10,8,7], budget = 19
 * 输出：0
 * 解释：即使运行任何一个单个机器人，还是会超出 budget，所以我们返回 0 。
 *
 *
 * 提示：
 * chargeTimes.length == runningCosts.length == n
 * 1 <= n <= 5 * 104
 * 1 <= chargeTimes[i], runningCosts[i] <= 105
 * 1 <= budget <= 1015
 */
public class MaximumRobots {

    @Test
    public void test() {
//        System.out.println(maximumRobots(new int[]{11,12,19}, new int[]{10,8,7}, 19));
//        System.out.println(maximumRobots(new int[]{3,6,1,3,4}, new int[]{2,1,3,4,5}, 25));
        System.out.println(maximumRobots(new int[]{19,63,21,8,5,46,56,45,54,30,92,63,31,71,87,94,67,8,19,89,79,25}
                , new int[]{91,92,39,89,62,81,33,99,28,99,86,19,5,6,19,94,65,86,17,10,8,42}, 85));
    }

    public int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {
        long[] sum = new long[runningCosts.length + 1];
        for (int i = 1; i <= runningCosts.length; i++) {
            sum[i] = sum[i - 1] + runningCosts[i - 1];
        }

        int[] queue = new int[chargeTimes.length];
        int head = 0, tail = -1;
        int l = 0;
        int ans = 0;
        for (int i = 0; i < chargeTimes.length; i++) {
            while (head <= tail && chargeTimes[queue[tail]] <= chargeTimes[i]) {
                tail--;
            }
            queue[++tail] = i;

            while (l <= i && chargeTimes[queue[head]] + (long) (i - l + 1) * (sum[i + 1] - sum[l]) > budget) {
                if (l == queue[head]) {
                    head++;
                }
                l++;
            }
            ans = Math.max(ans, i - l + 1);
        }
        return ans;
    }
}
