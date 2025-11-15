package com.lj.problem.leetcode._1;

import org.junit.Test;

import java.util.Arrays;

/**
 * 746. 使用最小花费爬楼梯
 * 简单
 * 给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶。
 *
 * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
 *
 * 请你计算并返回达到楼梯顶部的最低花费。
 *
 *
 *
 * 示例 1：
 *
 * 输入：cost = [10,15,20]
 * 输出：15
 * 解释：你将从下标为 1 的台阶开始。
 * - 支付 15 ，向上爬两个台阶，到达楼梯顶部。
 * 总花费为 15 。
 * 示例 2：
 *
 * 输入：cost = [1,100,1,1,1,100,1,1,100,1]
 * 输出：6
 * 解释：你将从下标为 0 的台阶开始。
 * - 支付 1 ，向上爬两个台阶，到达下标为 2 的台阶。
 * - 支付 1 ，向上爬两个台阶，到达下标为 4 的台阶。
 * - 支付 1 ，向上爬两个台阶，到达下标为 6 的台阶。
 * - 支付 1 ，向上爬一个台阶，到达下标为 7 的台阶。
 * - 支付 1 ，向上爬两个台阶，到达下标为 9 的台阶。
 * - 支付 1 ，向上爬一个台阶，到达楼梯顶部。
 * 总花费为 6 。
 *
 *
 * 提示：
 *
 * 2 <= cost.length <= 1000
 * 0 <= cost[i] <= 999
 */
public class MinCostClimbingStairs {

    @Test
    public void test() {
        System.out.println(minCostClimbingStairs2(new int[]{10, 15, 20}));
    }


    public int minCostClimbingStairs6(int[] cost) {
        int n = cost.length;
        int[] f = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            f[i] = Math.min(f[i - 1] + cost[i - 1], f[i - 2] + cost[i - 2]);
        }
        return f[n];
    }

    public int minCostClimbingStairs5_1(int[] cost) {
        int[] cache = new int[cost.length + 1];
        Arrays.fill(cache, -1);
        return dfs2(cost.length, cost, cache);
    }

    private int dfs2(int i, int[] cost, int[] cache) {
        if (i <= 1) return 0;
        if (cache[i] != -1) return cache[i];
        cache[i] = Math.min(cost[i - 1] + dfs2(i - 1, cost, cache), cost[i - 2] + dfs2(i - 2, cost, cache));
        return cache[i];
    }

    public int minCostClimbingStairs5(int[] cost) {
        return dfs2(cost.length, cost);
    }

    private int dfs2(int i, int[] cost) {
        if (i <= 1) return 0;
        return Math.min(cost[i - 1] + dfs2(i - 1, cost), cost[i - 2] + dfs2(i - 2, cost));
    }


    public int minCostClimbingStairs4(int[] cost) {
        int[] dfs = new int[cost.length];
        dfs[0] = cost[0];
        dfs[1] = cost[1];

        for (int i = 2; i < cost.length; i++) {
            dfs[i] = Math.min(cost[i] + dfs[i-1], cost[i] + dfs[i-2]);
        }
        return Math.min(dfs[cost.length-1], dfs[cost.length-2]);
    }


    public int minCostClimbingStairs3(int[] cost) {
        int[] cache = new int[cost.length];
        Arrays.fill(cache, -1);
        return Math.min(dfs(cost.length - 1, cost, cache), dfs(cost.length - 2, cost, cache));
    }

    private int dfs(int i, int[] cost, int[] cache) {
        if (i < 0) {
            return 0;
        }

        if (cache[i] != -1) {
            return cache[i];
        }

        int ans = cost[i] + dfs(i - 1, cost, cache);
        if (i > 0) {
            ans = Math.min(ans, cost[i] + dfs(i - 2, cost, cache));
        }

        cache[i] = ans;

        return ans;
    }


    public int minCostClimbingStairs2(int[] cost) {
        return Math.min(dfs(cost.length - 1, cost), dfs(cost.length - 2, cost));
    }

    private int dfs(int i, int[] cost) {
        if (i < 0) {
            return 0;
        }

        int ans = cost[i] + dfs(i - 1, cost);
        if (i > 0) {
            ans = Math.min(ans, cost[i] + dfs(i - 2, cost));
        }
        return ans;
    }

    public int minCostClimbingStairs(int[] cost) {
        return dfs(cost.length - 1, 1, cost);
    }


    private int dfs(int i, int len, int[] cost) {
        if (i < 0) {
            return 0;
        }

        if (len == 1) {
            return Math.min(cost[i] + dfs(i - 1, 1, cost), dfs(i - 1, 0, cost));
        }else {
            return cost[i] + dfs(i - 1, 1, cost);
        }
    }




}
