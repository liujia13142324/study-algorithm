package com.lj.problem.leetcode._3;

import org.junit.Test;

import java.util.Arrays;

/**
 * 1770. 执行乘法运算的最大分数
 * 提示
 * 给你两个长度分别 n 和 m 的整数数组 nums 和 multipliers ，其中 n >= m ，数组下标 从 1 开始 计数。
 * 初始时，你的分数为 0 。你需要执行恰好 m 步操作。在第 i 步操作（从 1 开始 计数）中，需要：
 * 选择数组 nums 开头处或者末尾处 的整数 x 。
 * 你获得 multipliers[i] * x 分，并累加到你的分数中。
 * 将 x 从数组 nums 中移除。
 * 在执行 m 步操作后，返回 最大 分数。
 *
 * 示例 1：
 * 输入：nums = [1,2,3], multipliers = [3,2,1]
 * 输出：14
 * 解释：一种最优解决方案如下：
 * - 选择末尾处的整数 3 ，[1,2,3] ，得 3 * 3 = 9 分，累加到分数中。
 * - 选择末尾处的整数 2 ，[1,2] ，得 2 * 2 = 4 分，累加到分数中。
 * - 选择末尾处的整数 1 ，[1] ，得 1 * 1 = 1 分，累加到分数中。
 * 总分数为 9 + 4 + 1 = 14 。
 *
 * 示例 2：
 * 输入：nums = [-5,-3,-3,-2,7,1], multipliers = [-10,-5,3,4,6]
 * 输出：102
 * 解释：一种最优解决方案如下：
 * - 选择开头处的整数 -5 ，[-5,-3,-3,-2,7,1] ，得 -5 * -10 = 50 分，累加到分数中。
 * - 选择开头处的整数 -3 ，[-3,-3,-2,7,1] ，得 -3 * -5 = 15 分，累加到分数中。
 * - 选择开头处的整数 -3 ，[-3,-2,7,1] ，得 -3 * 3 = -9 分，累加到分数中。
 * - 选择末尾处的整数 1 ，[-2,7,1] ，得 1 * 4 = 4 分，累加到分数中。
 * - 选择末尾处的整数 7 ，[-2,7] ，得 7 * 6 = 42 分，累加到分数中。
 * 总分数为 50 + 15 - 9 + 4 + 42 = 102 。
 *
 * 提示：
 * n == nums.length
 * m == multipliers.length
 * 1 <= m <= 103
 * m <= n <= 105
 * -1000 <= nums[i], multipliers[i] <= 1000
 */
public class MaximumScore {

    @Test
    public void test() {
        System.out.println(maximumScore3(new int[]{1,2,3}, new int[]{3,2,1}));
        System.out.println(maximumScore3(
                new int[]{830,388,289,14,-221,610,163,444,-750,-217,-235,-882,746,-907,67,-71,-990,400,-54,-114,-100,233,667,612,-812,-471,-639,-306,-95,524,-198,-520,-652,704,-697,-43,-539,-105,-160,838,499,-109,-977,975,658,-250,593,154,-777,496,747,307,-340,403,-749,-185,721,327,-851,-112,770,-14,754,-242,-220,-776,-66,348,-707,-693,9,-354,741,129,234,638,404,-408,-30,422,-234,818,-627,706,-752,-699,-773,-786,241,432,273,272,-57,878,-149,-547,491,519,870,700,476,-99,902,-878,-237,-549,445,-372,277,-486,872,-569,-687,339,653,-564,862,898,-962,306,668,-143,344,-312,108,-536,-453,-52,627,-225,-28,403,-422,367,133,970,-575,353,347,275,-876,337,594,441,-498,-875,-934,133}
                , new int[]{-844,-363,797,480,141,733,-935,842,160,-928,787,-895,-742,-963,889,-713,-264,-400,117,163,68,-286,-810,-365,180,-690,-558,-409,290,51,-272,-454,-110,850,578,131,-913,675,817,380,410,860,-441,56,-80,-650,-474,858,269})
        );

        System.out.println(maximumScore(
                new int[]{830,388,289,14,-221,610,163,444,-750,-217,-235,-882,746,-907,67,-71,-990,400,-54,-114,-100,233,667,612,-812,-471,-639,-306,-95,524,-198,-520,-652,704,-697,-43,-539,-105,-160,838,499,-109,-977,975,658,-250,593,154,-777,496,747,307,-340,403,-749,-185,721,327,-851,-112,770,-14,754,-242,-220,-776,-66,348,-707,-693,9,-354,741,129,234,638,404,-408,-30,422,-234,818,-627,706,-752,-699,-773,-786,241,432,273,272,-57,878,-149,-547,491,519,870,700,476,-99,902,-878,-237,-549,445,-372,277,-486,872,-569,-687,339,653,-564,862,898,-962,306,668,-143,344,-312,108,-536,-453,-52,627,-225,-28,403,-422,367,133,970,-575,353,347,275,-876,337,594,441,-498,-875,-934,133}
                , new int[]{-844,-363,797,480,141,733,-935,842,160,-928,787,-895,-742,-963,889,-713,-264,-400,117,163,68,-286,-810,-365,180,-690,-558,-409,290,51,-272,-454,-110,850,578,131,-913,675,817,380,410,860,-441,56,-80,-650,-474,858,269})
        );
    }

    public int maximumScore3(int[] nums, int[] multipliers) {
        int[] dp = new int[nums.length + 1];
        for (int k = multipliers.length - 1; k >= 0; k--) {
            for (int j = nums.length; j >= nums.length - k; j--) {
                dp[j] = Math.max(dp[j - 1] + nums[j - 1] * multipliers[k], dp[j] + nums[j + k - nums.length] * multipliers[k]);
            }
        }
        return dp[nums.length];
    }


    public int maximumScore2(int[] nums, int[] multipliers) {
        int[] dp = new int[nums.length + 1];
        for (int i = 0; i < multipliers.length; i++) {
            int x = multipliers.length - 1 - i;
            int k = nums.length - (dp.length - multipliers.length + i);
            for (int j = dp.length - 1; j >= dp.length - multipliers.length + i; j--) {
                dp[j] = Math.max(dp[j] + nums[x--] * multipliers[k], dp[j - 1] + nums[j - 1] * multipliers[k]);
            }
        }
        return dp[nums.length];
    }


    /**
     * 还是超时
     * @param nums
     * @param multipliers
     * @return
     */
    public int maximumScore_(int[] nums, int[] multipliers) {
        int[][] dp = new int[nums.length][multipliers.length + 1];
        int[] tmp = new int[nums.length];

        for (int i = nums.length - 1; i >= 0; i--) {
            int[] prev = new int[nums.length];
            for (int k = multipliers.length - 1; k >= 0; k--) {
                dp[i][k] = nums[i] * multipliers[k];
                for (int j = nums.length - 1; j > i; j--) {
                    tmp[j] = dp[j][k];
                    dp[j][k] = Math.max(prev[j] + nums[i] * multipliers[k], dp[j - 1][k + 1] + nums[j] * multipliers[k]);
                }
                System.arraycopy(tmp, i + 1, prev, i + 1, nums.length - i - 1);
            }
        }

        return dp[nums.length - 1][0];
    }

    /**
     * 内存超了
     * @param nums
     * @param multipliers
     * @return
     */
    public int maximumScore(int[] nums, int[] multipliers) {
        int[][][] cache = new int[nums.length][nums.length][multipliers.length];
        for (int[][] c1: cache) {
            for (int[] c2: c1) {
                Arrays.fill(c2, Integer.MIN_VALUE);
            }
        }
        return dfs(0, nums.length - 1, 0, nums, multipliers, cache);
    }

    private int dfs(int i, int j, int k, int[] nums, int[] m, int[][][] cache) {
        if (k == m.length) {
            return 0;
        }
        if (cache[i][j][k] != Integer.MIN_VALUE) {
            return cache[i][j][k];
        }
        return cache[i][j][k] = Math.max(
                dfs(i + 1, j, k + 1, nums, m, cache) + nums[i] * m[k]
                , dfs(i, j - 1, k + 1, nums, m, cache) + nums[j] * m[k]
        );
    }

}
