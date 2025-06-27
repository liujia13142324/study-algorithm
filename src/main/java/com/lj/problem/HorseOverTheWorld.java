package com.lj.problem;

import com.lj.study.common.utils.MyArrayUtil;

/**
 * 马踏棋盘
 * 马踏棋盘算法也被称为骑士周游问题
 * 将马随机放在国际象棋的8×8棋盘Board[0～7][0～7]的某个方格中，马按走棋规则(马走日字)进行移动。要求每个方格只进入一次，走遍棋盘上全部64个方格
 *
 */
public class HorseOverTheWorld {
    
    public int[][] overWorld(int[] startPos) {
        
        int[][] result = new int[8][8];
    
    
        overWorld(result, startPos[0], startPos[1], 1);
        
        return result;
    }
    
    private void overWorld(int[][] result, int i, int j, int steps) {
    
        result[i][j] = steps;
        
        int nextI;
        int nextJ;
        
        if (steps >= result.length * result[0].length) {
            MyArrayUtil.printArrayOf2(result, "\t");
            System.out.println();
            return;
        }
    
        // 1
        if ((nextI = i + 2) < result.length && (nextJ = j + 1) < result.length && result[nextI][nextJ] == 0) {
            overWorld(result, nextI, nextJ, steps + 1);
            // 恢复，重新探索
            result[nextI][nextJ] = 0;
        }
    
        // 2
        if ((nextI = i + 1) < result.length && (nextJ = j + 2) < result.length && result[nextI][nextJ] == 0) {
            overWorld(result, nextI, nextJ, steps + 1);
            // 恢复，重新探索
            result[nextI][nextJ] = 0;
        }
    
        // 3
        if ((nextI = i - 1) >= 0 && (nextJ = j + 2) < result.length && result[nextI][nextJ] == 0) {
            overWorld(result, nextI, nextJ, steps + 1);
            // 恢复，重新探索
            result[nextI][nextJ] = 0;
        }
    
        // 4
        if ((nextI = i - 2) >= 0 && (nextJ = j + 1) < result.length && result[nextI][nextJ] == 0) {
            overWorld(result, nextI, nextJ, steps + 1);
            // 恢复，重新探索
            result[nextI][nextJ] = 0;
        }
    
        // 5
        if ((nextI = i - 2) >= 0 && (nextJ = j - 1) >= 0 && result[nextI][nextJ] == 0) {
            overWorld(result, nextI, nextJ, steps + 1);
            // 恢复，重新探索
            result[nextI][nextJ] = 0;
        }
    
        // 6
        if ((nextI = i - 1) >= 0 && (nextJ = j - 2) >= 0 && result[nextI][nextJ] == 0) {
            overWorld(result, nextI, nextJ, steps + 1);
            // 恢复，重新探索
            result[nextI][nextJ] = 0;
        }
    
        // 7
        if ((nextI = i + 1) < result.length && (nextJ = j - 2) >= 0 && result[nextI][nextJ] == 0) {
            overWorld(result, nextI, nextJ, steps + 1);
            // 恢复，重新探索
            result[nextI][nextJ] = 0;
        }
    
        // 8
        if ((nextI = i + 2) < result.length && (nextJ = j - 1) >= 0 && result[nextI][nextJ] == 0) {
            overWorld(result, nextI, nextJ, steps + 1);
            // 恢复，重新探索
            result[nextI][nextJ] = 0;
        }
    }
    
    
    public static void main(String[] args) {
        new HorseOverTheWorld().overWorld(new int[]{0,0});
    }
    
}
