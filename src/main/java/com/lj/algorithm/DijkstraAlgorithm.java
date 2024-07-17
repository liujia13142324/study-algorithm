package com.lj.algorithm;

import com.lj.datastructure.notline.graph.GraphByArray;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 迪杰斯特拉(Dijkstra)算法是典型最短路径算法，用于计算一个结点到其他结点的最短路径（加粗）。
 *  它的主要特点是以起始点为中心向外层层扩展(广度优先搜索思想)，直到扩展到终点为止。
 */
public class DijkstraAlgorithm {
    
    GraphByArray graph;
    
    public DijkstraAlgorithm(GraphByArray graph) {
        this.graph = graph;
    }
    
    public DijkstraResult resolve(int startIdx) {
        List<String> vertex = graph.getVertex();
        int[][] edge = graph.getEdge();
        int[] dis = new int[vertex.size()];
        List<String>[] pathInfos = new ArrayList[vertex.size()];
        boolean[] already = new boolean[vertex.size()];
        int disConnectNumber = graph.getDisConnectNumber();
        
        for (int i = 0; i < dis.length; i++) {
            if (i == startIdx) {
                dis[i] = 0;
            } else {
                dis[i] = disConnectNumber;
            }
        }
        
        for (int i = 0; i < dis.length; i++) {
            int idx = selectMin(dis, already);
            // 修改 dis，记录结果
            for (int j = 0; j < edge[idx].length; j++) {
                if (edge[idx][j] != disConnectNumber) {
                    
                    if( edge[idx][j]+dis[idx] < dis[j] ){
                        dis[j] = dis[idx] + edge[idx][j];
                        pathInfos[j] = new ArrayList<>();
                        if(pathInfos[idx] != null){
                            pathInfos[j].addAll(pathInfos[idx]);
                        }
                        pathInfos[j].add( vertex.get(idx)+vertex.get(j)+"("+edge[idx][j]+")" );
                    }
                }
            }
        }
        
        return new DijkstraResult(startIdx,dis,pathInfos);
    }
    
    /**
     * 选择权值最小的边对应的点的索引
     *
     * @param dis
     * @param already
     * @return
     */
    private int selectMin(int[] dis, boolean[] already) {
        int minIdx = 0;
        int minV = Integer.MAX_VALUE;
        for (int i = 0; i < dis.length; i++) {
            if (!already[i] && minV > dis[i]) {
                minIdx = i;
                minV = dis[i];
            }
        }
        already[minIdx] = true;
        return minIdx;
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
    
        DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm(graph);
        DijkstraResult resolve = dijkstraAlgorithm.resolve(6);
        System.out.println(Arrays.toString(resolve.getResults()));
        System.out.println(resolve.getPrettyResult());
        resolve.showPrettyPath();
    }
    
    
    
    @AllArgsConstructor
    public class DijkstraResult{
        int startIdx;
        int[] results;
        List[] pathInfos;
        
        public int[] getResults() {
            return results;
        }
        
        public List[] getPathInfos() {
            return pathInfos;
        }
        
        public List<String> getPrettyResult(){
            List<String> vertex = graph.getVertex();
            List<String> result = new ArrayList<>();
            for(int i=0; i<results.length; i++){
                if(i == startIdx){continue;}
                result.add( vertex.get(startIdx)+vertex.get(i)+"("+ results[i] +")" );
            }
            return result;
        }
        
        public void showPrettyPath(){
            List<String> vertex = graph.getVertex();
            for(int idx=0 ; idx<pathInfos.length; idx++){
                if(idx == startIdx){
                    continue;
                }
                List<String> path = pathInfos[idx];
                System.out.println(vertex.get(startIdx)+vertex.get(idx)+"("+results[idx]+"):\t"+ StringUtils.join(path,"->"));
            }
        }
    }
    
}
