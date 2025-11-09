package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 22. 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：["()"]
 *
 *
 * 提示：
 *
 * 1 <= n <= 8
 */
public class GenerateParenthesis {


    @Test
    public void test () {
        System.out.println(generateParenthesis3(3));
        System.out.println(generateParenthesis4(3));
    }



    /**
     * 选 （ 还是 ）
     * @param n
     * @return
     */
    public List<String> generateParenthesis2(int n) {
        List<String> ans = new ArrayList<>();
        char[] path = new char[2*n];
        dfs(0, 0, 0, ans, path);
        return ans;
    }

    public void dfs(int i, int lc, int rc, List<String> ans, char[] path) {
        if (i == path.length) {
            ans.add(new String(path));
            return;
        }

        // choose (
        if (lc < path.length/2) {
            path[i] = '(';
            dfs(i + 1, lc + 1, rc, ans, path);
        }
        // choose )
        if (rc < lc) {
            path[i] = ')';
            dfs(i + 1, lc, rc + 1, ans, path);
        }
    }


    /**
     * 枚举 （ 结束的位置
     * @param n
     * @return
     */
    public List<String> generateParenthesis3(int n) {
        int[] pos = new int[n];
        List<String> ans  = new ArrayList<>();
        dfs(0, 0, pos, ans);
        return ans;
    }

    private void dfs(int i, int lc, int[] pos, List<String> ans) {
        if (lc == pos.length) {
            char[] chars = new char[2 * pos.length];
            Arrays.fill(chars, ')');
            for (int p: pos) chars[p] = '(';
            ans.add(new String(chars));
            return;
        }

        for (int j = i; j <= 2 * lc; j++) {
            pos[lc] = j;
            dfs(j + 1, lc + 1, pos, ans);
        }
    }

    /**
     * 枚举 （ 结束的位置，方法二
     * @param n
     * @return
     */
    public List<String> generateParenthesis4(int n) {
        int[] pos = new int[n];
        List<String> ans  = new ArrayList<>();
        dfs(0, 0, 0, pos, ans);
        return ans;
    }

    /**
     *
     * @param i         下一个左括号开始的位置
     * @param lc        左括号的数量
     * @param balance   左括号-右括号
     * @param pos
     * @param ans
     */
    private void dfs(int i, int lc, int balance, int[] pos, List<String> ans) {
        if (lc == pos.length) {
            char[] chars = new char[2 * pos.length];
            Arrays.fill(chars, ')');
            for (int p: pos) chars[p] = '(';
            ans.add(new String(chars));
            return;
        }

        // 枚举右括号的个数
        for (int r = 0; r <= balance; r++) {
            pos[lc] = i + r;
            dfs(i + r + 1, lc + 1, balance - r + 1, pos, ans);
        }
    }


    // 左括号的数量只可能小于等于右括号
    public List<String> generateParenthesis(int n) {
        List<String> combinations = new ArrayList<>();
        generateAll(new StringBuilder(), n, n, combinations);
        return combinations;
    }
    
    
    public void generateAll(StringBuilder str, int leftCount, int rightCount, List<String> combinations) {
        if (leftCount == 0 && rightCount == 0) {
            combinations.add(str.toString());
            return;
        }
        
        // 相等的时候，只能是左括号
        if (leftCount == rightCount) {
            generateAll(str.append("("), leftCount - 1, rightCount, combinations);
            str.deleteCharAt(str.length() - 1);
        }else if (leftCount < rightCount){
            // 既可以是左括号，也可以是右括号
            if (leftCount > 0) {
                generateAll(str.append("("), leftCount - 1, rightCount, combinations);
                str.deleteCharAt(str.length() - 1);
            }
            generateAll(str.append(")"), leftCount, rightCount - 1, combinations);
            str.deleteCharAt(str.length() - 1);
        }
        
    }
    
    /*public List<String> generateParenthesis(int n) {
        List<String> combinations = new ArrayList<String>();
        generateAll(new char[2 * n], 0, combinations);
        return combinations;
    }
    
    public void generateAll(char[] current, int pos, List<String> result) {
        if (pos == current.length) {
            if (valid(current)) {
                result.add(new String(current));
            }
        } else {
            current[pos] = '(';
            generateAll(current, pos + 1, result);
            current[pos] = ')';
            generateAll(current, pos + 1, result);
        }
    }
    
    public boolean valid(char[] current) {
        int balance = 0;
        for (char c: current) {
            if (c == '(') {
                ++balance;
            } else {
                --balance;
            }
            if (balance < 0) {
                return false;
            }
        }
        return balance == 0;
    }*/
    
    
    
    /*public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList();
        int sum = 0;
        boolean[] visited = new boolean[2*n];
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i<n; i++) {
            sb.append("()");
        }
        
        dfs(sb.toString().split(""),  "", visited, result, sum);
        
        return new ArrayList<>(new HashSet<>(result));
    }
    
    public void dfs(String[] strs, String prefix, boolean[] visited, List<String> result, int sum) {
        if (prefix.length() >= strs.length) {
            result.add(prefix);
            return;
        }
        
        for (int i = 0; i < strs.length; i++) {
            if (!visited[i]) {
                if (sum >= 0) {
                    visited[i] = true;
                    dfs(strs, prefix + strs[i], visited, result
                            , sum + (strs[i].equals("(") ? 1 : -1));
                    visited[i] = false;
                }
            }
        }
        
    }*/
    
    
    public static void main(String[] args) {
        List<String> strings = new GenerateParenthesis().generateParenthesis(2);
        System.out.println(strings.size() + " --> " + strings);
    
        List<List<Integer>> permute = new Permute().permute(new int[]{1, 2, 3, 4});
    
        System.out.println(permute.size() + "--->" + permute);
    
    }

}
