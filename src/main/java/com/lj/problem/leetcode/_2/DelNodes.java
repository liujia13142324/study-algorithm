package com.lj.problem.leetcode._2;

import java.util.ArrayList;
import java.util.List;

/**
 * 1110. 删点成林
 * 给出二叉树的根节点 root，树上每个节点都有一个不同的值。
 * 如果节点值在 to_delete 中出现，我们就把该节点从树上删去，最后得到一个森林（一些不相交的树构成的集合）。
 * 返回森林中的每棵树。你可以按任意顺序组织答案。
 *
 *
 * 示例 1：
 * 输入：root = [1,2,3,4,5,6,7], to_delete = [3,5]
 * 输出：[[1,2,null,4],[6],[7]]
 *
 * 示例 2：
 * 输入：root = [1,2,4,null,3], to_delete = [3]
 * 输出：[[1,2,4]]
 *
 * 提示：
 * 树中的节点数最大为 1000。
 * 每个节点都有一个介于 1 到 1000 之间的值，且各不相同。
 * to_delete.length <= 1000
 * to_delete 包含一些从 1 到 1000、各不相同的值。
 */
public class DelNodes {


    // TODO 看看别人怎么做的
    int needDelCnt;
    int map[] = new int[1001];
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        needDelCnt = to_delete.length;
        for (int num: to_delete) map[num] = 1;
        List<TreeNode> ans = new ArrayList();
        root = dfs(root, ans);
        if (root != null) ans.add(root);
        return ans;
    }

    public TreeNode dfs(TreeNode root, List<TreeNode> ans) {
        if (root == null) return null;
        if (needDelCnt == 0) return root;
        root.left = dfs(root.left, ans);
        root.right = dfs(root.right, ans);
        if (map[root.val] == 1) {
            if (root.left != null) ans.add(root.left);
            if (root.right != null) ans.add(root.right);
            needDelCnt --;
            return null;
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
