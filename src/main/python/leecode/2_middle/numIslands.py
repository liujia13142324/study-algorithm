# 200. 岛屿数量
# 已解答
# 中等
# 相关标签
# premium lock icon
# 相关企业
# 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
#
# 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
#
# 此外，你可以假设该网格的四条边均被水包围。
#
#
#
# 示例 1：
#
# 输入：grid = [
#   ['1','1','1','1','0'],
#   ['1','1','0','1','0'],
#   ['1','1','0','0','0'],
#   ['0','0','0','0','0']
# ]
# 输出：1
# 示例 2：
#
# 输入：grid = [
#   ['1','1','0','0','0'],
#   ['1','1','0','0','0'],
#   ['0','0','1','0','0'],
#   ['0','0','0','1','1']
# ]
# 输出：3
#
#
# 提示：
#
# m == grid.length
# n == grid[i].length
# 1 <= m, n <= 300
# grid[i][j] 的值为 '0' 或 '1'
from typing import List


class Solution:
    def numIslands(self, grid: List[List[str]]) -> int:
        n = len(grid)
        n2 = len(grid[0])

        def dfs(i, j, grid: List[List[str]]):
            if i < 0 or i == n or j < 0 or j == n2 or grid[i][j] != '1':
                return
            grid[i][j] = '2'
            dfs(i + 1, j, grid)
            dfs(i - 1, j, grid)
            dfs(i, j + 1, grid)
            dfs(i, j - 1, grid)

        ans = 0
        for i in range(0, n):
            for j in range(0, n2):
                if grid[i][j] == '1':
                    ans += 1
                    dfs(i, j, grid)
        return ans