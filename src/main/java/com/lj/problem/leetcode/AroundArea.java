package com.lj.problem.leetcode;

import com.lj.study.common.utils.MyArrayUtil;
import org.junit.Test;

public class AroundArea {

  @Test
  public void test() throws InstantiationException, IllegalAccessException {
    Object o = MyArrayUtil.convertStrTo2DArr(
            "X X X X\n" +
            "X O O X\n" +
            "X O O X\n" +
            "X O X X" , 'a' , " ");
    char[][]chars = (char[][]) o;
    MyArrayUtil.printArrayOf2(chars);
    solve(chars);
    System.out.println("--------------");
    MyArrayUtil.printArrayOf2(chars);
  }
  
  int n,m;
  public void solve(char[][] board) {
    n = board.length;
    if(n<=0) return;
    m = board[0].length;
    
    for( int i=0 ; i<n ; i++ ){
      dfs(board,i,0);
      dfs(board,i,m-1);
    }
  
    for( int i=0 ; i<m ; i++ ){
      dfs(board,0,i);
      dfs(board,n-1,i);
    }
    
    for( int i=0;i<n;i++ ){
      for( int j=0;j<m ;j++ ){
        if( board[i][j]=='A' ){
          board[i][j]='O';
        }else{
          board[i][j]='X';
        }
      }
    }
    
  }
  
  private void dfs(char[][] board, int i, int j) {
    if( i>=n||i<0||j>=m||j<0||board[i][j]!='O' ){
      return;
    }
    board[i][j]='A';
    dfs(board,i,j+1);
    dfs(board,i+1,j);
    dfs(board,i,j-1);
    dfs(board,i-1,j);
  }
  
}
