import java.util.*;

public class TaskA11RequestsLCA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int logN = (int) (Math.log(n) / Math.log(2)) + 1;
        Graph graph = new Graph(n, logN);
        for (int i = 1; i < n; i++) {
            int v = sc.nextInt();
            graph.addEdge(v, i);
        }
        graph.dfs(0, 0);
        StringBuilder result = new StringBuilder();
        int m = sc.nextInt();
        for (int j = 0; j < m; j++) {
            int v = sc.nextInt();
            int u = sc.nextInt();
            result.append(graph.findLCA(v, u)).append("\n");
        }
        System.out.print(result);
    }
}

class Node {
    int parent;
    int enterTime;
    int exitTime;
    int[] ancestors;

    public Node(int logN) {
        ancestors = new int[logN + 1];
    }
}

class Graph {
    private final List<List<Integer>> adjList;
    private final Node[] nodes;
    private final int logN;
    private int timer;

    public Graph(int n, int logN) {
        this.logN = logN;
        this.nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(logN);
        }
        adjList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int from, int to) {
        adjList.get(from).add(to);
        adjList.get(to).add(from);
    }

    public void dfs(int v, int parent) {
        Node node = nodes[v];
        node.enterTime = timer++;
        node.parent = parent;
        node.ancestors[0] = parent;
        for (int i = 1; i <= logN; i++) {
            node.ancestors[i] = nodes[node.ancestors[i - 1]].ancestors[i - 1];
        }
        for (int u : adjList.get(v)) {
            if (parent != u) {
                dfs(u, v);
            }
        }
        node.exitTime = timer++;
    }

    public int findLCA(int v, int u) {
        if (isAncestor(u, v)) return u;
        if (isAncestor(v, u)) return v;
        for (int i = logN; i >= 0; i--) {
            if (!isAncestor(nodes[v].ancestors[i], u)) {
                v = nodes[v].ancestors[i];
            }
        }
        return nodes[v].ancestors[0];
    }

    public boolean isAncestor(int a, int b) {
        return nodes[a].enterTime <= nodes[b].enterTime && nodes[a].exitTime >= nodes[b].exitTime;
    }
}