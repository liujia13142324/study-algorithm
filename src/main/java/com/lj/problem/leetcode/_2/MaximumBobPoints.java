package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.Arrays;

/**
 * 2212. 射箭比赛中的最大得分
 * 提示
 * Alice 和 Bob 是一场射箭比赛中的对手。比赛规则如下：
 * Alice 先射 numArrows 支箭，然后 Bob 也射 numArrows 支箭。
 * 分数按下述规则计算：
 * 箭靶有若干整数计分区域，范围从 0 到 11 （含 0 和 11）。
 * 箭靶上每个区域都对应一个得分 k（范围是 0 到 11），Alice 和 Bob 分别在得分 k 区域射中 ak 和 bk 支箭。如果 ak >= bk ，那么 Alice 得 k 分。如果 ak < bk ，则 Bob 得 k 分
 * 如果 ak == bk == 0 ，那么无人得到 k 分。
 * 例如，Alice 和 Bob 都向计分为 11 的区域射 2 支箭，那么 Alice 得 11 分。如果 Alice 向计分为 11 的区域射 0 支箭，但 Bob 向同一个区域射 2 支箭，那么 Bob 得 11 分。
 *
 * 给你整数 numArrows 和一个长度为 12 的整数数组 aliceArrows ，该数组表示 Alice 射中 0 到 11 每个计分区域的箭数量。现在，Bob 想要尽可能 最大化 他所能获得的总分。
 *
 * 返回数组 bobArrows ，该数组表示 Bob 射中 0 到 11 每个 计分区域的箭数量。且 bobArrows 的总和应当等于 numArrows 。
 *
 * 如果存在多种方法都可以使 Bob 获得最大总分，返回其中 任意一种 即可。
 *
 *
 *
 * 示例 1：
 * 输入：numArrows = 9, aliceArrows = [1,1,0,1,0,0,2,1,0,1,2,0]
 * 输出：[0,0,0,0,1,1,0,0,1,2,3,1]
 * 解释：上表显示了比赛得分情况。
 * Bob 获得总分 4 + 5 + 8 + 9 + 10 + 11 = 47 。
 * 可以证明 Bob 无法获得比 47 更高的分数。
 *
 * 示例 2：
 * 输入：numArrows = 3, aliceArrows = [0,0,1,0,0,0,0,0,0,0,0,2]
 * 输出：[0,0,0,0,0,0,0,0,1,1,1,0]
 * 解释：上表显示了比赛得分情况。
 * Bob 获得总分 8 + 9 + 10 = 27 。
 * 可以证明 Bob 无法获得比 27 更高的分数。
 *
 *
 * 提示：
 *
 * 1 <= numArrows <= 105
 * aliceArrows.length == bobArrows.length == 12
 * 0 <= aliceArrows[i], bobArrows[i] <= numArrows
 * sum(aliceArrows[i]) == numArrows
 */
public class MaximumBobPoints {

    @Test
    public void test() {
        System.out.println(Arrays.toString(maximumBobPoints(9, new int[]{1,1,0,1,0,0,2,1,0,1,2,0})));
    }

    public int[] maximumBobPoints(int numArrows, int[] aliceArrows) {
        int[] ans = new int[12];
        int[] path = new int[12];
        dfs(ans.length - 1, numArrows, aliceArrows, path, ans, new int[]{0, 0});
        return ans;
    }

    private void dfs(int i, int remainShots, int[] aliceArrows, int[] path, int[] ans, int[] sums) {
        if (i == 0 || remainShots == 0) {
            if (sums[0] > sums[1]) {
                sums[1] = sums[0];
                path[0] = remainShots;
                System.arraycopy(path, 0, ans, 0, ans.length);
            }
            return;
        }

        // 选
        if (remainShots > aliceArrows[i]) {
            path[i] = aliceArrows[i] + 1;
            sums[0] += i;
            dfs(i-1, remainShots - path[i], aliceArrows, path, ans, sums);
            path[i] = 0;
            sums[0] -= i;
        }

        // 不选
        dfs(i-1, remainShots, aliceArrows, path, ans, sums);
    }


    /**
     * 二进制枚举，12 个靶子，共有 2^12 中可能，即 0 ～ 1023
     * // TODO 这个可以剪枝吗
     * 000000000000
     * 000000000001
     * 000000000010
     * 000000000011
     * ...
     */
    public int[] maximumBobPoints2(int numArrows, int[] aliceArrows) {
        int[] ans = null;
        int maxScore = 0;
        for (int i = 0, len = 1 << aliceArrows.length; i < len; i++) {
            int[] tmp = new int[aliceArrows.length];
            int remains = numArrows;
            int score = 0;
            for (int j = 0; j < aliceArrows.length; j++) {
                // 区域获胜
                if (((i >>> j) & 1) == 1 && remains > aliceArrows[j] ) {
                    tmp[j] = aliceArrows[j] + 1;
                    remains -= aliceArrows[j] + 1;
                    score += j;
                    if (remains == 0) {
                        break;
                    }
                }
            }
            if (score > maxScore) {
                tmp[0] += remains;
                maxScore = score;
                ans = tmp;
            }
        }

        return ans;
    }

    @Test
    public void testBinary() {
        int k = 10;
        k -= 5 + 1;
        System.out.println(k);
        int target = 3;
        for (int i = 0, len = 1 << target; i < len; i++) {
            int[] result = new int[target];
            for (int j = 0; j < target; j++) {
                result[j] = (i >>> j) & 1;
            }
            System.out.println(Arrays.toString(result));
        }
    }
}
