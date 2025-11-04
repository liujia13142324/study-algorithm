package com.lj.problem.leetcode._2;

import org.junit.Test;

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
 */
public class PunishmentNumber {

    @Test
    public void test() {
        System.out.println(punishmentNumber(10));
        System.out.println(punishmentNumber(37));
    }

    // TODO 看看别人怎么做的
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

    private static int[] cache = null;

    public int punishmentNumber(int n) {

        if (cache == null) {
            cache = new int[1001];
            for (int k = 1; k <= 1000; k++) {
                int ans = 0;
                for (int i = 1; i <= k; i++) {
                    int tmp = i * i;
                    if (check(0, tmp+"", i)) {
                        ans += tmp;
                    }
                }
                cache[k] = ans;
            }
        }

        return cache[n];
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
