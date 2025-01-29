import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaskE5BinSearchByHash {
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
        String p = reader.readLine();
        int lengthP = p.length();
        String t = reader.readLine();
        Pair PairForP = calcPrefixHash(p);
        Pair PairForT = calcPrefixHash(t);
        int countOfMatchers = 0;
        for (int i = 0; i <= t.length() - lengthP; i++) {
            int left = 0;
            int right = lengthP - 1;
            while (left < right) {
                int middle = (right + left) / 2;
                if (calcHashOfSubstring(PairForP.prefixHash, PairForP.correctMultiply, 1, middle + 1) == calcHashOfSubstring(PairForT.prefixHash, PairForT.correctMultiply, i + 1, i + 1 + middle)) {
                    left = middle + 1;
                } else {
                    right = middle;
                }
            }
            int matchLength = left;
            if (matchLength == lengthP - 1 || matchLength == lengthP) {
                countOfMatchers++;
                result.append(i + 1 + " ");
            } else if (matchLength < lengthP - 1 && calcHashOfSubstring(PairForP.prefixHash, PairForP.correctMultiply, matchLength + 2, lengthP) == calcHashOfSubstring(PairForT.prefixHash, PairForT.correctMultiply, i + matchLength + 2, i + lengthP)) {
                countOfMatchers++;
                result.append(i + 1 + " ");
            }
        }
        result.insert(0, countOfMatchers + "\n");
        System.out.println(result.toString().trim());
    }
}