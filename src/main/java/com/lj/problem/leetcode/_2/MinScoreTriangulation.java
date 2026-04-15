package com.lj.problem.leetcode._2;

import java.util.Arrays;

/**
 * 1039. 多边形三角剖分的最低得分
 * 提示
 * 你有一个凸的 n 边形，其每个顶点都有一个整数值。给定一个整数数组 values ，其中 values[i] 是按 顺时针顺序 第 i 个顶点的值。
 * 假设将多边形 剖分 为 n - 2 个三角形。对于每个三角形，该三角形的值是顶点标记的乘积，三角剖分的分数是进行三角剖分后所有 n - 2 个三角形的值之和。
 * 返回 多边形进行三角剖分后可以得到的最低分 。
 *
 * 提示：
 *
 * n == values.length
 * 3 <= n <= 50
 * 1 <= values[i] <= 100
 */
public class MinScoreTriangulation {


    public int minScoreTriangulation(int[] values) {
        int[][] cache = new int[values.length][values.length];
        return dfs(0, values.length-1, values, cache);
    }

    /**
     * 从 i ~ j, 顺时针枚举 k，分割子问题
     * @param i
     * @param j
     * @param values
     * @return
     */
    private int dfs(int i, int j, int[] values, int[][] cache) {
        if (i + 1 == j) {
            return 0;
        }
        if (cache[i][j] != 0) return cache[i][j];
        cache[i][j] = Integer.MAX_VALUE;
        for (int k = i + 1; k < j; k++) {
            cache[i][j] = Math.min(cache[i][j], dfs(i, k ,values, cache) + dfs(k, j, values, cache) + values[i] * values[j] * values[k]);
        }
        return cache[i][j];
    }

    /**
     * 这个递推跑的比上面慢（leetcode结果）
      * @param values
     * @return
     */
    public int minScoreTriangulation_(int[] values) {
        int[][] dp = new int[values.length][values.length];
        for (int i = values.length - 3; i >= 0; i--) {
            for (int j = i + 2; j < values.length; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + values[i] * values[k] * values[j]);
                }
            }
        }
        return dp[0][values.length-1];
    }
}
