package com.lj.problem.leetcode._2;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * 给定一个二维整数数组 items ，其中 items[i] = [pricei, weighti] 表示第 i 个物品的价格和重量。
 *
 * 还给定一个 正 整数容量 capacity 。
 *
 * 每个物品可以分成两个部分，比率为 part1 和 part2 ，其中 part1 + part2 == 1 。
 *
 * 第一个物品的重量是 weighti * part1 ，价格是 pricei * part1 。
 * 同样，第二个物品的重量是 weighti * part2 ，价格是 pricei * part2 。
 * 使用给定的物品，返回填满容量为 capacity 的背包所需的 最大总价格 。如果无法填满背包，则返回 -1 。与实际答案的差距在 10-5 以内的 实际答案 将被视为接受。
 *
 *
 * 输入：items = [[50,1],[10,8]], capacity = 5
 * 输出：55.00000
 * 解释：
 * 我们将第二个物品分成两个部分，part1 = 0.5，part2 = 0.5。
 * 第一个物品的价格和重量分别为 5 和 4 。同样地，第二个物品的价格和重量也是 5 和 4 。
 * 经过操作后，数组 items 变为 [[50,1],[5,4],[5,4]] 。
 * 为了填满容量为 5 的背包，我们取价格为 50 的第一个元素和价格为 5 的第二个元素。
 * 可以证明，55.0 是我们可以达到的最大总价值。
 * 示例 2 ：
 *
 * 输入：items = [[100,30]], capacity = 50
 * 输出：-1.00000
 * 解释：无法用给定的物品装满背包。
 *
 *
 * 提示：
 *
 * 1 <= items.length <= 105
 * items[i].length == 2
 * 1 <= pricei, weighti <= 104
 * 1 <= capacity <= 109
 */
public class BagProblem2 {
    
    public double maxPrice(int[][] items, int capacity) {
        
        int totalWeight = 0;
        double[][] perVal = new double[items.length][3];
        int i = 0;
        
        for (int[] one: items) {
            totalWeight += one[1];
            perVal[i++] = new double[] {one[0], one[1], (double)one[0]/one[1]};
        }
        
        if (totalWeight < capacity){
            return -1d;
        }
        
        Arrays.sort(perVal, Comparator.comparingDouble(e-> -e[2]));
        
        double result = 0d;
        for (i=0; i<perVal.length; i++) {
            double val = perVal[i][0];
            double weight = perVal[i][1];
            double perWeight = perVal[i][2];
            if (weight < capacity) {
                result += val;
                capacity -= weight;
            }else {
                result = result + perWeight * capacity ;
                break;
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
//        System.out.println(new BagProblem2().maxPrice(new int[][]{{50,1},{10,8}}, 5));
        System.out.println(new BagProblem2().maxPrice(new int[][]{{17,19},{15,5},{3,7},{5,9}}, 29));
    }
    
}
