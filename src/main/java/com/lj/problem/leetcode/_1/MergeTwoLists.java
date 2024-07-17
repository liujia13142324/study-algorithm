package com.lj.problem.leetcode._1;

import org.aspectj.weaver.loadtime.definition.Definition;

/** 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的
 */
public class MergeTwoLists {
    
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    
    
    class Solution {
    
        /**
         * 头插法
         */
        /*public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            ListNode newOne = new ListNode();
        
            while (l1 == null || l2 == null) {
                if (l1.val > l2.val) {
                    newOne.next = l2;
                    l2 = l2.next;
                }else {
                    newOne.next = l1;
                    l1 = l1.next;
                }
                newOne = newOne.next;
            }
        
            newOne.next = l1 == null ? l2 : l1;
        
            return newOne;
        }*/
    
        /**
         * 递归写法
         * @param l1
         * @param l2
         * @return
         */
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            
            if (l1 == null) {
                return l2;
            }else if (l2 == null) {
                return l1;
            }else  if (l1.val < l2.val) {
                l1.next = mergeTwoLists(l1.next, l2);
                return l1;
            }else {
                l2.next = mergeTwoLists(l2.next, l1);
                return l2;
            }
        }
        
    }
}
