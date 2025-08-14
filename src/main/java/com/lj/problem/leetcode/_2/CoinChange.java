package com.lj.problem.leetcode._2;


import org.junit.Test;

import java.util.Arrays;

/**
 * 322. 零钱兑换
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * 你可以认为每种硬币的数量是无限的。
 *
 *
 *
 * 示例 1：
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 *
 * 示例 2：
 * 输入：coins = [2], amount = 3
 * 输出：-1
 *
 * 示例 3：
 * 输入：coins = [1], amount = 0
 * 输出：0
 *
 * 提示：
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 231 - 1
 * 0 <= amount <= 104
 */
public class CoinChange {

    @Test
    public void test1() {
        System.out.println(coinChange(new int[]{1,2,5}, 11));
        System.out.println(coinChange(new int[]{2}, 3));
        System.out.println(coinChange(new int[]{1}, 0));
        System.out.println(coinChange(new int[]{186,419,83,408}, 6249));
        System.out.println(coinChange2(new int[]{186,419,83,408}, 6249));
    }

    public int coinChange2(int[] coins, int amount) {

        if (amount == 0) return 0;

        Integer[][][] f = new Integer[coins.length][amount+1][amount+1];

        return dfs2(coins, coins.length-1, amount, 0, f);
    }

    private int dfs2(int[] coins, int i, int amount, int step, Integer[][][] f) {
        if (amount == 0) {
            return step;
        }

        if (i < 0) {
            return -1;
        }

        if (f[i][amount][step] != null) {
            return f[i][amount][step];
        }

        if (coins[i] > amount) {
            f[i][amount][step] = dfs2(coins, i - 1, amount, step, f);
            return f[i][amount][step];
        }

        int v1 = dfs2(coins, i, amount - coins[i], step+1, f);
        int v2 = dfs2(coins, i-1, amount, step, f);

        if (v1 < 0 && v2 < 0) {
            f[i][amount][step] = -1;
            return -1;
        }

        if (v1 < 0) {
            f[i][amount][step] = v2;
            return v2;
        }

        if (v2 < 0) {
            f[i][amount][step] = v1;
            return v1;
        }

        f[i][amount][step] = Math.min(v1, v2);
        return f[i][amount][step];
    }


    public int coinChange(int[] coins, int amount) {

        if (amount == 0) return 0;

        return dfs1(coins, coins.length-1, amount, 0);
    }

    private int dfs1(int[] coins, int i, int amount, int step) {
        if (amount == 0) {
            return step;
        }

        if (i < 0) {
            return -1;
        }

        if (coins[i] > amount) {
            return dfs1(coins, i-1, amount, step);
        }

        int v1 = dfs1(coins, i, amount - coins[i], step+1);
        int v2 = dfs1(coins, i-1, amount, step);

        if (v1 < 0 && v2 < 0) {
            return -1;
        }

        if (v1 < 0) {
            return v2;
        }

        if (v2 < 0) {
            return v1;
        }

        return Math.min(v1, v2);
    }


}
