package com.lj.problem.leetcode._1;


/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 *
 *
 * 示例 1：
 *
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * 示例 2：
 *
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 *
 *
 * 提示：
 *
 * 1 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] 仅由小写英文字母组成
 */
public class LongestCommonPrefix {
    
    
    
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int length = strs[0].length();
        int count = strs.length;
        for (int i = 0; i < length; i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < count; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }
    
    
    /*
    // 分治法
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        return longestCommonPrefix(strs, 0, strs.length - 1);
    }
    
    
    public String longestCommonPrefix(String[] strs, int start, int end){
        if (start == end) {
            return strs[start];
        }else {
            int mid = (start + end) >>> 1;
            String left = longestCommonPrefix(strs, start, mid);
            String right = longestCommonPrefix(strs, mid + 1, end);
            return commonPrefixStr(left, right);
        }
    }
    
    public String commonPrefixStr(String s1, String s2){
        int minLen = Math.min(s1.length(), s2.length());
        
        for (int i = 0; i < minLen; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                return s1.substring(0, i);
            }
        }
        
        return s1.substring(0, minLen);
    }*/
    
    
   /* public String longestCommonPrefix(String[] strs) {
        
        if (strs.length == 1) {
            return strs[0];
        }
        
        int maxLen = 0;
        int count = 0;
        
        outer:
        for(int i = 0; ; i++) {
            char c = '_';
            for (int j = 0; j < strs.length; j++) {
                if (i  >= strs[j].length()) {
                    break outer;
                }else if (c == '_'){
                    c = strs[j].charAt(i);
                }else if (c != strs[j].charAt(i)) {
                    break outer;
                }
                if (j == strs.length - 1 && ++count > maxLen){
                    maxLen = count;
                }
            }
        }
        
        // System.out.println(maxLen);
        return strs[0].substring(0, maxLen);
    }*/
    
    
    /*public String longestCommonPrefix(String[] strs) {
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        out:
        while (true) {
            char c = '_';
            for (int i = 0; i<strs.length; i++) {
                if (strs[i].length() <= idx) {
                    break out;
                }else if (i == 0) {
                    c = strs[i].charAt(idx);
                }else if (c != strs[i].charAt(idx)){
                    break out;
                }
            }
            sb.append(c);
            idx++;
        }
        return sb.toString();
    }*/
    
    
   
    
    /*public String longestCommonPrefix(String[] strs) {
        int min = Integer.MAX_VALUE;
        for (String s : strs) {
            if (s.length() < min) {
                min = s.length();
            }
        }
        
        String sub = "";
        out:
        for (int i = 0; i < min; i++) {
            sub = strs[0].substring(0, i + 1);
            for (String s: strs) {
                if (!s.startsWith(sub)) {
                    sub =  sub.substring(0, sub.length()-1);
                    break out;
                }
            }
        }
        
        return sub;
    }*/
    
    public static void main(String[] args) {
        System.out.println(new LongestCommonPrefix().longestCommonPrefix(new String[]{"dog","racecar","car"}));
        System.out.println(new LongestCommonPrefix().longestCommonPrefix(new String[]{"flower","flow","flight"}));
    }
    
}
