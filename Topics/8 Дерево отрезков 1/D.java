import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaskD8FindMinIndexJ {
    static class Node {
        int left;
        int right;
        int max;
    }

    static class SegmentTree {
        Node[] intervals;

        public SegmentTree(int[] array) {
            intervals = new Node[array.length * 4];
            buildSegmentTree(array, 0, array.length, 0);
        }

        void updateValues(int v) {
            int left = v * 2 + 1;
            int right = v * 2 + 2;
            if (intervals[left].max == intervals[right].max) {
                intervals[v].max = intervals[left].max;
            } else {
                if (intervals[left].max > intervals[right].max) {
                    intervals[v].max = intervals[left].max;
                } else {
                    intervals[v].max = intervals[right].max;
                }
            }
        }

        void buildSegmentTree(int[] array, int left, int right, int v) {
            intervals[v] = new Node();
            intervals[v].left = left;
            intervals[v].right = right;
            if (right - left == 1) {
                intervals[v].max = array[left];
            } else {
                int middle = (right + left) / 2;
                buildSegmentTree(array, left, middle, v * 2 + 1);
                buildSegmentTree(array, middle, right, v * 2 + 2);
                updateValues(v);
            }
        }

        int getIndex(int x, int left) {
            return findElementLargeThanX(0, x, left);
        }

        int findElementLargeThanX(int v, int x, int left) {
            if (intervals[v].right <= left) {
                return -1;
            }
            if (intervals[v].right - intervals[v].left == 1) {
                return intervals[v].max >= x ? intervals[v].left : -1;
            }
            int result = -1;
            int mid = (intervals[v].left + intervals[v].right) / 2;
            if (mid > left && intervals[2 * v + 1].max >= x) {
                result = findElementLargeThanX(2 * v + 1, x, left);
            }
            if (result == -1 && intervals[2 * v + 2].max >= x) {
                result = findElementLargeThanX(2 * v + 2, x, left);
            }
            return result;
        }

        void addElement(int index, int value) {
            findPlaceForAddElement(0, index, value);
        }

        void findPlaceForAddElement(int v, int index, int value) {
            if (intervals[v].right - intervals[v].left == 1) {
                intervals[v].max = value;
            } else {
                if (intervals[v * 2 + 2].left > index) {
                    findPlaceForAddElement(v * 2 + 1, index, value);
                } else {
                    findPlaceForAddElement(v * 2 + 2, index, value);
                }
                updateValues(v);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        StringBuilder result = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = reader.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int q = Integer.parseInt(firstLine[1]);
        int[] array = new int[n];
        String[] arrayOfValues = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            int value = Integer.parseInt(arrayOfValues[i]);
            array[i] = value;
        }
        SegmentTree segmentTree = new SegmentTree(array);
        for (int j = 0; j < q; j++) {
            String[] request = reader.readLine().split(" ");
            int typeOfOperation = Integer.parseInt(request[0]);
            int firstKey = Integer.parseInt(request[1]);
            int secondKey = Integer.parseInt(request[2]);
            switch (typeOfOperation) {
                case 1:
                    segmentTree.addElement(firstKey, secondKey);
                    break;
                case 2:
                    result.append(segmentTree.getIndex(firstKey, secondKey)).append("\n");
                    break;
            }
        }
        System.out.println(result);
    }
}