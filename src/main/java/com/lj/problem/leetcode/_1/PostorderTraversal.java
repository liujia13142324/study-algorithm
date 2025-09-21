package com.lj.problem.leetcode._1;

import java.util.ArrayList;
import java.util.List;

/**
 * 145. 二叉树的后序遍历
 * 给你一棵二叉树的根节点 root ，返回其节点值的 后序遍历 。
 *
 * 示例 1：
 * 输入：root = [1,null,2,3]
 * 输出：[3,2,1]
 *
 *
 * 示例 2：
 * 输入：root = [1,2,3,4,5,null,8,null,null,6,7,9]
 * 输出：[4,6,7,5,2,9,8,3,1]
 *
 *
 *
 * 示例 3：
 * 输入：root = []
 * 输出：[]
 *
 * 示例 4：
 * 输入：root = [1]
 * 输出：[1]
 *
 *
 * 提示：
 * 树中节点的数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 *
 * 进阶：递归算法很简单，你可以通过迭代算法完成吗？
 */
public class PostorderTraversal {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<Integer>();
        postorderTraversal(root,ans);
        return ans;
    }

    private void postorderTraversal(TreeNode root, List<Integer> ans) {
        if (root == null) return;
        postorderTraversal(root.left, ans);
        postorderTraversal(root.right, ans);
        ans.add(root.val);
    }

}
