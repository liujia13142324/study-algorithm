package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2850. 将石头分散到网格图的最少移动次数
 * 给你一个大小为 3 * 3 ，下标从 0 开始的二维整数矩阵 grid ，分别表示每一个格子里石头的数目。网格图中总共恰好有 9 个石头，一个格子里可能会有 多个 石头。
 * 每一次操作中，你可以将一个石头从它当前所在格子移动到一个至少有一条公共边的相邻格子。
 * 请你返回每个格子恰好有一个石头的 最少移动次数 。
 *
 *
 * 示例 1：
 * 输入：grid = [[1,1,0],[1,1,1],[1,2,1]]
 * 输出：3
 * 解释：让每个格子都有一个石头的一个操作序列为：
 * 1 - 将一个石头从格子 (2,1) 移动到 (2,2) 。
 * 2 - 将一个石头从格子 (2,2) 移动到 (1,2) 。
 * 3 - 将一个石头从格子 (1,2) 移动到 (0,2) 。
 * 总共需要 3 次操作让每个格子都有一个石头。
 * 让每个格子都有一个石头的最少操作次数为 3 。
 *
 * 示例 2：
 * 输入：grid = [[1,3,0],[1,0,0],[1,0,3]]
 * 输出：4
 * 解释：让每个格子都有一个石头的一个操作序列为：
 * 1 - 将一个石头从格子 (0,1) 移动到 (0,2) 。
 * 2 - 将一个石头从格子 (0,1) 移动到 (1,1) 。
 * 3 - 将一个石头从格子 (2,2) 移动到 (1,2) 。
 * 4 - 将一个石头从格子 (2,2) 移动到 (2,1) 。
 * 总共需要 4 次操作让每个格子都有一个石头。
 * 让每个格子都有一个石头的最少操作次数为 4 。
 *
 *
 * 提示：
 * grid.length == grid[i].length == 3
 * 0 <= grid[i][j] <= 9
 * grid 中元素之和为 9 。
 */
public class MinimumMoves {

    @Test
    public void test() {
        System.out.println(minimumMoves(new int[][]{
                {1,3,0}
                ,{1,0,0}
                ,{1,0,3}
        }));
    }

    // TODO 看看别人怎么写的
    public int minimumMoves(int[][] grid) {
        List<int[]> remains = new ArrayList<>();
        List<int[]> zeros = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] > 1) {
                    remains.add(new int[]{i, j, grid[i][j]});
                }else if (grid[i][j] == 0) {
                    zeros.add(new int[]{i, j});
                }
            }
        }
        int[] state = new int[2];
        state[1] = 1000;
        dfs(0, remains, zeros,state);
        return state[1];
    }

    private void dfs(int i, List<int[]> remains, List<int[]> zeros, int[]state) {
        if (i == zeros.size()) {
            if (state[0] < state[1]) {
                state[1] = state[0];
            }
            return;
        }
        int[] tmp2 = zeros.get(i);
        for (int j = 0; j < remains.size(); j++) {
            int[] tmp = remains.get(j);
            if (tmp[2] > 1) {
                tmp[2] -= 1;
                state[0] += Math.abs(tmp[0] - tmp2[0]) + Math.abs(tmp[1] - tmp2[1]);
                dfs(i + 1, remains, zeros, state);
                state[0] -= Math.abs(tmp[0] - tmp2[0]) + Math.abs(tmp[1] - tmp2[1]);
                tmp[2] += 1;
            }
        }
    }

}
