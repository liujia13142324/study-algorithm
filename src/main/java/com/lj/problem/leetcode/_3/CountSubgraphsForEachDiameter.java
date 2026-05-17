package com.lj.problem.leetcode._3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1617. 统计子树中城市之间最大距离
 * 给你 n 个城市，编号为从 1 到 n 。同时给你一个大小为 n-1 的数组 edges ，其中 edges[i] = [ui, vi] 表示城市 ui 和 vi 之间有一条双向边。题目保证任意城市之间只有唯一的一条路径。换句话说，所有城市形成了一棵 树 。
 * 一棵 子树 是城市的一个子集，且子集中任意城市之间可以通过子集中的其他城市和边到达。两个子树被认为不一样的条件是至少有一个城市在其中一棵子树中存在，但在另一棵子树中不存在。
 * 对于 d 从 1 到 n-1 ，请你找到城市间 最大距离 恰好为 d 的所有子树数目。
 * 请你返回一个大小为 n-1 的数组，其中第 d 个元素（下标从 1 开始）是城市间 最大距离 恰好等于 d 的子树数目。
 * 请注意，两个城市间距离定义为它们之间需要经过的边的数目。
 *
 * 示例 1：
 * 输入：n = 4, edges = [[1,2],[2,3],[2,4]]
 * 输出：[3,4,0]
 * 解释：
 * 子树 {1,2}, {2,3} 和 {2,4} 最大距离都是 1 。
 * 子树 {1,2,3}, {1,2,4}, {2,3,4} 和 {1,2,3,4} 最大距离都为 2 。
 * 不存在城市间最大距离为 3 的子树。
 *
 * 示例 2：
 * 输入：n = 2, edges = [[1,2]]
 * 输出：[1]
 *
 * 示例 3：
 * 输入：n = 3, edges = [[1,2],[2,3]]
 * 输出：[2,1]
 *
 * 提示：
 * 2 <= n <= 15
 * edges.length == n-1
 * edges[i].length == 2
 * 1 <= ui, vi <= n
 * 题目保证 (ui, vi) 所表示的边互不相同。
 */
public class CountSubgraphsForEachDiameter {

    int diameter;
    boolean[] set;
    boolean[] v;
    List<Integer>[] children;
    int n;
    int[] ans;

    public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
        this.n = n;
        children = new ArrayList[n];
        Arrays.setAll(children, i -> new ArrayList<>());
        for (int[] edge: edges) {
            int x = edge[0] - 1;
            int y = edge[1] - 1;
            children[x].add(y);
            children[y].add(x);
        }

        set = new boolean[n];
        ans = new int[n - 1];
        // 从第一个节点开始枚举
        traverseSet(0);
        return ans;
    }

    private void traverseSet(int i) {
        if (i == n) {
            for (int k = 0; k < n; k++) {
                // 从任一点进入，都可遍历整张图
                if (set[k]) {
                    v = new boolean[n];
                    diameter = 0;
                    dfs(k);
                    break;
                }
            }

            // 该子集可能不是一颗联通的树，所以需要匹配“枚举的子集”和“dfs遍历路径”是否相等
            // 比如 [[1,3],[1,4],[2,3]]，枚举 1,4 的时候，是可以成功匹配 dfs 路径，但是枚举 1,4,2 的时候，是不能成功匹配 dfs 路径的
            // 1,4,2不是一颗联通的树，所以从任意节点进入遍历的时候，无法遍历全部节点
            if (diameter > 0 && Arrays.equals(v, set)) {
                ans[diameter - 1] ++;
            }
            return;
        }

        // 不选择
        traverseSet(i + 1);
        // 选择
        set[i] = true;
        traverseSet(i + 1);
        set[i] = false;
    }

    private int dfs(int k) {
        int maxLen = 0;
        v[k] = true;
        for (int child: children[k]) {
            if (set[child] && !v[child]) {
                int len = dfs(child) + 1;
                diameter = Math.max(diameter, maxLen + len);
                maxLen = Math.max(maxLen, len);
            }
        }
        return maxLen;
    }
}
