package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * LCR 083. 全排列
 *
 * 46. 全排列
 * 相关企业
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * 示例 2：
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 *
 * 示例 3：
 * 输入：nums = [1]
 * 输出：[[1]]
 *
 * 提示：
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数 互不相同
 */
public class Permute {

    @Test
    public void test() {
        System.out.println(permute2(new int[]{1,2,3}));
        System.out.println(permute2(new int[]{1,2,3,4}));
    }


    /**
     * 回溯
     */
    public List<List<Integer>> permute(int[] nums) {
        int[] output = new int[nums.length];
        boolean[] visited = new boolean[nums.length];
        List<List<Integer>> res = new ArrayList<>();
        backtrack(0, nums, output, visited, res);
        return res;
    }

    private void backtrack(int curr, int[] nums, int[] output, boolean[] visited, List<List<Integer>> res) {
        if (curr == output.length) {
            addNewList(res,output);
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                output[curr] = nums[i];
                visited[i] = true;
                backtrack(curr + 1, nums, output, visited, res);
                visited[i] = false;
            }
        }
    }

    private void addNewList(List<List<Integer>> res, int[] output) {
        List list = new ArrayList();
        res.add(list);
        for (int i : output) {
            list.add(i);
        }
    }


    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(nums, 0, ans, new ArrayList<>());
        return ans;
    }

    private void dfs(int[] nums, int startIdx, List<List<Integer>> ans, List<Integer> pre) {
        if (startIdx == nums.length - 1) {
            pre.add(nums[startIdx]);
            ans.add(pre);
            return;
        }
        for (int i = startIdx; i < nums.length; i++) {
            swap(nums, startIdx, i);
            ArrayList<Integer> list = new ArrayList<>(pre);
            list.add(nums[startIdx]);
            dfs(nums, startIdx + 1, ans, list);
            swap(nums, i, startIdx);
        }
    }

    private void swap(int[] nums, int startIdx, int i) {
        int tmp = nums[i];
        nums[i] = nums[startIdx];
        nums[startIdx] = tmp;
    }



    
    // 递归方式
    /*public List<List<Integer>> permute(int[] nums) {
        
        if (nums.length == 1) {
            return Arrays.asList(new ArrayList(Arrays.asList(nums[0])));
        }
        
        List<List<Integer>> list = new ArrayList();
        
        int tmp[] = new int[nums.length-1];
        for (int i = 0; i < nums.length; i++) {
            int idx = 0;
            for (int j = 0; j < nums.length; j++) {
                if (nums[i] != nums[j]) {
                    tmp[idx++] = nums[j];
                }
            }
            for (List<Integer> l : permute(tmp)) {
                l.add(nums[i]);
                list.add(l);
            }
        }
        
        return list;
    }*/
    
    public static void main(String[] args) {
        List<List<Integer>> permute = new Permute().permute(new int[]{1, 2, 3, 4});
        System.out.println(permute.size() + "-->" + permute);
    }
}
