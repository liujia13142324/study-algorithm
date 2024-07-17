package com.lj.datastructure.notline.tree.old;

/**
 * @author lenovo
 *
 */
public class BitTreeNode {

	private Integer data;
	private Integer leftChild;
	private Integer rightChild;
	
	@Override
	public String toString() {
		return "BitTreeNode [data=" + data + ", leftChild=" + leftChild + ", rightChild=" + rightChild + "]";
	}

	public BitTreeNode() {
	}
	
	public BitTreeNode(Integer data, Integer leftChild, Integer rightChild) {
		super();
		this.data = data;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}
	public Integer getData() {
		return data;
	}
	public void setData(Integer data) {
		this.data = data;
	}
	public Integer getLeftChild() {
		return leftChild;
	}
	public void setLeftChild(Integer leftChild) {
		this.leftChild = leftChild;
	}
	public Integer getRightChild() {
		return rightChild;
	}
	public void setRightChild(Integer rightChild) {
		this.rightChild = rightChild;
	}
	
	
	
	
}
