package com.lj.problem.leetcode._2;

import java.util.HashMap;
import java.util.Map;

/**
 * LCP 64. 二叉树灯饰
 * 「力扣嘉年华」的中心广场放置了一个巨型的二叉树形状的装饰树。每个节点上均有一盏灯和三个开关。节点值为 0 表示灯处于「关闭」状态，节点值为 1 表示灯处于「开启」状态。每个节点上的三个开关各自功能如下：
 *
 * 开关 1：切换当前节点的灯的状态；
 * 开关 2：切换 以当前节点为根 的子树中，所有节点上的灯的状态，；
 * 开关 3：切换 当前节点及其左右子节点（若存在的话） 上的灯的状态；
 * 给定该装饰的初始状态 root，请返回最少需要操作多少次开关，可以关闭所有节点的灯。
 *
 * 示例 1：
 *
 * 输入：root = [1,1,0,null,null,null,1]
 *
 * 输出：2
 *
 * 解释：以下是最佳的方案之一，如图所示b71b95bf405e3b223e00b2820a062ba4.gif
 *
 * 示例 2：
 *
 * 输入：root = [1,1,1,1,null,null,1]
 *
 * 输出：1
 *
 * 解释：以下是最佳的方案，如图所示a4091b6448a0089b4d9e8f0390ff9ac6.gif
 *
 * 示例 3：
 *
 * 输入：root = [0,null,0]
 *
 * 输出：0
 *
 * 解释：无需操作开关，当前所有节点上的灯均已关闭
 *
 * 提示：
 *
 * 1 <= 节点个数 <= 10^5
 * 0 <= Node.val <= 1
 */
public class CloseLampInTree {

    public int closeLampInTree2(TreeNode root) {

        return dfs2(root, 0, 0, new HashMap<>());
    }

    /**
     *
     * @param node
     * @param s2  s2 是否切换，偶数次会抵消 --> s2 的奇偶性即代表的 s2 的开关
     * @param s3  s3 是否切换，
     * @return
     */
    private int dfs2(TreeNode node, int s2, int s3, Map<TreeNode, int[][]> cache) {
        if (node == null) return 0;
        if (cache.get(node) != null && cache.get(node)[s2][s3] != -1) {
            return cache.get(node)[s2][s3];
        }
        // 当前灯是开的情况
        // 当前开，s2 s3 同时切换抵消了/同时关闭，故还是开
        // 当前关，s2 s3 一正一负，故还是开
        if ((node.val == 1) == (s2 == s3)) {
            // s1 s2 s3 操作奇数次, 灯泡开 -> 关
            // s1 切换
            int s1_ = dfs2(node.left, s2, 0, cache) + dfs2(node.right, s2, 0, cache) + 1;
            // s2 切换
            int s2_ = dfs2(node.left, s2 ^ 1, 0, cache) + dfs2(node.right, s2 ^ 1, 0, cache) + 1;
            // s3 切换
            int s3_ = dfs2(node.left, s2, 1, cache) + dfs2(node.right, s2, 1, cache) + 1;
            // s1 s2 s3 同时切换 (奇数次，当前灯会熄灭)
            int s123_ = dfs2(node.left, s2 ^ 1, 1, cache) + dfs2(node.right, s2 ^ 1, 1, cache) + 3;
            if (cache.get(node) == null) {
                cache.put(node, new int[][]{{-1, -1}, {-1, -1}});
            }
            cache.get(node)[s2][s3] = Math.min(s123_, Math.min(s3_, Math.min(s1_, s2_)));
        } else {
            // s1 s2 s3 操作偶数次，灯泡保持关闭
            // s0 啥也不操作
            int s0_ = dfs2(node.left, s2, 0, cache) + dfs2(node.right, s2, 0, cache);
            // s12 切换
            int s12_ = dfs2(node.left, s2 ^ 1, 0, cache) + dfs2(node.right, s2 ^ 1, 0, cache) + 2;
            // s13 切换
            int s13_ = dfs2(node.left, s2, 1, cache) + dfs2(node.right, s2, 1, cache) + 2;
            // s23 切换
            int s23_ = dfs2(node.left, s2 ^ 1, 1, cache) + dfs2(node.right, s2 ^ 1, 1, cache) + 2;
            if (cache.get(node) == null) {
                cache.put(node, new int[][]{{-1, -1}, {-1, -1}});
            }
            cache.get(node)[s2][s3] = Math.min(s0_, Math.min(s12_, Math.min(s13_, s23_)));
        }

        return cache.get(node)[s2][s3];
    }



    public int closeLampInTree(TreeNode root) {

        return dfs(root, false, false);
    }

    /**
     *
     * @param node
     * @param s2  s2 是否切换，偶数次会抵消 --> s2 的奇偶性即代表的 s2 的开关
     * @param s3  s3 是否切换，
     * @return
     */
    private int dfs(TreeNode node, boolean s2, boolean s3) {
        if (node == null) return 0;
        // 当前灯是开的情况
        // 当前开，s2 s3 同时切换抵消了/同时关闭，故还是开
        // 当前关，s2 s3 一正一负，故还是开
        if ((node.val == 1) == (s2 == s3)) {
            // s1 s2 s3 操作奇数次, 灯泡开 -> 关
            // s1 切换
            int s1_ = dfs(node.left, s2, false) + dfs(node.right, s2, false) + 1;
            // s2 切换
            int s2_ = dfs(node.left, !s2, false) + dfs(node.right, !s2, false) + 1;
            // s3 切换
            int s3_ = dfs(node.left, s2, true) + dfs(node.right, s2, true) + 1;
            // s1 s2 s3 同时切换 (奇数次，当前灯会熄灭)
            int s123_ = dfs(node.left, !s2, true) + dfs(node.right, !s2, true) + 3;
            return Math.min(s123_, Math.min(s3_, Math.min(s1_, s2_)));
        } else {
            // s1 s2 s3 操作偶数次，灯泡保持关闭
            // s0 啥也不操作
            int s0_ = dfs(node.left, s2, false) + dfs(node.right, s2, false);
            // s12 切换
            int s12_ = dfs(node.left, !s2, false) + dfs(node.right, !s2, false) + 2;
            // s13 切换
            int s13_ = dfs(node.left, s2, true) + dfs(node.right, s2, true) + 2;
            // s23 切换
            int s23_ = dfs(node.left, !s2, true) + dfs(node.right, !s2, true) + 2;
            return Math.min(s0_, Math.min(s12_, Math.min(s13_, s23_)));
        }
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
