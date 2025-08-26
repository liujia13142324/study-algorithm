package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.Arrays;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 *
 *
 *
 * 示例 1：
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 *
 * 示例 2：
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 *
 * 示例 3：
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 *
 *
 * 提示：
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * nums 是一个非递减数组
 * -109 <= target <= 109
 */
public class SearchRange {

    @Test
    public void test() {
        System.out.println(Arrays.toString(searchRange(new int[]{5,7,7,8,8,10}, 8)));
        System.out.println(Arrays.toString(searchRange(new int[]{5,7,7,8,8,10}, 2)));
        System.out.println(Arrays.toString(searchRange(new int[]{}, 2)));

        System.out.println(Arrays.toString(searchRange2(new int[]{5,7,7,8,8,10}, 8)));
        System.out.println(Arrays.toString(searchRange2(new int[]{5,7,7,8,8,10}, 2)));
        System.out.println(Arrays.toString(searchRange2(new int[]{}, 2)));
    }

    int[] EMPTY = new int[]{-1, -1};

    // >=t第一位 和 <=t第一位
    public int[] searchRange2(int[] nums, int target) {
        // >=t第一位
        int i = low_bound(nums, target);
        if (i == nums.length || nums[i] != target) {
            return EMPTY;
        }
        return new int[]{i, low_bound(nums, target + 1) - 1};
    }

    /**
     * >=: 找到第一个大于等于 target 的
     * >  -->  >= target + 1
     * <= -->  >
     * <  -->
     */
    private int low_bound(int[] nums, int target) {
        int l = 0, r = nums.length - 1, mid;
        while (l <= r) {
            mid = (l + r) / 2;
            if (target <= nums[mid]) {
                r = mid - 1;
            }else {
                l = mid + 1;
            }
        }
        return l;
    }


    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) return EMPTY;
        int[] ans = new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};
        dfs(nums, target, 0, nums.length - 1, ans);
        return ans[0] == Integer.MAX_VALUE ? EMPTY: ans;
    }

    private void dfs(int[] nums, int target, int l, int r, int[] ans) {
        if (l > r || target > nums[r] || target < nums[l]) {
            return;
        }
        int mid = (l + r) / 2;
        if (nums[mid] == target) {
            ans[0] = Math.min(ans[0], mid);
            ans[1] = Math.max(ans[1], mid);
            dfs(nums, target, l, mid - 1, ans);
            dfs(nums, target, mid + 1, r, ans);
        }else if (nums[mid] > target) {
            dfs(nums, target, l, mid - 1, ans);
        }else {
            dfs(nums, target, mid + 1, r, ans);
        }
    }

}
