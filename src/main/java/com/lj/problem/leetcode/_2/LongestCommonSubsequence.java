package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.Arrays;

/**
 * 1143. 最长公共子序列
 * 提示
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 *
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 *
 * 示例 1：
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
 *
 * 示例 2：
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc" ，它的长度为 3 。
 *
 * 示例 3：
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0 。
 *
 * 提示：
 * 1 <= text1.length, text2.length <= 1000
 * text1 和 text2 仅由小写英文字符组成。
 */
public class LongestCommonSubsequence {

    @Test
    public void test() {
        System.out.println(longestCommonSubsequence("abcba", "abcbcba"));
    }

    public int longestCommonSubsequence4__(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();
        for (int i = 1; i <= chars1.length; i++) {
            for (int j = 1; j <= chars2.length; j++) {
                if (chars1[i - 1] == chars2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[chars1.length][chars2.length];
    }

    public int longestCommonSubsequence4_(String text1, String text2) {
        int[][] cache = new int[text1.length()][text2.length()];
        return dfs4_(text2.length() - 1, text1.length() - 1, text2.toCharArray(), text1.toCharArray(), cache);
    }

    private int dfs4_(int i, int j, char[] text1, char[] text2, int[][] cache) {
        if (i < 0 || j < 0) {
            return 0;
        }

        if (cache[i][j] != -1) {
            return cache[i][j];
        }
        int ans;
        if (text1[i] == text2[j]) {
            ans = 1 + dfs4_(i - 1, j - 1, text1, text2, cache);
        }else {
            ans = Math.max(dfs4_(i - 1, j, text1, text2, cache), dfs4_(i, j - 1, text1, text2, cache));
        }

        cache[i][j] = ans;
        return ans;
    }

    public int longestCommonSubsequence4(String text1, String text2) {
        return dfs4(text1.length() - 1, text2.length() - 1, text1.toCharArray(), text2.toCharArray());
    }

    private int dfs4(int i, int j, char[] text1, char[] text2) {
        if (i < 0 || j < 0) {
            return 0;
        }

        if (text1[i] == text2[j]) {
            return 1 + dfs4(i - 1, j - 1, text1, text2);
        }

        return Math.max(dfs4(i - 1, j, text1, text2), dfs4(i, j - 1, text1, text2));
    }


    public int longestCommonSubsequence3(String text1, String text2) {
        if (text1.length() < text2.length()) {
            return dfs3(text1.length() - 1, text2.length() - 1, text1.toCharArray(), text2.toCharArray(), newArrayFilled(text1.length(), text2.length()));
        }
        return dfs3(text2.length() - 1, text1.length() - 1, text2.toCharArray(), text1.toCharArray(), newArrayFilled(text2.length(), text1.length()));
    }

    private int dfs3(int i1, int i2, char[] text1, char[] text2, int[][] cache) {
        if (i1 < 0 || i2 < 0) {
            return 0;
        }

        if (cache[i1][i2] != -1) {
            return cache[i1][i2];
        }

        int ans = 0;
        for (int i = i2; i >= ans; i--) {
            if (text1[i1] == text2[i]) {
                ans = Math.max(ans, 1 + dfs3(i1 - 1, i - 1, text1, text2, cache));
            }
            if (ans > i1) {
                return ans;
            }
        }

        cache[i1][i2] = Math.max(ans, dfs3(i1 - 1, i2, text1, text2, cache));

        return cache[i1][i2];
    }

    public int longestCommonSubsequence(String text1, String text2) {
        if (text1.length() < text2.length()) {
            return dfs2(0, 0, text1.toCharArray(), text2.toCharArray(), newArrayFilled(text1.length(), text2.length()));
        }
        return dfs2(0, 0, text2.toCharArray(), text1.toCharArray(), newArrayFilled(text2.length(), text1.length()));
    }

    private int dfs2(int i1, int i2, char[] text1, char[] text2, int[][] cache) {
        if (i1 == text1.length || i2 == text2.length) {
            return 0;
        }

        if (cache[i1][i2] != -1) {
            return cache[i1][i2];
        }

        int ans = 0;
        for (int i = i2; i < text2.length - ans; i++) {
            if (text1[i1] == text2[i]) {
                ans = Math.max(ans, 1 + dfs2(i1 + 1, i + 1, text1, text2, cache));
            }
            if (i1 + ans >= text1.length) {
                return ans;
            }
        }

        cache[i1][i2] = Math.max(ans, dfs2(i1 + 1, i2, text1, text2, cache));

        return cache[i1][i2];
    }

    private int dfs(int i1, int i2, char[] text1, char[] text2, int[][] cache) {
        if (i1 == text1.length || i2 == text2.length) {
            return 0;
        }

        if (cache[i1][i2] != -1) {
            return cache[i1][i2];
        }

        int ans = 0;
        for (int i = i2; i < text2.length - ans; i++) {
            if (text1[i1] == text2[i]) {
                ans = Math.max(ans, 1 + dfs(i1 + 1, i + 1, text1, text2, cache));
            }
            if (ans == text1.length) {
                return ans;
            }
        }

        cache[i1][i2] = Math.max(ans, dfs(i1 + 1, i2, text1, text2, cache));

        return cache[i1][i2];
    }

    private int dfs(int i1, int i2, char[] text1, char[] text2) {
        if (i1 == text1.length) {
            return 0;
        }

        int ans = 0;
        for (int i = i2; i < text2.length - ans; i++) {
            if (text1[i1] == text2[i]) {
                ans = Math.max(ans, 1 + dfs(i1 + 1, i + 1, text1, text2));
            }
            if (ans == text1.length) {
                return ans;
            }
        }

        return Math.max(ans, dfs(i1 + 1, i2, text1, text2));
    }

    private int[][] newArrayFilled(int i, int j) {
        int[][] ans = new int[i][j];
        for (int[] tmp: ans) Arrays.fill(tmp, -1);
        return ans;
    }
}
