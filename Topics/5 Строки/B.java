//package Tink.less5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class TaskB5KMP {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder result = new StringBuilder();
    public static int[] prefixFunc(String substring) {
        int[] prefMassive = new int[substring.length()];
        for (int i = 1, j = 0; i < substring.length(); i++) {
            while (j > 0 && substring.charAt(j) != substring.charAt(i)) {
                j = prefMassive[j - 1];
            }
            if (substring.charAt(j) == substring.charAt(i)) {
                j++;
            }
            prefMassive[i] = j;
        }
        return prefMassive;
    }

    public static void KMP(String text, String sub) {
        if (sub.length() > text.length()) {
            result.append("0\n");
            return;
        }
        int[] prefix = prefixFunc(sub);
        int counter = 0;
        StringBuilder tempResult = new StringBuilder();
        for (int i = 0, j = 0; i < text.length(); i++) {
            while (j > 0 && sub.charAt(j) != text.charAt(i)) {
                j = prefix[j - 1];
            }
            if (sub.charAt(j) == text.charAt(i)) {
                j++;
            }
            if (j == sub.length()) {
                counter++;
                tempResult.append((i + 1) - j).append(" ");
                j = prefix[j - 1];
            }
        }
        result.append(tempResult.isEmpty() ? counter : counter + " ").append(tempResult.toString().trim()).append("\n");
    }

    public static void main(String[] args) throws IOException{
        String text = reader.readLine();
        int countOfRequests = Integer.parseInt(reader.readLine());
        for (int i = 0; i < countOfRequests; i++) {
            String subString = reader.readLine();
            KMP(text,subString);
        }
        System.out.print(result);
    }
}