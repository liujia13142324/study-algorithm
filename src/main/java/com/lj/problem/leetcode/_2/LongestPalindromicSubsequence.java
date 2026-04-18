package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.Arrays;

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
        System.out.println(longestPalindromicSubsequence_("vgjtj", 14));
    }

    public int longestPalindromicSubsequence_(String s, int k) {
        char[] chars = s.toCharArray();
        // 优化，如果能在 k 次内变成回文串，就直接返回
        int cnt = 0;
        for (int i = 0; i < chars.length / 2; i++) {
            int d = Math.abs(chars[i] - chars[chars.length - 1 - i]);
            cnt += Math.min(d, 26 - d);
        }
        if (cnt <= k) {
            return chars.length;
        }

        int[][] dp = new int[k + 1][s.length()];
        for(int[] tmp: dp) {
            Arrays.fill(tmp, 1);
        }
        for (int i = chars.length - 2; i >= 0; i--) {
            int[] prev = new int[k + 1];
            for (int j = i + 1; j < chars.length; j++) {
                int[] tmp = new int[k + 1];
                for (int k_ = 0; k_ <= k; k_++) {
                    tmp[k_] = dp[k_][j];
                    if (chars[i] == chars[j]) {
                        dp[k_][j] = prev[k_] + 2;
                    }else {
                        int x;
                        int abs = Math.abs(chars[i] - chars[j]);
                        if (k_ >= (x = Math.min(abs, 26 - abs))) {
                            dp[k_][j] = Math.max(dp[k_][j], prev[k_ - x] + 2);
                        }
                        if (dp[k_][j-1] > dp[k_][j] ){
                            dp[k_][j] = dp[k_][j-1];
                        }
                    }
                }
                System.arraycopy(tmp, 0, prev, 0, k + 1);
            }
        }
        return dp[k][s.length()-1];
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
