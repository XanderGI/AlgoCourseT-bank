//package Tink.less7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class TaskG7StackAndDP {
    static StringBuilder result = new StringBuilder();
    static int[][] dp;
    static int[][] parent;
    static String brackets;

    static boolean isCorrectPSP(char left, char right) {
        return ((left == '(' && right == ')') || (left == '[' && right == ']') || (left == '{' && right == '}'));
    }

    static void calcDP(char[] array) {
        int length = array.length;
        parent = new int[length][length];
        dp = new int[length][length];

        for (int i = 0; i < length; i++) {
            dp[i][i] = 1;
        }

        for (int len = 2; len <= length; len++) {
            for (int i = 0; i <= length - len; i++) {
                int j = i + len - 1;
                if (isCorrectPSP(array[i], array[j])) {
                    dp[i][j] = dp[i + 1][j - 1];
                    parent[i][j] = -1;
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                    if (dp[i][j] == dp[i + 1][j] + 1) {
                        parent[i][j] = i;
                    } else if (dp[i][j] == dp[i][j - 1] + 1) {
                        parent[i][j] = j - 1;
                    }
                }
                for (int k = i; k < j; k++) {
                    if (dp[i][j] > dp[i][k] + dp[k + 1][j]) {
                        parent[i][j] = k;
                        dp[i][j] = dp[i][k] + dp[k + 1][j];
                    }
                }
            }
        }
    }

    static void buildValidPSP(char[] arrayOfBrackets, int i, int j) {
        if (dp[i][j] == j - i + 1) {
            return;
        } else if (dp[i][j] == 0) {
            result.append(brackets, i, j+1);
        } else if (parent[i][j] == -1) {
            result.append(arrayOfBrackets[i]);
            buildValidPSP(arrayOfBrackets, i + 1, j - 1);
            result.append(arrayOfBrackets[j]);
        } else { 
            int k = parent[i][j];
            buildValidPSP(arrayOfBrackets, i, k);
            buildValidPSP(arrayOfBrackets, k + 1, j);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        brackets = reader.readLine();
        char[] arrayOfBrackets = brackets.toCharArray();
        calcDP(arrayOfBrackets);
        buildValidPSP(arrayOfBrackets, 0, arrayOfBrackets.length - 1);
        System.out.println(result);
    }
}