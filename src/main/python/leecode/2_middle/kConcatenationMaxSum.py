# 1191. K 次串联后最大子数组之和
# 已解答
# 中等
# 相关标签
# premium lock icon
# 相关企业
# 提示
# 给定一个整数数组 arr 和一个整数 k ，通过重复 k 次来修改数组。
#
# 例如，如果 arr = [1, 2] ， k = 3 ，那么修改后的数组将是 [1, 2, 1, 2, 1, 2] 。
#
# 返回修改后的数组中的最大的子数组之和。注意，子数组长度可以是 0，在这种情况下它的总和也是 0。
#
# 由于 结果可能会很大，需要返回结果对 109 + 7 取 模。
#
#
#
# 示例 1：
#
# 输入：arr = [1,2], k = 3
# 输出：9
# 示例 2：
#
# 输入：arr = [1,-2,1], k = 5
# 输出：2
# 示例 3：
#
# 输入：arr = [-1,-2], k = 7
# 输出：0
#
#
# 提示：
#
# 1 <= arr.length <= 105
# 1 <= k <= 105
# -104 <= arr[i] <= 104
from typing import List


class Solution:
    def kConcatenationMaxSum(self, arr: List[int], k: int) -> int:
        dp = ans = sum = 0
        for num in arr:
            sum += num
            dp = max(dp, 0) + num
            ans = max(ans, dp)

        if k == 1:
            return ans

        if sum > 0:
            ans = ans + (k - 1) * sum

        for num in arr:
            dp = max(dp, 0) + num
            ans = max(ans, dp)

        return ans % 1000000007
