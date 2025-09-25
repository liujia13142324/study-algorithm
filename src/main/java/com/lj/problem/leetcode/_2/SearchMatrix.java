package com.lj.problem.leetcode._2;

import org.junit.Test;

/**
 * 74. 搜索二维矩阵
 * 给你一个满足下述两条属性的 m x n 整数矩阵：
 *
 * 每行中的整数从左到右按非严格递增顺序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * 给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
 *
 *
 *
 * 示例 1：
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * 输出：true
 *
 * 示例 2：
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * 输出：false
 *
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -104 <= matrix[i][j], target <= 104
 */
public class SearchMatrix {

    @Test
    public void test() {
        System.out.println(searchMatrix(new int[][]{new int[]{1,3,5,7}, new int[]{10,11,16,20}, new int[]{23,30,34,60}}, 3));
        System.out.println(searchMatrix(new int[][]{new int[]{1}}, 0));
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int start = -1, end = matrix.length, laxIdx = matrix[0].length - 1;
        while (start + 1 < end) {
            int mid = (start + end) >>> 1;
            if (matrix[mid][laxIdx] >= target) {
                end = mid;
            }else {
                start = mid;
            }
        }
        if (end >= matrix.length) {
            return false;
        }
        int[] arr = matrix[end];
        start = -1;
        end = arr.length;
        while (start + 1 < end) {
            int mid = (start + end) >>> 1;
            if (arr[mid] >= target) {
                end = mid;
            }else {
                start = mid;
            }
        }
        return end < arr.length && arr[end] == target;
    }

}
