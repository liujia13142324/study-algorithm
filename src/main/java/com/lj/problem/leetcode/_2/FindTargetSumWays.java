package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 494. 目标和
 * 给你一个非负整数数组 nums 和一个整数 target 。
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,1,1,1], target = 3
 * 输出：5
 * 解释：一共有 5 种方法让最终目标和为 3 。
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 * 示例 2：
 * jenkins
 * 输入：nums = [1], target = 1
 * 输出：1
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 20
 * 0 <= nums[i] <= 1000
 * 0 <= sum(nums[i]) <= 1000
 * -1000 <= target <= 1000
 */
public class FindTargetSumWays {

    @Test
    public void test() {
        System.out.println(findTargetSumWays2(new int[]{1,1,1,1,1}, 3));
        System.out.println(findTargetSumWays2(new int[]{1}, 1));
    }

    public int findTargetSumWays2(int[] nums, int target) {
        target += Arrays.stream(nums).sum();
        if (target < 0 || target % 2 != 0) {
            return 0;
        }
        target /= 2;
        Map<String, Integer> cache = new HashMap<>();
        return dfs(nums, target, nums.length-1, cache);
    }

    public int dfs(int[] nums, int target, int i, Map<String, Integer> cache) {
        if (i < 0) {
            return target == 0 ? 1 : 0;
        }

        String cacheKey = target + "_" + i;

        if (cache.containsKey(cacheKey)) {
            return cache.get(cacheKey);
        }

        int result ;

        if (target < nums[i]) {
            result = dfs(nums, target, i-1, cache);
        }else {
            result = dfs(nums, target - nums[i], i-1, cache) + dfs(nums, target, i-1, cache);
        }

        cache.put(cacheKey, result);

        return result;
    }


    public int findTargetSumWays(int[] nums, int target) {
        return findTargetSumWays(nums, target, nums.length-1);
    }

    public int findTargetSumWays(int[] nums, int target, int i) {
        if (i < 0) {
            return target == 0 ? 1 : 0;
        }

        return findTargetSumWays(nums, target - nums[i], i-1) + findTargetSumWays(nums, target + nums[i], i-1);
    }

}
