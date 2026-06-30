package com.lj.problem.leetcode._2;

import org.junit.Test;

/**
 * 1438. 绝对差不超过限制的最长连续子数组
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个整数数组 nums ，和一个表示限制的整数 limit，请你返回最长连续子数组的长度，该子数组中的任意两个元素之间的绝对差必须小于或者等于 limit。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [8,2,4,7], limit = 4
 * 输出：2
 * 解释：所有子数组如下：
 * [8] 最大绝对差 |8-8| = 0 <= 4.
 * [8,2] 最大绝对差 |8-2| = 6 > 4.
 * [8,2,4] 最大绝对差 |8-2| = 6 > 4.
 * [8,2,4,7] 最大绝对差 |8-2| = 6 > 4.
 * [2] 最大绝对差 |2-2| = 0 <= 4.
 * [2,4] 最大绝对差 |2-4| = 2 <= 4.
 * [2,4,7] 最大绝对差 |2-7| = 5 > 4.
 * [4] 最大绝对差 |4-4| = 0 <= 4.
 * [4,7] 最大绝对差 |4-7| = 3 <= 4.
 * [7] 最大绝对差 |7-7| = 0 <= 4.
 * 因此，满足题意的最长子数组的长度为 2 。
 * 示例 2：
 *
 * 输入：nums = [10,1,2,4,7,2], limit = 5
 * 输出：4
 * 解释：满足题意的最长子数组是 [2,4,7,2]，其最大绝对差 |2-7| = 5 <= 5 。
 * 示例 3：
 *
 * 输入：nums = [4,2,2,2,4,4,2,2], limit = 0
 * 输出：3
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 109
 * 0 <= limit <= 109
 */
public class LongestSubarray2 {

    @Test
    public void test() {
        System.out.println(longestSubarray(new int[]{24,12,71,33,5,87,10,11,3,58,2,97,97,36,32,35,15,80,24,45,38,9,22,21,33,68,22,85,35,83,92,38,59,90,42,64,61,15,4,40,50,44,54,25,34,14,33,94,66,27,78,56,3,29,3,51,19,5,93,21,58,91,65,87,55,70,29,81,89,67,58,29,68,84,4,51,87,74,42,85,81,55,8,95,39}
                , 87));
    }

    public int longestSubarray(int[] nums, int limit) {
        int l = 0;
        // 递增
        int[] queue1 = new int[nums.length];
        // 递减
        int[] queue2 = new int[nums.length];
        int head1 = 0, tail1 = -1;
        int head2 = 0, tail2 = -1;
        int ans = 0;

        for (int i = 0; i < nums.length; i++) {
            while (head1 <= tail1 && nums[queue1[tail1]] >= nums[i]) {
                tail1--;
            }
            while (head2 <= tail2 && nums[queue2[tail2]] <= nums[i]) {
                tail2--;
            }
            queue1[++tail1] = i;
            queue2[++tail2] = i;
            while (nums[queue2[head2]] - nums[queue1[head1]] > limit) {
                if (head1 == tail1) {
                    l = queue2[head2++] + 1;
                } else {
                    l = queue1[head1++] + 1;
                }
            }
            ans = Math.max(ans, i - l + 1);
        }

        return ans;
    }

}
