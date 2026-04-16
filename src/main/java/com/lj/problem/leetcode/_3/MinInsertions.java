package com.lj.problem.leetcode._3;

import org.junit.Test;

import java.util.Arrays;

/**
 * 1312. 让字符串成为回文串的最少插入次数
 * 提示
 *
 * 给你一个字符串 s ，每一次操作你都可以在字符串的任意位置插入任意字符。
 * 请你返回让 s 成为回文串的 最少操作次数 。
 * 「回文串」是正读和反读都相同的字符串。
 *
 * 示例 1：
 * 输入：s = "zzazz"
 * 输出：0
 * 解释：字符串 "zzazz" 已经是回文串了，所以不需要做任何插入操作。

 * 示例 2：
 * 输入：s = "mbadm"
 * 输出：2
 * 解释：字符串可变为 "mbdadbm" 或者 "mdbabdm" 。

 * 示例 3：
 * 输入：s = "leetcode"
 * 输出：5
 * 解释：插入 5 个字符后字符串变为 "leetcodocteel" 。
 *
 * 提示：
 *
 * 1 <= s.length <= 500
 * s 中所有字符都是小写字母。
 */
public class MinInsertions {

    @Test
    public void test() {
        System.out.println(minInsertions("leetcode"));
    }

    public int minInsertions(String s) {
        char[] chars = s.toCharArray();
        if ((chars.length & 1) == 0) {
            return dfs(chars.length / 2 - 1, chars.length / 2, chars);
        }else {
            return dfs(chars.length / 2, chars.length / 2, chars);
        }
    }

    private int dfs(int i, int j, char[] chars) {

        if (i < 0) {
            return chars.length - j;
        }

        if (j >= chars.length) {
            return i + 1;
        }

        if (chars[i] == chars[j]) {
            return dfs(i - 1, j + 1, chars);
        }
        return Math.min(dfs(i - 1, j, chars), dfs(i, j + 1, chars)) + 1;
    }


    public int minInsertions2_(String s) {
        char[] chars = s.toCharArray();
        int[][] cache = new int[chars.length][chars.length];
        for (int[] c: cache) Arrays.fill(c, -1);
        return dfs(0, chars.length - 1, chars, cache);
    }

    private int dfs(int i, int j, char[] chars, int[][] cache) {
        if (i >= j) {
            return 0;
        }

        if (cache[i][j] != -1) return cache[i][j];

        if (chars[i] == chars[j]) {
            return cache[i][j] = dfs(i + 1, j - 1, chars, cache);
        }
        return cache[i][j] = Math.min(dfs(i + 1, j, chars, cache), dfs(i, j - 1, chars, cache)) + 1;
    }


    public int minInsertions2(String s) {
        char[] chars = s.toCharArray();
        return dfs2(0, chars.length - 1, chars);
    }
    private int dfs2(int i, int j, char[] chars) {

        if (i >= j) {
            return 0;
        }

        if (chars[i] == chars[j]) {
            return dfs2(i + 1, j - 1, chars);
        }
        return Math.min(dfs2(i + 1, j, chars), dfs2(i, j - 1, chars)) + 1;
    }
}
