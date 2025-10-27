package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.*;

/**
 * 131. 分割回文串
 * 给你一个字符串 s，请你将 s 分割成一些 子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 * <p>
 * 示例 1：
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 * <p>
 * 示例 2：
 * 输入：s = "a"
 * 输出：[["a"]]
 * <p>
 * <p>
 * 提示：
 * 1 <= s.length <= 16
 * s 仅由小写英文字母组成
 */
public class Partition {

    int count1;
    int count2;

    @Test
    public void test() {
//        System.out.println(partition3("aab"));
//        System.out.println(partition("abcdbaabd"));
//        System.out.println(partition("fff"));
//        System.out.println(partition("efe"));
//        System.out.println(partition("cdd"));
        System.out.println(partition2_1("cbbbcc"));
        System.out.println(partition3("cbbbcc"));
        System.out.println(count1);
        System.out.println(count2);
    }

    /**
     *TODO
     * 方法一：选或不选
     * 方法二：枚举字串结束的位置
     */

    /**
     * 选或不选
     * @param str
     */
    public void partition4(String str) {

    }


    //dp+缓存 10s
    public List<List<String>> partition2_1(String s) {
        char[] chars = s.toCharArray();
        return dfs(chars, chars.length - 1, new ArrayList[s.length()]);
    }

    private List<List<String>> dfs(char[] chars, int r, List<List<String>>[] cache) {
        if (r < 0) {
            List<List<String>> ans = new ArrayList<>();
            ans.add(new ArrayList<>());
            return ans;
        }

        if (cache[r] != null) return cache[r];

        List<List<String>> ans = new ArrayList<>();
        cache[r] = ans;
        for (int i = r; i >= 0; i--) {
            if (isHuiWen(chars, i, r)) {
                String s = new String(Arrays.copyOfRange(chars, i, r + 1));
                for (List<String> partition : dfs(chars, i - 1, cache)) {
                    List<String> newList = newArrayList1(partition);
                    newList.add(s);
                    ans.add(newList);
                }
            }
        }
        return ans;
    }


    // DP 15s
    public List<List<String>> partition2(String s) {
        char[] chars = s.toCharArray();
        return dfs(chars, chars.length - 1);
    }

    private List<List<String>> dfs(char[] chars, int r) {
        List<List<String>> ans = new ArrayList<>();
        if (r < 0) {
            ans.add(new ArrayList<>());
            return ans;
        }
        for (int i = r; i >= 0; i--) {
            if (isHuiWen(chars, i, r)) {
                String s = new String(Arrays.copyOfRange(chars, i, r + 1));
                for (List<String> partition : dfs(chars, i - 1)) {
                    partition.add(s);
                    ans.add(partition);
                }
            }
        }
        return ans;
    }

    // DP 迭代, 17s? Problem: 测试效果看 TestDfsOrder 这个类 ，结果大多数应该都是这个快，不知道 leetcode 为啥会这样？
    public List<List<String>> partition3(String s) {
        List<List<String>>[] dfs = new ArrayList[s.length() + 1];
        dfs[0] = new ArrayList<>();
        dfs[0].add(new ArrayList<>());
        char[] chars = s.toCharArray();
        for (int i = 1; i <= chars.length; i++) {
            dfs[i] = new ArrayList<>();
            for (int j = i-1; j >= 0; j--) {
                if (isHuiWen(chars, j, i-1)) {
                    String tmp = new String(Arrays.copyOfRange(chars, j, i));
                    for (List<String> list : dfs[j]) {
                        List<String> newList = newArrayList2(list);
                        newList.add(tmp);
                        dfs[i].add(newList);
                    }
                }
            }
        }
        return dfs[s.length()];
    }

    private List<String> newArrayList1(List<String> list) {
        count1++;
        return new ArrayList<>(list);
    }

    private List<String> newArrayList2(List<String> list) {
        count2++;
        return new ArrayList<>(list);
    }

    private boolean isHuiWen(char[] chars, int start, int end) {
        if (start == end) return true;
        int l = start;
        int r = end;
        while (l < r) {
            if (chars[l++] != chars[r--]) return false;
        }
        return true;
    }


    public List<List<String>> partition(String s) {
        // 分割成全部为回文
        return dfs(s, s.length() - 1, new HashSet<>());
    }

    private List<List<String>> dfs(String input, int idx, Set<String> cache) {
        if (idx == 0) {
            List<List<String>> ans = new ArrayList<>();
            ans.add(new ArrayList<>(Collections.singletonList("" + input.charAt(0))));
            return ans;
        }
        List<List<String>> ans = dfs(input, idx - 1, cache);
        for (int i = 0, len = ans.size(); i < len; i++) {
            List<String> list = ans.get(i);
            String s = "" + input.charAt(idx);
            for (int j = list.size() - 1; j >= 0; j--) {
                s = list.get(j) + s;
                if (isHuiWen(s)) {
                    List<String> tmp = new ArrayList<>(list.subList(0, j));
                    tmp.add(s);
                    String tmpStr = "";
                    if (!cache.contains((tmpStr = String.join(",", tmp)))) {
                        cache.add(tmpStr);
                        ans.add(tmp);
                    }
                }
            }
            list.add(String.valueOf(input.charAt(idx)));
        }

        return ans;
    }

    private boolean isHuiWen(String str) {
        if (str.length() == 1) return true;
        int l = 0;
        int r = str.length() - 1;
        while (l < r) {
            if (str.charAt(l++) != str.charAt(r--)) return false;
        }
        return true;
    }

    /*private void checkHuiWenAndAddSplit(List<String> list, char c, List<List<String>> ans) {
        String str = list.get(list.size() - 1) + c;
        int l = 0;
        int r = str.length() - 1;
        while (l < r) {
            if (str.charAt(l) == str.charAt(r)) {
                r--;
            }else {
                r = str.length() - 1;
            }
            l++;
        }
        if (r < str.length() - 1) {
            List<String> tmp = new ArrayList<>(list.subList(0, list.size() - 1));
            int start = r - (str.length() - 1 - l);
            for (int i = 0; i < start; i++) {
                tmp.add(String.valueOf(str.charAt(i)));
            }
            tmp.add(str.substring(start));
            ans.add(tmp);
        }
    }*/


    /*private List<List<String>> dfs(char[] chars, int idx) {
        if (idx < 0) {
            List<List<String>> list = new ArrayList<>();
            list.add(new ArrayList<>());
            return list;
        }
        List<List<String>> ans = dfs(chars, idx - 1);
        for (int i = 0, len = ans.size(); i < len; i++) {
            List<String> list = ans.get(i);
            list.add(String.valueOf(chars[idx]));
        }

        // 检查 char[0] ~ char[idx] 是否存在回文
        int l = 0;
        int r = idx;
        while (l < r) {
            if (chars[l] == chars[r]) {
                r --;
            }else {
                r = idx;
            }
            l ++;
        }

        // 存在回文
        if (r < idx) {
            List<String> tmp = new ArrayList<>();
            int start = r - (idx - l);
            for (int i = 0; i < start; i++) {
                tmp.add(String.valueOf(chars[i]));
            }
            tmp.add(new String(Arrays.copyOfRange(chars, start, idx + 1)));
            ans.add(tmp);
        }

        return ans;
    }*/


    /*

    // 倒序的
    public List<List<String>> partition(String s) {
        // 分割成全部为回文
        return dfs(s.toCharArray(), 0);
    }

    private List<List<String>> dfs(char[] chars, int idx) {
        if (idx == chars.length) {
            List<List<String>> list = new ArrayList<>();
            list.add(new ArrayList<>());
            return list;
        }
        List<List<String>> ans = dfs(chars, idx + 1);
        for (List<String> list: ans) {
            list.add(String.valueOf(chars[idx]));
        }

        // 检查 char[idx] 和 char[idx+1] ~ char[len-1] 是否存在回文串
        int l = idx;
        int r = chars.length - 1;
        while (l < r) {
            if (chars[l] == chars[r]) {
                l++;
            }else {
                l = idx;
            }
            r--;
        }

        // 存在回文
        if (l > idx) {
            List<String> tmp = new ArrayList<>();
            int end = l + r - idx + 1;
            tmp.add(new String(Arrays.copyOfRange(chars, idx, end)));
            while (end < chars.length) {
                tmp.add(String.valueOf(chars[end++]));
            }
            ans.add(tmp);
        }

        return ans;
    }*/

}
