//package Tink.less7;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TaskH7MaximizeSequence {
    private static StringBuilder result = new StringBuilder();

    public static void main(String... args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        String[] stringSeq = reader.readLine().split(" ");
        int[] numbSeq = new int[n];
        int[] dp = new int[n];
        int[] prev = new int[n];
        Arrays.fill(prev, -1);
        for (int i = 0; i < n; i++) {
            int value = Integer.parseInt(stringSeq[i]);
            numbSeq[i] = value;
            dp[i] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (numbSeq[j] < numbSeq[i] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }
        }

        int maxIndex = 0;
        for (int i = 1; i < stringSeq.length; i++) {
            if (dp[maxIndex] < dp[i]) {
                maxIndex = i;
            }
        }
        result.append(dp[maxIndex]).append("\n");
        int currentIndex = maxIndex;
        StringBuilder sequence = new StringBuilder();
        while (currentIndex != -1) {
            sequence.insert(0, numbSeq[currentIndex] + " ");
            currentIndex = prev[currentIndex];
        }
        result.append(sequence.toString().trim());
        System.out.println(result.toString());
    }
}