package com.lj.problem.leetcode._2;

import org.junit.Test;

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
    }

    public int numberOfWays(int n, int x) {
        double tmp = Math.pow(n, 1.0/x);
        int maxI;
        if (tmp == Math.floor(tmp)) {
            maxI = (int) (tmp + 1);
        }else {
            maxI = (int) Math.ceil(tmp);
        }
        return dfs(n, x, 1, maxI);
    }

    private int dfs(int n, int x, int i, int maxI) {
        if (n == 0) {
            return 1;
        }

        if (i >= maxI) {
            return 0;
        }
        // 0^2 + 1^2 + 3^2, 1^2 + 3^2
        // 0 + 4, 1 + 3
        return dfs(n, x, i+1, maxI) + dfs(n - (int) Math.pow(i, x), x, i+1, maxI);
    }
}
