package com.lj.problem.leetcode._3;

import org.junit.Test;

/**
 * 52. N 皇后 II
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n × n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
 *
 * 示例 1：
 * 输入：n = 4
 * 输出：2
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：1
 *
 * 提示：
 * 1 <= n <= 9
 */
public class TotalNQueens {

    @Test
    public void test() {
        System.out.println(totalNQueens(9));
    }

    public int totalNQueens2(int n) {
        boolean[] vertical = new boolean[n];
        boolean[] left = new boolean[n * 2];
        boolean[] right = new boolean[n * 2];
        return dfs(0, n, vertical, left, right);
    }

    public int dfs(int i, int n, boolean[] vertical, boolean[] left, boolean[] right) {
        if (i == n) {
            return 1;
        }
        int ans = 0;
        for (int c = 0; c < n; c++) {
           if (!vertical[c] && !left[i + c] && !right[i - c + n]) {
               vertical[c] = left[i + c] = right[i - c + n] = true;
               ans += dfs(i + 1, n, vertical, left, right);
               vertical[c] = left[i + c] = right[i - c + n] = false;
           }
        }
        return ans;
    }

    public int totalNQueens(int n) {
        boolean[] disabled = new boolean[n];
        int[] path = new int[n];
        return dfs(0, n, path, disabled);
    }

    public int dfs(int i, int n, int[] path, boolean[] disabled) {
        if (i == n) {
            return 1;
        }

        int ans = 0;

        for (int j = 0; j < n; j++) {
            if (!disabled[j]) {
                path[i] = j;
                ans += dfs(i+1, n, path, checkDisabled(i+1, path));
            }
        }

        return ans;
    }

    private boolean[] checkDisabled(int i, int[] path) {
        boolean[] result = new boolean[path.length];
        for (int j = 0; j < i; j++) {
            result[path[j]] = true;
            if (path[j] - (i - j) >= 0) {
                result[path[j] - (i - j)] = true;
            }

            if (path[j] + (i - j) < result.length) {
                result[path[j] + (i - j)] = true;
            }
        }

        return result;
    }

}
