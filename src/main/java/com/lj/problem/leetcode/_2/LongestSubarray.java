package com.lj.problem.leetcode._2;

import org.junit.Test;

/**
 * 1493. 删掉一个元素以后全为 1 的最长子数组
 * 给你一个二进制数组 nums ，你需要从中删掉一个元素。
 * 请你在删掉元素的结果数组中，返回最长的且只包含 1 的非空子数组的长度。
 * 如果不存在这样的子数组，请返回 0 。
 *
 *
 * 提示 1：
 * 输入：nums = [1,1,0,1]
 * 输出：3
 * 解释：删掉位置 2 的数后，[1,1,1] 包含 3 个 1 。
 *
 * 示例 2：
 * 输入：nums = [0,1,1,1,0,1,1,0,1]
 * 输出：5
 * 解释：删掉位置 4 的数字后，[0,1,1,1,1,1,0,1] 的最长全 1 子数组为 [1,1,1,1,1] 。

 * 示例 3：
 * 输入：nums = [1,1,1]
 * 输出：2
 * 解释：你必须要删除一个元素。
 *
 * 提示：
 * 1 <= nums.length <= 105
 * nums[i] 要么是 0 要么是 1 。
 */
public class LongestSubarray {

    @Test
    public void test() {
        System.out.println(longestSubarray2(new int[]{1,1,0,1}));
        System.out.println(longestSubarray2(new int[]{0,1,1,1,0,1,1,0,1}));
        System.out.println(longestSubarray2(new int[]{1,1,1}));
    }

    public int longestSubarray2(int[] nums) {
        int ans = 0;
        int cnt0 = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            // 1. 入，nums[right] 进入窗口
            cnt0 += 1 - nums[right]; // 维护窗口中的 0 的个数
            while (cnt0 > 1) { // 不符合题目要求
                // 2. 出，nums[left] 离开窗口
                cnt0 -= 1 - nums[left]; // 维护窗口中的 0 的个数
                left++;
            }
            // 3. 更新答案，注意不是 right-left+1，因为我们要删掉一个数
            ans = Math.max(ans, right - left);
        }
        return ans;
    }


    public int longestSubarray(int[] nums) {
        int l = 0, r = 0, ans = 0, pre = -1;
        for (int len = nums.length; r < len; r++) {
            if (nums[r] == 0 && pre == -1) {
                ans = Math.max(ans, r - l);
                pre = r;
            }else if (nums[r] == 0) {
                ans = Math.max(ans, r - l - 1);
                l = pre + 1;
                pre = r;
            }else {
                ans = Math.max(ans, r - l);
            }
        }
        return ans;
    }
}
