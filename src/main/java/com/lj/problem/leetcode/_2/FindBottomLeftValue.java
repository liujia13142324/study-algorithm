package com.lj.problem.leetcode._2;

import java.util.ArrayDeque;
import java.util.Stack;

/**
 * 513. 找树左下角的值
 * 给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
 *
 * 假设二叉树中至少有一个节点。
 *
 *
 *
 * 示例 1:
 * 输入: root = [2,1,3]
 * 输出: 1
 *
 * 示例 2:
 * 输入: [1,2,3,4,null,5,6,null,null,7]
 * 输出: 7
 *
 *
 * 提示:
 * 二叉树的节点个数的范围是 [1,104]
 * -231 <= Node.val <= 231 - 1
 */
public class FindBottomLeftValue {

    // TODO 看看别人写的
    int ans = -1;
    int maxDepth = -1;
    public int findBottomLeftValue(TreeNode root) {
        dfs(root, 0);
        return ans;
    }

    public void dfs(TreeNode root, int depth) {
        if (root == null) return;
        dfs(root.left, depth + 1);
        dfs(root.right, depth + 1);
        if (depth > maxDepth) {
            maxDepth = depth;
            ans = root.val;
        }
    }

    public int findBottomLeftValue2(TreeNode root) {
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int ans = -1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.pollFirst();
            ans = node.val;
            if (node.right != null) queue.addLast(node.right);
            if (node.left != null) queue.addLast(node.left);
        }
        return ans;
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
