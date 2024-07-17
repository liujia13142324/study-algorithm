package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.Arrays;

/**
 * 排列相关的问题
 */
public class Permutation {


    @Test
    public void testConstructArray(){
        System.out.println(Arrays.toString(constructArray(5,1)));;
    }

    /**
     * 给你两个整数 n 和 k ，请你构造一个答案列表 answer ，该列表应当包含从 1 到 n 的 n 个不同正整数，并同时满足下述条件：
     *
     * 假设该列表是 answer = [a1, a2, a3, ... , an] ，那么列表 [|a1 - a2|, |a2 - a3|, |a3 - a4|, ... , |an-1 - an|]
     * 中应该有且仅有 k 个不同整数。 返回列表 answer 。如果存在多种答案，只需返回其中 任意一种 。
     *
     * 输入：n = 3, k = 1
     * 输出：[1, 2, 3]
     * 解释：[1, 2, 3] 包含 3 个范围在 1-3 的不同整数，并且 [1, 1] 中有且仅有 1 个不同整数：1
     *  [1,1,1]
     *
     * 输入：n = 3, k = 2
     * 输出：[1, 3, 2]
     * 解释：[1, 3, 2] 包含 3 个范围在 1-3 的不同整数，并且 [2, 1] 中有且仅有 2 个不同整数：1 和 2
     * [2,1]
     *
     * 分析：
     *  k应该要小于等于n-1
     *  假设 n=4, k=3
     *  [1,4,2,1] -> [3,2,1]
     *
     *  假设 n=4, k=2
     *  [1,3,4,2] -> [2,1,2]
     *
     *  解：
     *  当 k=1   ----> [1,2,...,n]
     *  当 k=n-1 ----> [1,n,2,n-1,3,n-2, ...]
     *  其他一般情况，将上面两种特殊情况合并，即列表的前半部分相邻差均为1，后半部分相邻差从 k 开始逐渐递减到1
     *
     *  最终结果按照如下规律排列
     *  			    +k   -(k-1)       +(k-2)     -(k-3)
     * 1	2  ...	n-k    n        n-k+1        n-1         n-k+2  ...
     *
     * @param n
     * @param k
     * @return
     */
    public int[] constructArray(int n, int k) {

        int[] result = new int[n];
        int j = n-k;
        int i;

        for(i=0; i<j; i++){
            result[i] = i+1;
        }

        for(i=0; i<n-j; i++){
            result[i+j] = result[i+j-1] + (k-i) * ( i%2==0?1:-1 );
        }

        return result;
    }

}
