package com.lj.problem.leetcode._2;

import org.junit.Test;

/**
 * 713. 乘积小于 K 的子数组
 * 给你一个整数数组 nums 和一个整数 k ，请你返回子数组内所有元素的乘积严格小于 k 的连续子数组的数目。
 *
 * 示例 1：
 * 输入：nums = [10,5,2,6], k = 100
 * 输出：8
 * 解释：8 个乘积小于 100 的子数组分别为：[10]、[5]、[2]、[6]、[10,5]、[5,2]、[2,6]、[5,2,6]。
 * 需要注意的是 [10,5,2] 并不是乘积小于 100 的子数组。

 * 示例 2：
 * 输入：nums = [1,2,3], k = 0
 * 输出：0
 *
 * 提示:
 * 1 <= nums.length <= 3 * 104
 * 1 <= nums[i] <= 1000
 * 0 <= k <= 106
 */
public class NumSubarrayProductLessThanK {

    @Test
    public void test() {
        System.out.println(numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100));
//        System.out.println(numSubarrayProductLessThanK(new int[]{1,2,3}, 0));
//        System.out.println(numSubarrayProductLessThanK(new int[]{10,2,2,5,4,4,4,3,7,7}, 289));
//        System.out.println(numSubarrayProductLessThanK(new int[]{57,44,92,28,66,60,37,33,52,38,29,76,8,75,22}, 18));
        System.out.println(numSubarrayProductLessThanK(new int[]{100,2,3,4,100,5,6,7,100}, 100));
    }

    /**
     * 1. 当 r > num.length - 1, 移动 l
     * 2. 当 r < num.length - 1
     *  product * nums[r] < target
     *      r++
     *      ans++
     *  else:
     *      l++
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) return 0;
        int ans = 0, l = 0, r = 0, product = 1;
        while (r < nums.length) {
            if (nums[r] >= k) {
                r++;
                l = r;
                product = 1;
                continue;
            }
            if (product * nums[r] < k) {
                ans +=  (r - l + 1);
                product *= nums[r++];
            }else {
                product /= nums[l++];
            }
        }
        return ans;
    }

}
