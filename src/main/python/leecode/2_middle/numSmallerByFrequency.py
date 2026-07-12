# 1170. 比较字符串最小字母出现频次
# 已解答
# 中等
# 相关标签
# premium lock icon
# 相关企业
# 提示
# 定义一个函数 f(s)，统计 s  中（按字典序比较）最小字母的出现频次 ，其中 s 是一个非空字符串。
#
# 例如，若 s = "dcce"，那么 f(s) = 2，因为字典序最小字母是 "c"，它出现了 2 次。
#
# 现在，给你两个字符串数组待查表 queries 和词汇表 words 。对于每次查询 queries[i] ，需统计 words 中满足 f(queries[i]) < f(W) 的 词的数目 ，W 表示词汇表 words 中的每个词。
#
# 请你返回一个整数数组 answer 作为答案，其中每个 answer[i] 是第 i 次查询的结果。
#
#
#
# 示例 1：
#
# 输入：queries = ["cbd"], words = ["zaaaz"]
# 输出：[1]
# 解释：查询 f("cbd") = 1，而 f("zaaaz") = 3 所以 f("cbd") < f("zaaaz")。
# 示例 2：
#
# 输入：queries = ["bbb","cc"], words = ["a","aa","aaa","aaaa"]
# 输出：[1,2]
# 解释：第一个查询 f("bbb") < f("aaaa")，第二个查询 f("aaa") 和 f("aaaa") 都 > f("cc")。
#
#
# 提示：
#
# 1 <= queries.length <= 2000
# 1 <= words.length <= 2000
# 1 <= queries[i].length, words[i].length <= 10
# queries[i][j]、words[i][j] 都由小写英文字母组成
from typing import List


class Solution:
    def numSmallerByFrequency(self, queries: List[str], words: List[str]) -> List[int]:
        cnts = []
        for s in words:
            cnts.append(s.count(min(s)))
        cnts.sort()

        ans = list()
        n = len(cnts)
        for s in queries:
            ans.append(n - self.lowerBound(cnts, s.count(min(s))))
        return ans

    def lowerBound(self, arr: List[int], target: int):
        l = -1
        r = len(arr)
        while l + 1 < r:
            mid = (l + r) >> 1
            if arr[mid] > target:
                r = mid
            else:
                l = mid
        return r



