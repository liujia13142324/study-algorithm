package com.lj.problem.hwod;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给你一个字符串 s，字符串s首尾相连成一个环形 ，请你在环中找出’l’、‘o’、‘x’ 字符都恰好出现了偶数次最长
 *
 * 输入是一串小写的字母组成的字符串。输出是一个整数。
 *
 * 1 <= s.length <= 5 x 10^5
 * s 只包含小写英文字母。
 *
 * 输入：
 * alolobo
 *
 * 输出：
 * 6
 *
 * 说明：
 * 最长子字符串之一是 "alolob"，它包含 'l'，'o'各 2 个，以及 0 个 'x' 。
 *
 * 输入：
 * looxdolx
 *
 * 输出：
 * 7
 *
 * 说明：
 * 最长子字符串是 "oxdolxl"，由于是首尾连接在一起的，所以最后一个 'x' 和开头的 'l'是连接在一起的，此字符串包含 2 个 'l
 */
public class CountLoopStr {
    
    public static void main(String[] args) {
//        countLoopStr("lxolobo");
        countLoopStr("alolobo");
        countLoopStr("looxdolx");
        countLoopStr("looxdaxl");
    }
    
    
    public static void countLoopStr(String str) {
        int l = 0;
        int max = 0;
        int status = 0;
        int n = str.length();
        int[] pos = new int[1 << 3];
        Arrays.fill(pos, -1);
        pos[0] = 0;
        int idx;
        
        for (int i = 1, k = 2*n-1; i < k; i++) {
            // 左边框要移动
            if (i > n) l++;
            idx = (i - 1) % n;
            
            if (str.charAt(idx) == 'l') {
                status ^= 1 << 2;
            } else if (str.charAt(idx) == 'o') {
                status ^= 1 << 1;
            } else if (str.charAt(idx) == 'x') {
                status ^= 1 << 0;
            }
            
            if (pos[status] >= l) {
                max = Math.max(max, i - pos[status]);
            } else {
                pos[status] = i;
            }
            
        }
    
        System.out.println(max);
        
    }
    
    
    /*public static void countLoopStr(String str) {
        char[] chars = str.toCharArray();
        int max = -1;
        int maxI = 0;
        int maxJ = 0;
        
        for (int i = 0 ; i < chars.length; i++) {
            
            int j = i;
            int countL = 0;
            int countO = 0;
            int countX = 0;
            
            
            for (int k = 0; k < chars.length; k++) {
                if (chars[j] == 'l') {
                    countL++;
                }else if (chars[j] == 'o') {
                    countO++;
                }else if (chars[j] == 'x') {
                    countX++;
                }
                
                if (check(countL, countO, countX) && max < (k+1)) {
                    max = k + 1;
                    maxI = i;
                    maxJ = j;
                }
                
                j = (j + 1) % chars.length;
            }
            
            if (max == chars.length) {
                break;
            }
            
        }
        
        System.out.println(str + " -> " + max + " -> " + maxI + "," +maxJ);
    }*/
    
    private static boolean check(int c1, int c2, int c3) {
        return c1 % 2 == 0 && c2 % 2 == 0 && c3 % 2 == 0;
    }
}
