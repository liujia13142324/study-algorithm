package com.lj.problem.leetcode._1;

import java.util.ArrayList;
import java.util.List;

/**
 * 257. 二叉树的所有路径
 * 给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径。
 * 叶子节点 是指没有子节点的节点。
 *
 * 示例 1：
 * 输入：root = [1,2,3,null,5]
 * 输出：["1->2->5","1->3"]
 *
 * 示例 2：
 * 输入：root = [1]
 * 输出：["1"]
 *
 * 提示：
 * 树中节点的数目在范围 [1, 100] 内
 * -100 <= Node.val <= 100
 */
public class BinaryTreePaths {

    /**
     * 熟悉了 stringBuilder 的 api
     * @param root
     * @return
     */
    public List<String> binaryTreePaths3(TreeNode root) {
        List<String> ans = new ArrayList<>();
        dfs(root, ans, new StringBuilder());
        return ans;
    }

    private void dfs(TreeNode root, List<String> ans, StringBuilder sb) {
        if (root == null) {
            return;
        }
        int len = sb.length();
        sb.append(root.val);
        if (root.left == null && root.right == null) {
            ans.add(sb.toString());
            sb.setLength(len);
            return;
        }
        sb.append("->");
        dfs(root.left, ans, sb);
        dfs(root.right, ans, sb);
        sb.setLength(len);
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ans = new ArrayList<>();
        dfs(root, ans, new ArrayList<>());
        return ans;
    }

    private void dfs(TreeNode root, List<String> ans, List<String> path) {
        if (root == null) {
            return;
        }
        path.add(root.val + "");
        if (root.left == null && root.right == null) {
            ans.add(String.join("->", path));
            path.remove(path.size() - 1);
            return;
        }
        dfs(root.left, ans, path);
        dfs(root.right, ans, path);
        path.remove(path.size() - 1);
    }

    public List<String> binaryTreePaths2(TreeNode root) {
        List<String> ans = new ArrayList<>();
        dfs(root, ans, "");
        return ans;
    }

    private void dfs(TreeNode root, List<String> ans, String s) {
        if (root == null) {
            return;
        }
        s += root.val;
        if (root.left == null && root.right == null) {
            ans.add(s);
            return;
        }
        s += "->";
        dfs(root.left, ans, s);
        dfs(root.right, ans, s);
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
