import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class TaskA8SegmentTreeWithSum {

    static class Node {
        int left;
        int right;
        long sum;
    }

    static class SegmentTree {
        Node[] interval;

        public SegmentTree(int[] arrayOfValues) {
            interval = new Node[arrayOfValues.length * 4];
            buildTree(arrayOfValues, 0, arrayOfValues.length, 0);
        }

        void updateValues(int v) {
            interval[v].sum = interval[v * 2 + 1].sum + interval[v * 2 + 2].sum;
        }

        void buildTree(int[] arrayNumbs, int left, int right, int v) {
            interval[v] = new Node();
            interval[v].left = left;
            interval[v].right = right;

            if (right - left == 1) {
                interval[v].sum = arrayNumbs[left];
            } else {
                int middle = (right + left) / 2;
                buildTree(arrayNumbs, left, middle, v * 2 + 1);
                buildTree(arrayNumbs, middle, right, v * 2 + 2);
                updateValues(v);
            }
        }

        long getSum(int left, int right) {
            return findSum(0, left, right);
        }

        long findSum(int v, int left, int right) {
            if (interval[v].left >= left && interval[v].right <= right) {
                return interval[v].sum;
            } else if (interval[v].left >= right || interval[v].right <= left) {
                return 0;
            } else {
                long leftV = findSum(v * 2 + 1, left, right);
                long rightV = findSum(v * 2 + 2, left, right);
                return leftV + rightV;
            }
        }

        void addElement(int index, int value) {
            findPlaceForAdd(0, index, value);
        }

        void findPlaceForAdd(int v, int index, int value) {
            if (interval[v].right - interval[v].left == 1) {
                interval[v].sum = value;
            } else {
                if (interval[v * 2 + 2].left > index) {
                    findPlaceForAdd(v * 2 + 1, index, value);
                } else {
                    findPlaceForAdd(v * 2 + 2, index, value);
                }
                updateValues(v);
            }
        }
    }


    public static void main(String... args) throws IOException {
        StringBuilder result = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] firsLine = reader.readLine().split(" ");
        int n = Integer.parseInt(firsLine[0]);
        int m = Integer.parseInt(firsLine[1]);
        String[] stringMassive = reader.readLine().split(" ");
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            int value = Integer.parseInt(stringMassive[i]);
            array[i] = value;
        }
        SegmentTree segmentTree = new SegmentTree(array);
        for (int i = 0; i < m; i++) {
            String[] operation = reader.readLine().split(" ");
            int typeOperation = Integer.parseInt(operation[0]);
            int first = Integer.parseInt(operation[1]);
            int second = Integer.parseInt(operation[2]);
            switch (typeOperation) {
                case 1:
                    segmentTree.addElement(first, second);
                    break;

                case 2:
                    result.append(segmentTree.getSum(first, second)).append("\n");
                    break;
            }
        }
        System.out.println(result);
    }
}