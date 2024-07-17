package com.lj.problem.leetcode._2;

/**
 * 215. 数组中的第K个最大元素
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 *
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4], k = 2
 * 输出: 5
 * 示例 2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6], k = 4
 * 输出: 4
 *
 *
 * 提示：
 *
 * 1 <= k <= nums.length <= 105
 * -104 <= nums[i] <= 104
 */
public class FindKthLargest {
    
    
    int quickselect(int[] nums, int start, int end, int k) {
        
        if (start >= end) return nums[k];
        int l = start - 1;
        int r = end + 1;
        int base = nums[start];
        
        while (l < r) {
            // 无论 先 ++l 后 --r
            // 还是 先 --r 后 ++l
            // ==> 循环后 l == r 或者 l == r + 1
            while (nums[++l] < base);
            while (nums[--r] > base);
            if (l < r){
                int tmp = nums[l];
                nums[l] = nums[r];
                nums[r] = tmp;
            }
        }
        
        // 根据递归可知，l 位置就是 base 应该处的位置
        if (k == l) {return base;}
    
        /**
         * 为啥要用右边判断？
         * 当循环结束后
         *  其中 l 满足：[l, end]: num >= base , [start, l): num <= base，但是可能存在 start == l，导致 [start, start) 这个区间消失，
         *              只有一个[start, end]，导致递归参数不会发生变化，从而死递归
         *
         *  其中 r 满足：[start, r]: num <= base, (r, end]: num >= base，且 end != r，所以每次都能分成两个区间，所以每次递归参数都会发生变化
         *              （当 start == l --> [start, start] 和 [start + 1, end] 两个区间）
         */
        if (k <= r)
            // 当 end == r 且 k <= r 的时候，这一步可能会出现死递归，但是 end 不会等于 r
            return quickselect(nums, start, r, k);
        else
            return quickselect(nums, r+1, end, k);
    
        /*if (k >= l)
            // 当 start == l 且 k >= l 的时候，这一步可能会出现死递归，
            return quickselect(nums, l, end, k);
        else
            return quickselect(nums, start, l-1, k);*/
    }
    
    
    public int findKthLargest(int[] _nums, int k) {
        int n = _nums.length;
        return quickselect(_nums, 0, n - 1, n - k);
    }
    
    
    /*public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length-1, nums.length - k);
    }
    
    public int quickSelect(int[] nums, int start, int end, int targetPos) {
        int l = start;
        int r = end;
        int base = nums[start];
        while (l < r) {
            while (l < r && nums[r] > base) r--;
            // 跳过所有和pivot相等的元素，最坏情况下将n分割成1和n-1，而官方的写法会比较均匀地分割重复元素
            while (l < r && nums[l] <= base) l++;
            if (l < r) {
                int tmp = nums[l];
                nums[l] = nums[r];
                nums[r] = tmp;
            }
        }
    
        if (l == targetPos) {
            return base;
        }
    
        nums[start] = nums[l];
        nums[l] = base;
    
        if (targetPos < l) {
            return quickSelect(nums, start, l - 1, targetPos);
        } else {
            return quickSelect(nums, l + 1, end, targetPos);
        }
    
    }*/
    
    public static void main(String[] args) {
//        System.out.println(new FindKthLargest().findKthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 4));
//        System.out.println(new FindKthLargest().findKthLargest(new int[]{5,2,4,1,3,6,5}, 4));
//        System.out.println(new FindKthLargest().findKthLargest(new int[]{3,2,1,5,6,4}, 2));
        System.out.println(new FindKthLargest().findKthLargest(new int[]{9,8,7,6,5,4,3,2,1}, 4));
        System.out.println(Math.sqrt(18) % 1 > 0);
        System.out.println(Math.sqrt(8));
    }
    
}
