package com.lj.problem.leetcode._3;

import org.junit.Test;

/**
 * 84. 柱状图中最大的矩形
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 *
 * 示例 1:
 * 输入：heights = [2,1,5,6,2,3]
 * 输出：10
 * 解释：最大的矩形为图中红色区域，面积为 10
 *
 * 示例 2：
 * 输入： heights = [2,4]
 * 输出： 4
 *
 *
 * 提示：
 * 1 <= heights.length <=105
 * 0 <= heights[i] <= 104
 */
public class LargestRectangleArea {

    @Test
    public void test() {
        System.out.println(largestRectangleArea2(new int[]{999,999,999,999}));
        System.out.println(largestRectangleArea2(new int[]{5, 4, 1, 2}));
    }

    public int largestRectangleArea(int[] heights) {
        int ans = 0;
        int[] stack = new int[heights.length + 1];
        int idx = -1;
        stack[++idx] = -1;

        for (int i = 0; i < heights.length; i++) {
            while (idx != 0 && heights[stack[idx]] >= heights[i]) {
                idx--;
            }
            stack[++idx] = i;
            for (int j = 0; j < idx; j++) {
                ans = Math.max(ans, (i - stack[j]) * heights[stack[j + 1]]);
            }
        }

        return ans;
    }


    public int largestRectangleArea2(int[] heights) {
        int ans = 0;
        int[] stack = new int[heights.length + 1];
        int idx = -1;
        stack[++idx] = -1;

        for (int i = 0; i < heights.length; i++) {
            if (idx != 0 && heights[stack[idx]] >= heights[i]) {
                for (int j = 0; j < idx; j++) {
                    ans = Math.max(ans, (stack[idx] - stack[j]) * heights[stack[j + 1]]);
                }
                while (idx != 0 && heights[stack[idx]] >= heights[i]) {
                    idx--;
                }
            }
            stack[++idx] = i;
        }

        for (int j = 0; j < idx; j++) {
            ans = Math.max(ans, (stack[idx] - stack[j]) * heights[stack[j + 1]]);
        }
        return ans;
    }
}
