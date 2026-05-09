package com.lj.problem.leetcode._3;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void test() {
        System.out.println(longestPath(new int[]{-1, 0 ,1}, "aab"));
    }

    public int longestPath2(int[] parent, String s) {
        // 等于1直接返回
        if (parent.length == 1) return 1;
        char[] chars = s.toCharArray();
        List<Integer>[] children = new ArrayList[parent.length];
        for (int i = 1; i < parent.length; i++) {
            if (children[parent[i]] == null) {
                children[parent[i]] = new ArrayList<>();
            }
            children[parent[i]].add(i);
        }
        // 这个的含义是该节点的孩子中，最长的，满足题意的长度
        dfs2(0, children, chars);
        // 必须要这么写
        return ans + 1;
    }

    private int dfs2(int i, List<Integer>[] children, char[] chars) {
        // 初始值为0，代表孩子的最大长度
        int maxLen = 0;
        // 没有孩子直接返回
        if (children[i] == null) return maxLen;

        for (int k: children[i]) {
            int len = dfs2(k, children, chars) + 1;
            if (chars[k] != chars[i]) {
                // 这里不是任何情况都进得来！如果整个树的节点都是重复的，就进不来，也就更新不了这个答案。所以 dfs 的含义不能包含该节点
                ans = Math.max(ans, maxLen + len);
                maxLen = Math.max(maxLen, len);
            }
        }
        return maxLen;
    }


    public int longestPath(int[] parent, String s) {
        // 等于1直接返回
        if (parent.length == 1) return 1;
        char[] chars = s.toCharArray();
        List<Integer>[] children = new ArrayList[parent.length];
        for (int i = 1; i < parent.length; i++) {
            if (children[parent[i]] == null) {
                children[parent[i]] = new ArrayList<>();
            }
            children[parent[i]].add(i);
        }
        dfs(0, children, chars);
        return ans;
    }

    private int[] dfs(int i, List<Integer>[] children, char[] chars) {
        int[] result = new int[]{1, chars[i]};
        // 没有孩子直接返回
        if (children[i] == null) return result;

        int singleMax = 0;
        int[] maxTow = new int[2];
        for (int k: children[i]) {
            int[] tmp = dfs(k, children, chars);
            // 单链路最长的分支
            singleMax = Math.max(singleMax, tmp[0]);
            // 和根节点不相等, 最长的两条分支
            if (tmp[1] != chars[i]) {
                if (maxTow[0] < tmp[0]) {
                    maxTow[1] = maxTow[0];
                    maxTow[0] = tmp[0];
                }else if (maxTow[1] < tmp[0]) {
                    maxTow[1] = tmp[0];
                }
            }
            // 单链路且和父节点不重复最长，往上传递的结果
            if (tmp[1] != chars[i] && tmp[0] + 1 > result[0]) {
                result[0] = tmp[0] + 1;
            }
        }

        ans = Math.max(ans, Math.max(singleMax, maxTow[0] + maxTow[1] + 1));
        return result;
    }

    /**
     * parent 不讲武徳，几乎顺序都没有
     * @param i
     * @param parent
     * @param chars
     * @return
     */
    private int[] dfs(int i, int[] parent, char[] chars) {
        int idx = lowerBound(i, parent, i);
        int[] result = new int[]{1, chars[i]};
        int singleMax = 0;
        int[] maxTow = new int[2];
        for (int k = idx; k < parent.length && parent[k] == i; k++) {
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
                result[0] = tmp[0] + 1;
            }
        }

        ans = Math.max(ans, Math.max(singleMax, maxTow[0] + maxTow[1] + 1));
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
