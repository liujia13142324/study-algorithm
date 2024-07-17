package com.lj.problem.leetcode._2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 计算数组中，奇数 >= k 的子数组的个数
 */
public class NumberOfSubarrays {
    
    
    // 前缀和，记录第i个 odds 停顿的次数，odds[0] 初始化为1
    public int numberOfSubarrays(int[] nums, int k) {
        int result = 0;
        int odds = 0;
        int[] pre = new int[nums.length + 1];
        pre[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            odds += nums[i] & 1;
            result += (odds >= k ? pre[odds - k] : 0);
            pre[odds] += 1;
        }
        
        return result;
    }
    
    
    // 求和：(odd[i] - odd[i-1]) * (odd[i+k] - odd[i+k-1])
    /*public int numberOfSubarrays(int[] nums, int k) {
    
        int[] odd = new int[nums.length + 2];
        int idx = 1;
        int result = 0;
        
        for (int i = 0; i < nums.length; i++) {
            if ( (nums[i] & 0x1) == 1) {
                odd[idx++] = i;
            }
        }
        
        odd[0] = -1; odd[idx] = nums.length;
        
        for (int i = 1,j = idx - k; i <= j; i++) {
            result += (odd[i] - odd[i-1]) * (odd[i+k] - odd[i+k-1]);
        }
        
        return result;
    }*/
    
    /*public int numberOfSubarrays(int[] nums, int k) {
        int[] pre = new int[nums.length + 1] ;
        int count = 0;
        int left = 0;
        int oldLeft = left;
        for (int i = 1; i <= nums.length; i++) {
            if (nums[i - 1] % 2 != 0) {
                pre[i] = pre[i-1] + 1;
            }else {
                pre[i] = pre[i - 1];
            }
            
            if (pre[i] - pre[left] >= k) {
                oldLeft = left;
                while (pre[i] - pre[left] >= k) {
                    left++;
                }
            }
            
            count += (left - oldLeft);
        }
    
        
        return count;
    }*/
    
    
    
    public static void main(String[] args) {
//        System.out.println(new NumberOfSubarrays().numberOfSubarrays(new int[]{1,1,2,1,1}, 3));
        System.out.println(new NumberOfSubarrays().numberOfSubarrays(new int[]{2,2,2,1,2,2,1,2,2,2}, 2));
        System.out.println(new NumberOfSubarrays().numberOfSubarrays(new int[]{2,4,6}, 1));
    }
    
    
}
