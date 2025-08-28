package com.lj.problem.leetcode._3;

import org.junit.Test;

/**
 * 42. 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * 示例 1：
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。

 * 示例 2：
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 */
public class Trap {

    @Test
    public void test() {
        System.out.println(trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        System.out.println(trap(new int[]{4,2,0,3,2,5}));
        // 23
        System.out.println(trap(new int[]{5,5,1,7,1,1,5,2,7,6}));
    }


    public int trap(int[] height) {
        if (height.length < 2) return 0;
        int l = 0, r = height.length - 1;
        int maxL = height[l];
        int maxR = height[r];
        int sum = (r - l - 1) * Math.min(maxL, maxR);
//        int area;
        while (l < r) {
            if (height[l] <= height[r] && ++l < r) {
                if (height[l] > maxL) {
//                    area = (r - l - 1) * Math.min(height[l], maxR);
                    sum = (r - l - 1) * Math.min(height[l], maxR) + sum - (r - l) * Math.min(maxL, maxR);
                    maxL = height[l];
                }else {
                    sum -= height[l];
                }
            }else if (l < --r){
                if (height[r] > maxR) {
//                    area = (r - l - 1) * Math.min(height[r], maxL);
                    sum = (r - l - 1) * Math.min(height[r], maxL) + sum - (r - l) * Math.min(maxL, maxR);
                    maxR = height[r];
                }else {
                    sum -= height[r];
                }
            }
        }
        return sum;
    }
}
