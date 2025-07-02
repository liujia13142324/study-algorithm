package com.lj.problem.leetcode._1;

/**
 *

 3304. 找出第 K 个字符 I

 提示
 Alice 和 Bob 正在玩一个游戏。最初，Alice 有一个字符串 word = "a"。
 给定一个正整数 k。
 现在 Bob 会要求 Alice 执行以下操作 无限次 :
 将 word 中的每个字符 更改 为英文字母表中的 下一个 字符来生成一个新字符串，并将其 追加 到原始的 word。
 例如，对 "c" 进行操作生成 "cd"，对 "zb" 进行操作生成 "zbac"。
 在执行足够多的操作后， word 中 至少 存在 k 个字符，此时返回 word 中第 k 个字符的值。
 注意，在操作中字符 'z' 可以变成 'a'。

 示例 1:
 输入：k = 5
 输出："b"
 解释：
 最初，word = "a"。需要进行三次操作:
 生成的字符串是 "b"，word 变为 "ab"。
 生成的字符串是 "bc"，word 变为 "abbc"。
 生成的字符串是 "bccd"，word 变为 "abbcbccd"。
 示例 2:

 输入：k = 10
 输出："c"

 提示：
 1 <= k <= 500
 */
public class KthCharacter {

    /**
     * 方法二，直接统计 k-1 的二进制中含有 1 的个数
     * @param k
     * @return
     */
    public char kthCharacter2(int k) {
        int count = 0;
        k--;
        while (k > 0) {
            count += k & 1;
            k >>>= 1;
        }
        char result = (char) ('a' + count);
        return result > 'z' ? 'a' : result;
    }

    public char kthCharacter(int k) {
        char[] str = new char[tableSizeFor(k)];
        str[0] = 'a';

        for (int i = 1; i < k; i<<=1) {
            for (int j = 0; j < i; j++) {
                char next = (char) (str[j] + 1);
                str[j + i] = next > 'z' ? 'a' : next;
            }
        }

        return str[k-1];
    }

    private int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n+1;
    }

    public static void main(String[] args) {
        System.out.println(new KthCharacter().kthCharacter2(5));;
    }
}
