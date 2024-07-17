package com.lj.problem.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//无向图的克隆
public class CloneGraph {
  
  @Test
  public void testArrayList(){
    List list = new ArrayList(10);
    list.add(1,30);
    list.add(5,2);
    System.out.println(list);
  }
  
  Map<Integer , Node> cloned= new HashMap<>();
  
  public Node cloneGraph(Node node) {
    
    if( node==null ){
      return null;
    }
    if(node.neighbors.size()==0){
      Node n = new Node();
      n.val=1;
      return n;
    }
    Node newNode = new Node();
    newNode.val = node.val;
    cloned.put( newNode.val , newNode );
    
    for( int i=0 ; i<node.neighbors.size() ; i++ ){
      if( cloned.containsKey( node.neighbors.get(i).val ) ){
        newNode.neighbors.add(cloned.get(node.neighbors.get(i).val));
      }else{
        newNode.neighbors.add(cloneGraph( node.neighbors.get(i)));
      }
    }
    
    return newNode;
  }
  class Node {
    public int val;
    public List<Node> neighbors;
    
    public Node() {
      val = 0;
      neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val) {
      val = _val;
      neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val, ArrayList<Node> _neighbors) {
      val = _val;
      neighbors = _neighbors;
    }
  }
}
