package com.lj.problem.leetcode._2;

import org.junit.Test;

/**
 * 143. 重排链表
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 *
 * L0 → L1 → … → Ln - 1 → Ln
 * 请将其重新排列后变为：
 *
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例 1：
 *
 * 输入：head = [1,2,3,4]
 * 输出：[1,4,2,3]
 *
 * 示例 2：
 * 输入：head = [1,2,3,4,5]
 * 输出：[1,5,2,4,3]
 *
 *
 * 提示：
 * 链表的长度范围为 [1, 5 * 104]
 * 1 <= node.val <= 1000
 */
public class ReorderList {

    @Test
    public void test() {
        ListNode head = new ListNode(new int[]{1, 2, 3, 4, 5, 6});
        reorderList2(head);
        System.out.println(head.formatString());
    }

    public void reorderList2(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode next = slow.next;
        slow.next = null;
        slow = next;

        slow = reverseList(slow);

        ListNode p = head;
        while (slow != null) {
            next = slow.next;
            ListNode next2 = p.next;
            p.next = slow;
            slow.next = next2;

            slow = next;
            p = next2;
        }
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    public void reorderList(ListNode head) {
        if (head.next == null || head.next.next == null) return;
        ListNode l = head, r = head;
        do {
            while (r.next != null && r.next.next != null) {
                r = r.next;
            }
            ListNode n = l.next;
            l.next = r.next;
            r.next = null;
            r = l.next;
            l = n;
            r.next = l;
            r = l;
        }while (r.next != null && r.next.next != null);
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
        ListNode(int[] arr){
            val = arr[0];
            ListNode curr = this;
            for(int i = 1; i < arr.length; i++){
                curr.next = new ListNode(arr[i]);
                curr = curr.next;
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
