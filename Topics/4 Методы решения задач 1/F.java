//package Tink.less4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TaskF4ScanLine {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static int lengthResult = 0;

    static class Pair implements Comparable {
        int value;
        char sign;

        public Pair(int value, char sign) {
            this.value = value;
            this.sign = sign;
        }

        @Override
        public int compareTo(Object o) {
            Pair p = (Pair) o;
            return Integer.compare(this.value,p.value);
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "value=" + value +
                    ", sign=" + sign +
                    '}';
        }
    }

    static void calcLengthOfNumericLine(Pair[] arrayOfPair) {
        int counter = 1;
        Integer left = arrayOfPair[0].value;
        for (int i = 1; i < arrayOfPair.length; i++) {
            if (arrayOfPair[i].sign == '-') {
                counter--;
                if (counter == 0) {
                    lengthResult += arrayOfPair[i].value - left;
                    left = null;
                }
            } else {
                if (left == null) {
                    left = arrayOfPair[i].value;
                }
                counter++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(reader.readLine());
        Pair[] arrayOfPair = new Pair[n*2];
        for (int i = 0; i < n; i++) {
            String[] segment = reader.readLine().split(" ");
            int valueLeft = Integer.parseInt(segment[0]);
            int valueRight = Integer.parseInt(segment[1]);
            arrayOfPair[2*i] = new Pair(valueLeft,'+');
            arrayOfPair[2*i+1] = new Pair(valueRight,'-');
        }
        Arrays.sort(arrayOfPair);
        calcLengthOfNumericLine(arrayOfPair);
        System.out.println(lengthResult);
    }
}