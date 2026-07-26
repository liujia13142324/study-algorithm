# 并查集

class UnionFind:
    def __init__(self, size: int):
        self.root = [i for i in range(size)]

    def find(self, x: int):
        if x != self.root[x]:
            self.root[x] = self.find(self.root[x])
        return self.root[x]

    def merger(self, x: int, y: int) -> bool:
        x = self.find(x)
        y = self.find(y)
        if x != y:
            self.root[x] = y
            return True
        return False


