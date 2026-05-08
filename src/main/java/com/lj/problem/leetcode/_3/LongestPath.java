package com.lj.problem.leetcode._3;

/**
 * 2246. 相邻字符不同的最长路径
 * 给你一棵 树（即一个连通、无向、无环图），根节点是节点 0 ，这棵树由编号从 0 到 n - 1 的 n 个节点组成。用下标从 0 开始、长度为 n 的数组 parent 来表示这棵树，其中 parent[i] 是节点 i 的父节点，由于节点 0 是根节点，所以 parent[0] == -1 。
 * 另给你一个字符串 s ，长度也是 n ，其中 s[i] 表示分配给节点 i 的字符。
 * 请你找出路径上任意一对相邻节点都没有分配到相同字符的 最长路径 ，并返回该路径的长度。
 *
 * 示例 1：
 * 输入：parent = [-1,0,0,1,1,2], s = "abacbe"
 * 输出：3
 * 解释：任意一对相邻节点字符都不同的最长路径是：0 -> 1 -> 3 。该路径的长度是 3 ，所以返回 3 。
 * 可以证明不存在满足上述条件且比 3 更长的路径。

 * 示例 2：
 * 输入：parent = [-1,0,0,0], s = "aabc"
 * 输出：3
 * 解释：任意一对相邻节点字符都不同的最长路径是：2 -> 0 -> 3 。该路径的长度为 3 ，所以返回 3 。
 *
 *
 * 提示：
 * n == parent.length == s.length
 * 1 <= n <= 105
 * 对所有 i >= 1 ，0 <= parent[i] <= n - 1 均成立
 * parent[0] == -1
 * parent 表示一棵有效的树
 * s 仅由小写英文字母组成
 */
public class LongestPath {

    int ans = 0;

    public int longestPath(int[] parent, String s) {
        char[] chars = s.toCharArray();
        dfs(0, parent, chars);
        return ans;
    }

    private int[] dfs(int i, int[] parent, char[] chars) {
        int idx = lowerBound(i, parent, parent[i]);
        int[] result = new int[]{1, chars[i]};
        int singleMax = 0;
        int[] maxTow = new int[2];
        for (int k = idx; k < parent.length; k++) {
            int[] tmp = dfs(k, parent, chars);
            singleMax = Math.max(singleMax, tmp[0]);
            // 和根节点不相等
            if (tmp[1] != chars[i]) {
                if (maxTow[0] < tmp[0]) {
                    maxTow[0] = tmp[0];
                }else if (maxTow[1] < tmp[0]) {
                    maxTow[1] = tmp[0];
                }
            }
            // 单链路且和父节点不重复最长
            if (tmp[1] != chars[i] && tmp[0] + 1 > result[0]) {
                result = tmp;
            }
        }

        ans = Math.max(ans, Math.max(singleMax, maxTow[0] + maxTow[1]));
        return result;
    }

    private int lowerBound(int l, int[] parent, int target) {
        int r = parent.length;
        while (l + 1 < r) {
            int mid = (l + r) >>> 1;
            if (parent[mid] >= target) {
                r = mid;
            }else {
                l = mid;
            }
        }
        return r;
    }
}
