package com.lj.problem.leetcode._1;

/**
 * 111. 二叉树的最小深度
 * 给定一个二叉树，找出其最小深度。
 *
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * 说明：叶子节点是指没有子节点的节点。
 *
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：2
 *
 * 示例 2：
 * 输入：root = [2,null,3,null,4,null,5,null,6]
 * 输出：5
 *
 *
 * 提示：
 * 树中节点数的范围在 [0, 105] 内
 * -1000 <= Node.val <= 1000
 */
public class MinDepth {

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

    int ans = Integer.MAX_VALUE;

    /**
     * 自顶向下，最优性剪枝
     */
    public int minDepth2(TreeNode root) {
        dfs(root, 0);
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    private void dfs(TreeNode root, int depth) {
        depth++;
        // 最优性剪枝
        if (root == null || ans <= depth) return;

        if (root.left == null && root.right == null) {
            // ans = Math.min(ans, depth);
            // 这时候 depth < ans
            ans = depth;
            return;
        }
        dfs(root.left, depth);
        dfs(root.right, depth);
    }


    /**
     * 自底向上
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        int l = minDepth(root.left);
        int r = minDepth(root.right);

        if (l == 0 && r == 0) {
            return 1;
        }else if (l == 0) {
            return r + 1;
        }else if (r == 0) {
            return l + 1;
        }
        return Math.min(l, r) + 1;
    }

    public int minDepth3(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) {
            return 1;
        }
        if (root.left == null) {
            return minDepth(root.right) + 1;
        }
        if (root.right == null) {
            return minDepth(root.left) + 1;
        }
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }


}
