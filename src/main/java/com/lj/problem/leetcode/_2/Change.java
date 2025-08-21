package com.lj.problem.leetcode._2;

import org.junit.Test;

/**
 * 518. 零钱兑换 II
 * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
 * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
 * 假设每一种面额的硬币有无限个。
 * 题目数据保证结果符合 32 位带符号整数。
 *
 *
 *
 * 示例 1：
 * 输入：amount = 5, coins = [1, 2, 5]
 * 输出：4
 * 解释：有四种方式可以凑成总金额：
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 *
 * 示例 2：
 * 输入：amount = 3, coins = [2]
 * 输出：0
 * 解释：只用面额 2 的硬币不能凑成总金额 3 。
 *
 * 示例 3：
 * 输入：amount = 10, coins = [10]
 * 输出：1
 *
 * 提示：
 * 1 <= coins.length <= 300
 * 1 <= coins[i] <= 5000
 * coins 中的所有值 互不相同
 * 0 <= amount <= 5000
 */
public class Change {

    @Test
    public void test() {
        System.out.println(change(5, new int[]{1, 2, 5}));
        System.out.println(change(3, new int[]{2}));
        System.out.println(change(10, new int[]{10}));
        System.out.println();
        System.out.println(change3(5, new int[]{1, 2, 5}));
        System.out.println(change3(3, new int[]{2}));
        System.out.println(change3(10, new int[]{10}));
    }

    public int change3(int amount, int[] coins) {
        int[] f = new int[amount+1];
        f[0] = 1;
        for (int coin : coins) {
            for (int j = 1; j <= amount; j++) {
                if (j >= coin) {
                    f[j] += f[j - coin];
                }
            }
        }
        return f[amount];
    }

    public int change2(int amount, int[] coins) {
        Integer[][] f = new Integer[coins.length][amount+1];
        return dfs2(coins, amount, coins.length - 1, f);
    }

    private int dfs2(int[] coins, int amount, int i, Integer[][] f) {
        if (amount == 0) {return 1;}
        if (i < 0) return 0;
        if (f[i][amount] != null) return f[i][amount];
        if (coins[i] > amount) {
            f[i][amount] = dfs2(coins, amount, i-1, f);
            return f[i][amount];
        }
        f[i][amount] = dfs2(coins, amount, i-1, f) + dfs2(coins, amount - coins[i], i, f);
        return f[i][amount];
    }

    public int change(int amount, int[] coins) {
        return dfs(coins, amount, coins.length - 1);
    }

    private int dfs(int[] coins, int amount, int i) {
        if (amount == 0) {return 1;}
        if (i < 0) return 0;
        if (coins[i] > amount) return dfs(coins, amount, i-1);
        return dfs(coins, amount, i-1) + dfs(coins, amount - coins[i], i);
    }

}
