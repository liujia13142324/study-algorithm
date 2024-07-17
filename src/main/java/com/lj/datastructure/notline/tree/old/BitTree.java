package com.lj.datastructure.notline.tree.old;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class BitTree {
	//初始化树的最多节点数为 100 ，  第101用于计算深度且一定为null
	private BitTreeNode [] nodes = new BitTreeNode[201];;
	private int size ; 
	private int depth;
	
	public BitTree() {
	}

	public BitTree(BitTreeNode[] nodes){
		this.nodes = nodes;
	}
	
	public int calculateDepth( BitTreeNode root  ){
		// 递归出口出口
		if(root == null){
			return 0;
		}
		int leftDepth = calculateDepth(getLeftChildNode(root));
		int rightDepth =  calculateDepth(getRightChildNode(root));
		
		return leftDepth > rightDepth ? leftDepth + 1 : rightDepth + 1 ;
		
		
		/*return calculateDepth(nodes[root.getLeftChild() == null ?100:root.getLeftChild() ])
				> calculateDepth(nodes[root.getRightChild()  == null ?100:root.getRightChild() ])
				? calculateDepth(nodes[root.getLeftChild() == null ?100:root.getLeftChild() ]) + 1
				: calculateDepth(nodes[root.getRightChild()  == null ?100:root.getRightChild() ]) + 1;
		*/
	}
	
	
	public BitTreeNode getRightChildNode(BitTreeNode root) {
		BitTreeNode rightNode = null ;
		if(root.getRightChild() != null){
			rightNode = nodes[root.getRightChild()];
		}
		return rightNode;
	}

	public BitTreeNode getLeftChildNode(BitTreeNode root) {
		BitTreeNode leftNode = null ;
		if(root.getLeftChild() != null){
			leftNode = nodes[ root.getLeftChild() ];
		}
		return leftNode ;
	}

	public void createNewBitTree(Integer num[]) throws Exception{
		// 循环变量
		int i ;
		
		if(num.length<0){
			throw new Exception("数组的长度不对~");
		}

		
		if(num[0] == null){
			throw new Exception("根结点不能为空！");
		}
		
		// 初始化 root;
		BitTreeNode root = new BitTreeNode();
		root.setData(num[0]);
		nodes[0]  = root ;
		size ++ ;
		for(i=1; i<num.length ; i++){
			if(num[i] != null){
				nodes[size] = new BitTreeNode();
				nodes[size].setData(num[i]);
				try {
					if( (i-1)%2 == 0 ){ // 为左孩子  
						// size ---> 该结点在节点列表中的位置 ；
						nodes[(i-1)/2].setLeftChild(size); // (i-1)/2为父节点的索引
					}else if( (i-1)%2 == 1){ // 为右孩子
						nodes[(i-1)/2].setRightChild(size);
					}
				} catch (NullPointerException e) {
					System.err.println("父节点为空！");
				}
				size++;
			}
		}
		
		depth = calculateDepth(nodes[0]);
	}
	
	/**
	 * 前序打印
	 */
	public void printerByPreOrder(){
		byPreOrder(nodes[0]);
		System.out.println();
	}
	
	/**
	 * 前序打印不递归
	 */
	public void printerByPreOrderNotRecursion(){
		LinkedList<BitTreeNode> stack = new LinkedList<BitTreeNode>();
		
		BitTreeNode node = nodes[0];
		while( true ){
			while(node != null){
				System.out.print(node.getData()+",");
				stack.push(node);
				node = getLeftChildNode(node);
			}
			// 出口
			if(stack.isEmpty()){
				break;
			}
			node = stack.pop();
			node = getRightChildNode(node);
		}
		System.out.println();
	}

	
	private void byPreOrder(BitTreeNode bitTreeNode) {
		if(bitTreeNode != null){
			System.out.print(bitTreeNode.getData()+",");
			byPreOrder(getLeftChildNode(bitTreeNode));
			byPreOrder(getRightChildNode(bitTreeNode));
		}
	}

	
	/**
	 * 中序打印不递归
	 */
	public void printerByMidOrderNotRecursion(){
		LinkedList<BitTreeNode> stack = new LinkedList<BitTreeNode>();
		BitTreeNode node = nodes[0];
		while(true){
			while(node != null){
				stack.push(node);
				node = getLeftChildNode(node);
			}
			if(stack.isEmpty()){
				break;
			}
			node = stack.pop();
			System.out.print(node.getData()+",");
			node = getRightChildNode(node);
		}
		System.out.println();
	}
	
	/**
	 * 中序打印
	 */
	public void printerByMidOrder(){
		byMidOrder(nodes[0]);
		System.out.println();
	}

	private void byMidOrder(BitTreeNode bitTreeNode) {
		if(bitTreeNode != null){
			byMidOrder(getLeftChildNode(bitTreeNode));
			System.out.print(bitTreeNode.getData()+",");
			byMidOrder(getRightChildNode(bitTreeNode));
		}
	}

	/**
	 * 后序打印不递归
	 */
	public void printerByRearOrderNotRecursion(){
		LinkedList<BitTreeNode> stack = new LinkedList<BitTreeNode>();
		Map<BitTreeNode,Integer> record = new HashMap<BitTreeNode,Integer>();
		for(int i=0 ; i<size ; i++){
			record.put(nodes[i], 0);
		}
		BitTreeNode node = nodes[0];
		while( true ){
			while(node.getLeftChild() != null && record.get(getLeftChildNode(node)) == 0){
				stack.push(node);
				node = getLeftChildNode(node);
			}
			if(node.getRightChild() != null && record.get(getRightChildNode(node)) == 0){
				stack.push(node);
				node = getRightChildNode(node);
			}else{
				System.out.print(node.getData()+",");
				record.put(node, 1);
				if(!stack.isEmpty()){
					node = stack.pop();
				}else{
					break;
				}
				
			}
		}
		System.out.println();
	}
	
	/**
	 * 后序打印
	 */
	public void printerByRearOrder(){
		byRearOrder(nodes[0]);
		System.out.println();
	}
	
	
	private void byRearOrder(BitTreeNode bitTreeNode) {
		if(bitTreeNode != null){
			byRearOrder(getLeftChildNode(bitTreeNode));
			byRearOrder(getRightChildNode(bitTreeNode));
			System.out.print(bitTreeNode.getData()+",");
		}
	}
	
	public static BitTree InverseGeneratedByTwoErgodic(){
		// TODO 根据两种遍历，还原出整个树
		return null;
	}
	
	public String toString(){
		return Arrays.toString(nodes);
	}

	public BitTreeNode[] getNodes() {
		return nodes;
	}

	public void setNodes(BitTreeNode[] nodes) {
		this.nodes = nodes;
	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}


}
