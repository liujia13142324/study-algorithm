package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.*;

/**
 * 199. 二叉树的右视图
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 * 示例 1：
 * 输入：root = [1,2,3,null,5,null,4]
 * 输出：[1,3,4]
 * 解释：
 *
 * 示例 2：
 * 输入：root = [1,2,3,4,null,null,null,5]
 * 输出：[1,3,4,5]
 * 解释：
 *
 * 示例 3：
 * 输入：root = [1,null,3]
 * 输出：[1,3]
 *
 * 示例 4：
 * 输入：root = []
 * 输出：[]
 *
 * 提示:
 *
 * 二叉树的节点个数的范围是 [0,100]
 * -100 <= Node.val <= 100
 */
public class RightSideView {

    @Test
    public void test() {
        /*TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(4);
        System.out.println(rightSideView3(root));*/
        int[] num = new int[5];
        int idx = -1;
        for (int i = 1; i <= 20; i++) {
            num[idx = nextIndex(idx, num.length)] = i;
            System.out.println(Arrays.toString(num));
        }
    }

    int maxDepth2 = 0;
    List<Integer> ans = new ArrayList();
    public List<Integer> rightSideView2(TreeNode root) {
        dfs(root, 0);
        return ans;
    }
    public void dfs(TreeNode root, int depth) {
        if (root == null) return;
        if (maxDepth2 == depth) {
            ans.add(root.val);
            maxDepth2++;
        }
        dfs(root.right, depth + 1);
        dfs(root.left, depth + 1);
    }


    int maxDepth = Integer.MIN_VALUE;
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList();
        dfs(ans, root, 0);
        return ans;
    }

    public void dfs(List<Integer> ans, TreeNode root, int depth) {
        if (root == null) return;
        if (depth > maxDepth) {
            ans.add(root.val);
            maxDepth = depth;
        }
        dfs(ans, root.right, depth + 1);
        dfs(ans, root.left, depth + 1);
    }

    public List<Integer> rightSideView4(TreeNode root) {
        if (root == null) return Collections.emptyList();
        ArrayDeque<TreeNode> stack = new ArrayDeque();
        stack.addLast(root);
        List<Integer> ans = new ArrayList<>();
        while (!stack.isEmpty()) {
            int size = stack.size();
            ans.add(stack.peekLast().val);
            while (size --> 0) {
                TreeNode node = stack.pollFirst();
                if (node.left != null) stack.addLast(node.left);
                if (node.right != null) stack.addLast(node.right);
            }
        }
        return ans;
    }

    /**
     * @param root
     * @return
     */
    public List<Integer> rightSideView3(TreeNode root) {
        if (root == null) return Collections.emptyList();
        int length = 64;
        TreeNode[] arr = new TreeNode[length];
        int idx = -1;
        int start = -1;
        arr[++idx] = root;
        List<Integer> ans = new ArrayList<>();
        while (start != idx) {
            int size = (idx - start + length) % length;
            ans.add(arr[idx].val);
            while (size --> 0) {
                TreeNode node = arr[start = nextIndex(start, length)];
                if (node.left != null) arr[idx = nextIndex(idx, length)] = node.left;
                if (node.right != null) arr[idx = nextIndex(idx, length)] = node.right;
            }
        }
        return ans;
    }

    private int nextIndex(int curr, int len) {
        return ++curr % len;
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
