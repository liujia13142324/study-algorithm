# 3393. 统计异或值为给定值的路径数目
# 已解答
# 中等
# 相关标签
# premium lock icon
# 相关企业
# 提示
# 给你一个大小为 m x n 的二维整数数组 grid 和一个整数 k 。
#
# 你的任务是统计满足以下 条件 且从左上格子 (0, 0) 出发到达右下格子 (m - 1, n - 1) 的路径数目：
#
# 每一步你可以向右或者向下走，也就是如果格子存在的话，可以从格子 (i, j) 走到格子 (i, j + 1) 或者格子 (i + 1, j) 。
# 路径上经过的所有数字 XOR 异或值必须 等于 k 。
# 请你返回满足上述条件的路径总数。
#
# 由于答案可能很大，请你将答案对 109 + 7 取余 后返回。
#
#
#
# 示例 1：
#
# 输入：grid = [[2, 1, 5], [7, 10, 0], [12, 6, 4]], k = 11
#
# 输出：3
#
# 解释：
#
# 3 条路径分别为：
#
# (0, 0) → (1, 0) → (2, 0) → (2, 1) → (2, 2)
# (0, 0) → (1, 0) → (1, 1) → (1, 2) → (2, 2)
# (0, 0) → (0, 1) → (1, 1) → (2, 1) → (2, 2)
# 示例 2：
#
# 输入：grid = [[1, 3, 3, 3], [0, 3, 3, 2], [3, 0, 1, 1]], k = 2
#
# 输出：5
#
# 解释：
#
# 5 条路径分别为：
#
# (0, 0) → (1, 0) → (2, 0) → (2, 1) → (2, 2) → (2, 3)
# (0, 0) → (1, 0) → (1, 1) → (2, 1) → (2, 2) → (2, 3)
# (0, 0) → (1, 0) → (1, 1) → (1, 2) → (1, 3) → (2, 3)
# (0, 0) → (0, 1) → (1, 1) → (1, 2) → (2, 2) → (2, 3)
# (0, 0) → (0, 1) → (0, 2) → (1, 2) → (2, 2) → (2, 3)
# 示例 3：
#
# 输入：grid = [[1, 1, 1, 2], [3, 0, 3, 2], [3, 0, 2, 2]], k = 10
#
# 输出：0
#
#
#
# 提示：
#
# 1 <= m == grid.length <= 300
# 1 <= n == grid[r].length <= 300
# 0 <= grid[r][c] < 16
# 0 <= k < 16
from typing import List


class Solution:

    def countPathsWithXorValue(self, grid: List[List[int]], k: int) -> int:
        m = len(grid)
        n = len(grid[0])
        dp = [[[-1] * 16 for _ in range(n)] for _ in range(m)]

        def dfs(i, j, target):
            if i == 0 and j == 0:
                return 1 if target == grid[0][0] else 0
            if dp[i][j][target] != -1:
                return dp[i][j][target]

            next_target = target ^ grid[i][j]
            ans = 0
            if i > 0:
                ans += dfs(i - 1, j, next_target)
            if j > 0:
                ans += dfs(i, j - 1, next_target)

            dp[i][j][target] = ans % 1000000007

            return dp[i][j][target]

        return dfs(len(grid) - 1, len(grid[0]) - 1, k)


