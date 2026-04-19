package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 3040. 相同分数的最大操作数目 II
 * 给你一个整数数组 nums ，如果 nums 至少 包含 2 个元素，你可以执行以下操作中的 任意 一个：
 * 选择 nums 中最前面两个元素并且删除它们。
 * 选择 nums 中最后两个元素并且删除它们。
 * 选择 nums 中第一个和最后一个元素并且删除它们。
 * 一次操作的 分数 是被删除元素的和。
 * 在确保 所有操作分数相同 的前提下，请你求出 最多 能进行多少次操作。
 * 请你返回按照上述要求 最多 可以进行的操作次数。
 *
 * 示例 1：
 * 输入：nums = [3,2,1,2,3,4]
 * 输出：3
 * 解释：我们执行以下操作：
 * - 删除前两个元素，分数为 3 + 2 = 5 ，nums = [1,2,3,4] 。
 * - 删除第一个元素和最后一个元素，分数为 1 + 4 = 5 ，nums = [2,3] 。
 * - 删除第一个元素和最后一个元素，分数为 2 + 3 = 5 ，nums = [] 。
 * 由于 nums 为空，我们无法继续进行任何操作。

 * 示例 2：
 * 输入：nums = [3,2,6,1,4]
 * 输出：2
 * 解释：我们执行以下操作：
 * - 删除前两个元素，分数为 3 + 2 = 5 ，nums = [6,1,4] 。
 * - 删除最后两个元素，分数为 1 + 4 = 5 ，nums = [6] 。
 * 至多进行 2 次操作。
 *
 * 提示：
 * 2 <= nums.length <= 2000
 * 1 <= nums[i] <= 1000
 */
public class MxOperations {

    @Test
    public void test() {
        System.out.println(maxOperations__(new int[]{3,2,1,2,3,4}));
    }

    public int maxOperations_(int[] nums) {
        Map<Integer, int[][]> map = new HashMap<>();

        map.put(nums[0] + nums[1], init(nums.length));
        if (map.get(nums[nums.length - 1] + nums[nums.length - 2]) == null) {
            map.put(nums[nums.length - 1] + nums[nums.length - 2], init(nums.length));
        }
        if (map.get(nums[0] + nums[nums.length - 1]) == null) {
            map.put(nums[0] + nums[nums.length - 1], init(nums.length));
        }
        return 1 + Math.max(
                dfs_(2, nums.length - 1, nums[0] + nums[1], nums, map.get(nums[0] + nums[1])),
                Math.max(
                        dfs_(0, nums.length - 3, nums[nums.length - 1] + nums[nums.length - 2], nums, map.get(nums[nums.length - 1] + nums[nums.length - 2])),
                        dfs_(1, nums.length - 2, nums[0] + nums[nums.length - 1], nums, map.get(nums[0] + nums[nums.length - 1]))
                )
        );
    }
    private int dfs_(int i, int j, int k, int[] nums, int[][] cache) {
        if (i >= j) return 0;
        if (cache[i][j] != -1) return cache[i][j];
        int ans = 0;
        if (i + 1 <= j && nums[i] + nums[i + 1] == k) {
            ans = cache[i][j] = dfs_(i + 2, j, k, nums, cache) + 1 ;
        }
        if (ans >= nums.length / 2) {
            return ans;
        }
        if (j - 1 >= i && nums[j] + nums[j - 1] == k) {
            ans = Math.max(ans, dfs_(i, j - 2, k, nums, cache) + 1);
        }
        if (ans >= nums.length / 2) {
            return cache[i][j] = ans;
        }
        if (nums[i] + nums[j] == k) {
            ans = Math.max(ans, dfs_(i + 1, j - 1, k, nums, cache) + 1);
        }
        return cache[i][j] = ans;
    }

    private int[][] init(int length) {
        int[][] cache = new int[length][length];
        for (int[] tmp: cache) Arrays.fill(tmp, -1);
        return cache;
    }

    /**
     * 递推
     * @param nums
     * @return
     */
    public int maxOperations__(int[] nums) {
        Map<Integer, int[][]> map = new HashMap<>();
        map.put(nums[0] + nums[1], new int[nums.length + 1][nums.length + 1]);
        map.computeIfAbsent(nums[nums.length - 1] + nums[nums.length - 2], k -> new int[nums.length + 1][nums.length + 1]);
        map.computeIfAbsent(nums[0] + nums[nums.length - 1], k -> new int[nums.length + 1][nums.length + 1]);

        int[] ks = new int[]{nums[0] + nums[1], nums[nums.length - 1] + nums[nums.length - 2], nums[0] + nums[nums.length - 1]};

        for (int k: ks) {
            for (int i = nums.length - 2; i >= 0; i--) {
                for (int j = i + 2; j <= nums.length; j++) {
                    if (nums[i] + nums[i + 1] == k ) {
                        int[][] dp = map.get(k);
                        dp[i][j] = 1 + dp[i + 2][j];
                    }
                    if (nums[i] + nums[j - 1] == k) {
                        int[][] dp = map.get(k);
                        dp[i][j] = Math.max(dp[i][j], 1 + dp[i+1][j-1]);
                    }
                    if (nums[j - 1] + nums[j - 2] == k) {
                        int[][] dp = map.get(k);
                        dp[i][j] = Math.max(dp[i][j], 1 + dp[i][j - 2]);
                    }
                }
            }
        }

        int ans = 0;
        for (int[][] dp: map.values()) {
            ans = Math.max(ans, dp[0][nums.length]);
        }

        return ans;
    }

    public int maxOperations(int[] nums) {
        int ans1 = 1 + dfs(2, nums.length - 1, nums[0] + nums[1], nums);
        if (ans1 >= nums.length / 2) {
            return ans1;
        }
        int ans2 = 1 + dfs(0, nums.length - 3, nums[nums.length - 1] + nums[nums.length - 2], nums);
        if (ans2 >= nums.length / 2) {
            return ans2;
        }
        int ans3 = 1 + dfs(1, nums.length - 2, nums[0] + nums[nums.length - 1], nums);
        return Math.max(ans1, Math.max(ans2, ans3));
    }

    private int dfs(int i, int j, int k, int[] nums) {
        if (i >= j) return 0;
        int ans = 0;
        if (i + 1 <= j && nums[i] + nums[i + 1] == k) {
            ans = dfs(i + 2, j, k, nums) + 1 ;
        }
        if (ans >= nums.length / 2) {
            return ans;
        }
        if (j - 1 >= i && nums[j] + nums[j - 1] == k) {
            ans = Math.max(ans, dfs(i, j - 2, k, nums) + 1);
        }
        if (ans >= nums.length / 2) {
            return ans;
        }
        if (nums[i] + nums[j] == k) {
            ans = Math.max(ans, dfs(i + 1, j - 1, k, nums) + 1);
        }
        return ans;
    }

}
