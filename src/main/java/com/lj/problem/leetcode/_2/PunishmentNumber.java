package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 2698. 求一个整数的惩罚数
 * 提示
 * 给你一个正整数 n ，请你返回 n 的 惩罚数 。
 *
 * n 的 惩罚数 定义为所有满足以下条件 i 的数的平方和：
 *
 * 1 <= i <= n
 * i * i 的十进制表示的字符串可以分割成若干连续子字符串，且这些子字符串对应的整数值之和等于 i 。
 *
 *
 * 示例 1：
 * 输入：n = 10
 * 输出：182
 * 解释：总共有 3 个范围在 [1, 10] 的整数 i 满足要求：
 * - 1 ，因为 1 * 1 = 1
 * - 9 ，因为 9 * 9 = 81 ，且 81 可以分割成 8 + 1 。
 * - 10 ，因为 10 * 10 = 100 ，且 100 可以分割成 10 + 0 。
 * 因此，10 的惩罚数为 1 + 81 + 100 = 182
 *
 * 示例 2：
 * 输入：n = 37
 * 输出：1478
 * 解释：总共有 4 个范围在 [1, 37] 的整数 i 满足要求：
 * - 1 ，因为 1 * 1 = 1
 * - 9 ，因为 9 * 9 = 81 ，且 81 可以分割成 8 + 1 。
 * - 10 ，因为 10 * 10 = 100 ，且 100 可以分割成 10 + 0 。
 * - 36 ，因为 36 * 36 = 1296 ，且 1296 可以分割成 1 + 29 + 6 。
 * 因此，37 的惩罚数为 1 + 81 + 100 + 1296 = 1478
 *
 * 提示：
 * 1 <= n <= 1000
 */
public class PunishmentNumber {

    @Test
    public void test() {
        System.out.println(punishmentNumber(10));
        System.out.println(punishmentNumber3(10));
        System.out.println(punishmentNumber(37));
        System.out.println(punishmentNumber3(37));
    }


    private static int[] cache = null;
    public int punishmentNumber(int n) {

        if (cache == null) {
            cache = new int[1001];
            for (int k = 1; k < 1001; k++) {
                int tmp = k * k;
                if (check2(0, (tmp+"").toCharArray(), k)) {
                    cache[k] = cache[k - 1] + tmp;
                }else {
                    cache[k] = cache[k - 1];
                }
            }
        }

        return cache[n];
    }


    private static int[] cache3 = new int[1001];
    private static int cacheIdx = 0;

    public int punishmentNumber3(int n) {
        if (cacheIdx >= n) {
            return cache3[n];
        }

        for (int i = cacheIdx + 1; i <= n; i++) {
            int tmp = i * i;
            if (check2(0, (tmp+"").toCharArray(), i)) {
                cache3[++cacheIdx] = cache3[cacheIdx - 1] + tmp;
            }else {
                cache3[++cacheIdx] = cache3[cacheIdx - 1];
            }
        }
        return cache3[cacheIdx];
    }

    public int punishmentNumber2(int n) {
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            int tmp = i * i;
            if (check(0, tmp+"", i)) {
                ans += tmp;
            }
        }
        return ans;
    }

    /**
     * 不使用 substring 和 parseInteger
     */
    private boolean check2(int idx, char[] n, int target) {
        if (idx == n.length) return target == 0;
        int x = 0;
        for (int i = idx; i < n.length; i++) {
            x = x * 10 + n[i] - '0';
            if (x > target) break;
            if (check2(i + 1, n, target - x) ) {
                return true;
            }
        }
        return false;
    }


    private boolean check(int idx, String n, int target) {
        if (idx == n.length() || target < 0) return target == 0;
        for (int i = idx, len = n.length(); i < len; i++) {
            int tmp = Integer.parseInt(n.substring(idx, i + 1));
            if (check(i + 1, n, target - tmp) ) {
                return true;
            }
        }
        return false;
    }

}
