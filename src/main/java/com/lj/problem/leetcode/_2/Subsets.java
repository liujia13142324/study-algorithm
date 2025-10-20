package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 78. 子集
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 *
 * 示例 2：
 * 输入：nums = [0]
 * 输出：[[],[0]]
 *
 *
 * 提示：
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * nums 中的所有元素 互不相同
 */
public class Subsets {

    @Test
    public void test() {
        System.out.println(subsets(new int[]{1,2,3,4}));
    }

    //TODO 看看别人怎么做的
    public List<List<Integer>> subsets(int[] nums) {
        return dfs(nums, 0);
    }

    private List<List<Integer>> dfs(int[] nums, int idx) {
        if (idx == nums.length) {
            List<List<Integer>> ans = new ArrayList<>();
            ans.add(new ArrayList<>());
            return ans;
        }
        List<List<Integer>> ans = dfs(nums, idx + 1);
        for (int i = 0, size = ans.size(); i < size; i++) {
            List<Integer> tmp = new ArrayList<>(ans.get(i));
            tmp.add(nums[idx]);
            ans.add(tmp);
        }
        return ans;
    }
}
