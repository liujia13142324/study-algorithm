package com.lj.problem.leetcode._2;

/**
 * 19. 删除链表的倒数第 N 个结点
 * 提示
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 *
 * 示例 2：
 * 输入：head = [1], n = 1
 * 输出：[]
 *
 * 示例 3：
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 *
 * 提示：
 *
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 *
 * 进阶：你能尝试使用一趟扫描实现吗？
 */
public class RemoveNthFromEnd {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode r = dummy;
        for (int i = 0; i < n; i++) {
            r = r.next;
        }
        ListNode l = dummy;
        while (r.next != null) {
            r = r.next;
            l = l.next;
        }
        l.next = l.next.next;
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
