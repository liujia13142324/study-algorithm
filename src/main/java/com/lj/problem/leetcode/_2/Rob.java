package com.lj.problem.leetcode._2;

import cn.hutool.core.lang.hash.Hash;
import org.junit.Test;

import java.util.Arrays;

/**
 * 198. 打家劫舍11
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 *
 *
 * 示例 1：
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 *
 * 示例 2：
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 *
 * 提示：
 *
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 */
public class Rob {

    @Test
    public void test() {
        System.out.println(rob(new int[]{1,2,3,1}));
        System.out.println(rob(new int[]{2,7,9,3,1}));
    }


    public int rob(int[] nums) {
        /*int[] memory = new int[nums.length];
        Arrays.fill(memory, -1);
        return ro2(nums, nums.length - 1, memory);*/

        return ro5(nums);
    }

    public int ro5(int[] nums) {
        int f0 = 0, f1 = 0, f2 = 0;
        for (int i = 0; i < nums.length; i++) {
            f2 = Math.max(nums[i] + f0, f1);
            f0 = f1;
            f1 = f2;
        }
        return f2;
    }

    public int ro4(int[] nums) {
        int[] f = new int[nums.length + 2];
        for (int i = 0; i < nums.length; i++) {
            f[i+2] = Math.max(nums[i] + f[i], f[i+1]);
        }

        return f[f.length - 1];
    }

    public int ro3(int[] nums) {
        if (nums.length < 2) return nums[0];
        if (nums.length < 3) return Math.max(nums[0], nums[1]);
        int[] f = new int[nums.length];
        f[0] = nums[0];
        f[1] = Math.max(nums[1], nums[0]);
        for (int i = 2; i < nums.length; i++) {
            f[i] = Math.max(nums[i] + f[i-2], f[i-1]);
        }

        return f[f.length - 1];
    }

    public int ro2(int[] nums, int i, int[] memory){
        if (i < 0) {
            return 0;
        }

        if (memory[i] != -1) {
            return memory[i];
        }

        memory[i] = Math.max(nums[i] + ro2(nums, i-2, memory), ro2(nums, i-1, memory));

        return memory[i];
    }

    public int rob(int[] nums, int i, int[] memory){
        if (i < 0) {
            return 0;
        }
        int s1 = 0, s2 = 0;
        if (i - 2 >= 0) {
            if (memory[i - 2] == -1) {
                s1 = rob(nums, i - 2, memory);
                memory[i - 2] = s1;
            }else {
                s1 = memory[i - 2];
            }
        }

        if (i - 1 >= 0) {
            if (memory[i - 1] == -1) {
                s2 = rob(nums, i - 1, memory);
                memory[i - 1] = s2;
            }else {
                s2 = memory[i - 1];
            }
        }

        return Math.max(nums[i]+s1, s2);
    }
}
