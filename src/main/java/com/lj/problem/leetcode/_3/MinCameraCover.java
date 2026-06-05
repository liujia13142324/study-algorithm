package com.lj.problem.leetcode._3;

import org.junit.Test;

/**
 * 968. 监控二叉树
 * 困难
 * 给定一个二叉树，我们在树的节点上安装摄像头。
 *
 * 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。
 *
 * 计算监控树的所有节点所需的最小摄像头数量。
 *
 * 示例 1：
 * 输入：[0,0,null,0,0]
 * 输出：1
 * 解释：如图所示，一台摄像头足以监控所有节点。
 *
 * 示例 2：
 * 输入：[0,0,null,0,null,0,null,null,0]
 * 输出：2
 * 解释：需要至少两个摄像头来监视树的所有节点。 上图显示了摄像头放置的有效位置之一。
 *
 * 提示：
 *
 * 给定树的节点数的范围是 [1, 1000]。
 * 每个节点的值都是 0。
 */
public class MinCameraCover {

    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(4);
        root.left.left.left.right = new TreeNode(5);
        System.out.println(minCameraCover(root));
    }

    public int minCameraCover2(TreeNode root) {
        int[] ans = dfs2(root);
        return Math.min(ans[0], ans[1]);
    }

    private int[] dfs2(TreeNode node) {
        if (node == null) {
            return new int[]{Integer.MAX_VALUE / 2, 0, 0};
        }
        int[] l = dfs2(node.left);
        int[] r = dfs2(node.right);

        return new int[]{
                Math.min(l[0], l[2]) + Math.min(r[0], r[2]) + 1,
                Math.min(l[0] + r[0], Math.min(l[0] + r[1], l[1] + r[0])),
                Math.min(l[0], l[1]) + Math.min(r[0], r[1])
        };
    }


    public int minCameraCover(TreeNode root) {
        int[] ans = dfs(root);
        return Math.min(ans[0], ans[1]);
    }

    private int[] dfs(TreeNode node) {
        int[] ans = new int[] {1, 1, 0};
        int l0 = 100000000, r0 = 100000000, l1 = 0, r1 = 0;
        if (node.left != null) {
            int[] l = dfs(node.left);
            int tmp = Math.min(l[0], l[1]);
            ans[0] += Math.min(tmp, l[2]);
            ans[2] += tmp;
            l0 = l[0];
            l1 = l[1];
        }

        if (node.right != null) {
            int[] r = dfs(node.right);
            int tmp = Math.min(r[0], r[1]);
            ans[0] += Math.min(tmp, r[2]);
            ans[2] += tmp;
            r0 = r[0];
            r1 = r[1];
        }

        ans[1] = Math.min(l0 + r0, Math.min(l0 + r1, l1 + r0));
        if (ans[1] >= 100000000) ans[1] = 1;
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
