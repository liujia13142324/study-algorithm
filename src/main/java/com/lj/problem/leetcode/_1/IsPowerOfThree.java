package com.lj.problem.leetcode._1;

import org.junit.Test;

import java.util.HashSet;

/**
 * 326. 3的幂
 * 给定一个整数，写一个函数来判断它是否是 3 的幂次方。如果是，返回 true ；否则，返回 false 。
 * 整数 n 是 3 的幂次方需满足：存在整数 x 使得 n == 3x
 *
 * 示例 1：
 * 输入：n = 27
 * 输出：true
 *
 * 示例 2：
 * 输入：n = 0
 * 输出：false

 * 示例 3：
 * 输入：n = 9
 * 输出：true

 * 示例 4：
 * 输入：n = 45
 * 输出：false
 *
 * 1, 3, 9, 27, 81
 *
 * 提示：
 * -231 <= n <= 231 - 1
 * 进阶：你能不使用循环或者递归来完成本题吗？
 */
public class IsPowerOfThree {

    @Test
    public void test() {
        System.out.println(Math.pow(3, 19));
        System.out.println(isPowerOfThree(27));
        System.out.println(isPowerOfThree(0));
        System.out.println(isPowerOfThree(9));
        System.out.println(isPowerOfThree(45));

        System.out.println(isPowerOfThree2(27));
        System.out.println(isPowerOfThree2(0));
        System.out.println(isPowerOfThree2(9));
        System.out.println(isPowerOfThree2(45));
    }

    public boolean isPowerOfThree3(int n) {
        return 1162261467 % n == 0;
    }

    public boolean isPowerOfThree2(int n) {
        while (n > 1 && n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }

    HashSet<Integer> set = new HashSet<>();
    boolean init;

    private void init(){
        if (init) {
            return;
        }
        init = true;
        int i = 1;
        do{
            set.add(i);
            i *= 3;
        }while(i > 0);
    }

    public boolean isPowerOfThree(int n) {
        init();
        return set.contains(n);
    }
}
