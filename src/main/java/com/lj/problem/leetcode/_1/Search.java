package com.lj.problem.leetcode._1;

import org.junit.Test;

/**
 * 704. 二分查找
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果 target 存在返回下标，否则返回 -1。
 * 你必须编写一个具有 O(log n) 时间复杂度的算法。
 *
 * 示例 1:
 * 输入: nums = [-1,0,3,5,9,12], target = 9
 * 输出: 4
 * 解释: 9 出现在 nums 中并且下标为 4
 *
 * 示例 2:
 * 输入: nums = [-1,0,3,5,9,12], target = 2
 * 输出: -1
 * 解释: 2 不存在 nums 中因此返回 -1
 *
 *
 * 提示：
 * 你可以假设 nums 中的所有元素是不重复的。
 * n 将在 [1, 10000]之间。
 * nums 的每个元素都将在 [-9999, 9999]之间。
 */
public class Search {

    @Test
    public void test() {
        System.out.println(search(new int[]{-1,0,3,5,9,12}, 9));
        System.out.println(search(new int[]{-1,0,3,5,9,12}, 2));

        System.out.println(search2(new int[]{-1,0,3,5,9,12}, 9));
        System.out.println(search2(new int[]{-1,0,3,5,9,12}, 2));
    }


    public int search2(int[] nums, int target) {
        int l = 0, r = nums.length - 1, mid;
        while (l <= r) {
            mid = (l + r) / 2;
            if (nums[mid] == target) return mid;
            if (target > nums[mid]) {
                l = mid + 1;
            }else {
                r = mid - 1;
            }
        }
        return -1;
    }


    public int search(int[] nums, int target) {
        return dfs(nums, target, 0, nums.length - 1);
    }

    private int dfs(int[] nums, int target, int l, int r) {
        if (l > r) {
            return -1;
        }
        int i = (l+r)/2;
        if (nums[i] == target) return i;

        return target > nums[i] ? dfs(nums, target, i+1, r) : dfs(nums, target, 0, i-1);
    }

}
