package com.lj.problem.leetcode._3;

import org.junit.Test;

/**
 * 76. 最小覆盖子串
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 *
 * 注意：
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 *
 * 示例 1：
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
 *
 * 示例 2：
 * 输入：s = "a", t = "a"
 * 输出："a"
 * 解释：整个字符串 s 是最小覆盖子串。
 *
 * 示例 3:
 * 输入: s = "a", t = "aa"
 * 输出: ""
 * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
 * 因此没有符合条件的子字符串，返回空字符串。
 *
 *
 * 提示：
 * m == s.length
 * n == t.length
 * 1 <= m, n <= 105
 * s 和 t 由英文字母组成
 */
public class MinWindow {

    @Test
    public void test() {
//        System.out.println((int)'Z');
//        System.out.println((int)'z');
        System.out.println(minWindow2("ADOBECODEBANC", "ABC"));
        System.out.println(minWindow2("a", "a"));
        System.out.println(minWindow2("a", "aa"));
        System.out.println(minWindow2("ab", "a"));
        System.out.println(minWindow2("aa", "aa"));
        System.out.println(minWindow2("acbbaca", "aba"));
        System.out.println(minWindow2("bba", "ab"));
        System.out.println(minWindow2("aBbaBBBBA", "BBA"));
        System.out.println(minWindow2("aaabdabcefaecbef", "abc"));
        System.out.println(minWindow2("baAaABabBba", "AbbB"));

    }

    public String minWindow2(String s, String t) {
        int[] target = new int['z' + 1];
        int[] cnt = new int['z' + 1];
        for (char c: t.toCharArray()) {
            target[c]++;
        }
        int l = 0, len = s.length(), targetLen = t.length(), matchLen = 0, minLen = Integer.MAX_VALUE, minL = -1, minR = -1;
        char[] chars = s.toCharArray();
        for (int r = 0; r < len; r++) {
            if (target[chars[r]] > 0) {
                cnt[chars[r]]++;
                if (matchLen == 0) {
                    l = r;
                }

                if (cnt[chars[r]] <= target[chars[r]]) {
                    matchLen++;
                }

                if (matchLen == targetLen) {
                    // 去除左边界的多余字符
                    while (target[chars[l]] <= 0 || cnt[chars[l]] > target[chars[l]]) {
                        if (target[chars[l]] > 0) {
                            cnt[chars[l]]--;
                        }
                        l++;
                    }

                    if (r - l + 1 < minLen) {
                        minLen = Math.min(minLen, r - l + 1);
                        minL = l;
                        minR = r;
                    }
                }
            }
        }
        return minL >= 0 ? s.substring(minL, minR + 1): "";
    }


    public String minWindow(String s, String t) {

        if (s.length() < t.length()) return "";

        byte[] target = new byte[123];
        int tartLen = t.length();
        for (char c: t.toCharArray()) {
            target[c]++;
        }
        byte[] cnt = new byte[123];
        char[] charArray = s.toCharArray();
        // 找到第一个窗口，包含完整的 t
        int r = 0, l = 0, matchLen = 0;
        for (; r < charArray.length; r++) {
            if (cnt[charArray[r]] < target[charArray[r]]) {
                if (matchLen == 0) {
                    l = r;
                }
                cnt[charArray[r]] ++;
                matchLen++;
                if (matchLen == tartLen) {
                    break;
                }
                continue;
            }
            if (target[charArray[r]] > 0) {
                cnt[charArray[r]] ++;
                if (charArray[l] == charArray[r] && cnt[charArray[r]] > target[charArray[r]]) {

                }
            }
        }

        if (matchLen < tartLen) return "";

        if (r >= charArray.length) return s;

        // 开始右滑
        int minL = l, minR = r, minLen = r - l + 1;
        r++;
        for (; r < charArray.length; r++) {
            if (target[charArray[r]] > 0) {
                if (matchLen == tartLen) {
                    boolean findSame = false;
                    while (l < r) {
                        if (!findSame && charArray[l] == charArray[r]) {
                            findSame = true;
                            cnt[charArray[l]] --;
                            matchLen--;
                            l++;
                            continue;
                        }
                        if (findSame && target[charArray[l]] > 0) {
                            break;
                        }
                        if (cnt[charArray[l]] > 0) {
                            cnt[charArray[l]] --;
                            matchLen--;
                        }
                        l++;
                    }
                }
                if (cnt[charArray[r]] < target[charArray[r]]) {
                    cnt[charArray[r]] ++;
                    matchLen++;
                    if (matchLen == tartLen && r-l+1 < minLen) {
                        minLen = r-l+1;
                        minL = l;
                        minR = r;
                    }
                }
            }
        }

        return s.substring(minL, minR+1);
    }
}
