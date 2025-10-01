package com.lj.problem.leetcode._3;

import com.lj.problem.leetcode._2.ReverseBetween;
import org.junit.Test;

/**
 * 25. K 个一组翻转链表
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]

 * 示例 2：
 * 输入：head = [1,2,3,4,5], k = 3
 * 输出：[3,2,1,4,5]
 *
 * 提示：
 * 链表中的节点数目为 n
 * 1 <= k <= n <= 5000
 * 0 <= Node.val <= 1000
 *
 * 进阶：你可以设计一个只用 O(1) 额外内存空间的算法解决此问题吗？
 */
public class ReverseKGroup {

    @Test
    public void test() {
        System.out.println(reverseKGroup3(new ListNode(new int[]{1,2,3,4,5}), 3).formatString());
        System.out.println(reverseKGroup3(new ListNode(new int[]{1,2,3,4,5,6}), 3).formatString());

        System.out.println(reverseKGroup(new ListNode(new int[]{1,2,3,4,5}), 3).formatString());
        System.out.println(reverseKGroup(new ListNode(new int[]{1,2,3,4,5,6}), 3).formatString());
    }

    // 这种最快，自己写的感觉还可以的，是 reverseKGroup3 和 reverseKGroup
    public ListNode reverseKGroup4(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode pre = dummy;
        ListNode end = dummy;

        while (end.next != null) {
            for (int i = 0; i < k && end != null; i++) end = end.next;
            if (end == null) break;
            ListNode start = pre.next;
            ListNode next = end.next;
            end.next = null;
            pre.next = reverse(start);
            start.next = next;
            pre = start;

            end = pre;
        }
        return dummy.next;
    }

    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

    public ListNode reverseKGroup3(ListNode head, int k) {
        int i = 0;
        boolean newHead = false;
        ListNode joint = new ListNode(Integer.MIN_VALUE, head);
        ListNode nextJoint = null;
        ListNode pre = joint;
        ListNode cur = pre.next;
        ListNode next;

        while (cur != null) {
            pre = joint;
            for (; i < k && cur != null; i++) {
                next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }

            if (i != k) {
                break;
            }

            if (!newHead) {
                head = pre;
                newHead = true;
            }

            nextJoint = joint.next;
            joint.next.next = cur;
            joint.next = pre;
            joint = nextJoint;
            i = 0;
        }

        if (i % k != 0) {
            // 反转多余的部分
            cur = pre;
            pre = null;
            while (cur != joint) {
                next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
        }

        return head;
    }


    public ListNode reverseKGroup2(ListNode head, int k) {
        if (head.next == null) return head;

        ListNode p = head;
        ListNode newHead = null;
        ListNode nextHead = head;
        while (p != null) {
            for (int i = 1; i < k && p != null; i++) {
                p = p.next;
            }
            if (p == null) {
                break;
            }

            ListNode pre = nextHead;
            ListNode curr = pre.next;
            ListNode next;

            for (int i = 1; i < k; i++) {
                next = curr.next;
                curr.next = pre;
                pre = curr;
                curr = next;
            }

            if (newHead == null) {
                head.next = curr;
                newHead = pre;
                p = curr;
                nextHead = curr;
            } else {
                nextHead.next = curr;
                head.next = pre;
                head = nextHead;
                p = curr;
                nextHead = curr;
            }
        }

        return newHead == null ? head : newHead;
    }


    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0, head);
        ListNode idx = dummy;
        ListNode joint = dummy;
        while (true) {
            for (int i = 0; i < k && idx != null; i++) {
                idx = idx.next;
            }
            if (idx == null) break;
            ListNode nextJoint = joint.next;
            ListNode pre = nextJoint;
            ListNode curr = pre.next;
            for (int i = 1; i < k; i++) {
                ListNode next = curr.next;
                curr.next = pre;
                pre = curr;
                curr = next;
            }
            nextJoint.next = curr;
            joint.next = pre;
            joint = nextJoint;
            idx = nextJoint;
        }
        return dummy.next;
    }




    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
        ListNode(int[] arr){
            val = arr[0];
            ListNode cur = this;
            for (int i = 1; i < arr.length; i++) {
                cur.next = new ListNode(arr[i]);
                cur = cur.next;
            }
        }

        public String formatString() {
            ListNode cur = this;
            StringBuffer sb = new StringBuffer();
            while (cur != null) {
                sb.append(cur.val).append(" ");
                cur = cur.next;
            }
            return sb.toString();
        }
    }

}
