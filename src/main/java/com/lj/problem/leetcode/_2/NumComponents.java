package com.lj.problem.leetcode._2;

/**
 * 817. 链表组件
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个链表的头节点 head，该链表包含唯一的整数值，以及一个整数数组 nums，它是链表值的一个子集。
 *
 * 返回列表 nums 中组件的个数，这里对组件的定义为：链表中一段最长连续结点的值（该值必须在列表 nums 中）构成的集合。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入: head = [0,1,2,3], nums = [0,1,3]
 * 输出: 2
 * 解释: 链表中,0 和 1 是相连接的，且 nums 中不包含 2，所以 [0, 1] 是 nums 的一个组件，同理 [3] 也是一个组件，故返回 2。
 * 示例 2：
 *
 *
 *
 * 输入: head = [0,1,2,3,4], nums = [0,3,1,4]
 * 输出: 2
 * 解释: 链表中，0 和 1 是相连接的，3 和 4 是相连接的，所以 [0, 1] 和 [3, 4] 是两个组件，故返回 2。
 *
 *
 * 提示：
 *
 * 链表中节点数为n
 * 1 <= n <= 104
 * 0 <= Node.val < n
 * Node.val 中所有值 不同
 * 1 <= nums.length <= n
 * 0 <= nums[i] < n
 * nums 中所有值 不同
 */
public class NumComponents {

    public int numComponents(ListNode head, int[] nums) {
        boolean[] mapping = new boolean[10001];
        for (int num: nums) {
            mapping[num] = true;
        }

        int ans = 0;
        while (head != null) {
            if (mapping[head.val]) {
                ans++;
                while (head != null && mapping[head.val]) {
                    head = head.next;
                }
            } else {
                head = head.next;
            }
        }

        return ans;
    }


    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

}
