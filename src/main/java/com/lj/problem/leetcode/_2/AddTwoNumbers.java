package com.lj.problem.leetcode._2;


/**
 * 两数相加
 *
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 *
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 */
public class AddTwoNumbers {



    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode result = l1;
        ListNode pre = null;
        while (l1 != null && l2 != null) {
            if (l1.val + l2.val >= 10) {
                l1.val = l1.val + l2.val - 10;
                if (l1.next != null) {
                    l1.next.val++;
                }else {
                    l1.next = new ListNode(1);
                }
            }else {
                l1.val += l2.val;
            }
            pre = l1;
            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null && l1.val >= 10) {
            l1.val = l1.val - 10;
            if (l1.next != null) {
                l1.next.val++;
            }else {
                l1.next = new ListNode(1);
            }
            l1 = l1.next;
        }

        while (l2 != null) {
            pre.next = new ListNode(l2.val);
            l2 = l2.next;
            pre = pre.next;
        }
        return result;
    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode h = head;
        int more = 0;
        int tmp;
        while (l1 != null && l2 != null) {
            tmp = l1.val + l2.val + more;
            more = add(h, tmp);
            h = h.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        
        while (l1 != null) {
            tmp = l1.val + more;
            more = add(h, tmp);
            l1 = l1.next;
            h = h.next;
        }
        while (l2 != null) {
            tmp = l2.val + more;
            more = add(h, tmp);
            l2 = l2.next;
            h = h.next;
        }
        
        if (more > 0) {
            add(h, 1);
        }
        
        return head.next;
    }
    
    public int add(ListNode pre, int value) {
        if (value >= 10) {
            pre.next = new ListNode( value % 10);
            return 1;
        }else {
            pre.next = new ListNode(value);
            return 0;
        }
    }
    
    
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
