package com.lj.problem.leetcode._2;

import java.util.ArrayDeque;

/**
 * 1302. 层数最深叶子节点的和
 * 提示
 * 给你一棵二叉树的根节点 root ，请你返回 层数最深的叶子节点的和 。
 *
 * 示例 1：
 * 输入：root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
 * 输出：15

 * 示例 2：
 * 输入：root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
 * 输出：19

 * 提示：
 * 树中节点数目在范围 [1, 104] 之间。
 * 1 <= Node.val <= 100
 */
public class DeepestLeavesSum {

    int sum = 0;
    int maxDepth = 0;
    public int deepestLeavesSum(TreeNode root) {
        dfs(root, 1);
        return sum;
    }

    // TODO 看看别人怎么做的
    public void dfs(TreeNode node, int depth) {
        if (node == null) return;
        dfs(node.left, depth + 1);
        dfs(node.right, depth + 1);
        if (depth > maxDepth) {
            maxDepth = depth;
            sum = node.val;
        }else if (depth == maxDepth) {
            sum += node.val;
        }
    }

    public int deepestLeavesSum2(TreeNode root) {
        int sum=0;
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            sum = 0;
            while (size --> 0) {
                TreeNode node = queue.pollFirst();
                sum+=node.val;
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        return sum;
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
