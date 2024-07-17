package com.lj.algorithm;


import com.lj.datastructure.notline.graph.GraphByArray;
import com.test.common.utils.MyArrayUtil;

import java.util.Arrays;
import java.util.List;

/**
 * 普利姆，最小生成树算法 （并不保证任意两点路径最短哦）
 * <p>
 * 有胜利乡有7个村庄(A, B, C, D, E, F, G) ，现在需要修路把7个村庄连通
 * 各个村庄的距离用边线表示(权) ，比如 A – B 距离 5公里
 * 问：如何修路保证各个村庄都能连通，并且总的修建公路总里程最短?
 */
public class PrimAlgorithm {
    
    GraphByArray originGraph;
    
    public PrimAlgorithm(GraphByArray originGraph){
        this.originGraph = originGraph;
    }
    
    /**
     * 最小生成树（图）
     * @return
     */
    public GraphByArray solve(){
        List<String> vertex = originGraph.getVertex();
        // 最小生成树的边的个数=总顶点个数-1
        int vertextCount = vertex.size();
        int edgesCount = vertextCount-1;
        String[][] edgesInfo = new String[edgesCount][];
        boolean[] visited = new boolean[vertex.size()];
        int[][] edge = originGraph.getEdge();
        
        //从第一个点开始遍历
        visited[1] = true;
        
        for(int i=0 ; i<edgesCount ; i++){
            int min = originGraph.getDisConnectNumber();
            int minRow = -1;
            int minCol = -1;
            
            for(int row=0 ; row<vertextCount; row++){
                for( int col=0 ; col<vertextCount; col++){
                    if(visited[row] && !visited[col] && min>edge[row][col]){
                        min = edge[row][col];
                        minRow = row;
                        minCol = col;
                    }
                }
            }
            // 从已知边集合，找到最小的边，插入
            edgesInfo[i] = new String[]{vertex.get(minRow),vertex.get(minCol),edge[minRow][minCol]+""};
            // 记录最小边的对方顶点为已访问，把对面顶点所有的边加入已知边集合
            visited[minCol] = true;
        }
        
        return new GraphByArray(vertex,edgesInfo,originGraph.getDisConnectNumber(),originGraph.getSelfNumber());
    }
    
    public static void main(String[] args) {
        GraphByArray graph = new GraphByArray(
                Arrays.asList("A", "B", "C", "D", "E", "F", "G")
                , new String[][]{
                {"A", "B", "5"},
                {"A", "G", "2"},
                {"A", "C", "7"},
                {"B", "G", "3"},
                {"B", "D", "9"},
                {"C", "E", "8"},
                {"D", "F", "4"},
                {"E", "G", "4"},
                {"E", "F", "5"},
                {"F", "G", "6"},
        },
                65535
        );
        MyArrayUtil.printArrayOf2(graph.getEdgePrettyFormat());
        System.out.println();
        PrimAlgorithm primAlgorithm = new PrimAlgorithm(graph);
            GraphByArray result = primAlgorithm.solve();
        System.out.println("最小生成树权值："+result.totalWeight());
        MyArrayUtil.printArrayOf2(result.getEdgePrettyFormat());
        System.out.println();
        MyArrayUtil.printArrayOf2(result.edgesInfo());
    }
    
}
