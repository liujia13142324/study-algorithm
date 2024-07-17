package com.lj.problem.leetcode._2;

import java.util.List;

/**
 * 给定 m 个数组，每个数组都已经按照升序排好序了。现在你需要从两个不同的数组中选择两个整数（每个数组选一个）并且计算它们的距离。两个整数 a 和 b 之间的距离定义为它们差的绝对值 |a-b| 。你的任务就是去找到最大距离
 *
 * 示例 1：
 *
 * 输入：
 * [[1,2,3],
 *  [4,5],
 *  [1,2,3]]
 * 输出： 4
 * 解释：
 * 一种得到答案 4 的方法是从第一个数组或者第三个数组中选择 1，同时从第二个数组中选择 5 。
 *
 * [[1,4],[0,5]]
 * 4
 *
 *
 * 注意：
 *
 * 每个给定数组至少会有 1 个数字。列表中至少有两个非空数组。
 * 所有 m 个数组中的数字总数目在范围 [2, 10000] 内。
 * m 个数组中所有整数的范围在 [-10000, 10000] 内。
 */
public class MaxDistanceOfArr {
    
  
    public int maxDistance(List<List<Integer>> arrays) {
        List<Integer> tmp = arrays.get(0);
        // 记录全局的最大最小值
        int max = tmp.get(tmp.size()-1);
        int min = tmp.get(0);
        // 每次计算, max(result, max(next[len-1]-min, next[0]-max))
        int result = -1;
    
        for (int i = 1; i < arrays.size(); i++) {
            tmp = arrays.get(i);
            result = Math.max(result, Math.max(
                    Math.abs(tmp.get(tmp.size() - 1) - min)
                    ,Math.abs(tmp.get(0) - max))
            );
        
            min = Math.min(min, tmp.get(0));
            max = Math.max(max, tmp.get(tmp.size()-1));
        
        }
    
    
        return result;
    }
    
    
}
