package com.lj.problem.leetcode._1;

import java.util.HashMap;
import java.util.Map;

/**
 * 543. 二叉树的直径
 * 给你一棵二叉树的根节点，返回该树的 直径 。
 * 二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。
 * 两节点之间路径的 长度 由它们之间边数表示。
 *
 * 示例 1：
 * 输入：root = [1,2,3,4,5]
 * 输出：3
 * 解释：3 ，取路径 [4,2,1,3] 或 [5,2,1,3] 的长度。
 *
 * 示例 2：
 * 输入：root = [1,2]
 * 输出：1
 *
 * 提示：
 *
 * 树中节点数目在范围 [1, 104] 内
 * -100 <= Node.val <= 100
 */
public class DiameterOfBinaryTree {


    public int diameterOfBinaryTree(TreeNode root) {
        Map<TreeNode, Integer> depthCache = new HashMap<>();
        return dfs(root, depthCache);
    }

    private int dfs(TreeNode node,  Map<TreeNode, Integer> depthCache) {
        if (node == null) {
            return 0;
        }
        int left = depth(node.left, depthCache);
        int right = depth(node.right, depthCache);
        return Math.max(Math.max(left + right, dfs(node.left, depthCache)), dfs(node.right, depthCache));
    }

    private int depth(TreeNode node, Map<TreeNode, Integer> depthCache) {
        if (node == null) return 0;
        if (depthCache.get(node) != null) return depthCache.get(node);
        int ans = Math.max(depth(node.left, depthCache), depth(node.right, depthCache)) + 1;
        depthCache.put(node, ans);
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
