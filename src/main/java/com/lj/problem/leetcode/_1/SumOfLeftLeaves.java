package com.lj.problem.leetcode._1;

import org.junit.Test;

import java.util.Arrays;

/**
 * 404. 左叶子之和
 * 给定二叉树的根节点 root ，返回所有左叶子之和。
 *
 *
 * 示例 1：
 * 输入: root = [3,9,20,null,null,15,7]
 * 输出: 24
 * 解释: 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
 *
 * 示例 2:
 * 输入: root = [1]
 * 输出: 0
 *
 *
 * 提示:
 * 节点数在 [1, 1000] 范围内
 * -1000 <= Node.val <= 1000
 */
public class SumOfLeftLeaves {

    @Test
    public void test() {
        char[] chars = new char[10];
        chars[0] = '0' + 0;
        chars[1] = '0' + 1;
        System.out.println(chars[0]);
        System.out.println(chars[1]);
        Arrays.copyOfRange(chars, 0, 10);
    }

    public int sumOfLeftLeaves(TreeNode root) {
        return sumOfLeftLeaves(root.left, 0) + sumOfLeftLeaves(root.right, 1);
    }

    public int sumOfLeftLeaves(TreeNode root, int flag) {
        if (root == null) return 0;
        if (root.left == null && root.right == null){
            if (flag == 0) return root.val;
            else return 0;
        }
        return sumOfLeftLeaves(root.left, 0) + sumOfLeftLeaves(root.right, 1);
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
