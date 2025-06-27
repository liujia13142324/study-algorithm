package com.lj.problem;

import com.lj.study.common.utils.MyArrayUtil;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.ArrayList;

public class MazeMapPath {
    
    MazeMap map ;
    
    {
        int [][] m = MyArrayUtil.convertStrTo2DIntArr(
                "0,0,0,0,0,0\n"
                        + "0,0,0,2,0,0\n"
                        + "2,2,0,2,0,0\n"
                        + "0,0,2,0,0,2\n"
                        + "0,2,0,0,0,0\n"
                        + "0,0,2,0,0,0\n"
                ,","
        );
        map = new MazeMap(m);
    }
    
    @Test
    public void test(){
        
        Point source = new Point(3,0);
        Point dest = new Point(5,1);
        ArrayList<Point> paths = new ArrayList<>();
        boolean r = move(source,dest,paths);
        if(r){
            for (int i = paths.size()-1; i >= 0 ; i--) {
                System.out.println(paths.get(i));
            }
        }else{
            System.out.println("该路径无法到达");
        }
    }
    
    private boolean move(Point source, Point dest, ArrayList<Point> paths) {
        if(source.equals(dest)){
            paths.add(source);
            return true;
        }
        int i = source.i;
        int j = source.j;
        // 设置为已走过
        map.setHistory(i,j);
        // 是否能走通
        boolean r = false;
        // 上 右 下 左
        if(!r && map.canPass(i-1,j)){
            map.setHistory(i-1,j);
            r = move(new Point(i-1,j),dest,paths);
        }
        
        if(!r && map.canPass(i,j+1)){
            map.setHistory(i,j+1);
            r = move(new Point(i,j+1),dest,paths);
        }
        
        if(!r && map.canPass(i+1,j)){
            map.setHistory(i+1,j);
            r = move(new Point(i+1,j),dest,paths);
        }
        
        if(!r && map.canPass(i,j-1)){
            map.setHistory(i,j-1);
            r = move(new Point(i,j-1),dest,paths);
        }
        
        if(r){
            // 添加到paths
            paths.add(source);
        }
        
        return r;
    }
    
    
    
    @AllArgsConstructor
    class Point{
        int i;
        int j;
        public boolean equals(Point p){
            return this.i == p.i && this.j == p.j;
        }
        public String toString(){
            return "("+i+","+j+")";
        }
    }
    
    static class MazeMap{
        int[][] map;
        static int BLOCK = 2;
        static int HISTORY = 1;
        static int PASSED = 0;
        int maxI;
        int maxJ;
    
        public MazeMap(int[][] map) {
            this.map = map;
            maxI = map.length;
            maxJ = map[0].length;
        }
    
        public boolean canPass(int i , int j){
            if(i<0 || i>=maxJ || j<0 || j>=maxJ){
                return false;
            }
            if(map[i][j] == BLOCK || map[i][j] == HISTORY){
                return false;
            }
            return true;
        }
        
        public void setHistory(int i , int j){
            map[i][j] = HISTORY;
        }
    }
}
