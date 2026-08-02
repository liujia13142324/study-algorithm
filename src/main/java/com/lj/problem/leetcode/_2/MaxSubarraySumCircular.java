package com.lj.problem.leetcode._2;

import org.junit.Test;

/**
 * 918. 环形子数组的最大和
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给定一个长度为 n 的环形整数数组 nums ，返回 nums 的非空 子数组 的最大可能和 。
 *
 * 环形数组 意味着数组的末端将会与开头相连呈环状。形式上， nums[i] 的下一个元素是 nums[(i + 1) % n] ， nums[i] 的前一个元素是 nums[(i - 1 + n) % n] 。
 *
 * 子数组 最多只能包含固定缓冲区 nums 中的每个元素一次。形式上，对于子数组 nums[i], nums[i + 1], ..., nums[j] ，不存在 i <= k1, k2 <= j 其中 k1 % n == k2 % n 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,-2,3,-2]
 * 输出：3
 * 解释：从子数组 [3] 得到最大和 3
 * 示例 2：
 *
 * 输入：nums = [5,-3,5]
 * 输出：10
 * 解释：从子数组 [5,5] 得到最大和 5 + 5 = 10
 * 示例 3：
 *
 * 输入：nums = [3,-2,2,-3]
 * 输出：3
 * 解释：从子数组 [3] 和 [3,-2,2] 都可以得到最大和 3
 *
 *
 * 提示：
 *
 * n == nums.length
 * 1 <= n <= 3 * 104
 * -3 * 104 <= nums[i] <= 3 * 104
 */
public class MaxSubarraySumCircular {

    @Test
    public void testss() {
        System.out.println(maxSubarraySumCircular(new int[]{0,5,8,-9,9,-7,3,-2}));
    }

    public int maxSubarraySumCircular(int[] nums) {
        int sum = 0;
        int pre_max = 0;
        int pre_min = 0;
        int min_sub = Integer.MAX_VALUE;
        int max_sub = Integer.MIN_VALUE;
        for (int num: nums) {
            sum += num;
            min_sub = Math.min(min_sub, sum - pre_max);
            max_sub = Math.max(max_sub, sum - pre_min);
            pre_max = Math.max(pre_max, sum);
            pre_min = Math.min(pre_min, sum);
        }

        return min_sub == sum ? max_sub : Math.max(sum - min_sub, max_sub);
    }
}
