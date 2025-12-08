package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

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

        System.out.println(numberOfWays7_(74, 5));
    }

    // 预处理
    static int[][] dp;
    private void init() {
        if (dp == null) {
            dp = new int[6][301];
            for (int i = 0; i < 6; i++) dp[i][0] = 1;
            for (int x = 1; x < 6; x++) {
                for (int i = 1; i <= 300; i++) {
                    for (int j = 300; j > 0; j--) {
                        long val = j - pow(i, x);
                        if (val >= 0) {
                            dp[x][j] = (dp[x][j] + dp[x][(int)val]) % (1_000_000_000 + 7);
                        }
                    }
                }
            }
        }
    }
    public int numberOfWays7____(int n, int x) {
        init();
        return dp[x][n];
    }

    // 递推大多数情况下，并没有递归快，因为递归只需要计算需要算的节点，但是递推需要计算全部节点
    // 但如果使用预处理就不一样了，
    public int numberOfWays7___(int n, int x) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = n; j > 0; j--) {
                long val = j - pow(i, x);
                if (val >= 0) {
                    dp[j] = (dp[j] + dp[(int)val]) % (1_000_000_000 + 7);
                }
            }
        }
        return dp[n];
    }

    public int numberOfWays7__(int n, int x) {
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) dp[i][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                long val = j - pow(i, x);
                if (val >= 0) {
                    dp[i][j] = (dp[i - 1][j] + dp[i - 1][(int)val]) % (1_000_000_000 + 7);
                }else {
                    dp[i][j] = dp[i - 1][j] % (1_000_000_000 + 7);
                }
            }
        }
        return dp[n][n];
    }

    public int numberOfWays7_(int n, int x) {
        int[][] cache = new int[n + 1][n + 1];
        for (int[] arr: cache) Arrays.fill(arr, -1);
        return (int) dfs(n, n, x, cache);
    }

    private long dfs(int i, int target, int x, int[][] cache) {
        if (target == 0) return 1;
        if (i < 0 || target < 0) return 0;
        if (cache[i][target] != -1) return cache[i][target];
        long val = target - pow(i, x);
        cache[i][target] = (int) (dfs(i - 1, val < 0 ? -1 : (int) val, x, cache) + dfs(i - 1 , target, x, cache) % (1_000_000_000 + 7));
        return cache[i][target];
    }

    private long dfs2(int i, int target, int x, int[][] cache) {
        if (target == 0) return 1;
        if (i < 0) return 0;
        if (cache[i][target] != -1) return cache[i][target];
        long val = target - pow(i, x);
        if (val < 0) {
            cache[i][target] = (int) (dfs2(i - 1 , target, x, cache) % (1_000_000_000 + 7));
        }else {
            cache[i][target] = (int) ((dfs2(i - 1, (int) val, x, cache) + dfs2(i - 1 , target, x, cache)) % (1_000_000_000 + 7));
        }
        return cache[i][target];
    }


    public long numberOfWays7(int n, int x) {
        return dfs(n, n, x);
    }

    private int dfs(int i, int target, int x) {
        if (target == 0) return 1;
        if (i < 0 || target < 0) return 0;
        long val = pow(i, x);
        return dfs(i - 1, (int) (target - val), x) + dfs(i - 1 , target, x);
    }

    private long pow(int x, int n) {
        if (n == 0 || x == 1x   ) return 1;
        long val = pow(x, n >>> 1);
        return (n & 1) == 0 ? val * val : val * val * x;
    }






    /**
     * https://leetcode.cn/problems/ways-to-express-an-integer-as-sum-of-powers/solutions/2354970/0-1-bei-bao-mo-ban-ti-by-endlesscheng-ap09/
     */
    int numberOfWays6(int n, int x) {
        long[] f = new long[n + 1];
        f[0] = 1;
        int value = 1;
        int sum = 0;
        for (int i = 1; value <= n; i++, value = (int) Math.pow(i, x)) {
            sum += value;
            for (int s = Math.min(sum, n); s >= value; s--) {
                f[s] += f[s - value];
            }
        }
        return (int) (f[n] % 1_000_000_007);
    }

    /**
     * 去除一遍循环，优化不大（根本没有优化）
     */
    public int numberOfWays5(int n, int x) {
        long[] f = new long[n + 1];
        f[0] = 1;
        int tmp1, j, len = maxI(n, x);
        for (int i = 1; i < len; i++) {
            tmp1 = (int) Math.pow(i, x);
            for (j = n; j >= tmp1; j--) {
                f[j] += f[j - tmp1];
            }
        }
        f[n] += f[n - (int) Math.pow(len, x)];
        return (int) (f[n] % 1000000007);
    }

    public int numberOfWays4(int n, int x) {
        long[] f = new long[n + 1];
        f[0] = 1;
        int tmp1, j, len = maxI(n, x);
        for (int i = 1; i <= len; i++) {
            tmp1 = (int) Math.pow(i, x);
            // j 每次都从 n 开始, tmp1 = i^x, i从1开始，
            // 第一轮最终影响的范围只有 j = 1^x = 1
            // 第二轮最终收到影响的范围 j <= 2^x + 1
            // 1: 0, 1^x
            // 2: 0, 1, 2^x, 2^x+1 (2^x+1^x)
            // 3: 0, 1, 2^x, 2^x+1, 3^x, 3^x+1, 3^x+2^x, 3^x+2^x+1 (3^x+2^x+1^x)
            for (j = n; j >= tmp1; j--) {
                f[j] += f[j - tmp1];
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
