package com.lj.problem.leetcode._2;

import org.junit.Test;

public class MaximumLength {

    @Test
    public void test() {
        System.out.println(maximumLength(new int[]{1,3}));
        System.out.println(maximumLength(new int[]{1,5,9,4,2}));
        System.out.println(maximumLength(new int[]{1,2,1,1,2,1,2}));
        System.out.println(maximumLength(new int[]{1,8,4,2,4}));
        System.out.println(maximumLength(new int[]{2,13,26,70,76}));
    }

    public int maximumLength(int[] nums) {
        int c1 = 0, c2 = 0, c3 = 0;
        int pre = 0;

        for (int i = 0; i < nums.length; i++) {
            // 奇数
            if ((nums[i] & 1) == 1) {
                c1++;
            }else {
                // 偶数
                c2++;
            }

            if ( c3 == 0 || ((nums[i] & 1) ^ (pre & 1)) == 1 ) {
                c3++;
                pre = nums[i];
            }

        }
        return Math.max(c3, Math.max(c1, c2));
    }

    @Test
    public void test2() {
        System.out.println((2 & 1) ^ (3 & 1));
        System.out.println((2 & 1) ^ (4 & 1));
    }


}
