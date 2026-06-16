package com.lj.problem.leetcode._2;

import java.util.Arrays;

/**
 * 503. 下一个更大元素 II
 * 中等
 * 给定一个循环数组 nums （ nums[nums.length - 1] 的下一个元素是 nums[0] ），返回 nums 中每个元素的 下一个更大元素 。
 *
 * 数字 x 的 下一个更大的元素 是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1 。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [1,2,1]
 * 输出: [2,-1,2]
 * 解释: 第一个 1 的下一个更大的数是 2；
 * 数字 2 找不到下一个更大的数；
 * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
 * 示例 2:
 *
 * 输入: nums = [1,2,3,4,3]
 * 输出: [2,3,4,-1,4]
 *
 *
 * 提示:
 *
 * 1 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 */
public class NextGreaterElements {

    public int[] nextGreaterElements(int[] nums) {
        int[] stack = new int[nums.length];
        int[] ans = new int[nums.length];
        Arrays.fill(ans, -1);
        int index = -1;
        for (int i = 0; i < nums.length; i++) {
            while (index != -1 && nums[i] > nums[stack[index]]) {
                ans[stack[index--]] = nums[i];
            }
            stack[++index] = i;
        }

        for (int num: nums) {
            while (num > nums[stack[index]]) {
                ans[stack[index--]] = num;
            }
        }

        return ans;
    }

}
