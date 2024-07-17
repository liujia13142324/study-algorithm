package com.lj.problem.leetcode;

import org.junit.Test;

public class CheckHuiwenSubstr {
  
  @Test
  public void testCount(){
    System.out.println(countSubstrings("aabaa"));
  }
  
  public int countSubstrings(String s) {
    int result = 0;
    
    for( int i=0 ; i<s.length() ; i++ ){
      for( int j=0 ; j+i<s.length() ; j++ ){
        if( checkIsHuiwen( s , i , i+j ) ){
          result++;
        }
      }
    }
    return result;
  }
 
  
  
  
  public boolean checkIsHuiwen(String s, int startPos , int endPos){
    if( startPos==endPos ){
      return true;
    }
    boolean isHuiwen = true;
    while( startPos<endPos ){
      if( s.charAt( startPos )!= s.charAt(endPos) ){
        isHuiwen=false;
        break;
      }
      startPos++;
      endPos--;
    }
    return isHuiwen;
  }
  
  @Test
  public void testIsHuiWen(){
    System.out.println(checkIsHuiwen("sada",1,1));
  }
  
  
}
