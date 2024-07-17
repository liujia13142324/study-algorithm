package com.lj.problem.leetcode._2;

import java.util.Arrays;

/**
 * “下一个排列” 的定义是：给定数字序列的字典序中下一个更大的排列。如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 *
 * 我们可以将该问题形式化地描述为：给定若干个数字，将其组合为一个整数。如何将这些数字重新排列，以得到下一个更大的整数。如 123 下一个更大的数为 132。如果没有更大的整数，则输出最小的整数。
 *
 * 以 1,2,3,4,5,6 为例，其排列依次为：
 *
 * 123456
 * 123465
 * 123546
 * ...
 * 654321
 *
 * 可以看到有这样的关系：123456 < 123465 < 123546 < ... < 654321。
 *
 *
 * 标准的 “下一个排列” 算法可以描述为：
 * 1、从后向前 查找第一个 相邻升序 的元素对 (i,j)，满足 A[i] < A[j]。此时 [j,end) 必然是降序
 * 2、在 [j,end) 从后向前 查找第一个满足 A[i] < A[k] 的 k。A[i]、A[k] 分别就是上文所说的「小数」、「大数」
 * 3、将 A[i] 与 A[k] 交换
 * 4、可以断定这时 [j,end) 必然是降序，逆置 [j,end)，使其升序
 * 5、如果在步骤 1 找不到符合的相邻元素对，说明当前 [begin,end) 为一个降序顺序，则直接跳到步骤 4
 *
 * 该方法支持数据重复，
 *

 */
public class NextPermutation {
    
    public int[] nextPermutation(int[] nums) {
    
        boolean find = false;
        outer:
        for (int i = nums.length - 2, j = i + 1; i >= 0; i--,j--) {
            if (nums[i] < nums[j]) {
                find = true;
                for (int k = nums.length - 1; k >= j; k--) {
                    if (nums[i] < nums[k]) {
                        int tmp = nums[i];
                        nums[i] = nums[k];
                        nums[k] = tmp;
                        // 对 [j,end) 进行升序
//                        Arrays.sort(nums,j,nums.length);
                        reverse(nums, j, nums.length - 1);
                        break outer;
                    }
                }
            }
        }
        
        if (!find && nums.length > 1) {
//            int tmp = nums[nums.length - 1];
//            nums[nums.length - 1] = nums[0];
//            nums[0] = tmp;
            // 对 [j,end) 进行升序
//            Arrays.sort(nums,1,nums.length);
            reverse(nums, 0, nums.length - 1);
        }
        
        return nums;
    }
    
    private void reverse(int[] nums, int fromIndx, int toIndx) {
        while (fromIndx < toIndx) {
            int tmp = nums[fromIndx];
            nums[fromIndx] = nums[toIndx];
            nums[toIndx] = tmp;
            fromIndx++;
            toIndx--;
        }
    }
    
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new NextPermutation().nextPermutation(new int[]{1,2,3,4,5})));
        System.out.println(Arrays.toString(new NextPermutation().nextPermutation(new int[]{1,2,5,4,3})));
        System.out.println(Arrays.toString(new NextPermutation().nextPermutation(new int[]{5,4,3,2,1})));
    }
    
    
}
