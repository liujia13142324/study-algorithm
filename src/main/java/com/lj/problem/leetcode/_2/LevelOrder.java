package com.lj.problem.leetcode._2;

import com.lj.study.common.bean.A;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * 102. 二叉树的层序遍历
 * 提示
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
 *
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[3],[9,20],[15,7]]
 *
 * 示例 2：
 * 输入：root = [1]
 * 输出：[[1]]
 *
 * 示例 3：
 * 输入：root = []
 * 输出：[]
 *
 * 提示：
 * 树中节点数目在范围 [0, 2000] 内
 * -1000 <= Node.val <= 1000
 */
public class LevelOrder {

    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> tmp = new ArrayList<>(size);
            while (size-- > 0) {
                TreeNode node = queue.poll();
                tmp.add(node.val);
                if (node.left != null) queue.addLast(node.left);
                if (node.right != null) queue.addLast(node.right);
            }
            ans.add(tmp);
        }

        return ans;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        List<TreeNode> queue = new ArrayList<>();
        queue.add(root);
        dfs(queue, ans);
        return ans;
    }
    public void dfs(List<TreeNode> queue, List<List<Integer>> ans) {
        if (queue.isEmpty()) return;
        List<TreeNode> queue2 = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        for (TreeNode node: queue) {
            tmp.add(node.val);
            if (node.left != null) queue2.add(node.left);
            if (node.right != null) queue2.add(node.right);
        }
        ans.add(tmp);
        dfs(queue2, ans);
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
