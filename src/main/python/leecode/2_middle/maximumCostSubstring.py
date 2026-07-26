# 2606. 找到最大开销的子字符串
# 已解答
# 中等
# 相关标签
# premium lock icon
# 相关企业
# 提示
# 给你一个字符串 s ，一个字符 互不相同 的字符串 chars 和一个长度与 chars 相同的整数数组 vals 。
#
# 子字符串的开销 是一个子字符串中所有字符对应价值之和。空字符串的开销是 0 。
#
# 字符的价值 定义如下：
#
# 如果字符不在字符串 chars 中，那么它的价值是它在字母表中的位置（下标从 1 开始）。
# 比方说，'a' 的价值为 1 ，'b' 的价值为 2 ，以此类推，'z' 的价值为 26 。
# 否则，如果这个字符在 chars 中的位置为 i ，那么它的价值就是 vals[i] 。
# 请你返回字符串 s 的所有子字符串中的最大开销。
#
#
#
# 示例 1：
#
# 输入：s = "adaa", chars = "d", vals = [-1000]
# 输出：2
# 解释：字符 "a" 和 "d" 的价值分别为 1 和 -1000 。
# 最大开销子字符串是 "aa" ，它的开销为 1 + 1 = 2 。
# 2 是最大开销。
# 示例 2：
#
# 输入：s = "abc", chars = "abc", vals = [-1,-1,-1]
# 输出：0
# 解释：字符 "a" ，"b" 和 "c" 的价值分别为 -1 ，-1 和 -1 。
# 最大开销子字符串是 "" ，它的开销为 0 。
# 0 是最大开销。
#
#
# 提示：
#
# 1 <= s.length <= 105
# s 只包含小写英文字母。
# 1 <= chars.length <= 26
# chars 只包含小写英文字母，且 互不相同 。
# vals.length == chars.length
# -1000 <= vals[i] <= 1000
from typing import List


class Solution:
    def maximumCostSubstring(self, s: str, chars: str, vals: List[int]) -> int:
        price_mapping = dict(zip(chars, vals))
        sums = 0
        mins = 0
        ans = 0
        for c in s:
            sums += price_mapping.get(c, ord(c) - ord('a') + 1)
            ans = max(ans, sums - mins)
            mins = min(mins, sums)
        return ans

    def maximumCostSubstring(self, s: str, chars: str, vals: List[int]) -> int:
        price_mapping = dict(zip(chars, vals))
        pre = 0
        ans = 0
        for c in s:
            pre = max(pre, 0) + price_mapping.get(c, ord(c) - ord('a') + 1)
            ans = max(ans, pre)
        return ans


if __name__ == '__main__':
    print(list(zip([1,2,3], [4,5,6])))
    print(dict(zip([1,2,3], [4,5,6])))
