import java.util.Scanner;

public class Task2binSearch {

    public static int binaryFindMuchSuitableNumber(int[] array, int numb) {
        int left = 0;
        int right = array.length-1;
        int minAbs = Math.abs(array[(right+left)/2] - numb);
        int minValue = array[right];
        while (left <= right) {
            int middle = (right+left)/2;
            if (array[middle] > numb) {
                right = middle - 1;
            } else if (array[middle] < numb){
                left = middle + 1;

            } else {
                return numb;
            }
            int tempAbs = Math.abs(numb-array[middle]);
            if (tempAbs < minAbs || (tempAbs <= minAbs && minValue > array[middle])) {
                minAbs = tempAbs;
                minValue = array[middle];
            }
        }
        return minValue;
    }

    public static void main(String... args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] array = new int[n];
        int[] questions = new int[k];
        for (int i =0; i < n; i++) {
            array[i] = sc.nextInt();
        }
        for (int j = 0; j < k; j++) {
            questions[j] = sc.nextInt();
        }
        for (int el : questions) {
            System.out.println(binaryFindMuchSuitableNumber(array,el));
        }
    }
}