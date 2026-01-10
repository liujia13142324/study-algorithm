package com.lj.problem.leetcode._3;

import org.junit.Test;

import java.util.Arrays;

/**
 * 1671. 得到山形数组的最少删除次数
 * 提示
 * 我们定义 arr 是 山形数组 当且仅当它满足：
 *
 * arr.length >= 3
 * 存在某个下标 i （从 0 开始） 满足 0 < i < arr.length - 1 且：
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * 给你整数数组 nums​ ，请你返回将 nums 变成 山形状数组 的​ 最少 删除次数。
 *
 * 示例 1：
 * 输入：nums = [1,3,1]
 * 输出：0
 * 解释：数组本身就是山形数组，所以我们不需要删除任何元素。
 *
 * 示例 2：
 * 输入：nums = [2,1,1,5,6,2,3,1]
 *
 * 输出：3
 * 解释：一种方法是将下标为 0，1 和 5 的元素删除，剩余元素为 [1,5,6,3,1] ，是山形数组。
 *
 * 提示：
 *
 * 3 <= nums.length <= 1000
 * 1 <= nums[i] <= 109
 * 题目保证 nums 删除一些元素后一定能得到山形数组。
 *
 * 通过次数
 * 22,727/45.2K
 * 通过率
 * 50.2%
 */
public class MinimumMountainRemovals {

    @Test
    public void test() {
//        System.out.println(minimumMountainRemovals(new int[]{1,3,1}));
//        System.out.println(minimumMountainRemovals(new int[]{2,1,1,5,6,2,3,1}));
//        System.out.println(minimumMountainRemovals(new int[]{4,3,2,1,1,2,3,1}));
//        System.out.println(minimumMountainRemovals(new int[]{1,2,3,4,4,3,2,1}));
        System.out.println(minimumMountainRemovals(new int[]{2,9,19,45,41,96,72,40,100,37,36,13,7}));
    }

    public int minimumMountainRemovals(int[] nums) {
        int[] dp = new int[nums.length];
        int i = 0;
        int j = nums.length - 1;

        int idx1 = i;
        int idx2 = j;
        int tmp;

        int firstI = 0;
        int firstJ = 0;

        while (i < nums.length) {
            tmp = find1(-1, idx1, nums[i], dp);
            if (tmp == idx1) {
                dp[idx1++] = nums[i];
                firstI = i;
            }else {
                dp[tmp] = nums[i];
            }
            i++;
        }

        Arrays.fill(dp, 0);
        while (j > firstI) {
            tmp = find2(idx2, nums.length, nums[j], dp);
            if (tmp == idx2) {
                dp[idx2--] = nums[j];
                firstJ = j;
            }else {
                dp[tmp] = nums[j];
            }
            j--;
        }

        int ans = nums.length - (idx1 + nums.length - 1 - idx2);

        // 反着还要在算一边

        return nums[firstI] == nums[firstJ] ? ans + 1 : ans;
    }

    private int find1(int l, int r, int target, int[] nums) {
        while (l + 1 < r) {
            int mid = (l + r) >>> 1;
            if (nums[mid] >= target) {
                r = mid;
            }else {
                l = mid;
            }
        }
        return r;
    }

    private int find2(int l, int r, int target, int[] nums) {
        while (l + 1 < r) {
            int mid = (l + r) >>> 1;
            if (nums[mid] >= target) {
                l = mid;
            }else {
                r = mid;
            }
        }
        return l;
    }

}
