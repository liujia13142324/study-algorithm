package com.lj.problem.leetcode._1;

/**
 * 110. 平衡二叉树
 * 给定一个二叉树，判断它是否是 平衡二叉树
 *
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：true
 *
 * 示例 2：
 * 输入：root = [1,2,2,3,3,null,null,4,4]
 * 输出：false
 *
 * 示例 3：
 * 输入：root = []
 * 输出：true
 *
 *
 * 提示：
 * 树中的节点数在范围 [0, 5000] 内
 * -104 <= Node.val <= 104
 */
public class IsBalanced {

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

    // 自底向上（求root高度的同时，把是否平衡判断了）
    public boolean isBalanced2(TreeNode root) {
        return height(root) != -1;
    }
    public int height(TreeNode root) {
        if (root == null) return 0;
        int lh = height(root.left);
        if (lh == -1) return -1;
        int rh = height(root.right);
        if (rh == -1 || Math.abs(lh - rh) > 1) {
            return -1;
        }
        return Math.max(lh, rh) + 1;
    }

    // 自顶向下
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        int minus = depth(root.left) - depth(root.right);
        return Math.abs(minus) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    private int depth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(depth(root.left), depth(root.right)) + 1;
    }
}
