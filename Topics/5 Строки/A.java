import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class TaskA5PolynomialHashCodeSubstring {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder result = new StringBuilder();
    static int coefficient = 37;
    static int mod = (int) 1e9 + 7;

    static class Pair {
        long[] prefixHash;
        long[] correctMultiply;

        public Pair(long[] prefixHash, long[] correctMultiply) {
            this.prefixHash = prefixHash;
            this.correctMultiply = correctMultiply;
        }
    }

    static Pair calcPrefixHash(String string) {
        long[] prefixHash = new long[string.length() + 1];
        prefixHash[0] = 0;
        for (int i = 1; i <= string.length(); i++) {
            prefixHash[i] = (prefixHash[i - 1] * coefficient + (string.charAt(i - 1) - 'a' + 1)) % mod;
        }
        long[] multiply = new long[string.length() + 1];
        multiply[0] = 1;
        multiply[1] = coefficient;
        for (int j = 2; j <= string.length(); j++) {
            multiply[j] = (multiply[j - 1] * multiply[1]) % mod;
        }

        return new Pair(prefixHash, multiply);
    }

    static long calcHashOfSubstring(long[] prefixHash, long[] multiplyCoefficient, int left, int right) {
        return ((long) mod * mod + prefixHash[right] - prefixHash[left - 1] * multiplyCoefficient[right - left + 1]) % mod;
    }

    public static void main(String[] args) throws IOException {
        String text = reader.readLine();
        Pair pairOfValues = calcPrefixHash(text);
        int requests = Integer.parseInt(reader.readLine());
        for (int i = 0; i < requests; i++) {
            String[] request = reader.readLine().split(" ");
            int leftFirst = Integer.parseInt(request[0]);
            int rightFirst = Integer.parseInt(request[1]);
            int leftSecond = Integer.parseInt(request[2]);
            int rightSecond = Integer.parseInt(request[3]);
            long firstHash = calcHashOfSubstring(pairOfValues.prefixHash, pairOfValues.correctMultiply, leftFirst, rightFirst);
            long secondHash = calcHashOfSubstring(pairOfValues.prefixHash, pairOfValues.correctMultiply, leftSecond, rightSecond);
            result.append(firstHash == secondHash ? "Yes" : "No").append("\n");
        }
        System.out.println(result);
    }
}