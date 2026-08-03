package com.lj.problem.leetcode._2;

import org.junit.Test;

/**
 * 567. 字符串的排列
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的 排列。如果是，返回 true ；否则，返回 false 。
 *
 * 换句话说，s1 的排列之一是 s2 的 子串 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s1 = "ab" s2 = "eidbaooo"
 * 输出：true
 * 解释：s2 包含 s1 的排列之一 ("ba").
 * 示例 2：
 *
 * 输入：s1= "ab" s2 = "eidboaoo"
 * 输出：false
 *
 *
 * 提示：
 *
 * 1 <= s1.length, s2.length <= 104
 * s1 和 s2 仅包含小写字母
 */
public class CheckInclusion {

    @Test
    public void test() {
//        System.out.println(checkInclusion3("ab", "eidboaoo"));
        System.out.println(checkInclusion4("ab", "eidbaooo"));
//        System.out.println(checkInclusion3("hello", "ooolleoooleh"));
    }

    /**
     * 不定滑窗
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {
        int targetLen = s1.length();
        int n = s2.length();
        if (targetLen > n) return false;
        int[] mapping = new int[27];
        for (char c: s1.toCharArray()) {
            mapping[c - 'a'] ++;
        }
        char[] chars = s2.toCharArray();
        for (int i = 0; i < n; i++) {
            if (mapping[chars[i] - 'a'] > 0) {
                int r = i;
                while (r - i < targetLen && r < n) {
                    while (mapping[chars[r] - 'a'] <= 0 && i < r) {
                        mapping[chars[i ++] - 'a'] ++;
                    }
                    if (mapping[chars[r] - 'a'] <= 0) break;
                    mapping[chars[r] - 'a'] --;
                    r++;
                }
                if (r - i == targetLen) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 定滑窗，统计匹配字母的数量
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion4(String s1, String s2) {
        int targetLen = s1.length();
        int n = s2.length();
        if (targetLen > n) return false;
        int[] mapping = new int[26];
        int letter = 0;
        for (char c : s1.toCharArray()) {
            if (mapping[c - 'a'] == 0) letter++;
            mapping[c - 'a']++;
        }
        char[] chars = s2.toCharArray();
        int l = 0;
        for (int i = 0; i < n; i++) {
            mapping[chars[i] - 'a'] --;
            if (mapping[chars[i] - 'a'] == 0) {
                letter--;
            }
            if (letter == 0) return true;
            if (i - l + 1 < targetLen) continue;

            // 左边离开窗口
            if (mapping[chars[l] - 'a'] == 0) {
                letter++;
            }
            mapping[chars[l] - 'a'] ++;
            l++;
        }

        return false;
    }


    /**
     * 定滑窗，直接统计匹配数量 --> 不行，反例，s1: adc, s2: dcda
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion3(String s1, String s2) {
        int targetLen = s1.length();
        int n = s2.length();
        if (targetLen > n) return false;
        boolean[] mapping = new boolean[26];
        int[] cnt = new int[26];
        for (char c : s1.toCharArray()) {
            mapping[c - 'a'] = true;
            cnt[c - 'a']++;
        }
        char[] chars = s2.toCharArray();
        int matchCnt = 0;
        int l = 0;
        for (int i = 0; i < n; i++) {
            if (cnt[chars[i] - 'a'] > 0) {
                cnt[chars[i] - 'a'] --;
                matchCnt++;
            }
            if (matchCnt == targetLen) return true;
            if (i - l + 1 < targetLen) continue;

            // 离开窗口，为下次循环做准备
            if (mapping[chars[l] - 'a']) {
                matchCnt--;
                cnt[chars[l] - 'a']++;
            }
            l++;
        }
        return false;
    }

    /**
     * 错的，直接去count，匹配  abc,  ababc 这样的例子会失败
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion2(String s1, String s2) {
        int[] mapping = new int[27];
        int[] tmp = new int[27];
        int targetLen = s1.length();
        for (char c: s1.toCharArray()) {
            mapping[c - 'a'] ++;
            tmp[c - 'a'] ++;
        }
        int matchCnt = 0;
        boolean preMatched = false;
        for (char c: s2.toCharArray()) {
            if (tmp[c - 'a'] > 0) {
                matchCnt++;
                tmp[c - 'a'] --;
                preMatched = true;
                if (matchCnt == targetLen) {
                    return true;
                }
            }else if(preMatched){
                preMatched = false;
                matchCnt = 0;
                System.arraycopy(mapping, 0, tmp, 0, tmp.length);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        long ans = 1;
        for (int i = 1; i <= 10000; i++) {
            ans *= i;
            System.out.println("i: " + i + ", ans: " + ans);
            if (ans < 0) {
                return;
            }
        }
    }
}
