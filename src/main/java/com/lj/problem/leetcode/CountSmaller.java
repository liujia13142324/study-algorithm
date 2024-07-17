package com.lj.problem.leetcode;

import com.lj.algorithm.sort.QuickSort;
import com.test.common.utils.MyArrayUtil;
import org.junit.Test;

import java.util.*;

// 树状数组
public class CountSmaller {
  /**
   * 315. 计算右侧小于当前元素的个数
   * 给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
   *
   * 示例:
   *
   * 输入: [5,2,6,1]
   * 输出: [2,1,1,0]
   * 解释:
   * 5 的右侧有 2 个更小的元素 (2 和 1).
   * 2 的右侧仅有 1 个更小的元素 (1).
   * 6 的右侧有 1 个更小的元素 (1).
   * 1 的右侧有 0 个更小的元素.
   *
   * 来源：力扣（LeetCode）
   * 链接：https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self
   * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
   */
  @Test
  public void testCountSmaller(){
//		int params [] = new int[]{5,2,6,1};
//		int params[] = new int[]{26,78,27,100,33,67,90,23,66,5,38,7,35,23,52,22,83,51,98,69,81,32,78,28,94,13,2,97,3,76,99,51,9,21,84,66,65,36,100,41};
    int params[] = MyArrayUtil.getRandomArray(23000);
    Date date = new Date();
    List result = countSmaller(params);
    System.out.println( (new Date().getTime() - date.getTime()));
		System.out.println(result);
  }
  
  private List<Integer> countSmaller(int[] params) {
    List result = new ArrayList(params.length);
    int l = params.length;
    for( int i=0 ; i<l ;i++ ){
      int num = 0;
      for( int j=i+1 ; j<l ; j++ ){
        if( params[i]>params[j] ) num++;
      }
      result.add(num);
    }
    return result;
  }
  // 效率更差，时间复杂度也是n2
  private List<Integer> countSmaller2(int[] params) {
    List<Integer> result = new ArrayList<>();
    int l = params.length;
    if(params.length<=0){
      return result;
    }
    for( int i=0 ; i<l-1 ; i++ ){
      result.add( Math.max(quickSort2( params , i ,l-1  )-i,0) );
    }
    result.add(0);
    return result;
  }
  
  
  private List<Integer> countSmaller3 (int[] params) {
    Set<Integer> rangeOfV= new HashSet<>();
    for( int i=0 ; i<params.length ; i++ ){
      rangeOfV.add( params[i] );
    }
    int a[] = new int[ rangeOfV.size() ];
    int index = 0;
    for (int num : rangeOfV) {
      a[index++] = num;
    }
    Arrays.sort( a );
    
    int a2[] = new int[a.length];
    List<Integer> result = new ArrayList<>();
    TreeShapeArray2 array = new TreeShapeArray2( a2.length );
    for( int i=params.length-1 ; i>=0 ; i--){
      int pos = Arrays.binarySearch( a ,params[i] )+1;
      array.update( pos , array.a[pos]+1 );
      result.add(0, array.getSum(pos-1) );
    }
    
    return result;
  }
  
  //  一轮快排
  public int quickSort2(int num[], int start, int end ) {
    
    if (start < end) {
      int l = start;
      int r = end;
      int temp = num[l];
      int equalCount=0;
      while (l < r) {
        //比较的时候可以没有等号
        while (l < r && num[r] >= temp){
          r--;
          if( num[r] == temp ){
            equalCount++;
          }
        }
        if( l<r ){
          l++;
        }
        while (l < r && num[l] < temp){
          l++;
        }
        if( l<r ){
          r--;
        }
      }
//			num[l] = temp;
      return Math.max(l-equalCount,0);
    }
    return start;
  }
  
  
  @Test
  public void testQuckSort(){
    int num[] = new int[]{26,78,27,100,33,67,90,23,66,5,38,7,35,23,52,22,83,51,98,69,81,32,78,28,94,13,2,97,3,76,99,51,9,21,84,66,65,36,100,41};
    QuickSort q = new QuickSort();
    q.quickSort( num , 0,num.length-1 );
    MyArrayUtil.printArray( num );
  }
  
  @Test
  public void testTreeShapeAray(){
//		TreeShapeArray array = new TreeShapeArray(new int[]{2,1,35,6,4,2,1,3});
    TreeShapeArray2 array = new TreeShapeArray2(new int[]{2,1,35,6,4,2,1,3});
    System.out.println(array.getSum(5));
    array.update( 3,10 );
    System.out.println(array.getSum(7));
  }
  
  
  
  /**
   *  k为数字n二进制尾巴连续0的个数 ， 其中 2^k = i&(-i) 当 i不为0时候成立，所以下面的运算都是把0作为首位来计算的，所以做了一步换算，应该舍弃0存储为，
   *  从1开始存会好理解一些
   */
  class TreeShapeArray{
    int a[];
    int c[];
    // C[i] = A[i - 2^k+1] + A[i - 2^k+2] + ... + A[i];
    public TreeShapeArray (int [] a){
      this.a=a;
      c = new int[a.length];
      int indexC=0;
      for( int i=0 ; i<a.length ;i++ ){
        int pos = i+1;
        int posj = pos-lowbit(pos)+1;
        while( posj<=pos ){
          c[indexC] += a[posj-1];
          posj++;
        }
        indexC++;
      }
    }
    
    
    public int lowbit(int i){
      return i&(-i);
    }
    
    // SUMI = C[i] + C[i-2^k1] + C[(i - 2^k1) - 2^k2] + .....
    public int getSum( int n ){
      int sum=0;
      while( n>0 ){
        sum+=c[n-1];
        n-=lowbit( n );
      }
      return sum;
    }
    
  }
  // 存储位置从1开始的版本
  class TreeShapeArray2{
    int a[];
    int c[];
    // C[i] = A[i - 2^k+1] + A[i - 2^k+2] + ... + A[i];
    public TreeShapeArray2 (int [] a){
      this.a = new int[ a.length+1 ];
      for( int i=0 ; i<a.length ; i++ ){
        this.a[i+1] = a[i];
      }
      c = new int[this.a.length];
      int indexC=1;
      for( int i=1 ; i<this.a.length ;i++ ){
        int posj = i-lowbit(i)+1;
        while( posj<=i ){
          c[indexC] += a[posj-1];
          posj++;
        }
        indexC++;
      }
    }
    public TreeShapeArray2 (int length){
      this.a = new int[ length+1 ];
      this.c = new int[ length+1 ];
    }
    
    public int lowbit(int i){
      return i&(-i);
    }
    
    // SUMI = C[i] + C[i-2^k1] + C[(i - 2^k1) - 2^k2] + .....
    public int getSum( int n ){
      int sum=0;
      while( n>0 ){
        sum+=c[n];
        n-=lowbit( n );
      }
      return sum;
    }
    
    //A[i] 包含于 C[i]、C[i + 2^k]、C[(i + 2^k) + 2^k]...；
    public void update( int i,int value ){
      int old = a[i];
      a[i] = value;
      while( i<c.length){
        c[i] = c[i] - old + value;
        i+=lowbit(i);
      }
    }
    
  }
}
