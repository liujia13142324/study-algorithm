package com.lj.datastructure.notline;

/**
 * 并查集
 */
public class UnionFind {

    public int[] root;
    public int cnt;

    public UnionFind(int size) {
        root = new int[size];
        cnt = size;
        for (int i = 0; i < size; i++) {
            root[i] = i;
        }
    }

    public void merge(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            root[x] = y;
            cnt --;
        }
    }

    private int find(int i) {
        if (root[i] == i) {
            return i;
        }
        // 路径压缩
        return root[i] = find(root[i]);
    }

}
