import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaskB9Testing {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int m;
    static long[] array;
    static StringBuilder result = new StringBuilder();

    static void readInputData() throws IOException {
        String[] firstLine = reader.readLine().split(" ");
        n = Integer.parseInt(firstLine[0]);
        m = Integer.parseInt(firstLine[1]);
        array = new long[n];
    }

    static class Node {
        long sum;
        long add;
        int replace;

        Node() {
            sum = 0;
            add = 0;
            replace = -1;
        }
    }

    public static class SegmentTree {
        private static Node[] tree;
        private static final int REPLACE_FLAG = -1;

        public SegmentTree(int size) {
            tree = new Node[getTreeSize(size)];
            for (int i = 0; i < tree.length; i++) {
                tree[i] = new Node();
            }
        }

        private static int getTreeSize(int n) {
            int size = 1;
            while (size < n) {
                size *= 2;
            }
            return 2 * size;
        }

        private static void pushTree(int v, int treeSize) {
            if (tree[v].replace != REPLACE_FLAG) {
                updateReplaceOperation(v, treeSize);
            }
            updateAddOperation(v, treeSize);
        }

        private static void updateAddOperation(int v, int treeSize) {
            if (treeSize != 1 && tree[v].add != 0) {
                tree[v * 2].add += tree[v].add;
                tree[v * 2 + 1].add += tree[v].add;
            }
            tree[v].sum += tree[v].add * treeSize;
            tree[v].add = 0;
        }

        private static void updateReplaceOperation(int v, int treeSize) {
            if (treeSize != 1) {
                tree[v * 2].replace = tree[v * 2 + 1].replace = tree[v].replace;
                tree[v * 2].add = tree[v * 2 + 1].add = 0;
            }
            tree[v].sum = (long) tree[v].replace * treeSize;
            tree[v].replace = REPLACE_FLAG;
        }

        private static long getSegmentSum(int node, int segStart, int segEnd, int queryStart, int queryEnd) {
            pushTree(node, segEnd - segStart + 1);
            if (queryStart > queryEnd) {
                return 0;
            }
            if (queryStart == segStart && queryEnd == segEnd) {
                return tree[node].sum;
            }
            int mid = getMiddle(segStart, segEnd);
            return getSegmentSum(node * 2, segStart, mid, queryStart, Math.min(queryEnd, mid)) + getSegmentSum(node * 2 + 1, mid + 1, segEnd, Math.max(queryStart, mid + 1), queryEnd);
        }

        private static int getMiddle(int start, int end) {
            return (start + end) / 2;
        }

        private static void updateSegmentTree(int node, int segStart, int segEnd, int updateStart, int updateEnd, long value, boolean isReplace) {
            pushTree(node, segEnd - segStart + 1);
            if (updateStart > updateEnd) return;
            if (updateStart == segStart && segEnd == updateEnd) {
                if (isReplace) {
                    tree[node].replace = (int) value;
                    tree[node].add = 0;
                } else {
                    tree[node].add += value;
                }
                pushTree(node, segEnd - segStart + 1);
            } else {
                int mid = getMiddle(segStart, segEnd);
                updateSegmentTree(node * 2, segStart, mid, updateStart, Math.min(updateEnd, mid), value, isReplace);
                updateSegmentTree(node * 2 + 1, mid + 1, segEnd, Math.max(updateStart, mid + 1), updateEnd, value, isReplace);
                tree[node].sum = tree[node * 2].sum + tree[node * 2 + 1].sum;
            }
        }

        public static void updateReplace(int node, int segStart, int segEnd, int updateStart, int updateEnd, long value) {
            updateSegmentTree(node, segStart, segEnd, updateStart, updateEnd, value, true);
        }

        public static void updateAdd(int node, int segStart, int segEnd, int updateStart, int updateEnd, long value) {
            updateSegmentTree(node, segStart, segEnd, updateStart, updateEnd, value, false);
        }
    }

    public static void main(String[] args) throws IOException {
        readInputData();
        SegmentTree segmentTree = new SegmentTree(n);
        for (int i = 0; i < m; i++) {
            String[] request = reader.readLine().split(" ");
            int operation = Integer.parseInt(request[0]);
            int left = Integer.parseInt(request[1]);
            int right = Integer.parseInt(request[2]);
            int value;
            switch (operation) {
                case 1:
                    value = Integer.parseInt(request[3]);
                    segmentTree.updateReplace(1, 0, n - 1, left, right - 1, value);
                    break;
                case 2:
                    value = Integer.parseInt(request[3]);
                    segmentTree.updateAdd(1, 0, n - 1, left, right - 1, value);
                    break;
                case 3:
                    result.append(segmentTree.getSegmentSum(1, 0, n - 1, left, right - 1)).append("\n");
                    break;
            }
        }
        System.out.println(result);
    }
}