package com.lj.problem.leetcode._3;

import org.junit.Test;

/**
 * 1499. 满足不等式的最大值
 * 困难
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个数组 points 和一个整数 k 。数组中每个元素都表示二维平面上的点的坐标，并按照横坐标 x 的值从小到大排序。也就是说 points[i] = [xi, yi] ，并且在 1 <= i < j <= points.length 的前提下， xi < xj 总成立。
 *
 * 请你找出 yi + yj + |xi - xj| 的 最大值，其中 |xi - xj| <= k 且 1 <= i < j <= points.length。
 *
 * 题目测试数据保证至少存在一对能够满足 |xi - xj| <= k 的点。
 *
 *
 *
 * 示例 1：
 *
 * 输入：points = [[1,3],[2,0],[5,10],[6,-10]], k = 1
 * 输出：4
 * 解释：前两个点满足 |xi - xj| <= 1 ，代入方程计算，则得到值 3 + 0 + |1 - 2| = 4 。第三个和第四个点也满足条件，得到值 10 + -10 + |5 - 6| = 1 。
 * 没有其他满足条件的点，所以返回 4 和 1 中最大的那个。
 * 示例 2：
 *
 * 输入：points = [[0,0],[3,0],[9,2]], k = 3
 * 输出：3
 * 解释：只有前两个点满足 |xi - xj| <= 3 ，代入方程后得到值 0 + 0 + |0 - 3| = 3 。
 *
 *
 * 提示：
 *
 * 2 <= points.length <= 105
 * points[i].length == 2
 * -108 <= xi, yi <= 108
 * 0 <= k <= 2 * 108
 * 对于所有的 1 <= i < j <= points.length，均有 xi < xj。
 * xi 构成一个严格递增序列。
 */
public class FindMaxValueOfEquation {

    @Test
    public void test() {
        System.out.println(findMaxValueOfEquation(new int[][]{{1,3},{2,0},{5,10},{6,-10}}
                , 1));

        System.out.println(findMaxValueOfEquation(new int[][]{{-17,-6},{-4,0},{-2,-16},{-1,2},{0,11},{6,18}}
                , 13));
    }

    /**
     * 单调 yi - xi 的最大值
     * @param points
     * @param k
     * @return
     */
    public int findMaxValueOfEquation2(int[][] points, int k) {
        int[] minus = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            minus[i] = points[i][1] - points[i][0];
        }

        int[] queue = new int[points.length];
        int head = 0, tail = -1;
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < points.length; i++) {
            while (tail >= head && points[i][0] - points[queue[head]][0] > k) {
                head++;
            }
            if (tail >= head) {
                ans = Math.max(ans, points[i][0] - points[queue[head]][0] + points[i][1] + points[queue[head]][1]);
            }
            while (tail >= head && minus[i] >= minus[queue[tail]]) {
                tail--;
            }
            queue[++tail] = i;
        }

        return ans;
    }

    /**
     * 单调 sum((yi - y<i-1>) - (xi - x<i-1>)) 的最大值
     * @param points
     * @param k
     * @return
     */
    public int findMaxValueOfEquation(int[][] points, int k) {
        int[] sums = new int[points.length];
        for (int i = 1; i < points.length; i++) {
            sums[i] = sums[i - 1] + ((points[i][1] - points[i - 1][1]) - (points[i][0] - points[i - 1][0]));
        }

        int[] queue = new int[points.length];
        int head = 0, tail = -1;
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < points.length; i++) {
            while (tail >= head && points[i][0] - points[queue[head]][0] > k) {
                head++;
            }
            if (tail >= head) {
                ans = Math.max(ans, points[i][0] - points[queue[head]][0] + points[i][1] + points[queue[head]][1]);
            }
            while (tail >= head && sums[i] >= sums[queue[tail]]) {
                tail--;
            }
            queue[++tail] = i;
        }

        return ans;
    }


}
