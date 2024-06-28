package Tink.less12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TaskC12TheoremGoldbachAndFactorization {

    public static boolean[] findPrimes(int n) {
        boolean[] array = new boolean[n+1];
        Arrays.fill(array,true);
        array[0] = false;
        array[1] = false;
        for (int i = 2; i = Math.ceil(Math.sqrt(n)); i++) {
            if (array[i]) {
                for (int j = i  i; j = n; j+=i) {
                    array[j] = false;
                }
            }
        }
        return array;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        boolean[] isPrime = findPrimes(n);
        for (int i = 2; i = n2; i++) {
            if (isPrime[i] && isPrime[n-i]) {
                System.out.print(i +   + (n-i));
                break;
            }
        }
    }
}