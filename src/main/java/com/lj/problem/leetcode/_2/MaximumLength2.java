package com.lj.problem.leetcode._2;

import com.lj.study.common.bean.A;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MaximumLength2 {

    @Test
    public void test() {
        System.out.println(maximumLength(new int[]{1,2,3,4,5}, 2));
    }


    public int maximumLength(int[] nums, int k) {

        List<List<Integer>>[] list = new ArrayList[k];
        int i;
        List<List<Integer>> tmpList;
        for (int num: nums) {
            outer:
            for (i = 0; i < k; i++) {
                tmpList = list[i];
                if (tmpList == null) {
                    list[i] = new ArrayList<>();
                    list[i].add(new ArrayList<>());
                    list[i].get(0).add(num);
                    continue;
                }

                for (List<Integer> tmpList2: tmpList) {
                    if ((tmpList2.get(tmpList2.size() - 1) + num) % k == i) {
                        tmpList2.add(num);
                        continue outer;
                    }
                }

                List<Integer> tmp2 = new ArrayList<>();
                tmp2.add(num);
                tmpList.add(tmp2);
            }

        }

        int result = 0;

        for (List<List<Integer>> l1: list) {
            for (List<Integer> l2: l1) {
                result = Math.max(result, l2.size());
            }
        }

        return result;
    }

}
