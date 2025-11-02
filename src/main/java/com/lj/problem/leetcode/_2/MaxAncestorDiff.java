package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.Arrays;

/**
 * 1026. 节点与其祖先之间的最大差值
 * 给定二叉树的根节点 root，找出存在于 不同 节点 A 和 B 之间的最大值 V，其中 V = |A.val - B.val|，且 A 是 B 的祖先。
 *
 * （如果 A 的任何子节点之一为 B，或者 A 的任何子节点是 B 的祖先，那么我们认为 A 是 B 的祖先）
 *
 *
 * 示例 1：
 * 输入：root = [8,3,10,1,6,null,14,null,null,4,7,13]
 * 输出：7
 * 解释：
 * 我们有大量的节点与其祖先的差值，其中一些如下：
 * |8 - 3| = 5
 * |3 - 7| = 4
 * |8 - 1| = 7
 * |10 - 13| = 3
 * 在所有可能的差值中，最大值 7 由 |8 - 1| = 7 得出。
 *
 * 示例 2：
 * 输入：root = [1,null,2,null,0,3]
 * 输出：3
 *
 * 提示：
 * 树中的节点数在 2 到 5000 之间。
 * 0 <= Node.val <= 105
 */
public class MaxAncestorDiff {

    int[] NULL = new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};

    @Test
    public void test() {
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(3);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(6);
        root.right.right = new TreeNode(14);
        root.left.right.left = new TreeNode(4);
        root.left.right.right = new TreeNode(7);
        root.right.right.left = new TreeNode(13);
        System.out.println(maxAncestorDiff2(root));
    }

    // 自上向下
    int ans = -1;
    public int maxAncestorDiff2(TreeNode root) {
        dfs(root, Integer.MAX_VALUE, Integer.MIN_VALUE);
        return ans;
    }

    private void dfs(TreeNode root, int min, int max) {
        if (root == null) return;
        min = Math.min(min, root.val);
        max = Math.max(max, root.val);
        ans = Math.max(ans, max - min);
        dfs(root.left, min, max);
        dfs(root.right, min, max);
    }


    // 自下向上
    public int maxAncestorDiff(TreeNode root) {
        dfs(root, new TreeNode[]{root});
        return ans;
    }

    public void dfs(TreeNode root, TreeNode[] ancestors) {
        if (root == null) return;
        for (TreeNode ancestor: ancestors) {
            ans = Math.max(ans, Math.abs(ancestor.val - root.val));
        }
        ancestors = Arrays.copyOf(ancestors, ancestors.length + 1);
        ancestors[ancestors.length - 1] = root;
        dfs(root.left, ancestors);
        dfs(root.right, ancestors);
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
