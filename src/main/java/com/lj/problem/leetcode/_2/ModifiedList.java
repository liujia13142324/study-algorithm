package com.lj.problem.leetcode._2;

import com.lj.problem.leetcode._1.RemoveElements;

import java.util.Arrays;

/**
 * 3217. 从链表中移除在数组中存在的节点
 * 给你一个整数数组 nums 和一个链表的头节点 head。从链表中移除所有存在于 nums 中的节点后，返回修改后的链表的头节点。
 *
 * 示例 1：
 * 输入： nums = [1,2,3], head = [1,2,3,4,5]
 * 输出： [4,5]
 * 解释：
 * 移除数值为 1, 2 和 3 的节点。
 *
 * 示例 2：
 * 输入： nums = [1], head = [1,2,1,2,1,2]
 * 输出： [2,2,2]
 * 解释：
 * 移除数值为 1 的节点。
 *
 * 示例 3：
 * 输入： nums = [5], head = [1,2,3,4]
 * 输出： [1,2,3,4]
 * 解释：
 * 链表中不存在值为 5 的节点。
 *
 * 提示：
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 * nums 中的所有元素都是唯一的。
 * 链表中的节点数在 [1, 105] 的范围内。
 * 1 <= Node.val <= 105
 * 输入保证链表中至少有一个值没有在 nums 中出现过。
 */
public class ModifiedList {

    public ListNode modifiedList(int[] nums, ListNode head) {
        int[] map = new int[100001];
        for (int n: nums) {
            map[n] = 1;
        }
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;
        while (head != null) {
            if (map[head.val] == 1) {
                prev.next = head.next;
                head = head.next;
            }else {
                prev = head;
                head = head.next;
            }
        }
        return dummy.next;
    }

    public ListNode modifiedList2(int[] nums, ListNode head) {
        int[] map = new int[100001];
        for (int n: nums) {
            map[n] = 1;
        }
        ListNode dummy = new ListNode(0, head);
        ListNode cur = dummy;
        while (cur.next != null) {
            if (map[cur.next.val] == 1) {
                cur.next = cur.next.next; // 删除
            } else {
                cur = cur.next; // 向后移动
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
