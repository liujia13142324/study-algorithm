package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 216. 组合总和 III
 * 找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
 *
 * 只使用数字1到9
 * 每个数字 最多使用一次
 * 返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。
 *
 *
 * 示例 1:
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 解释:
 * 1 + 2 + 4 = 7
 * 没有其他符合的组合了。

 * 示例 2:
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 * 解释:
 * 1 + 2 + 6 = 9
 * 1 + 3 + 5 = 9
 * 2 + 3 + 4 = 9
 * 没有其他符合的组合了。

 * 示例 3:
 * 输入: k = 4, n = 1
 * 输出: []
 * 解释: 不存在有效的组合。
 * 在[1,9]范围内使用4个不同的数字，我们可以得到的最小和是1+2+3+4 = 10，因为10 > 1，没有有效的组合。
 *
 *
 * 提示:
 *
 * 2 <= k <= 9
 * 1 <= n <= 60
 */
public class CombinationSum3 {

    @Test
    public void test() {

    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        Integer[] path = new Integer[k];
        dfs(1, k, n, ans, path);
        return ans;
    }

    public void dfs(int curr, int k, int target, List<List<Integer>> ans, Integer[] path) {

        /*
        // 等于 1 的时候，也能推出
        if (k == 1) {
            if (target >= curr && target <= 9) {
                path[path.length - k] = target;
                ans.add(new ArrayList<>(Arrays.asList(path)));
            }
            return;
        }*/

        if (k == 0) {
            if (target == 0) {
                ans.add(new ArrayList<>(Arrays.asList(path)));
            }
            return;
        }

        // 从小往大选，一旦超过 target，就不用选了，后续怎么都会大于 target
        if (target - curr >= 0) {
            //  选
            path[path.length - k] = curr;
            dfs(curr + 1, k - 1, target - curr, ans, path);

            if(9 - curr >= k) {
                // 不选
                dfs(curr + 1, k, target, ans, path);
            }
        }
    }


    public List<List<Integer>> combinationSum3_2(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        Integer[] path = new Integer[k];
        dfs2(1, k, n, ans, path);
        return ans;
    }

    // 剪枝1
    public void dfs2(int curr, int k, int target, List<List<Integer>> ans, Integer[] path) {

        if (k == 0) {
            if (target == 0) ans.add(new ArrayList<>(Arrays.asList(path)));
            return;
        }

        for (int i = curr; i <= 9 - k + 1; i++) {
            if (target - i < 0) break;
            path[path.length - k] = i;
            dfs2(i + 1, k - 1, target - i, ans, path);
        }
    }

    // 剪枝2
    public void dfs3(int curr, int k, int target, List<List<Integer>> ans, Integer[] path) {
        // 当 target 小于0，或者大于最大的 k 个数之和，说明不满足
        if (target < 0 || target > (9 - k + 1 + 9) * k / 2) {
            return;
        }
        // 上面 target < 0 和 target > 0 (k = 0) 情况都会进上面，所以下面只有 target == 0 这一种情况
        if (k == 0) {
            ans.add(new ArrayList<>(Arrays.asList(path)));
            return;
        }

        for (int i = curr; i <= 9 - k + 1; i++) {
            path[path.length - k] = i;
            dfs3(i + 1, k - 1, target - i, ans, path);
        }
    }
}
