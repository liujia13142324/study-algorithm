package com.lj.problem.leetcode._2;

import cn.hutool.core.lang.hash.Hash;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 1297. 子串的最大出现次数
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个字符串 s ，请你返回满足以下条件且出现次数最大的 任意 子串的出现次数：
 *
 * 子串中不同字母的数目必须小于等于 maxLetters 。
 * 子串的长度必须大于等于 minSize 且小于等于 maxSize 。
 *
 *
 * 示例 1：
 *
 * 输入：s = "aababcaab", maxLetters = 2, minSize = 3, maxSize = 4
 * 输出：2
 * 解释：子串 "aab" 在原字符串中出现了 2 次。
 * 它满足所有的要求：2 个不同的字母，长度为 3 （在 minSize 和 maxSize 范围内）。
 * 示例 2：
 *
 * 输入：s = "aaaa", maxLetters = 1, minSize = 3, maxSize = 3
 * 输出：2
 * 解释：子串 "aaa" 在原字符串中出现了 2 次，且它们有重叠部分。
 * 示例 3：
 *
 * 输入：s = "aabcabcab", maxLetters = 2, minSize = 2, maxSize = 3
 * 输出：3
 * 示例 4：
 *
 * 输入：s = "abcde", maxLetters = 2, minSize = 3, maxSize = 3
 * 输出：0
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 10^5
 * 1 <= maxLetters <= 26
 * 1 <= minSize <= maxSize <= min(26, s.length)
 * s 只包含小写英文字母。
 */
public class MaxFreq {

    /**
     * 只用考虑 minSize,
     * 子串越短，包含的不同字母个数越少，越能满足 ≤maxLetters 的要求。
     * 子串越短，子串在 s 中的出现次数也越多。
     * 结合上面两个性质，我们只需考虑长度恰好等于 minSize 的子串（maxSize 是多余的）。
     *
     */
    public int maxFreq2(String s, int maxLetters, int minSize, int maxSize) {
        int letterCnt = 0;
        int[] letterCounter = new int[26];
        Map<String, Integer> ansMap = new HashMap<>();
        int ans = 0;
        char[] chars = s.toCharArray();
        int l = 0;
        for (int r = 0; r < chars.length; r++) {
            letterCounter[chars[r] - 'a']++;
            if (letterCounter[chars[r] - 'a'] == 1) letterCnt++;
            if (r < minSize - 1) continue;
            if (letterCnt <= maxLetters) {
                ans = Math.max(ans, ansMap.merge(s.substring(l, r + 1), 1, Integer::sum));
            }
            letterCounter[chars[l] - 'a']--;
            if (letterCounter[chars[l] - 'a'] == 0) letterCnt--;
            l++;
        }
        return ans;
    }


    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        int letterCnt = 0;
        int[] letterCounter = new int[26];
        Map<String, Integer> ansMap = new HashMap<>();
        int ans = 0;
        char[] chars = s.toCharArray();
        int l = 0;
        for (int r = 0; r < chars.length; r++) {
            letterCounter[chars[r] - 'a']++;
            if (letterCounter[chars[r] - 'a'] == 1) letterCnt++;

            if (r - l + 1 < minSize) {
                continue;
            }

            // letters 统计 || 长度统计
            while (letterCnt > maxLetters || r - l + 1 > maxSize) {
                if (letterCounter[chars[l] - 'a'] == 1) {
                    letterCnt--;
                }
                letterCounter[chars[l] - 'a']--;
                l++;
            }

            for (int j = l; j <= r - minSize + 1; j++) {
                String subString = s.substring(j, r + 1);
                ans = Math.max(ans, ansMap.merge(subString, 1, Integer::sum));
            }
        }

        return ans;
    }


    @Test
    public void test() {
        System.out.println(maxFreq2("aabcabcab", 2, 2, 3));;
    }

}
