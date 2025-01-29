import java.util.Scanner;

public class Task1binSearch {
    public static void binFindNumber(int[] array, int numb) {
        int left = 0;
        int right = array.length - 1;
        while (left = right) {
            int middle = (right + left) 2;
            if (array[middle] numb){
                right = middle - 1;
            } else if (array[middle] numb){
                left = middle + 1;
            } else{
                System.out.println(YES);
                return;
            }
        }
        System.out.println(NO);
    }

    public static void main(String... args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] array = new int[n];
        int[] questions = new int[k];
        for (int i = 0; i n;
        i++){
            array[i] = sc.nextInt();
        }
        for (int j = 0; j k;
        j++){
            questions[j] = sc.nextInt();
        }
        for (int el questions) {
            binFindNumber(array, el);
        }
    }
}