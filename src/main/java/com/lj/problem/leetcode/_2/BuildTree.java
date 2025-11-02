package com.lj.problem.leetcode._2;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 *
 * 示例 1:
 * 输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * 输出: [3,9,20,null,null,15,7]
 *
 * 示例 2:
 * 输入: preorder = [-1], inorder = [-1]
 * 输出: [-1]
 *
 * 提示:
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder 和 inorder 均 无重复 元素
 * inorder 均出现在 preorder
 * preorder 保证 为二叉树的前序遍历序列
 * inorder 保证 为二叉树的中序遍历序列
 */
public class BuildTree {

    // TODO 看看别人咋做的
    int[] map = new int[6001];
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        for(int i = 0; i < inorder.length; i++) map[inorder[i]+3000] = i;
        return buildTree2(preorder, 0, preorder.length - 1);
    }
    public TreeNode buildTree2(int[] preorder, int l, int r) {
        if (l > r) return null;
        int val = preorder[idx++];
        TreeNode root = new TreeNode(val);
        root.left =  buildTree2(preorder, l, map[val+3000]-1);
        root.right = buildTree2(preorder, map[val+3000]+1, r);
        return root;
    }

    int idx = 0;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, inorder, 0, preorder.length - 1);
    }
    public TreeNode buildTree(int[] preorder, int[] inorder, int l, int r) {
        if (l > r) return null;
        int val = preorder[idx++];
        TreeNode root = new TreeNode(val);
        for (int i = l; i <= r; i++) {
            if (inorder[i] == val) {
                root.left = buildTree(preorder, inorder, l, i-1);
                root.right = buildTree(preorder, inorder, i+1, r);
                break;
            }
        }
        return root;
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
