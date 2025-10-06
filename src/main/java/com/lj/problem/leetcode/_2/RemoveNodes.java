package com.lj.problem.leetcode._2;

import org.junit.Test;

/**
 * 2487. 从链表中移除节点
 * 给你一个链表的头节点 head 。
 * 移除每个右侧有一个更大数值的节点。
 * 返回修改后链表的头节点 head 。
 *
 * 示例 1：
 * 输入：head = [5,2,13,3,8]
 * 输出：[13,8]
 * 解释：需要移除的节点是 5 ，2 和 3 。
 * - 节点 13 在节点 5 右侧。
 * - 节点 13 在节点 2 右侧。
 * - 节点 8 在节点 3 右侧。
 *
 * 示例 2：
 * 输入：head = [1,1,1,1]
 * 输出：[1,1,1,1]
 * 解释：每个节点的值都是 1 ，所以没有需要移除的节点。
 *
 * 提示：
 * 给定列表中的节点数目在范围 [1, 105] 内
 * 1 <= Node.val <= 105
 */
public class RemoveNodes {

    @Test
    public void test() {
        System.out.println(removeNodes3(new ListNode(new int[]{5,2,13,3,8})).formatString());
    }

    public ListNode removeNodes3(ListNode head) {
        head = reverse(head);
        ListNode curr = head;
        while (curr != null && curr.next != null) {
            if (curr.val > curr.next.val) {
                curr.next = curr.next.next;
            }else {
                curr = curr.next;
            }
        }
        return reverse(head);
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

    public ListNode removeNodes2(ListNode head) {
        ListNode dummy = new ListNode(Integer.MAX_VALUE, head);
        ListNode pre = dummy;
        ListNode[] array = new ListNode[100001];
        int idx = 0;
        array[idx++] = pre;
        while (head != null) {
            if (head.val > pre.val) {
                int targetIdx = search(array, idx, head.val);
                array[targetIdx].next = head;
                pre = array[targetIdx];
                idx = targetIdx + 1;
                array[idx++] = head;
            }else {
                array[idx++] = head;
                pre = head;
                head = head.next;
            }
        }
        return dummy.next;
    }


    // 找到第一个 < target，并重置 r
    private int search(ListNode[] array, int r, int target) {
        int l = -1;
        while (l + 1 < r) {
            int mid = (l + r) >>> 1;
            if (array[mid].val >= target) {
                l = mid;
            }else {
                r = mid;
            }
        }
        return l;
    }

    public ListNode removeNodes(ListNode head) {
        ListNode dummy = new ListNode(Integer.MAX_VALUE, head);
        ListNode pre = dummy;
        while (head != null) {
            if (head.val > pre.val) {
                ListNode p = dummy;
                while (p.next.val >= head.val) p = p.next;
                p.next = head;
                pre = p;
            }else {
                pre = head;
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
