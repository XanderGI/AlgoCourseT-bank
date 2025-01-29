import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class TaskE8FindLIS {
    private static final int MOD = 1000000007;

    static int methodCompressArray(int[] array) {
        int max = 0;
        int[] tempArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            tempArray[i] = array[i];
        }
        Arrays.sort(tempArray);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < tempArray.length; i++) {
            if (!map.containsKey(tempArray[i])) {
                map.put(tempArray[i], max++);
            }
        }
        for (int i = 0; i < tempArray.length; i++) {
            array[i] = map.get(array[i]);
        }
        return max;
    }

    static class Node {
        int maxLen;
        int count;

        Node(int maxLen, int count) {
            this.maxLen = maxLen;
            this.count = count;
        }
    }

    static class SegmentTree {
        Node[] tree;

        SegmentTree(int size) {
            tree = new Node[4 * size];
            buildTree(0, size, 0);
        }

        void buildTree(int left, int right, int parent) {
            if (left == right) {
                tree[parent] = new Node(0, 1);
            } else {
                int mid = (left + right) / 2;
                buildTree(left, mid, 2 * parent + 1);
                buildTree(mid + 1, right, 2 * parent + 2);
                tree[parent] = merge(tree[2 * parent + 1], tree[2 * parent + 2]);
            }
        }

        Node getMaxLenAndCount(int left, int right, int parent, int qleft, int qright) {
            if (left > qright || right < qleft) {
                return new Node(0, 0);
            }
            if (qleft <= left && qright >= right) {
                return tree[parent];
            }
            int mid = (left + right) / 2;
            Node leftQuery = getMaxLenAndCount(left, mid, 2 * parent + 1, qleft, qright);
            Node rightQuery = getMaxLenAndCount(mid + 1, right, 2 * parent + 2, qleft, qright);
            return merge(leftQuery, rightQuery);
        }

        private Node merge(Node left, Node right) {
            if (left.maxLen > right.maxLen) {
                return left;
            } else if (left.maxLen < right.maxLen) {
                return right;
            } else {
                return new Node(left.maxLen, (left.count + right.count) % MOD);
            }
        }

        void update(int left, int right, int parent, int pos, int newLength, int newCount) {
            if (left == right) {
                if (newLength == tree[parent].maxLen) {
                    tree[parent].count = (tree[parent].count + newCount) % MOD;
                } else if (tree[parent].maxLen < newLength) {
                    tree[parent].count = newCount;
                    tree[parent].maxLen = newLength;
                }
                return;
            } else {
                int mid = (left + right) / 2;
                if (pos <= mid) {
                    update(left, mid, 2 * parent + 1, pos, newLength, newCount);
                } else {
                    update(mid + 1, right, 2 * parent + 2, pos, newLength, newCount);
                }
                tree[parent] = merge(tree[2 * parent + 1], tree[2 * parent + 2]);
            }
        }

        public static int findNumberOfLIS(int[] nums) {
            int max = methodCompressArray(nums);
            SegmentTree tree = new SegmentTree(max + 1);
            for (int i = 0; i < nums.length; i++) {
                int maxLength = 1;
                int count = 1;
                if (nums[i] > 0) {
                    Node temp = tree.getMaxLenAndCount(0, max, 0, 0, nums[i] - 1);
                    if (temp.maxLen + 1 > maxLength) {
                        maxLength = temp.maxLen + 1;
                        count = temp.count;
                    }
                }
                tree.update(0, max, 0, nums[i], maxLength, count);
            }
            return tree.tree[0].count;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        String[] data = reader.readLine().split(" ");
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(data[i]);
        }
        System.out.println((SegmentTree.findNumberOfLIS(array)) % MOD);
    }
}