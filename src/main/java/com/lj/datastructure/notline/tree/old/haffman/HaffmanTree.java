package com.lj.datastructure.notline.tree.old.haffman;

public class HaffmanTree {
	//最大权值
	static final int MAXVALUE = 1000;
	int nodeNum;  //叶子结点个数
	public HaffmanTree(int n){
		this.nodeNum = n;
	}
	
	public void haffman(int [] weight , HaffNode[] nodes){
		int n = this.nodeNum;
		int m1,m2,x1,x2;
		//初始化所有的节点，对应有n个叶子结点的哈夫曼树，有2n-1个结点
		//二叉树性质3：二叉树中 度为2的结点数 = 叶子节点数-1 , 所以总数为 2n-1
		for(int i = 0 ;i<2*n-1 ; i++){
			HaffNode temp = new HaffNode();
			if(i<n){
				temp.weight = weight[i];
			}else{
				temp.weight = 0;
			}
			temp.parent = 0;
			temp.flag = 0;
			temp.leftChild = -1 ;
			temp.rightChild = -1 ;
			nodes[i] = temp;
		}
		//初始化n-1个非叶子结点，n-1表示要循环n-1次求的n-1个数
		for(int i=0 ; i<n-1 ; i++){
			m1 = m2 = MAXVALUE;
			x1 = x2 = 0 ;
			//找到最小的两个叶子节点
			for(int j=0 ; j<n+i ; j++){
				if(nodes[j].weight<m1 && nodes[j].flag==0){
					m2 = m1;
					x2 = x1;
					m1 = nodes[j].weight;
					x1 = j ;
				}else if(nodes[j].weight < m2 && nodes[j].flag==0){
					m2 = nodes[j].weight;
					x2 = j;
				}
			}
			//将权值最小的2个组合成一个2插树
			nodes[x1].flag = 1 ;
			nodes[x1].parent = n+i;
			nodes[x2].flag = 1 ;
			nodes[x2].parent = n+i;
			nodes[n+i].weight = m1+m2;
			nodes[n+i].leftChild = x1;
			nodes[n+i].rightChild = x2;
		}
		
	}
	
	public void haffmanCode(HaffNode[]nodes , HaffCode[] haffCode){
		int n = this.nodeNum;
		// haffmanCode code的长度不会超过（haffmanTree最深的叶子）为叶子总数减一
		int codeMaxLength = n-1;
		HaffCode code = new HaffCode(n);
		int child , parent;
		int i,j;
		//依据nodes构建haffmanTree的规则，前n个结点为叶子节点
		for(i=0 ; i<n ; i++){
			code.start = codeMaxLength-1;
			parent = nodes[i].parent;
			//当前结点的编号,用于向上搜索父节点用的
			child = i;
			while(parent!=0){
				if(nodes[parent].leftChild == child){
					code.bit[code.start] = 0;
				}else{
					code.bit[code.start] = 1;
				}
				code.start -- ;
				child = parent;
				parent = nodes[child].parent; //和上面那句顺序不能差
			}
			
			//需要一个临时的HaffCode，因为上面的code是公共的--->否则发生错误：haffCode里面都是一个code 
			HaffCode temp = new HaffCode(n);
			//1~n-1
			for(j=code.start;j<codeMaxLength;j++){
				temp.bit[j] = code.bit[j];
			}
			temp.weight = nodes[i].weight;
			temp.start = code.start;
			haffCode[i] = temp;
		}
	}
	
}
