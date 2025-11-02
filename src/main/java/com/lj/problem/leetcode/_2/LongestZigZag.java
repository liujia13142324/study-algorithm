package com.lj.problem.leetcode._2;

/**
 * 1372. 二叉树中的最长交错路径
 * 给你一棵以 root 为根的二叉树，二叉树中的交错路径定义如下：
 *
 * 选择二叉树中 任意 节点和一个方向（左或者右）。
 * 如果前进方向为右，那么移动到当前节点的的右子节点，否则移动到它的左子节点。
 * 改变前进方向：左变右或者右变左。
 * 重复第二步和第三步，直到你在树中无法继续移动。
 * 交错路径的长度定义为：访问过的节点数目 - 1（单个节点的路径长度为 0 ）。
 *
 * 请你返回给定树中最长 交错路径 的长度。
 *
 * 示例 1：
 * 输入：root = [1,null,1,1,1,null,null,1,1,null,1,null,null,null,1,null,1]
 * 输出：3
 * 解释：蓝色节点为树中最长交错路径（右 -> 左 -> 右）。
 *
 * 示例 2：
 * 输入：root = [1,1,1,null,1,null,null,1,1,null,1]
 * 输出：4
 * 解释：蓝色节点为树中最长交错路径（左 -> 右 -> 左 -> 右）。
 *
 * 示例 3：
 * 输入：root = [1]
 * 输出：0
 *
 *
 * 提示：
 * 每棵树最多有 50000 个节点。
 * 每个节点的值在 [1, 100] 之间。
 */
public class LongestZigZag {

    // TODO 看看别人写的
    int ans = 0;
    public int longestZigZag2(TreeNode root) {
        dfs2(root, 0, 0);
        return ans;
    }

    private void dfs2(TreeNode root, int len, int direction) {
        if (root == null) return;
        ans = Math.max(ans, len);
        dfs2(root.left, direction >= 0 ? len + 1: len, -1);
        dfs2(root.right, direction <= 0 ? len + 1: len, 1);
    }


    public int longestZigZag(TreeNode root) {
        int[] tmp = dfs(root);
        return ans - 1;
    }
    private int[] dfs(TreeNode root) {
        if (root == null) return new int[]{0, 0};
        int[] result = new int[]{dfs(root.left)[1] + 1, dfs(root.right)[0] + 1};
        ans = Math.max(Math.max(ans, result[0]), result[1]);
        return result;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
