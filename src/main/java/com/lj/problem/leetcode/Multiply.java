package com.lj.problem.leetcode;

import org.junit.Test;

import java.math.BigDecimal;

//字符串相乘
public class Multiply {
  
  @Test
  public void test(){
    String num1="999";
    String num2="999";
    System.out.println( multiply( num1 , num2 ) );
    BigDecimal b = new BigDecimal(num1);
    System.out.println(b.multiply( new BigDecimal(num2) ).toString());
  }
  
  public String multiply(String num1, String num2) {
    if( num1==null || num2==null ){
      return null;
    }
    if("0".equals(num1) || "0".equals(num2)){
      return "0";
    }
    if("1".equals(num1)){
      return num2;
    }
    if("1".equals(num2)){
      return num1;
    }
    return generateAddStrs2(num1,num2);
  }
  
  /**
   *
   * @param num1 被乘数
   * @param num2 乘数
   * @return
   */
  private String generateAddStrs(String num1, String num2) {
    String result = "0";
    int length1 = num1.length();
    for( int i=num2.length()-1 ; i>=0; i-- ){
      int n = (num2.charAt(i)-'0');
      if( n==0 ) {
        continue;
      }
      byte[]r = new byte[length1+1];
      int up=0;
      for( int j=length1-1 ; j>=0; j--){
        int v =  (n*(num1.charAt(j)-'0') + up);
        up = (byte) (v/10);
        r[j+1] = (byte) (v%10);
      }
      if(up>0){
        r[0]= (byte) up;
      }
      StringBuffer s = new StringBuffer();
      for( int k=0 ; k<=length1 ; k++ ){
        if( k==0&&r[0]==0 ){
          continue;
        }
        s.append(r[k]);
      }
      for( int k=0,m=num2.length()-1-i; k<m ;k++ ){
        s.append(0);
      }
      result = addStrByByte(result.getBytes() , s.toString().getBytes() );
    }
    return result;
  }
  
  private String generateAddStrs2(String num1, String num2) {
    int n=num1.length() , m = num2.length();
    short shorts[] = new short[ n+m ];
    
    for( int i=n-1 ; i>=0 ; i-- ){
      byte c1 = (byte) (num1.charAt( i )-'0');
      for( int j=m-1 ; j>=0 ; j-- ){
        shorts[ i+j+1 ] +=  c1 * ( num2.charAt(j)-'0' );
      }
    }
    int up=0;
    for( int i=shorts.length-1 ; i>=0 ; i-- ){
      int num = shorts[ i ]+up;
      up = num /10;
      shorts[ i ] = (byte) (num % 10);
    }
    StringBuffer r = new StringBuffer();
    for( int i=0 ; i<shorts.length;i++  ){
      if( i==0 && shorts[i]==0 ){
        continue;
      }
      r.append(shorts[i]);
    }
    return r.toString();
  }
  
 
  @Test
  public void testStrAdd(){
    String s1="123150012312";
    String s2="132125123123123";
    System.out.println( addStrByByte(s1.getBytes(),s2.getBytes()) );
    System.out.println(new BigDecimal(s1).add( new BigDecimal(s2) ).toString());
  }
  
  
  private String addStrByByte( byte[] a , byte [] b ){
    boolean carry = false;
    byte minus = '0'*2;
    byte[]c;
    if(a.length>=b.length){
      c=a;
    }else{
      c=b;
    }
    int indexA=a.length-1;
    int indexB=b.length-1;
    int indexC=c.length-1;
    while( indexA >=0 || indexB>=0 ){
      int r=0;
      if(indexA>=0 && indexB>=0){
        r = a[indexA--] + b[indexB--] - minus;
      }else if( indexA>=0 ){
        r = a[indexA--] - '0';
      }else if( indexB>=0 ){
        r = b[indexB--] - '0';
      }
      if(carry) r++;
      if(r>=10){
        c[indexC--]= (byte) (r%10);
        carry=true;
      }else{
        c[indexC--]=(byte)r;
        carry =false;
      }
    }
    StringBuffer s = new StringBuffer();
    if( carry ){
      s.append(1);
      for( byte bs : c ){
        s.append(bs);
      }
    }else{
      for( byte bs : c ){
        s.append(bs);
      }
    }
    
    return s.toString();
  }
  
  
}
