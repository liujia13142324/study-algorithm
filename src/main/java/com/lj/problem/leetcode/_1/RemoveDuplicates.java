package com.lj.problem.leetcode._1;


import java.util.Arrays;

/**
 * 给你一个 非严格递增排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。然后返回 nums 中唯一元素的个数。
 *
 * 考虑 nums 的唯一元素的数量为 k ，你需要做以下事情确保你的题解可以被通过：
 *
 * 更改数组 nums ，使 nums 的前 k 个元素包含唯一元素，并按照它们最初在 nums 中出现的顺序排列。nums 的其余元素与 nums 的大小不重要。
 * 返回 k 。
 * 判题标准:
 *
 * 系统会用下面的代码来测试你的题解:
 *
 * int[] nums = [...]; // 输入数组
 * int[] expectedNums = [...]; // 长度正确的期望答案
 *
 * int k = removeDuplicates(nums); // 调用
 *
 * assert k == expectedNums.length;
 * for (int i = 0; i < k; i++) {
 *     assert nums[i] == expectedNums[i];
 * }
 * 如果所有断言都通过，那么您的题解将被 通过。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,2]
 * 输出：2, nums = [1,2,_]
 * 解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
 * 示例 2：
 *
 * 输入：nums = [0,0,1,1,1,2,2,3,3,4]
 * 输出：5, nums = [0,1,2,3,4]
 * 解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 3 * 10^4
 * -10^4 <= nums[i] <= 10^4
 * nums 已按 非严格递增 排列
 */
public class RemoveDuplicates {
    
    
    public int removeDuplicates(int[] nums) {
        
        int p = 0;
        
        for (int q = 1; q < nums.length; q++) {
            if (nums[q] != nums[p]) {
                nums[p + 1] = nums[q];
                p++;
            }
        }
        
        
//        System.out.println(Arrays.toString(nums) + "--->" + (p+1));
    
        return p + 1;
        
    }
    
    
    /**
     * 定位复制
     * @return
     */
    /*public int removeDuplicates(int[] nums) {
        int [][] marks = new int[nums.length / 2][2];
        int size = 0;
        int pre = nums[0];
        int preIdx = 0;
        int len = nums.length;
        int moved;
        int i = 1;
        
        for (; i < nums.length; i ++) {
            if (nums[i] != pre) {
                moved = i - preIdx;
                if (moved > 1) {
                    marks[size][0] = i;
                    marks[size++][1] = moved - 1;
                }
                pre = nums[i];
                preIdx = i;
            }
        }
        
        if (preIdx != i) {
            moved = i - preIdx;
            if (moved > 1) {
                marks[size][0] = i;
                marks[size++][1] = moved - 1;
            }
//            pre = nums[i];
//            preIdx = i;
        }
        
        int[] pos;
        if (size > 0) {
            for (i = size - 1; i >=0; i--) {
                pos = marks[i];
                moved = len - pos[0];
                System.arraycopy(nums, pos[0], nums, pos[0] - pos[1], moved);
                len -= pos[1];
            }
        }
    
//        System.out.println(Arrays.toString(nums) + "--->" + len);
    
        return len;
        
    }*/
    
    // 直接复制
    /*public int removeDuplicates(int[] nums) {
        int move=0;
        int pre = nums[0];
        int k = nums.length;
        int i = 1;
        
        for (; i < k; i++) {
            if (pre == nums[i]) {
                move++;
            }else {
                System.arraycopy(nums, i, nums, i - move, k - i);
                i -= move;
                pre = nums[i];
                k = k - move;
                move = 0;
            }
        }
        
        if (move > 0) {
            k = k - move;
        }
    
        return k;
    }*/
    
    public static void main(String[] args) {
        new RemoveDuplicates().removeDuplicates(new int[]{1,1,2});
        new RemoveDuplicates().removeDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4});
        new RemoveDuplicates().removeDuplicates(new int[]{1, 1});
    }
    
}
