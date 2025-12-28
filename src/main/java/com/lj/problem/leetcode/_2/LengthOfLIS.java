package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.Arrays;

/**
 * 300.最长递增子序列
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 *
 * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的
 * 子序列。
 *
 *
 * 示例 1：
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 *
 * 示例 2：
 * 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 *
 * 示例 3：
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 *
 * 提示：
 * 1 <= nums.length <= 2500
 * -104 <= nums[i] <= 104
 *
 * 进阶：
 * 你能将算法的时间复杂度降低到 O(n log(n)) 吗?
 */
public class LengthOfLIS {

    @Test
    public void test() {
//        System.out.println(lengthOfLIS3___(new int[]{1,3,6,7,9,4,10,5,6}));
//        System.out.println(lengthOfLIS2___(new int[]{10, 9, 2, 5, 3, 7}));
        System.out.println(lengthOfLIS4(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
    }

    public int lengthOfLIS4_(int[] nums) {
        int r = 0;
        for (int num: nums) {
            int i = find(-1, r, num, nums);
            if (i == r) {
                nums[r++] = num;
            }else {
                nums[i] = num;
            }
        }
        return r;
    }

    public int lengthOfLIS4(int[] nums) {
        int[] g = new int[nums.length];
        int idx = 0;
        for (int num: nums) {
            int i = find(-1, idx, num, g);
            if (i == idx) {
                g[idx++] = num;
            }else {
                g[i] = num;
            }
        }
        return idx;
    }

    public int lengthOfLIS2____(int[] nums) {
        int max = -100000;
        int min = 100000;
        for (int num: nums) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        int[] dp = new int[max - min + 2];
        int r = max - min + 1;
        for (int i = 1; i <= nums.length; i++) {
            int tmp = nums[i - 1] - min;
            // 找到第一个 j > tmp
            for (int j = find(0,  r + 1, tmp); j <= r; j++) {
                dp[j] = Math.max(dp[j], dp[tmp] + 1);
            }
        }
        return dp[max - min + 1];
    }

    private int find(int l, int r, int tmp) {
        while (l + 1 < r) {
            int mid = (l + r) >>> 1;
            if (mid >= tmp) {
                r = mid;
            }else {
                l = mid;
            }
        }
        return r;
    }

    private int find(int l, int r, int tmp, int[] nums) {
        while (l + 1 < r) {
            int mid = (l + r) >>> 1;
            if (nums[mid] > tmp) {
                r = mid;
            }else {
                l = mid;
            }
        }
        return r;
    }

    public int lengthOfLIS3___(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public int lengthOfLIS3__(int[] nums) {
        int[] cache = new int[nums.length];
        int max = 1;
        for (int i = nums.length - 1; i > 0; i--) {
            max = Math.max(max, dfs3(i, nums, cache));
        }
        return max;
    }

    private int dfs3(int i, int[] nums, int[] cache) {
        if (cache[i] != 0) return cache[i];
        int val = 1;
        for (int j = i - 1; j >= 0; j--) {
            if (nums[j] < nums[i]) {
                val = Math.max(val, dfs3(j, nums, cache) + 1);
            }
        }
        cache[i] = val;
        return val;
    }

    public int lengthOfLIS3(int[] nums) {
        return dfs3(nums.length - 1, nums);
    }

    /**
     * 以 i 结尾的最长有序子序列的长度
     */
    private int dfs3(int i, int[] nums) {
        int val = 1;
        for (int j = i - 1; j >= 0; j--) {
            if (nums[j] < nums[i]) {
                val = Math.max(val, dfs3(j, nums) + 1);
            }
        }
        return val;
    }


    public int lengthOfLIS2___(int[] nums) {
        int max = -100000;
        int min = 100000;
        for (int num: nums) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        int[] dp = new int[max - min + 2];
        /*for (int num: nums) {
            int tmp = num - min;
            for (int j = max - min + 1; j >= 1; j-- ) {
                if (tmp < j) {
                    dp[j] = Math.max(dp[j], dp[tmp] + 1);
                }
            }
        }*/
        // 仔细看，这个也可以，因为这个 tmp < j 的逻辑能保证不会覆盖
        for (int num: nums) {
            int tmp = num - min;
            for (int j = 1; j <= max - min + 1; j++) {
                if (tmp < j) {
                    dp[j] = Math.max(dp[j], dp[tmp] + 1);
                }
            }
        }
        return dp[max - min + 1];
    }

    public int lengthOfLIS2__(int[] nums) {
        int max = -100000;
        int min = 100000;
        for (int num: nums) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        int[][] dp = new int[nums.length + 1][max - min + 2];

        for (int i = 1; i <= nums.length; i++) {
            int tmp = nums[i - 1] - min;
            for (int j = 1; j <= max - min + 1; j++) {
                if (tmp < j) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][tmp] + 1);
                }else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[nums.length][max - min + 1];
    }

    public int lengthOfLIS2_(int[] nums) {
        int max = -100000;
        int min = 100000;
        for (int num: nums) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        int[][] cache = new int[nums.length][max - min + 2];
        return dfs(nums.length - 1, max - min + 1, nums, cache, min);
    }

    private int dfs(int i, int j, int[] nums, int[][] cache, int min) {
        if (i < 0 || j == 0) return 0;

        if (cache[i][j] != 0) {
            return cache[i][j];
        }

        int val = 0;
        int tmp = nums[i] - min;
        if (tmp < j) {
            val = dfs(i - 1, tmp, nums, cache, min) + 1;
        }
        cache[i][j] = Math.max(val, dfs(i - 1, j, nums, cache, min));
        return cache[i][j];
    }

    public int lengthOfLIS2(int[] nums) {
        return dfs(nums.length - 1, nums, Integer.MAX_VALUE);
    }

    /**
     * 这个 pre 也可以考虑用上一个元素的下标代替
     * 用下标是不是就不能使用二分了？
     */
    private int dfs(int i, int[] nums, int pre) {
        if (i < 0) return 0;

        int val = 0;
        if (nums[i] < pre) {
            val = dfs(i - 1, nums, nums[i]) + 1;
        }

        return Math.max(val, dfs(i - 1, nums, pre));
    }







    public int lengthOfLIS(int[] nums) {
        int[] min = new int[2501];
        min[0] = -10000;
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            if (min[result] < nums[i]) {
                min[++result] = nums[i];
            }else {
                min[search(min, result, nums[i])] = nums[i];
            }
        }
        
        return result;
    }
    
    
    public int search(int[] min, int endIdx, int target) {
        
        int l = 1;
        while (l <= endIdx) {
            int mid = (l+endIdx) / 2;
            if (target > min[mid]) {
                l = mid + 1;
            }else if (target < min[mid]){
                endIdx = mid - 1;
            }else {
                return mid;
            }
        }
        
        return l;
    }
    
    public static void main(String[] args) {
        System.out.println(new LengthOfLIS().lengthOfLIS(new int[]{0,1,0,3,2,3}));
        System.out.println(new LengthOfLIS().lengthOfLIS(new int[]{3,3,3,3,3}));
        System.out.println(new LengthOfLIS().lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
    }
}
