package com.lj.problem.leetcode._2;

import org.junit.Test;

/**
 * 209. 长度最小的子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * 找出该数组中满足其总和大于等于 target 的长度最小的 子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 *
 *
 * 示例 1：
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 *
 * 示例 2：
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 *
 * 示例 3：
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 *
 *
 * 提示：
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 104
 *
 *
 * 进阶：
 * 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。
 */
public class MinSubArrayLen {

    @Test
    public void test() {
        System.out.println(minSubArrayLen(7, new int[]{2,3,1,2,4,3}));
        System.out.println(minSubArrayLen(4, new int[]{1,4,4}));
        System.out.println(minSubArrayLen(11, new int[]{1,1,1,1,1,1,1,1}));
        System.out.println(minSubArrayLen(15, new int[]{1,2,3,4,5}));

        System.out.println(minSubArrayLen2(7, new int[]{2,3,1,2,4,3}));
        System.out.println(minSubArrayLen2(4, new int[]{1,4,4}));
        System.out.println(minSubArrayLen2(11, new int[]{1,1,1,1,1,1,1,1}));
        System.out.println(minSubArrayLen2(15, new int[]{1,2,3,4,5}));
    }

    public int minSubArrayLen2(int target, int[] nums) {
        int sum = 0, ans = 100001, l = 0;
        for (int r = 0; r < nums.length; r++) {
            sum += nums[r];
            if (sum < target) {
                continue;
            }
            do {
                ans = Math.min(ans, r - l + 1);
                sum -= nums[l++];
            }while (sum >= target);
        }
        return ans > 100000 ? 0 : ans;
    }



    public int minSubArrayLen(int target, int[] nums) {
        int sum = 0; int r = 0;
        for (; r < nums.length; r++) {
            sum+=nums[r];
            if (sum >= target) break;
        }
        if (sum < target) {return 0;}
        int l = 0;
        for (; l < r; l++) {
            if (sum - nums[l] < target)  break;
            sum -= nums[l];
        }

        int ans = Integer.MAX_VALUE;
        while (true) {
            if (sum >= target) {
                ans = Math.min(ans, r - l + 1);
                sum -= nums[l];
                l++;
            }else if (r < nums.length - 1){
                sum += nums[++r];
            }else {
                break;
            }
        }

        return ans;
    }
}
