package com.lj.problem.hwod;

import java.util.Arrays;

/**
 * 题目描述:给定一个包含0和1的二维矩阵
 * 给定一个初始位置和速度
 * 一个物体从给定的初始位置触发,在给定的速度下进行移动,遇到矩阵的边缘则发生镜面反射无论物体经过 0 还是 1,都不影响其速度请计算并给出经过t时间单位后,物体经过1 点的次数
 * 矩阵以左上角位置为[0,0](列(x),行(行)),例如下面A点坐标为[2,1](第二列,第一行)
 * ------- 递増(x)
 * 1001000010000100100001000010010000100001001000010000100100001000010010000100001001000010000
 * 递増(y)
 *
 * 注意:
 * 1.如果初始位置的点是 1,也计算在内
 * 2. 时间的最小单位为1,不考虑小于1 个时间单位内经过的点
 *
 * 输入描述:第一行为初始信息
 * <w> <h> <x> <y> <sx> <sy> <t>
 *
 * 第二行开始一共h行,为二维矩阵信息,
 * 其中
 * w,h为矩阵的宽和高
 * x,y为起始位置
 * sx,sy为初始速度
 * t为经过的时间
 *
 * 所有输幢00010000据范国如下
 * 0 <w <1000 < h < 1000<=X<W0 <=y<W.1 <= sx <= 1.1 <= sy <= 10 <=t< 100
 *
 * 输出描述:经过1的个数
 * 注意初始位置也要计算在内

 * 示例1
 * 输入:12 7 2 1 1 -1 13
 *
 *  0 1 2 3 4 5 6 7 8 9 10 11
 *0 0 0 1 0 0 0 0 1 0 0 0 0
 *1 0 0 1 0 0 0 0 1 0 0 0 0
 *2 0 0 1 0 0 0 0 1 0 0 0 0
 *3 0 0 1 0 0 0 0 1 0 0 0 0
 *4 0 0 1 0 0 0 0 1 0 0 0 0
 *5 0 0 1 0 0 0 0 1 0 0 0 0
 *6 0 0 1 0 0 0 0 1 0 0 0 0
 *
 * 1 0   右
 * 1 -1  右上
 * 0 -1  上
 * -1 -1 左上
 * -1 0  左
 * -1 1  左下
 * 0 1   下
 * 1 1   右下
 *
 * 输出:3
 * 说明:初始位置为(2,1),速度为(1,-1),那么13个时间单位后,经过点1的个数为3
 *
 */
public class Count1OfReflection {
    
    int solve(String[] input) {
        int i = 0;
        int w = Integer.valueOf(input[i++]);
        int h = Integer.valueOf(input[i++]);
        int x = Integer.valueOf(input[i++]);
        int y = Integer.valueOf(input[i++]);
        int sx = Integer.valueOf(input[i++]);
        int sy = Integer.valueOf(input[i++]);
        int t = Integer.valueOf(input[i++]);
        // 注意反过来
        String[][] square = new String[h][w];
        
        for (int k = 0; k < h; k++) {
            square[k] = input[i + k].split("");
        }
        
        int result = 0;
        if (square[y][x].equals("1")) {
            result++;
        }
        
        int tmpx,tmpy;
        for (; t > 0; t--) {
            // 左、右边缘
            if ((tmpx = x + sx) < 0 || tmpx >= w) {
                sx = -sx;
            }
            // 上、下边缘，可能会同时触碰两个边缘
            if ((tmpy = y + sy) < 0 || tmpy >= h) {
                sy = -sy;
            }
            x = x + sx;
            y = y + sy;
            if (square[y][x].equals("1")) {
                result++;
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println(new Count1OfReflection().solve(
                new String[]{
                        "12", "7", "2", "1", "1", "-1", "13"
                        ,"001000010000"
                        ,"001000010000"
                        ,"001000010000"
                        ,"001000010000"
                        ,"001000010000"
                        ,"001000010000"
                        ,"001000010000"
                }
        ));
    
        System.out.println(new Count1OfReflection().solve(
                new String[]{
                        "12", "7", "2", "1", "1", "1", "13"
                        ,"001000010000"
                        ,"001000010000"
                        ,"001000010000"
                        ,"001000010000"
                        ,"001000010000"
                        ,"001000010000"
                        ,"001000010000"
                }
        ));
    
        System.out.println(new Count1OfReflection().solve(
                new String[]{
                        "12", "7", "2", "1", "1", "0", "13"
                        ,"001000010000"
                        ,"001000010000"
                        ,"001000010000"
                        ,"001000010000"
                        ,"001000010000"
                        ,"001000010000"
                        ,"001000010000"
                }
        ));
    
        System.out.println(new Count1OfReflection().solve(
                new String[]{
                        "12", "7", "2", "1", "-1", "-1", "13"
                        ,"001000010000"
                        ,"001000010000"
                        ,"001000010000"
                        ,"001000010000"
                        ,"001000010000"
                        ,"001000010000"
                        ,"001000010000"
                }
        ));
    
        System.out.println(new Count1OfReflection().solve(
                new String[]{
                        "12", "7", "2", "1", "0", "-1", "13"
                        ,"001000010000"
                        ,"001000010000"
                        ,"001000010000"
                        ,"001000010000"
                        ,"001000010000"
                        ,"001000010000"
                        ,"001000010000"
                }
        ));
    }
    
}
