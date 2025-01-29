import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaskD12FindLastNumberInFactorial {
    static int result = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        findLastNonZeroNumber(N, 0);
        System.out.println(result);
    }

    static void findLastNonZeroNumber(int n, int countOfFives) {
        int number = n;
        if (number == 1) {
            return;
        }
        while (number % 5 == 0) {
            countOfFives++;
            number /= 5;
        }
        while (countOfFives != 0 && number % 2 == 0) {
            number /= 2;
            countOfFives--;
        }
        result = (result * (number % 10)) % 10;
        findLastNonZeroNumber(n - 1, countOfFives);
    }
}