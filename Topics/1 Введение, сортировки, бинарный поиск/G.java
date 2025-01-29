import java.util.Scanner;
import java.util.StringJoiner;

public class TaskG1doubleBinSearch {
    static Scanner sc = new Scanner(System.in);

    static void veryBadSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            array[i] = array[i / 2];
            array[i / 2] = temp;
        }
    }

    public static void main(String[] args) {
        int n = sc.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = i + 1;
        }
        veryBadSort(array);
        StringJoiner sj = new StringJoiner(" ");
        for (int j = 0; j < n; j++) {
            sj.add("" + array[j]);
        }
        System.out.println(sj);
    }
}