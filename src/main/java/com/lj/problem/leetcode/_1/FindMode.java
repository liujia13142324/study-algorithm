package com.lj.problem.leetcode._1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 501. 二叉搜索树中的众数
 * 给你一个含重复值的二叉搜索树（BST）的根节点 root ，找出并返回 BST 中的所有 众数（即，出现频率最高的元素）。
 * 如果树中有不止一个众数，可以按 任意顺序 返回。
 *
 * 假定 BST 满足如下定义：
 * 结点左子树中所含节点的值 小于等于 当前节点的值
 * 结点右子树中所含节点的值 大于等于 当前节点的值
 * 左子树和右子树都是二叉搜索树
 *
 * 示例 1：
 * 输入：root = [1,null,2,2]
 * 输出：[2]
 *
 * 示例 2：
 * 输入：root = [0]
 * 输出：[0]
 *
 * 提示：
 *
 * 树中节点的数目在范围 [1, 104] 内
 * -105 <= Node.val <= 105
 *
 */
public class FindMode {

    @Test
    public void test() {
//        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(1);
//        System.out.println(Arrays.toString(findMode2(root)));

    }

    int pre = Integer.MAX_VALUE;
    int cnt = 0;
    int[] ans2;
    // 两遍中序遍历，第一遍可以求出大小，就不用 ans 数组加复制了
    public int[] findMode3(TreeNode root) {
        dfs3(root);
        ans2 = new int[idx];
        cnt = 0;
        idx = 0;
        pre = Integer.MAX_VALUE;
        dfs3(root);
        return ans2;
    }

    public void dfs3(TreeNode node) {
        if (node == null) return;
        dfs3(node.left);
        if (node.val == pre) {
            cnt++;
        } else {
            cnt = 1;
        }
        if (cnt > maxCnt) {
            maxCnt = cnt;
            idx = 1;
        } else if (cnt == maxCnt){
            if (ans2 != null) ans2[idx] = node.val;
            idx++;
        }
        pre = node.val;
        dfs3(node.right);
    }

    public int[] findMode2(TreeNode root) {
        dfs2(root);
        return Arrays.copyOf(ans, idx);
    }
    public void dfs2(TreeNode node) {
        if (node == null) return;
        dfs2(node.left);
        if (node.val == pre) {
            cnt++;
        } else {
            cnt = 1;
        }
        if (cnt > maxCnt) {
            ans[0] = node.val;
            maxCnt = cnt;
            idx = 1;
        } else if (cnt == maxCnt){
            ans[idx++] = node.val;
        }
        pre = node.val;
        dfs2(node.right);
    }


    int[] map = new int[200001];
    int[] ans = new int[10000];
    int maxCnt = 0;
    int idx = 0;
    public int[] findMode(TreeNode root) {
        dfs(root);
        return Arrays.copyOf(ans, idx);
    }

    public void dfs(TreeNode node) {
        if (node == null) return;
        dfs(node.left);
        map[node.val + 100000]++;
        if (map[node.val + 100000] > maxCnt) {
            ans[0] = node.val;
            idx = 1;
            maxCnt = map[node.val + 100000];
        } else if (map[node.val + 100000] == maxCnt) {
            ans[idx++] = node.val;
        }
        dfs(node.right);
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
