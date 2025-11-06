package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 93. 复原 IP 地址
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 *
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
 *
 *
 * 示例 1：
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 *
 * 示例 2：
 * 输入：s = "0000"
 * 输出：["0.0.0.0"]

 * 示例 3：
 * 输入：s = "101023"
 * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 20
 * s 仅由数字组成
 */
public class RestoreIpAddresses {

    @Test
    public void test () {
//        System.out.println(restoreIpAddresses("25525511135"));
//        System.out.println(restoreIpAddresses("0000"));
        System.out.println(restoreIpAddresses("101023"));
//        System.out.println(restoreIpAddresses("19216811"));
    }

    public List<String> restoreIpAddresses(String s) {
        if (s.length() < 4 || s.length() > 24) {
            return Collections.emptyList();
        }
        String[] path = new String[4];
        List<String> ans = new ArrayList<>();
        dfs(0, 0, 4, s, path, ans);
        return ans;
    }

    private void dfs(int start, int end, int n, String s, String[] path, List<String> ans) {

        if (n == 1) {
            if (s.length() - start > 3) {
                return;
            }
            if (s.charAt(start) == '0' && start != s.length() - 1) {
                return;
            }
            String tmp = s.substring(start);
            if (Integer.parseInt(tmp) > 255) {
                return;
            }
            path[path.length - n] = tmp;
            ans.add(String.join(".", path));
            return;
        }

        String tmp = s.substring(start, end + 1);
        if (Integer.parseInt(tmp) > 255) {
            return;
        }
        path[path.length - n] = tmp;
        dfs(end + 1, end + 1, n - 1, s, path, ans);

        // not choose,
        if (s.charAt(start) != '0' && end < s.length() - n && end - start + 1 < 3) {
            dfs(start, end + 1, n, s, path, ans);
        }
    }

}
