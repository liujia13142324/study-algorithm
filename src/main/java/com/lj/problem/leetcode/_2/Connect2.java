package com.lj.problem.leetcode._2;

/**
 * 117. 填充每个节点的下一个右侧节点指针 II
 * 给定一个二叉树：
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL 。
 *
 * 初始状态下，所有 next 指针都被设置为 NULL 。
 *
 * 示例 1：
 * 输入：root = [1,2,3,4,5,null,7]
 * 输出：[1,#,2,3,#,4,5,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化输出按层序遍历顺序（由 next 指针连接），'#' 表示每层的末尾。
 *
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 *
 *
 * 提示：
 * 树中的节点数在范围 [0, 6000] 内
 * -100 <= Node.val <= 100
 * 进阶：
 *
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序的隐式栈空间不计入额外空间复杂度。
 */
public class Connect2 {

    // TODO 看看别人怎么做的
    public Node connect(Node root) {
        if(root == null) return null;
        root.next = null;
        Node curr = root;
        while (curr != null){
            Node pre = null;
            Node next = null;
            while (curr != null) {
                if (curr.left != null) {
                    if (next == null) {
                        next = curr.left;
                        pre = next;
                    }
                    pre.next = curr.left;
                    pre = curr.left;
                }
                if (curr.right != null) {
                    if (next == null) {
                        next = curr.right;
                        pre = next;
                    }
                    pre.next = curr.right;
                    pre = curr.right;
                }
                curr = curr.next;
            }
            if (pre != null) {
                pre.next = null;
            }
            curr = next;
        }
        return root;
    }

    // Definition for a Node.
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
    };
}
