package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * 1498. 满足条件的子序列数目
 * 给你一个整数数组 nums 和一个整数 target 。
 *
 * 请你统计并返回 nums 中能满足其最小元素与最大元素的 和 小于或等于 target 的 非空 子序列的数目。
 *
 * 由于答案可能很大，请将结果对 109 + 7 取余后返回。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [3,5,6,7], target = 9
 * 输出：4
 * 解释：有 4 个子序列满足该条件。
 * [3] -> 最小元素 + 最大元素 <= target (3 + 3 <= 9)
 * [3,5] -> (3 + 5 <= 9)
 * [3,5,6] -> (3 + 6 <= 9)
 * [3,6] -> (3 + 6 <= 9)
 * 示例 2：
 *
 * 输入：nums = [3,3,6,8], target = 10
 * 输出：6
 * 解释：有 6 个子序列满足该条件。（nums 中可以有重复数字）
 * [3] , [3] , [3,3], [3,6] , [3,6] , [3,3,6]
 * 示例 3：
 *
 * 输入：nums = [2,3,3,4,6,7], target = 12
 * 输出：61
 * 解释：共有 63 个非空子序列，其中 2 个不满足条件（[6,7], [7]）
 * 有效序列总数为（63 - 2 = 61）
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 106
 * 1 <= target <= 106
 */
public class NumSubseq {


    @Test
    public void testAll() {
        System.out.println(new NumSubseq().numSubseq(new int[]{3,5,6,7}, 9));;
        System.out.println(new NumSubseq().numSubseq(new int[]{3,3,6,8}, 10));;
        System.out.println(new NumSubseq().numSubseq(new int[]{2,3,3,4,6,7}, 12));;
        System.out.println(new NumSubseq().numSubseq(new int[]{6,10,12,3,29,21,12,25,17,19,16,1,2,24,9,17,25,22,12,22,26,24,24,11,3,7,24,5,15,30,23,5,20,10,19,20,9,27,11,4,23,4,4,12,22,27,16,11,26,10,23,26,16,21,24,21,17,13,21,9,16,17,27}
                , 26));;
    }

    /**
     * TODO 待研究
     * 排序+相向双指针
     */

    private static final int MOD = 1_000_000_007;
    private static final int[] pow2 = new int[100_000]; // 2^i
    private static boolean initialized = false;

    // 这样写比 static block 快
    private void init() {
        if (initialized) {
            return;
        }
        initialized = true;

        pow2[0] = 1;
        for (int i = 1; i < pow2.length; i++) {
            pow2[i] = pow2[i - 1] * 2 % MOD;
        }
    }

    public int numSubseq(int[] nums, int target) {
        init();
        Arrays.sort(nums);
        long ans = 0;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) { // 可以相等，此时子序列的最小最大是同一个数
            if (nums[left] + nums[right] <= target) {
                // nums[left] 可以作为子序列的最小值
                // 其余下标在 [left+1,right] 中的数选或不选都可以
                ans += pow2[right - left];
                left++;
            } else {
                // nums[right] 太大了，即使与剩余元素的最小值 nums[left] 相加也不满足要求
                right--;
            }
        }
        return (int) (ans % MOD);
    }


    /*public int numSubseq(int[] nums, int target) {
        Arrays.sort(nums);
        int half = target / 2;
        int firstGtIdx = binarySearchFirstGt(nums, half, 0, nums.length - 1);
        if (firstGtIdx == -1) {
            return BigDecimal.valueOf(2).pow(nums.length).add(BigDecimal.valueOf(-1)).divideAndRemainder(
                    BigDecimal.valueOf(10).pow(9).add(BigDecimal.valueOf(7))
            )[1].intValue();
        }


        BigDecimal count = BigDecimal.valueOf(0);

        for (; firstGtIdx < nums.length; firstGtIdx++) {
            int target2 = target - nums[firstGtIdx];
            if (target2 <= 0) {
                count = count.add(BigDecimal.valueOf(2).pow(firstGtIdx));
                continue;
            }
            int i = binarySearchFirstGt(nums, target2, 0, firstGtIdx - 1);
            count = count.add(BigDecimal.valueOf(2).pow(i == -1 ? 0 : firstGtIdx - i));
        }

        return BigDecimal.valueOf(2).pow(nums.length).subtract(BigDecimal.valueOf(1)).subtract(count).divideAndRemainder(
                BigDecimal.valueOf(10).pow(9).add(BigDecimal.valueOf(7))
        )[1].intValue();
    }*/

    public int binarySearchFirstGt(int[] nums, int target, int startPos, int endPos) {
        int left = startPos, right = endPos;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return firstGt(mid, nums, target, endPos);
            }
            if (nums[mid] < target) {
                left = mid + 1;
            }
            if (nums[mid] > target) {
                right = mid - 1;
            }
        }

        return left <= endPos ? left : -1;
    }

    private int firstGt(int idx, int[] nums, int target, int endPos) {
        for (int i = idx + 1; i <= endPos; i++) {
            if (nums[i] > target) {
                return i;
            }
        }
        return -1;
    }





    @Test
    public void test() {
//        System.out.println(binarySearchFirstGt(new int[]{3, 3, 6, 8}, 5, 0, 3));
//        System.out.println(binarySearchFirstGt(new int[]{3,5,6,7}, 4, 0, 3));
//        System.out.println(binarySearchFirstGt(new int[]{2,3,3,4,6,7}, 6, 0, 5));
//        System.out.println(binarySearchFirstGt(new int[]{2,3,3,4,6}, 6, 0, 4));
//        System.out.println(binarySearchFirstGt(new int[]{3,5,6,7}, 2, 0, 3));
//        System.out.println(binarySearchFirstGt(new int[]{2,3,3,4,6,7}, 5, 0, 4));
        System.out.println(Math.pow(2, 0));
    }

}
