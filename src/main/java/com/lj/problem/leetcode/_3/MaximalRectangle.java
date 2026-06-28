package com.lj.problem.leetcode._3;

/**
 * 85. 最大矩形
 * 困难
 * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 *
 * 示例 1：
 * 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * 输出：6
 * 解释：最大矩形如上图所示。
 *
 * 示例 2：
 * 输入：matrix = [["0"]]
 * 输出：0

 * 示例 3：
 * 输入：matrix = [["1"]]
 * 输出：1
 *
 * 提示：
 *
 * rows == matrix.length
 * cols == matrix[0].length
 * 1 <= rows, cols <= 200
 * matrix[i][j] 为 '0' 或 '1'
 */
public class MaximalRectangle {


    /**
     * 做 m 次 84 题
     * @param matrix
     * @return
     */
    public int maximalRectangle(char[][] matrix) {
        char[] arr = new char[matrix[0].length];
        int[] stack = new int[matrix[0].length + 1];
        int ans = 0;

        for (char[] chars : matrix) {
            for (int j = 0; j < chars.length; j++) {
                if (chars[j] == '1') {
                    arr[j]++;
                } else {
                    arr[j] = 0;
                }
            }
            int idx = -1;
            stack[++idx] = -1;
            for (int j = 0; j <= arr.length; j++) {
                int h = j == chars.length ? -1 : arr[j];
                while (idx > 0 && arr[stack[idx]] > h) {
                    ans = Math.max(ans, (j - stack[idx - 1] - 1) * arr[stack[idx]]);
                    idx--;
                }
                stack[++idx] = j;
            }
        }

        return ans;
    }
}
