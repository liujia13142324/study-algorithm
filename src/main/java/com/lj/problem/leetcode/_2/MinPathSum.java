package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.Arrays;

/**
 * 64. 最小路径和
 * 中等
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * 说明：每次只能向下或者向右移动一步。
 *
 *
 * 示例 1：
 * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 * 输出：7
 * 解释：因为路径 1→3→1→1→1 的总和最小。
 *
 * 示例 2：
 * 输入：grid = [[1,2,3],[4,5,6]]
 * 输出：12
 *
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * 0 <= grid[i][j] <= 200
 *
 * TMP
 *
 */
public class MinPathSum {

    @Test
    public void test() {
        System.out.println(minPathSum____(new int[][]{{1,2,3}, {4,5,6}}));
    }

    // 滚动数组
    public int minPathSum______(int[][] grid) {
        int[] dfs = new int[grid[0].length + 1];
        Arrays.fill(dfs, 100000);
        dfs[1] = 0;
        for (int[] row : grid) {
            for (int j = 0; j < grid[0].length; j++) {
                dfs[j + 1] = row[j] + Math.min(dfs[j + 1], dfs[j]);
            }
        }
        return dfs[grid[0].length];
    }

    // 滚动数组
    public int minPathSum_____(int[][] grid) {
        int[] dfs = new int[grid[0].length + 1];
        Arrays.fill(dfs, 100000);
        dfs[1] = 0;
        for (int i = 1; i <= grid.length; i++) {
            for (int j = 1; j <= grid[0].length; j++) {
                dfs[j] = grid[i - 1][j - 1] + Math.min(dfs[j], dfs[j - 1]);
            }
        }
        return dfs[grid[0].length];
    }


    // 递推
    public int minPathSum___(int[][] grid) {
        int[][] dfs = new int[grid.length + 1][grid[0].length + 1];
        Arrays.fill(dfs[0], 10000000);
        for (int i = 0; i <= grid.length; i++) {
            dfs[i][0] = 10000000;
        }
        dfs[0][1] = dfs[1][0] = 0;
        for (int i = 1; i <= grid.length; i++) {
            for (int j = 1; j <= grid[0].length; j++) {
                dfs[i][j] = grid[i - 1][j - 1] + Math.min(dfs[i - 1][j], dfs[i][j - 1]);
            }
        }
        return dfs[grid.length][grid[0].length];
    }

    // 递推
    public int minPathSum____(int[][] grid) {
        int[][] dfs = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int min = 100000;
                if (i > 0) min = dfs[i - 1][j];
                if (j > 0) min = Math.min(min, dfs[i][j - 1]);
                dfs[i][j] = min != 100000 ? grid[i][j] + min : grid[i][j];
            }
        }
        return dfs[grid.length - 1][grid[0].length - 1];
    }

    // 记忆化
    public int minPathSum__(int[][] grid) {
        int[][] cache = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                cache[i][j] = -1;
            }
        }
        return dfs_(grid.length - 1, grid[0].length - 1, grid, cache);
    }

    private int dfs_(int i, int j, int[][] grid, int[][] cache) {
        if (i == 0 && j == 0) {
            return grid[0][0];
        }

        if (cache[i][j] != -1) {
            return cache[i][j];
        }

        int ans = Integer.MAX_VALUE;
        if (i > 0) {
            ans = grid[i][j] + dfs_(i - 1, j, grid, cache);
        }
        if (j > 0) {
            ans = Math.min(ans, grid[i][j] + dfs_(i, j - 1, grid, cache));
        }

        cache[i][j] = ans;

        return ans;
    }

    // 记忆化
    public int minPathSum_(int[][] grid) {
        int[][] cache = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                cache[i][j] = -1;
            }
        }
        return dfs(0, 0, grid, cache);
    }

    private int dfs(int i, int j, int[][] grid, int[][] cache) {
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            return grid[i][j];
        }

        if (cache[i][j] != -1) {
            return cache[i][j];
        }

        int ans = Integer.MAX_VALUE;
        if (i < grid.length - 1) {
            ans = grid[i][j] + dfs(i + 1, j, grid, cache);
        }
        if (j < grid[0].length - 1) {
            ans = Math.min(ans, grid[i][j] + dfs(i, j + 1, grid, cache));
        }

        cache[i][j] = ans;

        return ans;
    }

    // 递归
    public int minPathSum(int[][] grid) {
        return dfs(0, 0, grid);
    }

    private int dfs(int i, int j, int[][] grid) {
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            return grid[i][j];
        }

        int ans = Integer.MAX_VALUE;
        if (i < grid.length - 1) {
            ans = grid[i][j] + dfs(i + 1, j, grid);
        }
        if (j < grid[0].length - 1) {
            ans = Math.min(ans, grid[i][j] + dfs(i, j + 1, grid));
        }

        return ans;
    }

}
