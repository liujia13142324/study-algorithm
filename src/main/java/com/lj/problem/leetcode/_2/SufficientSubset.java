package com.lj.problem.leetcode._2;


import org.junit.Test;

/**
 * 1080. 根到叶路径上的不足节点
 * 给你二叉树的根节点 root 和一个整数 limit ，请你同时删除树中所有 不足节点 ，并返回最终二叉树的根节点。
 * 假如通过节点 node 的每种可能的 “根-叶” 路径上值的总和全都小于给定的 limit，则该节点被称之为 不足节点 ，需要被删除。
 * 叶子节点，就是没有子节点的节点。
 *
 * 示例 1：
 * 输入：root = [1,2,3,4,-99,-99,7,8,9,-99,-99,12,13,-99,14], limit = 1
 * 输出：[1,2,3,4,null,null,7,8,9,null,14]
 *
 * 示例 2：
 * 输入：root = [5,4,8,11,null,17,4,7,1,null,null,5,3], limit = 22
 * 输出：[5,4,8,11,null,17,4,7,null,null,null,5]
 *
 * 示例 3：
 * 输入：root = [1,2,-3,-5,null,4,null], limit = -1
 * 输出：[1,null,-3,4]
 *
 *
 * 提示：
 * 树中节点数目在范围 [1, 5000] 内
 * -105 <= Node.val <= 105
 * -109 <= limit <= 109
 *
 */
public class SufficientSubset {

    @Test
    public void test() {
        /*TreeNode treeNode = new TreeNode(5);
        treeNode.left = new TreeNode(4);
        treeNode.right = new TreeNode(8);
        treeNode.left.left = new TreeNode(11);
        treeNode.right.left = new TreeNode(17);
        treeNode.right.right = new TreeNode(4);
        treeNode.left.left.left = new TreeNode(7);
        treeNode.left.left.right = new TreeNode(1);
        treeNode.right.right.left = new TreeNode(5);
        treeNode.right.right.right = new TreeNode(3);*/

        TreeNode treeNode = new TreeNode(5);
        treeNode.left = new TreeNode(4);
        treeNode.right = new TreeNode(8);
        treeNode.left.left = new TreeNode(11);
        treeNode.right.left = new TreeNode(17);
        treeNode.right.right = new TreeNode(4);
        treeNode.left.left.left = new TreeNode(7);
        treeNode.left.left.right = new TreeNode(1);
        treeNode.right.right.left = new TreeNode(5);
        treeNode.right.right.right = new TreeNode(3);
        System.out.println(sufficientSubset(treeNode, 22));
    }

    public TreeNode sufficientSubset2(TreeNode root, int limit) {
        limit -= root.val;
        if (root.left == null && root.right == null) return limit > 0 ? null : root;
        if (root.left != null) root.left = sufficientSubset(root.left, limit);
        if (root.right != null) root.right = sufficientSubset(root.right, limit);
        return root.left == null && root.right == null ? null : root;
    }

    public TreeNode sufficientSubset(TreeNode root, int limit) {
        return dfs(root, 0, limit) ? null : root;
    }

    private boolean dfs(TreeNode root, int sum, int limit) {
        sum += root.val;
        if (root.left == null && root.right == null) return sum < limit;
        boolean result1 = true;
        boolean result2 = true;
        if (root.left != null) result1 = dfs(root.left, sum, limit);
        if (root.right != null) result2 = dfs(root.right, sum, limit);
        if (result1 && result2) return true;
        if (result1) root.left = null;
        if (result2) root.right = null;
        return false;
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
