package com.lj.problem.leetcode._1;

/**
 * 链表相交
 */
public class GetIntersectionNode {
    
    
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA;
        ListNode b = headB;

        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        
        return a;
    }
    
    
    /* public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA;
        ListNode b = headB;
        while (a != null) {
            while (b != null){
                if (a == b) {
                    return a;
                }
                b = b.next;
            }
            b = headB;
            a = a.next;
        }
        
        return null;
    }*/

    
     public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
  }

}
