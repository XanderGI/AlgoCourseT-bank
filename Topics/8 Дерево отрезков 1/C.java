//package Tink.less8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaskC8FindUnits {
    static class Node {
        int left;
        int right;
        int count;
    }

    static class SegmentTree {
        Node[] interval;
        public SegmentTree(int[] arrayOfValues) {
            interval = new Node[arrayOfValues.length * 4];
            buildTree(arrayOfValues,0,arrayOfValues.length,0);
        }

        void updateValues(int v) {
            interval[v].count = interval[v*2+1].count + interval[v*2+2].count;
        }

        void buildTree(int[] arrayNumbs, int left, int right, int v) {
            interval[v] = new Node();
            interval[v].left = left;
            interval[v].right = right;

            if (right - left == 1) {
                interval[v].count = arrayNumbs[left];
            } else {
                int middle = (right+left)/2;
                buildTree(arrayNumbs,left,middle,v*2+1);
                buildTree(arrayNumbs,middle,right,v*2+2);
                updateValues(v);
            }
        }

        int getIndex(int k) {
            return findIndex(0,k+1);
        }

        int findIndex(int v, int k) {
            if (interval[v].right - interval[v].left == 1) {
                return interval[v].left;
            } else {
                if (interval[v*2+1].count >= k) {
                    return findIndex(2*v+1,k);
                } else {
                    return findIndex(v*2+2,k-interval[v*2+1].count);
                }
            }
        }

        void addElement(int index) {
            findPlaceForAdd(0,index);
        }

        void findPlaceForAdd(int v, int index) {
            if (interval[v].right - interval[v].left == 1) {
                interval[v].count = 1 - interval[v].count;; // если 1 => 0 иначе 1.
            } else {
                int middle = (interval[v].left + interval[v].right) / 2;
                if (index < middle) {
                    findPlaceForAdd(v*2+1, index);
                } else {
                    findPlaceForAdd(v*2+2, index);
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
            int index = Integer.parseInt(operation[1]);
            switch (typeOperation) {
                case 1:
                    segmentTree.addElement(index);
                    break;

                case 2:
                    result.append(segmentTree.getIndex(index)).append("\n");
                    break;
            }
        }
        System.out.println(result);
    }
}