package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.Arrays;

/**
 * 2563. 统计公平数对的数目
 * 给你一个下标从 0 开始、长度为 n 的整数数组 nums ，和两个整数 lower 和 upper ，返回 公平数对的数目 。
 * 如果 (i, j) 数对满足以下情况，则认为它是一个 公平数对 ：
 *
 * 0 <= i < j < n，且
 * lower <= nums[i] + nums[j] <= upper
 *
 * 示例 1：
 * 输入：nums = [0,1,7,4,4,5], lower = 3, upper = 6
 * 输出：6
 * 解释：共计 6 个公平数对：(0,3)、(0,4)、(0,5)、(1,3)、(1,4) 和 (1,5) 。
 *
 * 示例 2：
 * 输入：nums = [1,7,9,2,5], lower = 11, upper = 11
 * 输出：1
 * 解释：只有单个公平数对：(2,3) 。
 *
 *
 * 提示：
 * 1 <= nums.length <= 105
 * nums.length == n
 * -109 <= nums[i] <= 109
 * -109 <= lower <= upper <= 109
 */
public class CountFairPairs {

    @Test
    public void test() {
        System.out.println(countFairPairs(new int[]{0,1,7,4,4,5}, 3, 6));
        System.out.println(countFairPairs(new int[]{1,7,9,2,5}, 11, 11));
    }

    // TODO 双指针
    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        long ans = 0;
        for (int i = 0; i < nums.length; i++) {
            // 找到第一个 l，使 num[l] >= lower
            int l = lowBound(nums, i, lower - nums[i]);
            // 找到第一个 r，使 num[r] <= lower
            int r = lowBound(nums, i, upper - nums[i] + 1) - 1;

            if (r <= i) {
                break;
            }

            ans += Math.max(Math.min(r, nums.length - 1) - Math.max(l, i + 1) + 1, 0) ;
        }

        return ans;
    }

    // 开区间
    public int lowBound(int[] nums, int l, int target) {
        int r = nums.length;
        while (l + 1 < r) {
            int mid = (l + r) >>> 1;
            if (nums[mid] >= target) {
                r = mid;
            }else {
                l = mid;
            }
        }
        return r;
    }


    /**
     * 别人答案，比我要慢一些
     * @return
     */
    public long countFairPairs2(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        long ans = 0;
        for (int j = 0; j < nums.length; j++) {
            // 注意要在 [0, j-1] 中二分，因为题目要求两个下标 i < j
            int r = lowerBound(nums, j, upper - nums[j] + 1);
            int l = lowerBound(nums, j, lower - nums[j]);
            ans += r - l;
        }
        return ans;
    }

    // 原理请看 https://www.bilibili.com/video/BV1AP41137w7/
    private int lowerBound(int[] nums, int right, int target) {
        int left = -1;
        while (left + 1 < right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }
}
