import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaskD4MultiplyTableAndBinarySearchByAns {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    static boolean isCorrectValueForAns(long middle, int n, long k) {
        long count = 0;
        for (int i = 1; i <= n; i++) {
            count += Math.min(middle / i, n);
            if (count >= k) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        String[] firstLine = reader.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        long k = Long.parseLong(firstLine[1]);
        long left = 1;
        long right = (long) n * n;
        while (right - left > 1) {
            long middle = (right + left) / 2;
            if (isCorrectValueForAns(middle, n, k)) {
                right = middle;
            } else {
                left = middle;
            }
        }
        System.out.println(isCorrectValueForAns(left, n, k) ? left : right);
    }
}