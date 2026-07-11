package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 3694. 删除子字符串后不同的终点
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个由字符 'U'、'D'、'L' 和 'R' 组成的字符串 s，表示在无限的二维笛卡尔网格上的移动。
 *
 * 'U': 从 (x, y) 移动到 (x, y + 1)。
 * 'D': 从 (x, y) 移动到 (x, y - 1)。
 * 'L': 从 (x, y) 移动到 (x - 1, y)。
 * 'R': 从 (x, y) 移动到 (x + 1, y)。
 * 你还得到了一个正整数 k。
 *
 * 你 必须 选择并移除 恰好一个 长度为 k 的连续子字符串 s。然后，从坐标 (0, 0) 开始，按顺序执行剩余的移动。
 *
 * 返回可到达的 不同 最终坐标的数量。
 *
 *
 *
 * 示例 1:
 *
 * 输入：s = "LUL", k = 1
 *
 * 输出：2
 *
 * 解释：
 *
 * 移除长度为 1 的子字符串后，s 可以是 "UL"、"LL" 或 "LU"。执行这些移动后，最终坐标将分别是 (-1, 1)、(-2, 0) 和 (-1, 1)。有两个不同的点 (-1, 1) 和 (-2, 0)，因此答案是 2。
 *
 * 示例 2:
 *
 * 输入：s = "UDLR", k = 4
 *
 * 输出：1
 *
 * 解释：
 *
 * 移除长度为 4 的子字符串后，s 只能是空字符串。最终坐标将是 (0, 0)。只有一个不同的点 (0, 0)，因此答案是 1。
 *
 * 示例 3:
 *
 * 输入：s = "UU", k = 1
 *
 * 输出：1
 *
 * 解释：
 *
 * 移除长度为 1 的子字符串后，s 变为 "U"，它总是以 (0, 1) 结束，因此只有一个不同的最终坐标。
 *
 *
 *
 * 提示:
 *
 * 1 <= s.length <= 105
 * s 只包含 'U'、'D'、'L' 和 'R'。
 * 1 <= k <= s.length
 */
public class DistinctPoints {

    @Test
    public void test() {
        System.out.println(distinctPoints("LUL", 1));
    }

    private static final int[][] DIRS = new int[128][];

    static {
        DIRS['U'] = new int[]{0, 1};
        DIRS['D'] = new int[]{0, -1};
        DIRS['L'] = new int[]{-1, 0};
        DIRS['R'] = new int[]{1, 0};
    }

    /**
     * 算第一个窗口的增减量
     * @param s
     * @param k
     * @return
     */
    public int distinctPoints3(String s, int k) {
        int n = s.length();
        char[] chars = s.toCharArray();
        Set<Long> cache = new HashSet<>();
        long x = 0, y = 0;
        cache.add(((x + n) << 20) | (y + n));

        for (int i = k; i < n; i++) {
            x += DIRS[chars[i]][0] - DIRS[chars[i - k]][0];
            y += DIRS[chars[i]][1] - DIRS[chars[i - k]][1];
            cache.add(((x + n) << 20) | (y + n));
        }
        return cache.size();
    }



    public int distinctPoints2(String s, int k) {
        int n = s.length();
        long x = 0, y = 0;
        int ans = 0;
        Set<Long> cache = new HashSet<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < n; i++) {
            x += DIRS[chars[i]][0];
            y += DIRS[chars[i]][1];
            if (i < k - 1) {
                continue;
            }
            cache.add(((x + n) << 20) | (y + n));

            // 左边递减，为下次循环左准备
            x -= DIRS[chars[i - k + 1]][0];
            y -= DIRS[chars[i - k + 1]][1];
        }

        return cache.size();
    }


    public int distinctPoints(String s, int k) {
        int n = s.length();
        if (n == k) return 1;
        Map<Integer, Set<Integer>> cache = new HashMap<>();
        char[] chars = s.toCharArray();
        int[] tmp = {0, 0};
        for (int c: chars) {
            cacl1(tmp, c);
        }

        int l = 0;
        int ans = 0;
        for (int r = 0; r < n; r++) {
            cacl2(tmp, chars[r]);
            if (r < k - 1) {
                continue;
            }
            while (r - l + 1 > k) {
                cacl1(tmp, chars[l++]);
            }
            if (!cache.containsKey(tmp[0]) || !cache.get(tmp[0]).contains(tmp[1])) {
                Set<Integer> val = cache.getOrDefault(tmp[0], new HashSet<>());
                val.add(tmp[1]);
                cache.put(tmp[0], val);
                ans++;
            }
        }

        return ans;
    }

    private void cacl2(int[] tmp, char c) {
        switch (c) {
            case 'U':
                tmp[1]--;
                break;
            case 'D':
                tmp[1]++;
                break;
            case 'L':
                tmp[0]++;
                break;
            default:
                tmp[0]--;
                break;
        }
    }

    private void cacl1(int[] tmp, int c) {
        switch (c) {
            case 'U':
                tmp[1]++;
                break;
            case 'D':
                tmp[1]--;
                break;
            case 'L':
                tmp[0]--;
                break;
            default:
                tmp[0]++;
                break;
        }
    }

}
