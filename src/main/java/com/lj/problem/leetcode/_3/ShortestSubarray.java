package com.lj.problem.leetcode._3;

import org.junit.Test;

/**
 * 862. 和至少为 K 的最短子数组
 * 困难
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个整数数组 nums 和一个整数 k ，找出 nums 中和至少为 k 的 最短非空子数组 ，并返回该子数组的长度。如果不存在这样的 子数组 ，返回 -1 。
 *
 * 子数组 是数组中 连续 的一部分。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1], k = 1
 * 输出：1
 * 示例 2：
 *
 * 输入：nums = [1,2], k = 4
 * 输出：-1
 * 示例 3：
 *
 * 输入：nums = [2,-1,2], k = 3
 * 输出：3
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * -105 <= nums[i] <= 105
 * 1 <= k <= 109
 */
public class ShortestSubarray {

    @Test
    public void test() {
        /*System.out.println(shortestSubarray(new int[]{2,-1,2}, 3));
        System.out.println(shortestSubarray(new int[]{-28,81,-20,28,-29}, 89));
        System.out.println(shortestSubarray(new int[]{84,-37,32,40,95}, 167));
        System.out.println(shortestSubarray(new int[]{75,-32,50,32,97}, 129));
        System.out.println(shortestSubarray(new int[]{-34,37,51,3,-12,-50,51,100,-47,99,34,14,-13,89,31,-14,-44,23,-38,6}
                , 151));*/
//        System.out.println(shortestSubarray(new int[]{75,-32,50,32,97}, 129));
//        System.out.println(shortestSubarray(new int[]{-47,45,92,86,17,-22,77,62,-1,42}, 180));
//        System.out.println(shortestSubarray(new int[]{56,-21,56,35,-9}, 61));
//        System.out.println(shortestSubarray(new int[]{84,-37,32,40,95}, 167));
        System.out.println(shortestSubarray(new int[]{11,47,97,35,-46,59,46,51,59,80,14,-6,2,20,96,1,18,74,-17,71}
                , 282));


    }

    public int shortestSubarray2(int[] nums, int k) {
        int[] sums = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        int[] queue = new int[nums.length + 1];
        int head = 0, tail = -1;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= nums.length; i++) {
            // 这两个 while 交换顺序都是可以的
            while (tail >= head && sums[i] - sums[queue[head]] >= k) {
                ans = Math.min(ans, i - queue[head]);
                head++;
            }
            while (tail >= head && sums[queue[tail]] >= sums[i]) {
                tail--;
            }
            queue[++tail] = i;
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public int shortestSubarray(int[] nums, int k) {
        int[] sums = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }

        int l = 0, sum = 0;
        int ans = Integer.MAX_VALUE;
        int[] queue = new int[nums.length];
        int head = 0, tail = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[l] < 0) {
                l++;
                continue;
            }
            sum += nums[i];
            while (head <= tail && sums[queue[tail] + 1] - sums[l] >= sum) {
                tail--;
            }
            queue[++tail] = i;
            while (sum >= k) {
                ans = Math.min(ans, i - l + 1);
                if (ans == 1) return ans;
                head++;
                while (l < queue[head]) {
                    sum -= nums[l++];
                }
            }
        }

        while (l < nums.length) {
            sum -= nums[l ++];
            if (sum >= k) {
                ans = Math.min(ans, nums.length - l);
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
