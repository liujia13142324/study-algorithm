package com.lj.problem.leetcode._2;

import org.junit.Test;

/**
 * 92. 反转链表 II
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5], left = 2, right = 4
 * 输出：[1,4,3,2,5]
 *
 * 示例 2：
 * 输入：head = [5], left = 1, right = 1
 * 输出：[5]
 *
 * 提示：
 * 链表中节点数目为 n
 * 1 <= n <= 500
 * -500 <= Node.val <= 500
 * 1 <= left <= right <= n
 *
 * 进阶： 你可以使用一趟扫描完成反转吗？
 */
public class ReverseBetween {

    @Test
    public void test() {
        System.out.println(reverseBetween(new ListNode(new int[]{1,2,3,4,5}), 2,4).formatString());
//        System.out.println(reverseBetween(new ListNode(new int[]{5}), 1,1));
//        System.out.println(reverseBetween(new ListNode(new int[]{3, 5}), 1,2).formatString());
    }

    // TODO 看教程，以及别人的写法
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right) return head;
        int i = 1;
        ListNode p = head;
        while (i < left-1) {
            p = p.next;
            i++;
        }
        ListNode start = p;
        ListNode c = p.next;
        ListNode n;
        if (c == null) {
            return head;
        }
        while (i < right) {
            n = c.next;
            c.next = p;
            p = c;
            c = n;
            i++;
        }

        if (left == 1) {
            head.next = null;
            return p;
        }

        start.next.next = c;
        start.next = p;
        return head;
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
