//package Tink.less2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class TaskC2PostfixExpr {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String[] data = br.readLine().split(" ");
        Stack<Integer> stack = new Stack<>();
        for (int i =0; i < data.length; i++) {
            if (data[i].equals("*")) {
                stack.push(stack.pop() * stack.pop());
            } else if (data[i].equals("+")) {
                stack.push(stack.pop() + stack.pop());
            } else if (data[i].equals("-")) {
                int rightValue = stack.pop();
                stack.push(stack.pop()-rightValue);
            } else {
                stack.push(Integer.parseInt(data[i]));
            }
        }
        System.out.println(stack.pop());
    }
}