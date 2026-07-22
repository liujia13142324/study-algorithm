

class Solution:
    def countMonobit(self, n: int) -> int:
        ans = 1
        i = 1
        while i <= n:
            ans += 1
            i = (i << 1) + 1
        return ans