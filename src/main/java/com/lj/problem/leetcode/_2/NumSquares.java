package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.Arrays;

/**
 * 279. 完全平方数
 * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
 *
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 *
 * 示例 1：
 * 输入：n = 12
 * 输出：3
 * 解释：12 = 4 + 4 + 4
 *
 * 示例 2：
 * 输入：n = 13
 * 输出：2
 * 解释：13 = 4 + 9
 *
 * 提示：
 * 1 <= n <= 104
 */
public class NumSquares {

    @Test
    public void test() {
        System.out.println(numSquares2(12));
        System.out.println(numSquares2(13));

        System.out.println(numSquares3_1(12));
        System.out.println(numSquares3_1(13));
    }


    private static final int N = 10000;
    private static final int[] f = new int[N + 1];
    private static boolean initialized = false;

    // 这样写比 static block 更快
    private void init() {
        if (initialized) {
            return;
        }
        initialized = true;

        Arrays.fill(f, Integer.MAX_VALUE);
        f[0] = 0;
        for (int i = 1; i * i <= N; i++) {
            for (int j = i * i; j <= N; j++) {
                f[j] = Math.min(f[j], f[j - i * i] + 1); // 不选 vs 选
            }
        }
    }

    // 全量预加载优化，只算一次题目给出的N的全部范围，后续的用力直接返回结果就好
    public int numSquares4(int n) {
        init();
        return f[n];
    }


    public int numSquares3_1(int n) {
        numSquares3_2();
        return f[n];
    }

    public void numSquares3_2() {
        if (initialized) return;
        initialized = true;
        Arrays.fill(f, N);
        f[0] = 0;
        int square;
        for (int i = 1; i*i <= N; i++) {
            square = i * i;
            for (int j = square; j <= N; j++) {
                f[j] = Math.min(f[j], 1 + f[j - square]);
            }
        }
    }


    public int numSquares3(int n) {
        int maxI = (int) Math.pow(n, 0.5);
        int[] f = new int[n + 1];
        Arrays.fill(f, n);
        f[0] = 0;
        int square;
        for (int i = 1; i <= maxI; i++) {
            square = i * i;
            for (int j = square; j <= n; j++) {
                f[j] = Math.min(f[j], 1 + f[j - square]);
            }
        }
        return f[n];
    }

    public int numSquares2(int n) {
        int maxI = (int) Math.pow(n, 0.5);
        return dfs(n, maxI);
    }


    private int dfs(int n, int i) {
        if (n == 0) return 0;

        if (i <= 0 || n < 0) return n;

        int square = i * i;

        if (n < square) {
            return dfs(n , i-1);
        }

        return Math.min(dfs(n , i-1), 1 + dfs(n - square, i));
    }






    public int numSquares(int n) {
        int[] f = new int[n+1];
        
        for (int i = 1; i <= n; i++) {
            int min = i;
            for (int j = 1; j * j <= i; j++) {
                min = Math.min(min, f[i - j * j]);
            }
            f[i] = min + 1;
        }
        
        return f[n];

    }
    
    
    public static void main(String[] args) {
        System.out.println(new NumSquares().numSquares(61));
    }

}
