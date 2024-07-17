package com.lj.problem.leetcode._2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * LCR 083. 全排列
 */
public class Permute {
    
    
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
