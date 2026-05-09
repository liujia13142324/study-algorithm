package com.lj.problem.leetcode._2;

import org.junit.Test;

/**
 * 687. 最长同值路径
 * 给定一个二叉树的 root ，返回 最长的路径的长度 ，这个路径中的 每个节点具有相同值 。 这条路径可以经过也可以不经过根节点。
 * 两个节点之间的路径长度 由它们之间的边数表示。
 *
 * 示例 1:
 * 输入：root = [5,4,5,1,1,5]
 * 输出：2
 *
 * 示例 2:
 * 输入：root = [1,4,5,4,4,5]
 * 输出：2
 *
 * 提示:
 * 树的节点数的范围是 [0, 104]
 * -1000 <= Node.val <= 1000
 * 树的深度将不超过 1000
 */
public class LongestUnivaluePath {

    @Test
    public void test() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(1);
        root.right.right = new TreeNode(5);
        System.out.println(longestUnivaluePath(root));
    }

    int ans = 0;

    /**
     * 边数量，不是节点长度！
     * @param root
     * @return
     */
    public int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode root) {
        if (root == null) return 0;
        int left = dfs(root.left);
        int right = dfs(root.right);
        int max = 0;
        int result = 1;
        if (left > 0 && root.left.val == root.val) {
            max += left;
            result += left;
        }
        if (right > 0 && root.right.val == root.val) {
            max += right;
            result = Math.max(result, right + 1);
        }
        ans = Math.max(ans, max);
        return result;
    }

    /**
     * 这种写法更加简洁
     * @param root
     * @return
     */
    public int longestUnivaluePath2(TreeNode root) {
        dfs2(root);
        return ans;
    }

    private int dfs2(TreeNode node) {
        if (node == null) {
            return -1; // 下面 +1 后，对于叶子节点就刚好是 0
        }
        int lLen = dfs2(node.left) + 1; // 左子树最大链长+1
        int rLen = dfs2(node.right) + 1; // 右子树最大链长+1
        if (node.left != null && node.left.val != node.val) lLen = 0; // 链长视作 0
        if (node.right != null && node.right.val != node.val) rLen = 0; // 链长视作 0
        ans = Math.max(ans, lLen + rLen); // 两条链拼成路径
        return Math.max(lLen, rLen); // 当前子树最大链和
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
