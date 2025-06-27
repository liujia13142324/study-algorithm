package com.lj.problem.leetcode._1;



/**
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 *
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 *
 * 输入：root = [1,2,2,null,3,3,null]
 * 输出：false
 */
public class IsSymmetricBinaryTree {
    
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
    
    /**
     * 递归比较
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        return check(root.left, root.right);
    }
    
    private boolean check(TreeNode left, TreeNode right) {
        if (left == null  && right == null) {
            return true;
        }else if (left == null || right == null) {
            return false;
        }
        
        if (left.val != right.val) {
            return false;
        }
        
        return check(left.left, right.right) && check(left.right, right.left);
    }
}
