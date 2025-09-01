package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 18.四数之和
 * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：
 *
 * 0 <= a, b, c, d < n
 * a、b、c 和 d 互不相同
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * 你可以按 任意顺序 返回答案 。
 *
 *
 * 示例 1：
 * 输入：nums = [1,0,-1,0,-2,2], target = 0
 * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 *
 * 示例 2：
 * 输入：nums = [2,2,2,2,2], target = 8
 * 输出：[[2,2,2,2]]
 *
 *
 * 提示：
 * 1 <= nums.length <= 200
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 */
public class FourSum {

    @Test
    public void test() {
        // -2 -1 0 0 1 2
        // [[-2, -1, 1, 2], [-2, 0, 0, 2], [-1, 0, 0, 1]]
//        System.out.println(fourSum(new int[]{1,0,-1,0,-2,2}, 0));
//        System.out.println(fourSum(new int[]{2,2,2,2,2}, 8));
        System.out.println(fourSum(new int[]{1000000000,1000000000,1000000000,1000000000}, -294967296));
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int preI = Integer.MAX_VALUE;
        long target2 = target;
        for (int i = 0; i < nums.length - 3; i++) {
            if (preI == nums[i]) {
                continue;
            }
            preI = nums[i];
            int preJ = Integer.MAX_VALUE;
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (preJ == nums[j]) {
                    continue;
                }
                preJ = nums[j];
                int l = j + 1;
                int r = nums.length - 1;
                long newTarget = target2 - nums[i] - nums[j];
                while (l < r) {
                    long tmp = nums[l] + nums[r];
                    if (tmp > newTarget) {
                        r--;
                    }else if (tmp < newTarget) {
                        l++;
                    }else{
                        result.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                        while (nums[l] == nums[++l] && l < r);
                        while (nums[r] == nums[--r] && l < r);
                    }
                }
            }
        }
        return result;
    }
}
