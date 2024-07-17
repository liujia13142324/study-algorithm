package com.lj.problem.leetcode._1;

/**
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 *
 * 回文数
 * 是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 例如，121 是回文，而 123 不是。
 */
public class IsPalindrome {
    
    public boolean isPalindrome(int x) {
        
        // 必须要判断被 10 取整的情况，不然下面的反转逻辑就得变化
        if (x < 0 || (x % 10 == 0 && x !=0)) return false;
        
        // 反转后一般的数字
        int revertedNum = 0;
        while (revertedNum < x) {
            revertedNum = revertedNum * 10 + x % 10;
            x /= 10;
        }
        
        // 当出现奇数个数字，例如 12321，这种时候 x = 12 , revertedNum =123
        return x == revertedNum || x == revertedNum / 10;
    }
}
