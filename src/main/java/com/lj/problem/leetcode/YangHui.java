package com.lj.problem.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class YangHui {
  /**
   * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
   */
  
  @Test
  public void test(){
    System.out.println( generate(5) );
  }
  
  
  List lsit1 = Arrays.asList(1);
  List lsit2 = Arrays.asList(1,1);
  
  private List<List<Integer>> generate(int rows) {
    List<List<Integer>> result = new ArrayList<>();
    for( int i=0 ; i<rows ; i++ ){
      if(i==0){
        result.add(lsit1);
      }else if(i==1){
        result.add(lsit2);
      }else{
        List<Integer> up = result.get( i-1 );
        List<Integer> r = new ArrayList<>();
        r.add(1);
        for( int j=0 ; j<up.size()-1 ; j++ ){
          r.add( up.get(j)+up.get(j+1) );
        }
        r.add(1);
        result.add(r);
      }
    }
    return result;
  }
}
