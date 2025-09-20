package com.lj.problem.leetcode._1;

import com.lj.study.common.bean.A;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 118. 杨辉三角
 * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
 *
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 *
 * 示例 1:
 * 输入: numRows = 5
 * 输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 *
 * 示例 2:
 * 输入: numRows = 1
 * 输出: [[1]]
 *
 * 提示:
 * 1 <= numRows <= 30
 */
public class Generate {


    public List<List<Integer>> generate2(int numRows) {
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

    public List<List<Integer>> generate(int numRows) {
        if (numRows == 1) return Collections.singletonList(Collections.singletonList(1));
        if (numRows == 2) return Arrays.asList(Collections.singletonList(1), Arrays.asList(1, 1));

        List<List<Integer>> ans = new ArrayList<>(Arrays.asList(Collections.singletonList(1), Arrays.asList(1, 1)));
        // numRows - 2
        for (int i = 2; i < numRows; i++) {
            List<Integer> tmp = new ArrayList<>(i + 1);
            tmp.add(1);
            List<Integer> preLine = ans.get(i - 1);
            for (int j = 0, len = preLine.size() - 1; j < len; j++) {
                tmp.add(preLine.get(j) + preLine.get(j + 1));
            }
            tmp.add(1);
            ans.add(tmp);
        }
        return ans;
    }


}
