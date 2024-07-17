package com.lj.problem.leetcode._2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 438. 找到字符串中所有字母异位词
 *
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 *
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 *
 *
 *
 * 示例 1:
 *
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 *  示例 2:
 *
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 *
 *
 * 提示:
 *
 * 1 <= s.length, p.length <= 3 * 104
 * s 和 p 仅包含小写字母
 */
public class FindAnagrams {
    
    
    public List<Integer> findAnagrams(String s, String p) {
        
        int n = s.length();
        int m = p.length();
        List<Integer> result = new ArrayList();
        
        if (n < m) {return result;}
        
        
        int[] pCount = new int[26];
        int[] sCount = new int[26];
        
        for (int i = 0; i < m; i++) {
            pCount[p.charAt(i) - 'a']++;
            sCount[s.charAt(i) - 'a']++;
        }
        
        if (Arrays.equals(sCount, pCount)) {
            result.add(0);
        }
        
        for (int i = 0, j = i + m, k = n - m; i < k; i++,j++) {
            sCount[s.charAt(i) - 'a']--;
            sCount[s.charAt(j) - 'a']++;
            
            if (Arrays.equals(sCount, pCount)) {
                result.add(i+1);
            }
        }
        
        return result;
    }
    
    /*public List<Integer> findAnagrams(String s, String p) {
        int n = s.length();
        int m = p.length();
        int subLen = n - m;
        List<Integer> result = new ArrayList();
        int[] hash = new int[26];
        int[] uses = new int[26];
        
        for (int i = 0; i < m; i++) {
            hash[p.charAt(i) - 'a']++;
        }
        
        int l = -1;
        int r;
        int idx1;
        while (++l <= subLen) {
            r = l;
            while (r < n && hash[idx1=(s.charAt(r++) - 'a')] - uses[idx1] > 0) {
                uses[idx1]++;
                if (r - l == m) {
                    result.add(l);
                    uses[s.charAt(l++) - 'a']--;
                }
            }
            if (r > l) {
                // uses = new int[26];
                Arrays.fill(uses, 0);
            }
        }
        
        return result;
    }*/
    
    
    
    
}
