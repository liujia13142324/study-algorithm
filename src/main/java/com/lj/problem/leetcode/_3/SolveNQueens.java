package com.lj.problem.leetcode._3;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 51. N 皇后
 * 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
 *
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 *
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 *
 *
 * 示例 1：
 * 输入：n = 4
 * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：[["Q"]]
 *
 * 提示：
 *
 * 1 <= n <= 9
 */
public class SolveNQueens {

    @Test
    public void test() {
        List<List<String>> lists = solveNQueens2(4);
        System.out.println(lists.size());
        System.out.println(lists);
    }


    public List<List<String>> solveNQueens3(int n) {
        int[] path = new int[n];
        List<List<String>> ans = new ArrayList<>();
        // 垂直不能走
        boolean[] vertical = new boolean[n];
        // 左斜不能走，左斜线 i1 + j1 == i2 + j2 == i3 + j3 ...
        boolean[] left = new boolean[n * 2];
        // 右斜不能走，右斜线 i1 - j1 == i2 - j2 == i3 - j3 ...
        boolean[] right = new boolean[n * 2];
        dfs(0, path, ans, vertical, left, right);
        return ans;
    }

    private void dfs(int i, int[] path, List<List<String>> ans, boolean[] vertical, boolean[] left, boolean[] right) {
        if (i == path.length) {
            List<String> tmp = new ArrayList<>();
            for (int k : path) {
                char[] chars = new char[path.length];
                Arrays.fill(chars, '.');
                chars[k] = 'Q';
                tmp.add(new String(chars));
            }
            ans.add(tmp);
            return;
        }

        for (int c = 0; c < path.length; c++) {
            if (!vertical[c] && !left[i + c] && !right[i - c + path.length]) {
                path[i] = c;
                vertical[c] = left[i + c] = right[i - c + path.length] = true;
                dfs(i + 1, path, ans, vertical, left, right);
                vertical[c] = left[i + c] = right[i - c + path.length] = false;
            }
        }
    }



    public List<List<String>> solveNQueens2(int n) {
        int[] path = new int[n];
        List<List<String>> ans = new ArrayList<>();
        dfs(0, path, ans);
        return ans;
    }

    private void dfs(int i, int[] path, List<List<String>> ans) {
        if (i == path.length) {
            List<String> tmp = new ArrayList<>();
            for (int k : path) {
                char[] chars = new char[path.length];
                Arrays.fill(chars, '.');
                chars[k] = 'Q';
                tmp.add(new String(chars));
            }
            ans.add(tmp);
            return;
        }

        for (int j = 0; j < path.length; j++) {
            if (isValid(i, j, path)) {
                path[i] = j;
                dfs(i + 1, path, ans);
            }
        }
    }

    private boolean isValid(int i, int j, int[] path) {
        for (int k = 0; k < i; k++) {
            //
            if (j == path[k] || i + j == k + path[k] || i - j == k - path[k]) {
                return false;
            }
        }
        return true;
    }


    public List<List<String>> solveNQueens(int n) {
        // 存放每行放置皇后的位置
        int[] path = new int[n];
        // 存放每列可以放置的位置
        boolean[] disabled = new boolean[n];
        List<List<String>> ans = new ArrayList<>();
        dfs(0, path, disabled, ans);
        return ans;
    }

    private void dfs(int i, int[]path, boolean[] disabled, List<List<String>> ans) {
        if (i == path.length) {
            List<String> tmp = new ArrayList<>();
            for (int k : path) {
                char[] chars = new char[path.length];
                Arrays.fill(chars, '.');
                chars[k] = 'Q';
                tmp.add(new String(chars));
            }
            ans.add(tmp);
            return;
        }

        for (int j = 0; j < path.length; j++) {
            if (!disabled[j]) {
                path[i] = j;
                dfs(i + 1, path, copyAndSetDisabled(i, path), ans);
            }
        }

    }


    private boolean[] copyAndSetDisabled(int curr, int[] path) {
        boolean[] disabled = new boolean[path.length];
        for (int i = 0; i <= curr; i++) {
            // 竖着
            disabled[path[i]] = true;
            // 左斜
            if (path[i] - (curr + 1 - i) >= 0) {
                disabled[path[i] - (curr + 1 - i)] = true;
            }
            // 右斜
            if (path[i] + (curr + 1 - i) < path.length) {
                disabled[path[i] + (curr + 1 - i)] = true;
            }
        }
        return disabled;
    }

}
