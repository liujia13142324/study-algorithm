package com.lj.problem.leetcode._1;

/**
 * 整数的各位积和之差
 *
 * 给你一个整数 n，请你帮忙计算并返回该整数「各位数字之积」与「各位数字之和」的差。
 *
 * 示例 1：
 * 输入：n = 234
 * 输出：15
 * 解释：
 * 各位数之积 = 2 * 3 * 4 = 24
 * 各位数之和 = 2 + 3 + 4 = 9
 * 结果 = 24 - 9 = 15
 *
 * 示例 2：
 * 输入：n = 4421
 * 输出：21
 * 解释：
 * 各位数之积 = 4 * 4 * 2 * 1 = 32
 * 各位数之和 = 4 + 4 + 2 + 1 = 11
 * 结果 = 32 - 11 = 21
 *
 * 提示：
 * 1 <= n <= 10^5
 */
public class SubtractProductAndSum {

    int subtractProductAndSum(int n) {
        int product = 1, sum = 0, num = 0;
        int i = 100000;
        while (n / i == 0) i /= 10;
        for (; i >= 1; i/=10) {
            num = n / i;
            sum += num;
            product *= num;
            n %= i;
        }
        return product - sum;
    }
}
