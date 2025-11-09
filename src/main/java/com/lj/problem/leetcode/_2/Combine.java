package com.lj.problem.leetcode._2;

import java.util.ArrayList;
import java.util.List;

/**
 * 77. 组合
 * 相关企业
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 *
 * 你可以按 任何顺序 返回答案。
 *
 * 示例 1：
 * 输入：n = 4, k = 2
 * 输出：
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 *
 * 示例 2：
 * 输入：n = 1, k = 1
 * 输出：[[1]]
 *
 *
 * 提示：
 * 1 <= n <= 20
 * 1 <= k <= n
 */
public class Combine {

    /**
     * 选、或者不选
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(1, n, k, ans, path);
        return ans;
    }

    public void dfs(int i, int n, int k, List<List<Integer>> ans, List<Integer> path) {
        if (k == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }
        path.add(i);
        dfs(i+1, n, k-1, ans, path);
        path.remove(path.size()-1);
        if (i <= n - k) {
            dfs(i+1, n, k, ans, path);
        }
    }

    /**
     * 枚举结果
      * @return
     */
    public List<List<Integer>> combine2(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
//        dfs2(1, n, k, ans, path);
        dfs3(n, k, ans, path);
        return ans;
    }

    public void dfs2(int i, int n, int k, List<List<Integer>> ans, List<Integer> path) {
        if (k == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int j = i; j <= n - k + 1; j++) {
            path.add(j);
            dfs2(j + 1, n, k - 1, ans, path);
            path.remove(path.size() - 1);
        }
    }

    // 倒序枚举
    public void dfs3(int i, int k, List<List<Integer>> ans, List<Integer> path) {
        if (k == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int j = i; j >= k; j--) {
            path.add(j);
            dfs3(j - 1, k - 1, ans, path);
            path.remove(path.size() - 1);
        }
    }
}
