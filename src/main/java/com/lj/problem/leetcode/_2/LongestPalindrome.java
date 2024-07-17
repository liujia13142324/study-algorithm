package com.lj.problem.leetcode._2;

/**
 * 最长回文子串
 * 给你一个字符串 s，找到 s 中最长的回文
 * 子串
 * 。
 *
 * 如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
 */
public class LongestPalindrome {
    
    
    // 中心扩散法
    public String longestPalindrome(String s) {
        int maxLen = 1;
        int maxStart = 0;
        
        for (int i = 0, n = s.length() - 1; i < n; i++) {
            int len1 = expending(i, i, s);
            int len2 = expending(i, i+1, s);
            int len3 = Math.max(len1, len2);
            if (len3 > maxLen) {
                maxLen = len3;
                maxStart = i - (len3-1)/2;
                // n = s.length() - maxLen;
            }
        }
        
        return s.substring(maxStart, maxStart + maxLen);
    }
    
    
    public int expending(int center1, int center2, String s) {
        while (
                center1 >= 0 && center2 < s.length() &&
                        (s.charAt(center1) == s.charAt(center2))
        ) {
            center1--; center2++;
        }
        
        return (center2 - center1 + 1) - 2;
    }
    
   /*
    public String longestPalindrome(String s){
        String maxStr = s.length() == 0 ? "" : s.charAt(0) + "";
        int maxLen = maxStr.length();
    
        for (int i = 0, k = s.length() - maxLen; i < k ; i++) {
            // 从后往前，由最长的回文开始判断，是否满足，然后递减
            for (int j = s.length() - 1; i < j - maxLen + 1; j--) {
                int end = j;
                int start = i;
                while (start < end) {
                    if (s.charAt(start) != s.charAt(end)) {
                        break;
                    }
                    start++; end--;
                }
                int len = (j-i+1);
                // 是回文，由于从后往前遍历，直接就能 break
                if (start >= end ) {
                    // 且比当前最长的都要长
                    if (maxLen < len) {
                        maxStr = s.substring(i, j+1);
                        maxLen = len;
                        k = s.length() - maxLen;
                    }
                    break;
                }
            }
        }
    
        
        *//*for (int i = 0, k = s.length() - 1; i < k - maxLen + 1; i++) {
            for (int j = s.length() - 1; i < j - maxLen + 1; j--) {
                int end = j;
                int start = i;
                while (start < end) {
                    if (s.charAt(start) != s.charAt(end)) {
                        break;
                    }
                    start++; end--;
                }
                int len = (j-i+1);
                // 是回文，由于从后往前遍历，直接就能 break
                if (start >= end ) {
                    // 且比当前最长的都要长
                    if (maxLen < len) {
                        maxStr = s.substring(i, j+1);
                        maxLen = len;
                    }
                    break;
                }
            }
        }*//*
        
        
        // 最初不优化版本
        *//*for (int i = 0, k = s.length() - 1; i < k; i++) {
            for (int j = s.length() - 1; j > i; j--) {
                int end = j;
                int start = i;
                while (start < end) {
                    if (s.charAt(start) != s.charAt(end)) {
                        break;
                    }
                    start++; end--;
                }
                int len = (j-i+1);
                // 是回文，由于从后往前遍历，直接就能 break
                if (start >= end ) {
                    // 且比当前最长的都要长
                    if (maxStr.length() < len) {
                        maxStr = s.substring(i, j+1);
                    }
                    break;
                }
            }
        }*//*
        
        return maxStr;
    }*/
    
    
    
    
    public static void main(String[] args) {
        System.out.println(new LongestPalindrome().longestPalindrome("cbbd"));
    }
}
