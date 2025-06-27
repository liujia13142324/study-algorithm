package com.lj.datastructure.notline.graph;

import com.lj.datastructure.line.queue.LoopArrayQueue;
import com.lj.study.common.utils.MyArrayUtil;

import java.util.Arrays;
import java.util.List;

/**
 * 邻接矩阵表达边
 */
public class GraphByArray {
    
    /**
     * 顶点
     */
    List<String> vertex;
    /**
     * 描述边的关系
     */
    int[][] edge;
    
    /**
     * 表示不能连接的数字
     */
    int disConnectNumber;
    
    /**
     * 表示和自己和自己关联的数字
     */
    int selfNumber;
    
    /**
     * 边的数量
     */
    int edgeCount;
    
    public GraphByArray() {
    }
    
    public List<String> getVertex() {
        return vertex;
    }
    
    public int[][] getEdge() {
        return edge;
    }
    
    public String[][] getEdgePrettyFormat() {
        String[][] edge = new String[this.edge.length][];
        for(int i=0 ; i<this.edge.length ; i++){
            String[] row = new String[this.edge[i].length];
            for(int j=0 ; j<this.edge[i].length ; j++){
                row[j] = (this.edge[i][j] == disConnectNumber ? "N" : this.edge[i][j]+"");
            }
            edge[i] = row;
        }
        return edge;
    }
    
    public int getDisConnectNumber() {
        return disConnectNumber;
    }
    
    public int getSelfNumber() {
        return selfNumber;
    }
    
    public GraphByArray(List<String> vertex, String[][] edgesInfo) {
        this(vertex,edgesInfo,0);
    }
    
    public GraphByArray(List<String> vertex, String[][] edgesInfo ,int disConnectNumber) {
        this(vertex,edgesInfo,disConnectNumber,disConnectNumber);
    }
    
    public GraphByArray(List<String> vertex, String[][] edgesInfo ,int disConnectNumber, int selfNumber) {
        this.vertex = vertex;
        this.disConnectNumber = disConnectNumber;
        this.edge = new int[vertex.size()][vertex.size()];
        this.selfNumber = selfNumber;
        initEdges();
    
        for (String[] edgeInfo : edgesInfo) {
            addEdge(edgeInfo[0], edgeInfo[1], edgeInfo[2]);
        }
    }
    
    public GraphByArray(List<String> vertex, int[][] edges , int disConnectNumber) {
        this(vertex,edges,disConnectNumber,false);
    }
    
    
    
    public GraphByArray(List<String> vertex, int[][] edges , int disConnectNumber , boolean needCountEdge) {
        this(vertex,edges,disConnectNumber,disConnectNumber,needCountEdge);
    }
    
    public GraphByArray(List<String> vertex, int[][] edges , int disConnectNumber , int selfNumber , boolean needCountEdge) {
        this.vertex = vertex;
        this.edge = edges;
        this.disConnectNumber = disConnectNumber;
        this.selfNumber = selfNumber;
        initEdges();
        if(needCountEdge){
            // TODO 边的数量需要遍历计算
        }
    }
    
    private void initEdges() {
        // 初始化
        for(int i=0 ;i<edge.length; i++){
            for( int j=0 ; j<edge[i].length ; j++ ){
                if( i == j){
                    edge[i][j] = selfNumber;
                }else{
                    edge[i][j] = disConnectNumber;
                }
            }
        }
    }
    
    public String[][] edgesInfo(){
        String[][] result = new String[edgeCount][3];
        int idx=0;
        for(int i=0 ; i<edge.length ; i++){
            for( int j=i+1; j<edge[i].length; j++ ){
                if(edge[i][j] != disConnectNumber){
                    String[] s = new String[3];
                    s[0] = vertex.get(i);
                    s[1] = vertex.get(j);
                    s[2] = edge[i][j] + "";
                    result[idx++] = s;
                }
            }
        }
        return result;
    }
    
    public int[][] edgesInfo2(){
        int[][] result = new int[edgeCount][3];
        int idx=0;
        for(int i=0 ; i<edge.length ; i++){
            for( int j=i+1; j<edge[i].length; j++ ){
                if(edge[i][j] != disConnectNumber){
                    int[] s = new int[3];
                    s[0] = i;
                    s[1] = j;
                    s[2] = edge[i][j];
                    result[idx++] = s;
                }
            }
        }
        return result;
    }
    
    public int totalWeight(){
        int result = 0;
        for(int i=0 ; i<edge.length ; i++){
            for( int j=i+1; j<edge[i].length; j++ ){
                if(edge[i][j] != disConnectNumber){
                    result += edge[i][j];
                }
            }
        }
        return result;
    }
    
    public void insertEdge(int p1, int p2, int weight) {
        edge[p1][p2] = weight;
        edge[p2][p1] = weight;
        edgeCount++;
    }
    
    public void addEdge(String p1, String p2, String weight) {
        addEdge(p1, p2, Integer.parseInt(weight));
    }
    
    public void addEdge(String p1, String p2, int weight) {
        int index1 = vertex.indexOf(p1);
        int index2 = vertex.indexOf(p2);
        if (index1 < 0 || index2 < 0) {
            throw new IllegalArgumentException("不存在的边!");
        }
        insertEdge(index1, index2, weight);
    }
    
    
    // 深度优先遍历
    public void depthFirstTraverse() {
        boolean[] visited = new boolean[vertex.size()];
        depthFirstTraverse(0, visited);
        System.out.println();
    }
    
    
    // 广度优先遍历
    public void broadFirstTraverse() {
        boolean[] visited = new boolean[vertex.size()];
        broadFirstTraverse(0,visited);
        System.out.println();
    }
    
    public void broadFirstTraverse(int vertexIndex, boolean[] visited) {
        LoopArrayQueue<Integer> queue = new LoopArrayQueue<>(vertex.size());
        queue.add(vertexIndex);
        while(!queue.isEmpty()){
            Integer idx = queue.poll();
            if(!visited[idx]){
                System.out.print(vertex.get(idx)+" ");
                visited[idx] = true;
                
                for(int i = 0; i<edge[idx].length; i++){
                    if(edge[idx][i]>0){
                        queue.add(i);
                    }
                }
            }
        }
    }
    
    private void depthFirstTraverse(int vertexIndex, boolean[] visited) {
        if (!visited[vertexIndex]) {
            System.out.print(vertex.get(vertexIndex) + " ");
            visited[vertexIndex] = true;
            
            for (int i = 0;  i < edge[vertexIndex].length; i++) {
                if (edge[vertexIndex][i] > 0) {
                    depthFirstTraverse(i, visited);
                }
            }
        }
    }
    
    /*private void depthFirstTraverse(int vertexIndex, boolean[] visited) {
        System.out.print(vertex.get(vertexIndex) + " ");
        visited[vertexIndex] = true;
        
        for (int i = 0;  i < edge[vertexIndex].length; i++) {
            if (edge[vertexIndex][i] > 0 && !visited[i]) {
                depthFirstTraverse(i, visited);
            }
        }
    }*/
    
    /**
     * @param vertex
     * @param edgeDesc 边描述数组，int[][3] 0:p1 1:p2 2:weight
     * @return
     */
    public static GraphByArray parse(List<String> vertex, int[][] edgeDesc) {
        
        return parse(vertex,edgeDesc,0);
    }
    
    public static GraphByArray parse(List<String> vertex, int[][] edgeDesc , int disConnectNumber) {
        
        GraphByArray graph = new GraphByArray(vertex
                ,new int[vertex.size()][vertex.size()]
                ,disConnectNumber);
        
        for (int i = 0; i < edgeDesc.length; i++) {
            graph.insertEdge(edgeDesc[i][0], edgeDesc[i][1], edgeDesc[i][2]);
        }
        
        return graph;
    }
    
    public static void main(String[] args) {
        GraphByArray graph = GraphByArray.parse(
                Arrays.asList("A", "B", "C", "D", "E", "F")
                , new int[][]{
                        {0, 1, 1},
                        {0, 3, 1},
                        {1, 3, 1},
                        {2, 4, 1},
                        {3, 4, 1},
                        {4, 5, 1},
                }
        );
        
        MyArrayUtil.printArrayOf2(graph.edge);
        System.out.print("深度优先遍历：");
        graph.depthFirstTraverse();
        System.out.print("宽度优先遍历：");
        graph.broadFirstTraverse();
        
        GraphByArray g2 = new GraphByArray(
                Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8")
                , new String[][]{
                {"1", "2", "1"},
                {"1", "3", "1"},
                {"2", "4", "1"},
                {"2", "5", "1"},
                {"3", "6", "1"},
                {"3", "7", "1"},
                {"4", "8", "1"},
                {"5", "8", "1"},
                {"6", "7", "1"},
        }
        );
        MyArrayUtil.printArrayOf2(g2.edge);
        System.out.print("深度优先遍历：");
        g2.depthFirstTraverse();
        System.out.print("宽度优先遍历：");
        g2.broadFirstTraverse();
        
    }
}
