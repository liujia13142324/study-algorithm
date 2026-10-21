package com.lj.problem.leetcode._2;


import org.junit.Test;

import java.util.Arrays;

/**
 * 1824. 最少侧跳次数
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个长度为 n 的 3 跑道道路 ，它总共包含 n + 1 个 点 ，编号为 0 到 n 。一只青蛙从 0 号点第二条跑道 出发 ，它想要跳到点 n 处。然而道路上可能有一些障碍。
 *
 * 给你一个长度为 n + 1 的数组 obstacles ，其中 obstacles[i] （取值范围从 0 到 3）表示在点 i 处的 obstacles[i] 跑道上有一个障碍。如果 obstacles[i] == 0 ，那么点 i 处没有障碍。任何一个点的三条跑道中 最多有一个 障碍。
 *
 * 比方说，如果 obstacles[2] == 1 ，那么说明在点 2 处跑道 1 有障碍。
 * 这只青蛙从点 i 跳到点 i + 1 且跑道不变的前提是点 i + 1 的同一跑道上没有障碍。为了躲避障碍，这只青蛙也可以在 同一个 点处 侧跳 到 另外一条 跑道（这两条跑道可以不相邻），但前提是跳过去的跑道该点处没有障碍。
 *
 * 比方说，这只青蛙可以从点 3 处的跑道 3 跳到点 3 处的跑道 1 。
 * 这只青蛙从点 0 处跑道 2 出发，并想到达点 n 处的 任一跑道 ，请你返回 最少侧跳次数 。
 *
 * 注意：点 0 处和点 n 处的任一跑道都不会有障碍。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：obstacles = [0,1,2,3,0]
 * 输出：2
 * 解释：最优方案如上图箭头所示。总共有 2 次侧跳（红色箭头）。
 * 注意，这只青蛙只有当侧跳时才可以跳过障碍（如上图点 2 处所示）。
 * 示例 2：
 *
 *
 * 输入：obstacles = [0,1,1,3,3,0]
 * 输出：0
 * 解释：跑道 2 没有任何障碍，所以不需要任何侧跳。
 * 示例 3：
 *
 *
 * 输入：obstacles = [0,2,1,0,3,0]
 * 输出：2
 * 解释：最优方案如上图所示。总共有 2 次侧跳。
 *
 *
 * 提示：
 *
 * obstacles.length == n + 1
 * 1 <= n <= 5 * 105
 * 0 <= obstacles[i] <= 3
 * obstacles[0] == obstacles[n] == 0
 */
public class MinSideJumps {

    @Test
    public void test() {
        System.out.println(minSideJumps(new int[]{0,0,0,3,3,1,3,0,1,1,0,3,0,2,1,2,2,0,0,0,1,2,3,1,2,1,1,2,2,1,1,0,2,3,3,3,0,2,3,2,0,0,0,1,0,2,2,0,0,2,0,2,0,1,1,0,3,1,3,3,0,1,0,3,0,3,1,2,3,1,0,0,2,3,2,0,0,3,1,2,3,2,2,3,1,3,3,2,0,1,3,0,3,2,2,3,2,1,2,2,0}));
    }

    /**
     * 不在同一跑到横跳，不会产生环，
     * @param obstacles
     * @return
     */
    public int minSideJumps2(int[] obstacles) {
        int[][] cache = new int[3][obstacles.length];
        for (int[] tmp: cache) {
            Arrays.fill(tmp, -1);
        }
        return dfs2(1, 0, obstacles, cache);
    }

    private int dfs2(int i, int j, int[] obstacles, int[][] cache) {
        if (j == obstacles.length - 1) {
            return 0;
        }
        if (cache[i][j] != -1) {
            return cache[i][j];
        }
        int minVal = Integer.MAX_VALUE / 2;

        if (obstacles[j + 1] != (i + 1)) {
            minVal = Math.min(minVal, dfs2(i, j + 1, obstacles, cache));
        }

        for (int k = 0; k < 3; k++) {
            if (i != k && obstacles[j] != (k + 1) && obstacles[j + 1] != (k + 1)) {
                minVal = Math.min(minVal, dfs2(k, j + 1, obstacles, cache) + 1);
            }
        }
        return cache[i][j] = minVal;
    }

    /**
     * 在同一跑道横跳，会产生环（这个做法是瞎猫碰到死耗子？）
     * 对于这道题，侧跳并不一定需要在没有障碍时提前进行。由于跑道之间的侧跳代价为 1，而向前移动代价为 0，提前侧跳有时可以被推迟到真正需要的时候。
     * 因此，单看这个 else，还不能直接断言第二段代码一定会产生错误答案；
     * @param obstacles
     * @return
     */
    public int minSideJumps(int[] obstacles) {
        int[][] cache = new int[3][obstacles.length];
        for (int[] tmp: cache) {
            Arrays.fill(tmp, -1);
        }
        boolean[][] visited = new boolean[3][obstacles.length];
        return dfs(1, 0, obstacles, cache, visited);
    }

    /**
     *  cache 和 visited 混用的问题：
     *  比如，当遍历 i,j 的时候，为了避免出现环路，会设置 visited[i,j] = true, 那么在遍历横跳位置的时候，
     *  其他位置（比如，i+1, j）就没办法获取 i, j 的结果。因为避免环路跳过i, j 的判断，除此之外，此刻 i, j 也没有算出来。
     * @return
     */
    private int dfs(int i, int j, int[] obstacles, int[][] cache, boolean[][] visited) {
        if (j == obstacles.length - 1) {
            return 0;
        }
        if (cache[i][j] != -1) {
            return cache[i][j];
        }
        visited[i][j] = true;
        int minVal = Integer.MAX_VALUE / 2;

        if (obstacles[j + 1] != (i + 1)) {
            minVal = Math.min(minVal, dfs(i, j + 1, obstacles, cache, visited));
        } else {
            for (int k = 0; k < 3; k++) {
                if (!visited[k][j] && obstacles[j] != (k + 1) && obstacles[j + 1] != (k + 1)) {
                    minVal = Math.min(minVal, dfs(k, j + 1, obstacles, cache, visited) + 1);
                }
            }
        }
        visited[i][j] = false;
        return cache[i][j] = minVal;
    }

    /**
     *  最早版本的问题，存在 cache 和 visited 混用的问题，不能AC。实例分析见 MinSideJumps.md
     */
    /*private int dfs(int i, int j, int[] obstacles, int[][] cache, boolean[][] visited) {
        if (j == obstacles.length - 1) {
            return 0;
        }
        if (cache[i][j] != -1) {
            return cache[i][j];
        }
        visited[i][j] = true;
        int minVal = Integer.MAX_VALUE / 2;

        if (obstacles[j + 1] != (i + 1)) {
            minVal = Math.min(minVal, dfs(i, j + 1, obstacles, cache, visited));
        }

        for (int k = 0; k < 3; k++) {
            if (!visited[k][j] && obstacles[j] != (k + 1)) {
                minVal = Math.min(minVal, dfs(k, j, obstacles, cache, visited) + 1);
            }
        }
        visited[i][j] = false;
        return cache[i][j] = minVal;
    }*/

}
