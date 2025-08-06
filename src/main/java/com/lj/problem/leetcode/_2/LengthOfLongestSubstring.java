package com.lj.problem.leetcode._2;

import org.junit.Test;
import sun.security.util.Length;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * 3. 无重复字符的最长子串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长
 * 子串的长度
 *
 * 示例 1:
 *
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */
public class LengthOfLongestSubstring {



    public static void main(String[] args) {
        System.out.println(new LengthOfLongestSubstring().lengthOfLongestSubstring("abcabcbb"));
        System.out.println(new LengthOfLongestSubstring().lengthOfLongestSubstring("bbbbbbb"));
        System.out.println(new LengthOfLongestSubstring().lengthOfLongestSubstring("dvdf"));
        System.out.println(new LengthOfLongestSubstring().lengthOfLongestSubstring(" "));
        System.out.println(new LengthOfLongestSubstring().lengthOfLongestSubstring("abba"));
    }

    @Test
    public void test() {
        System.out.println(lengthOfLongestSubstring1("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("abcabcbb"));

        System.out.println(lengthOfLongestSubstring1("bbbbbbb"));
        System.out.println(lengthOfLongestSubstring("bbbbbbb"));

        System.out.println(lengthOfLongestSubstring1("abba"));
        System.out.println(lengthOfLongestSubstring("abba"));

        System.out.println(lengthOfLongestSubstring1("  "));
        System.out.println(lengthOfLongestSubstring("  "));
    }

    public int lengthOfLongestSubstring1(String s) {
        int l = 0, r = 0, ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        char[] charArray = s.toCharArray();

        for (int len = charArray.length; r < len; r++) {
            if (map.get(charArray[r]) == null || map.get(charArray[r]) < l) {
                map.put(charArray[r], r);
                ans = Math.max(ans, r - l + 1);
            }else {
                l = map.get(charArray[r]) + 1;
                map.put(charArray[r], r);
            }
        }
        return ans;
    }



    /*public int lengthOfLongestSubstring(String s) {
        int[] helper = new int[128];
        char[] chars = s.toCharArray();
        int max = 0;
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            for (int j = i; j < chars.length; j++) {
                if (helper[chars[j]] != 0) {
                    if (count > max) {
                        max = count;
                    }
                    Arrays.fill(helper, 0);
                    count = 0;
                    break;
                }else {
                    helper[chars[j]] = 1;
                    count++;
                }
            }
            
        }
        return Math.max(max, count) ;
    }*/
    
    /**
     * 滑动窗口算法
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int[] helper = new int[128];
        Arrays.fill(helper, -1);
        char[] chars = s.toCharArray();
        int max = 0;
        int left = 0;
        int i;

        // i 就是窗口的右边界
        for (i = 0; i < chars.length; i++) {
            if (helper[chars[i]] != -1) {
                max = Math.max(i - left,max);
                // 当出现的重复字符是在窗口的范围之内
                if (helper[chars[i]] >= left) {
                    // 窗口左边界右移动
                    left = helper[chars[i]] + 1;
                }
            }
            
            helper[chars[i]] = i;
        }
        return Math.max(max, i - left) ;
    }
    
    
    

}
