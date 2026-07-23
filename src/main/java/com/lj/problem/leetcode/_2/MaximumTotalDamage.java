package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 3186. 施咒的最大总伤害
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 一个魔法师有许多不同的咒语。
 *
 * 给你一个数组 power ，其中每个元素表示一个咒语的伤害值，可能会有多个咒语有相同的伤害值。
 *
 * 已知魔法师使用伤害值为 power[i] 的咒语时，他们就 不能 使用伤害为 power[i] - 2 ，power[i] - 1 ，power[i] + 1 或者 power[i] + 2 的咒语。
 *
 * 每个咒语最多只能被使用 一次 。
 *
 * 请你返回这个魔法师可以达到的伤害值之和的 最大值 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：power = [1,1,3,4]
 *
 * 输出：6
 *
 * 解释：
 *
 * 可以使用咒语 0，1，3，伤害值分别为 1，1，4，总伤害值为 6 。
 *
 * 示例 2：
 *
 * 输入：power = [7,1,6,6]
 *
 * 输出：13
 *
 * 解释：
 *
 * 可以使用咒语 1，2，3，伤害值分别为 1，6，6，总伤害值为 13 。
 *
 *
 *
 * 提示：
 *
 * 1 <= power.length <= 105
 * 1 <= power[i] <= 109
 */
public class MaximumTotalDamage {

    @Test
    public void test() {
//        System.out.println(maximumTotalDamage2(new int[]{1,1,3,4}));
        System.out.println(maximumTotalDamage2(new int[]{7,1,6,6}));
//        System.out.println(maximumTotalDamage2(new int[]{5,9,2,10,2,7,10,9,3,8}));
    }

    public long maximumTotalDamage(int[] power) {
        Arrays.sort(power);
        long[][] dp = new long[2][power.length + 1];
        long ans = dp[1][1] = power[0];
        for (int i = 2; i <= power.length; i++) {
            if (power[i - 1] == power[i - 2]) {
                dp[1][i] = dp[1][i - 1] + power[i - 1];
            }else {
                int pre = find(power, power[i - 1] - 2) + 1;
                dp[1][i] = Math.max(dp[0][pre], dp[1][pre]) + power[i - 1];
            }
            dp[0][i] = ans;
            ans = Math.max(ans, dp[1][i]);
        }
        return ans;
    }

    public long maximumTotalDamage3(int[] power) {
        Map<Integer, Integer> cntMap = new HashMap<>();
        for (int p: power) {
            cntMap.merge(p, 1, Integer::sum);
        }
        int n = cntMap.size();
        int[] distinct = new int[n];
        int i = 0;
        for (int x: cntMap.keySet()) {
            distinct[i++] = x;
        }
        Arrays.sort(distinct);
        int j = 0;
        long[] dp = new long[n + 1];
        for (i = 1; i <= n; i++) {
            int x = distinct[i - 1];
            while (distinct[j] < x - 2) {
                j++;
            }
            dp[i] = Math.max(dp[i - 1], dp[j] + (long)x * cntMap.get(x));
        }
        return dp[n];
    }


    public long maximumTotalDamage2(int[] power) {
        Map<Integer, Integer> cntMap = new HashMap<>();
        for (int p: power) {
            cntMap.merge(p, 1, Integer::sum);
        }
        int[] distinct = new int[cntMap.size()];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry: cntMap.entrySet()) {
            distinct[i++] = entry.getKey();
        }
        Arrays.sort(distinct);
        long[] dp = new long[distinct.length + 1];
        for (i = 1; i <= distinct.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[find(distinct, distinct[i - 1] - 2) + 1] + (long) distinct[i - 1] * cntMap.get(distinct[i - 1]) );
        }
        return dp[distinct.length];
    }


    private int find(int[] power, int target) {
        int l = -1;
        int r = power.length;
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (power[mid] < target) {
                l = mid;
            }else {
                r = mid;
            }
        }

        return l;
    }


}
