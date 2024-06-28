#include <iostream>
#include <vector>
#include <string>
#include <sstream>

class Node {
public:
    int count, totalSum, set, left, right;
    bool up;
    Node() = default;
    Node(int left, int right) : left(left), right(right), up(false), count(0), totalSum(0), set(0) {}
};

class SegmentTree {
private:
    std::vector<Node> tree;
    void build(int left, int right, int v) {
        tree[v] = Node(left, right);
        if (left < right) {
            int middle = (left + right) >> 1;
            build(left, middle, v * 2);
            build(middle + 1, right, v * 2 + 1);
        }
    }

    void push(int v) {
        if (!tree[v].up) return;
        tree[v].count = (tree[v].set == 1 ? (tree[v].right - tree[v].left + 1) : 0);
        tree[v].totalSum = (tree[v].set == 1 ? 1 : 0);
        tree[v].up = false;
        if (tree[v].left < tree[v].right) {
            int left = v * 2;
            int right = v * 2 + 1;
            tree[left].set =tree[v].set;
            tree[right].set =tree[v].set;
            tree[left].up = true;
            tree[right].up = true;
        }
    }

public:
    SegmentTree(int size) : tree(size * 4) {
        build(0, size, 1);
    }
    void update(int v, int left, int right, int value) {
        if (tree[v].right < left || tree[v].left > right) {
            return;
        }
        if (tree[v].right <= right && tree[v].left >= left) {
            push(v);
            tree[v].set = value;
            tree[v].up = true;
            return;
        }
        push(v);
        update(v * 2, left, right, value);
        update(v * 2 + 1, left, right, value);
        int leftNode = v * 2;
        while (true) {
         push(leftNode);
         if (tree[leftNode].left == tree[leftNode].right) {
            break;
         }
         leftNode = leftNode * 2 + 1;
      }
      bool leftIsBlack = tree[leftNode].count == 1;
      int rightNode = v * 2 + 1;
            while (true) {
                push(rightNode);
                if (tree[rightNode].left == tree[rightNode].right) {
                    break;
                }
                rightNode = rightNode * 2;
            }
        bool rightIsBlack = tree[rightNode].count == 1;
        tree[v].count = tree[v * 2].count + tree[v * 2 + 1].count;
        tree[v].totalSum = tree[v * 2].totalSum + tree[v * 2 + 1].totalSum;
        if (rightIsBlack && leftIsBlack) {
            tree[v].totalSum--;
        }
    }

    Node& getRoot() {
        return tree[1];
    }
};

int main() {
   std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);
    std::cout.tie(nullptr);
    int n;
    std::cin >> n;
    SegmentTree segmentTree(1000000);
    for (int i = 0; i < n; i++) {
        char color;
        int leftCoord, length;
        std::cin >> color >> leftCoord >> length;
        length = (length < 0) ? ++length : --length;
        int isWhite = color == 'W' ? 0 : 1;
        segmentTree.update(1, leftCoord + 500000, leftCoord + length + 500000, isWhite);
        std::cout << segmentTree.getRoot().totalSum << ' ' << segmentTree.getRoot().count << '\n';
    }
    return 0;
}