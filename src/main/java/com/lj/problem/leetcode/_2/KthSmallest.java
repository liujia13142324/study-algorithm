package com.lj.problem.leetcode._2;


/**
 * 二叉搜索树中第K小的元素
 */
public class KthSmallest {
    
    
    int count = 1;
    Integer result = null;
    public int kthSmallest(TreeNode node, int k) {
        if (node == null || result != null) {
            return -1;
        }
        kthSmallest(node.left, k);
        
        if (result != null) {
            return result;
        }
        
        if (count == k) {
            result = node.val;
            return result;
        }else {
            count++;
        }
        kthSmallest(node.right, k);
        
        if (result != null) {
            return result;
        }else {
            return -1;
        }
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
    
    public static void main(String[] args) {
    
    }
    
}
