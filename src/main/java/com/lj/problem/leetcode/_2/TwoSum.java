package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.Arrays;

/**
 * 167. 两数之和 II - 输入有序数组
 *
 * 给你一个下标从 1 开始的整数数组 numbers ，该数组已按 非递减顺序排列，请你从数组中找出满足相加之和等于目标数 target 的两个数。
 * 如果设这两个数分别是 numbers[index1] 和 numbers[index2] ，则 1 <= index1 < index2 <= numbers.length 。
 * 以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。
 * 你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
 * 你所设计的解决方案必须只使用常量级的额外空间。
 *
 *
 * 示例 1：
 * 输入：numbers = [2,7,11,15], target = 9
 * 输出：[1,2]
 * 解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。返回 [1, 2] 。
 *
 * 示例 2：
 * 输入：numbers = [2,3,4], target = 6
 * 输出：[1,3]
 * 解释：2 与 4 之和等于目标数 6 。因此 index1 = 1, index2 = 3 。返回 [1, 3] 。
 *
 * 示例 3：
 * 输入：numbers = [-1,0], target = -1
 * 输出：[1,2]
 * 解释：-1 与 0 之和等于目标数 -1 。因此 index1 = 1, index2 = 2 。返回 [1, 2] 。
 *
 * 提示：
 *
 * 2 <= numbers.length <= 3 * 104
 * -1000 <= numbers[i] <= 1000
 * numbers 按 非递减顺序 排列
 * -1000 <= target <= 1000
 * 仅存在一个有效答案
 */
public class TwoSum {

    @Test
    public void test() {
        System.out.println(Arrays.toString(twoSum(new int[]{2,3,4,6,7,11,15}, 7)));
        System.out.println(Arrays.toString(twoSum2(new int[]{2,3,4,6,7,11,15}, 7)));
    }

    public int[] twoSum2(int[] numbers, int target) {
        int l = 0, r = numbers.length - 1;
        while (numbers[l] + numbers[r] != target) {
            if (numbers[l] + numbers[r] > target) {
                // 第一个 <= target - numbers[l] 的位置
                r = low_bound(numbers, target - numbers[l] + 1) - 1;
            }else {
                // 第一个 >= target - numbers[r] 的位置
                l = low_bound(numbers, target - numbers[r]);
            }
        }
        return new int[]{l+1, r+1};
    }

    // 第一个 >= target 的位置: low(target)
    // 第一个 >  target 的位置: low(target + 1)
    // 第一个 <  target 的位置: low(target) - 1
    // 第一个 <= target 的位置: low(target + 1) - 1
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

    /**
     * 2,3,4,6,7,11,15 target=7
     *
     * target <= num[i] + num[len-1]  --> target - num[len-1] <= nums[i]
     * numbers = [2,7,11,15], target = 9
     * 9 - 15 = -6
     */
    public int[] twoSum(int[] numbers, int target) {
        int l = 0, r = numbers.length - 1;
        while (true) {
            if (numbers[l] + numbers[r] > target) {
                r--;
            }else if (numbers[l] + numbers[r] < target) {
                l++;
            }else {
                return new int[]{l+1, r+1};
            }
        }
    }
}
