package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.Arrays;

/**
 * 740. 删除并获得点数
 * 中等
 * 提示
 * 给你一个整数数组 nums ，你可以对它进行一些操作。
 *
 * 每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除 所有 等于 nums[i] - 1 和 nums[i] + 1 的元素。
 *
 * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
 *
 *
 *
 * 示例 1：
 * 输入：nums = [3,4,2]
 * 输出：6
 * 解释：
 * 删除 4 获得 4 个点数，因此 3 也被删除。
 * 之后，删除 2 获得 2 个点数。总共获得 6 个点数。
 *
 *
 * 示例 2：
 * 输入：nums = [2,2,3,3,3,4]
 * 输出：9
 * 解释：
 * 删除 3 获得 3 个点数，接着要删除两个 2 和 4 。
 * 之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
 * 总共获得 9 个点数。
 *
 * 提示：
 * 1 <= nums.length <= 2 * 104
 * 1 <= nums[i] <= 104
 */
public class DeleteAndEarn {


    @Test
    public void test() {
        System.out.println(deleteAndEarn2(new int[]{8,10,4,9,1,3,5,9,4,10}));
    }


    public int deleteAndEarn2__(int[] nums) {
        int max = 0;
        for (int n: nums) {
            max = Math.max(max, n);
        }

        int[] help = new int[max + 1];
        for (int n: nums) {
            help[n] += n;
        }
        int i1 = 0;
        int i2 = help[1];
        int ans = i2;
        for (int i = 2; i <= max; i++) {
            ans = Math.max(help[i] + i1, i2);
            i1 = i2;
            i2 = ans;
        }
        return ans;
    }


    public int deleteAndEarn2_(int[] nums) {
        int max = 0;
        for (int n: nums) {
            max = Math.max(max, n);
        }

        int[] help = new int[max + 1];
        for (int n: nums) {
            help[n] += n;
        }
        int[] dfs = new int[max + 1];
        dfs[1] = help[1];
        for (int i = 2; i <= max; i++) {
            dfs[i] = Math.max(help[i] + dfs[i - 2], dfs[i - 1]);
        }
        return dfs[max];
    }


    public int deleteAndEarn2(int[] nums) {
        int max = 0;
        for (int n: nums) {
            max = Math.max(max, n);
        }

        int[] help = new int[max + 1];
        for (int n: nums) {
            help[n] += n;
        }
        int[]cache = new int[max + 1];
        Arrays.fill(cache, -1);
        return dfs(help.length - 1, help, cache);
    }

    private int dfs(int i, int[] help, int[] cache) {
        if (i <= 0) return 0;
        if (cache[i] != -1) return cache[i];
        cache[i] = Math.max(help[i] + dfs(i - 2, help, cache), dfs(i - 1 ,help, cache));
        return cache[i];
    }


    public int deleteAndEarn(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        int[] cnt = new int[10002];
        int sum[] = new int[2];
        dfs(0, nums, sum, visited, cnt);
        return sum[1];
    }

    private void dfs(int i, int[] nums, int[] sum, boolean[] visited, int[] cnt) {
        if (i == nums.length) {
            if (sum[0] > sum[1]) sum[1] = sum[0];
            return;
        }

        for (int j = 0; j < nums.length; j++) {
            if (!visited[j]) {
                visited[j] = true;
                if (cnt[nums[j]] == 0) {
                    sum[0] += nums[j];
                    cnt[nums[j]+1]++;
                    cnt[nums[j]-1]++;
                    dfs(i + 1, nums, sum, visited, cnt);
                    sum[0] -= nums[j];
                    cnt[nums[j]+1]--;
                    cnt[nums[j]-1]--;
                }else {
                    dfs(i + 1, nums, sum, visited, cnt);
                }
                visited[j] = false;
            }
        }
    }


}
