package com.lj.problem.leetcode._2;

/**
 * 583. 两个字符串的删除操作
 * 给定两个单词 word1 和 word2 ，返回使得 word1 和  word2 相同所需的最小步数。
 * 每步 可以删除任意一个字符串中的一个字符。
 *
 * 示例 1：
 * 输入: word1 = "sea", word2 = "eat"
 * 输出: 2
 * 解释: 第一步将 "sea" 变为 "ea" ，第二步将 "eat "变为 "ea"
 *
 * 示例  2:
 * 输入：word1 = "leetcode", word2 = "etco"
 * 输出：4
 *
 * 提示：
 * 1 <= word1.length, word2.length <= 500
 * word1 和 word2 只包含小写英文字母
 */
public class MinDistance2 {

    public int minDistance(String word1, String word2) {
        /**
         * 定义行为：
         * dfs(i-1, j-1) 相等，同时删除
         * dfs(i-1, j) 删除 word1
         * dfs(i, j-1) 删除 word2
         */
        int[] dp = new int[word2.length() + 1];
        char[] chars = word2.toCharArray();
        for (int i = 1; i < dp.length; i++) dp[i] = i;
        int row = 1;
        for (char c: word1.toCharArray()) {
            dp[0] = row++;
            int pre = dp[0]- 1;
            for (int j = 1; j < dp.length; j++) {
                int tmp = dp[j];
                if (c == chars[j - 1]) {
                    dp[j] = pre;
                }else {
                    dp[j] = Math.min(dp[j], dp[j-1]) + 1;
                }
                pre = tmp;
            }
        }

        return dp[word2.length()];
    }

}
