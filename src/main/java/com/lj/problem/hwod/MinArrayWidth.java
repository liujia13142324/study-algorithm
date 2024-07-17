package com.lj.problem.hwod;

import java.util.HashMap;

/**
 * 题目描述:给定一个矩阵，包含N*M个整数，和一个包含K个整数的数组。
 * 现在要求在这个矩阵中找一个宽度最小的子矩阵，要求子矩阵包含数组中所有的整数。
 * 输入描述:第一行输入两个正整数N，M，表示矩阵大小。接下来N行M列表示矩阵内容。下一行包含一个正整数K。下一行包含K个整数，表示所需包含的数组，K个整数可能存在重复数字所有输入数据小于1000。
 * 输出描述:输出包含一个整数，表示满足要求子矩阵的最小宽度，若找不到，输出-1.
 *
 * 示例1
 * 输入:2 5
 * 1 2 2 3 1
 * 2 3 23 2
 * 3
 * 1 2 3
 * 输出:2
 * 说明:矩阵第0、3列包含了1、2、3,矩阵第3、4列包含了1、2、3
 *
 * 示例2
 * 输入:2 5
 * 1 2 2 3 1
 * 1 3 2 3 4
 * 3
 * 1 1 4
 * 输出:5
 * 说明:矩阵第1、2、3、4、5列包含了1、1、4
 *
 */
public class MinArrayWidth {

    public int solve(int[][] arr, int[] match) {
        int width = arr[0].length;
        int height = arr.length;
        int matchCount = match.length;
        HashMap<Integer, Integer> map = new HashMap<>(matchCount);
        for (int i = 0; i < matchCount; i++) {
            if (map.get(match[i]) == null) {
                map.put(match[i], 1);
            }else {
                map.put(match[i], map.get(match[i]) + 1);
            }
        }
        
        int minWidth = Integer.MAX_VALUE;
    
        int w = 1;
        int mCount = 0;
        Integer tmp;
        HashMap<Integer, Integer> remainCount = new HashMap<>();
        
        for (int j = 0; j < width; j++) {
            
            for (int i = 0; i < height && mCount < matchCount; i++) {
                if (map.get(arr[i][j]) != null) {
                    if ((tmp = remainCount.get(arr[i][j])) == null) {
                        mCount++;
                        remainCount.put(arr[i][j], map.get(arr[i][j]) - 1);
                    }else {
                        mCount++;
                        remainCount.put(arr[i][j], tmp - 1);
                    }
                }
            }
            
            if (mCount == matchCount && w < minWidth) {
                minWidth = w;
                w = 1;
                matchCount = 0;
                remainCount.clear();
            }else {
                w++;
            }
        }
        
        
        return minWidth == Integer.MAX_VALUE ? -1 : minWidth;
    }
    
    
    public static void main(String[] args) {
        System.out.println(new MinArrayWidth().solve(
                new int[][]{
                        new int[]{1,2,2,3,1}
                        ,new int[]{2,3,2,3,2}
                }
            
                , new int[]{1,2,3}));
    
        System.out.println(new MinArrayWidth().solve(
                new int[][]{
                        new int[]{1,2,2,3,1}
                        ,new int[]{1,3,2,3,4}
                }
            
                , new int[]{1,1,4}));
    
        
    }
    
    
}
