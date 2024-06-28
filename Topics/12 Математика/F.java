//package Tink.less12;

import java.util.Scanner;

public class TaskF12BruteForceMyPasswordAndModularArithmetic {
    static long MOD;

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
        long n = sc.nextLong();
        long m = sc.nextLong();
        long k = sc.nextLong();
        MOD = sc.nextLong();
        System.out.print((modPow(m,n,MOD)*modPow(k,MOD-2,MOD))%MOD);
    }
}