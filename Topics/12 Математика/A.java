//package Tink.less12;

import java.util.Scanner;

public class TaskA12LCAwithGCD {
    static long gcd(long a, long b) {
        while (b != 0) {
            long c = a % b;
            a = b;
            b = c;
        }
        return a;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long N = sc.nextLong();
        long K = sc.nextLong();
        System.out.println((N*K)/gcd(N,K));
    }
}