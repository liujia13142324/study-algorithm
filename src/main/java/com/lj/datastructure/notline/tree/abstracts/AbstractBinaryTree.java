package com.lj.datastructure.notline.tree.abstracts;

import com.lj.datastructure.notline.tree.enums.TraverseType;
import org.apache.commons.lang3.StringUtils;

import java.util.function.Consumer;

public abstract class AbstractBinaryTree<T> implements TraverseAble<T> {
    
//    Integer layer;
    
    public void prettyShow(int distance){
        int layer = layer();
        int cols = (int) Math.pow(2, layer);
        
        String[][] graph = new String[layer][cols];
        createTreeGraphByPreOrder(getRoot(),graph,cols/2-1,0);
        /*if(distance == 1){
            MyArrayUtil.printArrayOf2AndRemoveEmpty(graph," ");
            return;
        }*/
        for( int i=0 ; i<graph.length ; i++ ){
            int count = 0;
            
            // 当distance为1时，定位节点位置；
            // 左偏移量
            int leftOffset= (int)(Math.pow(2,layer-i-1)-1);
            // 两个节点之间的距离
            int distanceX = (int)(Math.pow(2,layer-i));
            
            for( int j=leftOffset; j<graph[i].length ; j+=distanceX){
                
                if(count<=0){
                    for(int k=0 ; k<leftOffset*distance ; k++){
                        System.out.print(" ");
                    }
                }else{
                    for(int k=0 ; k<distanceX*distance-1 ; k++){
                        System.out.print(" ");
                    }
                }
                if(StringUtils.isNotEmpty(graph[i][j])){
                    System.out.print(graph[i][j]);
                }else{
                    System.out.print(" ");
                }
                count++;
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public abstract T getRoot();
    
    public abstract T getRightNode(T node);
    
    public abstract T getLeftNode(T node);
    
    public abstract Object getNodeVal(T t);
    
    @Override
    public Consumer<T> getDefaultHandle() {
        return  t -> System.out.print(getNodeVal(t)+" ");
    }
    
    public int layer(){
        /*if(layer == null){
            layer = layer(getRoot());
        }
        return layer;*/
        return layer(getRoot());
    }
    
    
    public int layer(T node){
        if(node == null){
            return 0;
        }
        int left = layer(getLeftNode(node)) ;
        int right = layer(getRightNode(node)) ;
        return Math.max(left,right) + 1;
    }
    
    @Override
    public T getFirst() {
        return getRoot();
    }
    
    protected boolean isEmpty(){
        return getRoot() == null;
    }
    
    @Override
    public  void traverse(TraverseType traverseType, T node, Consumer<T> handle) {
        switch (traverseType){
            case IN_ORDER:
                inOrder(node,handle);
                break;
            case PRE_ORDER:
                preOrder(node,handle);
                break;
            case POST_ODER:
                postOrder(node,handle);
                break;
        }
    }
    
    // 前根
    protected abstract void preOrder(T node, Consumer<T> handle);
    
    // 中根
    protected abstract void inOrder(T node, Consumer<T> handle);
    
    // 后根
    protected abstract void postOrder(T node, Consumer<T> handle);
    
    private void createTreeGraphByPreOrder(T node, String graph[][] , int nodeColIndex , int nodeRowIndex){
        if(node == null){
            return ;
        }
        
        graph[nodeRowIndex][nodeColIndex] = getNodeVal(node)+"";
        
        createTreeGraphByPreOrder(getLeftNode(node),graph,nodeColIndex-getDistance(nodeRowIndex,layer()),nodeRowIndex+1);
        createTreeGraphByPreOrder(getRightNode(node),graph,nodeColIndex+getDistance(nodeRowIndex,layer()),nodeRowIndex+1);
    }
    
    /**
     * 控制打印出来树的列距，以免空的不够开被覆盖
     * @param row
     * @param depth
     * @return
     */
    private static int getDistance(int row , int depth ){
        return (int) Math.pow(2,depth-row-2);
    }
}
