package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntFunction;

/**
 * 2915. 和为目标值的最长子序列的长度
 * 给你一个下标从 0 开始的整数数组 nums 和一个整数 target 。
 * 返回和为 target 的 nums 子序列中，子序列 长度的最大值 。如果不存在和为 target 的子序列，返回 -1 。
 * 子序列 指的是从原数组中删除一些或者不删除任何元素后，剩余元素保持原来的顺序构成的数组。
 *
 * 示例 1：
 * 输入：nums = [1,2,3,4,5], target = 9
 * 输出：3
 * 解释：总共有 3 个子序列的和为 9 ：[4,5] ，[1,3,5] 和 [2,3,4] 。最长的子序列是 [1,3,5] 和 [2,3,4] 。所以答案为 3 。
 *
 * 示例 2：
 * 输入：nums = [4,1,3,2,1,5], target = 7
 * 输出：4
 * 解释：总共有 5 个子序列的和为 7 ：[4,3] ，[4,1,2] ，[4,2,1] ，[1,1,5] 和 [1,3,2,1] 。最长子序列为 [1,3,2,1] 。所以答案为 4 。

 * 示例 3：
 * 输入：nums = [1,1,5,4,5], target = 3
 * 输出：-1
 * 解释：无法得到和为 3 的子序列。
 *
 *
 * 提示：
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 1000
 * 1 <= target <= 1000
 */
public class LengthOfLongestSubsequence {

    @Test
    public void test() {
        System.out.println(lengthOfLongestSubsequence3(Arrays.asList(1,2,3,4,5), 9));
        System.out.println(lengthOfLongestSubsequence3(Arrays.asList(4,1,3,2,1,5), 7));
        System.out.println(lengthOfLongestSubsequence3(Arrays.asList(1,1,5,4,5), 3));

        System.out.println(lengthOfLongestSubsequence4(Arrays.asList(1,2,3,4,5), 9));
        System.out.println(lengthOfLongestSubsequence4(Arrays.asList(4,1,3,2,1,5), 7));
        System.out.println(lengthOfLongestSubsequence4(Arrays.asList(1,1,5,4,5), 3));

    }

    public int lengthOfLongestSubsequence4(List<Integer> nums, int target) {
        int[] array = nums.stream().mapToInt(Integer::intValue).toArray();
        int[] f = new int[target + 1];
        for (int i = 1; i < f.length; i++) {
            f[i] = Integer.MIN_VALUE;
        }
        for (int k : array) {
            for (int j = target; j > 0; j--) {
                if (j >= k) {
                    f[j] = Math.max(f[j], 1 + f[j - k]);
                }
            }
        }
        return f[target] < 0 ? -1 : f[target];
    }

    public int lengthOfLongestSubsequence3(List<Integer> nums, int target) {
        int len = nums.size();
        int[][] f = new int[target + 1][len + 1];
        for (int i = 1; i < f.length; i++) {
            f[i][0] = Integer.MIN_VALUE;
        }
        for (int i = 0; i < len; i++) {
            for (int j = 1; j <= target; j++) {
                if (j < nums.get(i)) {
                    f[j][i + 1] = f[j][i];
                }else {
                    f[j][i + 1] = Math.max(f[j][i], 1 + f[j-nums.get(i)][i]);
                }
            }
        }
        return f[target][len] < 0 ? -1 : f[target][len];
    }

    public int lengthOfLongestSubsequence2(List<Integer> nums, int target) {
        Integer[][] f = new Integer[target + 1][nums.size()];
        int ans = dfs2(nums, target, nums.size() - 1, f);
        return ans < 0 ? -1 : ans;
    }

    private int dfs2(List<Integer> nums, int target, int i, Integer[][] f) {
        if (target == 0) {
            return 0;
        }
        if (i < 0) {
            return Integer.MIN_VALUE;
        }

        if (f[target][i] != null) {
            return f[target][i];
        }

        if (nums.get(i) > target) {
            f[target][i] = dfs2(nums, target, i - 1, f);
            return f[target][i];
        }

        f[target][i] = Math.max(dfs2(nums, target, i - 1, f), 1 + dfs2(nums, target - nums.get(i), i - 1, f));
        return f[target][i];
    }


    public int lengthOfLongestSubsequence(List<Integer> nums, int target) {
        int ans = dfs(nums, target, nums.size() - 1);
        return ans < 0 ? -1 : ans;
    }

    private int dfs(List<Integer> nums, int target, int i) {
        if (target == 0) {
            return 0;
        }

        if (i < 0) {
            return Integer.MIN_VALUE;
        }

        if (nums.get(i) > target) {
            return dfs(nums, target, i - 1);
        }

        return Math.max(dfs(nums, target, i - 1), 1 + dfs(nums, target - nums.get(i), i - 1));
    }
}
