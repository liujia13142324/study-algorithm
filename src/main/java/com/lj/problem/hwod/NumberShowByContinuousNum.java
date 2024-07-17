package com.lj.problem.hwod;

/**
 * 题目描述:一个整数可以由连续的自然数之和来表示。给定一个整数,计算该整数有几种连续自然数之和的表达式，且打印出每种表达式。
 * 输入描述:一个目标整数T(1<=T<= 1000)
 * 输出描述:该整数的所有表达式和表达式的个数。如果有多种表达式，输出要求为:1.自然数个数最少的表达式优先输出2.每个表达式中按自然数递增的顺序输出，
 * 具体的格式参见样例。在每个测试数据结束时,输出一行”ResultX”，其中X是最终的表达式个数。
 *
 *示例1
 * 输入:9
 * 输出:9=9
 * 9=4+5
 * 9=2+3+4
 * Result:3
 * 说明:整数9有三种表示方法,第1个表达式只有1个自然数,最先输出,第2个表达式有2个自然数,第2次序输出,第3个表达式有3个自然数
 * ,最后输出。每个表达式中的自然数都是按递增次序输出的。数字与符号之间无空格
 *
 * 示例2
 * 输入:10
 * 输出:10=10
 * 10=1+2+3+4
 * Result:2
 */
public class NumberShowByContinuousNum {
    
    public void solve(int num){
        int result = 1;
        System.out.println(num + "=" + num);
        
        for (int i = 1, k = num/2 + 1; i < k; i++) {
            int tmp = i;
            StringBuilder str = new StringBuilder().append(num).append("=").append(i);
            for (int j = i + 1; tmp < num; j++) {
                tmp += j;
                str.append("+").append(j);
            }
            if (tmp == num) {
                System.out.println(str.toString());
                result++;
            }
        }
    
        System.out.println("Result:"+result);
        
    }
    
    
    public static void main(String[] args) {
        NumberShowByContinuousNum solver = new NumberShowByContinuousNum();
        for (int i = 0; i < 20; i++) {
            solver.solve(i);
            System.out.println();
        }
    }
    
    
}
