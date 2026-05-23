package com.lj.problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * # P1352 没有上司的舞会
 *
 * ## 题目描述
 *
 * 某大学有 $n$ 个职员，编号为 $1\ldots n$。
 *
 * 他们之间有从属关系，也就是说他们的关系就像一棵以校长为根的树，父结点就是子结点的直接上司。
 *
 * 现在有个周年庆宴会，宴会每邀请来一个职员都会增加一定的快乐指数 $r_i$，但是呢，如果某个职员的直接上司来参加舞会了，那么这个职员就无论如何也不肯来参加舞会了。
 *
 * 所以，请你编程计算，邀请哪些职员可以使快乐指数最大，求最大的快乐指数。
 *
 * ## 输入格式
 *
 * 输入的第一行是一个整数 $n$。
 *
 * 第 $2$ 到第 $(n + 1)$ 行，每行一个整数，第 $(i+1)$ 行的整数表示 $i$ 号职员的快乐指数 $r_i$。
 *
 * 第 $(n + 2)$ 到第 $2n$ 行，每行输入一对整数 $l, k$，代表 $k$ 是 $l$ 的直接上司。
 *
 * ## 输出格式
 *
 * 输出一行一个整数代表最大的快乐指数。
 *
 * ## 输入输出样例 #1
 *
 * ### 输入 #1
 *
 * ```
 * 7
 * 1
 * 1
 * 1
 * 1
 * 1
 * 1
 * 1
 * 1 3
 * 2 3
 * 6 4
 * 7 4
 * 4 5
 * 3 5
 *
 * ```
 *
 * ### 输出 #1
 *
 * ```
 * 5
 *
 * ```
 *
 * ## 说明/提示
 *
 * #### 数据规模与约定
 *
 * 对于 $100\%$ 的数据，保证 $1\leq n \leq 6 \times 10^3$，$-128 \leq r_i\leq 127$，$1 \leq l, k \leq n$，且给出的关系一定是一棵树。
 */
public class PartyWithoutABoss {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] happyPoint = new int[n];
        List<Integer>[] children = new ArrayList[n];
        boolean[] hasParent = new boolean[n];
        for (int i = 0; i < n; i++) {
            happyPoint[i] = sc.nextInt();
        }
        for (int i = 0; i < n - 1; i++) {
            int child = sc.nextInt() - 1;
            int parent = sc.nextInt() - 1;
            if (children[parent] == null) {
                children[parent] = new ArrayList<>();
            }
            children[parent].add(child);
            hasParent[child] = true;
        }

        int root = 0;
        for (int i = 0; i < n; i++) {
            if (!hasParent[i]) {
                root = i;
                break;
            }
        }

        int[] calc = dfs(root, happyPoint, children);
        System.out.println(Math.max(calc[0], calc[1]));
    }

    private static int[] dfs(int i, int[] happyPoint, List<Integer>[] children) {
        if (children[i] == null) {
            // 还是应该需要判断是否为正数
            return new int[]{happyPoint[i], 0};
        }
        int contain = Math.max(happyPoint[i], 0);
        int notContain = 0;
        for (int child: children[i]) {
            int[] childCalculation = dfs(child, happyPoint, children);
            if (childCalculation[1] > 0) {
                contain += childCalculation[1];
            }
            if (childCalculation[0] > 0 || childCalculation[1] > 0) {
                notContain += Math.max(childCalculation[0], childCalculation[1]);
            }
        }

        return  new int[]{contain, notContain};
    }
}
