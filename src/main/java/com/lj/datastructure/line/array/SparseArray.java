package com.lj.datastructure.line.array;

import com.lj.study.common.utils.MyArrayUtil;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * 稀疏数组
 * 第一行是元信息，记录原数组的信息，后面的每一行代表一个非零值。
 *
 * origin_rows  origin_cols  origin_valid_count
 * first_i      first_j      first_val
 * ...
 *
 */
@Data
public class SparseArray {

    int[][] array;

    public static SparseArray parse(int[][] origin) {

        SparseArray sparseArray = new SparseArray();
        List<int[]> validPos = new LinkedList<>();
        int iLen = origin.length;
        int jLen = origin[0].length;
        int i = 0;

        for (; i < iLen; i++) {
            for (int j = 0; j < jLen; j++) {
                if (origin[i][j] != 0) {
                    validPos.add(new int[]{i, j, origin[i][j]});
                }
            }
        }

        validPos.add(0, new int[]{iLen, jLen, validPos.size()});
        sparseArray.array = new int[validPos.size()][3];

        for (i = 0, iLen = validPos.size(); i < iLen; i++) {
            sparseArray.array[i] = validPos.get(i);
        }

        return sparseArray;

    }

    public static int[][] sparseArray2Normal(int [][] sparseArray) {
        int i = 0;
        int[] tmp = sparseArray[i++];
        int[][] result = new int[tmp[0]][tmp[1]];
        for(;i<sparseArray.length;i++){
            tmp = sparseArray[i];
            result[tmp[0]][tmp[1]] = tmp[2];
        }

        return result;
    }

    public int[][] sparseArray2Normal() {
        return sparseArray2Normal(array);
    }

    public static void main(String[] args) {
        SparseArray sparseArray = SparseArray.parse(MyArrayUtil.convertStrTo2DIntArr(
                     "0 0 0 0 0 1 0\n" +
                        "1 0 0 1 0 0 0\n" +
                        "0 0 1 0 0 0 0\n" +
                        "0 1 0 0 1 0 0\n" +
                        "0 2 1 0 0 0 0\n" +
                        "0 0 0 2 0 0 1", " "));

        MyArrayUtil.printArrayOf2(sparseArray.array, " ");
        System.out.println();
        MyArrayUtil.printArrayOf2(sparseArray.sparseArray2Normal(), " ");
    }

}
