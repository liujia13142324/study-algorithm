package com.lj.problem.leetcode._1;

import org.junit.Test;

/**
 * 125. 验证回文串
 * 如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个 回文串 。
 * 字母和数字都属于字母数字字符。
 * 给你一个字符串 s，如果它是 回文串 ，返回 true ；否则，返回 false 。
 *
 *
 * 示例 1：
 * 输入: s = "A man, a plan, a canal: Panama"
 * 输出：true
 * 解释："amanaplanacanalpanama" 是回文串。
 *
 * 示例 2：
 * 输入：s = "race a car"
 * 输出：false
 * 解释："raceacar" 不是回文串。
 *
 * 示例 3：
 * 输入：s = " "
 * 输出：true
 * 解释：在移除非字母数字字符之后，s 是一个空字符串 "" 。
 * 由于空字符串正着反着读都一样，所以是回文串。
 *
 *
 * 提示：
 * 1 <= s.length <= 2 * 105
 * s 仅由可打印的 ASCII 字符组成
 */
public class IsPalindrome2 {

    @Test
    public void test() {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(isPalindrome("race a car"));
        System.out.println(isPalindrome(" "));

        System.out.println(isPalindrome2("A man, a plan, a canal: Panama"));
        System.out.println(isPalindrome2("race a car"));
        System.out.println(isPalindrome2(" "));
    }

    public boolean isPalindrome3(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (!Character.isLetterOrDigit(s.charAt(l))) {
                l++;
            }else if (!Character.isLetterOrDigit(s.charAt(r))) {
                r--;
            }else if (Character.toLowerCase(s.charAt(l)) == Character.toLowerCase(s.charAt(r))) {
                l++;
                r--;
            }else {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome2(String s) {
        char[] charArray = s.toCharArray();
        int l = 0, r = charArray.length - 1;
        while (l < r) {
           if (!Character.isLetterOrDigit(charArray[l])) {
               l++;
           }else if (!Character.isLetterOrDigit(charArray[r])) {
               r--;
           }else if (Character.toLowerCase(charArray[l]) == Character.toLowerCase(charArray[r])) {
               l++;
               r--;
           }else {
               return false;
           }
        }
        return true;
    }


    public boolean isPalindrome(String s) {
        char[] charArray = s.toCharArray();
        int l = 0, r = charArray.length - 1;
        /**
         * a-z: [97, 122]
         * 0-9 [48,57]
         * A-Z: [65-90]
         */
        while (l < r) {
            char c1 = charArray[l];
            while (l < r && !Character.isLetterOrDigit(c1)) {
                l++;
                c1 = charArray[l];
            }
            if (l >= r) {
                break;
            }

            char c2 = charArray[r];
            while (l < r && !Character.isLetterOrDigit(c2)) {
                r--;
                c2 = charArray[r];
            }
            if (l >= r) {
                break;
            }
            if (Character.toLowerCase(c1) != Character.toLowerCase(c2)) {
                return false;
            }
            l++;r--;
        }
        return true;
    }


}
