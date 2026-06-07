package com.lj.problem.leetcode._1;

/**
 * 242. 有效的字母异位词
 * 简单
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的 字母异位词。
 *
 * 示例 1:
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 *
 * 示例 2:
 * 输入: s = "rat", t = "car"
 * 输出: false
 *
 *
 * 提示:
 * 1 <= s.length, t.length <= 5 * 104
 * s 和 t 仅包含小写字母
 *
 * 进阶: 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 */
public class IsAnagram {

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        char[] chars1 = s.toCharArray();
        char[] chars2 = t.toCharArray();
        short[] cnt = new short[26];
        for (int i = 0; i < chars1.length; i++) {
            cnt[chars1[i] - 'a']++;
        }
        for (int i = 0; i < chars2.length; i++) {
            if (cnt[chars2[i] - 'a'] < 1) {
                return false;
            }
            cnt[chars2[i] - 'a']--;
        }
        return true;
    }

}
