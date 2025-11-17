package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.Arrays;

/**
 * 377. 组合总和 Ⅳ
 * 中等
 * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
 * 题目数据保证答案符合 32 位整数范围。
 *
 * 示例 1：
 * 输入：nums = [1,2,3], target = 4
 * 输出：7
 * 解释：
 * 所有可能的组合为：
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * 请注意，顺序不同的序列被视作不同的组合。
 *
 * 示例 2：
 * 输入：nums = [9], target = 3
 * 输出：0
 *
 *
 * 提示：
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 1000
 * nums 中的所有元素 互不相同
 * 1 <= target <= 1000
 * tmp
 *
 * 进阶：如果给定的数组中含有负数会发生什么？问题会产生何种变化？如果允许负数出现，需要向题目中添加哪些限制条件？
 */
public class CombinationSum4 {

    @Test
    public void test() {
        System.out.println(combinationSum4(new int[]{1,2,3}, 4));
        System.out.println(combinationSum4(new int[]{9}, 3));
        System.out.println(combinationSum4___(new int[]{1,2,3}, 4));
//        System.out.println(combinationSum4___(new int[]{9}, 3));
    }


    // 记忆化，这个是最快的
    public int combinationSum4_2_(int[] nums, int target) {
        int[] memo = new int[target + 1];
        Arrays.fill(memo, -1);
        return dfs_2(target, nums, memo);
    }

    private int dfs_2(int i, int[] nums, int[] memo) {
        if (i == 0) { // 爬完了
            return 1;
        }
        if (memo[i] != -1) { // 之前计算过
            return memo[i];
        }
        int res = 0;
        for (int x : nums) { // 枚举所有可以爬的台阶数
            if (x <= i) {
                res += dfs_2(i - x, nums, memo);
            }
        }
        return memo[i] = res; // 记忆化
    }

    // 递推-这个好像没法滚动了
    public int combinationSum4_2__(int[] nums, int target) {
        int[] f = new int[target + 1];
        f[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int x : nums) {
                if (x <= i) {
                    f[i] += f[i - x];
                }
            }
        }
        return f[target];
    }

    // 递归2
    public int combinationSum4_2(int[] nums, int target) {
        return dfs_2(target, nums);
    }

    private int dfs_2(int i, int[] nums) {
        if (i == 0) { // 爬完了
            return 1;
        }
        int res = 0;
        for (int x : nums) { // 枚举所有可以爬的台阶数
            if (x <= i) {
                res += dfs_2(i - x, nums);
            }
        }
        return res;
    }


    // 滚动数组
    public int combinationSum4____(int[] nums, int target) {
        int[] targets = new int[target + 1];
        int[] dp = new int[nums.length + 1];
        targets[0] = 1;

        for (int j = 1; j <= target; j++) {
            for (int i = 1; i <= nums.length; i++) {
                dp[i] = dp[i - 1];
                if (j >= nums[i -1]) {
                    dp[i] += targets[j - nums[i - 1]];
                }
            }
            targets[j] = dp[nums.length];
        }

        return dp[nums.length];
    }


    // 递推
    public int combinationSum4___(int[] nums, int target) {
        int[][] dp = new int[nums.length + 1][target + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 1;
        }

        for (int j = 1; j <= target; j++) {
            for (int i = 1; i <= nums.length; i++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= nums[i -1]) {
                    dp[i][j] += dp[nums.length][j - nums[i - 1]];
                }
            }
        }
        return dp[nums.length][target];
    }

    // 记忆化，递归入口 nums.length - 1
    public int combinationSum4__(int[] nums, int target) {
        int[][] cache = new int[nums.length][target + 1];
        for (int[] arr : cache) {
            Arrays.fill(arr, -1);
        }
        return dfs_(nums.length - 1, target, nums, cache);
    }

    private int dfs_(int i, int target, int[] nums, int[][] cache) {
        if (target <= 0) {
            return target == 0 ? 1 : 0;
        }
        if (i < 0) {
            return 0;
        }
        if (cache[i][target] != -1) {
            return cache[i][target];
        }

        cache[i][target] = dfs_(i - 1, target, nums, cache) + dfs_(nums.length - 1, target - nums[i], nums, cache);

        return cache[i][target];
    }

    // 记忆化，递归入口 0
    public int combinationSum4_(int[] nums, int target) {
        // 干掉排序好像更快一点
//        Arrays.sort(nums);
        int[][] cache = new int[nums.length][target + 1];
        for (int[] arr : cache) {
            Arrays.fill(arr, -1);
        }
        return dfs(0, target, nums, cache);
    }

    private int dfs(int i, int target, int[] nums, int[][] cache) {
        if (target <= 0) {
            return target == 0 ? 1 : 0;
        }
        /*if (i == nums.length || target < nums[i]) {
            return 0;
        }*/
        if (i == nums.length) {
            return 0;
        }
        if (cache[i][target] != -1) {
            return cache[i][target];
        }

        cache[i][target] = dfs(0, target - nums[i], nums, cache) + dfs(i + 1, target, nums, cache);

        return cache[i][target];
    }

    /*private int dfs(int i, int[] nums, int target, int[] cache) {
        if (target <= 0) {
            return target == 0 ? 1 : 0;
        }
        if (i == nums.length || target < nums[i]) {
            return 0;
        }
        return dfs(0, nums, target - nums[i]) + dfs(i + 1, nums, target);
    }*/


    // 递归
    public int combinationSum4(int[] nums, int target) {
        Arrays.sort(nums);
        return dfs(0, target, nums);
    }

    private int dfs(int i, int target, int[] nums) {
        if (target <= 0) {
            return target == 0 ? 1 : 0;
        }
        if (i == nums.length || target < nums[i]) {
            return 0;
        }
        return dfs(0, target - nums[i], nums) + dfs(i + 1, target, nums);
    }

}
