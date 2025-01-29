import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TaskA3FindDiameter {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int maxHeight = 0;
    static int diameter = 0;
    static StringBuilder results = new StringBuilder("0 ");
    static Node deepestNode = null;
    static HashSet<Node> visited = new HashSet<>();

    static class Node {
        Node parent;
        int value;
        int height;
        List<Node> listOfChild;

        public Node(int value) {
            this.value = value;
            this.height = 0;
            this.listOfChild = new ArrayList<>();
        }

        public void addChild(Node childNode) {
            childNode.height = this.height + 1;
            childNode.parent = this;
            results.append(childNode.height).append(" ");
            if (maxHeight < childNode.height) {
                maxHeight = childNode.height;
                deepestNode = childNode;
            }
            this.listOfChild.add(childNode);
        }
    }

    public static Node buildTree(int[] array) {
        Map<Integer, Node> nodes = new HashMap<>();
        nodes.put(0, new Node(0));
        for (int j = 1; j < array.length; j++) {
            int parentValue = array[j - 1];
            Node parent = nodes.computeIfAbsent(parentValue, Node::new);
            Node child = nodes.computeIfAbsent(j, Node::new);
            parent.addChild(child);
        }
        return nodes.get(0);
    }


    public static void dfs(Node deepestNode, int currentDistance) {
        visited.add(deepestNode);

        diameter = Math.max(currentDistance, diameter);

        // Идем по всем потомкам узла
        for (Node child : deepestNode.listOfChild) {
            if (!visited.contains(child)) {
                dfs(child, currentDistance + 1);
            }
        }

        // Если есть родительский узел и он ещё не посещен, продолжаем поиск
        if (deepestNode.parent != null && !visited.contains(deepestNode.parent)) {
            dfs(deepestNode.parent, currentDistance + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        String[] data = br.readLine().split(" ");
        int[] array = new int[n];
        for (int i = 0; i < n - 1; i++) {
            array[i] = Integer.parseInt(data[i]);
        }
        Node root = buildTree(array);
        dfs(deepestNode, 0);
        results.insert(0, maxHeight + " " + diameter + "\n");
        System.out.println(results.toString().trim());
    }
}