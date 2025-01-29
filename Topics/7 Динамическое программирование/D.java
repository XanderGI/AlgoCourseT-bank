import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TaskD7KnightsMove {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int m;


    static int lazyRecursionDP(int[][] dp, int i, int j) {
        if (i < 0 || i >= n || j < 0 || j >= m) {
            return 0;
        }
        if (dp[i][j] == -1) { // если клетка ещё не была посещена(избегаем циклического поведения)
            int firstMove = lazyRecursionDP(dp, i + 1, j + 2);
            int secondMove = lazyRecursionDP(dp, i + 2, j + 1);
            int thirdMove = lazyRecursionDP(dp, i - 1, j + 2);
            int fourthMove = lazyRecursionDP(dp, i + 2, j - 1);
            dp[i][j] = firstMove + secondMove + thirdMove + fourthMove;
        }
        return dp[i][j];
    }

    public static void main(String[] args) throws IOException {
        String[] values = reader.readLine().split(" ");
        n = Integer.parseInt(values[0]);
        m = Integer.parseInt(values[1]);
        int[][] dp = new int[n][m];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        dp[n - 1][m - 1] = 1;
        lazyRecursionDP(dp, 0, 0);
        System.out.println(dp[0][0]);
    }
}