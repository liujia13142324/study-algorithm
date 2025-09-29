package com.lj.problem.leetcode._1;

import org.junit.Test;

/**
 * 206. 反转链表
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 *
 * 示例 2：
 * 输入：head = [1,2]
 * 输出：[2,1]
 *
 * 示例 3：
 * 输入：head = []
 * 输出：[]
 *
 * 提示：
 * 链表中节点的数目范围是 [0, 5000]
 * -5000 <= Node.val <= 5000
 *
 * 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？
 * tmp
 */
public class ReverseList {

    @Test
    public void test() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        ListNode reversed = reverseList2(head);
        while (reversed != null) {
            System.out.println(reversed.val);
            reversed = reversed.next;
        }
    }

    ListNode ans = null;
    public ListNode reverseList2(ListNode head) {
        if (head == null) return null;
        traverse(head).next = null;
        return ans;
    }

    private ListNode traverse(ListNode current) {
        if (current.next == null) {
            ans = current;
            return current;
        }
        ListNode pre = traverse(current.next);
        pre.next = current;
        return current;
    }
































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
