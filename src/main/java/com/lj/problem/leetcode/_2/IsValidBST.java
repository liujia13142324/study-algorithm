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

    // 中序方法，搜索二叉树中序遍历就是一个有序序列
    long pre = Long.MIN_VALUE;
    public boolean isValidBST6(TreeNode root) {
        return midOrder(root);
    }

    public boolean midOrder(TreeNode root) {
        if (root.left != null && !midOrder(root.left)) return false;
        if (root.val <= pre) return false;
        pre = root.val;
        return root.right == null || midOrder(root.right);
    }

    // 后序方法一
    public boolean isValidBST3(TreeNode root) {
        if (root == null) return true;
        if (root.left != null && maxVal3(root.left) >= root.val) return false;
        if (root.right != null && minVal3(root.right) <= root.val) return false;
        return isValidBST3(root.left) && isValidBST3(root.right);
    }

    // 最大值只可能是当前节点，或者当前节点的右子树中
    public long maxVal3(TreeNode root) {
        if (root.right == null) {
            return root.val;
        }
        if (root.right.val > root.val) {
            return maxVal3(root.right);
        }
        return Long.MAX_VALUE;
    }

    // 最小值只可能是当前节点，或者当前节点的左子树中
    public long minVal3(TreeNode root) {
        if (root.left == null) {
            return root.val;
        }
        if (root.left.val < root.val) {
            return minVal3(root.left);
        }
        return Long.MIN_VALUE;
    }

    // 前序方法
    public boolean isValidBST5(TreeNode root) {
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean dfs(TreeNode root, long min, long max){
        if (root.val <= min || root.val >= max) return false;
        if (root.left != null && !dfs(root.left, min, root.val)) return false;
        return root.right == null || dfs(root.right, root.val, max);
    }

    // 后序方法二
    public boolean isValidBST4(TreeNode root) {
        return dfs(root)[0] != Long.MIN_VALUE;
    }

    public long[] dfs(TreeNode root) {
        if (root == null) return new long[]{Long.MAX_VALUE, Long.MIN_VALUE};
        long [] left = dfs(root.left);
        long [] right = dfs(root.right);
        if (left[1] >= root.val || right[0] <= root.val) {
            return new long[]{Long.MIN_VALUE, Long.MAX_VALUE};
        }
        return new long[]{Math.min(root.val, left[0]), Math.max(root.val, right[1])};
    }


    // 垃圾方法，也是后序
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
        maxMap.put(root, Math.max(root.val, Math.max(maxVal2(root.left), maxVal2(root.right))));
        return maxMap.get(root);
    }

    public long minVal2(TreeNode root) {
        if (root == null) return Long.MAX_VALUE;
        if (minMap.get(root) != null) return minMap.get(root);
        minMap.put(root, Math.min(root.val, Math.min(minVal2(root.left), minVal2(root.right))));
        return minMap.get(root);
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
