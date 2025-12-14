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

    public int longestCommonSubsequence(String text1, String text2) {
        if (text1.length() < text2.length()) {
            return dfs2(0, 0, text1.toCharArray(), text2.toCharArray(), newArrayFilled(text1.length(), text2.length()));
        }
        return dfs2(0, 0, text2.toCharArray(), text1.toCharArray(), newArrayFilled(text2.length(), text1.length()));
    }

    private int[][] newArrayFilled(int i, int j) {
        int[][] ans = new int[i][j];
        for (int[] tmp: ans) Arrays.fill(tmp, -1);
        return ans;
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
}
