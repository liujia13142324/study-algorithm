package com.lj.problem.leetcode._3;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 3203. 合并两棵树后的最小直径
 * 给你两棵 无向 树，分别有 n 和 m 个节点，节点编号分别为 0 到 n - 1 和 0 到 m - 1 。给你两个二维整数数组 edges1 和 edges2 ，长度分别为 n - 1 和 m - 1 ，其中 edges1[i] = [ai, bi] 表示在第一棵树中节点 ai 和 bi 之间有一条边，edges2[i] = [ui, vi] 表示在第二棵树中节点 ui 和 vi 之间有一条边。
 * 你必须在第一棵树和第二棵树中分别选一个节点，并用一条边连接它们。
 * 请你返回添加边后得到的树中，最小直径 为多少。
 * 一棵树的 直径 指的是树中任意两个节点之间的最长路径长度。
 *
 * 示例 1：
 * 输入：edges1 = [[0,1],[0,2],[0,3]], edges2 = [[0,1]]
 * 输出：3
 * 解释：
 * 将第一棵树中的节点 0 与第二棵树中的任意节点连接，得到一棵直径为 3 的树。
 *
 * 示例 2：
 * 输入：edges1 = [[0,1],[0,2],[0,3],[2,4],[2,5],[3,6],[2,7]], edges2 = [[0,1],[0,2],[0,3],[2,4],[2,5],[3,6],[2,7]]
 * 输出：5
 * 解释：
 * 将第一棵树中的节点 0 和第二棵树中的节点 0 连接，可以得到一棵直径为 5 的树。
 *
 * 提示：
 * 1 <= n, m <= 105
 * edges1.length == n - 1
 * edges2.length == m - 1
 * edges1[i].length == edges2[i].length == 2
 * edges1[i] = [ai, bi]
 * 0 <= ai, bi < n
 * edges2[i] = [ui, vi]
 * 0 <= ui, vi < m
 * 输入保证 edges1 和 edges2 分别表示一棵合法的树。
 */
public class MinimumDiameterAfterMerge {

    @Test
    public void test() {
       /* System.out.println(minimumDiameterAfterMerge2(
                        new int[][]{{0, 1}, {0, 2}, {0, 3}, {2, 4}, {2, 5}, {3, 6}, {2, 7}}
                        , new int[][]{{0, 1}, {0, 2}, {0, 3}, {2, 4}, {2, 5}, {3, 6}, {2, 7}}
                )
        );

        System.out.println(minimumDiameterAfterMerge2(
                        new int[][]{}
                        , new int[][]{{0, 1}, {1, 2}}
                )
        );*/

        System.out.println(minimumDiameterAfterMerge2(
                        new int[][]{{0,1},{2,0},{3,2},{3,6},{8,7},{4,8},{5,4},{3,5},{3,9}}
                        , new int[][]{{0, 1}, {0, 2}, {0, 3}}
                )
        );
    }

    int[] len = new int[]{0, 0};
    public int minimumDiameterAfterMerge2(int[][] edges1, int[][] edges2) {
        len = new int[]{0, 0};
        dfs2(edges1, 0);
        dfs2(edges2, 1);
        return Math.max(
                (int)(1 + Math.ceil(len[0] / 2.0) + Math.ceil(len[1] / 2.0))
                , Math.max(len[0], len[1])
        );
    }

    private void dfs2(int[][] edges, int lenIdx) {
        if (edges.length == 0) return;
        List<Integer>[] totalEdges = new ArrayList[edges.length + 1];
        Arrays.setAll(totalEdges, e->new ArrayList<>());
        for (int[] edge : edges) {
            totalEdges[edge[0]].add(edge[1]);
            totalEdges[edge[1]].add(edge[0]);
        }

        dfs(-1, 0, totalEdges, lenIdx);
    }

    private int dfs(int parent, int i, List<Integer>[] totalEdges, int lenIdx) {
        if (totalEdges[i] == null) return 0;
        int[] two = new int[]{0, 0};
        int max = 0;
        for (int child: totalEdges[i]) {
            if (child == parent) continue;
            int len = dfs(i, child, totalEdges, lenIdx) + 1;
            max = Math.max(max, len);
            if (len > two[0]) {
                two[1] = two[0];
                two[0] = len;
            }else if (len > two[1]) {
                two[1] = len;
            }
        }

        len[lenIdx] = Math.max(len[lenIdx], two[0] + two[1]);
        return max;
    }


    public int minimumDiameterAfterMerge(int[][] edges1, int[][] edges2) {
        int[] m1 = dfs(edges1);
        int[] m2 = dfs(edges2);
        return Math.max(m1[1] + m2[1] + 1, Math.max(m1[0], m2[0]));
    }

    private int[] dfs(int[][] edges) {
        if (edges.length == 0) return new int[]{0, 0};
        List<Integer>[] totalEdges = new ArrayList[edges.length + 1];
        Arrays.setAll(totalEdges, e->new ArrayList<>());
        for (int[] edge : edges) {
            totalEdges[edge[0]].add(edge[1]);
            totalEdges[edge[1]].add(edge[0]);
        }

        int min = Integer.MAX_VALUE;
        int diameter = Integer.MIN_VALUE;
        for (int i = 0; i < totalEdges.length; i++) {
            int[] two = new int[2];
            int max = Integer.MIN_VALUE;
            for (int child: totalEdges[i]) {
                int len = dfs(child, i, totalEdges) + 1;
                max = Math.max(max, len);
                if (len > two[0]) {
                    two[1] = two[0];
                    two[0] = len;
                }else if (len > two[1]) {
                    two[1] = len;
                }
            }
            min = Math.min(min, max);
            diameter = Math.max(diameter, two[0] + two[1]);
        }

        return new int[]{diameter, min};
    }

    private int dfs(int current, int parent, List<Integer>[] totalEdges) {
        int maxLen = 0;
        for (int child: totalEdges[current]) {
            if (child == parent) {
                continue;
            }
            maxLen = Math.max(maxLen, dfs(child, current, totalEdges) + 1);
        }
        return maxLen;
    }

}
