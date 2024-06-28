//package Tink.less4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TaskE4PrefixSumAndBinarySearchByAns {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static int[] array;

    static boolean isAnswer(long m, int k, int[] array) {
        long s = 0;
        int count = 1;
        for (int num : array) {
            if (num > m) {
                return false;
            }
            if (s + num > m) {
                count++;
                s = num;
            } else {
                s += num;
            }
        }
        return count <= k;
    }

    public static void main(String[] args) throws IOException {
        String[] nk = reader.readLine().split(" ");
        int k = Integer.parseInt(nk[1]);
        array = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        long sumOfAll = Arrays.stream(array).asLongStream().sum();
        long left = 1;
        long right = sumOfAll;

        while (left < right) {
            long middle = left+(right-left)/2;
            if (isAnswer(middle, k, array)) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        System.out.println(left);
    }
}