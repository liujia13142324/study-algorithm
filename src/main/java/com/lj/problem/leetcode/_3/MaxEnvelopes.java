package com.lj.problem.leetcode._3;

import com.lj.study.common.utils.MyArrayUtil;
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
 *
 */
public class MaxEnvelopes {

    @Test
    public void test() {
//        System.out.println(maxEnvelopes4(new int[][]{{5,4},{6,4},{6,7},{2,3}}));
//        System.out.println(maxEnvelopes4(new int[][]{{4,5},{4,6},{6,7},{2,3},{1,1},{1,1}}));
        System.out.println(maxEnvelopes5(new int[][]{{2,100},{3,200},{4,300},{5,500},{5,400},{5,250},{6,370},{6,360},{7,380}}));
//        System.out.println(maxEnvelopes2(new int[][]{{1,15},{7,18},{7,6},{7,100},{2,200},{17,30},{17,45},{3,5},{7,8},{3,6},{3,10},{7,20},{17,3},{17,45}}));
//        System.out.println(maxEnvelopes2(new int[][]{{6,10},{11,14},{6,1},{16,14},{13,2}}));
    }


    /**
     * 根据 e[0] 递增，如果 e[0] 相同，根据e[1] 递减
     * @param envelopes
     * @return
     */
    public int maxEnvelopes5(int[][] envelopes) {
        Arrays.sort(envelopes, (e1,e2)->{
            if (e1[0] != e2[0]) {
                return e1[0] - e2[0];
            }else {
                return e2[1] - e1[1];
            }
        });

        int[] tmp = new int[envelopes.length];
        int len = 0;
        for (int i = 0; i < envelopes.length; i++) {
            int idx = lowerBoundOfArray1(-1, len, envelopes[i][1], tmp);
            if (idx == len) {
                tmp[len++] = envelopes[i][1];
            }else {
                tmp[idx] = envelopes[i][1];
            }
        }
        return len;
    }

    private int lowerBoundOfArray1(int l, int r, int target, int[] array) {
        while (l + 1 < r) {
            int mid = (l + r) / 2;
            if (array[mid] >= target) {
                r = mid;
            }else {
                l = mid;
            }
        }
        return r;
    }


    /**
     * 50 / 87
     * @param envelopes
     * @return
     */
    public int maxEnvelopes4(int[][] envelopes) {
        Arrays.sort(envelopes, Comparator.comparingInt(a -> a[0]));
        return dfs4(0, envelopes,  new int[envelopes.length][2], 0);
    }

    private int dfs4(int i, int[][] envelopes, int[][] tmp, int size) {
        if (i == envelopes.length) return size;

        int idx1 = find2(-1, size, envelopes[i][0], tmp, 0);
        int idx2 = find2(-1, size, envelopes[i][1], tmp, 1);
        if (idx1 == idx2) {
            if (idx1 == size) {
                tmp[size++] = envelopes[i];
            }else {
                tmp[idx1] = envelopes[i];
            }
            return dfs4(i + 1, envelopes, tmp, size);
        }

        // 选
        int minIdx = Math.min(idx1, idx2);
        int[][] tmp2 = clone(tmp);
        tmp2[minIdx] = envelopes[i];
        return Math.max(
                dfs4(i + 1, envelopes, tmp2, minIdx + 1),
                // 不选
                dfs4(i + 1, envelopes, tmp, size)
        );
    }

    private int[][] clone(int[][] tmp) {
        int[][] result = new int[tmp.length][tmp[0].length];
        for (int i = 0; i < tmp.length; i++) {
            for (int j = 0; j < tmp[i].length; j++) {
                result[i][j] = tmp[i][j];
            }
        }
        return result;
    }

    private int find2(int l, int r, int target, int[][] nums, int z) {
        while (l + 1 < r) {
            int mid = (l + r) >>> 1;
            if (nums[mid][z] >= target) {
                r = mid;
            }else {
                l = mid;
            }
        }
        return r;
    }


    /**
     * 69 / 87
     * @param envelopes
     * @return
     */
    public int maxEnvelopes3(int[][] envelopes) {
        Arrays.sort(envelopes, Comparator.comparingInt(a -> a[0]));
        return dfs3(envelopes.length - 1, new int[]{10001, 10001}, envelopes);
    }

    private int dfs3(int i, int[] pre, int[][] envelopes) {
        if (i < 0) return 0;
        int val = 0;
        if (envelopes[i][0] < pre[0] && envelopes[i][1] < pre[1]) {
            val = 1 + dfs3(i - 1, envelopes[i], envelopes);
        }
        return Math.max(val, dfs3(i - 1, pre, envelopes));
    }

    /**
     * 85/87
     * @param envelopes
     * @return
     */
    public int maxEnvelopes2__(int[][] envelopes) {
        Arrays.sort(envelopes, Comparator.comparingInt(a -> a[0]));
        int[] dp = new int[envelopes.length];
        Arrays.fill(dp, 1);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < envelopes.length; i++) {
           for (int j = 0; j < i; j++) {
               if (envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1]) {
                   dp[i] = Math.max(dp[i], dp[j] + 1);
               }
           }
            max = Math.max(max, dp[i]);
        }
        return max;
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
