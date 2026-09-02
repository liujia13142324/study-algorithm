package com.lj.problem.leetcode._2;

/**
 * 3603. 交替方向的最小路径代价 II
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你两个整数 m 和 n，分别表示网格的行数和列数。
 *
 * 进入单元格 (i, j) 的成本定义为 (i + 1) * (j + 1)。
 *
 * 另外给你一个二维整数数组 waitCost，其中 waitCost[i][j] 定义了在该单元格 等待 的成本。
 *
 * 路径始终从第 1 步进入单元格 (0, 0) 并支付入场花费开始。
 *
 * 每一步，你都遵循交替模式：
 *
 * 在 奇数秒 ，你必须向 右 或向 下 移动到 相邻 的单元格，并支付其进入成本。
 * 在 偶数秒 ，你必须原地 等待恰好 1 秒并在 1 秒期间支付 waitCost[i][j]。
 * 返回到达 (m - 1, n - 1) 所需的 最小 总成本。
 *
 *
 *
 * 示例 1：
 *
 * 输入：m = 1, n = 2, waitCost = [[1,2]]
 *
 * 输出：3
 *
 * 解释：
 *
 * 最佳路径为：
 *
 * 从第 1 秒开始在单元格 (0, 0)，进入成本为 (0 + 1) * (0 + 1) = 1。
 * 第 1 秒：向右移动到单元格 (0, 1)，进入成本为 (0 + 1) * (1 + 1) = 2。
 * 因此，总成本为 1 + 2 = 3。
 *
 * 示例 2：
 *
 * 输入：m = 2, n = 2, waitCost = [[3,5],[2,4]]
 *
 * 输出：9
 *
 * 解释：
 *
 * 最佳路径为：
 *
 * 从第 1 秒开始在单元格 (0, 0)，进入成本为 (0 + 1) * (0 + 1) = 1。
 * 第 1 秒：向下移动到单元格 (1, 0)，进入成本为 (1 + 1) * (0 + 1) = 2。
 * 第 2 秒：在单元格 (1, 0) 等待，支付 waitCost[1][0] = 2。
 * 第 3 秒：向右移动到单元格 (1, 1)，进入成本为 (1 + 1) * (1 + 1) = 4。
 * 因此，总成本为 1 + 2 + 2 + 4 = 9。
 *
 * 示例 3：
 *
 * 输入：m = 2, n = 3, waitCost = [[6,1,4],[3,2,5]]
 *
 * 输出：16
 *
 * 解释：
 *
 * 最佳路径为：
 *
 * 从第 1 秒开始在单元格 (0, 0)，进入成本为 (0 + 1) * (0 + 1) = 1。
 * 第 1 秒：向右移动到单元格 (0, 1)，进入成本为 (0 + 1) * (1 + 1) = 2。
 * 第 2 秒：在单元格 (0, 1) 等待，支付 waitCost[0][1] = 1。
 * 第 3 秒：向下移动到单元格 (1, 1)，进入成本为 (1 + 1) * (1 + 1) = 4。
 * 第 4 秒：在单元格 (1, 1) 等待，支付 waitCost[1][1] = 2。
 * 第 5 秒：向右移动到单元格 (1, 2)，进入成本为 (1 + 1) * (2 + 1) = 6。
 * 因此，总成本为 1 + 2 + 1 + 4 + 2 + 6 = 16。
 *
 *
 *
 * 提示：
 *
 * 1 <= m, n <= 105
 * 2 <= m * n <= 105
 * waitCost.length == m
 * waitCost[0].length == n
 * 0 <= waitCost[i][j] <= 105
 */
public class MinCost {



    /**
     * 9 ms, 还有个 8 ms 的，思路差不多就不写了
     * @param m
     * @param n
     * @param waitCost
     * @return
     */
    public long minCost2(int m, int n, int[][] waitCost) {
        long[] dp = new long[n];
        waitCost[0][0] = 0;
        dp[0] = 1;
        for (int j = 1; j < n; j++) {
            dp[j] = dp[j - 1] + waitCost[0][j - 1] + j + 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                long min = dp[j] + waitCost[i - 1][j];
                if (j > 0) {
                    min = Math.min(min, dp[j - 1] + waitCost[i][j - 1]);
                }
                dp[j] = min + (long) (i + 1) * (j + 1);
            }
        }
        return dp[n - 1];
    }

    /**
     * 11 ms
     * @param m
     * @param n
     * @param waitCost
     * @return
     */
    public long minCost(int m, int n, int[][] waitCost) {
        long[] dp = new long[n];
        waitCost[0][0] = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                long min = Long.MAX_VALUE;
                if (i > 0) {
                    min = dp[j] + waitCost[i - 1][j];
                }
                if (j > 0) {
                    min = Math.min(min, dp[j - 1] + waitCost[i][j-1]);
                }
                dp[j] = (min == Long.MAX_VALUE ? 0 : min) + (long) (i + 1) * (j + 1);
            }
        }

        return dp[n - 1];
    }

}
