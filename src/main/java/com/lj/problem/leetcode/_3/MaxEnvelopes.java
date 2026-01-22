package com.lj.problem.leetcode._3;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 354. 俄罗斯套娃信封问题
 * 给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。
 *
 * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 *
 * 请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 *
 * 注意：不允许旋转信封。
 *
 *
 * 示例 1：
 * 输入：envelopes = [[5,4],[6,4],[6,7],[2,3]]
 * 输出：3
 * 解释：最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
 *
 * 示例 2：
 * 输入：envelopes = [[1,1],[1,1],[1,1]]
 * 输出：1
 *
 * 提示：
 * 1 <= envelopes.length <= 105
 * envelopes[i].length == 2
 * 1 <= wi, hi <= 105
 *
 * tmp
 */
public class MaxEnvelopes {

    @Test
    public void test() {
//        System.out.println(maxEnvelopes2(new int[][]{{5,4},{6,4},{6,7},{2,3}}));
//        System.out.println(maxEnvelopes2(new int[][]{{2,100},{3,200},{4,300},{5,500},{5,400},{5,250},{6,370},{6,360},{7,380}}));
//        System.out.println(maxEnvelopes2(new int[][]{{1,15},{7,18},{7,6},{7,100},{2,200},{17,30},{17,45},{3,5},{7,8},{3,6},{3,10},{7,20},{17,3},{17,45}}));
        System.out.println(maxEnvelopes2(new int[][]{{6,10},{11,14},{6,1},{16,14},{13,2}}));
    }

    /**
     * 85/87
     * @param envelopes
     * @return
     */
    public int maxEnvelopes2_(int[][] envelopes) {
        Arrays.sort(envelopes, Comparator.comparingInt(a -> a[0]));
        int[] cache = new int[envelopes.length];
        int max = Integer.MIN_VALUE;
        for (int i = envelopes.length - 1; i >= 0; i--) {
            max = Math.max(max, dfs2(i, envelopes, cache));
        }
        return max;
    }

    private int dfs2(int i, int[][] envelopes, int[] cache) {
        if (cache[i] != 0) {
            return cache[i];
        }
        int max = 1;
        for (int j = i - 1; j >= 0; j--) {
            if (envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1]) {
                max = Math.max(max, dfs2(j, envelopes, cache) + 1);
            }
        }
        cache[i] = max;
        return max;
    }

    public int maxEnvelopes2(int[][] envelopes) {
        Arrays.sort(envelopes, Comparator.comparingInt(a -> a[0]));
        int max = Integer.MIN_VALUE;
        for (int i = envelopes.length - 1; i >= 0; i--) {
            max = Math.max(max, dfs2(i, envelopes));
        }
        return max;
    }

    private int dfs2(int i, int[][] envelopes) {
        int max = 1;
        for (int j = i - 1; j >= 0; j--) {
            if (envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1]) {
                max = Math.max(max, dfs2(j, envelopes) + 1);
            }
        }
        return max;
    }

    private int find(int l, int r, int target, int[][] nums, int fromIdx) {
        while (l + 1 < r) {
            int mid = (l + r) >>> 1;
            if (nums[mid][fromIdx] >= target) {
                r = mid;
            }else {
                l = mid;
            }
        }
        return r;
    }

    private int find(int l, int r, int target, int[] arr) {
        while (l + 1 < r) {
            int mid = (l + r) >>> 1;
            if (arr[mid] >= target) {
                r = mid;
            }else {
                l = mid;
            }
        }
        return r;
    }

    /**
     * 想办法减枝, 依旧 85/87
     * @param envelopes
     * @return
     */
    public int maxEnvelopes_(int[][] envelopes) {
        boolean[] path = new boolean[envelopes.length];
        int[] cache = new int[envelopes.length];
        boolean[] skip = new boolean[envelopes.length];
        int max = 0;
        for (int i = 0; i < envelopes.length; i++) {
            if (skip[i]) continue;
            max = Math.max(max, dfs_(i, path, envelopes, cache, skip));
        }
        return max;
    }

    private int dfs_(int i, boolean[] path, int[][] envelopes, int[] cache, boolean[] skip) {
        if (i < 0) return 0;
        if (cache[i] != 0) return cache[i];
        int ans = 1;
        path[i] = true;
        for (int j = 0; j <envelopes.length; j++) {
            if (path[j]) {
                continue;
            }
            if (envelopes[j][0] > envelopes[i][0] && envelopes[j][1] > envelopes[i][1]) {
                ans = Math.max(ans, 1 + dfs_(j, path, envelopes, cache, skip));
                skip[j] = true;
            }
        }
        path[i] = false;
        cache[i] = ans;
        return ans;
    }


    /**
     * 加缓存好一点，可以跑到 85/87
     * @param envelopes
     * @return
     */
    public int maxEnvelopes(int[][] envelopes) {
        boolean[] path = new boolean[envelopes.length];
        int[] cache = new int[envelopes.length];
        int max = 0;
        for (int i = 0; i < envelopes.length; i++) {
            max = Math.max(max, dfs(i, path, envelopes, cache));
        }
        return max;
    }

    private int dfs(int i, boolean[] path, int[][] envelopes, int[] cache) {
        if (i < 0) return 0;
        if (cache[i] != 0) return cache[i];
        int ans = 1;
        path[i] = true;
        for (int j = 0; j <envelopes.length; j++) {
            if (path[j]) {
                continue;
            }
            if (envelopes[j][0] > envelopes[i][0] && envelopes[j][1] > envelopes[i][1]) {
                ans = Math.max(ans, 1 + dfs(j, path, envelopes, cache));
            }
        }
        path[i] = false;
        cache[i] = ans;
        return ans;
    }

}
