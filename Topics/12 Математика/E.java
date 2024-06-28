//package Tink.less12;

import java.util.Scanner;

public class TaskE12FindBinomialCoefficient {
    private static final long MOD = (long) 1e9 + 7;

    static long findFactorial(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result = (result*i)%MOD;
        }
        return result;
    }

    public static long modPow(long base, long exponent, long mod) {
        base %= mod;
        long result = 1;
        while (exponent > 0) {
            if (exponent % 2 == 1) {
                result = (result * base) % mod;
            }
            base = (base * base) % mod;
            exponent /= 2;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        long denominator = (findFactorial(k) * findFactorial(n-k))%MOD;
        System.out.print((findFactorial(n) * modPow(denominator,MOD-2,MOD))%MOD);
    }
}