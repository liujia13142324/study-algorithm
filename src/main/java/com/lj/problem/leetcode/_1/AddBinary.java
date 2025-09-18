package com.lj.problem.leetcode._1;

/**
 * 67. 二进制求和
 * 相关企业
 * 给你两个二进制字符串 a 和 b ，以二进制字符串的形式返回它们的和。
 *
 * 示例 1：
 * 输入:a = "11", b = "1"
 * 输出："100"
 *
 * 示例 2：
 * 输入：a = "1010", b = "1011"
 * 输出："10101"
 *
 *
 * 提示：
 * 1 <= a.length, b.length <= 104
 * a 和 b 仅由字符 '0' 或 '1' 组成
 * 字符串如果不是 "0" ，就不含前导零
 */
public class AddBinary {

    public String addBinary(String a, String b) {
        char[] charArray = a.toCharArray();
        char[] charArray1 = b.toCharArray();
        char[] result;
        if (charArray.length >= charArray1.length) {
            result = charArray;
        }else {
            result = charArray1;
        }
        int i = charArray.length - 1, j = charArray1.length - 1, k = 0;
        while (i >= 0 && j >= 0) {
            if (charArray[i] == '1' && charArray[j] == '1') {

            }else if (charArray[i] == '0' && charArray[j] == '0') {

            }
        }


    }

}
