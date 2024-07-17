package com.lj.problem.leetcode._1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
 * <p>
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 * <p>
 * 输入: numRows = 5
 * 输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 * <p>
 * 输入: numRows = 1
 * 输出: [[1]]
 * <p>
 * 1 <= numRows <= 30
 */
public class YangHui {
    
    @Test
    public void test(){
        System.out.println(generate(1));
        System.out.println(generate(2));
        System.out.println(generate(3));
        System.out.println(generate(4));
        System.out.println(generate(5));
        System.out.println(generate(6));
    }
    
    public List<List<Integer>> generate(int numRows) {
        
        List<List<Integer>> result = new ArrayList<>(numRows);
        
        for (int i = 0; i < numRows; i++) {
            ArrayList<Integer> row = new ArrayList<>(numRows);
            result.add(row);
            row.add(1);
            
            for (int j = 1; j < i; j++) {
                
                row.add(
                        result.get(i - 1).get(j - 1)
                                + result.get(i - 1).get(j)
                );
                
            }
            
            if(i>0){
                row.add(1);
            }
        }
        
        return result;
    }
}
