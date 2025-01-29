import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaskB8FindMinAndMinCount {
    static class Node {
        int left;
        int right;
        int min;
        int countOfMin;
    }

    static class Pair {
        int min;
        int count;

        public Pair(int min, int count) {
            this.min = min;
            this.count = count;
        }
    }

    static class SegmentTree {
        Node[] interval;
        public SegmentTree(int[] arrayOfValues) {
            interval = new Node[arrayOfValues.length * 4];
            buildTree(arrayOfValues,0,arrayOfValues.length,0);
        }

        void updateValues(int v) {
            int left = v*2+1;
            int right = v*2+2;
            if (interval[left].min == interval[right].min) {
                interval[v].min = interval[left].min;
                interval[v].countOfMin = interval[left].countOfMin + interval[right].countOfMin;
            } else if (interval[left].min > interval[right].min) {
                interval[v].min = interval[right].min;
                interval[v].countOfMin = interval[right].countOfMin;
            } else {
                interval[v].min = interval[left].min;
                interval[v].countOfMin = interval[left].countOfMin;
            }
        }

        void buildTree(int[] arrayNumbs, int left, int right, int v) {
            interval[v] = new Node();
            interval[v].left = left;
            interval[v].right = right;

            if (right - left == 1) {
                interval[v].min = arrayNumbs[left];
                interval[v].countOfMin = 1;
            } else {
                int middle = (right+left)/2;
                buildTree(arrayNumbs,left,middle,v*2+1);
                buildTree(arrayNumbs,middle,right,v*2+2);
                updateValues(v);
            }
        }

        Pair getMin(int left, int right) {
            return findMinAndCount(0,left,right);
        }

        Pair findMinAndCount(int v, int left, int right) {
            if (interval[v].left >= left && interval[v].right <= right) {
                return new Pair(interval[v].min,interval[v].countOfMin);
            } else if (interval[v].left >= right || interval[v].right <= left) {
                return new Pair(Integer.MAX_VALUE,-1);
            } else {
                Pair leftV = findMinAndCount(v*2+1,left,right);
                Pair rightV = findMinAndCount(v*2+2,left,right);
                if (leftV.min == rightV.min) {
                    return new Pair(leftV.min,leftV.count + rightV.count);
                } else if (leftV.min < rightV.min) {
                    return new Pair(leftV.min,leftV.count);
                } else {
                    return new Pair(rightV.min,rightV.count);
                }
            }
        }

        void addElement(int index, int value) {
            findPlaceForAdd(0,index,value);
        }

        void findPlaceForAdd(int v, int index, int value) {
            if (interval[v].right - interval[v].left == 1) {
                interval[v].min = value;
            } else {
                if (interval[v*2+2].left > index) {
                    findPlaceForAdd(v*2+1,index,value);
                } else {
                    findPlaceForAdd(v*2+2,index,value);
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
                    segmentTree.addElement(first,second);
                    break;

                case 2:
                    Pair resultOfReq = segmentTree.getMin(first,second);
                    result.append(resultOfReq.min+ " " +resultOfReq.count).append("\n");
                    break;
            }
        }
        System.out.println(result);
    }
}