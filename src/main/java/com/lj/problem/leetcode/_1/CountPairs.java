package com.lj.problem.leetcode._1;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 统计和小于目标的下标对数目
 * 给你一个下标从 0 开始长度为 n 的整数数组 nums 和一个整数 target ，请你返回满足 0 <= i < j < n 且 nums[i] + nums[j] < target 的下标对 (i, j) 的数目。
 *
 * 示例 1：
 * 输入：nums = [-1,1,2,3,1], target = 2
 * 输出：3
 * 解释：总共有 3 个下标对满足题目描述：
 * - (0, 1) ，0 < 1 且 nums[0] + nums[1] = 0 < target
 * - (0, 2) ，0 < 2 且 nums[0] + nums[2] = 1 < target
 * - (0, 4) ，0 < 4 且 nums[0] + nums[4] = 0 < target
 * 注意 (0, 3) 不计入答案因为 nums[0] + nums[3] 不是严格小于 target 。
 *
 * 示例 2：
 * 输入：nums = [-6,2,5,-2,-7,-1,3], target = -2
 * 输出：10
 * 解释：总共有 10 个下标对满足题目描述：
 * - (0, 1) ，0 < 1 且 nums[0] + nums[1] = -4 < target
 * - (0, 3) ，0 < 3 且 nums[0] + nums[3] = -8 < target
 * - (0, 4) ，0 < 4 且 nums[0] + nums[4] = -13 < target
 * - (0, 5) ，0 < 5 且 nums[0] + nums[5] = -7 < target
 * - (0, 6) ，0 < 6 且 nums[0] + nums[6] = -3 < target
 * - (1, 4) ，1 < 4 且 nums[1] + nums[4] = -5 < target
 * - (3, 4) ，3 < 4 且 nums[3] + nums[4] = -9 < target
 * - (3, 5) ，3 < 5 且 nums[3] + nums[5] = -3 < target
 * - (4, 5) ，4 < 5 且 nums[4] + nums[5] = -8 < target
 * - (4, 6) ，4 < 6 且 nums[4] + nums[6] = -4 < target
 *
 *
 * 提示：
 * 1 <= nums.length == n <= 50
 * -50 <= nums[i], target <= 50
 * 0 <= nums[i] + 50, target + 50 <= 100
 */
public class CountPairs {

    @Test
    public void test() {
        System.out.println(countPairs(Arrays.asList(-1, 1, 2, 3, 1), 2));
        System.out.println(countPairs(Arrays.asList(-6,2,5,-2,-7,-1,3), -2));
        System.out.println(countPairs(Arrays.asList(6,-1,7,4,2,3), 8));
        // 1,2 0,3, 1,3 2,3
        System.out.println(countPairs(Arrays.asList(6, 4, 1, -7), 7));
    }
    /**
     * nums[i] + nums[j] < target
     *  -50 <= nums[j] < target - nums[i]
     *
     */
    public int countPairs(List<Integer> nums, int target) {
        int max = 0, size = nums.size();
        for (int i = 0; i < size; i++) {
            nums.set(i , nums.get(i) + 50);
            max = Math.max(max, nums.get(i));
        }
        int[] sum = new int[max+1];
        for (int k = nums.get(0); k < sum.length; k++) {
            sum[k]++;
        }
        int ans = 0;
        target+=100;
        int r;
        for (int i = 1; i < size; i++) {
            r = Math.min(target-nums.get(i), max+1);
            if (r < 1) {continue;}
            ans += sum[r-1];
            for (int k = nums.get(i); k < sum.length; k++) {
                sum[k]++;
            }
        }
        return ans;
    }

}
