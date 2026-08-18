package com.lj.problem.leetcode._2;

import java.util.*;

/**
 * 1481. 不同整数的最少数目
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个整数数组 arr 和一个整数 k 。现需要从数组中恰好移除 k 个元素，请找出移除后数组中不同整数的最少数目。
 *
 *
 *
 * 示例 1：
 *
 * 输入：arr = [5,5,4], k = 1
 * 输出：1
 * 解释：移除 1 个 4 ，数组中只剩下 5 一种整数。
 * 示例 2：
 *
 * 输入：arr = [4,3,1,1,3,3,2], k = 3
 * 输出：2
 * 解释：先移除 4、2 ，然后再移除两个 1 中的任意 1 个或者三个 3 中的任意 1 个，最后剩下 1 和 3 两种整数。
 *
 *
 * 提示：
 *
 * 1 <= arr.length <= 10^5
 * 1 <= arr[i] <= 10^9
 * 0 <= k <= arr.length
 */
public class FindLeastNumOfUniqueInts {

    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> mapping = new HashMap<>();
        int[] cnt = new int[arr.length];
        int idx = 0;
        for (int num: arr) {
            if (mapping.containsKey(num)) {
                cnt[mapping.get(num)]++;
            }else {
                cnt[idx]++;
                mapping.put(num, idx++);
            }
        }
        Arrays.sort(cnt, 0, idx);
        int i = 0;
        for (; i < idx; i++) {
            if (cnt[i] > k) {
                break;
            }
            k -= cnt[i];
        }

        return idx - i;
    }

}
