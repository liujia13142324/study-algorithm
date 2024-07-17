package com.lj.problem.leetcode;

import com.test.common.utils.MyArrayUtil;
import org.junit.Test;

/**
 * 给定一个代表游戏板的二维字符矩阵。 'M' 代表一个未挖出的地雷，'E' 代表一个未挖出的空方块，'B' 代表没有相邻（上，下，左，右，和所有4个对角线）地雷的已挖出的空白方块，数字（'1' 到 '8'）表示有多少地雷与这块已挖出的方块相邻，'X' 则表示一个已挖出的地雷。
 *
 * 现在给出在所有未挖出的方块中（'M'或者'E'）的下一个点击位置（行和列索引），根据以下规则，返回相应位置被点击后对应的面板：
 *
 * 如果一个地雷（'M'）被挖出，游戏就结束了- 把它改为 'X'。
 * 如果一个没有相邻地雷的空方块（'E'）被挖出，修改它为（'B'），并且所有和其相邻的未挖出方块都应该被递归地揭露。
 * 如果一个至少与一个地雷相邻的空方块（'E'）被挖出，修改它为数字（'1'到'8'），表示相邻地雷的数量。
 * 如果在此次点击中，若无更多方块可被揭露，则返回面板。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minesweeper
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MineSweeper {
  
  @Test
  public void test(){
    /*char borad [][] = new char[][]{{'B', '1', 'E', '1', 'B'},{'B', '1', 'M', '1', 'B'}
            ,{'B', '1', '1', '1', 'B'},{'B', 'B', 'B', 'B', 'B'}
    };*/
    char borad [][]= new char[][]{
            {'E', 'E', 'E', 'E', 'E'},{'E', 'E', 'M', 'E', 'E'},
            {'E', 'E', 'E', 'E', 'E'},{'E', 'E', 'E', 'E', 'E'}
    };
    MyArrayUtil.printArrayOf2( borad );
    System.out.println("----------");
    updateBoard(borad,new int[]{3,0});
    MyArrayUtil.printArrayOf2( borad );
  }
  int[][] direction = new int[][]{ {1,0},{0,1},{-1,0},{0,-1},{1,1},{1,-1},{-1,1},{-1,-1} };
  public char[][] updateBoard(char[][] board, int[] click) {
    int i = click[0];
    int j = click[1];
    if(!checkBorder(board,i,j)){
      return board;
    }
    if( board[i][j] == 'M' ){
      board[i][j] = 'X';
      return board;
    }
    if( board[i][j] == 'E' ){
      int aroundCount= 0;
      for( int n=0 ; n<direction.length ; n++ ){
        if( checkBorder(board,i+direction[n][0],j+direction[n][1])
                && board[i+direction[n][0]][j+direction[n][1]] == 'M' ){
          aroundCount++;
        }
      }
      if(aroundCount == 0){
        board[i][j] = 'B';
        for( int n=0 ; n<direction.length ; n++ ){
          updateBoard( board, new int[]{i+direction[n][0],j+direction[n][1]} );
        }
      }else{
        board[i][j] = (char)(aroundCount+48);
      }
      
    }
    
    return board;
  }
  
  private boolean checkBorder(char[][] board, int i, int j) {
    return i>=0 && j>=0 && i<board.length && j<board[0].length;
  }
  
  @Test
  public void testAround (){
    System.out.println( Math.pow(-1,2) );
  }
  
}
