import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaskF5algManacher {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static long countManacherPalindromes(String s) {
        int n = s.length();
        int[] arrayOfRad = new int[n];
        int center = 0;
        int radius = 0;
        long count = 0;
        for (int i = 0; i < n; i++) {
            int mirror = 2 * center - i;
            if (i < radius) {
                arrayOfRad[i] = Math.min(radius - i, arrayOfRad[mirror]);
            }
            while (i + arrayOfRad[i] + 1 < n && i - arrayOfRad[i] - 1 >= 0 && s.charAt(i + arrayOfRad[i] + 1) == s.charAt(i - arrayOfRad[i] - 1)) {
                arrayOfRad[i]++;
            }
            if (i + arrayOfRad[i] > radius) {
                center = i;
                radius = i + arrayOfRad[i];
            }
            count += (arrayOfRad[i] + 1) / 2;
        }
        return count;
    }


    public static void main(String[] args) throws IOException {
        String text = reader.readLine();
        String modifyText = " " + String.join(" ", text.split("")) + " ";
        long counter = countManacherPalindromes(modifyText);
        System.out.println(counter);
    }
}