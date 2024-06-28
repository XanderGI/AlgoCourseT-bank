//package Tink.less7DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaskE7LevensteinsDamerauDP {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String first = reader.readLine();
        String second = reader.readLine();
        char[] arrayCharsFirst = first.toCharArray();
        char[] arrayCharsSecond = second.toCharArray();
        int[][] dp = new int[first.length()+1][second.length()+1];
        for (int i = 0; i < first.length()+1; i++) {
            for (int j = 0; j < second.length()+1; j++ ) {
                if (Math.min(i,j) == 0) { // если идет сравнение "" и какой-то строки, и наоборот
                    dp[i][j] = Math.max(i,j); // max так как это и будет минимально возможное расстояние.
                } else {
                    dp[i][j] = Math.min(dp[i-1][j] + 1, dp[i][j-1] +1); // при удалинии, и вставке
                    dp[i][j] = Math.min(dp[i][j],dp[i-1][j-1] + ((arrayCharsFirst[i-1] == arrayCharsSecond[j-1]) ? 0 : 1)); // при перестановке
                    if (i > 1 && j > 1 && arrayCharsFirst[i-1] == arrayCharsSecond[j-2] && arrayCharsSecond[j-1] == arrayCharsFirst[i-2]) { // при транспозиции
                        dp[i][j] = Math.min(dp[i][j],dp[i-2][j-2] + 1);
                    }
                }
            }
        }
        System.out.println(dp[first.length()][second.length()]);
    }
}