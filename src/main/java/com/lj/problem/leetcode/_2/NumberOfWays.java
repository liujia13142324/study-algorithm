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

        System.out.println(numberOfWays4(10, 2));
        System.out.println(numberOfWays4(4, 1));
        System.out.println(numberOfWays4(64, 3));
        System.out.println(numberOfWays4(125, 3));
    }

    public int numberOfWays4(int n, int x) {
        long[] f = new long[n + 1];
        f[0] = 1;
        int tmp1;
        for (int i = 1, len = maxI(n, x); i <= len; i++) {
            tmp1 = (int) Math.pow(i, x);
            for (int j = n; j >= tmp1; j--) {
                f[j] = f[j] + f[j - tmp1];
            }
        }
        return (int) (f[n] % 1000000007);
    }

    public int numberOfWays2(int n, int x) {
        Integer[] maxI = new Integer[n+1];
        Long[][] f = new Long[n+1][(int) Math.ceil(Math.pow(n, 1.0/x)) + 1];
        return (int) (dfs2(n, x, 1, f, maxI) % 1000000007);
    }

    private long dfs2(int n, int x, int i, Long[][] f, Integer[] maxI) {
        if (n == 0) {
            return 1L;
        }
        if (i > maxI(n, x, maxI)) {
            return 0L;
        }

        if (f[n][i] != null) {
            return f[n][i];
        }

        f[n][i] = dfs2(n, x, i+1, f, maxI) + dfs2(n - (int) Math.pow(i, x), x, i+1, f, maxI);
        return f[n][i];
    }

    // 这个写法不如2，所以放在后面
    public int numberOfWays3(int n, int x) {
        Long[][] f = new Long[n+1][maxI(n, x) + 1];
        return (int) (dfs3(n, x, 1, f) % 1000000007);
    }

    private long dfs3(int n, int x, int i, Long[][] f) {
        if (n == 0) {
            return 1L;
        }
        if (i > f[0].length - 1 || n < 0) {
            return 0L;
        }

        if (f[n][i] != null) {
            return f[n][i];
        }

        f[n][i] = dfs3(n, x, i+1, f) + dfs3(n - (int) Math.pow(i, x), x, i+1, f);
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
            return (int) (Math.ceil(tmp));
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
            cache[n] = (int) (Math.ceil(tmp));
        }else {
            cache[n] = (int) tmp;
        }
        return cache[n];
    }
}
