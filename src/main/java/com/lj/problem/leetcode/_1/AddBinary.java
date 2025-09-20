package com.lj.problem.leetcode._1;

import org.junit.Test;

import java.util.Arrays;

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


    public String addBinary2(String a, String b) {
        StringBuilder ans = new StringBuilder();
        int carry = 0;
        for(int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
            int sum = carry;
            sum += i >= 0 ? a.charAt(i) - '0' : 0;
            sum += j >= 0 ? b.charAt(j) - '0' : 0;
            ans.append(sum & 1);
            carry = sum >>> 1;
        }
        if (carry == 1) ans.append(1);
        return ans.reverse().toString();
    }


    public String addBinary(String a, String b) {
        char[] charArray = a.toCharArray();
        char[] charArray1 = b.toCharArray();
        char[] result;
        int offset = 2 * '0';
        int i = charArray.length - 1, j = charArray1.length - 1, k, m = 0;
        if (i >= j) {
            result = charArray;
            k = i;
        }else {
            result = charArray1;
            k = j;
        }
        while (i >= 0 && j >= 0) {
            int tmp = (charArray[i] + charArray1[j] + m) - offset;
            result[k] = (char) ('0' + (tmp & 1));
            m = tmp >>> 1;
            i--;j--;k--;
        }

        if (m > 0) {
            while (k >= 0 && m > 0) {
                if (result[k] == '0') {
                    result[k] = '1';
                    m = 0;
                }else {
                    result[k--] = '0';
                    m = 1;
                }
            }
        }

        if (m > 0) {
            char[] tmp = new char[result.length + 1];
            System.arraycopy(result, 0, tmp, 1, result.length);
            tmp[0] = '1';
            return new String(tmp);
        }

        return new String(result);
    }

    @Test
    public void test() {
        System.out.println(addBinary("11", "1"));
//        System.out.println(addBinary("1010", "1011"));
    }

}
