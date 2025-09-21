package com.lj.problem.leetcode._1;

import org.junit.Test;

import java.util.Arrays;

/**
 * 136. 只出现一次的数字
 * 提示
 * 给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 *
 * 你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。
 *
 * 示例 1 ：
 * 输入：nums = [2,2,1]
 * 输出：1
 *
 * 示例 2 ：
 * 输入：nums = [4,1,2,1,2]
 * 输出：4
 *
 * 示例 3 ：
 * 输入：nums = [1]
 * 输出：1
 *
 * 提示：
 *
 * 1 <= nums.length <= 3 * 104
 * -3 * 104 <= nums[i] <= 3 * 104
 * 除了某个元素只出现一次以外，其余每个元素均出现两次。
 */
public class SingleNumber {

    @Test
    public void test() {
        System.out.println(singleNumber4(new int[]{2,2,1}));
    }


    /**
     * 异或运算的交换律和结合律
     * 4⊕1⊕2⊕1⊕2
     * = 4⊕(1⊕1)⊕(2⊕2)
     * = 4⊕0⊕0
     * = 4
     * @return
     */
    public int singleNumber5(int[] nums) {
        int ans = 0;
        for (int x : nums) {
            ans ^= x;
        }
        return ans;

    }

    public int singleNumber2(int[] nums) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int num: nums) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        int[] map = new int[max - min + 1];
        for (int num: nums) {
            map[num - min]++;
        }

        for (int i = 0; i < map.length; i++) {
            if (map[i] == 1) {
                return i + min;
            }
        }

        return -1;
    }

    /**
     * 同样不如2
     * @param nums
     * @return
     */
    public int singleNumber4(int[] nums) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int sum = 0;
        for (int num: nums) {
            max = Math.max(max, num);
            min = Math.min(min, num);
            sum += num;
        }
        int[] map = new int[max - min + 1];
        for (int num: nums) {
            map[num - min]++;
            if (map[num - min] > 1) {
                sum -= num;
                sum -= num;
            }
        }
        return sum;
    }


    /**
     * 这个还不如 singleNumber2
     * @param nums
     * @return
     */
    public int singleNumber3(int[] nums) {
        int[] map = new int[60001];
        for (int num: nums) {
            map[num + 30000]++;
        }

        for (int i = 0; i < map.length; i++) {
            if (map[i] == 1) {
                return i + 30000;
            }
        }

        return -1;
    }




    public int singleNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i+=2) {
            if (nums[i] != nums[i+1]) {
                return nums[i];
            }
        }

        return nums[nums.length - 1];
    }
}
