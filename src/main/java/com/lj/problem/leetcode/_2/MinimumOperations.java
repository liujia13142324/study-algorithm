package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 2826. 将三个组排序
 * 提示
 * 给你一个整数数组 nums 。nums 的每个元素是 1，2 或 3。在每次操作中，你可以删除 nums 中的一个元素。返回使 nums 成为 非递减 顺序所需操作数的 最小值。
 *
 * 示例 1：
 * 输入：nums = [2,1,3,2,1]
 * 输出：3
 * 解释：
 * 其中一个最优方案是删除 nums[0]，nums[2] 和 nums[3]。
 *
 * 示例 2：
 * 输入：nums = [1,3,2,1,3,3]
 * 输出：2
 * 解释：
 * 其中一个最优方案是删除 nums[1] 和 nums[2]。
 *
 * 示例 3：
 * 输入：nums = [2,2,2,2,3,3]
 * 输出：0
 * 解释：
 * nums 已是非递减顺序的。
 *
 * 提示：
 *
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 3
 * 进阶：你可以使用 O(n) 时间复杂度以内的算法解决吗？
 */
public class MinimumOperations {

    @Test
    public void test() {
        System.out.println(minimumOperations2(Arrays.asList(2,1,3,2,1)));
        System.out.println(minimumOperations2(Arrays.asList(1,3,2,1,3,3)));
        System.out.println(minimumOperations2(Arrays.asList(2,2,2,2,3,3)));
    }


    public int minimumOperations5(List<Integer> nums) {
        int[] dp = new int[4];
        int max = 0;
        for (int num: nums) {
            for (int i = num; i > 0; i--) {
                dp[num] = Math.max(dp[num], dp[i] + 1);
            }
            max = Math.max(max, dp[num]);
        }
        return nums.size() - max;
    }


    /**
     * 方法三：合法子序列 DP
     * 这是一个固定的套路，见动态规划题单中的「§7.2 合法子序列 DP」。
     * 一般定义 f[x] 表示以元素 x 结尾的合法子序列的最长长度/个数/元素和，从子序列的倒数第二个数转移过来。
     * 本题倒数第二个数记作 j，那么必须满足 j ≤ x。
     *
     * 转移方程为
     * f[x] = max(f[1~j]) + 1;
     *
     * @param nums
     * @return
     */
    public int minimumOperations3(List<Integer> nums) {
        int[] dp = new int[4];
        int max = 0;
        for (int num: nums) {
            int tmp = 0;
            for (int i = 1; i <= num; i++) {
                tmp = Math.max(tmp, dp[i]);
            }
            dp[num] = tmp + 1;
            max = Math.max(max, dp[num]);
        }

        return nums.size() - max;
    }

    public int minimumOperations2_(List<Integer> nums) {
        int[] dp = new int[4];
        for (int num: nums) {
            dp[num] ++;
            dp[2] = Math.max(dp[1], dp[2]);
            dp[3] = Math.max(dp[2], dp[3]);
        }
        return nums.size() - dp[3];
    }

    public int minimumOperations2(List<Integer> nums) {
        int[] dp = new int[4];
        for (int num: nums) {
            for (int i = 3; i >= num; i--) {
                dp[i] = Math.max(dp[i], dp[num] + 1);
            }
        }
        return nums.size() - dp[3];
    }


    public int minimumOperations(List<Integer> nums) {
        int[] arr = new int[nums.size()];
        int i = 0;
        for (int num: nums) arr[i++] = num;
        i = 0;
        for (int num: arr) {
            int idx = find(-1, i, arr, num);
            if (idx == i) {
                arr[i++] = num;
            } else {
                arr[idx] = num;
            }
        }
        return arr.length - i;
    }

    private int find(int l, int r, int[] nums, int target) {
        while (l + 1 < r) {
            int mid = (l + r) >>> 1;
            if (nums[mid] > target) {
                r = mid;
            }else {
                l = mid;
            }
        }
        return r;
    }

    public int minimumOperations4(List<Integer> nums) {
        int[] arr = new int[nums.size()];
        for (int i = 0; i < nums.size(); i++) {
            arr[i] = nums.get(i);
        }
        int[] tmp = new int[arr.length];
        int r = 0;
        for (int num: arr) {
            int idx = lowerBound(-1, r, tmp, num);
            if (idx == r) {
                tmp[r++] = num;
            }else {
                tmp[idx] = num;
            }
        }
        return arr.length - r;
    }

    private int lowerBound(int l, int r, int[] arr, int target) {
        while (l + 1 < r) {
            int mid = (l + r) >>> 1;
            if (arr[mid] > target) {
                r = mid;
            }else {
                l = mid;
            }
        }
        return r;
    }
}
