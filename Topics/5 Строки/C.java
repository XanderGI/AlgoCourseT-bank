//package Tink.less5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class TaskC5CycleShift {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static int counter;
    static final int coefficient = 37;
    static final int mod = (int) 1e9+7;

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
            multiply[j] = (multiply[j-1] * multiply[1]) % mod;
        }

        return new Pair(prefixHash,multiply);
    }

    static long calcHashOfSubstring(long[] prefixHash, long[] multiplyCoefficient, int left, int right) {
        return ((long) mod * mod + prefixHash[right] - prefixHash[left-1] * multiplyCoefficient[right-left+1])%mod;
    }

    public static void main(String[] args) throws IOException {
        String a = reader.readLine();
        String b = reader.readLine();
        int lengthB = b.length();
        String bb = b+b;
        Pair pairOfValuesB = calcPrefixHash(bb);
        HashSet<Long> hashSet = new HashSet<>();
        for (int j = 0; j <= bb.length() - lengthB; j++) {
            hashSet.add(calcHashOfSubstring(pairOfValuesB.prefixHash, pairOfValuesB.correctMultiply,j+1,j+lengthB));
        }
        Pair pairOfValuesA = calcPrefixHash(a);
        for (int i = 0; i <= a.length() - lengthB; i++) {
            Long hash = calcHashOfSubstring(pairOfValuesA.prefixHash, pairOfValuesA.correctMultiply,i+1,i+lengthB);
            if (hashSet.contains(hash)) {
                counter++;
            }
        }
        System.out.println(counter);
    }
}