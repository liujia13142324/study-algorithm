# 1423. 可获得的最大点数
# 已解答
# 中等
# 相关标签
# premium lock icon
# 相关企业
# 提示
# 几张卡牌 排成一行，每张卡牌都有一个对应的点数。点数由整数数组 cardPoints 给出。
#
# 每次行动，你可以从行的开头或者末尾拿一张卡牌，最终你必须正好拿 k 张卡牌。
#
# 你的点数就是你拿到手中的所有卡牌的点数之和。
#
# 给你一个整数数组 cardPoints 和整数 k，请你返回可以获得的最大点数。
#
#
#
# 示例 1：
#
# 输入：cardPoints = [1,2,3,4,5,6,1], k = 3
# 输出：12
# 解释：第一次行动，不管拿哪张牌，你的点数总是 1 。但是，先拿最右边的卡牌将会最大化你的可获得点数。最优策略是拿右边的三张牌，最终点数为 1 + 6 + 5 = 12 。
# 示例 2：
#
# 输入：cardPoints = [2,2,2], k = 2
# 输出：4
# 解释：无论你拿起哪两张卡牌，可获得的点数总是 4 。
# 示例 3：
#
# 输入：cardPoints = [9,7,7,9,7,7,9], k = 7
# 输出：55
# 解释：你必须拿起所有卡牌，可以获得的点数为所有卡牌的点数之和。
# 示例 4：
#
# 输入：cardPoints = [1,1000,1], k = 1
# 输出：1
# 解释：你无法拿到中间那张卡牌，所以可以获得的最大点数为 1 。
# 示例 5：
#
# 输入：cardPoints = [1,79,80,1,1,1,200,1], k = 3
# 输出：202
from math import inf
from typing import List


class Solution:
    def maxScore(self, cardPoints: List[int], k: int) -> int:
        _len = len(cardPoints)
        if k == _len:
            return sum(cardPoints)

        l = _len - k
        r = l
        loop_cnt = 0
        sums = 0
        ans = 0

        while loop_cnt < 2 * k:
            sums += cardPoints[r]
            if loop_cnt >= k:
                sums -= cardPoints[l]
                l = (l + 1) % _len
            ans = max(ans, sums)
            loop_cnt += 1
            r = (r + 1) % _len

        return ans

    def maxScore(self, cardPoints: List[int], k: int) -> int:
        n = len(cardPoints)
        m = n - k
        sums = sum(cardPoints[:m])
        min_val = sums
        for i in range(m, n):
            sums += cardPoints[i] - cardPoints[i - m]
            min_val = min(min_val, sums)
        return sum(cardPoints) - min_val


    def maxScore(self, cardPoints: List[int], k: int) -> int:
        n = len(cardPoints)
        sums = sum(cardPoints[:k])
        ans = sums
        for i in range(1, k+1):
            sums += cardPoints[n - i] - cardPoints[k - i]
            ans = max(ans, sums)
        return ans


if __name__ == '__main__':
    arr = [1,2,3,4,5]
    print(Solution().maxScore([1,2,3,4,5,6,1], 3))
