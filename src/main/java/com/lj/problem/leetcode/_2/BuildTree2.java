package com.lj.problem.leetcode._2;

/**
 * 106. 从中序与后序遍历序列构造二叉树
 * 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。
 *
 * 示例 1:
 * 输入：inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
 * 输出：[3,9,20,null,null,15,7]
 *
 * 示例 2:
 * 输入：inorder = [-1], postorder = [-1]
 * 输出：[-1]
 *
 * 提示:
 * 1 <= inorder.length <= 3000
 * postorder.length == inorder.length
 * -3000 <= inorder[i], postorder[i] <= 3000
 * inorder 和 postorder 都由 不同 的值组成
 * postorder 中每一个值都在 inorder 中
 * inorder 保证是树的中序遍历
 * postorder 保证是树的后序遍历
 */
public class BuildTree2 {

    int idx;
    int[] map = new int[6001];
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        idx = postorder.length - 1;
        for (int i = 0; i < inorder.length; i++) map[inorder[i] + 3000] = i;
        return buildTree(postorder, 0, inorder.length - 1);
    }

    public TreeNode buildTree(int[] postorder, int l, int r) {
        if (l > r) return null;
        int val = postorder[idx--];
        TreeNode tree = new TreeNode(val);
        tree.right = buildTree(postorder, map[val + 3000] + 1, r);
        tree.left = buildTree(postorder, l, map[val + 3000] - 1);
        return tree;
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
