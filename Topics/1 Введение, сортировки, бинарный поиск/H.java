import java.util.Scanner;

public class TaskH1CounterSort {
    static Scanner sc = new Scanner(System.in);

    static int[] countElements(char[] input) {
        int[] c = new int[26];
        for (int i : input) {
            c[i - 'A'] += 1;
        }
        return c;
    }


    static void palindrome(int[] freqOfChar) {
        StringBuilder leftHalf = new StringBuilder();
        StringBuilder rightHalf = new StringBuilder();
        String center = "";

        for (int i = 0; i < freqOfChar.length; i++) {
            if (freqOfChar[i] % 2 == 1) {
                center = String.valueOf((char) ('A' + i));
                freqOfChar[i]--;
                break;
            }
        }

        for (int i = 0; i < freqOfChar.length; i++) {
            while (freqOfChar[i] > 1) {
                leftHalf.append((char) ('A' + i));
                rightHalf.insert(0, (char) ('A' + i));
                freqOfChar[i] -= 2;
            }
        }

        System.out.println(leftHalf + center + rightHalf);
    }

    public static void main(String[] args) {
        sc.nextLine();
        String string = sc.nextLine();
        char[] massOfChar = string.toCharArray();
        int[] freq = countElements(massOfChar);
        palindrome(freq);
    }

}