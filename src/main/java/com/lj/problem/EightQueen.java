package com.lj.problem;

import com.lj.study.common.aop.annotation.PrintLog;
import com.lj.study.common.utils.MyArrayUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 八皇后问题，回溯
 */
public class EightQueen {
    List<Board> results = new ArrayList<>();
    Board board = new Board(8);
    
    @Test
    @PrintLog
    public void testQueen() throws CloneNotSupportedException {
        setPoint(0, board, results);
        System.out.println(results.size());
//        results.forEach(b->b.show());
        
    }
    
    /**
     * 一维数组实现
     */
    @Test
    public void testQueue() {
        QueueSolutionByOneArray queueSolutionByOneArray = new QueueSolutionByOneArray(8);
        queueSolutionByOneArray.solve();
        System.out.println(queueSolutionByOneArray.allResult.size());
    }
    
    class QueueSolutionByOneArray {
        /**
         * 多少行多少列
         */
        int k;
        int[] result;
        List<int[]> allResult;
        
        public QueueSolutionByOneArray(int k) {
            this.k = k;
            result = new int[k];
            allResult = new ArrayList<>();
        }
        
        public void solve() {
            for (int i = 0; i < k; i++) {
                result[0] = i;
                solve(1, result);
            }
        }
        
        /**
         * 处理第n层
         *
         * @param n
         * @param result
         */
        private void solve(int n, int[] result) {
            if (n >= k) {
                allResult.add(result.clone());
                return;
            }
            int i;
            outer:
            for (int j = 0; j < k; j++) {
                i=0;
                for (; i < n; i++) {
                    // 进入下一层的条件
                    if (
                        // 不在同一竖行
                            !(j != result[i]
                                    //不在左斜线
                                    && ((result[i] - n + i) < 0 || j != (result[i] - n + i))
                                    // 不在右斜线
                                    && ((result[i] + n - i) >= k || j != (result[i] + n - i)))
                    ) {
                        continue outer;
                    }
                }
                result[n] = j;
                solve(n + 1, result);
            }
            
        }
        
    }
    
    /**
     * 实现
     *
     * @param i
     * @param board
     * @param results
     * @throws CloneNotSupportedException
     */
    private void setPoint(int i, Board board, List<Board> results) throws CloneNotSupportedException {
        if (i >= board.n) {
            results.add(board);
            return;
        }
        
        for (int k = 0; k < board.n; k++) {
            Board tmp = (Board) board.clone();
            //
//           count++;
            if (tmp.setPoint(i, k)) {
                setPoint(i + 1, tmp, results);
            }
        }
        
    }
    
    class Board implements Cloneable {
        
        int[][] board;
        int n;
        
        /**
         * 多维数组clone，必须要一个一个维度clone
         *
         * @return
         * @throws CloneNotSupportedException
         */
        @Override
        protected Object clone() throws CloneNotSupportedException {
            Board clone = new Board(n);
            clone.board = board.clone();
            for (int k = 0; k < board.length; k++) {
                clone.board[k] = board[k].clone();
            }
            return clone;
        }
        
        public Board(int n) {
            this.n = n;
            board = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    board[i][j] = 0;
                }
            }
        }
        
        public boolean setPoint(int i, int j) {
            
            if (board[i][j] != 0) {
                return false;
            }
            
            int k = 0;
            // 设置竖行
            for (; k < n; k++) {
                if (k == i) continue;
                if (board[k][j] == 2) {
                    return false;
                }
                board[k][j] = 1;
            }
            // 设置横行
            k = 0;
            for (; k < n; k++) {
                if (k == j) continue;
                if (board[i][k] == 2) {
                    return false;
                }
                board[i][k] = 1;
            }
            
            // 设置左斜行
            k = 1;
            while (i + k < n && j - k >= 0) {
                if (board[i + k][j - k] == 2) {
                    return false;
                }
                board[i + k][j - k] = 1;
                k++;
            }
            
            // 设置右斜行
            k = 1;
            while (i + k < n && j + k < n) {
                if (board[i + k][j + k] == 2) {
                    return false;
                }
                board[i + k][j + k] = 1;
                k++;
            }
            
            board[i][j] = 2;
            return true;
        }
        
        public void show() {
            MyArrayUtil.printArrayOf2(board);
            System.out.println();
        }
        
        public String toString() {
            return MyArrayUtil.toString(board);
        }
    }
    
    @Test
    public void testBoard() {
        Board board = new Board(8);
        board.setPoint(0, 0);
        board.show();
        System.out.println(board.setPoint(1, 1));
        System.out.println(board.setPoint(1, 2));
        board.show();
        board.setPoint(2, 4);
        board.show();
    }
    
    
    
    
    
    @Test
    public void testEightQueue(){
        int arr[][] = new int[8][8];
        tmpEightQueue(arr, 0);
    }
    
    
    public void tmpEightQueue(int[][] arr, int x){
        
        if (x >= arr.length) {
            printAnswer(arr);
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            place(arr, x, i);
            if (check(arr, x)) {
                tmpEightQueue(arr, x+1);
            }
            undoPlace(arr, x, i);
        }
    }
    
    private boolean check(int[][] arr, int x) {
        List<int[]> points = new ArrayList<>();
        
        for (int i = 0; i <= x; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i][j] != 0) {
                    for (int k = 0; k < points.size(); k++) {
                        if (j == points.get(k)[1]
                            || calcXielv(i,j,points.get(k)[0],points.get(k)[1]) == 1.0f
                        ) {
                            return false;
                        }
                    }
                    points.add(new int[]{i,j});
                    break;
                }
            }
        }

        return true;
    }
    
    private float calcXielv(int x1, int y1, int x2, int y2) {
        return  Float.valueOf(Math.abs(x1 - x2) + "") / Math.abs(y1 - y2);
    }
    
    private void undoPlace(int[][] arr, int x, int i) {
        arr[x][i] = 0;
    }
    
    private void place(int[][] arr, int x, int i) {
        arr[x][i] = 1;
    }
    
    private void printAnswer(int[][] arr) {
        List<String> points = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != 0) {
                    points.add("(" + i + "," + j + ") ");
                    break;
                }
            }
        }
        System.out.println(points);
    }
    
}
