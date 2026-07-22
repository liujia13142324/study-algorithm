package com.lj.problem.leetcode._1;

/**
 * 3827. 统计单比特整数
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个整数 n。
 *
 * 如果一个整数的二进制表示中所有位都相同，则称其为 单比特数（Monobit）。
 *
 * 返回范围[0, n]（包括两端）内 单比特数 的个数。
 *
 *
 *
 * 示例 1：
 *
 * 输入： n = 1
 *
 * 输出： 2
 *
 * 解释：
 *
 * 范围[0, 1]内的整数对应的二进制表示为"0"和"1"。
 * 每个表示都由相同的位组成，因此答案是2。
 * 示例 2：
 *
 * 输入： n = 4
 *
 * 输出： 3
 *
 * 解释：
 *
 * 范围[0, 4]内的整数对应的二进制表示为"0"、"1"、"10"、"11"和"100"。
 * 只有0、1和3满足单比特条件。因此答案是3。
 */
public class CountMonobit {

    /**
     * 答案即为 (n + 1) 二进制的长度
     *  0 <= 2^k - 1 <= n 求 k 的个数
     *  1 <= 2^k <= n + 1
     *  0 <= k <= w - 1， w 为 (n + 1) 二进制的长度
     *  故 k = w (0 ~ w-1)
     * @param n
     * @return
     */
    public int countMonobit2(int n) {
        return 32 - Integer.numberOfLeadingZeros(n + 1);
    }

    public int countMonobit(int n) {
        int ans = 1;
        for (int i = 1; i <= n; i = (i << 1) + 1) {
            ans ++;
        }
        return ans;
    }
}
