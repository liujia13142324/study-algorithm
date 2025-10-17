package com.lj.problem.leetcode._2;

import java.util.*;

/**
 * 107. 二叉树的层序遍历 II
 * 给你二叉树的根节点 root ，返回其节点值 自底向上的层序遍历 。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 *
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[15,7],[9,20],[3]]

 * 示例 2：
 * 输入：root = [1]
 * 输出：[[1]]

 * 示例 3：
 * 输入：root = []
 * 输出：[]
 *
 *
 * 提示：
 * 树中节点数目在范围 [0, 2000] 内
 * -1000 <= Node.val <= 1000
 */
public class LevelOrderBottom {

    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        if (root == null) return Collections.emptyList();
        List<List<Integer>> ans = new ArrayList<>();
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        dfs(queue, ans);
        return ans;
    }

    private void dfs(ArrayDeque<TreeNode> queue, List<List<Integer>> ans) {
        if (queue.isEmpty()) return;
        int size = queue.size();
        List<Integer> list = new ArrayList<>();
        while (size --> 0) {
            TreeNode poll = queue.poll();
            list.add(poll.val);
            if (poll.left != null) queue.offer(poll.left);
            if (poll.right != null) queue.offer(poll.right);
        }
        dfs(queue, ans);
        ans.add(list);
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) return Arrays.asList();
        ArrayDeque<TreeNode> queue = new ArrayDeque();
        List<List<Integer>> ans = new ArrayList<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> tmp = new ArrayList<>();
            while (size-->0) {
                TreeNode node = queue.pollFirst();
                tmp.add(node.val);
                if (node.left != null) queue.addLast(node.left);
                if (node.right != null) queue.addLast(node.right);
            }
            ans.add(tmp);
        }
        Collections.reverse(ans);
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
