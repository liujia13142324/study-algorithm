package com.lj.problem.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

/**
 * 有效括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ValidParenthese {
  
  public static void main(String[] args) {
    String input = "{[}]";
    boolean r = isValid(input);
    System.out.println(r);
  }
  
  private static boolean isValid(String input) {
    new ArrayList(new HashSet(new ArrayList()));
    if("".equals(input)){return true;}
    Stack<Character> stack = new Stack();
    for(int i=0 ; i<input.length() ; i++){
      Character c = input.charAt(i);
      if( c == '(' || c == '[' || c == '{'){
        stack.push(c);
      }else{
        if(stack.isEmpty()){
          return false;
        }
        Character top = stack.pop();
        if( !jugeIsMatch(top,c) ){
          return false;
        }
      }
    }
    return stack.isEmpty();
  }
  
  private static boolean jugeIsMatch(Character left, Character right) {
    
    return left == '(' ? right == ')' : left == '{' ? right == '}' : left == '[' ? right== ']' : false;
  }
  
}
