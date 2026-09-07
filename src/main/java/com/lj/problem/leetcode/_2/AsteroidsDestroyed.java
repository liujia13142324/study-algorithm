package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2126. 摧毁小行星
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个整数 mass ，它表示一颗行星的初始质量。再给你一个整数数组 asteroids ，其中 asteroids[i] 是第 i 颗小行星的质量。
 *
 * 你可以按 任意顺序 重新安排小行星的顺序，然后让行星跟它们发生碰撞。如果行星碰撞时的质量 大于等于 小行星的质量，那么小行星被 摧毁 ，并且行星会 获得 这颗小行星的质量。否则，行星将被摧毁。
 *
 * 如果所有小行星 都 能被摧毁，请返回 true ，否则返回 false 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：mass = 10, asteroids = [3,9,19,5,21]
 * 输出：true
 * 解释：一种安排小行星的方式为 [9,19,5,3,21] ：
 * - 行星与质量为 9 的小行星碰撞。新的行星质量为：10 + 9 = 19
 * - 行星与质量为 19 的小行星碰撞。新的行星质量为：19 + 19 = 38
 * - 行星与质量为 5 的小行星碰撞。新的行星质量为：38 + 5 = 43
 * - 行星与质量为 3 的小行星碰撞。新的行星质量为：43 + 3 = 46
 * - 行星与质量为 21 的小行星碰撞。新的行星质量为：46 + 21 = 67
 * 所有小行星都被摧毁。
 * 示例 2：
 *
 * 输入：mass = 5, asteroids = [4,9,23,4]
 * 输出：false
 * 解释：
 * 行星无论如何没法获得足够质量去摧毁质量为 23 的小行星。
 * 行星把别的小行星摧毁后，质量为 5 + 4 + 9 + 4 = 22 。
 * 它比 23 小，所以无法摧毁最后一颗小行星。
 *
 *
 * 提示：
 *
 * 1 <= mass <= 105
 * 1 <= asteroids.length <= 105
 * 1 <= asteroids[i] <= 105
 */
public class AsteroidsDestroyed {

    @Test
    public void test() {
        System.out.println(asteroidsDestroyed2(71683, new int[]{156,197,192,14,97,160,14,5}));
    }


    /**
     * 按照二进制分组, 优化版本
     * @param mass
     * @param asteroids
     * @return
     */
    public boolean asteroidsDestroyed3(int mass, int[] asteroids) {
        // 构造 min 数组，sum 数组
        // 求结果

        int[] min = new int[17];
        int[] sum = new int[17];
        int maxGroup = 0;
        Arrays.fill(min, Integer.MAX_VALUE);

        for (int asteroid: asteroids) {
            int group = 32 - Integer.numberOfLeadingZeros(asteroid) - 1;
            min[group] = Math.min(min[group], asteroid);
            sum[group] += asteroid;
            maxGroup = Math.max(maxGroup, group);
        }

        long _mass = mass;
        for (int i = 0; i <= maxGroup; i++) {
            if (min[i] != Integer.MAX_VALUE && _mass >= min[i]) {
                _mass += sum[i];
            }else if (min[i] != Integer.MAX_VALUE) {
                return false;
            }
        }

        return true;
    }


    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        Arrays.sort(asteroids);
        long _mass = mass;
        for (int asteroid : asteroids) {
            if (_mass < asteroid) {
                return false;
            }
            _mass += asteroid;
        }

        return true;
    }


    /**
     * 按照二进制分组
     * @param mass
     * @param asteroids
     * @return
     */
    public boolean asteroidsDestroyed2(int mass, int[] asteroids) {
        // 0 ~ 16
        List<Integer>[] groups = new List[] {
                new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),
                new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),
                new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),
                new ArrayList<>(),new ArrayList<>()
        };

        int maxGroup = 0;
        for (int asteroid : asteroids) {
            int group = 32-Integer.numberOfLeadingZeros(asteroid) - 1;
            List<Integer> groupArr = groups[group];
            maxGroup = Math.max(maxGroup, group);
            if (!groupArr.isEmpty() && groupArr.get(0) > asteroid) {
                groupArr.add(groupArr.get(0));
                groupArr.set(0, asteroid);
            }else {
                groupArr.add(asteroid);
            }
        }

        long _mass = mass;
        for (int i = 0; i <= maxGroup; i++) {
            if (!groups[i].isEmpty() && _mass >= groups[i].get(0)) {
                for (int __mass: groups[i]) {
                    _mass += __mass;
                }
            }else if (!groups[i].isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
