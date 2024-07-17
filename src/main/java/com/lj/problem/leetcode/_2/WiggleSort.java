package com.lj.problem.leetcode._2;

import com.test.common.bean.A;

import java.util.Arrays;

/**
 * 摇摆数组
 *
 * 给你一个的整数数组 nums, 将该数组重新排序后使 nums[0] <= nums[1] >= nums[2] <= nums[3]...
 * 输入数组总是有一个有效的答案。
 *
 *
 * 示例 1:
 * 输入：nums = [3,5,2,1,6,4]
 * 输出：[3,5,1,6,2,4]
 * 解释：[1,6,2,5,3,4]也是有效的答案
 *
 * 示例 2:
 * 输入：nums = [6,6,5,6,3,8]
 * 输出：[6,6,5,6,3,8]
 *
 *
 * 提示：
 * 1 <= nums.length <= 5 * 104
 * 0 <= nums[i] <= 104
 * 输入的 nums 保证至少有一个答案。
 */
public class WiggleSort {
    
    /**
     * 1、先排序
     * 2、每个奇数位置和下个位置交换位置
     * @param nums
     */
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length-1; i+=2){
            swap(nums, i, i+1);
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    
    public static void main(String[] args) {
    }
    
}
