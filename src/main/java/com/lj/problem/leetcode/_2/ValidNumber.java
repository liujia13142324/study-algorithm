package com.lj.problem.leetcode._2;

import java.util.regex.Pattern;

/**
 * 有效数字（按顺序）可以分成以下几个部分：
 *
 * 若干空格
 * 一个 小数 或者 整数
 * （可选）一个 'e' 或 'E' ，后面跟着一个 整数
 * 若干空格
 * 小数（按顺序）可以分成以下几个部分：
 *
 * （可选）一个符号字符（'+' 或 '-'）
 * 下述格式之一：
 * 至少一位数字，后面跟着一个点 '.'
 * 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
 * 一个点 '.' ，后面跟着至少一位数字
 * 整数（按顺序）可以分成以下几个部分：
 *
 * （可选）一个符号字符（'+' 或 '-'）
 * 至少一位数字
 * 部分有效数字列举如下：["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"]
 *
 * 部分无效数字列举如下：["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"]
 *
 * 给你一个字符串 s ，如果 s 是一个 有效数字 ，请返回 true 。
 *
 */
public class ValidNumber {
    
    /**
     *
     * 整数
     * 小数=整数.整数
     * 小数=.整数
     * 整数e整数
     * 小数e整数
     * @param s
     * @return
     */
    public boolean validNumber(String s) {
        
        boolean isDecimal = false;
        boolean startE = false;
        boolean symbol = false;
        boolean hasNumber = false;
    
        char[] chars = s.trim().toCharArray();
        for (int i = 0; i <chars.length; i++) {
            // 数字
            if (chars[i] >= 48 && chars[i] <= 57) {
                hasNumber = true;
            }else if(chars[i] == '+' || chars[i] == '-') {
                // 正常只能有一个符号，如果碰到 e，重置符号位
                if (symbol) {
                    return false;
                }
                // 符号在首位, 符号位的前面有 e或者E
                if (i == 0 || chars[i-1] == 'e' || chars[i-1] == 'E') {
                    symbol = true;
                }else{
                    return false;
                }
            
            }else if (chars[i] == 'e' || chars[i] == 'E') {
                // e 只能出现一次，e 之前要出现数字
                if (startE || !hasNumber) {
                    return false;
                }
                symbol = false;
                hasNumber = false;
                startE = true;
            }else if (chars[i] == '.') {
                // 小数，只能出现一个小数点、以及 e 之后只能是整数
                if (isDecimal || startE) {
                    return false;
                }
                isDecimal = true;
            }else {
                return false;
            }
        }
    
        return hasNumber;
    }
    
    
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("[\\+\\-]?\\d");
        System.out.println(pattern.matcher("zxcasd").find());;
        System.out.println(pattern.matcher("+123").find());;
        System.out.println(pattern.matcher("-123").find());;
        System.out.println(pattern.matcher("123").find());;
        System.out.println(pattern.matcher("+-123").find());;
        System.out.println(pattern.matcher("-zxcasd").find());;
    
    
        System.out.println("---");
        System.out.println(Pattern.compile("\\d").matcher("-123").find());
        System.out.println(Pattern.compile("\\d").matcher("+123").find());
        System.out.println(Pattern.compile("\\d").matcher("2e10").find());
        System.out.println(Pattern.compile("\\d").matcher("-.3").find());
        System.out.println(Pattern.compile("\\d").matcher("e9").find());
        System.out.println("---");
        
        double a = 2e10;
        System.out.println((long)a);
        
        a = -90E3;
        System.out.println((long)a);
        
        a = 3e+7;
        System.out.println(a + "--->" + (long)a);
        
        a = +6e-1;
        System.out.println(a + "--->" + a);
        
        a = 53.5e93;
        System.out.println(a + "--->" + (long)a + "--->" + Long.MAX_VALUE);
        
        a = -.9;
        System.out.println(a + "--->" + (long)a + "--->" + Long.MAX_VALUE);
    
        System.out.println((int)'0');
        System.out.println((int)'9');
    
        System.out.println(new ValidNumber().validNumber("-1E-16"));
    }
}
