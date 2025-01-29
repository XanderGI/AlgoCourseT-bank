import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaskA7oneDimensionalDP {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(reader.readLine());
        String[] line = reader.readLine().split(" ");
        int[] dp = new int[n + 1];
        dp[0] = 0; //база
        dp[1] = Integer.parseInt(line[0]);
        for (int i = 2; i < n + 1; i++) {
            int cost = Integer.parseInt(line[i - 1]);
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost; // тк мин сумма => выбираем из минимальных состояний
        }
        System.out.println(dp[n]);

    }
}