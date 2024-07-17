package com.lj.problem.hwod;

/**
 * 攀登者喜欢寻找各种地图，并且尝试攀登到最高的山峰。
 *
 * 地图表示为一维数组，数组的索引代表水平位置，数组的元素代表相对海拔高度。其中数组元素0代表地面。
 *
 * 例如：[0,1,2,4,3,1,0,0,1,2,3,1,2,1,0]，代表如下图所示的地图，地图中有两个山脉位置分别为 1,2,3,4,5 和 8,9,10,11,12,13，最高峰高度分别为 4,3。最高峰位置分别为3,10。
 *
 * 一个山脉可能有多座山峰(高度大于相邻位置的高度，或在地图边界且高度大于相邻的高度)。
 *
 * 输入：
 * 0,1,4,3,1,0,0,1,2,3,1,2,1,0
 *
 * 输出：
 * 3
 *
 * 说明：
 * 山峰所在索引分别为3，10，12
 *
 * 作者：code5bug
 * 链接：https://www.nowcoder.com/discuss/594230158995787776?sourceSSR=search
 * 来源：牛客网
 */
public class Climber1 {

    public int solve(int[] mountain) {
        
        int result = 0;
        
        for (int i=1; i < mountain.length; i++) {
            
            if (i < mountain.length && mountain[i] > mountain[i-1]) {
                while (i < mountain.length && mountain[i] > mountain[i-1]) {
                    i++;
                }
    
                result++;
            }
            
            if (i >= mountain.length - 1) {
                break;
            }
            
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println( new Climber1().solve(new int[]{0,1,4,3,1,0,0,1,2,3,1,2,1,0}));
        System.out.println( new Climber1().solve(new int[]{0, 1, 2, 3, 2, 4}));
    }
}
