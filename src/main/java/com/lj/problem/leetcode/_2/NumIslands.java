package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.*;

/**
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 *
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 * 示例 1：
 *
 * 输入：grid = [
 *   ['1','1','1','1','0'],
 *   ['1','1','0','1','0'],
 *   ['1','1','0','0','0'],
 *   ['0','0','0','0','0']
 * ]
 * 输出：1
 * 示例 2：
 *
 * 输入：grid = [
 *   ['1','1','0','0','0'],
 *   ['1','1','0','0','0'],
 *   ['0','0','1','0','0'],
 *   ['0','0','0','1','1']
 * ]
 * 输出：3
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] 的值为 '0' 或 '1'
 */
public class NumIslands {
    
    
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(i,j,grid);
                    count++;
                }
            }
        }
        return count;
    }
    
    private void dfs(int i, int j, char[][] grid) {
        if ( i >= 0 && i < grid.length && j >= 0 && j < grid[i].length) {
            if (grid[i][j] != '1') {
                return;
            }
    
            grid[i][j] = '2';
            dfs(i-1,j,grid);
            dfs(i+1,j,grid);
            dfs(i,j-1,grid);
            dfs(i,j+1,grid);
        }
    }
    
    
    /*public int numIslands(char[][] grid) {
        int count = 0;
        LinkedList<int[]> stack = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    stack.push(new int[]{i, j});
                    while(!stack.isEmpty()) {
                        int[] pop = stack.pop();
                        tracing(pop[0], pop[1], grid, stack);
                    }
                    count++;
                }
            }
        }
        return count;
    }
    
    private void tracing(int i, int j, char[][] grid, LinkedList<int[]> stack) {
        grid[i][j] = '2';
        if (i - 1 >= 0 && grid[i - 1][j] == '1') {
            stack.push(new int[]{i - 1,j});
        }
        if (i + 1 < grid.length && grid[i + 1][j] == '1') {
            stack.push(new int[]{i + 1,j});
        }
        if (j - 1 >= 0 && grid[i][j - 1] == '1') {
            stack.push(new int[]{i,j - 1});
        }
        if (j + 1 < grid[i].length && grid[i][j + 1] == '1') {
            stack.push(new int[]{i,j + 1});
        }
    }*/
    
  
    
  
    
    public static void main(String[] args) {
        System.out.println(new NumIslands().numIslands(
                new char[][]{
                        {'1','1','1','1','0'}
                        , {'1','1','0','1','0'}
                        , {'1','1','0','0','0'}
                        , {'0','0','0','0','0'}
                }
        ));
    
        System.out.println(new NumIslands().numIslands(
                new char[][]{
                        {'1','1','0','0','0'}
                        , {'1','1','0','0','0'}
                        , {'0','0','1','0','0'}
                        , {'0','0','0','1','1'}
                }
        ));
    
        System.out.println(new NumIslands().numIslands(
                new char[][]{
                        {'1','1','1'}
                        , {'0','1','0'}
                        , {'1','1','1'}
                }
        ));
        
        
    }
    
    @Test
    public void test1() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        // int[] result = new int[n];
        // int idx = 0;
        HashMap<Integer,Integer> map = new HashMap<>(n);
        String[] tmp;
        int k,v;
        for (int i = 0; i < n; i++) {
            tmp = in.nextLine().split(" ");
            k = Integer.parseInt(tmp[0]);
            v = Integer.parseInt(tmp[1]);
            if (map.get(k) == null) {
                map.put(k, v);
            }else {
                map.put(k, map.get(k) + v);
            }
        }
        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " +entry.getValue());
        }
    }
    
}
