package com.lj.problem.leetcode;

/**
 * 有效括号嵌套深度
 *
 * 输入：seq = "(()())"
 * 输出：[0,1,1,1,1,0]
 *
 * 输入：seq = "()(())()"
 * 输出：[0,0,0,1,1,0,1,1]
 * 解释：本示例答案不唯一。
 * 按此输出 A = "()()", B = "()()", max(depth(A), depth(B)) = 1，它们的深度最小。
 * 像 [1,1,1,0,0,1,1,1]，也是正确结果，其中 A = "()()()", B = "()", max(depth(A), depth(B)) = 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-nesting-depth-of-two-valid-parentheses-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class DepthOfParenthese {
  
  public static void main(String[] args) {
    
    int r[] = new DepthOfParenthese().maxDepthAfterSplit("(()((())))");
    for(int i=0 ; i<r.length ; i++){
      System.out.print(r[i]);
    }
    System.out.println();
  
  }
  
  /**使 max(depth(A), depth(B)) 的可能取值最小”。这句话其实相当于让 A 字符串和 B 字符串的 depth 尽可能的接近。
   *  为什么呢？因为 seq 对应的栈上，每个左括号都对应一个深度，而这个左括号，要么是 A 的，要么是 B 的。所以，
   *  栈上的左括号只要按奇偶分配给A和B就可以啦！时间复杂度很明显是 O(n)O(n) 的，空间复杂度也是 O(n)O(n)
   *  （如果算返回的变量的话）。
   *    作者：sweetiee
   *    链接：https://leetcode-cn.com/problems/maximum-nesting-depth-of-two-valid-parentheses-strings/solution/ti-mian-shuo-ming-ti-mu-jiang-jie-shuo-hao-fa-wan-/
   *    来源：力扣（LeetCode）
   *    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
   */


  public int[] maxDepthAfterSplit(String seq) {
    int r[] = new int[seq.length()];
    // 计算整个括号的深度
    int depth = 0;
    int right = 0;
    for( int i=0 ; i<seq.length() ; i++ ){
      Character c = seq.charAt(i);
      if(c=='('){
        depth++;
        if(depth % 2 == 1){
          r[i] = 1;
        }else if(depth % 2 == 0){
          r[i] = 0;
        }
      }else{
        right ++;
        if(right % 2 == 1){
          r[i] = 1;
        }else if(right % 2 == 0){
          r[i] = 0;
        }
      }
    }
    return r;
  }
  
  /**
   * 下面给出了括号序列 (()(())()) 在每一个字符处的嵌套深度：
   *
   * 括号序列   ( ( ) ( ( ) ) ( ) )
   * 下标编号   0 1 2 3 4 5 6 7 8 9
   * 嵌套深度   1 2 2 2 3 3 2 2 2 1
   * 知道如何计算嵌套深度，问题就很简单了：只要在遍历过程中，我们保证栈内一半的括号属于序列 A，一半的括号属于序列 B，那么就能保证拆分后最大的嵌套深度最小，是当前最大嵌套深度的一半。要实现这样的对半分配，我们只需要把奇数层的 ( 分配给 A，偶数层的 ( 分配给 B 即可。对于上面的例子，我们将嵌套深度为 1 和 3 的所有括号 (()) 分配给 A，嵌套深度为 2 的所有括号 ()()() 分配给 B。
   *
   * 此外，由于在这个问题中，栈中只会存放 (，因此我们不需要维护一个真正的栈，只需要用一个变量模拟记录栈的大小。
   *
   * 作者：LeetCode-Solution
   * 链接：https://leetcode-cn.com/problems/maximum-nesting-depth-of-two-valid-parentheses-strings/solution/you-xiao-gua-hao-de-qian-tao-shen-du-by-leetcode-s/
   * 来源：力扣（LeetCode）
   * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
   */
  public int[] maxDepthAfterSplit2(String seq) {
  
    int [] r = new int[seq.length()];
    char[] cs = seq.toCharArray();
    int d = 0;
    for(int i=0 ; i<cs.length ; i++){
      if(cs[i] == '('){
        d++;
        r[i] = d&1 ; //d%2; // 奇&1=1   偶&1=0
      }else{
        r[i] = d&1; //d%2;
        d--;
      }
    }
    return r;
    
  }
  
  public int countDepth(String seq){
    int depth = 0;
    int count=0;
    for(int i=0 ; i<seq.length() ; i++){
      if( seq.charAt(i) == '(' ){
        count++;
      }else{
        if(count>depth){
          depth = count;
        }
        count = 0;
      }
    }
    return depth;
  }
  
  
  

}













