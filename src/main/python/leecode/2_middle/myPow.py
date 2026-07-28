# 50. Pow(x, n)
# 已解答
# 中等
# 相关标签
# premium lock icon
# 相关企业
# 实现 pow(x, n) ，即计算 x 的整数 n 次幂函数（即，xn ）。
#
#
#
# 示例 1：
#
# 输入：x = 2.00000, n = 10
# 输出：1024.00000
# 示例 2：
#
# 输入：x = 2.10000, n = 3
# 输出：9.26100
# 示例 3：
#
# 输入：x = 2.00000, n = -2
# 输出：0.25000
# 解释：2-2 = 1/22 = 1/4 = 0.25
#
#
# 提示：
#
# -100.0 < x < 100.0
# -231 <= n <= 231-1
# n 是一个整数
# 要么 x 不为零，要么 n > 0 。
# -104 <= xn <= 104
from functools import cache


class Solution:
    def myPow(self, x: float, n: int) -> float:
        @cache
        def dfs(x: float, m: int) -> float:
            if m == 0:
                return 1
            return dfs(x, m // 2) * dfs(x, m // 2) if m & 1 == 0 else x * dfs(x, m - 1)

        return dfs(x, n) if n > 0 else dfs(1/x, -n)

if __name__ == '__main__':
    print(Solution().myPow(2, 10))