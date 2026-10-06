package com.lj.problem.leetcode._2;

import java.util.HashSet;
import java.util.Set;

/**
 * 2711. 对角线上不同值的数量差
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个下标从 0 开始、大小为 m x n 的二维矩阵 grid ，请你求解大小同样为 m x n 的答案矩阵 answer 。
 *
 * 矩阵 answer 中每个单元格 (r, c) 的值可以按下述方式进行计算：
 *
 * 令 topLeft[r][c] 为矩阵 grid 中单元格 (r, c) 左上角对角线上 不同值 的数量。
 * 令 bottomRight[r][c] 为矩阵 grid 中单元格 (r, c) 右下角对角线上 不同值 的数量。
 * 然后 answer[r][c] = |topLeft[r][c] - bottomRight[r][c]| 。
 *
 * 返回矩阵 answer 。
 *
 * 矩阵对角线 是从最顶行或最左列的某个单元格开始，向右下方向走到矩阵末尾的对角线。
 *
 * 如果单元格 (r1, c1) 和单元格 (r, c) 属于同一条对角线且 r1 < r ，则单元格 (r1, c1) 属于单元格 (r, c) 的左上对角线。类似地，可以定义右下对角线。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：grid = [[1,2,3],[3,1,5],[3,2,1]]
 * 输出：[[1,1,0],[1,0,1],[0,1,1]]
 * 解释：第 1 个图表示最初的矩阵 grid 。
 * 第 2 个图表示对单元格 (0,0) 计算，其中蓝色单元格是位于右下对角线的单元格。
 * 第 3 个图表示对单元格 (1,2) 计算，其中红色单元格是位于左上对角线的单元格。
 * 第 4 个图表示对单元格 (1,1) 计算，其中蓝色单元格是位于右下对角线的单元格，红色单元格是位于左上对角线的单元格。
 * - 单元格 (0,0) 的右下对角线包含 [1,1] ，而左上对角线包含 [] 。对应答案是 |1 - 0| = 1 。
 * - 单元格 (1,2) 的右下对角线包含 [] ，而左上对角线包含 [2] 。对应答案是 |0 - 1| = 1 。
 * - 单元格 (1,1) 的右下对角线包含 [1] ，而左上对角线包含 [1] 。对应答案是 |1 - 1| = 0 。
 * 其他单元格的对应答案也可以按照这样的流程进行计算。
 * 示例 2：
 *
 * 输入：grid = [[1]]
 * 输出：[[0]]
 * 解释：- 单元格 (0,0) 的右下对角线包含 [] ，左上对角线包含 [] 。对应答案是 |0 - 0| = 0 。
 *
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n, grid[i][j] <= 50
 */
public class DifferenceOfDistinctValues {


    public int[][] differenceOfDistinctValues3(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        Set<Integer> set = new HashSet<>();
        int[][] ans = new int[m][n];

        for (int k = 1; k < m + n; k++) {
            int minJ = Math.max(0, n - k);
            int maxJ = Math.min(m + n - 1 - k, n - 1);

            set.clear();
            for (int j = minJ, i = k + j - n; j <= maxJ; i++, j++) {
                ans[i][j] = set.size();
                set.add(grid[i][j]);
            }

            set.clear();
            for (int j = maxJ, i = k + j - n; j >= minJ; i--, j--) {
                ans[i][j] = Math.abs(ans[i][j] - set.size());
                set.add(grid[i][j]);
            }
        }

        return ans;
    }


    /**
     * 6 ms
     * @param grid
     */
    public int[][] differenceOfDistinctValues2(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        Set<Integer> set = new HashSet<>();
        int[][] ans = new int[m][n];

        for (int k = 1; k < m + n; k++) {
            int minJ = Math.max(0, n - k);
            int maxJ = Math.min(m + n - 1 - k, n - 1);

            set.clear();
            for (int i = Math.max(0, k - n), j = minJ; j <= maxJ; i++, j++) {
                ans[i][j] = set.size();
                set.add(grid[i][j]);
            }

            set.clear();
            for (int j = maxJ, i = Math.max(0, k - n) + maxJ - minJ; j >= minJ; i--, j--) {
                ans[i][j] = Math.abs(ans[i][j] - set.size());
                set.add(grid[i][j]);
            }
        }

        return ans;
    }



    public int[][] differenceOfDistinctValues(int[][] grid) {
        int[][] ans = new int[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int leftTopCnt = getLeftCnt2(grid, i, j);
                int rightBottomCnt = getRightCnt2(grid, i, j);
                ans[i][j] = Math.abs(leftTopCnt - rightBottomCnt);
            }
        }
        return ans;
    }

    /**
     * 6 ms
     * @param grid
     * @param i
     * @param j
     * @return
     */
    private int getRightCnt2(int[][] grid, int i, int j) {
        boolean[] map = new boolean[51];
        int size = 0;
        while (i < grid.length - 1 && j < grid[0].length - 1) {
            int k = grid[++i][++j];
            if (!map[k]) {
                size ++;
                map[k] = true;
            }
        }
        return size;
    }

    private int getLeftCnt2(int[][] grid, int i, int j) {
        boolean[] map = new boolean[51];
        int size = 0;
        while (i > 0 && j > 0) {
            int k = grid[--i][--j];
            if (!map[k]) {
                size ++;
                map[k] = true;
            }
        }
        return size;
    }


    /**
     * 20ms
     * @param grid
     * @return
     */
    private int getRightCnt(Set<Integer> sets, int[][] grid, int i, int j) {
        sets.clear();
        while (i < grid.length - 1 && j < grid[0].length - 1) {
            sets.add(grid[++i][++j]);
        }
        return sets.size();
    }

    private int getLeftCnt(Set<Integer> sets, int[][] grid, int i, int j) {
        sets.clear();
        while (i > 0 && j > 0) {
            sets.add(grid[--i][--j]);
        }
        return sets.size();
    }
}
