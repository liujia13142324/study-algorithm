package com.lj.problem.leetcode._1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 多数元素
 *
 * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 */
public class MajorityElement {
    
    
    /*public int majorityElement(int[] nums) {
        
        if (nums.length <= 2) {
            return nums[0];
        }
        
        Map<Integer, Integer> map = new HashMap();
        int maxCount = 0;
        int maxCountVal = 0;
        for (int i : nums) {
            if (map.get(i) == null) {
                map.put(i, 1);
            }else {
                int tmp = map.get(i) + 1;
                if (maxCount < tmp) {
                    maxCount = tmp;
                    maxCountVal = i;
                }
                map.put(i, tmp);
            }
        }
        
        return maxCountVal;
        
    }*/
    
    // 排序法
    /*public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }*/
    
    
    // 投票法
    public int majorityElement(int[] nums) {
        int cadidate = nums[0];
        int countCadidate = 0;
        for (int i : nums) {
            if (countCadidate == 0 ) {
                cadidate = i;
                countCadidate++;
            }else if (i == cadidate){
                countCadidate++;
            }else {
                countCadidate--;
            }
        }
        return cadidate;
    }
}
