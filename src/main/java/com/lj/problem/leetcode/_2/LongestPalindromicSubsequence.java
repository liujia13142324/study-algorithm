package com.lj.problem.leetcode._2;

import org.junit.Test;

/**
 * 3472. 至多 K 次操作后的最长回文子序列
 * 给你一个字符串 s 和一个整数 k。
 * 在一次操作中，你可以将任意位置的字符替换为字母表中相邻的字符（字母表是循环的，因此 'z' 的下一个字母是 'a'）。例如，将 'a' 替换为下一个字母结果是 'b'，将 'a' 替换为上一个字母结果是 'z'；同样，将 'z' 替换为下一个字母结果是 'a'，替换为上一个字母结果是 'y'。
 * 返回在进行 最多 k 次操作后，s 的 最长回文子序列 的长度。
 *
 * 示例 1：
 * 输入: s = "abced", k = 2
 * 输出: 3
 * 解释:
 * 将 s[1] 替换为下一个字母，得到 "acced"。
 * 将 s[4] 替换为上一个字母，得到 "accec"。
 * 子序列 "ccc" 形成一个长度为 3 的回文，这是最长的回文子序列。
 *
 * 示例 2：
 * 输入: s = "aaazzz", k = 4
 * 输出: 6
 * 解释:
 * 将 s[0] 替换为上一个字母，得到 "zaazzz"。
 * 将 s[4] 替换为下一个字母，得到 "zaazaz"。
 * 将 s[3] 替换为下一个字母，得到 "zaaaaz"。
 * 整个字符串形成一个长度为 6 的回文。
 *
 * 提示:
 *
 * 1 <= s.length <= 200
 * 1 <= k <= 200
 * s 仅由小写英文字母组成。
 */
public class LongestPalindromicSubsequence {

    @Test
    public void test() {
        System.out.println((Math.abs(('b' - 'c')) + 26) % 26);
    }

    public int longestPalindromicSubsequence(String s, int k) {
        int[][][] cache = new int[s.length()][s.length()][k];
        return dfs(0, s.length() - 1, k, s.toCharArray(), cache);
    }

    private int dfs(int i, int j, int k, char[] charArray, int[][][] cache) {
        if (i == j) return 1;
        if (i > j) return 0;

        if (cache[i][j][k] != 0) {
            return cache[i][j][k];
        }

        if (charArray[i] == charArray[j]) {
            return cache[i][j][k] = dfs(i + 1, j - 1, k, charArray, cache) + 2;
        }

        int ans = 0;
        int x = Math.min(Math.abs(charArray[i] - charArray[j]), 26 - Math.abs(charArray[i] - charArray[j]));
        if (k >= x) {
            ans = dfs(i + 1, j - 1, k - x, charArray, cache) + 2;
        }

        return cache[i][j][k] = Math.max(ans, Math.max(dfs(i + 1, j, k, charArray, cache), dfs(i, j - 1, k, charArray, cache)));
    }

}
