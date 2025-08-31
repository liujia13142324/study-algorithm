package com.lj.problem.leetcode._1;

import org.junit.Test;

/**
 * 各位相加
 * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。返回这个结果。
 *
 * 99 -> 18 -> 9
 * 999 -> 27 -> 9
 * 999999999 -> 81 -> 9
 * 1999999999 -> 82 -> 10 -> 1
 *
 * 523 -> 10 -> 1
 * 742 -> 13 -> 4
 *
 * 示例 1:
 * 输入: num = 38
 * 输出: 2
 * 解释: 各位相加的过程为：
 * 38 --> 3 + 8 --> 11
 * 11 --> 1 + 1 --> 2
 * 由于 2 是一位数，所以返回 2。
 *
 * 示例 2:
 * 输入: num = 0
 * 输出: 0
 *
 * 提示：
 * 0 <= num <= 231 - 1
 * 进阶：你可以不使用循环或者递归，在 O(1) 时间复杂度内解决这个问题吗？
 */
public class AddDigits {

    @Test
    public void test() {
        System.out.println(Integer.toBinaryString(38));
        System.out.println(addDigits2(38));
    }

    public int addDigits4(int num) {
        return (num - 1) % 9 + 1;
    }

    public int addDigits3(int num) {
        return num < 10 ? num : num%9 == 0 ? 9 : num%9;
    }

    public int addDigits2(int num) {
        while (num >= 10) {
            int tmp = 0;
            for (int i = 1000000000; i >= 1; i/=10) {
                tmp += num / i;
                num = num % i;
            }
            num = tmp;
        }
        return num;
    }

    public int addDigits(int num) {
        while (num >= 10) {
            int tmp = 0;
            for (long i = 10000000000L; i > 1; i/=10) {
                tmp += (int) ((num % i)/(i / 10));
            }
            num = tmp;
        }
        return num;
    }
}
