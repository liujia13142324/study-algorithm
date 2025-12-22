package com.lj.problem.leetcode._3;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;


/**
 * 1092. 最短公共超序列
 * 提示
 * 给你两个字符串 str1 和 str2，返回同时以 str1 和 str2 作为 子序列 的最短字符串。如果答案不止一个，则可以返回满足条件的 任意一个 答案。
 * 如果从字符串 t 中删除一些字符（也可能不删除），可以得到字符串 s ，那么 s 就是 t 的一个子序列。
 *
 * 示例 1：
 * 输入：str1 = "abac", str2 = "cab"
 * 输出："cabac"
 * 解释：
 * str1 = "abac" 是 "cabac" 的一个子串，因为我们可以删去 "cabac" 的第一个 "c"得到 "abac"。
 * str2 = "cab" 是 "cabac" 的一个子串，因为我们可以删去 "cabac" 末尾的 "ac" 得到 "cab"。
 * 最终我们给出的答案是满足上述属性的最短字符串。
 *
 * 示例 2：
 * 输入：str1 = "aaaaaaaa", str2 = "aaaaaaaa"
 * 输出："aaaaaaaa"
 *
 * 提示：
 *
 * 1 <= str1.length, str2.length <= 1000
 * str1 和 str2 都由小写英文字母组成。
 *
 */
public class ShortestCommonSupersequence {

    @Test
    public void test() {
        System.out.println(shortestCommonSupersequence___("abac", "cab"));
        System.out.println(shortestCommonSupersequence___("aaaaaaaa", "aaaaaaaa"));
    }

    public String shortestCommonSupersequence___(String str1, String str2) {
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 1; i <= chars2.length; i++) dp[0][i] = i;
        for (int i = 1; i <= chars1.length; i++) dp[i][0] = i;

        for (int i = 1; i <= chars1.length; i++) {
            for (int j = 1; j <= chars2.length; j++) {
                if (chars1[i - 1] == chars2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else{
                    dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + 1;
                }
            }
        }

        char[] ans = new char[dp[chars1.length][chars2.length]];
        int idx = ans.length - 1;
        int i = chars1.length - 1;
        int j = chars2.length - 1;

        while (i >= 0 || j >= 0) {
            if (i < 0) {
                System.arraycopy(chars2, 0, ans, 0, idx + 1);
                break;
            }
            if (j < 0) {
                System.arraycopy(chars1, 0, ans, 0, idx + 1);
                break;
            }
            if (chars1[i] == chars2[j]) {
                ans[idx --] = chars1[i];
                i--; j--;
            }else if (dp[i][j + 1] < dp[i + 1][j]) {
                ans[idx--] = chars1[i--];
            }else {
                ans[idx--] = chars2[j--];
            }
        }
        return new String(ans);
    }

    public String shortestCommonSupersequence__(String str1, String str2) {
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 1; i <= chars2.length; i++) dp[0][i] = i;
        for (int i = 1; i <= chars1.length; i++) dp[i][0] = i;

        for (int i = 1; i <= chars1.length; i++) {
            for (int j = 1; j <= chars2.length; j++) {
                if (chars1[i - 1] == chars2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else{
                    dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + 1;
                }
            }
        }

        char[] ans = new char[dp[chars1.length][chars2.length]];
        int idx = ans.length - 1;
        int i = chars1.length;
        int j = chars2.length;

        while (i > 0 || j > 0) {
            if (i == 0) {
                ans[idx--] = chars2[j-1];
                j--;
                continue;
            }
            if (j == 0) {
                ans[idx--] = chars1[i-1];
                i--;
                continue;
            }
            if (chars1[i - 1] == chars2[j - 1]) {
                ans[idx --] = chars1[i - 1];
                i--; j--;
            }else if (dp[i - 1][j] < dp[i][j - 1]) {
                ans[idx--] = chars1[i-1];
                i--;
            }else {
                ans[idx--] = chars2[j-1];
                j--;
            }
        }
        return new String(ans);
    }


    public String shortestCommonSupersequence_(String str1, String str2) {
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        String[] dp = new String[str2.length() + 1];
        for (int i = 0; i < dp.length; i++) dp[i] = str2.substring(0, i);
        for (int i = 1; i <= chars1.length; i++) {
            String pre = dp[0];
            dp[0] = str1.substring(0, i);
            for (int j = 1; j <= chars2.length; j++) {
                String tmp = dp[j];
                if (chars1[i - 1] == chars2[j - 1]) {
                    dp[j] = pre + chars1[i - 1];
                }else if (dp[j - 1].length() < dp[j].length()){
                    dp[j] = dp[j - 1] + chars2[j - 1];
                }else {
                    dp[j] += chars1[i - 1];
                }
                pre = tmp;
            }
        }
        return dp[chars2.length];
    }

    public String shortestCommonSupersequence(String str1, String str2) {
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        String[][] cache = new String[str1.length()][str2.length()];
        return dfs(chars1.length - 1, chars2.length - 1, chars1, chars2, cache);
    }

    private String dfs(int i, int j, char[] chars1, char[] chars2, String[][] cache) {
        if (i < 0) return new String(Arrays.copyOfRange(chars2, 0, j + 1));
        if (j < 0) return new String(Arrays.copyOfRange(chars1, 0, i + 1));
        if (cache[i][j] != null) return cache[i][j];
        if (chars1[i] == chars2[j]) {
            cache[i][j] = dfs(i - 1, j - 1, chars1, chars2, cache) + chars1[i];
        }else {
            String s1 = dfs(i - 1, j, chars1, chars2, cache) + chars1[i];
            String s2 = dfs(i, j - 1, chars1, chars2, cache) + chars2[j];
            cache[i][j] = s1.length() <= s2.length() ? s1 : s2;
        }
        return cache[i][j];
    }
}
