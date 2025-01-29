import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaskB4findMatrixSum {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder result = new StringBuilder();

    static long calcRequest(long[][] prefixSum, int y1, int x1, int y2, int x2) {
        return prefixSum[y2][x2] - prefixSum[y1 - 1][x2] - prefixSum[y2][x1 - 1] + prefixSum[y1 - 1][x1 - 1];
    }

    public static void main(String... args) throws IOException {
        String[] firstLine = reader.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int m = Integer.parseInt(firstLine[1]);
        int k = Integer.parseInt(firstLine[2]);
        long[][] prefixSum = new long[n + 1][m + 1];
        for (int i = 0; i < n; i++) {
            String[] row = reader.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                int value = Integer.parseInt(row[j]);
                prefixSum[i + 1][j + 1] = prefixSum[i][j + 1] + prefixSum[i + 1][j] - prefixSum[i][j] + value;
            }
        }
        for (int i = 0; i < k; i++) {
            String[] coords = reader.readLine().split(" ");
            int y1 = Integer.parseInt(coords[0]);
            int x1 = Integer.parseInt(coords[1]);
            int y2 = Integer.parseInt(coords[2]);
            int x2 = Integer.parseInt(coords[3]);
            result.append(calcRequest(prefixSum, y1, x1, y2, x2)).append("\n");
        }
        System.out.println(result);
    }
}