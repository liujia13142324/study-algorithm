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
        System.out.println(findTargetSumWays5(new int[]{1,1,1,1,1}, 3));
        System.out.println(findTargetSumWays5(new int[]{1}, 1));
        System.out.println(findTargetSumWays5(new int[]{0}, 0));

        System.out.println(findTargetSumWays4(new int[]{1,1,1,1,1}, 3));
        System.out.println(findTargetSumWays4(new int[]{1}, 1));
        System.out.println(findTargetSumWays4(new int[]{0}, 0));

        System.out.println(findTargetSumWays3(new int[]{1,1,1,1,1}, 3));
        System.out.println(findTargetSumWays3(new int[]{1}, 1));
    }


    public int findTargetSumWays5(int[] nums, int target) {
        target += Arrays.stream(nums).sum();
        if (target < 0 || target % 2 != 0) {
            return 0;
        }
        target /= 2;

        int[] f = new int[target + 1];
        f[0] = 1;
        for (int num : nums) {
            // 左边会影响右边，如果从左边开始跑，那么新右会被新左影响，所以要从右边往左跑，这样都只会用老一轮的数据，不会被影响
            for (int j = target; j >= 0; j--) {
                if (j >= num) {
                    f[j] = f[j] + f[j - num];
                }
            }
        }
        return f[target];
    }

    public int findTargetSumWays4(int[] nums, int target) {
        target += Arrays.stream(nums).sum();
        if (target < 0 || target % 2 != 0) {
            return 0;
        }
        target /= 2;

        int[][] f = new int[nums.length + 1][target + 1];
        for (int i = 0; i < f.length; i++) {
            f[i][0] = 1;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j <= target; j++) {
                if (j < nums[i]) {
                    f[i+1][j] = f[i][j];
                }else {
                    f[i+1][j] = f[i][j] + f[i][j - nums[i]];
                }
            }
        }

        return f[nums.length][target];
    }

    public int findTargetSumWays3(int[] nums, int target) {
        target += Arrays.stream(nums).sum();
        if (target < 0 || target % 2 != 0) {
            return 0;
        }
        target /= 2;
        Integer[][] cache = new Integer[nums.length][target + 1];
        return dfs1(nums, target, nums.length-1, cache);
    }

    public int dfs1(int[] nums, int target, int i, Integer[][] cache) {
        if (i < 0) {
            return target == 0 ? 1 : 0;
        }

        if (cache[i][target] != null) {
            return cache[i][target];
        }

        int result ;

        if (target < nums[i]) {
            result = dfs1(nums, target, i-1, cache);
        }else {
            result = dfs1(nums, target - nums[i], i-1, cache) + dfs1(nums, target, i-1, cache);
        }

        cache[i][target] = result;

        return result;
    }


    public int findTargetSumWays2(int[] nums, int target) {
        target += Arrays.stream(nums).sum();
        if (target < 0 || target % 2 != 0) {
            return 0;
        }
        target /= 2;
        return dfs(nums, target, nums.length-1);
    }

    public int dfs(int[] nums, int target, int i) {
        if (i < 0) {
            return target == 0 ? 1 : 0;
        }

        if (target < nums[i]) {
            return dfs(nums, target, i-1);
        }

        return dfs(nums, target - nums[i], i-1) + dfs(nums, target, i-1);
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
