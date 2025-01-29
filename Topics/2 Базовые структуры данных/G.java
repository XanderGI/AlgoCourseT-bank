import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class TaskG2GoblinsAndQueue {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        Deque<Integer> frontHalf = new ArrayDeque<>();
        Deque<Integer> backHalf = new ArrayDeque<>();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < N; i++) {
            String[] request = br.readLine().split(" ");
            switch (request[0]) {
                case "+":
                    backHalf.addLast(Integer.parseInt(request[1]));
                    break;
                case "-":
                    result.append(frontHalf.isEmpty() ? backHalf.pollFirst() : frontHalf.pollFirst()).append("\n");
                    break;
                case "*":
                    backHalf.addFirst(Integer.parseInt(request[1]));
                    break;
            }

            while (frontHalf.size() > backHalf.size() + 1) {
                backHalf.addFirst(frontHalf.pollLast());
            }
            while (backHalf.size() > frontHalf.size()) {
                frontHalf.addLast(backHalf.pollFirst());
            }
        }
        System.out.println(result);
    }
}