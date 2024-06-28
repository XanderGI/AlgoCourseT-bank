//package Tink.less7DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class TaskF7MaxSquare {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    static String findSolution(int[][] array) {
        StringBuilder solution = new StringBuilder();
        int lengthX = array.length;
        int lengthY = array[0].length;
        if (lengthX == 1 && lengthY == 1) {
            solution.append(1).append("\n").append("1 1");
            return solution.toString();
        }
        int x = 0;
        int y = 0;
        int maxSide = 0;
        int[][] dp = new int[lengthX][lengthY];
        for (int i = 0; i < lengthX; i++) {
            dp[i][0] = array[i][0];
        }
        for (int j = 0; j < lengthY; j++) {
            dp[0][j] = array[0][j];
        }
        for (int i = 1; i < lengthX; i++) {
            for (int j = 1; j < lengthY; j++) {
                if (array[i][j] > 0) {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
                if (maxSide <= dp[i][j]) {
                    maxSide = dp[i][j];
                    // возращаем именно координаты верхнего левого угла квадрата, а не нижнего правого угла квадарта. +2 тк нумерация с 1
                    x = i - maxSide + 2;
                    y = j - maxSide + 2;
                }
            }
        }
        solution.append(maxSide).append("\n").append(x + " " + y);
        return solution.toString();
    }

    public static void main(String[] args) throws IOException {
        String[] firstLine = reader.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int m = Integer.parseInt(firstLine[1]);
        String[] matrix;
        int[][] array = new int[n][m];
        for (int i = 0; i < n; i++) {
            matrix = reader.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                int value = Integer.parseInt(matrix[j]);
                array[i][j] = value;
            }
        }
        System.out.println(findSolution(array));
    }
}