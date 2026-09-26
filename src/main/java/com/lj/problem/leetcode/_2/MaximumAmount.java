package com.lj.problem.leetcode._2;


import java.util.Arrays;

/**
 * 3418. 机器人可以获得的最大金币数
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个 m x n 的网格。一个机器人从网格的左上角 (0, 0) 出发，目标是到达网格的右下角 (m - 1, n - 1)。在任意时刻，机器人只能向右或向下移动。
 *
 * 网格中的每个单元格包含一个值 coins[i][j]：
 *
 * 如果 coins[i][j] >= 0，机器人可以获得该单元格的金币。
 * 如果 coins[i][j] < 0，机器人会遇到一个强盗，强盗会抢走该单元格数值的 绝对值 的金币。
 * 机器人有一项特殊能力，可以在行程中 最多感化 2个单元格的强盗，从而防止这些单元格的金币被抢走。
 *
 * 注意：机器人的总金币数可以是负数。
 *
 * 返回机器人在路径上可以获得的 最大金币数 。
 *
 *
 *
 * 示例 1：
 *
 * 输入： coins = [[0,1,-1],[1,-2,3],[2,-3,4]]
 *
 * 输出： 8
 *
 * 解释：
 *
 * 一个获得最多金币的最优路径如下：
 *
 * 从 (0, 0) 出发，初始金币为 0（总金币 = 0）。
 * 移动到 (0, 1)，获得 1 枚金币（总金币 = 0 + 1 = 1）。
 * 移动到 (1, 1)，遇到强盗抢走 2 枚金币。机器人在此处使用一次感化能力，避免被抢（总金币 = 1）。
 * 移动到 (1, 2)，获得 3 枚金币（总金币 = 1 + 3 = 4）。
 * 移动到 (2, 2)，获得 4 枚金币（总金币 = 4 + 4 = 8）。
 * 示例 2：
 *
 * 输入： coins = [[10,10,10],[10,10,10]]
 *
 * 输出： 40
 *
 * 解释：
 *
 * 一个获得最多金币的最优路径如下：
 *
 * 从 (0, 0) 出发，初始金币为 10（总金币 = 10）。
 * 移动到 (0, 1)，获得 10 枚金币（总金币 = 10 + 10 = 20）。
 * 移动到 (0, 2)，再获得 10 枚金币（总金币 = 20 + 10 = 30）。
 * 移动到 (1, 2)，获得 10 枚金币（总金币 = 30 + 10 = 40）。
 *
 *
 * 提示：
 *
 * m == coins.length
 * n == coins[i].length
 * 1 <= m, n <= 500
 * -1000 <= coins[i][j] <= 1000
 */
public class MaximumAmount {

    public int maximumAmount3(int[][] coins) {
        int m = coins.length;
        int n = coins[0].length;
        int[][][] dp = new int[3][m + 1][n + 1];

        for (int k = 0; k < 3; k++) {
            Arrays.fill(dp[k][0], Integer.MIN_VALUE);
            for (int i = 2; i <= m; i++) {
                dp[k][i][0] = Integer.MIN_VALUE;
            }
        }

        for (int k = 0; k < 3; k++) {
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    dp[k][i][j] = Math.max(dp[k][i - 1][j], dp[k][i][j - 1]) + coins[i - 1][j - 1];
                    if (k > 0 && coins[i - 1][j - 1] < 0) {
                        dp[k][i][j] = Math.max(dp[k][i][j], Math.max(dp[k - 1][i - 1][j], dp[k - 1][i][j - 1]));
                    }
                }
            }
        }

        return dp[2][m][n];
    }


    public int maximumAmount2(int[][] coins) {
        int m = coins.length;
        int n = coins[0].length;
        int[][][] dp = new int[m][n][3];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], Integer.MIN_VALUE);
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0 ; j < n; j++) {
                for (int k = 0; k < 3; k++) {
                    if (i > 0) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j][k]);
                    }
                    if (j > 0) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i][j - 1][k]);
                    }
                    dp[i][j][k] = (dp[i][j][k] == Integer.MIN_VALUE ? 0 : dp[i][j][k]) + coins[i][j];

                    if (k > 0 && coins[i][j] < 0) {
                        int tmp = Integer.MIN_VALUE;
                        if (i > 0) {
                            tmp  = Math.max(tmp, dp[i - 1][j][k - 1]);
                        }
                        if (j > 0) {
                            tmp = Math.max(tmp, dp[i][j - 1][k - 1]);
                        }
                        dp[i][j][k] = Math.max(dp[i][j][k], tmp == Integer.MIN_VALUE ? 0 : tmp);
                    }
                }
            }
        }

        return dp[m-1][n-1][2];
    }


    public int maximumAmount(int[][] coins) {
        int[][][] cache = new int[3][coins.length][coins[0].length];
        for (int[][] c: cache) {
            for (int[] c2: c) {
                Arrays.fill(c2, Integer.MIN_VALUE);
            }
        }
        return dfs(coins.length - 1, coins[0].length - 1, 2, coins, cache);
    }

    private int dfs(int i, int j, int k, int[][] coins, int[][][] cache) {

        if (i < 0 || j < 0) {
            return Integer.MIN_VALUE;
        }

        if (cache[k][i][j] != Integer.MIN_VALUE) {
            return cache[k][i][j];
        }

        System.out.println(i + " " + j + " " + k);

        int val = coins[i][j];
        int ans = Integer.MIN_VALUE;

        if (val < 0 && k > 0) {
            // 使用感化能力
            int mx = Math.max(dfs(i - 1, j, k - 1, coins, cache), dfs(i, j - 1, k - 1, coins, cache));
            ans = mx == Integer.MIN_VALUE ? 0 : mx;
        }

        // 不使用感化能力
        int mx = Math.max(dfs(i - 1, j, k, coins, cache), dfs(i, j - 1, k, coins, cache));

        return cache[k][i][j] = Math.max(ans, (mx == Integer.MIN_VALUE ? 0 : mx) + coins[i][j]);
    }

    public static void main(String[] args) {
        /*String s = IoUtil.read(MaximumAmount.class.getResourceAsStream("/longtext"), "utf8");
        JSONArray arr = JSONArray.parseArray(s);
        int[][] tmp = new int[arr.size()][];
        for (int i = 0; i < arr.size(); i++) {
            JSONArray jsonArray = arr.getJSONArray(i);
            tmp[i] = new int[jsonArray.size()];
            for (int j = 0; j < jsonArray.size(); j++) {
                tmp[i][j] = jsonArray.getInteger(j);
            }
        }*/


        int[][] tmp  = new int[][]{
                {0,-1,1}
                ,{1,-2,-3}
                ,{2,-3,4}
                ,{2,5,4}
        };
        new MaximumAmount().maximumAmount3(tmp);
    }
}
