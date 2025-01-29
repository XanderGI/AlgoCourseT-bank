import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class TaskD2BalloonsAndStack {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static class Pair<K, V> {
        K value;
        V strike;

        public Pair(K value, V min) {
            this.value = value;
            this.strike = min;
        }

        public V getStrike() {
            return strike;
        }

        public K getValue() {
            return value;
        }

    }

    public static void main(String[] args) throws IOException {
        br.readLine();
        String[] array = br.readLine().split(" ");
        Stack<Pair<Integer, Integer>> stack = new Stack<>();
        int result = 0;
        for (int i = 0; i < array.length; i++) {
            Integer value = Integer.parseInt(array[i]);
            Pair<Integer, Integer> peekInStack = (stack.isEmpty()) ? null : stack.peek();
            if (!stack.isEmpty() && value != peekInStack.getValue() && peekInStack.getStrike() >= 3) {
                int strike = peekInStack.getStrike();
                result += peekInStack.getStrike();
                while (strike > 0) {
                    stack.pop();
                    strike--;
                }
            }
            if (!stack.isEmpty() && value == stack.peek().getValue()) {
                stack.push(new Pair<>(value, stack.peek().getStrike() + 1));
                if (i == array.length - 1 && stack.peek().getStrike() >= 3) {
                    int strike = stack.peek().getStrike();
                    result += stack.peek().getStrike();
                    while (strike > 0) {
                        stack.pop();
                        strike--;
                    }
                }
            } else {
                stack.push(new Pair<>(value, 1));
            }

        }
        System.out.println(result);
    }
}