import java.util.Scanner;

public class Task4binSearch {
    static Scanner sc = new Scanner(System.in);

    private static double expr(double x) {
        return x * x + Math.sqrt(x + 1);
    }

    public static double doubleBinFindNumber(double c) {
        double eps = 1e-6;
        double left = 0;
        double right = 1e5;
        double middle = (right + left) / 2;
        double prevMiddle = -1;
        while (Math.abs(expr(middle) - c) > eps) {
            if (prevMiddle == middle) {
                return Math.round(middle);
            }

            double funcValue = expr(middle);
            if (funcValue > c) {
                right = middle;
            } else if (funcValue < c) {
                left = middle;
            } else {
                return middle;
            }
            prevMiddle = middle;
            middle = (right + left) / 2;
        }
        return middle;
    }


    public static void main(String... args) {
        double c = sc.nextDouble();
        System.out.println(doubleBinFindNumber(c));
    }
}