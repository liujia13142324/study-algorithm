package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 672. 灯泡开关 Ⅱ

 * 房间中有 n 只已经打开的灯泡，编号从 1 到 n 。墙上挂着 4 个开关 。
 * 这 4 个开关各自都具有不同的功能，其中：
 * 开关 1 ：反转当前所有灯的状态（即开变为关，关变为开）
 * 开关 2 ：反转编号为偶数的灯的状态（即 0, 2, 4, ...）
 * 开关 3 ：反转编号为奇数的灯的状态（即 1, 3, ...）
 * 开关 4 ：反转编号为 j = 3k + 1 的灯的状态，其中 k = 0, 1, 2, ...（即 1, 4, 7, 10, ...）
 * 你必须 恰好 按压开关 presses 次。每次按压，你都需要从 4 个开关中选出一个来执行按压操作。
 *
 * 给你两个整数 n 和 presses ，执行完所有按压之后，返回 不同可能状态 的数量。
 *
 * 示例 1：
 * 输入：n = 1, presses = 1
 * 输出：2
 * 解释：状态可以是：
 * - 按压开关 1 ，[关]
 * - 按压开关 2 ，[开]
 *
 * 示例 2：
 * 输入：n = 2, presses = 1
 * 输出：3
 * 解释：状态可以是：
 * - 按压开关 1 ，[关, 关]
 * - 按压开关 2 ，[开, 关]
 * - 按压开关 3 ，[关, 开]
 *
 * 示例 3：
 * 输入：n = 3, presses = 1
 * 输出：4
 * 解释：状态可以是：
 * - 按压开关 1 ，[关, 关, 关]
 * - 按压开关 2 ，[开, 关, 开]
 * - 按压开关 3 ，[关, 开, 关]
 * - 按压开关 4 ，[关, 开, 开]
 *
 *
 * 提示：
 *
 * 1 <= n <= 1000
 * 0 <= presses <= 1000
 */
public class FlipLights {
    
    
    @Test
    public void testFlipLights() {
        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE));
        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
    }



    public int flipLights(int n, int presses) {
        // 情况1：一次都不按，只有【全亮】1种状态
        if (presses == 0) {
            return 1;
        }
        // 情况2：n=1，只有1个灯，无论怎么按，只有2种状态（亮/灭）
        if (n == 1) {
            return 2;
        }
        // 情况3：n=2
        else if (n == 2) {
            // 按1次 → 3种；按≥2次 → 4种（全部状态）
            return presses == 1 ? 3 : 4;
        }
        // 情况4：n ≥ 3（最常见情况）
        else {
            // 按1次→4种；按2次→7种；按≥3次→8种（全部状态）
            return presses == 1 ? 4 : presses == 2 ? 7 : 8;
        }
    }

}
