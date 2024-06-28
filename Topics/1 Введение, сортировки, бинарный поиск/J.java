//package Tink.less1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class TaskJ1prefSumAndBinSearch  {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder result = new StringBuilder();
    static String[] data;
    static int n;
    static int m;

    static class SplitResult {
        public final int position;
        public final long difference;

        public SplitResult(int position, long difference) {
            this.position = position;
            this.difference = difference;
        }
    }

    static long upPart(int mid) {
        long midM = (long)mid * m;
        return midM * (midM + 1) / 2;
    }

    static long leftPart(int mid) {
        long part1 = (long)n * (1 + mid) * mid / 2;
        long part2 = (long)n * (n - 1) * m * mid / 2;
        return part1 + part2;
    }

    static SplitResult findVerticalSplit(long allSum) {
        int left = 0;
        int right = m - 1;
        while (right - left > 1) {
            int middle = left + (right - left) / 2;
            long leftPartSum = leftPart(middle);
            if (leftPartSum * 2 <= allSum) {
                left = middle;
            } else {
                right = middle;
            }
        }
        long leftPartSum = leftPart(left);
        long rightPartSum = allSum - leftPartSum;
        long leftDistance = Math.abs(rightPartSum - leftPartSum);
        leftPartSum = leftPart(right);
        rightPartSum = allSum - leftPartSum;
        long rightDistance = Math.abs(rightPartSum - leftPartSum);
        return new SplitResult(
                leftDistance <= rightDistance ? left + 1 : right + 1,
                Math.min(leftDistance, rightDistance)
        );
    }

    static SplitResult findHorizontalSplit(long allSum) {
        int left = 0;
        int right = n - 1;
        while (right - left > 1) {
            int middle = left + (right - left) / 2;
            long upPartSum = upPart(middle);
            if (upPartSum * 2 <= allSum) {
                left = middle;
            } else {
                right = middle;
            }
        }
        long upPartSum = upPart(left);
        long downPartSum = allSum - upPartSum;
        long leftDistance = Math.abs(downPartSum - upPartSum);
        long middlePartSum = upPart(right);
        downPartSum = allSum - middlePartSum;
        long rightDistance = Math.abs(downPartSum - middlePartSum);
        return new SplitResult(
                leftDistance <= rightDistance ? left + 1 : right + 1,
                Math.min(leftDistance, rightDistance)
        );
    }

    static long calcAllSum() {
        return ((long)n * m + 1) * ((long)n * m) / 2;
    }

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        for (int i =0; i < t; i++) {
            data = br.readLine().split(" ");
            n = Integer.parseInt(data[0]);
            m = Integer.parseInt(data[1]);
            long allSum = calcAllSum();
            SplitResult hor = findHorizontalSplit(allSum);
            SplitResult vert = findVerticalSplit(allSum);
            if (hor.difference < vert.difference) {
                result.append("H " + hor.position).append("\n");
            } else {
                result.append("V " + vert.position).append("\n");
            }
        }
        System.out.println(result);
    }
}