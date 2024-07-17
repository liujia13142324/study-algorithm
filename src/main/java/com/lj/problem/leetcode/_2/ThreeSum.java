package com.lj.problem.leetcode._2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和
 *
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
 *
 * 你返回所有和为 0 且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 */
public class ThreeSum {
    
    
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        
        for (int i = 0,n = nums.length - 2; i < n; i++) {
            if (nums[i] > 0) {break;}
            
            // 跳过重复的 i
            if (i > 0 && nums[i] == nums[i -1]) {continue;}
            
            for (int second = i + 1, third = nums.length - 1; second < third; second++) {
                // 跳过重复的 second
                if (second > i + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 右边的值要控制小于等于 (nums[second] + nums[i]))
                while (third > second && nums[third] > -(nums[second] + nums[i])) {
                    third--;
                }
                
                if (second >= third) {
                    break;
                }
                
                if (nums[i] + nums[second] + nums[third] == 0) {
                    result.add(Arrays.asList(nums[i], nums[second], nums[third]));
                    // 跳过重复的 second
                    while (third == third - 1) third--;
                }
            }
            
        }
        return result;
    }
    
    
    public static void main(String[] args) {
        System.out.println(ThreeSum.threeSum(new int[]{-4, -1, -1, 0, 1, 3}));
    }
    

}
