import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaskB12Factorization {
    public static void main(String[] args) throws IOException {
        StringBuilder result = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        for (int i = 2; i <= Math.ceil(Math.sqrt(N)); i++) {
            int count = 0;
            while (N % i == 0) {
                count++;
                N /= i;
            }
            if (count > 0) {
                result.append(i).append(count > 1 ? "^" + count : "").append("*");
            }
        }
        if (N > 1) {
            result.append(N);
        } else {
            result.setLength(result.length() - 1);
        }
        System.out.print(result);
    }
}