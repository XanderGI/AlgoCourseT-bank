//package Tink.less1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaskI1BubbleSort1 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder("1 ");
        int[] positions = new int[n];
        int[] arrayOfBit = new int[n];
        String[] data = br.readLine().split(" ");

        for (int i =0; i < n; i++) {
            positions[i] = Integer.parseInt(data[i]);
        }

        int indexOFZero = n-1;
        for(int i = 1; i < n; ++i) {
            int pos = positions[i-1] - 1;
            arrayOfBit[pos] = 1;
            while (indexOFZero >= 0 && arrayOfBit[indexOFZero] == 1) {
                --indexOFZero;
            }
            result.append((indexOFZero < 0) ? i : i - ((n-2) - indexOFZero)).append(" ");
        }
        result.append("1");
        System.out.println(result);
    }
}