package com.lj.problem.leetcode._2;

import cn.hutool.core.io.IoUtil;

import java.io.InputStream;
import java.util.Arrays;

/**
 * 55. 跳跃游戏
 *
 * 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 * 示例 2：
 *
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 */
public class CanJump {
    
    
    
    public boolean canJump(int[] nums) {
        // 能跳跃的最远的位置
        int k = 0;
        // 每次更新下个节点能跳跃的最远的位置
        for (int i = 0; i < nums.length; i++) {
            // 如果 i>k，说明 i 是跳不到的位置
            if (i > k) {return false;}
            k = Math.max(k, nums[i] + i);
        }
        return true;
    }
    
    public static void main(String[] args) {
        InputStream arrays = CanJump.class.getClassLoader().getResourceAsStream("arrays");
        String s = IoUtil.readUtf8(arrays);
        int[] ints = Arrays.stream(s.split(",")).mapToInt(e -> Integer.parseInt(e.trim())).toArray();
        long startTime = System.currentTimeMillis();
        System.out.println(new CanJump().canJump(ints));
        System.out.println(System.currentTimeMillis() - startTime);
    }
}
