package com.lj.problem.leetcode._2;

import java.util.ArrayList;
import java.util.List;

/**
 * 199. 二叉树的右视图
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 * 示例 1：
 * 输入：root = [1,2,3,null,5,null,4]
 * 输出：[1,3,4]
 * 解释：
 *
 * 示例 2：
 * 输入：root = [1,2,3,4,null,null,null,5]
 * 输出：[1,3,4,5]
 * 解释：
 *
 * 示例 3：
 * 输入：root = [1,null,3]
 * 输出：[1,3]
 *
 * 示例 4：
 * 输入：root = []
 * 输出：[]
 *
 * 提示:
 *
 * 二叉树的节点个数的范围是 [0,100]
 * -100 <= Node.val <= 100
 */
public class RightSideView {

    int maxDepth2 = 0;
    List<Integer> ans = new ArrayList();
    public List<Integer> rightSideView2(TreeNode root) {
        dfs(root, 0);
        return ans;
    }
    public void dfs(TreeNode root, int depth) {
        if (root == null) return;
        if (maxDepth2 == depth) {
            ans.add(root.val);
            maxDepth2++;
        }
        dfs(root.right, depth + 1);
        dfs(root.left, depth + 1);
    }


    int maxDepth = Integer.MIN_VALUE;
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList();
        dfs(ans, root, 0);
        return ans;
    }

    public void dfs(List<Integer> ans, TreeNode root, int depth) {
        if (root == null) return;
        if (depth > maxDepth) {
            ans.add(root.val);
            maxDepth = depth;
        }
        dfs(ans, root.right, depth + 1);
        dfs(ans, root.left, depth + 1);
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
