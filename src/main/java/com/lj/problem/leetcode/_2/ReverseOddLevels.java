package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.ArrayDeque;

/**
 * 2415. 反转二叉树的奇数层
 * 给你一棵 完美 二叉树的根节点 root ，请你反转这棵树中每个 奇数 层的节点值。
 *
 * 例如，假设第 3 层的节点值是 [2,1,3,4,7,11,29,18] ，那么反转后它应该变成 [18,29,11,7,4,3,1,2] 。
 * 反转后，返回树的根节点。
 *
 * 完美 二叉树需满足：二叉树的所有父节点都有两个子节点，且所有叶子节点都在同一层。
 *
 * 节点的 层数 等于该节点到根节点之间的边数。
 *
 *
 * 示例 1：
 * 输入：root = [2,3,5,8,13,21,34]
 * 输出：[2,5,3,8,13,21,34]
 * 解释：
 * 这棵树只有一个奇数层。
 * 在第 1 层的节点分别是 3、5 ，反转后为 5、3 。
 *
 * 示例 2：
 * 输入：root = [7,13,11]
 * 输出：[7,11,13]
 * 解释：
 * 在第 1 层的节点分别是 13、11 ，反转后为 11、13 。
 *
 * 示例 3：
 * 输入：root = [0,1,2,0,0,0,0,1,1,1,1,2,2,2,2]
 * 输出：[0,2,1,0,0,0,0,2,2,2,2,1,1,1,1]
 * 解释：奇数层由非零值组成。
 * 在第 1 层的节点分别是 1、2 ，反转后为 2、1 。
 * 在第 3 层的节点分别是 1、1、1、1、2、2、2、2 ，反转后为 2、2、2、2、1、1、1、1 。
 *
 *
 * 提示：
 * 树中的节点数目在范围 [1, 214] 内
 * 0 <= Node.val <= 105
 * root 是一棵 完美 二叉树
 */
public class ReverseOddLevels {


    @Test
    public void test() {
        /*TreeNode treeNode = new TreeNode(2);
        treeNode.left = new TreeNode(3);
        treeNode.right = new TreeNode(5);
        treeNode.left.left = new TreeNode(8);
        treeNode.left.right = new TreeNode(13);
        treeNode.right.left = new TreeNode(21);
        treeNode.right.right = new TreeNode(34);
        System.out.println(reverseOddLevels(treeNode));*/

        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        arrayDeque.addLast(1);
        arrayDeque.addLast(2);
        arrayDeque.addLast(3);
        arrayDeque.addFirst(4);
        arrayDeque.addFirst(5);
        arrayDeque.addFirst(6);
        for (Integer i : arrayDeque) {
            System.out.println(i);
        }

        while (!arrayDeque.isEmpty()) {
            System.out.println(arrayDeque.pollFirst());
        }

    }

    // TODO 看看别人怎么做的
    /**
     * 数组的写法
     * 完美二叉树，2^14以内的节点，最多 13 层，该层元素个数为 2^(13-1) = 4096，pre 层最多为 2048
     * @param root
     * @return
     */
    public TreeNode reverseOddLevels(TreeNode root) {
        int level = 1;
        TreeNode curr = root.left;
        TreeNode[] pre = new TreeNode[] {root.left, root.right};
        while (curr != null) {
            TreeNode[] next = new TreeNode[2 << level];
            int l = 0;
            int r = pre.length - 1;
            int start = 0;
            int end = next.length - 1;
            while (l < r) {
                if ((level & 1) == 1) {
                    int val = pre[l].val;
                    pre[l].val = pre[r].val;
                    pre[r].val = val;
                }
                if (pre[l].left != null) {
                    next[start++] = pre[l].left;
                    next[start++] = pre[l].right;
                    next[end--] = pre[r].right;
                    next[end--] = pre[r].left;
                }
                l++;
                r--;
            }
            curr = curr.left;
            pre = next;
            level ++;
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
