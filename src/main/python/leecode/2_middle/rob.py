from functools import cache
from typing import List


class Solution:
    def rob(self, nums: List[int]) -> int:
        @cache
        def dfs(i):
            if i < 0:
                return 0
            return max(dfs(i - 1), dfs(i - 2) + nums[i])
        return dfs(len(nums) - 1)

    def rob(self, nums: List[int]) -> int:
        n = len(nums)
        dp = [0] * (n + 1)
        dp[0] = 0
        dp[1] = nums[0]
        for i in range(2, n + 1):
            dp[i] = max(dp[i - 1], dp[i - 2] + nums[i - 1])
        return dp[n]

    def rob(self, nums: List[int]) -> int:
        n = len(nums)
        pre = 0
        ans = nums[0]
        for i in range(2, n + 1):
            tmp = ans
            ans = max(ans, pre + nums[i - 1])
            pre = tmp
        return ans


if __name__ == '__main__':
    print(Solution().rob([1,2,3,1]))