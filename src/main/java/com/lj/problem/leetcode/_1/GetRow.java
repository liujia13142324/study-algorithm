package com.lj.problem.leetcode._1;

/**
 * 119. 杨辉三角 II
 * 给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex 行。
 *
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 *
 * 示例 1:
 * 输入: rowIndex = 3
 * 输出: [1,3,3,1]
 *
 * 示例 2:
 * 输入: rowIndex = 0
 * 输出: [1]
 *
 * 示例 3:
 * 输入: rowIndex = 1
 * 输出: [1,1]
 *
 *
 * 提示:
 * 0 <= rowIndex <= 33
 */

import com.lj.study.common.bean.A;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GetRow {

    @Test
    public void test() {
        System.out.println(c(1,1));
        System.out.println(c(5,3));
        System.out.println(c(10,4));
    }

    static List<List<Integer>>  preHandle = generate(34);

    /**
     * 预处理
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        return preHandle.get(rowIndex);
    }


    /**
     * 滚动数组
     */
    public List<Integer> getRow3(int rowIndex) {
        if (rowIndex == 0) return Arrays.asList(1);
        if (rowIndex == 1) return Arrays.asList(1,1);
        List<Integer> ans = new ArrayList<>(rowIndex + 1);
        ans.add(1);
        ans.add(1);
        for (int i = 2; i <= rowIndex; i++) {
            for (int j = i - 1; j > 0; j--) {
                ans.set(j, ans.get(j) + ans.get(j - 1));
            }
            ans.add(1);
        }
        return ans;
    }

    public List<Integer> getRow2(int rowIndex) {
        List<Integer> ans = new ArrayList<>(rowIndex + 1);
        for (int i = 0; i <= rowIndex; i++) {
            ans.add(c(rowIndex, i));
        }
        return ans;
    }

    private Integer c(int n, int m) {
        if (m == 0 || m == n) {
            return 1;
        }
        int ans = 0;
        for (int i = n - 1, j = m - 1; i >= j; i--) {
            ans += c(i, j);
        }
        return ans;
    }


    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>(numRows);
        ans.add(Collections.singletonList(1));
        for (int i = 1; i < numRows; i++) {
            List<Integer> tmp = new ArrayList<>();
            tmp.add(1);
            for (int j = 1; j < i; j++) {
                tmp.add(ans.get(i - 1).get(j) + ans.get(i - 1).get(j - 1));
            }
            tmp.add(1);
            ans.add(tmp);
        }
        return ans;
    }
}
