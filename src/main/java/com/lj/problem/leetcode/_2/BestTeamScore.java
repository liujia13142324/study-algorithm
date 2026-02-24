package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 1626. 无矛盾的最佳球队
 * 提示
 * 假设你是球队的经理。对于即将到来的锦标赛，你想组合一支总体得分最高的球队。球队的得分是球队中所有球员的分数 总和 。
 * 然而，球队中的矛盾会限制球员的发挥，所以必须选出一支 没有矛盾 的球队。如果一名年龄较小球员的分数 严格大于 一名年龄较大的球员，则存在矛盾。同龄球员之间不会发生矛盾。
 * 给你两个列表 scores 和 ages，其中每组 scores[i] 和 ages[i] 表示第 i 名球员的分数和年龄。请你返回 所有可能的无矛盾球队中得分最高那支的分数 。
 *
 * 示例 1：
 * 输入：scores = [1,3,5,10,15], ages = [1,2,3,4,5]
 * 输出：34
 * 解释：你可以选中所有球员。
 *
 * 示例 2：
 * 输入：scores = [4,5,6,5], ages = [2,1,2,1]
 * 输出：16
 * 解释：最佳的选择是后 3 名球员。注意，你可以选中多个同龄球员。

 * 示例 3：
 * 输入：scores = [1,2,3,5], ages = [8,9,10,1]
 * 输出：6
 * 解释：最佳的选择是前 3 名球员。
 *
 * 提示：
 *
 * 1 <= scores.length, ages.length <= 1000
 * scores.length == ages.length
 * 1 <= scores[i] <= 106
 * 1 <= ages[i] <= 1000
 */
public class BestTeamScore {

    @Test
    public void test() {
//        System.out.println(bestTeamScore(new int[]{1,3,5,10,15}, new int[]{1,2,3,4,5}));
//        System.out.println(bestTeamScore(new int[]{4,5,6,5}, new int[]{2,1,2,1}));
//        System.out.println(bestTeamScore(new int[]{1,3,7,3,2,4,10,7,5}, new int[]{4,5,2,1,1,2,4,1,4}));
        System.out.println(bestTeamScore(new int[]{596,277,897,622,500,299,34,536,797,32,264,948,645,537,83,589,770}, new int[]{18,52,60,79,72,28,81,33,96,15,18,5,17,96,57,72,72}));
    }



    /**
     * 查找和最大的递增序列
     * @param scores
     * @param ages
     * @return
     */
    public int bestTeamScore(int[] scores, int[] ages) {
        int[][] tmp = new int[scores.length][2];
        for (int i = 0; i < scores.length; i++) {
            tmp[i][0] = scores[i];
            tmp[i][1] = ages[i];
        }
        Arrays.sort(tmp, (e1,e2)->{
            if (e1[0] != e2[0]) {
                return e1[0] - e2[0];
            }
            return e1[1] - e2[1];
        });

        if (tmp[0][0] == tmp[tmp.length - 1][0]) {
            return tmp[0][0] * tmp.length;
        }

        int ans = 0;
        int[] dp = new int[scores.length];
        for (int i = 0; i < dp.length; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (tmp[j][1] <= tmp[i][1]) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + tmp[i][0];
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }

    /**
     * 查找和最大的递增序列 --> 根据值域
     * @param scores
     * @param ages
     * @return
     */
    public int bestTeamScore2(int[] scores, int[] ages) {
        int[][] tmp = new int[scores.length][2];
        int maxAge = 0;
        for (int i = 0; i < scores.length; i++) {
            tmp[i][0] = scores[i];
            tmp[i][1] = ages[i];
            maxAge = Math.max(maxAge, ages[i]);
        }
        Arrays.sort(tmp, (e1,e2)->{
            if (e1[0] != e2[0]) {
                return e1[0] - e2[0];
            }
            return e1[1] - e2[1];
        });

        if (tmp[0][0] == tmp[tmp.length - 1][0]) {
            return tmp[0][0] * tmp.length;
        }

        int[] dp = new int[maxAge + 1];
        int maxSum = 0;
        for (int i = 0; i < tmp.length; i++) {
            for (int j = 1; j <= tmp[i][1]; j++) {
                dp[tmp[i][1]] = Math.max(dp[tmp[i][1]], dp[j]);
            }
            dp[tmp[i][1]] += tmp[i][0];
            maxSum = Math.max(maxSum, dp[tmp[i][1]]);
        }
        return maxSum;
    }


    public int bestTeamScore_(int[] scores, int[] ages) {
        int n = scores.length, ans = 0;
        Integer[] ids = new Integer[n];
        for (int i = 0; i < n; ++i)
            ids[i] = i;
        Arrays.sort(ids, (i, j) -> scores[i] != scores[j] ? scores[i] - scores[j] : ages[i] - ages[j]);

        int[] f = new int[n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j)
                if (ages[ids[j]] <= ages[ids[i]])
                    f[i] = Math.max(f[i], f[j]);
            f[i] += scores[ids[i]];
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }

}
