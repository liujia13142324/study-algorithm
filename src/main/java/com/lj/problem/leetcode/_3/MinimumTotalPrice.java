package com.lj.problem.leetcode._3;

import org.junit.Test;

import java.util.*;

/**
 * 2646. 最小化旅行的价格总和
 * 困难
 * 现有一棵无向、无根的树，树中有 n 个节点，按从 0 到 n - 1 编号。给你一个整数 n 和一个长度为 n - 1 的二维整数数组 edges ，其中 edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间存在一条边。
 *
 * 每个节点都关联一个价格。给你一个整数数组 price ，其中 price[i] 是第 i 个节点的价格。
 *
 * 给定路径的 价格总和 是该路径上所有节点的价格之和。
 *
 * 另给你一个二维整数数组 trips ，其中 trips[i] = [starti, endi] 表示您从节点 starti 开始第 i 次旅行，并通过任何你喜欢的路径前往节点 endi 。
 *
 * 在执行第一次旅行之前，你可以选择一些 非相邻节点 并将价格减半。
 *
 * 返回执行所有旅行的最小价格总和。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：n = 4, edges = [[0,1],[1,2],[1,3]], price = [2,2,10,6], trips = [[0,3],[2,1],[2,3]]
 * 输出：23
 * 解释：
 * 上图表示将节点 2 视为根之后的树结构。第一个图表示初始树，第二个图表示选择节点 0 、2 和 3 并使其价格减半后的树。
 * 第 1 次旅行，选择路径 [0,1,3] 。路径的价格总和为 1 + 2 + 3 = 6 。
 * 第 2 次旅行，选择路径 [2,1] 。路径的价格总和为 2 + 5 = 7 。
 * 第 3 次旅行，选择路径 [2,1,3] 。路径的价格总和为 5 + 2 + 3 = 10 。
 * 所有旅行的价格总和为 6 + 7 + 10 = 23 。可以证明，23 是可以实现的最小答案。
 * 示例 2：
 *
 *
 * 输入：n = 2, edges = [[0,1]], price = [2,2], trips = [[0,0]]
 * 输出：1
 * 解释：
 * 上图表示将节点 0 视为根之后的树结构。第一个图表示初始树，第二个图表示选择节点 0 并使其价格减半后的树。
 * 第 1 次旅行，选择路径 [0] 。路径的价格总和为 1 。
 * 所有旅行的价格总和为 1 。可以证明，1 是可以实现的最小答案。
 *
 *
 * 提示：
 *
 * 1 <= n <= 50
 * edges.length == n - 1
 * 0 <= ai, bi <= n - 1
 * edges 表示一棵有效的树
 * price.length == n
 * price[i] 是一个偶数
 * 1 <= price[i] <= 1000
 * 1 <= trips.length <= 100
 * 0 <= starti, endi <= n - 1
 */
public class MinimumTotalPrice {

    @Test
    public void test() {
        System.out.println(minimumTotalPrice(4, new int[][]{{0,1}, {1,2}, {1,3}}, new int[]{2,2,10,6}, new int[][]{{0,3}, {2,1}, {2,3}}));
    }

    int[] newPrice;

    public int minimumTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {
        List<Integer>[] children = new ArrayList[n];
        Arrays.setAll(children, e -> new ArrayList<>());
        for (int[] edge : edges) {
            children[edge[0]].add(edge[1]);
            children[edge[1]].add(edge[0]);
        }
        // 递归，根据旅游路径，算出新的价值，没有路过的节点，价值归0
        newPrice = new int[n];
        for (int[] trip: trips) {
            dfsForNewPrice(-1, trip[0], trip[1], price, children);
        }

        int[] ans = dfs(-1, 0, children);

        return Math.min(ans[0], ans[1]);
    }

    private int[] dfs(int parent, int i, List<Integer>[] children) {
        int[] ans = new int[]{newPrice[i] / 2, newPrice[i]};
        for (int child: children[i]) {
            if (child == parent) continue;
            int[] tmp = dfs(i, child, children);
            ans[0] += tmp[1];
            ans[1] += Math.min(tmp[0], tmp[1]);
        }
        return ans;
    }

    private int dfsForNewPrice(int parent, int i, int target, int[] price, List<Integer>[] children) {
        if (i == target) {
            newPrice[i] += price[i];
            return i;
        }
        for (int child: children[i]) {
            if (parent == child) continue;
            int val = dfsForNewPrice(i, child, target, price, children);
            if (val >= 0) {
                newPrice[i] += price[i];
                return i;
            }
        }
        return -1;
    }


}
