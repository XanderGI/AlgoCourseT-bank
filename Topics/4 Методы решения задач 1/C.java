import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaskC4cowsAndBinarySearchByAnswer {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    static boolean checkDistance(int[] stalls, int distance, int countOfCows) {
        int count = 1;
        int lastPosOfCow = stalls[0];
        for (int posStall : stalls) {
            if (lastPosOfCow + distance > posStall) {
                continue;
            }
            lastPosOfCow = posStall;
            count++;
        }
        return count >= countOfCows;
    }

    static int binarySearch(int[] posStalls, int cows) {
        int left = 0;
        int right = 1000000000;
        while (right - left > 1) {
            int middle = (right + left) / 2;
            if (checkDistance(posStalls, middle, cows)) {
                left = middle;
            } else {
                right = middle;
            }
        }
        return left;
    }

    public static void main(String[] args) throws IOException {
        String[] firstLine = reader.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int countOfCows = Integer.parseInt(firstLine[1]);
        int[] posStalls = new int[n];
        String[] secondLine = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            int pos = Integer.parseInt(secondLine[i]);
            posStalls[i] = pos;
        }
        System.out.println(binarySearch(posStalls, countOfCows));
    }
}