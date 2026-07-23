# 3186. 施咒的最大总伤害
# 已解答
# 中等
# 相关标签
# premium lock icon
# 相关企业
# 提示
# 一个魔法师有许多不同的咒语。
#
# 给你一个数组 power ，其中每个元素表示一个咒语的伤害值，可能会有多个咒语有相同的伤害值。
#
# 已知魔法师使用伤害值为 power[i] 的咒语时，他们就 不能 使用伤害为 power[i] - 2 ，power[i] - 1 ，power[i] + 1 或者 power[i] + 2 的咒语。
#
# 每个咒语最多只能被使用 一次 。
#
# 请你返回这个魔法师可以达到的伤害值之和的 最大值 。
#
#
#
# 示例 1：
#
# 输入：power = [1,1,3,4]
#
# 输出：6
#
# 解释：
#
# 可以使用咒语 0，1，3，伤害值分别为 1，1，4，总伤害值为 6 。
#
# 示例 2：
#
# 输入：power = [7,1,6,6]
#
# 输出：13
#
# 解释：
#
# 可以使用咒语 1，2，3，伤害值分别为 1，6，6，总伤害值为 13 。
from collections import Counter
from typing import List


class Solution:
    def maximumTotalDamage(self, power: List[int]) -> int:
        def find(target: int):
            l = -1
            r = len(power)
            while l + 1 < r:
                mid = (l + r) >> 1
                if power[mid] < target:
                    l = mid
                else:
                    r = mid
            return l

        power = sorted(power)
        n = len(power)
        dp = [[0] * (n + 1), [0] * (n + 1)]
        ans = dp[1][1] = power[0]
        for i in range(2, n + 1):
            if power[i - 1] == power[i - 2]:
                dp[1][i] = dp[1][i - 1] + power[i - 1]
            else:
                j = find(power[i - 1] - 2) + 1
                dp[1][i] = max(dp[0][j], dp[1][j]) + power[i - 1]
            dp[0][i] = ans
            ans = max(ans, dp[1][i])
        return ans

    def maximumTotalDamage(self, power: List[int]) -> int:
        cnt = Counter(power)
        a = sorted(cnt)
        f = [0] * (len(a) + 1)
        j = 0
        for i, x in enumerate(a):
            while a[j] < a[i] - 2:
                j += 1
            f[i + 1] = max(f[i], f[j] + x * cnt[x])
        return f[-1]


if __name__ == '__main__':
    print(Solution().maximumTotalDamage([7,1,6,6]))
