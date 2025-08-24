package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.Arrays;

/**
 * 3439. 重新安排会议得到最多空余时间 I
 *
 * 给你一个整数 eventTime 表示一个活动的总时长，这个活动开始于 t = 0 ，结束于 t = eventTime 。
 * 同时给你两个长度为 n 的整数数组 startTime 和 endTime 。它们表示这次活动中 n 个时间 没有重叠 的会议，其中第 i 个会议的时间为 [startTime[i], endTime[i]] 。
 * 你可以重新安排 至多 k 个会议，安排的规则是将会议时间平移，且保持原来的 会议时长 ，你的目的是移动会议后 最大化 相邻两个会议之间的 最长 连续空余时间。
 * 移动前后所有会议之间的 相对 顺序需要保持不变，而且会议时间也需要保持互不重叠。
 * 请你返回重新安排会议以后，可以得到的 最大 空余时间。
 *
 * 注意，会议 不能 安排到整个活动的时间以外。
 *
 * 示例 1：
 * 输入：eventTime = 5, k = 1, startTime = [1,3], endTime = [2,5]
 * 输出：2
 * 解释：
 * 将 [1, 2] 的会议安排到 [2, 3] ，得到空余时间 [0, 2] 。
 *
 * 示例 2：
 * 输入：eventTime = 10, k = 1, startTime = [0,2,9], endTime = [1,4,10]
 * 输出：6
 * 解释：
 * 将 [2, 4] 的会议安排到 [1, 3] ，得到空余时间 [3, 9] 。
 *
 * 示例 3：
 * 输入：eventTime = 5, k = 2, startTime = [0,1,2,3,4], endTime = [1,2,3,4,5]
 * 输出：0
 * 解释：
 * 活动中的所有时间都被会议安排满了。
 *
 * 1 <= eventTime <= 109
 * n == startTime.length == endTime.length
 * 2 <= n <= 105
 * 1 <= k <= n
 * 0 <= startTime[i] < endTime[i] <= eventTime
 * endTime[i] <= startTime[i + 1] 其中 i 在范围 [0, n - 2] 之间。
 *
 *
 */
public class MaxFreeTime {

    @Test
    public void test1() {
        System.out.println(maxFreeTime(5, 1, new int[]{1,3}, new int[]{2,5}));
        System.out.println(maxFreeTime(5, 2, new int[]{0,1,2,3,4}, new int[]{1,2,3,4,5}));
        System.out.println(maxFreeTime(10, 1, new int[]{0,2,9}, new int[]{1,4,10}));
        System.out.println(maxFreeTime(21, 2, new int[]{18,20}, new int[]{20,21}));
        System.out.println(maxFreeTime(99, 1, new int[]{7,21,25}, new int[]{13,25,78}));
        System.out.println();
        System.out.println(maxFreeTime2(5, 1, new int[]{1,3}, new int[]{2,5}));
        System.out.println(maxFreeTime2(5, 2, new int[]{0,1,2,3,4}, new int[]{1,2,3,4,5}));
        System.out.println(maxFreeTime2(10, 1, new int[]{0,2,9}, new int[]{1,4,10}));
        // 18, 0, 0
        System.out.println(maxFreeTime2(21, 2, new int[]{18,20}, new int[]{20,21}));
        // 7-0, 21-13, 25-25, 99-78
        // 7, 8, 0, 21
        System.out.println(maxFreeTime2(99, 1, new int[]{7,21,25}, new int[]{13,25,78}));
    }


    // 1 0 1 0 0
    // 1 A 1 B B
    // [1,2), [3,5)
    // 1, 1, 0, win=2
    // 0 1 0 0 1 1 1 1 1 0
    // A 1 B B 1 1 1 1 1 C
    // 0:[0, 1) 1:[2, 4) 2:[9, 10)
    // 0, 1, 5, 0, win=2
    // 1 0 0 0 1 1 1 1 1 0
    // 1 A B B 1 1 1 1 1 C
    // 0:[1, 2) 1:[2, 4) 2:[9, 10)
    // 1, 0, 5, 0, win=2
    public int maxFreeTime2(int eventTime, int k, int[] startTime, int[] endTime) {
        int len = startTime.length;
        int[] help = new int[len + 1];
        help[0] = startTime[0];
        for (int i = 1; i < len; i++) {
            help[i] = startTime[i] - endTime[i - 1];
        }
        help[len] = eventTime - endTime[len -1];
        int l = 0, r = 0, sum = 0, ans, win = k + 1;
        while (r < win) {
            sum += help[r++];
        }
        ans = sum;
        while (r < help.length) {
            sum += help[r++];
            sum -= help[l++];
            ans = Math.max(ans, sum);
        }
        return ans;
    }



    public int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
        int len = startTime.length;
        int[] gap = new int[len + 1];
        gap[0] = startTime[0];
        for (int i = 1; i < len; i++) {
            gap[i] = startTime[i] - endTime[i - 1];
        }
        gap[len] = eventTime - endTime[len - 1];

        int win = k + 1;
        int l = 0, r = 0, sum = 0, result = 0;
        while (r < win) {
            sum += gap[r++];
        }

        result = sum;

        while (r < gap.length) {
            sum += gap[r++];
            sum -= gap[l++];
            result = Math.max(result, sum);
        }

        return result;
    }


}
