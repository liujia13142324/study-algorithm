package com.lj.datastructure.notline.tree.old.haffman;

public class HaffNode {
	int weight;
	int parent;
	int flag;
	int leftChild;
	int rightChild;
	@Override
	public String toString() {
		return "weight:"+this.weight +"  leftChild:"+this.leftChild+"  rightChild:"+this.rightChild;
	}
	
}
