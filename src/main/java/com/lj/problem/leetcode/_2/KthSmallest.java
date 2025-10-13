package com.lj.problem.leetcode._2;


/**
 * 230. 二叉搜索树中第 K 小的元素
 * 提示
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 小的元素（从 1 开始计数）。
 *
 * 示例 1：
 * 输入：root = [3,1,4,null,2], k = 1
 * 输出：1
 *
 * 示例 2：
 * 输入：root = [5,3,6,2,4,null,null,1], k = 3
 * 输出：3
 * 提示：
 * 树中的节点数为 n 。
 * 1 <= k <= n <= 104
 * 0 <= Node.val <= 104
 *
 *
 * 进阶：如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法？
 */
public class KthSmallest {

    // TODO 看看别人怎么做的
    int ans = -1;
    int c = -1;
    public int kthSmallest2(TreeNode root, int k) {
        c = k;
        dfs(root);
        return ans;
    }
    public void dfs(TreeNode root) {
        if (root == null || ans != -1) return;
        dfs(root.left);
        c--;
        if (c == 0) ans = root.val;
        dfs(root.right);
    }


    int count = 1;
    Integer result = null;
    public int kthSmallest(TreeNode node, int k) {
        if (node == null || result != null) {
            return -1;
        }
        kthSmallest(node.left, k);
        
        if (result != null) {
            return result;
        }
        
        if (count == k) {
            result = node.val;
            return result;
        }else {
            count++;
        }
        kthSmallest(node.right, k);
        
        if (result != null) {
            return result;
        }else {
            return -1;
        }
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
    
    public static void main(String[] args) {
    
    }
    
}
