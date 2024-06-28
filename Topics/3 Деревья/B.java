//package Tink.less3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Task3BTestBSTandAVL {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Map<Integer, Node> nodes = new HashMap<>();
    static int min = Integer.MIN_VALUE;

    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

    }

    static boolean isBST(Node root)
    {
        if (root != null) {
            /*left->center->right*/
            if (!isBST(root.left)) return false;
            if (root.value <= min) return false;
            min = root.value;
            return isBST(root.right);
        }
        return true;
    }

    static void printInOrder(Node root) {
        if (root != null) {
            printInOrder(root.left);
            System.out.print(root.value + " ");
            printInOrder(root.right);
        }
    }

    static void BuildBinaryTree(int n, int r) throws IOException {

        for (int i = 0; i < n; i++) {
            String[] readerNodes = br.readLine().split(" ");
            int left = Integer.parseInt(readerNodes[0]);
            int right = Integer.parseInt(readerNodes[1]);

            Node leftNode = left != -1 ? nodes.computeIfAbsent(left, Node::new) : null;
            Node rightNode = right != -1 ? nodes.computeIfAbsent(right, Node::new) : null;

            Node newNode = nodes.computeIfAbsent(i, k -> new Node(k, leftNode, rightNode));
            if (leftNode != null){ newNode.left = leftNode; }
            if (rightNode != null){ newNode.right = rightNode; }
        }
    }
    /*https://javascopes.com/balanced-binary-tree-check-ccdcea44/*/
    public static int checkBalance(Node root) {
        if (root == null) {
            return 0;
        }
        int leftSubTree = checkBalance(root.left);
        if (leftSubTree == -1) {
            return -1;
        }
        int rightSubTree = checkBalance(root.right);
        if (rightSubTree == -1) {
            return -1;
        }
        if (Math.abs(rightSubTree-leftSubTree) > 1) {
            return -1;
        }
        return Math.max(rightSubTree,leftSubTree) + 1;
    }

    public static boolean isBalanced(Node root) {
        return checkBalance(root) != -1;
    }

    public static void main(String... args) throws IOException {
        String[] firstLine = br.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int r = Integer.parseInt(firstLine[1]);
        BuildBinaryTree(n,r);
        Node root = nodes.get(r);
        System.out.println(isBST(root) && isBalanced(root) ? 1 : 0);
    }
}