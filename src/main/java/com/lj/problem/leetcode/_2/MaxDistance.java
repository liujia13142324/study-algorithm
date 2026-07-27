package com.lj.problem.leetcode._2;

import java.util.List;

/**
 * 624. 数组列表中的最大距离
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定 m 个数组，每个数组都已经按照升序排好序了。
 *
 * 现在你需要从两个不同的数组中选择两个整数（每个数组选一个）并且计算它们的距离。两个整数 a 和 b 之间的距离定义为它们差的绝对值 |a-b| 。
 *
 * 返回最大距离。
 *
 * 示例 1：
 *
 * 输入：[[1,2,3],[4,5],[1,2,3]]
 * 输出：4
 * 解释：
 * 一种得到答案 4 的方法是从第一个数组或者第三个数组中选择 1，同时从第二个数组中选择 5 。
 * 示例 2：
 *
 * 输入：arrays = [[1],[1]]
 * 输出：0
 *
 *
 * 提示：
 *
 * m == arrays.length
 * 2 <= m <= 105
 * 1 <= arrays[i].length <= 500
 * -104 <= arrays[i][j] <= 104
 * arrays[i] 以 升序 排序。
 * 所有数组中最多有 105 个整数。
 */
public class MaxDistance {

    public int maxDistance(List<List<Integer>> arrays) {
        int ans = 0;
        int minVal = arrays.get(0).get(0);
        int maxVal = arrays.get(0).get(arrays.get(0).size()-1);

        for (int i = 1, n = arrays.size(); i < n; i++) {
            List<Integer> curr = arrays.get(i);
            ans = Math.max(ans, Math.max(Math.abs(curr.get(0) - maxVal), Math.abs(curr.get(curr.size() - 1) - minVal)));
            minVal = Math.min(minVal, curr.get(0));
            maxVal = Math.max(maxVal, curr.get(curr.size()-1));
        }

        return ans;
    }
}
