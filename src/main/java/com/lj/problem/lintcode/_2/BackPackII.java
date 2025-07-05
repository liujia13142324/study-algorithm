package com.lj.problem.lintcode._2;

/**
 * 有 n 个物品和一个大小为 m 的背包. 给定数组 A 表示每个物品的大小和数组 V 表示每个物品的价值.
 *
 * 问最多能装入背包的总价值是多大?
 *
 * A[i], V[i], n, m 均为整数
 * 你不能将物品进行切分
 * 你所挑选的要装入背包的物品的总大小不能超过 m
 * 每个物品只能取一次
 * m<=1000
 * len(A),len(V)<=100
 *
 * 样例 1：
 *
 * 输入：
 * m = 10
 * A = [2, 3, 5, 7]
 * V = [1, 5, 2, 4]
 *
 * 输出：
 * 9
 * 解释：
 * 装入 A[1] 和 A[3] 可以得到最大价值, V[1] + V[3] = 9
 *
 *样例 2：
 *
 * 输入：
 * m = 10
 * A = [2, 3, 8]
 * V = [2, 5, 8]
 *
 * 输出：
 * 10
 * 解释：
 * 装入 A[0] 和 A[2] 可以得到最大价值, V[0] + V[2] = 10
 */
public class BackPackII {

    // TODO 待研究 滚动数组减少空间成本

    public int backPackII2(int m, int[] w, int[] v) {
        int thingCount = w.length;

    }


    public int backPackII(int m, int[] w, int[] v) {
        int thingCount = w.length;
        int[][] value = new int[thingCount + 1][ m + 1];

        for (int j = 1; j <= m; j++) {
            for (int i = 1; i <= thingCount; i++) {
                if (j - w[i-1] >= 0) {
                    value[i][j] = Math.max(value[i-1][j], v[i-1] + value[i-1][j-w[i-1]]);
                }else {
                    value[i][j] = value[i-1][j];
                }
            }
        }

        return value[thingCount][m];
    }
}
