package com.lj.problem.leetcode._1;

import org.junit.Test;

/**
 * 234. 回文链表
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 *
 * 示例 1：
 * 输入：head = [1,2,2,1]
 * 输出：true
 *
 * 示例 2：
 * 输入：head = [1,2]
 * 输出：false
 *
 * 提示：
 *
 * 链表中节点数目在范围[1, 105] 内
 * 0 <= Node.val <= 9
 *
 * 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 */
public class IsPalindrome3 {

    @Test
    public void test() {
        System.out.println(isPalindrome(new ListNode(new int[]{1})));
    }

    public boolean isPalindrome(ListNode head) {
        ListNode s = head, f = head;
        while (f != null && f.next != null) {
            f = f.next.next;
            s = s.next;
        }
        f = reverse(s);
        while (head != s) {
            if (head.val != f.val) return false;
            head = head.next;
            f = f.next;
        }
        return true;
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
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
