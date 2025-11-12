package com.lj.problem.leetcode._2;

import org.junit.Test;

/**
 * 357. 统计各位数字都不同的数字个数
 * 提示
 * 给你一个整数 n ，统计并返回各位数字都不同的数字 x 的个数，其中 0 <= x < 10n 。
 *
 * 示例 1：
 * 输入：n = 2
 * 输出：91
 * 解释：答案应为除去 11、22、33、44、55、66、77、88、99 外，在 0 ≤ x < 100 范围内的所有数字。
 *
 * 示例 2：
 * 输入：n = 0
 * 输出：1
 *
 *
 * 提示：
 * 0 <= n <= 8
 */
public class CountNumbersWithUniqueDigits {


    @Test
    public void test() {
        System.out.println(countNumbersWithUniqueDigits(4));
        System.out.println(countNumbersWithUniqueDigits2(4));
    }

    // TODO 看看别人怎么做的
    /**
     *  数学法， 首位不为 0 的数字， 例 n = 3，把 3,2,1 位的结果相加，在加上 1（为0的情况）
     *  0 -> 1
     *  1 -> 9
     *  2 -> 9 * 9
     *  3 -> 9 * 9 * 8
     *  4 -> 9 * 9 * 8 * 7
     */
    public int countNumbersWithUniqueDigits2(int n) {
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int val = 9;
            for (int j = 0; j < i; j++) {
                val *= (9 - j);
            }
            ans += val;
        }
        return ans + 1;
    }

    private static int[] cache = new int[9];
    public int countNumbersWithUniqueDigits(int n) {
        boolean[] disabled = new boolean[10];
        int ans = 0;
        while (n >= 0) {
            if (cache[n] == 0) {
                cache[n] = dfs(0, n, disabled);
            }
            ans += cache[n];
            n--;
        }
        return ans;
    }

    private int dfs(int i, int n, boolean[] disabled) {
        if (i == n) return 1;
        int ans = 0;
        for (int j = 0; j < 10; j++) {
            if (!disabled[j] && (i > 0 || j > 0)) {
                disabled[j] = true;
                ans += dfs(i + 1, n, disabled);
                disabled[j] = false;
            }
        }
        return ans;
    }
}
