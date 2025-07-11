package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.*;

/**
 * 3169. 无需开会的工作日
 * 给你一个正整数 days，表示员工可工作的总天数（从第 1 天开始）。另给你一个二维数组 meetings，长度为 n，其中 meetings[i] = [start_i, end_i] 表示第 i 次会议的开始和结束天数（包含首尾）。
 * 返回员工可工作且没有安排会议的天数。
 *
 * 注意：会议时间可能会有重叠。
 *
 * 示例 1：
 * 输入：days = 10, meetings = [[5,7],[1,3],[9,10]]
 * 输出：2
 * 解释：
 * 第 4 天和第 8 天没有安排会议。
 *
 * 示例 2：
 * 输入：days = 5, meetings = [[2,4],[1,3]]
 * 输出：1
 * 解释：
 * 第 5 天没有安排会议。
 *
 * 示例 3：
 * 输入：days = 6, meetings = [[1,6]]
 * 输出：0
 * 解释：
 * 所有工作日都安排了会议。
 *
 * 提示：
 *
 * 1 <= days <= 109
 * 1 <= meetings.length <= 105
 * meetings[i].length == 2
 * 1 <= meetings[i][0] <= meetings[i][1] <= days
 */
public class CountDays {

    @Test
    public void test1(){
        System.out.println(countDays2(10, new int[][]{new int[]{5,7},new int[]{1,3}, new int[]{9,10}}));
    }


    public int countDays2(int days, int[][] meetings) {
        Arrays.sort(meetings, Comparator.comparing(e->e[0]));
        int hasCount = 0;
        for (int i = 0, j; i <meetings.length;) {
            for (j = i+1; j <meetings.length; j++) {
                if (meetings[j][0] > meetings[i][1]) {
                    break;
                }
                meetings[i][1] = Math.max(meetings[i][1], meetings[j][1]);
            }
            hasCount += (meetings[i][1] - meetings[i][0] + 1);
            i = j;
        }

        return days - hasCount;
    }


    public int countDays(int days, int[][] meetings) {
        int[] hash = new int[days + 1];
        for (int[] meeting : meetings) {
            Arrays.fill(hash, meeting[0], meeting[1] + 1, 1);
        }
        int count = 0;
        for (int i = 1; i <= days; i++) {
            if (hash[i] == 0) {
                count++;
            }
        }
        return count;
    }


}
