package com.lj.problem.leetcode._1;

/**
 * 1290. 二进制链表转整数
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个单链表的引用结点 head。链表中每个结点的值不是 0 就是 1。已知此链表是一个整数数字的二进制表示形式。
 *
 * 请你返回该链表所表示数字的 十进制值 。
 *
 * 最高位 在链表的头部。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：head = [1,0,1]
 * 输出：5
 * 解释：二进制数 (101) 转化为十进制数 (5)
 * 示例 2：
 *
 * 输入：head = [0]
 * 输出：0
 *
 *
 * 提示：
 *
 * 链表不为空。
 * 链表的结点总数不超过 30。
 * 每个结点的值不是 0 就是 1。
 */
public class GetDecimalValue {

    public int getDecimalValue(ListNode head) {
        int ans = 0;
        while (head != null) {
            ans <<= 1;
            ans += head.val;
            head = head.next;
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
