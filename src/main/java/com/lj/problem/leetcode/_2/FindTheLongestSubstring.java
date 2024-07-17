package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给你一个字符串 s ，请你返回满足以下条件的最长子字符串的长度：每个元音字母，即 'a'，'e'，'i'，'o'，'u' ，在子字符串中都恰好出现了偶数次。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "eleetminicoworoep"
 * 输出：13
 * 解释：最长子字符串是 "leetminicowor" ，它包含 e，i，o 各 2 个，以及 0 个 a，u 。
 * 示例 2：
 *
 * 输入：s = "leetcodeisgreat"
 * 输出：5
 * 解释：最长子字符串是 "leetc" ，其中包含 2 个 e 。
 * 示例 3：
 *
 * 输入：s = "bcbcbc"
 * 输出：6
 * 解释：这个示例中，字符串 "bcbcbc" 本身就是最长的，因为所有的元音 a，e，i，o，u 都出现了 0 次。
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 5 x 10^5
 * s 只包含小写英文字母。
 */
public class FindTheLongestSubstring {
    
    
    public int findTheLongestSubstring(String s) {
        int n = s.length();
        int max = 0;
        int status = 0;
        int pos[] = new int[1 << 5];
        Arrays.fill(pos, -1);
        pos[0] = 0;
        char tmp;
        
        for (int i = 1; i <= n; i++) {
            tmp = s.charAt(i-1);
            if (tmp == 'a') {
                status ^= 1<<4;
            }else if (tmp == 'e') {
                status ^= 1<<3;
            }else if (tmp == 'i') {
                status ^= 1<<2;
            }else if (tmp == 'o') {
                status ^= 1<<1;
            }else if (tmp == 'u') {
                status ^= 1<<0;
            }
            
            if (pos[status] >= 0) {
                max = Math.max(max, i - pos[status]);
            }else {
                pos[status] = i;
            }
        }
        
        return max;
    }
    
  
    @Test
    public void test1() {
        System.out.println(1 | 1<<4);
    }
    
    
    /*public int findTheLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        
        int max = 0;
//        int maxI = 0;
//        int maxK = 0;
        
        out:
        for (int i = 0,j = chars.length; i < j; i++) {
            int countA = 0;
            int countE = 0;
            int countI = 0;
            int countO = 0;
            int countU = 0;
            int k = i;
            for (; k < chars.length; k++) {
                if (chars[k] == 'a') {
                    countA++;
                }else if (chars[k] == 'e') {
                    countE++;
                }else if (chars[k] == 'i') {
                    countI++;
                }else if (chars[k] == 'o') {
                    countO++;
                }else if (chars[k] == 'u') {
                    countU++;
                }
    
                if (check(countA,countE,countI,countO,countU) && k - i + 1 > max) {
                    max = k - i + 1;
//                    maxI = i;
//                    maxK = k;
                    if (max == s.length()) {
                        break out;
                    }
                }
            }
    
            j = chars.length - max;
    
            if (max == s.length()) {
                break;
            }
        }
    
//        System.out.print(maxI + " " + maxK + " ");
        
        return max;
    }
    
    private boolean check(int countA, int countE, int countI, int countO, int countU) {
        return (countA & 1) == 0 && (countE & 1) == 0 && (countI & 1) == 0 && (countO & 1) == 0 && (countU & 1) == 0;
    }*/
    
    public static void main(String[] args) {
//        System.out.println(new FindTheLongestSubstring().findTheLongestSubstring("el"));
        System.out.println(new FindTheLongestSubstring().findTheLongestSubstring("eleetminicoworoep"));
        System.out.println(new FindTheLongestSubstring().findTheLongestSubstring("leetcodeisgreat"));
        System.out.println(new FindTheLongestSubstring().findTheLongestSubstring("bcbcbc"));
        System.out.println(new FindTheLongestSubstring().findTheLongestSubstring("qnplnlarrtztkotazhufrsfczrzibvccaoayyihidztfljcffiqfviuwjowkppdajmknzgidixqgtnahamebxfowqvnrhuzwqohquamvszkvunbxjegbjccjjxfnsiearbsgsofywtqbmgldgsvnsgpdvmjqpaktmjafgkzszekngivdmrlvrpyrhcxbceffrgiyktqilkkdjhtywpesrydkbncmzeekdtszmcsrhsciljsrdoidzbjatvacndzbghzsnfdofvhfxdnmzrjriwpkdgukbaazjxtkomkmccktodig"));
    }
    
    
}
