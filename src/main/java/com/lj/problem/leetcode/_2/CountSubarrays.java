package com.lj.problem.leetcode._2;

import org.junit.Test;

/**
 * 2962. 统计最大元素出现至少 K 次的子数组
 * 给你一个整数数组 nums 和一个 正整数 k 。
 * 请你统计有多少满足 「 nums 中的 最大 元素」至少出现 k 次的子数组，并返回满足这一条件的子数组的数目。
 * 子数组是数组中的一个连续元素序列。
 *
 * 示例 1：
 * 输入：nums = [1,3,2,3,3], k = 2
 * 输出：6
 * 解释：包含元素 3 至少 2 次的子数组为：[1,3,2,3]、[1,3,2,3,3]、[3,2,3]、[3,2,3,3]、[2,3,3] 和 [3,3] 。

 * 示例 2：
 * 输入：nums = [1,4,2,1], k = 3
 * 输出：0
 * 解释：没有子数组包含元素 4 至少 3 次。
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 106
 * 1 <= k <= 105
 */
public class CountSubarrays {


    @Test
    public void test() {
        System.out.println(countSubarrays(new int[]{1,3,2,3,3}, 2));
        System.out.println(countSubarrays(new int[]{1,4,2,1}, 3));
        System.out.println(countSubarrays(new int[]{61,23,38,23,56,40,82,56,82,82,82,70,8,69,8,7,19,14,58,42,82,10,82,78,15,82}, 2));

        System.out.println(countSubarrays3(new int[]{1,3,2,3,3}, 2));
        System.out.println(countSubarrays3(new int[]{1,4,2,1}, 3));
        System.out.println(countSubarrays3(new int[]{61,23,38,23,56,40,82,56,82,82,82,70,8,69,8,7,19,14,58,42,82,10,82,78,15,82}, 2));
    }

    public long countSubarrays3(int[] nums, int k) {
        int max = Integer.MIN_VALUE;
        // 不做统计更快一些
        for (int num: nums) {
            max = Math.max(max, num);
        }
        long ans = 0;
        int l = 0, maxCount = 0;
        for (int num : nums) {
            if (num == max) {
                maxCount++;
            }
            if (maxCount == k) {
                while (nums[l++] != max);
                maxCount--;
            }
            ans += l;
        }
        return ans;
    }


    public long countSubarrays2(int[] nums, int k) {
        int max = Integer.MIN_VALUE;
        // 不做统计更快一些
        for (int num: nums) {
            max = Math.max(max, num);
        }
        int maxCount = 0;
        long ans = 0;
        int l = -1;
        for (int r = 0; r < nums.length; r++) {
            if (nums[r] == max) {
                if (l == -1) {
                    l = r;
                }
                maxCount++;
            }
            if (maxCount > k) {
                while (nums[++l] != max);
                maxCount--;
            }

            if (maxCount >= k) {
                ans += l + 1;
            }
        }
        return ans;
    }

    public long countSubarrays(int[] nums, int k) {
        int max = Integer.MIN_VALUE;
        int maxCount = 0;
        for (int num: nums) {
            if (num > max) {
                max = num;
                maxCount = 1;
            }else if (num == max) {
                maxCount ++;
            }
        }
        if (maxCount < k) {
            return 0;
        }
        maxCount = 0;
        long ans = 0;
        int l = -1;
        for (int r = 0; r < nums.length; r++) {
            if (nums[r] == max) {
                if (l == -1) {
                    l = r;
                }
                maxCount++;
            }
            while (maxCount > k) {
                if (nums[++l] == max) {
                    maxCount--;
                }
            }

            if (maxCount >= k) {
                ans += l + 1;
            }
        }
        return ans;
    }
}
