//package Tink.less1;

import java.util.Scanner;

public class Task3binSearch {
    static Scanner sc = new Scanner(System.in);

    public static void SecretNumber(int n) {
        int left = 1;
        int right = n;
        String sign = "";
        int middle;
        while (left < right) {
            middle = (left+right + 1)/2;
            System.out.println(middle);
            sign = sc.next();
            if (sign.equals("<")) {
                right = middle - 1;
            } else if (sign.equals(">=")) {
                left = middle;
            }
        }
        System.out.println("! " + left);
    }

    public static void main(String... args) {

        int n = sc.nextInt();
        SecretNumber(n);
    }
}