package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * 2787. 将一个数字表示成幂的和的方案数
 * 给你两个 正 整数 n 和 x 。
 * 请你返回将 n 表示成一些 互不相同 正整数的 x 次幂之和的方案数。换句话说，你需要返回互不相同整数 [n1, n2, ..., nk] 的集合数目，满足 n = n1x + n2x + ... + nkx 。
 * 由于答案可能非常大，请你将它对 109 + 7 取余后返回。
 * 比方说，n = 160 且 x = 3 ，一个表示 n 的方法是 n = 23 + 33 + 53 。
 *
 *
 * 示例 1：
 * 输入：n = 10, x = 2
 * 输出：1
 * 解释：我们可以将 n 表示为：n = 32 + 12 = 10 。
 * 这是唯一将 10 表达成不同整数 2 次方之和的方案。
 *
 * 示例 2：
 * 输入：n = 4, x = 1
 * 输出：2
 * 解释：我们可以将 n 按以下方案表示：
 * - n = 41 = 4 。
 * - n = 31 + 11 = 4 。
 *
 *
 * 提示：
 * 1 <= n <= 300
 * 1 <= x <= 5
 */
public class NumberOfWays {

    @Test
    public void test() {
        System.out.println(numberOfWays(10, 2));
        System.out.println(numberOfWays(4, 1));
        System.out.println(numberOfWays(64, 3));
        System.out.println(numberOfWays(125, 3));

        System.out.println(numberOfWays2(10, 2));
        System.out.println(numberOfWays2(4, 1));
        System.out.println(numberOfWays2(64, 3));
        System.out.println(numberOfWays2(125, 3));
    }

    public int numberOfWays2(int n, int x) {
        Integer[] maxI = new Integer[n+1];
        Long[][] f = new Long[n+1][maxI(n, x, maxI) + 1];
        return (int) (dfs2(n, x, 1, f, maxI) % 1000000007);
    }

    private long dfs2(int n, int x, int i, Long[][] f, Integer[] maxI) {
        if (n == 0) {
            return 1L;
        }
        if (i >= maxI(n, x, maxI)) {
            return 0L;
        }

        if (f[n][i] != null) {
            return f[n][i];
        }

        f[n][i] = dfs2(n, x, i+1, f, maxI) + dfs2(n - (int) Math.pow(i, x), x, i+1, f, maxI);
        return f[n][i];
    }

    public int numberOfWays(int n, int x) {
        return dfs(n, x, 1, maxI(n, x));
    }

    private int dfs(int n, int x, int i, int maxI) {
        if (n == 0) {
            return 1;
        }
        if (i > maxI) {
            return 0;
        }
        // 0^2 + 1^2 + 3^2, 1^2 + 3^2
        // 0 + 4, 1 + 3
        int nextN = n - (int) Math.pow(i, x);
        return dfs(n, x, i+1, maxI) + dfs(nextN, x, i+1, maxI(nextN, x));
    }

    private int maxI(int n, int x) {
        double tmp = Math.pow(n, 1.0/x);
        if (Math.ceil(tmp) - tmp < 1e-9) {
            return (int) (Math.ceil(tmp) + 1);
        }else {
            return (int) tmp;
        }
    }

    private int maxI(int n, int x, Integer[] cache) {
        if (cache[n] != null) {
            return cache[n];
        }
        double tmp = Math.pow(n, 1.0/x);
        if (Math.ceil(tmp) - tmp < 1e-9) {
            cache[n] = (int) (Math.ceil(tmp) + 1);
        }else {
            cache[n] = (int) tmp;
        }
        return cache[n];
    }
}
