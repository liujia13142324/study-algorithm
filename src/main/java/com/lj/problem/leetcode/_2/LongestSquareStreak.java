package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.Arrays;

/**
 * 2501. 数组中最长的方波
 * 提示
 * 给你一个整数数组 nums 。如果 nums 的子序列满足下述条件，则认为该子序列是一个 方波 ：
 *
 * 子序列的长度至少为 2 ，并且
 * 将子序列从小到大排序 之后 ，除第一个元素外，每个元素都是前一个元素的 平方 。
 * 返回 nums 中 最长方波 的长度，如果不存在 方波 则返回 -1 。
 *
 * 子序列 也是一个数组，可以由另一个数组删除一些或不删除元素且不改变剩余元素的顺序得到。
 *
 * 示例 1 ：
 * 输入：nums = [4,3,6,16,8,2]
 * 输出：3
 * 解释：选出子序列 [4,16,2] 。排序后，得到 [2,4,16] 。
 * - 4 = 2 * 2.
 * - 16 = 4 * 4.
 * 因此，[4,16,2] 是一个方波.
 * 可以证明长度为 4 的子序列都不是方波。
 *
 * 示例 2 ：
 * 输入：nums = [2,3,5,6,7]
 * 输出：-1
 * 解释：nums 不存在方波，所以返回 -1 。
 *
 * 提示：
 *
 * 2 <= nums.length <= 105
 * 2 <= nums[i] <= 105
 */
public class LongestSquareStreak {

    @Test
    public void test() {
        System.out.println(longestSquareStreak(new int[]{4,3,6,16,8,2}));
    }

    public int longestSquareStreak(int[] nums) {
        // 2 3 4 6 8 16
        Arrays.sort(nums);
        int tmp;
        int len = nums.length - 1;
        int max = 1;
        for (int i = 0; i <= len && (tmp=nums[i]*nums[i]) <= nums[len]; i++) {
            int tmpMax = 1;
            int l = i;
            for (int j = find(l, nums.length, nums, tmp); j > i && j <= len; j = find(l, nums.length, nums, tmp)) {
                tmpMax++;
                tmp = nums[j] * nums[j];
                l = j;
            }
            max = Math.max(max, tmpMax);
        }

        return max == 1 ? -1 : max;
    }

    private int find(int l, int r, int[] nums, int target) {
        while (l + 1 < r) {
            int mid = (l + r) >>> 1;
            if (nums[mid] > target) {
                r = mid;
            }else if (nums[mid] < target) {
                l = mid;
            }else {
                return mid;
            }
        }
        return -1;
    }

}
