package com.lj.problem.hwod;

import java.nio.charset.Charset;

/**
 * 题目描述:均衡串定义:字符串只包含两种字符，且两种字符的个数相同。
 * 给定一个均衡字符串，请给出可分割成新的均衡子串的最大个数。
 * 约定字符串中只包含大写的"X'和"Y'两种字符。
 *
 * 输入描述:均衡串:XXYYXY
 *
 * 字符串的长度[2.10000]，给定的字符串均为均衡串，
 *
 * 输出描述:可分割为两个子串:
 * XXYY
 * XY
 *
 * 输入：s = "RLRRLLRLRL"
 * 输出：4
 * 解释：s 可以分割为 "RL"、"RRLL"、"RL"、"RL" ，每个子字符串中都包含相同数量的 'L' 和 'R' 。
 *
 * 输入：s = "RLRRRLLRLL"
 * 输出：2
 * 解释：s 可以分割为 "RL"、"RRRLLRLL"，每个子字符串中都包含相同数量的 'L' 和 'R' 。
 * 注意，s 无法分割为 "RL"、"RR"、"RL"、"LR"、"LL" 因为第 2 个和第 5 个子字符串不是平衡字符串。
 *
 * 输入：s = "LLLLRRRR"
 * 输出：1
 * 解释：s 只能保持原样 "LLLLRRRR" 。
 *
 *
 */
public class SplitBalanceString {
    
    public int balancedStringSplit(String s) {
        int count = 0;
        Character first = null;
        int firstCount = 0;
        int secondCount = 0;
        char[] chars = s.toCharArray();
        for (int i=0; i<chars.length; i++) {
            if (first == null) {
                first = chars[i];
                firstCount++;
                continue;
            }
            
            if (first == chars[i]) {
                firstCount++;
            }else{
                secondCount++;
            }
            
            if (firstCount == secondCount) {
                count++;
                first = null;
                firstCount = 0;
                secondCount = 0;
            }
        }
        
        return count;
    }
    
    public static void main(String[] args) {
    
        System.out.println(new SplitBalanceString().balancedStringSplit("XXYYXY"));
        System.out.println(new SplitBalanceString().balancedStringSplit("RLRRLLRLRL"));
        System.out.println(new SplitBalanceString().balancedStringSplit("RLRRRLLRLL"));
        System.out.println(new SplitBalanceString().balancedStringSplit("LLLLRRRR"));
    }
    
}
