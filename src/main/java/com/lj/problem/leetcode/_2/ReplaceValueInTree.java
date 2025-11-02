package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.ArrayDeque;

/**
 * 2641. 二叉树的堂兄弟节点 II
 * 提示
 * 给你一棵二叉树的根 root ，请你将每个节点的值替换成该节点的所有 堂兄弟节点值的和 。
 *
 * 如果两个节点在树中有相同的深度且它们的父节点不同，那么它们互为 堂兄弟 。
 *
 * 请你返回修改值之后，树的根 root 。
 *
 * 注意，一个节点的深度指的是从树根节点到这个节点经过的边数。
 *
 *
 * 示例 1：
 * 输入：root = [5,4,9,1,10,null,7]
 * 输出：[0,0,0,7,7,null,11]
 * 解释：上图展示了初始的二叉树和修改每个节点的值之后的二叉树。
 * - 值为 5 的节点没有堂兄弟，所以值修改为 0 。
 * - 值为 4 的节点没有堂兄弟，所以值修改为 0 。
 * - 值为 9 的节点没有堂兄弟，所以值修改为 0 。
 * - 值为 1 的节点有一个堂兄弟，值为 7 ，所以值修改为 7 。
 * - 值为 10 的节点有一个堂兄弟，值为 7 ，所以值修改为 7 。
 * - 值为 7 的节点有两个堂兄弟，值分别为 1 和 10 ，所以值修改为 11 。
 *
 * 示例 2：
 * 输入：root = [3,1,2]
 * 输出：[0,0,0]
 * 解释：上图展示了初始的二叉树和修改每个节点的值之后的二叉树。
 * - 值为 3 的节点没有堂兄弟，所以值修改为 0 。
 * - 值为 1 的节点没有堂兄弟，所以值修改为 0 。
 * - 值为 2 的节点没有堂兄弟，所以值修改为 0 。
 *
 *
 * 提示：
 * 树中节点数目的范围是 [1, 105] 。
 * 1 <= Node.val <= 104
 */
public class ReplaceValueInTree {

    @Test
    public void test() {
        TreeNode root = new TreeNode(49);
        root.left = new TreeNode(40);
        root.right = new TreeNode(35);
        root.left.left = new TreeNode(42);
        root.left.right = new TreeNode(7);
        root.left.left.left = new TreeNode(50);
        root.left.right.right = new TreeNode(44);
        root.left.right.right.right = new TreeNode(27);
        root.left.right.right.right.left = new TreeNode(21);
        replaceValueInTree(root);
    }

    // TODO 看看别人咋么做的
    public TreeNode replaceValueInTree(TreeNode root) {
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        root.val = 0;
        queue.addLast(root);
        while (!queue.isEmpty()) {
            int sum = 0;
            for (TreeNode node: queue) {
                if (node.left != null) {
                    sum += node.left.val;
                }
                if (node.right != null) {
                    sum += node.right.val;
                }
            }
            int size = queue.size();
            while (size --> 0) {
                TreeNode node = queue.pollFirst();
                int nodeSum =
                        (node.left == null ? 0 : node.left.val) +
                                (node.right == null ? 0 : node.right.val);
                if (node.left != null) {
                    node.left.val = sum - nodeSum;
                    queue.addLast(node.left);
                }
                if (node.right != null) {
                    node.right.val = sum - nodeSum;
                    queue.addLast(node.right);
                }
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
