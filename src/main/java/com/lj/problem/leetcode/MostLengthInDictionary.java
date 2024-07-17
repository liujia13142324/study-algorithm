package com.lj.problem.leetcode;


import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class MostLengthInDictionary {
  
  /**
   * 给出一个字符串数组words组成的一本英语词典。从中找出最长的一个单词，该单词是由words词典中其他单词逐步添加一个字母组成。若其中有多个可行的答案，则返回答案中字典序最小的单词。
   *
   * 若无答案，则返回空字符串。
   *
   * 输入：
   * words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
   * 输出："apple"
   * 解释：
   * "apply"和"apple"都能由词典中的单词组成。但是"apple"的字典序小于"apply"。
   *
   * 来源：力扣（LeetCode）
   * 链接：https://leetcode-cn.com/problems/longest-word-in-dictionary
   * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
   *
   */
  @Test
  public void test(){
    String result = longestWord2( new String[]{"a", "banana", "app", "appl", "ap", "apply", "apple"} );
    System.out.println(result);
  }
  
  //暴力破解1
  public String longestWord(String[] words) {
    String answer="";
    Set<String> wordsSet = new HashSet<>();
    for( String word:words ){
      wordsSet.add(word);
    }
    
    for( int i=0 ; i<words.length ; i++ ){
      if( words[i].length()>answer.length()||( words[i].length() == answer.length() && words[i].compareTo( answer ) < 0 )){
        boolean success = true;
        for( int j=words[i].length()-1 ; j>0; j-- ){
          if( !wordsSet.contains( words[i].substring(0,j) ) ){
            success=false;
          }
        }
        if(success){
          answer=words[i];
        }
      }
    }
    
    return answer;
  }
  
  
  //方法二，使用前缀树 ， 性能更差....
  public String longestWord2(String[] words) {
    Tries tree = new Tries(words);
    int i=0;
    for( String word : words ){
      tree.insert( word, i++);
    }
    return tree.dfsForLongest();
  }
  
  
  public class Tries{
    //前缀树
    String [] words;
    TreeNode root;
    
    public Tries(String[]words){
      TreeNode root = new TreeNode();
      root.children = new HashMap<>();
      this.root=root;
      this.words=words;
    }
    
    public void insert(String word,int index){
      TreeNode root = this.root;
      for( char c : word.toCharArray() ){
        root.children.putIfAbsent(c, new TreeNode(c));
        root = root.children.get(c);
      }
      root.end=index;
    }
    
    public String dfsForLongest(){
      String result="";
      Stack<TreeNode> stack = new Stack<>();
      stack.push(this.root);
      while( stack.size()>0 ){
        TreeNode node = stack.pop();
        if( node.end>=0 || node == root ){
          if(node.end>=0){
            String str = words[node.end];
            if( str.length()>result.length() || ( str.length()==result.length() && str.compareTo( result ) < 0 ) ){
              result = str;
            }
          }
          
          if(node.children!=null){
            for(TreeNode n:node.children.values()){
              stack.push(n);
            }
          }
        }
      }
      return result;
    }
  }
  
  public class TreeNode{
    char value;
    HashMap<Character,TreeNode> children;
    int end=-1;
  
    public TreeNode(char value) {
      this.value = value;
      children = new HashMap<>();
    }
    public TreeNode() {
      children = new HashMap<>();
    }
  }
  
}
