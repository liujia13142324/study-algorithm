package com.lj.problem.leetcode._2;

/**
 * 148. 排序链表
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。

 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]

 * 输入：head = [-1,5,3,4,0]
 * 输出：[-1,0,3,4,5]

 * 输入：head = []
 * 输出：[]

 * 提示：

 * 链表中节点的数目在范围 [0, 5 * 104] 内
 * -105 <= Node.val <= 105

 * 进阶：你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 */
public class SortList {
    
    public ListNode sortList(ListNode head) {
        return mergeSort(head, null);
    }
    
    public ListNode mergeSort(ListNode head, ListNode tail) {
        
        if (head == null) {
            return head;
        }
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        
        ListNode fast = head;
        ListNode slow = head;
        while(fast != tail) {
            fast = fast.next;
            slow = slow.next;
            if (fast != tail) fast = fast.next;
        }
        
        ListNode left = mergeSort(head, slow);
        ListNode right = mergeSort(slow, tail);
        return merge(left, right);
    }
    
    public ListNode merge(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode h = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                h.next = l1;
                l1 = l1.next;
            }else {
                h.next = l2;
                l2 = l2.next;
            }
            h = h.next;
        }
        
        if (l1 != null) {
            h.next = l1;
        }else if (l2 != null) {
            h.next = l2;
        }
        
        return head.next;
    }
    
    
   /* public ListNode sortList(ListNode head) {
        
        if (head == null) {
            return null;
        }
        
        ListNode subList = sortList(head.next);
        return merge(head, subList);
    }
    
    public ListNode merge(ListNode head, ListNode subList) {
        if (subList == null) {
            return head;
        }
        if (head.val <= subList.val) {
            head.next = subList;
            return head;
        }
        
        ListNode h = subList;
        ListNode tmp;
        while (h.next != null) {
            tmp = h.next;
            if (tmp != null && head.val <= tmp.val) {
                h.next = head;
                head.next = tmp;
                return subList;
            }
            h = h.next;
        }
        
        head.next = null;
        h.next = head;
        
        return subList;
    }*/
    
    
    
    public class ListNode {
        int val;
        ListNode next;
        
        ListNode() {
        }
        
        ListNode(int val) {
            this.val = val;
        }
        
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
    
}
