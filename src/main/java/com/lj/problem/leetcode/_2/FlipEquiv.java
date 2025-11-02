package com.lj.problem.leetcode._2;

import org.junit.Test;

/**
 * 951. 翻转等价二叉树
 * 我们可以为二叉树 T 定义一个 翻转操作 ，如下所示：选择任意节点，然后交换它的左子树和右子树。
 * 只要经过一定次数的翻转操作后，能使 X 等于 Y，我们就称二叉树 X 翻转 等价 于二叉树 Y。
 * 这些树由根节点 root1 和 root2 给出。如果两个二叉树是否是翻转 等价 的树，则返回 true ，否则返回 false 。
 *
 * 示例 1：
 * Flipped Trees Diagram
 * 输入：root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 = [1,3,2,null,6,4,5,null,null,null,null,8,7]
 * 输出：true
 * 解释：我们翻转值为 1，3 以及 5 的三个节点。
 *
 * 示例 2:
 * 输入: root1 = [], root2 = []
 * 输出: true
 *
 * 示例 3:
 * 输入: root1 = [], root2 = [1]
 * 输出: false
 *
 *
 * 提示：
 * 每棵树节点数在 [0, 100] 范围内
 * 每棵树中的每个值都是唯一的、在 [0, 99] 范围内的整数
 */
public class FlipEquiv {

    @Test
    public void test() {
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);
        root1.right.left = new TreeNode(6);
        root1.left.right.left = new TreeNode(7);
        root1.left.right.right = new TreeNode(8);

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(3);
        root2.right = new TreeNode(2);
        root2.left.right = new TreeNode(6);
        root2.right.left = new TreeNode(4);
        root2.right.right = new TreeNode(5);
        root2.right.right.left = new TreeNode(8);
        root2.right.right.right = new TreeNode(7);

        System.out.println(flipEquiv(root1, root2));
    }

    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (!checkNodeEqual(root1, root2)) return false;
        if (root1 == null) return true;
        if (!checkNodeEqual(root1.left, root2.left) ||
                !checkNodeEqual(root1.right, root2.right)) {
            TreeNode tmp = root2.left;
            root2.left = root2.right;
            root2.right = tmp;
        }
        if (!checkNodeEqual(root1.left, root2.left) ||
                !checkNodeEqual(root1.right, root2.right)) {
            return false;
        }
        return flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right);
    }

    private boolean checkNodeEqual(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) return root1 == root2;
        return root1.val == root2.val;
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
