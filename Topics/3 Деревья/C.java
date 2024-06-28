//package Tink.less3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task3CfindLCA {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Map<Integer,Node> nodes = new HashMap<>();
    static StringBuilder result = new StringBuilder();
    static class Node {
        int value;
        Node parent;
        int height;
        List<Node> listOfChild;


        public Node(int value) {
            this.value = value;
            this.height = 0;
            this.listOfChild = new ArrayList<>();
        }

        public void addChild(Node child) {
            child.height = this.height + 1;
            child.parent = this;
            this.listOfChild.add(child);
        }

    }
    static Node buildTree() throws IOException {
        br.readLine();
        String[] arrayOfNode = br.readLine().split(" ");
        nodes.put(0,new Node(0));
        for (int i = 1; i <= arrayOfNode.length; i++) {
            int parentValue = Integer.parseInt(arrayOfNode[i-1]);
            Node parent = nodes.computeIfAbsent(parentValue,Node::new);
            Node child = nodes.computeIfAbsent(i,Node::new);
            parent.addChild(child);
        }
        return nodes.get(0);
    }
    public static void printPreOrder(Node root) {
        if (root != null) {
            System.out.print(root.value + " ");
            for (Node child : root.listOfChild) {
                printPreOrder(child);
            }
        }
    }

    public static Node findLCA(Node u, Node v) {
        while(u.height > v.height) {
            u = u.parent;
        }
        while (v.height > u.height) {
            v = v.parent;
        }
        while(u.value != v.value) {
            u = u.parent;
            v = v.parent;
        }
        return u;
    }

    public static void main(String[] args) throws IOException {
        Node root = buildTree();
        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            String[] pair = br.readLine().split(" ");
            Node u = nodes.get(Integer.parseInt(pair[0]));
            Node v = nodes.get(Integer.parseInt(pair[1]));
            result.append(findLCA(u,v).value).append("\n");
        }
        System.out.println(result);
    }
}