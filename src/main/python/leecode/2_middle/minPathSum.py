# 64. 最小路径和
# 已解答
# 中等
# 相关标签
# premium lock icon
# 相关企业
# 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
#
# 说明：每次只能向下或者向右移动一步。
#
#
#
# 示例 1：
#
#
# 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
# 输出：7
# 解释：因为路径 1→3→1→1→1 的总和最小。
# 示例 2：
#
# 输入：grid = [[1,2,3],[4,5,6]]
# 输出：12
#
#
# 提示：
#
# m == grid.length
# n == grid[i].length
# 1 <= m, n <= 200
# 0 <= grid[i][j] <= 200
from typing import List


class Solution:
    def minPathSum(self, grid: List[List[int]]) -> int:
        n = len(grid)
        m = len(grid[0])
        dp = [[0 for _ in range(m)] for _ in range(n)]

        for i in range(n):
            for j in range(m):
                dp[i][j] = grid[i][j]
                if i > 0 and j > 0:
                    dp[i][j] += min(dp[i-1][j], dp[i][j-1])
                elif i > 0:
                    dp[i][j] += dp[i-1][j]
                elif j > 0:
                    dp[i][j] += dp[i][j-1]

        return dp[n-1][m-1]


if __name__ == '__main__':
    print(Solution().minPathSum([[1,3,1],[1,5,1],[4,2,1]]))
    # dp = [[0] * 3] * 3

    # dp[0][1] = 2
    # print(dp)