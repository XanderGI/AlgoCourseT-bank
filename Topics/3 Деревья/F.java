import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Task3FrealizationTreapForContest {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static Node root;

    static class Node {
        int value;
        long priority;
        Node left;
        Node right;
        private static final Random random = new Random();

        public Node(int value) {
            this.value = value;
            this.priority = random.nextLong() & Long.MAX_VALUE;
        }
    }

    static class Pair {
        Node left;
        Node right;

        public Pair() {
        }

        public Pair(Node left, Node right) {
            this.left = left;
            this.right = right;
        }
    }

    /*T(v) <= T(u)*/
    static Node merge(Node v, Node u) {
        if (v == null) { // значит отдаем только u
            return u;
        } else if (u == null) { //значит отдаем только v
            return v;
        } else if (v.priority > u.priority) { // значит надо склеить правое поддерево v и u
            v.right = merge(v.right, u);
            return v;
        } else {
            u.left = merge(v, u.left); // левое поддерево u и все дерево v
            return u;
        }

    }

    static Pair split(Node root, int value) {
        if (root == null) {
            return new Pair();
        } else { /*отсекаем правое поддерево*/
            if (root.value <= value) {
                Pair pair = split(root.right, value);
                root.right = pair.left;
                return new Pair(root, pair.right);
            } else { /*отсекаем левое поддерево*/
                Pair pair = split(root.left, value);
                root.left = pair.right;
                return new Pair(pair.left, root);
            }
        }
    }


    static int next(Node root, int value) {
        int result = -1;
        while (root != null) {
            if (root.value >= value) {
                result = root.value;
                root = root.left;
            } else {
                root = root.right;
            }
        }
        //-1 если подходящего элемента не существует.
        return result;
    }

    static int findElement(Node root, int value) {
        if (root != null) {
            if (root.value == value) {
                return root.value;
            } else if (root.value > value) {
                return findElement(root.left, value);
            } else {
                return findElement(root.right, value);
            }
        } else {
            return -1;
        }
    }

    /*left <= value < right*/
    static Node addElement(Node root, int value) {
        if (findElement(root, value) != -1) {
            return root;
        }
        Pair splitTree = split(root, value);
        Node newNode = new Node(value);
        return merge(merge(splitTree.left, newNode), splitTree.right);
    }

    public static void main(String[] args) throws IOException {
        StringBuilder result = new StringBuilder();
        int n = Integer.parseInt(reader.readLine());
        int prevValue = -1;
        for (int i = 0; i < n; i++) {
            String[] pair = reader.readLine().split(" ");
            int value = Integer.parseInt(pair[1]);
            switch (pair[0]) {
                case "+":
                    if (prevValue != -1) {
                        value = (prevValue + value) % 1000000000;
                        prevValue = -1;
                    }
                    root = addElement(root, value);
                    break;
                case "?":
                    int nextValue = next(root, value);
                    result.append(nextValue).append("\n");
                    prevValue = nextValue;
                    break;
            }
        }
        System.out.println(result);
    }
}