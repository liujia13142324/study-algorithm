package com.lj.algorithm;

import com.lj.datastructure.notline.graph.GraphByArray;
import com.lj.study.common.utils.MyArrayUtil;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 克鲁斯卡尔(Kruskal)算法，是用来求加权连通图的最小生成树的算法
 * 作用和prim算法是一样的，性能比它好
 * <p>
 * 基本思想：按照权值从小到大的顺序选择n-1条边，并保证这n-1条边不构成回路
 * 具体做法：首先构造一个只含n个顶点的森林，然后依权值从小到大从连通网中选择边加入到森林中，并使森林中不产生回路，
 * 直至森林变成一棵树为止
 */
public class KruskalAlgorithm {
    
    private GraphByArray graph;
    
    public KruskalAlgorithm(GraphByArray graph) {
        this.graph = graph;
    }
    
    public GraphByArray solve() {
        int[][] edgesInfo = graph.edgesInfo2();
        List<String> vertex = graph.getVertex();
        // 边按照从小到大排好序（为了整体路径最短）
        Arrays.sort(edgesInfo, Comparator.comparingInt(i -> i[2]));
        
//        MyArrayUtil.printArrayOf2(edgesInfo);
        /**
         *  ends[i] 记录已构建的最小图，从i出发的终点是哪个点
         */
        int[] ends = new int[vertex.size()];
        for(int i=0,j=ends.length; i<j; i++){
            ends[i] = i;
        }
        int[][] resultEdges = new int[vertex.size()-1][];
        int idx=0;
        
        for(int i=0,j=edgesInfo.length,k=vertex.size()-1; i<j && idx < k; i++){
            int[] edge = edgesInfo[i];
            int end1 = getEnd(ends,edge[0]);
            int end2 = getEnd(ends,edge[1]);
            
            // 防止出现回路
            if(end1 != end2){
                // 修改end1而不是end[edge[0]]，使用三点回路分析理解，end1有两种可能，
                // 1：就是edge[0]，当这个点是第一次加入结果图
                // 2: edge[0]之前的终点，这个点已经是结果图的一部分。
                // 当新的节点加入后，有可能要修改原来的点的终点，这样才对，所以用end1。
                // 至于用debug会看到ends里面会看到有些点的终点不对，是因为没有新的边的加入要经过这些点，所以不就会再更新点了。
                
                //
                ends[end1] = end2;
                resultEdges[idx++] = edge.clone();
            }
        }
        
        return GraphByArray.parse(vertex,resultEdges,graph.getDisConnectNumber());
    }
    
    /**
     *
     * @return
     */
    private int getEnd(int[] ends, int i) {
        if(ends[i] != i){
            return getEnd(ends, ends[i]);
        }
        return i;
    }
    
    public static void main(String[] args) {
        GraphByArray graph = new GraphByArray(
                Arrays.asList("A", "B", "C", "D", "E", "F", "G")
                , new String[][]{
                {"A", "B", "12"},
                {"A", "F", "16"},
                {"A", "G", "14"},
                {"B", "C", "10"},
                {"B", "F", "7"},
                {"C", "D", "3"},
                {"C", "E", "5"},
                {"C", "F", "6"},
                {"D", "E", "4"},
                {"E", "F", "2"},
                {"E", "G", "8"},
                {"F", "G", "9"},
           /*     {"A", "G", "2"},
                {"B", "G", "3"},
                {"D", "F", "4"},
                {"E", "G", "4"},
                {"A", "B", "5"},
                {"E", "F", "5"},
                {"F", "G", "6"},
                {"A", "C", "7"},
                {"C", "E", "8"},
                {"B", "D", "9"},*/
        }, 65535
        );
    
        MyArrayUtil.printArrayOf2(graph.getEdgePrettyFormat());
        System.out.println();
    
        KruskalAlgorithm kruskalAlgorithm = new KruskalAlgorithm(graph);
        GraphByArray result = kruskalAlgorithm.solve();
    
        System.out.println("totalWeight:"+result.totalWeight());
        MyArrayUtil.printArrayOf2(result.getEdgePrettyFormat());
        MyArrayUtil.printArrayOf2(result.edgesInfo());
    
        System.out.println("PrimAlgorithm=========>");
        PrimAlgorithm primAlgorithm = new PrimAlgorithm(graph);
        GraphByArray result2 = primAlgorithm.solve();
        System.out.println("totalWeight:"+result2.totalWeight());
        MyArrayUtil.printArrayOf2(result2.getEdgePrettyFormat());
        MyArrayUtil.printArrayOf2(result2.edgesInfo());
    }
    
}
