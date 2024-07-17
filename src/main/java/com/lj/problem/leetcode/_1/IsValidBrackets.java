package com.lj.problem.leetcode._1;


import com.lj.datastructure.line.statck.Stack;

import java.util.LinkedList;

/**
 *  20. 有效的括号
     给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     
     有效字符串需满足：
     
     1、左括号必须用相同类型的右括号闭合。
     2、左括号必须以正确的顺序闭合。
     3、每个右括号都有一个对应的相同类型的左括号。
     
     
     示例 1：
     
     输入：s = "()"
     输出：true
     示例 2：
     
     输入：s = "()[]{}"
     输出：true
     示例 3：
     
     输入：s = "(]"
     输出：false
     
     
     提示：
     
     1 <= s.length <= 104
     s 仅由括号 '()[]{}' 组成
 */
public class IsValidBrackets {
    
    public boolean isValid(String s) {
        
        LinkedList<Character> stack = new LinkedList<>();
        
        char c,pop;
        
        for (int i = 0, k = s.length(); i < k; i++) {
            c = s.charAt(i);
            if (c == '(' || c == '[' || c =='{') {
                stack.push(c);
            }else if (!stack.isEmpty()){
                pop = stack.pop();
                if (c == ')' && pop != '(') {
                    return false;
                }else if (c == '}' && pop != '{') {
                    return false;
                }else if (c == ']' && pop != '[') {
                    return false;
                }
            }else {
                return false;
            }
        }
        
        return stack.isEmpty();
    }
    
    public static void main(String[] args) {
        System.out.println(new IsValidBrackets().isValid("([)]"));
    }
}
