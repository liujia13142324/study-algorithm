package com.lj.datastructure.notline.tree;

import com.lj.datastructure.notline.tree.abstracts.NormalBinaryTree;

import java.util.*;

public class HoffManTree<T> extends NormalBinaryTree<TreeNode<T>> {
    TreeNode root;
    int wpl;
    final static String EMPTY_SIGNAL = "*";
    Map<T,String> codes ;
    
    
    public Map<T, String> getCodes() {
        return new HashMap<>(codes);
    }
    
    public void showCodes(){
        codes.forEach((k,v)->{
            System.out.print("<"+k+","+v+"> ");
        });
        System.out.println();
    }
    
    public int getWpl() {
        return wpl;
    }
    
    public HoffManTree(List<TreeNode> nodes){
        if(nodes.size() <=0 ){
            return;
        }
    
        if(nodes.size() <=2 ){
            int sum = nodes.stream().mapToInt(t -> t.getWeight()).sum();
            root = getNotLeafNode(sum);
            root.setLeft(nodes.get(0));
            if(nodes.size()>1){
                root.setRight(nodes.get(1));
            }
        }
        
        // 使用链表操作
        LinkedList<TreeNode> l = new LinkedList<>(nodes);
        l.sort(Comparator.comparingInt(t->t.getWeight()));
        while(l.size()>1){
            TreeNode first = l.remove(0);
            TreeNode second = l.remove(0);
            TreeNode r = getNotLeafNode(first.getWeight()+second.getWeight());
            r.left = first;
            r.right = second;
            int k=0;
            while(k<l.size() && r.weight > l.get(k).weight){
                k++;
            }
            l.add(k,r);
        }
        this.root = l.get(0);
        codes = new HashMap<>();
        this.wpl = countWplAndCodes(root,0, "");
    }
    
    private int countWplAndCodes(TreeNode<T> node, int d0 , String prefix) {
        if(node.isLeaf()){
            codes.put(node.getValue(),prefix);
            return node.weight * d0;
        }
        int left = countWplAndCodes(node.left, d0 + 1 , prefix+"0");
        int right = countWplAndCodes(node.right, d0 + 1,prefix+"1");
        return left+right;
    }
    
    private TreeNode getNotLeafNode(int weight){
        TreeNode result = new TreeNode();
        result.weight = weight;
        result.value = EMPTY_SIGNAL;
        return result;
    }
    
    // 压缩源文件
    /*public static byte[] compress(String source){
        // 根据字节去
    }*/
    
    @Override
    public TreeNode getRoot() {
        return root;
    }
    
    @Override
    public Object getNodeVal(TreeNode<T> tTreeNode) {
        return tTreeNode.getValue();
    }
    
    @Override
    public TreeNode getRightNode(TreeNode node) {
        return node.getRight();
    }
    
    @Override
    public TreeNode getLeftNode(TreeNode node) {
        return node.getLeft();
    }
    
    // 多一个字节存实际最后字节的有效位数
    public static HoffManTree<Byte> byteHoffManTree(byte[] originBts) throws InstantiationException, IllegalAccessException {
        // 统计生成节点
        Map<Byte,Integer> countMap = new HashMap<>(256);
        for(byte b : originBts){
            if(countMap.get(b) == null){
                countMap.put(b,1);
            }else{
                countMap.put(b,countMap.get(b)+1);
            }
        }
        Byte[] vals = new Byte[countMap.size()];
        int[] weights = new int[countMap.size()];
        int i=0;
        for(Map.Entry<Byte, Integer> entry:countMap.entrySet() ){
            vals[i] = entry.getKey();
            weights[i++] = entry.getValue();
        }
        List<TreeNode> parse = TreeNode.parse(vals, weights, TreeNode.class);
    
        // 构建哈夫曼树，获取哈夫曼编码
        return new HoffManTree(parse);
    }
    
    public static byte[] compress(byte[] originBts,Map<Byte,String> hoffManCodes){
    
        // 开始压缩
        StringBuilder binaryBuilder = new StringBuilder();
        String binaryResult ;
        int i=0;
        for(;i<originBts.length;i++){
            binaryBuilder.append( hoffManCodes.get(originBts[i]) );
//            binaryResult += hoffManCodes.get(originBts[i]);
        }
        binaryResult = binaryBuilder.toString();
        //拆分成字节 ，最后一个字节为标志，表示实际最后一个字节有多少位
        byte[] result = new byte[(int) Math.ceil(binaryResult.length()/8f)+1];
        int j = binaryResult.length();
        i=0;
        while(true){
            int start = i*8;
            int end = (i+1)*8;
            if(end < j){
                result[i++] = (byte) Integer.parseInt(binaryResult.substring(start,end),2);
            }else{
                String suffix = binaryResult.substring(start);
                result[i++] = (byte) Integer.parseInt(suffix,2);
                result[i] = (byte) suffix.length();
                break;
            }
        }
        return result;
    }
    
    public static byte[] decompress(byte[] bts,Map<Byte,String> codes){
        Map<String,Byte> reverseCodeMap = new HashMap(codes.size());
        codes.forEach((k,v)-> reverseCodeMap.put(v, k));
        // 先简单实现
        StringBuilder s = new StringBuilder();
        String binaryStr ;
        int i=0;
        for(; i<bts.length ; i++){
            
            byte b = bts[i];
            String t;
            if(i == bts.length-2){
                // 倒数第二个，实则是最后一个实际元素
                t = Integer.toBinaryString( (1 << bts[i+1])|bts[i] );
                t = t.substring(t.length()-bts[i+1]);
                // 跳出循环
                i++;
            }else{
                int temp = 256 | b;
                t = Integer.toBinaryString(temp);
                t = t.substring(t.length()-8);
            }
    
            s.append(t);
        }
        binaryStr = s.toString();
        List<Byte> resultList = new ArrayList<>();
        StringBuilder tmp = new StringBuilder();
        for(char c : binaryStr.toCharArray()){
            tmp.append(c);
            if(reverseCodeMap.get(tmp.toString()) != null){
                resultList.add(reverseCodeMap.get(tmp.toString()));
                tmp = new StringBuilder();
            }
        }
        
        byte[] result = new byte[resultList.size()];
        for(i=0 ; i<resultList.size(); i++){
            result[i] = resultList.get(i);
        }
        return result;
    }
    
    
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        List<TreeNode> nodes = TreeNode.parse(
                new String[]{"a", "b","c", "d", "e", "f", "g"}
                , new int[]{13, 7, 8, 3, 29, 6, 1}
                , TreeNode.class
        );
        HoffManTree hoffManTree = new HoffManTree(new ArrayList<>(nodes));
        hoffManTree.prettyShow(2);
        System.out.println("wpl:"+hoffManTree.getWpl());
        System.out.print("code:");
        hoffManTree.showCodes();
    
        System.out.println();
        TreeNode node = huffmanTest(new ArrayList<>(nodes));
        LinkedBinaryTree testTree = new LinkedBinaryTree<>(node);
        testTree.prettyShow(2);
    }

    
    public static TreeNode huffmanTest(List<TreeNode> treeNodes) {
        
        if (treeNodes.size() == 0) {
            return null;
        }
        
        LinkedList<TreeNode> list = new LinkedList<>(treeNodes);
        
        list.sort(Comparator.comparingInt(e -> e.getWeight()));
       
        while (list.size() > 1) {
            TreeNode n1 = list.removeFirst();
            TreeNode n2 = list.removeFirst();
            TreeNode composed = compose(n1, n2);
            Iterator<TreeNode> iterator = list.iterator();
            int idx = 0;
            while (iterator.hasNext()) {
                TreeNode next = iterator.next();
                if (composed.weight > next.weight) {
                    idx ++;
                }else {
                    break;
                }
            }
            list.add(idx, composed);
        }
        
        return list.getFirst();
    }
    
    private static TreeNode compose(TreeNode n1, TreeNode n2) {
        TreeNode node = new TreeNode();
        node.setWeight(n1.getWeight() + n2.getWeight());
        node.left = n1;
        node.right = n2;
        node.setValue("*");
        return node;
    }
    
}
