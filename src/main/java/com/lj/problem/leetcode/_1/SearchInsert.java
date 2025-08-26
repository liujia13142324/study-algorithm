package com.lj.problem.leetcode._1;

import org.junit.Test;

/**
 * 35. 搜索插入位置
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 请必须使用时间复杂度为 O(log n) 的算法。
 *
 *
 * 示例 1:
 * 输入: nums = [1,3,5,6], target = 5
 * 输出: 2
 *
 * 示例 2:
 * 输入: nums = [1,3,5,6], target = 2
 * 输出: 1
 *
 * 示例 3:
 * 输入: nums = [1,3,5,6], target = 7
 * 输出: 4
 *
 *
 * 提示:
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums 为 无重复元素 的 升序 排列数组
 * -104 <= target <= 104
 */
public class SearchInsert {

    @Test
    public void test() {
        System.out.println(searchInsert(new int[]{1,3,5,6}, 5));
        System.out.println(searchInsert(new int[]{1,3,5,6}, 2));
        System.out.println(searchInsert(new int[]{1,3,5,6}, 7));
    }

    /**
     * t=2
     * 1,3,5,6
     * l=0, r=3, mid=1: t<3
     * l=0, r=0, mid=0: t>1
     * l=1, r=0 -> return 1
     *
     * t=7
     * 1,3,5,6
     * l=0, r=3, mid=1: 1,3,5,6
     * l=2, r=3, mid=2: 5,6
     * l=3, r=3, mid=3  6,
     * l=4, r=3 -> return 4
     */

    public int searchInsert(int[] nums, int target) {
        int l = 0, r = nums.length - 1, mid;
        while (l <= r) {
            mid = (l+r)/2;
            if (nums[mid] == target) return mid;
            if (target > nums[mid]) {
                l = mid + 1;
            }else {
                r = mid - 1;
            }
        }
        return l;
    }
}
