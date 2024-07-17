package com.lj.problem.leetcode._2;

import java.util.HashMap;
import java.util.Map;

/**
 * 最长连续序列
 *
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 *
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * 示例 2：
 *
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 *
 *
 * 提示：
 *
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 */
public class LongestConsecutive {
    public int longestConsecutive(int[] nums) {
        Map<Integer,Boolean> exist = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            exist.put(nums[i],true);
        }
        
        int max = 0;
        for (int i : exist.keySet()) {
            int count = 1;
            // 如果 i-1 在数组中，那么完全就不用处理的，等到处理 i-1 的时候再进行处理
            if (exist.get(i-1) == null) {
                for (int start = i + 1; exist.get(start) != null; start++,count++);
            }
            max = Math.max(max,count);
        }
        
        return max;
    }
}
