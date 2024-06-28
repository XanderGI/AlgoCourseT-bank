//package Tink.less1;

import java.util.Scanner;

public class Task5binSearch {
    static Scanner sc = new Scanner(System.in);
    static int a,b,c,d;

    private static double expr(double x) {
        return a*Math.pow(x,3) + b*Math.pow(x,2) + c*x + d;
    }

    public static double doubleBinFindNumber() {
        double eps = 1e-4;
        double left = -1e+5;
        double right = 1e+5;
        double middle = (right+left)/2;
        while (right - left > eps) {
            double funcValue = expr(middle);
            if (funcValue*expr(right) > 0) {
                right = middle;
            } else {
                left  = middle;
            }
            middle = (right+left)/2;
        }
        return middle;
    }

    public static void main(String... args) {
        a = sc.nextInt();
        b = sc.nextInt();
        c = sc.nextInt();
        d = sc.nextInt();
        System.out.println(doubleBinFindNumber());
    }
}