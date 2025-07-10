package com.lj.problem.leetcode._2;

import com.lj.study.common.bean.A;
import org.junit.Test;

import java.util.*;

/**
 * 1353. 最多可以参加的会议数目
 * 给你一个数组 events，其中 events[i] = [startDayi, endDayi] ，表示会议 i 开始于 startDayi ，结束于 endDayi 。
 * 你可以在满足 startDayi <= d <= endDayi 中的任意一天 d 参加会议 i 。在任意一天 d 中只能参加一场会议。
 * 请你返回你可以参加的 最大 会议数目。
 *
 *
 * 示例 1：
 * 输入：events = [[1,2],[2,3],[3,4]]
 * 输出：3
 * 解释：你可以参加所有的三个会议。
 * 安排会议的一种方案如上图。
 * 第 1 天参加第一个会议。
 * 第 2 天参加第二个会议。
 * 第 3 天参加第三个会议。
 *
 * 示例 2：
 * 输入：events= [[1,2],[2,3],[3,4],[1,2]]
 * 输出：4
 *
 */
public class MaxEvents {

    @Test
    public void test() {
//        System.out.println(maxEvents5(new int[][]{{1,2}, {2,3}, {3,4}}));
        System.out.println(maxEvents5(new int[][]{{1,2}, {2,3}, {3,4}, {1,2}}));
//        System.out.println(maxEvents5(new int[][]{{1,4}, {4,4}, {2,2}, {3,4}, {1,1}}));;
//        System.out.println(maxEvents(new int[][]{{1,5}, {1,5}, {1,5}, {2,3}, {2,3}}));;
//        System.out.println(maxEvents4(new int[][]{{1,2}, {1,2}, {3,3}, {1,5}, {1,5}}));;
//        System.out.println(maxEvents(new int[][]{{52,79}, {7,34}}));;
    }

    public int maxEvents5(int[][] events) {

        int max = 0;
        for (int[] e : events) {
            max = Math.max(max, e[1]);
        }

        List<Integer>[] groups = new ArrayList[max+1];
        Arrays.setAll(groups, e -> new ArrayList<>());
        for (int[] e : events) {
            groups[e[0]].add(e[1]);
        }

        List<Integer> startDays = new ArrayList<>();
        for(int i = 1; i < groups.length; i++) {
            if (!groups[i].isEmpty()) {
                startDays.add(i);
            }
        }

        int count = 0;
        int i = 0;
        int startDaysLen = startDays.size();
        int day = startDays.get(i);
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        // i > startDays 说明全部的会议都过了一遍，要么已经都处理完了，要么还在小顶堆里面等着出队
        while (day <= max && (i < startDaysLen || !priorityQueue.isEmpty())) {

            // 维护当前全部起始日期的指针
            if (i < startDaysLen && day >= startDays.get(i)) {
                i++;
            }

            for (int endDay: groups[day]) {
                priorityQueue.offer(endDay);
            }

            while (!priorityQueue.isEmpty() && priorityQueue.peek() < day) {
                priorityQueue.poll();
            }

            if (!priorityQueue.isEmpty()) {
                priorityQueue.poll();
                count++;
            }

            // 跳下个有意义的开始日期，避免无意义循环
            if (priorityQueue.isEmpty() && i < startDaysLen) {
                day = startDays.get(i);
            }else {
                day ++;
            }
        }

        return count;
    }

    public int maxEvents4(int[][] events) {

        int max = 0;
        int min = Integer.MAX_VALUE;
        for (int[] e : events) {
            max = Math.max(max, e[1]);
            min = Math.min(min, e[0]);
        }

        // 比直接用hash要快
        List<Integer>[] groups = new ArrayList[max+1];
        Arrays.setAll(groups, e -> new ArrayList<>());
        for (int[] e : events) {
            groups[e[0]].add(e[1]);
        }

        int count = 0;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        for (int day = min; day <= max; day++) {

            for (int endDay: groups[day]) {
                priorityQueue.offer(endDay);
            }

            while (!priorityQueue.isEmpty() && priorityQueue.peek() < day) {
                priorityQueue.poll();
            }

            if (!priorityQueue.isEmpty()) {
                priorityQueue.poll();
                count++;
            }

        }

        return count;
    }


    public int maxEvents3(int[][] events) {
        Arrays.sort(events, Comparator.comparing(e -> e[0]));
        int day = events[0][0];
        int count = 0;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        int j = 0;

        while (j < events.length || !priorityQueue.isEmpty()) {
            if (priorityQueue.isEmpty()) {
                day = events[j][0];
            }
            while (j < events.length && day >= events[j][0]) {
                priorityQueue.offer(events[j][1]);
                j++;
            }

            while (!priorityQueue.isEmpty() && priorityQueue.peek() < day) {
                priorityQueue.poll();
            }

            if (!priorityQueue.isEmpty()) {
                priorityQueue.poll();
                count++;
                day++;
            }

        }

        return count;
    }


    public int maxEvents2(int[][] events) {
        Arrays.sort(events, Comparator.comparing(e -> e[0]));
        int min = events[0][0];
        int max = 0;
        for (int i = 0; i < events.length; i++) {
            max = Math.max(max, events[i][1]);
        }

        int count = 0;

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        int j = 0;
        for (int i = min; i <= max; i++) {
            while (j < events.length && i >= events[j][0]) {
                priorityQueue.offer(events[j][1]);
                j++;
            }

            while (!priorityQueue.isEmpty() && priorityQueue.peek() < i) {
                priorityQueue.poll();
            }

            if (!priorityQueue.isEmpty()) {
                priorityQueue.poll();
                count++;
            }

        }

        return count;
    }


    // 暴破，超时
    public int maxEvents(int[][] events) {
        Comparator<int[]> comparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] < o2[0]) {
                    return -1;
                } else if (o1[0] > o2[0]) {
                    return 1;
                } else if (o1[1] < o2[1]) {
                    return -1;
                } else if (o1[1] > o2[1]) {
                    return 1;
                }
                return 0;
            }
        };

        Arrays.sort(events, comparator);

        int count = 0;

        for (int i = 0; i < events.length; i++) {
            if (events[i][0] <= events[i][1]) {
                count++;
                for (int j = i + 1; j < events.length; j++) {
                    if (events[j][0] <= events[i][0]) {
                        events[j][0] ++;
                    }else {
                        break;
                    }
                }
                Arrays.sort(events, comparator);
            }
        }


        return count;
    }

    @Test
    public void testPriorityQueue() {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue();
        priorityQueue.offer(2);
        priorityQueue.offer(10);
        priorityQueue.offer(5);
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
    }

}
