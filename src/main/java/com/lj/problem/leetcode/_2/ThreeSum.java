package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
 * 你返回所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 *
 * 示例 1：
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 解释：
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
 * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
 * 注意，输出的顺序和三元组的顺序并不重要。
 *
 * 示例 2：
 * 输入：nums = [0,1,1]
 * 输出：[]
 * 解释：唯一可能的三元组和不为 0 。
 *
 * 示例 3：
 * 输入：nums = [0,0,0]
 * 输出：[[0,0,0]]
 * 解释：唯一可能的三元组和为 0 。
 *
 * 提示：
 *
 * 3 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 *
 */
public class ThreeSum {

    @Test
    public void test() {
        System.out.println(threeSum3(new int[]{-1, 0, 1, 2, -1, -4}));
        System.out.println(threeSum3(new int[]{0, 0, 0}));
        System.out.println(threeSum3(new int[]{0, 1, 1}));

        System.out.println(threeSum4(new int[]{-1, 0, 1, 2, -1, -4}));
        System.out.println(threeSum4(new int[]{0, 0, 0}));
        System.out.println(threeSum4(new int[]{0, 1, 1}));
    }

    public List<List<Integer>> threeSum3(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int pre = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length && nums[i] <= 0; i++) {
            if (nums[i] == pre) {
                continue;
            }
            pre = nums[i];
            twoSum(nums, i+1, -pre, res);
        }
        return res;
    }

    private void twoSum(int[] nums, int left, int target, List<List<Integer>> res) {
        int v;
        for (int l = left, r = nums.length - 1; l < r;) {
            v = nums[l] + nums[r];
            if (v == target){
                res.add(Arrays.asList(nums[left-1], nums[l], nums[r]));
                while (nums[l] == nums[++l] && l < r);
                while (nums[r] == nums[--r] && l < r);
                continue;
            }
            if (v > target) {
                r--;
            }else {
                l++;
            }
        }
    }

    // 速度慢很多
    public List<List<Integer>> threeSum4(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int pre = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length && nums[i] <= 0; i++) {
            if (nums[i] == pre) {
                continue;
            }
            pre = nums[i];
            twoSum2(nums, i+1, -pre, res);
        }
        return res;
    }

    // -4, -1, -1, 0, 1, 2
    private void twoSum2(int[] nums, int left, int target, List<List<Integer>> res) {
        int v;
        for (int l = left, r = nums.length - 1; l < r;) {
            v = nums[l] + nums[r];
            if (v == target){
                res.add(Arrays.asList(nums[left-1], nums[l], nums[r]));
                while (nums[l] == nums[++l] && l < r);
                while (nums[r] == nums[--r] && l < r);
                continue;
            }
            if (v > target) {
                // 第一个 num[r] <= target - num[l]
                r = lowBound(nums, l, target - nums[l] + 1) - 1;
            }else {
                // 第一个 num[l] >= target - num[r]
                l = lowBound(nums, l, target - nums[r]);
            }
        }
    }

    private int lowBound(int[] nums, int l, int target) {
        int r = nums.length - 1, mid;
        while (l <= r) {
            mid = l + (r - l) / 2;
            if (target <= nums[mid]) {
                r = mid - 1;
            }else {
                l = mid + 1;
            }
        }
        return l;
    }


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
