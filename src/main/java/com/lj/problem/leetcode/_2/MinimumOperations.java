package com.lj.problem.leetcode._2;

import java.util.List;

/**
 * 2826. 将三个组排序
 * 提示
 * 给你一个整数数组 nums 。nums 的每个元素是 1，2 或 3。在每次操作中，你可以删除 nums 中的一个元素。返回使 nums 成为 非递减 顺序所需操作数的 最小值。
 *
 * 示例 1：
 * 输入：nums = [2,1,3,2,1]
 * 输出：3
 * 解释：
 * 其中一个最优方案是删除 nums[0]，nums[2] 和 nums[3]。
 *
 * 示例 2：
 * 输入：nums = [1,3,2,1,3,3]
 * 输出：2
 * 解释：
 * 其中一个最优方案是删除 nums[1] 和 nums[2]。
 *
 * 示例 3：
 * 输入：nums = [2,2,2,2,3,3]
 * 输出：0
 * 解释：
 * nums 已是非递减顺序的。
 *
 * 提示：
 *
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 3
 * 进阶：你可以使用 O(n) 时间复杂度以内的算法解决吗？
 */
public class MinimumOperations {



    public int minimumOperations(List<Integer> nums) {
        int[] arr = new int[nums.size()];
        int i = 0;
        for (int num: nums) arr[i++] = num;
        i = 0;
        for (int num: arr) {
            int idx = find(-1, i, arr, num);
            if (idx == i) {
                arr[i++] = num;
            } else {
                arr[idx] = num;
            }
        }
        return arr.length - i;
    }

    private int find(int l, int r, int[] nums, int target) {
        while (l + 1 < r) {
            int mid = (l + r) >>> 1;
            if (nums[mid] > target) {
                r = mid;
            }else {
                l = mid;
            }
        }
        return r;
    }
}
