//package Tink.less9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaskA9LazyPropagationWithSum {
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
        int left;
        int right;
        long min;
        long promise;
    }

    static class SegmentTree {
        static Node edges[];

        public SegmentTree(long[] array) {
            edges = new Node[array.length * 4];
            buildTree(array, 0, array.length, 0);
        }

        void changeValuesOnInterval(int reqLeft, int reqRight, int value) {
            modifyValues(reqLeft, reqRight, value, 0);
        }

        static void lazyPropagation(int node) {
            if (edges[node].promise != 0) {
                if (edges[node].right - edges[node].left > 1) {
                    int left = node * 2 + 1;
                    int right = node * 2 + 2;
                    edges[left].promise += edges[node].promise;
                    edges[left].min += edges[node].promise;
                    edges[right].promise += edges[node].promise;
                    edges[right].min += edges[node].promise;
                }
                edges[node].promise = 0;
            }
        }

        static void modifyValues(int reqLeft, int reqRight, int value, int node) {
            if (reqLeft >= edges[node].right || reqRight <= edges[node].left) {
                return;
            } else if (edges[node].left >= reqLeft && edges[node].right <= reqRight) {
                edges[node].min += value;
                edges[node].promise += value;
            } else {
                lazyPropagation(node);
                modifyValues(reqLeft, reqRight, value, node * 2 + 1);
                modifyValues(reqLeft, reqRight, value, node * 2 + 2);
                edges[node].min = Math.min(edges[node * 2 + 1].min, edges[node * 2 + 2].min);
            }
        }

        long getMin(int left, int right) {
            return findMinValue(left,right,0);
        }

        static long findMinValue(int left, int right, int node) {
            if (edges[node].left >= right || edges[node].right <= left) {
                return Long.MAX_VALUE;
            } else if (edges[node].left >= left && edges[node].right <= right) {
                return edges[node].min;
            } else {
                lazyPropagation(node);
                long minLeft = findMinValue(left,right,node*2+1);
                long minRight = findMinValue(left,right,node*2+2);
                return Math.min(minLeft,minRight);
            }
        }

        static void buildTree(long[] array, int left, int right, int v) {
            edges[v] = new Node();
            edges[v].left = left;
            edges[v].right = right;

            if (right - left == 1) {
                edges[v].min = array[left];
            } else {
                int middle = (right + left) / 2;
                buildTree(array, left, middle, v * 2 + 1);
                buildTree(array, middle, right, v * 2 + 2);
                edges[v].min = Math.min(edges[v * 2 + 1].min, edges[v * 2 + 2].min);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        readInputData();
        SegmentTree segmentTree = new SegmentTree(array);
        for (int i = 0; i < m; i++) {
            String[] request = reader.readLine().split(" ");
            int operation = Integer.parseInt(request[0]);
            int left = Integer.parseInt(request[1]);
            int right = Integer.parseInt(request[2]);
            switch (operation) {
                case 1:
                    int value = Integer.parseInt(request[3]);
                    segmentTree.changeValuesOnInterval(left,right,value);
                    break;
                case 2:
                    result.append(segmentTree.getMin(left,right)).append("\n");
                    break;
            }
        }
        System.out.println(result);
    }