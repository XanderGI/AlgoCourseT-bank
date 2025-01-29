import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaskB7ExplosionTwoDimensionalDP {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(reader.readLine());
        int[][] dp = new int[n][3]; // n состояний и 3 вариации контейнеров
        /*БАЗА!*/
        dp[0][0] = 1; // для C
        dp[0][1] = 1; // Для B
        dp[0][2] = 1; // Для А
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]; // пересчет состояний для C
            dp[i][1] = dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]; // пересчет состояний для B
            dp[i][2] = dp[i - 1][0] + dp[i - 1][1]; // пересчет состояний для A
        }
        System.out.println(dp[n - 1][0] + dp[n - 1][1] + dp[n - 1][2]);
    }
}