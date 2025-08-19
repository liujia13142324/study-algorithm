package com.lj.problem.leetcode._2;

import org.junit.Test;

/**
 * 416. 分割等和子集
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 * 示例 1：
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 *
 * 示例 2：
 * 输入：nums = [1,2,3,5]
 * 输出：false
 * 解释：数组不能分割成两个元素和相等的子集。
 *
 * 提示：
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 */
public class CanPartition {

    @Test
    public void test() {
        System.out.println(canPartition(new int[]{1,2,3,5}));
        System.out.println(canPartition(new int[]{1,5,11,5}));
        System.out.println(canPartition(new int[]{1, 1}));

        System.out.println(canPartition4(new int[]{1,2,3,5}));
        System.out.println(canPartition4(new int[]{1,5,11,5}));
        System.out.println(canPartition4(new int[]{1, 1}));
    }

    public boolean canPartition4(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) return false;
        int t = sum / 2, i, j;
        boolean[] f = new boolean[t + 1];
        f[0] = true;
        outer:
        for (i = 0; i < nums.length; i++) {
            for (j = t; j > 0; j--) {
                if (nums[i] <= j && !f[j]) {
                    f[j] = f[j - nums[i]];
                }
                if (f[t] || i == nums.length - 1) {
                    break outer;
                }
            }
        }
        return f[t];
    }

    public boolean canPartition3(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) return false;
        int t = sum / 2, i;
        boolean[][] f = new boolean[nums.length][t + 1];
        for (i = 0; i < f.length; i++) {
            f[i][0] = true;
        }
        for (i = 1; i < nums.length; i++) {
            for (int j = 1; j <= t; j++) {
                if (nums[i] > j) {
                    f[i][j] = f[i - 1][j];
                }else {
                    f[i][j] = f[i - 1][j] || f[i - 1][j - nums[i]];
                }
            }
        }

        return f[nums.length - 1][t];
    }


    public boolean canPartition2(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) return false;

        Boolean[][] f = new Boolean[nums.length][sum/2 + 1];

        return dfs2(nums, 0, sum/2, f);
    }

    private boolean dfs2(int[] nums, int i, int target, Boolean[][] f) {
        if (target == 0) {
            return true;
        }
        if (i >= nums.length) {
            return false;
        }

        if (f[i][target] != null) {
            return f[i][target];
        }

        if (nums[i] > target) {
            f[i][target] = dfs2(nums, i + 1, target, f);
            return f[i][target];
        }

        f[i][target] = dfs2(nums, i + 1, target - nums[i], f) || dfs2(nums, i + 1, target, f);
        return f[i][target];
    }

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // sumAll - sumN = sumM
        // sumN = sumM
        // sumN = sumAll / 2
        if (sum % 2 != 0) return false;

        return dfs(nums, 0, sum/2);
    }

    private boolean dfs(int[] nums, int i, int target) {
        if (target == 0) {
            return true;
        }
        if (i >= nums.length) {
            return false;
        }
        if (nums[i] > target) {
            return dfs(nums, i + 1, target);
        }

        return dfs(nums, i + 1, target - nums[i]) || dfs(nums, i + 1, target);
    }
}
