package com.lj.datastructure.notline.tree.old;

import com.lj.datastructure.notline.tree.old.haffman.HaffCode;
import com.lj.datastructure.notline.tree.old.haffman.HaffNode;
import com.lj.datastructure.notline.tree.old.haffman.HaffmanTree;

public class TestHaffManTree {
	public static void main(String args[]){
		int [] weights = new int[]{5,4,8,6,3,2,1};
		HaffmanTree haf = new HaffmanTree(weights.length);
		HaffNode[]nodes = new HaffNode[weights.length*2-1];
		haf.haffman(weights, nodes);
		HaffCode haffCodes [] = new HaffCode[weights.length];
		haf.haffmanCode(nodes, haffCodes);
		
		for (HaffCode haffCode : haffCodes) {
			System.out.println(haffCode);
		}
		for(int i=0 ; i<nodes.length ; i++){
			System.out.println(i+": "+nodes[i]);
		}
		
	}
}
