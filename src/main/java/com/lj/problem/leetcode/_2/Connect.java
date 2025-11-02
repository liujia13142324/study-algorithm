package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Stack;

/**
 * 116. 填充每个节点的下一个右侧节点指针
 * 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 *
 * 初始状态下，所有 next 指针都被设置为 NULL。
 *
 * 示例 1：
 * 输入：root = [1,2,3,4,5,6,7]
 * 输出：[1,#,2,3,#,4,5,6,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化的输出按层序遍历排列，同一层节点由 next 指针连接，'#' 标志着每一层的结束。
 *
 *  示例 2:
 * 输入：root = []
 * 输出：[]
 *
 * 提示：
 * 树中节点的数量在 [0, 212 - 1] 范围内
 * -1000 <= node.val <= 1000
 *
 * 进阶：
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 */
public class Connect {

    @Test
    public void test() {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        connect(root);
        System.out.println(root);
    }

    // TODO 看看别人怎么做的
    public Node connect2(Node root) {
        if (root == null) return null;
        root.next = null;
        Node curr = root;
        while (curr.left != null) {
            Node pre = null;
            Node dummy = curr;
            while (curr != null) {
                if (pre != null) pre.next = curr.left;
                curr.left.next = curr.right;
                pre = curr.right;
                curr = curr.next;
            }
            pre.next = null;
            curr = dummy.left;
        }
        return root;
    }

    public Node connect(Node root) {
        if (root == null) return null;
        ArrayDeque<Node> stack = new ArrayDeque<>();
        stack.addLast(root);
        while (!stack.isEmpty()) {
            int size = stack.size();
            Node pre = null;
            while (size --> 0) {
                Node pop = stack.pollFirst();
                pop.next = pre;
                pre = pop;
                if (pop.right != null) stack.addLast(pop.right);
                if (pop.left != null) stack.addLast(pop.left);
            }
        }
        return root;
    }


    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
