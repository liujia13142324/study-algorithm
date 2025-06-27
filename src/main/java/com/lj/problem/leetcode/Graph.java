package com.lj.problem.leetcode;

import com.alibaba.fastjson.JSONArray;
import com.lj.study.common.utils.MyArrayUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class Graph {
  
  @Test
  public void test(){
    String str = "[[],[3],[],[1],[]]";
    System.out.println(str);
    JSONArray arr = JSONArray.parseArray( str );
    int[][] params = new int[arr.size()][];
    for( int i=0 ; i<arr.size();i++ ){
      params[i] = new int[ arr.getJSONArray(i ).size() ];
      for( int j=0 ; j<arr.getJSONArray(i).size(); j++ ){
        params[i][j] = arr.getJSONArray(i).getInteger(j);
      }
    }
    System.out.println(isBipartite(params ));
  }
  
  /**
   * 前言
   * 对于图中的任意两个节点 uu 和 vv，如果它们之间有一条边直接相连，那么 uu 和 vv 必须属于不同的集合。
   *
   * 如果给定的无向图连通，那么我们就可以任选一个节点开始，给它染成红色。随后我们对整个图进行遍历，将该节点直接相连的所有节点染成绿色，表示这些节点不能与起始节点属于同一个集合。我们再将这些绿色节点直接相连的所有节点染成红色，以此类推，直到无向图中的每个节点均被染色。
   *
   * 如果我们能够成功染色，那么红色和绿色的节点各属于一个集合，这个无向图就是一个二分图；如果我们未能成功染色，即在染色的过程中，某一时刻访问到了一个已经染色的节点，并且它的颜色与我们将要给它染上的颜色不相同，也就说明这个无向图不是一个二分图。
   *
   * 算法的流程如下：
   *
   * 我们任选一个节点开始，将其染成红色，并从该节点开始对整个无向图进行遍历；
   *
   * 在遍历的过程中，如果我们通过节点 uu 遍历到了节点 vv（即 uu 和 vv 在图中有一条边直接相连），那么会有两种情况：
   *
   * 如果 vv 未被染色，那么我们将其染成与 uu 不同的颜色，并对 vv 直接相连的节点进行遍历；
   *
   * 如果 vv 被染色，并且颜色与 uu 相同，那么说明给定的无向图不是二分图。我们可以直接退出遍历并返回 \text{False}False 作为答案。
   *
   * 当遍历结束时，说明给定的无向图是二分图，返回 \text{True}True 作为答案。
   *
   * 我们可以使用「深度优先搜索」或「广度优先搜索」对无向图进行遍历，下文分别给出了这两种搜索对应的代码。
   *
   * 注意：题目中给定的无向图不一定保证连通，因此我们需要进行多次遍历，直到每一个节点都被染色，或确定答案为 \text{False}False 为止。每次遍历开始时，我们任选一个未被染色的节点，将所有与该节点直接或间接相连的节点进行染色
   *
   * 作者：LeetCode-Solution
   * 链接：https://leetcode-cn.com/problems/is-graph-bipartite/solution/pan-duan-er-fen-tu-by-leetcode-solution/
   * 来源：力扣（LeetCode）
   * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
   * @param graph
   * @return
   */
  
  public boolean isBipartite(int[][] graph) {
    Map<Integer , Boolean> map2 = new HashMap<>();
    Map<Integer , Integer> map = new HashMap<>();
    //可能不是连通图
    for( int i=0,j=graph.length ; i<j ; i++ ){
      while( !map.containsKey(i) ){
        boolean result = startForEach( graph , i  , 1 , map , map2 );
        if(!result){
          return false;
        }
      }
    }
//    System.out.println(map);
//    System.out.println(map2);
    return true;
  }
  
  private boolean startForEach(int[][] graph, int idx, int sign , Map<Integer, Integer> map, Map<Integer, Boolean> map2) {
  
    int []joint = graph[idx];
    map.put( idx , sign);
    map2.put(idx, true);
  
    for( int i=0 ; i<joint.length ; i++ ){
      if( graph[joint[i]].length ==0 ) continue;
      boolean r = widthFirst( graph , joint[i] ,sign , map , map2 );
      if( !r ) return false;
    }
    
    return true;
  }
  
  private boolean widthFirst(int[][] graph, int i, int sign, Map<Integer, Integer> result, Map<Integer, Boolean> isVisit) {
    if( result.containsKey(i) && result.get(i)!= -sign ){
      return false;
    }
    if( isVisit.containsKey( i ) ){
      return true;
    }
    isVisit.put( i ,true );
    result.put( i ,-sign );
    int []joint = graph[i];
    for( int j=0 ; j<joint.length ; j++ ){
      if( graph[joint[j]].length ==0 ) continue;
      boolean r = widthFirst( graph , joint[j] , -sign , result, isVisit);
      if( !r ) return false;
    }
    return true;
  }
  
  
  /**
   * 图像渲染
   * @param image
   * @param sr
   * @param sc
   * @param newColor
   * @return
   * 有一幅以二维整数数组表示的图画，每一个整数表示该图画的像素值大小，数值在 0 到 65535 之间。
   *
   * 给你一个坐标 (sr, sc) 表示图像渲染开始的像素值（行 ，列）和一个新的颜色值 newColor，让你重新上色这幅图像。
   *
   * 为了完成上色工作，从初始坐标开始，记录初始坐标的上下左右四个方向上像素值与初始坐标相同的相连像素点，接着再记录这四个方向上符合条件的像素点与他们对应四个方向上像素值与初始坐标相同的相连像素点，……，重复该过程。将所有有记录的像素点的颜色值改为新的颜色值。
   *
   * 最后返回经过上色渲染后的图像。
   *
   * 来源：力扣（LeetCode）
   * 链接：https://leetcode-cn.com/problems/flood-fill
   * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
   */
  public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
    if(image[sr][sc]==newColor){
      return image;
    }
    int oldColor = image[sr][sc];
    image[sr][sc]=newColor;
    if( sr-1>=0 && oldColor== image[sr-1][sc]){
      floodFill(image,sr-1,sc,newColor);
    }
    if( sr+1<=image.length-1 && oldColor == image[sr+1][sc]){
      floodFill(image,sr+1,sc,newColor);
    }
    if( sc-1>=0 && oldColor == image[sr][sc-1]){
      floodFill(image,sr ,sc-1,newColor);
    }
    if( sc+1<=image[0].length-1 && oldColor == image[sr][sc+1]){
      floodFill(image,sr,sc+1,newColor);
    }
    return image;
  }
  
 
  
  @Test
  public void testFloodFill(){
    int image [][] = new int[][]{{0,0,0},{0,1,1}};
    floodFill(image,1,1,1);
    MyArrayUtil.printArrayOf2(image);
  }
  
}
