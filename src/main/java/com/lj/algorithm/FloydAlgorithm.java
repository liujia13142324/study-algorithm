
package com.lj.algorithm;

import com.lj.datastructure.notline.graph.GraphByArray;
import com.lj.study.common.utils.MyArrayUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * 和Dijkstra算法一样，弗洛伊德(Floyd)算法也是一种用于寻找给定的加权图中顶点间最短路径的算法。
 * 该算法名称以创始人之一、1978年图灵奖获得者、斯坦福大学计算机科学系教授罗伯特·弗洛伊德命名
 *
 * 弗洛伊德算法(Floyd)计算图中各个顶点之间的最短路径
 *
 * 迪杰斯特拉算法用于计算图中某一个顶点到其他顶点的最短路径。
 *
 * 弗洛伊德算法 VS 迪杰斯特拉算法：迪杰斯特拉算法通过选定的被访问顶点，求出从出发访问顶点到其他顶点的最短路径；
 * 弗洛伊德算法中每一个顶点都是出发访问点，所以需要将每一个顶点看做被访问顶点，求出从每一个顶点到其他顶点的最短路径。
 */
public class FloydAlgorithm {

    private GraphByArray graph;
    
    public FloydAlgorithm(GraphByArray graph){
        this.graph = graph;
    }

    public FloydResult solve(){
        
        int vertexCount = graph.getVertex().size();
        int[][] dis = new int[vertexCount][];
        for( int i=0 ; i<dis.length; i++ ){
            dis[i] = graph.getEdge()[i].clone();
            dis[i][i] = 0;
        }
        
        for(int k=0 ; k<vertexCount; k++){
            // 以k 为中心点，从i到j
            for( int i=0; i<vertexCount; i++ ){
                for(int j=0; j<vertexCount; j++){
                    dis[i][j] = Math.min( dis[i][j] , dis[i][k]+dis[k][j] );
                }
            }
        }
        
        return new FloydResult(dis);
    }
    
    @AllArgsConstructor
    @Getter
    class FloydResult{
        int[][] dis;
        public void prettyShow(){
            List<String> vertex = graph.getVertex();
            System.out.print("\t");
            MyArrayUtil.printArray(vertex,"\t");
            for(int i=0; i<dis.length; i++){
                System.out.print(vertex.get(i)+"\t");
                MyArrayUtil.printArray(dis[i],"\t");
            }
        }
    }
    
    
    public static void main(String[] args) {
        GraphByArray graph = new GraphByArray(
                Arrays.asList("A", "B", "C", "D", "E", "F", "G")
                , new String[][]{
                {"A", "B", "5"},
                {"A", "C", "7"},
                {"A", "G", "2"},
                {"B", "D", "9"},
                {"B", "G", "3"},
                {"C", "E", "8"},
                {"D", "F", "4"},
                {"E", "F", "5"},
                {"E", "G", "4"},
                {"F", "G", "6"},
        }, 65536
        );
        FloydAlgorithm floydAlgorithm = new FloydAlgorithm(graph);
        FloydResult result = floydAlgorithm.solve();
//        MyArrayUtil.printArrayOf2(result.dis,"\t");
        result.prettyShow();
        
        System.out.println("DijkstraAlgorithm ---> ");
        DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm(graph);
        for(int i=0 ; i<graph.getVertex().size(); i++){
            DijkstraAlgorithm.DijkstraResult r = dijkstraAlgorithm.resolve(i);
            System.out.println(Arrays.toString(r.getResults()));
        }
    
    }
    
}
