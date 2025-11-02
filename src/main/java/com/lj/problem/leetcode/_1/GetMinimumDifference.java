package com.lj.problem.leetcode._1;

/**
 * 530. 二叉搜索树的最小绝对差
 * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
 * 差值是一个正数，其数值等于两值之差的绝对值。
 *
 * 示例 1：
 * 输入：root = [4,2,6,1,3]
 * 输出：1
 *
 * 示例 2：
 * 输入：root = [1,0,48,null,null,12,49]
 * 输出：1
 *
 *
 * 提示：
 * 树中节点的数目范围是 [2, 104]
 * 0 <= Node.val <= 105
 */
public class GetMinimumDifference {
    int ans = 1000000;
    int pre = 1000000;
    public int getMinimumDifference(TreeNode root) {
        midOrder(root);
        return ans;
    }

    public void midOrder(TreeNode root) {
        if (root == null || ans == 1) return;
        midOrder(root.left);
        ans = Math.min(ans, Math.abs(root.val - pre));
        pre = root.val;
        midOrder(root.right);
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
