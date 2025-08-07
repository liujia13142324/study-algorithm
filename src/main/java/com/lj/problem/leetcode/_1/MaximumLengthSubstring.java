package com.lj.problem.leetcode._1;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 3090. 每个字符最多出现两次的最长子字符串
 * 给你一个字符串 s ，请找出满足每个字符最多出现两次的最长子字符串，并返回该子字符串的 最大 长度。
 *
 * 示例 1：
 * 输入： s = "bcbbbcba"
 * 输出： 4
 * 解释：
 * 以下子字符串长度为 4，并且每个字符最多出现两次："bcbbbcba"。
 *
 * 示例 2：
 * 输入： s = "aaaa"
 * 输出： 2
 * 解释：
 * 以下子字符串长度为 2，并且每个字符最多出现两次："aaaa"。
 *
 *
 * 提示：
 *
 * 2 <= s.length <= 100
 * s 仅由小写英文字母组成。
 */
public class MaximumLengthSubstring {

    @Test
    public void test() {
//        System.out.println(maximumLengthSubstring("bcbbbcba"));
//        System.out.println(maximumLengthSubstring("aaaa"));
//        System.out.println(maximumLengthSubstring("cbddcdcacc"));
        System.out.println(maximumLengthSubstring("acaccacbba"));
    }

    public int maximumLengthSubstring(String s) {
        char[] charArray = s.toCharArray();
        Map<Character, int[]> map = new HashMap<>();
        int[] tmp;
        int l = 0, r = 0, ans = 0;
        for (int len = charArray.length; r < len; r++) {
            tmp = map.get(charArray[r]);
            if (tmp == null) {
                ans = Math.max(ans, r - l + 1);
                map.put(charArray[r], new int[]{r, Integer.MAX_VALUE});
            }else if (tmp[1] == Integer.MAX_VALUE) {
                ans = Math.max(ans, r - l + 1);
                tmp[1] = r;
            }else if (tmp[0] < l) {
                ans = Math.max(ans, r - l + 1);
                tmp[0] = tmp[1];
                tmp[1] = r;
            }else if (tmp[1] < 1) {
                ans = Math.max(ans, r - l + 1);
                tmp[0] = r;
                tmp[1] = Integer.MAX_VALUE;
            }else {
                ans = Math.max(ans, r - l);
                l = tmp[0] + 1;
                tmp[0] = tmp[1];
                tmp[1] = r;
            }
        }
        return ans;
    }
}
