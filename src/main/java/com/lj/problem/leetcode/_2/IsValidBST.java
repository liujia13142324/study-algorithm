package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 98. 验证二叉搜索树
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 *
 * 有效 二叉搜索树定义如下：
 *
 * 节点的左子树只包含 严格小于 当前节点的数。
 * 节点的右子树只包含 严格大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 *
 *
 * 示例 1：
 * 输入：root = [2,1,3]
 * 输出：true
 *
 * 示例 2：
 * 输入：root = [5,1,4,null,null,3,6]
 * 输出：false
 * 解释：根节点的值是 5 ，但是右子节点的值是 4 。
 *
 *
 * 提示：
 * 树中节点数目范围在[1, 104] 内
 * -231 <= Node.val <= 231 - 1
 */
public class IsValidBST {

    @Test
    public void test() {
        TreeNode root = new TreeNode(45);
        root.left = new TreeNode(42);
        root.left.right = new TreeNode(44);
        root.left.right.left = new TreeNode(43);
        root.left.right.left.left = new TreeNode(41);
        System.out.println(isValidBST2(root));
    }

    public boolean isValidBST2(TreeNode root) {
        if (root == null) return true;
        if (maxVal2(root.left) < root.val &&
                minVal2(root.right) > root.val) {
            return isValidBST2(root.left) && isValidBST2(root.right);
        }
        return false;
    }

    Map<TreeNode, Long> maxMap = new HashMap<>();
    Map<TreeNode, Long> minMap = new HashMap<>();

    public long maxVal2(TreeNode root) {
        if (root == null) return Long.MIN_VALUE;
        if (maxMap.get(root) != null) return maxMap.get(root);
        maxMap.put(root, Math.max(maxVal2(root.left), maxVal2(root.right)));
        return maxMap.get(root);
    }

    public long minVal2(TreeNode root) {
        if (root == null) return Long.MAX_VALUE;
        if (minMap.get(root) != null) return minMap.get(root);
        minMap.put(root, Math.min(minVal2(root.left), minVal2(root.right)));
        return minMap.get(root);
    }


    // TODO 优化
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        if (maxVal(root.left, Long.MIN_VALUE) < root.val &&
                minVal(root.right, Long.MAX_VALUE) > root.val) {
            return isValidBST(root.left) && isValidBST(root.right);
        }
        return false;
    }

    public long maxVal(TreeNode root, long maxVal) {
        if (root == null) return maxVal;
        maxVal = Math.max(maxVal, root.val);
        return Math.max(maxVal(root.left, maxVal), maxVal(root.right, maxVal));
    }

    public long minVal(TreeNode root, long minVal) {
        if (root == null) return minVal;
        minVal = Math.min(minVal, root.val);
        return Math.min(minVal(root.left, minVal), minVal(root.right, minVal));
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
