package com.lj.problem.leetcode._1;

/**
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 */
public class ReverseList {
    
    
    /**
     * 逆天的做法
     * @param head
     * @return
     */
    /*public ListNode reverseList(ListNode head) {
        ListNode h = null;
        while (head != null) {
            h = new ListNode(head.val, h);
            head = head.next;
        }
        
        return h;
    }*/
    
    /**
     * 头插法
     * @param head
     * @return
     */
    /*public ListNode reverseList(ListNode head) {
        ListNode h = new ListNode();
        ListNode cur = head;
        ListNode t;
        
        while (cur != null) {
            t = cur;
            cur = cur.next;
            t.next = h.next;
            h.next = t;
        }
        
        return h.next;
    }*/
    
    /**
     * 遍历法
     * @param c
     * @return
     */
    public ListNode reverseList(ListNode c) {
        ListNode n,p = null;
        
        while (c != null) {
            n = c.next;
            c.next = p;
            p = c;
            c = n;
        }
    
        return p;
    }
    
    /**
     * 递归1
     * @param head
     * @return
     */
    /*public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }*/
    
    
    /**
     * 递归2
     * @param args
     */
   /* ListNode newHead;
    public ListNode reverseList(ListNode head) {
        reverse(head, head.next);
        head.next = null;
        return newHead;
    }
    
    private void reverse(ListNode pre, ListNode next) {
        if (next == null) {
            newHead = pre;
            return;
        }
        reverse(next, next.next);
        next.next = pre;
    }*/
    
    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        
        ListNode reverser = new ReverseList().reverseList(head);
        
        while (reverser != null) {
            System.out.print(reverser.val + " ");
            reverser = reverser.next;
        }
    
        System.out.println();
    }
    
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    
}
