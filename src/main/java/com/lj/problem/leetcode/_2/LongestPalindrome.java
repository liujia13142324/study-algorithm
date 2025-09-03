package com.lj.problem.leetcode._2;

import org.junit.Test;

/**
 * 5. 最长回文子串
 * 给你一个字符串 s，找到 s 中最长的 回文 子串。
 *
 * 示例 1：
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 *
 * 示例 2：
 * 输入：s = "cbbd"
 * 输出："bb"
 *
 *
 * 提示：
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母组成
 */
public class LongestPalindrome {

    @Test
    public void test() {
        System.out.println(longestPalindrome2("babad"));
        System.out.println(longestPalindrome2("cbbd"));
        System.out.println(longestPalindrome2("aacabdkacaa"));
        System.out.println(longestPalindrome2("bacabab"));
        System.out.println(longestPalindrome2("xaabacxcabaaxcabaax"));
        System.out.println(longestPalindrome2("aaabaaaa"));

    }

    public String longestPalindrome2(String s) {
        char[] charArray = s.toCharArray();
        int maxL = 0, maxR = 0;
        for (int i = 0; i < charArray.length - maxR + maxL; i++) {
            int l = i;
            int r = charArray.length - 1;
            int secondLMatched = -1;
            boolean firstLMatched = false;
            while (l < r) {
                if (charArray[r] == charArray[i]) {
                    if (secondLMatched == -1 && firstLMatched) secondLMatched = r;
                    if (!firstLMatched) firstLMatched = true;
                }
                if (charArray[l] == charArray[r]) {
                    l++;
                    r--;
                }else if (secondLMatched != -1){
                    l = i;
                    r = secondLMatched;
                    firstLMatched = false;
                    secondLMatched = -1;
                }else {
                    l = i;
                    r--;
                }
            }
            // 汇聚到一点，回文数的就是以 l 为中心， l - i 为半径
            if (l == r && (l - i) * 2 + 1 > maxR - maxL) {
                maxL = i;
                maxR = i + (l - i) * 2;
            }else if ((l - i) * 2 > maxR - maxL){
                maxL = i;
                maxR = i + (l - i) * 2 - 1;
            }
        }
        return s.substring(maxL, maxR + 1);
    }



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
