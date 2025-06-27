package com.lj.datastructure.notline.tree.old;


import com.lj.study.common.utils.MyArrayUtil;

public class TestBitTree {

	/**
	 * 现在只能是数据为单位数，或者单数据的节点，以免长度不一破环形状。
	 * 可以把节点的数据域定义成一个char[]数组，把长度固定死
	 * @description 利用二维数组画二叉树，先生成一棵树
	 * @param args
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception{
//		int sizeOfGraph = 599;   // 二叉树图的大小 （图的大小取决于宽度）， 该大小能够支撑到深度为8的二叉树
		int sizeOfGraph =32;
		int num[][] = new int[10][sizeOfGraph];
		BitTree tree = new BitTree();
//		Integer datas[] = new Integer[]{1,6,3,4,5,2,7,8,2,1,6,3,4,5,2,7,8,2,3,1,1,6,3,4,5,2,7,8,2,3,1,2,3,2,3,2,3,2,3,1,6,3,4,5,2,7,8,2,3,1,2,3,2,3,3,1,2,3,2,3,1,6,3,4,5,2,7,8,2,3,1,2,3,2,3,1,6,3,4,5,2,7,8,2,3,1,2,3,2,3,1,2,3,1,6,3,4,5,2,7,8,2,3,1,2,3,2,3,1,2,3,1,6,3,4,5,2,7,8,2,3,1,2,3,2,3,1,2,3,1,6,3,4,5,2,7,8,2,3,1,2,3,2,3,1,2,3,6,3,4,5,2,7,8,2,3,1,2,3,2,3,1,2,3,1,6,3,4,5,2,7,8,2,3,1,2,3,2,3,1,2,3};
		Integer datas[] = new Integer[]{1,6,3,4,5,2,7,8,4,3,2,1,3,4,2,null,2,1,1,3};
		tree.createNewBitTree(datas);
		System.out.println(tree.getDepth());
		BitTreeNode nodes [] = tree.getNodes();
		//创建二叉树图表
		createTreeGraphByRootFirst(nodes[0], tree, num, sizeOfGraph/2-1, 0);
		//打印该图表
		MyArrayUtil.printArrayOf2AndRemove0(num);
		//遍历二叉树
		tree.printerByPreOrder();
		tree.printerByPreOrderNotRecursion();
		tree.printerByMidOrder();
		tree.printerByMidOrderNotRecursion();
		tree.printerByRearOrder();
		tree.printerByRearOrderNotRecursion();
	}

	/**
	 * 先根递归生成结点。。不需要计算父节点的索引，只需要计算生成节点放置的位置
	 * @param root
	 * @param tree
	 * @param graph  图表
	 * @param rootColIndex  获得的索引，控制列距
	 * @param rootRowIndex  可以控制行距 
	 */
	private static void createTreeGraphByRootFirst( BitTreeNode root, BitTree tree , int graph[][] , int rootColIndex , int rootRowIndex){
		if(root == null){
			return ;
		}
		// 要画出来，必须要提前要设计好格式，不然会冲突。。。  --->覆盖掉值
		if(graph[rootRowIndex][rootColIndex] == 0){
			graph[rootRowIndex][rootColIndex] = root.getData() ; 
		}
		createTreeGraphByRootFirst(tree.getLeftChildNode(root),tree,graph,rootColIndex-getDistance(rootRowIndex,tree.getDepth()),rootRowIndex+1);
		createTreeGraphByRootFirst(tree.getRightChildNode(root),tree,graph,rootColIndex+getDistance(rootRowIndex,tree.getDepth()),rootRowIndex+1);
	}
	
	/**
	 * 控制打印出来树的列距，以免空的不够开被覆盖
	 * @param row
	 * @param depth
	 * @return
	 */
	public static int getDistance(int row , int depth ){
		return (int) Math.pow(2,depth-row-2);
	}
	
}

/*
[BitTreeNode [data=1, leftChild=null, rightChild=1],
 BitTreeNode [data=3, leftChild=2, rightChild=3], 
 BitTreeNode [data=4, leftChild=null, rightChild=4], 
 BitTreeNode [data=5, leftChild=null, rightChild=null], 
 BitTreeNode [data=7, leftChild=null, rightChild=null], null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null]

*/
