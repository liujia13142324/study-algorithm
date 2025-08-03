package com.lj.problem.leetcode._2;

import org.junit.Test;

/**
 * 1456. 定长子串中元音的最大数目
 * 提示
 * 给你字符串 s 和整数 k 。
 * 请返回字符串 s 中长度为 k 的单个子字符串中可能包含的最大元音字母数。
 * 英文中的 元音字母 为（a, e, i, o, u）。
 *
 * 示例 1：
 * 输入：s = "abciiidef", k = 3
 * 输出：3
 * 解释：子字符串 "iii" 包含 3 个元音字母。
 *
 * 示例 2：
 * 输入：s = "aeiou", k = 2
 * 输出：2
 * 解释：任意长度为 2 的子字符串都包含 2 个元音字母。

 * 示例 3：
 * 输入：s = "leetcode", k = 3
 * 输出：2
 * 解释："lee"、"eet" 和 "ode" 都包含 2 个元音字母。

 * 示例 4：
 * 输入：s = "rhythms", k = 4
 * 输出：0
 * 解释：字符串 s 中不含任何元音字母。

 * 示例 5：
 * 输入：s = "tryhard", k = 4
 * 输出：1
 *
 * 提示：
 * 1 <= s.length <= 10^5
 * s 由小写英文字母组成
 * 1 <= k <= s.length
 */
public class MaxVowels {

    @Test
    public void test1() {
//        System.out.println(maxVowels("abciiidef", 3));
//        System.out.println(maxVowels("leetcode", 3));
        System.out.println(maxVowels("ibpbhixfiouhdljnjfflpapptrxgcomvnb", 33));
    }

    public int maxVowels(String s, int k) {
        int l = 0, r = 0, c = 0, len = s.length();
        int[] vowelsMapping = new int['z' + 1];
        vowelsMapping['a'] = 1;
        vowelsMapping['e'] = 1;
        vowelsMapping['i'] = 1;
        vowelsMapping['o'] = 1;
        vowelsMapping['u'] = 1;
        char[] charArray = s.toCharArray();
        while(r < k) {
            if (vowelsMapping[charArray[r]] == 1) {
                c++;
            }
            r++;
        }

        if (c == k) {
            return k;
        }

        int ans = c;

        while (r < len) {
            if (vowelsMapping[charArray[r++]] == 1) {
                c++;
            }
            if (vowelsMapping[charArray[l++]] == 1) {
                c--;
            }
            if (c == k) {
                return k;
            }
            ans = Math.max(ans, c);
        }
        return ans;
    }


    @Test
    public void test() {
        System.out.println((int)'u');
    }
}
