package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 2841. 几乎唯一子数组的最大和 1546
 * 给你一个整数数组 nums 和两个正整数 m 和 k 。
 * 请你返回 nums 中长度为 k 的 几乎唯一 子数组的 最大和 ，如果不存在几乎唯一子数组，请你返回 0 。
 * 如果 nums 的一个子数组有至少 m 个互不相同的元素，我们称它是 几乎唯一 子数组。
 * 子数组指的是一个数组中一段连续 非空 的元素序列。
 *
 *
 * 示例 1：
 * 输入：nums = [2,6,7,3,1,7], m = 3, k = 4
 * 输出：18
 * 解释：总共有 3 个长度为 k = 4 的几乎唯一子数组。分别为 [2, 6, 7, 3] ，[6, 7, 3, 1] 和 [7, 3, 1, 7] 。这些子数组中，和最大的是 [2, 6, 7, 3] ，和为 18 。
 *
 * 示例 2：
 * 输入：nums = [5,9,9,2,4,5,4], m = 1, k = 3
 * 输出：23
 * 解释：总共有 5 个长度为 k = 3 的几乎唯一子数组。分别为 [5, 9, 9] ，[9, 9, 2] ，[9, 2, 4] ，[2, 4, 5] 和 [4, 5, 4] 。这些子数组中，和最大的是 [5, 9, 9] ，和为 23 。

 * 示例 3：
 * 输入：nums = [1,2,1,2,1,2,1], m = 3, k = 3
 * 输出：0
 * 解释：输入数组中不存在长度为 k = 3 的子数组含有至少  m = 3 个互不相同元素的子数组。所以不存在几乎唯一子数组，最大和为 0 。
 *
 *
 * 提示：
 * 1 <= nums.length <= 2 * 104
 * 1 <= m <= k <= nums.length
 * 1 <= nums[i] <= 109
 */
public class MaxSum {

    @Test
    public void test() {
        System.out.println(maxSum(Arrays.asList(2,6,7,3,1,7), 3, 4));
        System.out.println(maxSum(Arrays.asList(5,9,9,2,4,5,4), 1, 3));
        System.out.println(maxSum(Arrays.asList(1,2,1,2,1,2,1), 3, 3));
        System.out.println(maxSum(Arrays.asList(1,1,1,3), 2, 2));
    }

    public long maxSum(List<Integer> nums, int m, int k) {
        int l = 0, r = 0, len = nums.size(),  tmp = 0, tmp2 = 0, tmp3 = 0;
        long sum = 0, maxSum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        while (r < k) {
            tmp = nums.get(r);
            map.put(tmp, map.getOrDefault(tmp, 0) + 1);
            sum += tmp;
            r++;
        }

        if (map.size() >= m) {
            maxSum = sum;
        }

        while (r < len) {
            tmp = nums.get(r++);
            tmp2 = nums.get(l++);
            sum += tmp;
            sum -= tmp2;
            map.put(tmp, map.getOrDefault(tmp, 0) + 1);
            tmp3 = map.get(tmp2) - 1;
            if (tmp3 == 0) map.remove(tmp2);
            else map.put(tmp2, tmp3);
            if (map.size() >= m) {
                maxSum = Math.max(sum, maxSum);
            }
        }

        return maxSum;
    }
}
