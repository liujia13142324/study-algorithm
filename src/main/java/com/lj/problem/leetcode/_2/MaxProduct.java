package com.lj.problem.leetcode._2;

import org.junit.Test;

/**
 * 152. 乘积最大子数组
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续 子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 *
 * 测试用例的答案是一个 32-位 整数。
 *
 * 请注意，一个只包含一个元素的数组的乘积是这个元素的值。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 *
 * 输入: nums = [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 *
 *
 * 提示:
 *
 * 1 <= nums.length <= 2 * 104
 * -10 <= nums[i] <= 10
 * nums 的任何子数组的乘积都 保证 是一个 32-位 整数
 */
public class MaxProduct {

    @Test
    public void test() {
        System.out.println(maxProduct3(new int[]{-4,-3,-2}));
    }

    public int maxProduct3(int[] nums) {
        int max = 1;
        int min = 1;
        int ans = Integer.MIN_VALUE;
        for (int num: nums) {
            if (num >= 0) {
                max = Math.max(max, 1) * num;
                min = Math.min(min, 1) * num;
            }else {
                int tmp = max;
                max = Math.min(min, 1) * num;
                min = Math.max(tmp, 1) * num;
            }
            ans = Math.max(ans, max);
        }
        return ans;
    }


    /*public int maxProduct(int[] nums) {
        
        if (nums.length == 1) {return nums[0];}
        
        int result = Integer.MIN_VALUE;
        for (int i = 0 ; i < nums.length-1; i++) {
            int tmp = nums[i];
            result = Math.max(result,tmp);
            for (int j = i + 1; j < nums.length; j++) {
                result = Math.max(result,nums[j]);
                tmp *= nums[j];
                result = Math.max(result,tmp);
            }
        }
        
        return result;
    }*/
    
   /* public int maxProduct(int[] nums) {
        if (nums.length == 1) {return nums[0];}
        int result = Integer.MIN_VALUE;
        
        int fMin;
//        int fMax = Integer.MIN_VALUE;
        int[] maxs = new int[nums.length];
        maxs[0] = fMin = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            maxs[i] = Math.max(maxs[i-1]*nums[i], Math.max(nums[i], fMin * nums[i]));
            fMin = Math.min(fMin * nums[i], Math.min(nums[i], maxs[i-1] * nums[i]) );
        }
        
        for (int i:maxs) {
            if (result < i) {
                result = i;
            }
        }
        return result;
    }*/
    
    
    public int maxProduct(int[] nums) {
        if (nums.length == 1) {return nums[0];}
        int result;
        int fMin;
        int fMax;
        result = fMin = fMax = nums[0];
        int tmp1,tmp2;
        for (int i = 1; i < nums.length; i++) {
            tmp1 = Math.max(fMax * nums[i], Math.max(nums[i], fMin * nums[i]));
            tmp2 = Math.min(fMin * nums[i], Math.min(nums[i], fMax * nums[i]) );
            fMax = tmp1;
            fMin = tmp2;
            result = Math.max(result, fMax);
        }
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println(new MaxProduct().maxProduct(new int[]{-4, -3, -2}));
    }
    
    
}
