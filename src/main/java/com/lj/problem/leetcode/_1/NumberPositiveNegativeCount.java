package com.lj.problem.leetcode._1;

import java.util.Arrays;

/**
 * 给你一个按 非递减顺序 排列的数组 nums ，返回正整数数目和负整数数目中的最大值。
 *
 * 换句话讲，如果 nums 中正整数的数目是 pos ，而负整数的数目是 neg ，返回 pos 和 neg二者中的最大值。
 * 注意：0 既不是正整数也不是负整数。
 *
 * 示例 1：
 *
 * 输入：nums = [-2,-1,-1,1,2,3]
 * 输出：3
 * 解释：共有 3 个正整数和 3 个负整数。计数得到的最大值是 3 。
 * 示例 2：
 *
 * 输入：nums = [-3,-2,-1,0,0,1,2]
 * 输出：3
 * 解释：共有 2 个正整数和 3 个负整数。计数得到的最大值是 3 。
 * 示例 3：
 *
 * 输入：nums = [5,20,66,1314]
 * 输出：4
 * 解释：共有 4 个正整数和 0 个负整数。计数得到的最大值是 4 。
 */
public class NumberPositiveNegativeCount {
    
    public int maximumCount(int[] nums) {
        
        int firstGrantThan0 = binarySearch(nums, 0);
        int firstLowThan0 = firstGrantThan0 - 1;
        
        while (firstLowThan0 > -1 && nums[firstLowThan0] == 0) {
            firstLowThan0--;
        }
        
        
        return Math.max(firstLowThan0+1, nums.length - firstGrantThan0);
    }
    
    // 找到第一个大于等于 val 的位置
    // 当返回大于 nums.length-1 时，说明 nums 里面的内容全部小于 val
    public static int binarySearch(int[] nums, int val) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            
            int mid = (left+right)/2;
            
            if (val >= nums[mid]) {
                left = mid + 1;
            }else if (val < nums[mid]) {
                right = mid - 1;
            }
        }
    
        return nums[left] <= val ? left + 1 : left;
    }
    
    public static void main(String[] args) {
//        System.out.println(new NumberPositiveNegativeCount().maximumCount(new int[]{-2,-1,-1,1,2,3}));
//        System.out.println(new NumberPositiveNegativeCount().maximumCount(new int[]{-3,-2,-1,0,0,1,2}));
        System.out.println(new NumberPositiveNegativeCount().maximumCount(new int[]{
                0,0,0,0,0,0,0,0,0,
        }));
    
    
//        System.out.println(binarySearch(new int[]{-2,-1,-1,1,2,3}, 0));
//        System.out.println(binarySearch(new int[]{-3,-2,-1,0,0,1,2}, 0));
    }
    
    
}
