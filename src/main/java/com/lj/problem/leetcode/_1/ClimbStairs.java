package com.lj.problem.leetcode._1;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：2
 * 解释：有两种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶
 * 2. 2 阶
 * 示例 2：
 *
 * 输入：n = 3
 * 输出：3
 * 解释：有三种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶 + 1 阶
 * 2. 1 阶 + 2 阶
 * 3. 2 阶 + 1 阶
 */
public class ClimbStairs {
    
    /**
     * 我们用 f(x)f(x)f(x) 表示爬到第 xxx 级台阶的方案数，考虑最后一步可能跨了一级台阶，也可能跨了两级台阶，所以我们可以列出如下式子：
     *
     * f(x)=f(x−1)+f(x−2)f(x) = f(x - 1) + f(x - 2)f(x)=f(x−1)+f(x−2)
     *
     * 满足斐波那契数列规律
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n == 1 ){
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        
        int p = 1;
        int result = 2;
        int tmp;
        for (int i = 0, k = n - 2; i < k; i++) {
            tmp = result;
            result += p;
            p = tmp;
        }
        
        return result;
    }
    
    /*int count = 0;
    public int climbStairs(int n) {
        climbStairs(0, 0, n);
        return count;
    }
    
    private void climbStairs(int cur, int step, int n) {
        cur += step;
        if (cur > n) {
            return;
        }else if (cur == n) {
            count++;
            return;
        }
        climbStairs(cur, 1, n);
        climbStairs(cur, 2, n);
    }*/
    
    public static void main(String[] args) {
        System.out.println(new ClimbStairs().climbStairs(44));
    }
}
