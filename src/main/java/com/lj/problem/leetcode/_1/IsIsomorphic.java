package com.lj.problem.leetcode._1;

/**
 * 205. 同构字符串
 * 给定两个字符串 s 和 t ，判断它们是否是同构的。
 * 如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。
 * 每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以映射到自己本身。
 *
 * 示例 1：
 * 输入：s = "egg", t = "add"
 * 输出：true
 * 解释：
 * 字符串 s 和 t 可以通过以下方式变得相同：
 * 将 'e' 映射为 'a'。
 * 将 'g' 映射为 'd'。
 *
 * 示例 2：
 * 输入：s = "f11", t = "b23"
 * 输出：false
 * 解释：
 * 字符串 s 和 t 无法变得相同，因为 '1' 需要同时映射到 '2' 和 '3'。
 *
 * 示例 3：
 * 输入：s = "paper", t = "title"
 * 输出：true
 *
 * 提示：
 * 1 <= s.length <= 5 * 104
 * t.length == s.length
 * s 和 t 由任意有效的 ASCII 字符组成
 */
public class IsIsomorphic {
    public boolean isIsomorphic(String s, String t) {
        char[] c1 = s.toCharArray();
        char[] c2 = t.toCharArray();
        byte[] mapping1 = new byte[128];
        byte[] mapping2 = new byte[128];

        for (int i = 0; i < c1.length; i++) {
            if (mapping1[c1[i]] == 0 && mapping2[c2[i]] == 0) {
                mapping1[c1[i]] = (byte) c2[i];
                mapping2[c2[i]] = (byte) c1[i];
                continue;
            }else if (mapping1[c1[i]] == c2[i]) {
                continue;
            }
            return false;
        }
        return true;
    }
}
