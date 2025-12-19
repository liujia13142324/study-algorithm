package com.lj.problem.leetcode._2;

import java.util.ArrayList;
import java.util.List;

/**
 * 97. 交错字符串
 * 给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。
 * 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：
 *
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
 * 注意：a + b 意味着字符串 a 和 b 连接。
 *
 *
 * 示例 1：
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出：true
 *
 * 示例 2：
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出：false
 *
 * 示例 3：
 * 输入：s1 = "", s2 = "", s3 = ""
 * 输出：true
 *
 *
 * 提示：
 * 0 <= s1.length, s2.length <= 100
 * 0 <= s3.length <= 200
 * s1、s2、和 s3 都由小写英文字母组成
 *
 * 进阶：您能否仅使用 O(s2.length) 额外的内存空间来解决它?
 */
public class IsInterleave {


    /**
     * 递归最快，有剪枝，跳过一些不必要的计算步骤
     */
    public boolean isInterleave2(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        char[] chars = s3.toCharArray();
        Boolean[][] cache = new Boolean[s1.length() + 1][s2.length() + 1];
        return dfs2(s1.length(), s2.length(), s1.toCharArray(), s2.toCharArray(), chars, cache);
    }

    private boolean dfs2(int i, int j, char[] s1, char[] s2, char[] s3, Boolean[][] cache) {
        if (i == 0 && j == 0) {
            return true;
        }
        if (cache[i][j] != null) {
            return cache[i][j];
        }
        char c = s3[i + j - 1];
        cache[i][j] = (i > 0 && s1[i - 1] == c && dfs2(i - 1, j, s1, s2, s3, cache)) ||
                      (j > 0 && s2[j - 1] == c && dfs2(i, j - 1, s1, s2, s3, cache));

        return cache[i][j];
    }

    /**
     * 递推2，一纬数组
     */
    public boolean isInterleave2__(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        char[] ss1 = s1.toCharArray();
        char[] ss2 = s2.toCharArray();
        char[] ss3 = s3.toCharArray();
        boolean[] dp = new boolean[ss2.length + 1];
        dp[0] = true;
        for (int i = 1; i <= ss2.length; i++) {
            dp[i] = ss2[i - 1] == ss3[i - 1] && dp[i - 1];
        }

        for (int i = 1; i <= ss1.length; i++) {
            dp[0] = ss1[i - 1] == ss3[i - 1] && dp[0];
            for (int j = 1; j <= ss2.length; j++) {
                char c = ss3[i + j - 1];
                dp[j] = (ss1[i - 1] == c && dp[j]) || (ss2[j - 1] == c && dp[j - 1]);
            }
        }

        return dp[ss2.length];
    }

    /**
     *  递推1，没有剪枝，二维数组空间
     */
    public boolean isInterleave2_(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        char[] ss1 = s1.toCharArray();
        char[] ss2 = s2.toCharArray();
        char[] ss3 = s3.toCharArray();
        boolean[][] dp = new boolean[ss1.length + 1][ss2.length + 1];
        dp[0][0] = true;
        for (int i = 1; i <= ss2.length; i++) {
            dp[0][i] = ss2[i - 1] == ss3[i - 1] && dp[0][i - 1];
        }

        for (int i = 1; i <= ss1.length; i++) {
            dp[i][0] = ss1[i - 1] == ss3[i - 1] && dp[i - 1][0];
            for (int j = 1; j <= ss2.length; j++) {
                char c = ss3[i + j - 1];
                dp[i][j] = (ss1[i - 1] == c && dp[i - 1][j]) || (ss2[j - 1] == c && dp[i][j - 1]);
            }
        }

        return dp[ss1.length][ss2.length];
    }


    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;

        char[] chars = s3.toCharArray();
        List<Character> path = new ArrayList<>(chars.length);
        Boolean[][] cache = new Boolean[s1.length() + 1][s2.length() + 1];
        return dfs(s1.length(), s2.length(), s1.toCharArray(), s2.toCharArray(), chars, path, cache);
    }

    private boolean dfs(int i, int j, char[] s1, char[] s2, char[] s3, List<Character> path, Boolean[][] cache) {
        if (i == 0 && j == 0) {
            return path.size() == s3.length;
        }
        if (cache[i][j] != null) {
            return cache[i][j];
        }
        int idx = s3.length - 1 - path.size();
        if (i > 0 && s1[i - 1] == s3[idx]) {
            path.add(s1[i - 1]);
            if (dfs(i - 1, j, s1, s2, s3, path, cache)) {
                cache[i][j] = true;
                return true;
            }
            path.remove(path.size() - 1);
        }

        if (j > 0 && s2[j - 1] == s3[idx]) {
            path.add(s2[j - 1]);
            if (dfs(i, j - 1, s1, s2, s3, path, cache)) {
                cache[i][j] = true;
                return true;
            }
            path.remove(path.size() - 1);
        }

        cache[i][j] = false;
        return false;
    }
}
