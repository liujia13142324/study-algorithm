package com.lj.datastructure.notline.tree;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class TreeNode<T> {
    
    TreeNode left;
    TreeNode right;
    T value;
    int weight;
    
    public boolean isEmpty(){
        return value == null;
    }
    
    public boolean isLeaf(){
        return left == null && right == null;
    }
    
    public static <T extends TreeNode> List<T> parse(Object[] values, Class<T> clazz) throws IllegalAccessException, InstantiationException {
       
        return parse(values,null, clazz);
    }
    
    public static <T extends TreeNode> List<T> parse(Object[] values,int[] weight, Class<T> clazz) throws IllegalAccessException, InstantiationException {
        List<T> nodes = new ArrayList<>(values.length);
        for (int i = 0; i < values.length; i++) {
            T node = clazz.newInstance();
            node.setValue(values[i]);
            if(weight != null){
                node.setWeight(weight[i]);
            }
            nodes.add(node);
        }
        return nodes;
    }
    
    /**
     * 由 values 构成完全二叉树
     *
     * @param values
     * @param clazz
     * @param <T>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T extends TreeNode> T parseTree(Object[] values, Class<T> clazz) throws IllegalAccessException, InstantiationException {
        List<T> nodes = parse(values, clazz);
        
        return parseTree(nodes);
    }
    
    
    public static <T extends TreeNode> T parseTree(List<T> nodes) {
        if (nodes.isEmpty()) {
            return null;
        }
        
        for (int i = 0, j = nodes.size() / 2; i < j; i++) {
            if(!nodes.get(2 * i + 1).isEmpty()){
                nodes.get(i).left = nodes.get(2 * i + 1);
            }
            if (2 * i + 2 < nodes.size() && !nodes.get(2 * i + 2).isEmpty()) {
                nodes.get(i).right = nodes.get(2 * i + 2);
            }
        }
        
        return nodes.get(0);
    }
    
    
    public TreeNode(T value) {
        this.value = value;
    }
    
    public TreeNode getLeft() {
        return left;
    }
    
    public TreeNode getRight() {
        return right;
    }
    
}


