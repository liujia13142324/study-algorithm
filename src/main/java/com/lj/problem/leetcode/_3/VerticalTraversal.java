package com.lj.problem.leetcode._3;

import org.junit.Test;

import java.util.*;

/**
 * 987. 二叉树的垂序遍历
 * 给你二叉树的根结点 root ，请你设计算法计算二叉树的 垂序遍历 序列。
 * 对位于 (row, col) 的每个结点而言，其左右子结点分别位于 (row + 1, col - 1) 和 (row + 1, col + 1) 。树的根结点位于 (0, 0) 。
 * 二叉树的 垂序遍历 从最左边的列开始直到最右边的列结束，按列索引每一列上的所有结点，形成一个按出现位置从上到下排序的有序列表。如果同行同列上有多个结点，则按结点的值从小到大进行排序。
 * 返回二叉树的 垂序遍历 序列。

 *
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[9],[3,15],[20],[7]]
 * 解释：
 * 列 -1 ：只有结点 9 在此列中。
 * 列  0 ：只有结点 3 和 15 在此列中，按从上到下顺序。
 * 列  1 ：只有结点 20 在此列中。
 * 列  2 ：只有结点 7 在此列中。

 * 示例 2：
 * 输入：root = [1,2,3,4,5,6,7]
 * 输出：[[4],[2],[1,5,6],[3],[7]]
 * 解释：
 * 列 -2 ：只有结点 4 在此列中。
 * 列 -1 ：只有结点 2 在此列中。
 * 列  0 ：结点 1 、5 和 6 都在此列中。
 *           1 在上面，所以它出现在前面。
 *           5 和 6 位置都是 (2, 0) ，所以按值从小到大排序，5 在 6 的前面。
 * 列  1 ：只有结点 3 在此列中。
 * 列  2 ：只有结点 7 在此列中。
 *
 * 示例 3：
 * 输入：root = [1,2,3,4,6,5,7]
 * 输出：[[4],[2],[1,5,6],[3],[7]]
 * 解释：
 * 这个示例实际上与示例 2 完全相同，只是结点 5 和 6 在树中的位置发生了交换。
 * 因为 5 和 6 的位置仍然相同，所以答案保持不变，仍然按值从小到大排序。
 *
 * 提示：
 *
 * 树中结点数目总数在范围 [1, 1000] 内
 * 0 <= Node.val <= 1000
 */
public class VerticalTraversal {

    @Test
    public void test() {
        TreeNode treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(9);
        treeNode.right = new TreeNode(20);
        treeNode.right.left = new TreeNode(15);
        treeNode.right.right = new TreeNode(7);
        System.out.println(verticalTraversal(treeNode));


        treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(1);
        treeNode.right = new TreeNode(4);
        treeNode.left.left = new TreeNode(0);
        treeNode.left.right = new TreeNode(2);
        treeNode.right.left = new TreeNode(2);
        System.out.println(verticalTraversal(treeNode));

        StringBuilder sb = new StringBuilder();
        sb.append('a');
    }


    // TODO 看看别人怎么做的
    DoubleLinkedNode<List<LevelVal>> head;
    int minX = Integer.MAX_VALUE;

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        verticalTraversal(root, new DoubleLinkedNode<>(new ArrayList<>()), 0, 0);
        List<List<Integer>> ans = new ArrayList<>();
        while (head != null) {
            head.val.sort((o1, o2) -> {
                if (o1.y == o2.y) {
                    return o1.val - o2.val;
                }
                return o1.y - o2.y;
            });
            List<Integer> list = new ArrayList<>();
            for (LevelVal val: head.val) {
                list.add(val.val);
            }
            ans.add(list);
            head = head.next;
        }
        return ans;
    }

    private void verticalTraversal(TreeNode root, DoubleLinkedNode<List<LevelVal>> curr, int x, int y) {
        // 直接添加，最后再排序
        curr.val.add(new LevelVal(root.val, y));
        if (x < minX) {
            minX = x;
            head = curr;
        }
        if (root.left != null) {
            if (curr.pre == null) {
                curr.pre = new DoubleLinkedNode<>(new ArrayList<>());
                curr.pre.next = curr;
            }
            verticalTraversal(root.left, curr.pre, x-1, y+1);
        }

        if (root.right != null) {
            if (curr.next == null) {
                curr.next = new DoubleLinkedNode<>(new ArrayList<>());
                curr.next.pre = curr;
            }
            verticalTraversal(root.right, curr.next, x+1, y+1);
        }
    }

    public class DoubleLinkedNode<T> {
        T val;
        DoubleLinkedNode pre;
        DoubleLinkedNode next;
        public DoubleLinkedNode(T val) {
            this.val = val;
        }
    }

    public class LevelVal {
        int val;
        int y;
        public LevelVal(int val, int y) {
            this.val = val;
            this.y = y;
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

}

