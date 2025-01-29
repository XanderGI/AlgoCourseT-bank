import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class TaskE2SortingWagonsWithStack {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        br.readLine();
        String[] stringWagons = br.readLine().split(" ");
        Stack<Integer> stack = new Stack<>();
        StringBuilder stringOfSeq = new StringBuilder();
        StringBuilder result = new StringBuilder("\n");
        int numberOfWagons = 1;
        int numberOfOperations = 0;
        int countOfWagonsForOperation = 1;
        for (int i = 0; i < stringWagons.length; i++) {
            int value = Integer.parseInt(stringWagons[i]);
            if (!stack.isEmpty() && value > stack.peek()) {
                System.out.println("0");
                return;
            }
            stack.push(value);
            stringOfSeq.append("1");
            while (!stack.isEmpty() && stack.peek() == numberOfWagons) {
                numberOfWagons++;
                stack.pop();
                stringOfSeq.append("2");
            }
        }
        for (int j = 0; j < stringOfSeq.length() - 1; j++) {
            if (stringOfSeq.charAt(j) == stringOfSeq.charAt(j + 1)) {
                countOfWagonsForOperation++;
            } else {
                numberOfOperations++;
                result.append(stringOfSeq.charAt(j) + " " + countOfWagonsForOperation + "\n");
                countOfWagonsForOperation = 1;
            }
        }
        numberOfOperations++;
        result.append(stringOfSeq.charAt(stringOfSeq.length() - 1) + " " + countOfWagonsForOperation + "\n");
        System.out.println(result.insert(0, numberOfOperations));
    }
}