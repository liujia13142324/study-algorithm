package com.lj.problem.leetcode._2;

import org.junit.Test;

/**
 *
 82. 删除排序链表中的重复元素 II
 给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。

 示例 1：
 输入：head = [1,2,3,3,4,4,5]
 输出：[1,2,5]

 示例 2：
 输入：head = [1,1,1,2,3]
 输出：[2,3]

 提示：
 链表中节点数目在范围 [0, 300] 内
 -100 <= Node.val <= 100
 题目数据保证链表已经按升序 排列
 */
public class DeleteDuplicates {

    @Test
    public void test() {
        ListNode listNode = deleteDuplicates(new ListNode(new int[]{1, 1}));
        System.out.println(listNode != null ? listNode.formatString(): null);
    }

    public ListNode deleteDuplicates2(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode curr = dummy;
        while (curr.next != null && curr.next.next != null) {
            int val = curr.next.val;
            if (curr.next.next.val == val) {
                while (curr.next != null && curr.next.val == val) {
                    curr.next = curr.next.next;
                }
            }else {
                curr = curr.next;
            }
        }
        return dummy.next;
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;

        while (head != null && head.next != null) {
            if (head.val == head.next.val) {
                int val = head.val;
                while (head != null && head.val == val) {
                    prev.next = head.next;
                    head = head.next;
                }
            }else {
                prev = head;
                head = head.next;
            }
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
