package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.Arrays;

/**
 * 72. 编辑距离
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
 * 你可以对一个单词进行如下三种操作：
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *
 * 示例 1：
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')

 * 示例 2：
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 *
 *
 * 提示：
 * 0 <= word1.length, word2.length <= 500
 * word1 和 word2 由小写英文字母组成
 */
public class MinDistance {

    @Test
    public void test() {
        System.out.println(minDistance("horse", "ros"));
        System.out.println(minDistance("intention", "execution"));

        System.out.println(minDistance_("horse", "ros"));
        System.out.println(minDistance_("intention", "execution"));
        System.out.println(minDistance_("", ""));
    }

    public int minDistance_(String word1, String word2) {
        /**
         * s = horse
         * t = ros
         * 删除：dfs(s-1, t) --> 从 s 中删除某个字符
         * 插入：dfs(s, t-1) --> 在 s 中插入某个 t 中的字符，从而可以消除 t 中的某个字符
         * 替换：1 + dfs(s-1,t-1) --> 替换成 t 中某个字符，然后分别消除
         */
        char[] chars = word2.toCharArray();
        int[] dp = new int[chars.length + 1];
        for (int i = 1; i < dp.length; i++) dp[i] = i;
        int row = 1;
        for (char c: word1.toCharArray()) {
            // j < 0 -> i+1, i从0开始 -> row, 从1开始
            dp[0] = row++;
            // dp[0] 上面就是 pre
            int pre = dp[0] - 1;
            for (int j = 1; j <= chars.length; j++) {
                int tmp = dp[j];
                if (c == chars[j - 1]) {
                    // 相等，直接消除, 左上
                    dp[j] = pre;
                } else {
                    // 不相等，从下面操作选一个最小值(上，左，左上)
                    dp[j] = Math.min(Math.min(dp[j], dp[j-1]), pre) + 1;
                }
                pre = tmp;
            }
        }
        return dp[chars.length];
    }


    public int minDistance(String word1, String word2) {
        /**
         * s = horse
         * t = ros
         * 删除：dfs(s-1, t) --> 从 s 中删除某个字符
         * 插入：dfs(s, t-1) --> 在 s 中插入某个 t 中的字符，从而可以消除 t 中的某个字符
         * 替换：1 + dfs(s-1,t-1) --> 替换成 t 中某个字符，然后分别消除
         */
        int[][] cache = new int[word1.length()][word2.length()];
        for (int[] tmp: cache) Arrays.fill(tmp, -1);
        return dfs(word1.length()-1, word2.length()-1, word1.toCharArray(), word2.toCharArray(), cache);
    }

    private int dfs(int i, int j, char[] word1, char[] word2, int[][] cache) {
        if (i < 0) return j + 1;
        if (j < 0) return i + 1;
        if (cache[i][j] != -1) return cache[i][j];
        if (word1[i] == word2[j]) {
            cache[i][j] = dfs(i - 1, j - 1, word1, word2, cache);
        }else {
            cache[i][j] = 1 + Math.min(
                Math.min(dfs(i - 1, j, word1, word2, cache), dfs(i, j - 1, word1, word2, cache))
                , dfs(i - 1, j - 1, word1, word2, cache)
            );
        }
        return cache[i][j];
    }


    private String lcs(String word1, String word2) {
        String[] dp = new String[word2.length() + 1];
        Arrays.fill(dp, "");

        for (char c : word1.toCharArray()) {
            String pre = "";
            for (int j = 1, len = word2.length(); j <= len; j++) {
                String tmp = dp[j];
                if (word2.charAt(j-1) == c) {
                    // 左上
                    dp[j] = pre + c;
                }else if (dp[j - 1].length() > dp[j].length()){
                    // 上一个或左一个
                    dp[j] = dp[j-1];
                }
                pre = tmp;
            }
        }

        return dp[word2.length()];
    }

    @Test
    public void testLcs() {
        System.out.println(lcs("horse", "ros"));
        System.out.println(lcs("intention", "execution"));
    }

}
