package com.lj.datastructure.notline.tree.old.haffman;

import java.util.Arrays;

public class HaffCode {
	int[] bit;
	int start;
	int weight;
	HaffCode(int n){
		bit = new int[n-1];
		start = n - 1;
	}
	
	@Override
	public String toString() {
		return Arrays.toString(this.bit)+"  start:"+this.start+"  weight:"+this.weight ;
	}
}
