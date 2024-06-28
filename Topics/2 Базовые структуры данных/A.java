//package Tink.less2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;


public class TaskA2MinValueOnStack {
    static class Pair<K,V>{
        K value;
        V min;

        public Pair(K value, V min) {
            this.value = value;
            this.min = min;
        }

        public V getMin() {
            return min;
        }

    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        Stack<Pair<Integer,Integer>> stack = new Stack<>();
        int n = Integer.parseInt(br.readLine());
        for(int i =0; i < n; i++) {
            String[] data = br.readLine().split(" ");
            switch (Integer.parseInt(data[0])) {
                case 3 -> System.out.println(stack.peek().getMin());
                case 2 -> stack.pop();
                case 1 -> {
                    Integer value = Integer.parseInt(data[1]);
                    stack.add(new Pair<>(value, stack.isEmpty() ? value : value < stack.peek().getMin() ? value : stack.peek().getMin()));
                }
            }
        }
    }
}