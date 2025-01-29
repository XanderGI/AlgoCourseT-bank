import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class TaskF2AstrogradDeque {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String... args) throws IOException {
        int countOfRequests = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        int[] enterIndex = new int[100001];
        int countDeparted = 0;
        for (int i = 0; i < countOfRequests; i++) {
            String[] request = br.readLine().split(" ");
            Integer id = (request.length > 1) ? Integer.parseInt(request[1]) : null;
            switch (request[0]) {
                case "1" -> {
                    deque.addLast(id);
                    enterIndex[id] = deque.size() + countDeparted;
                }
                case "2" -> {
                    deque.removeFirst();
                    countDeparted++;
                }
                case "3" -> deque.removeLast();
                case "4" -> result.append(enterIndex[id] - 1 - countDeparted).append("\n");
                case "5" -> {
                    deque.peekFirst();
                    result.append(deque.peekFirst()).append("\n");
                }
            }
        }
        System.out.println(result);
    }
}