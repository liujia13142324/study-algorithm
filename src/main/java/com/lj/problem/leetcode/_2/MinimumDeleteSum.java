package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.Arrays;

/**
 * 712. 两个字符串的最小ASCII删除和
 * 给定两个字符串s1 和 s2，返回 使两个字符串相等所需删除字符的 ASCII 值的最小和 。
 *
 * 示例 1:
 * 输入: s1 = "sea", s2 = "eat"
 * 输出: 231
 * 解释: 在 "sea" 中删除 "s" 并将 "s" 的值(115)加入总和。
 * 在 "eat" 中删除 "t" 并将 116 加入总和。
 * 结束时，两个字符串相等，115 + 116 = 231 就是符合条件的最小和。
 *
 * 示例 2:
 * 输入: s1 = "delete", s2 = "leet"
 * 输出: 403
 * 解释: 在 "delete" 中删除 "dee" 字符串变成 "let"，
 * 将 100[d]+101[e]+101[e] 加入总和。在 "leet" 中删除 "e" 将 101[e] 加入总和。
 * 结束时，两个字符串都等于 "let"，结果即为 100+101+101+101 = 403 。
 * 如果改为将两个字符串转换为 "lee" 或 "eet"，我们会得到 433 或 417 的结果，比答案更大。
 *
 * 提示:
 *
 * 0 <= s1.length, s2.length <= 1000
 * s1 和 s2 由小写英文字母组成
 */
public class MinimumDeleteSum {

    @Test
    public void test() {
        System.out.println(minimumDeleteSum("delete", "leet"));
    }

    public int minimumDeleteSum(String s1, String s2) {
        if (s1.equals(s2)) return 0;
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        int[] sum1 = new int[s1.length() + 1];
        int[] sum2 = new int[s2.length() + 1];
        for (int i = 1; i < sum1.length; i++) sum1[i] = sum1[i-1] + chars1[i-1];
        for (int i = 1; i < sum2.length; i++) sum2[i] = sum2[i-1] + chars2[i-1];
        int[][] cache = new int[s1.length()][s2.length()];
        for (int[] tmp: cache) Arrays.fill(tmp, -1);
        return dfs(chars1.length - 1, chars2.length - 1, chars1, chars2, sum1, sum2, cache);
    }

    private int dfs(int i, int j, char[] s1, char[] s2, int[] sum1, int[] sum2, int[][] cache) {

        if (i < 0) return sum2[j+1];
        if (j < 0) return sum1[i+1];

        if (cache[i][j] != -1) return cache[i][j];

        int ans;
        if (s1[i] == s2[j]) {
            ans = dfs(i - 1, j - 1, s1, s2, sum1, sum2, cache);
        } else {
            ans = Math.min(
                    dfs(i - 1, j, s1, s2, sum1, sum2, cache) + (int) s1[i],
                    dfs(i, j - 1, s1, s2, sum1, sum2, cache) + (int) s2[j]
            );
        }
        cache[i][j] = ans;
        return ans;
    }

    @Test
    public void testAscii() {
        System.out.println((int)'s');
        System.out.println((int)'t');
    }
}
