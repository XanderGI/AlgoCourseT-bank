import java.util.*;

public class TaskB11RequestMinCostEdgeLCA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder result = new StringBuilder();
        int n = sc.nextInt();
        int logN = (int) Math.ceil(Math.log(n) / Math.log(2));
        Graph graph = new Graph(n, logN);
        for (int i = 1; i < n; i++) {
            int from = sc.nextInt();
            int weight = sc.nextInt();
            graph.addEdge(from, i, weight);
        }
        graph.dfs(0, -1, 0, 0);
        int q = sc.nextInt();
        for (int i = 0; i < q; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            result.append(graph.findMinEdgeOnPath(u, v)).append("\n");
        }
        System.out.print(result);
    }
}

class Node {
    int parent;
    int enterTime;
    int exitTime;
    int[][] ancestors;
    int depth;

    public Node(int logN) {
        ancestors = new int[logN + 1][2];
        for (int i = 0; i < ancestors.length; i++) {
            ancestors[i][0] = -1;
            ancestors[i][1] = Integer.MAX_VALUE;
        }
    }
}

class Graph {
    private final List<List<Pair>> adjList;
    private final Node[] nodes;
    private final int logN;
    private int timer;

    static class Pair {
        int vertex;
        int weight;

        Pair(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    public Graph(int n, int logN) {
        this.logN = logN;
        this.nodes = new Node[n];
        this.adjList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(logN);
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int from, int to, int weight) {
        adjList.get(from).add(new Pair(to, weight));
        adjList.get(to).add(new Pair(from, weight));
    }

    public void dfs(int v, int parent, int weight, int depth) {
        Node node = nodes[v];
        node.enterTime = timer++;
        node.parent = parent;
        node.depth = depth;
        node.ancestors[0][0] = parent;
        node.ancestors[0][1] = weight;
        for (int i = 1; i <= logN; i++) {
            int[] ancestorInfo = node.ancestors[i - 1];
            if (ancestorInfo[0] != -1) {
                int[] higherAncestorInfo = nodes[ancestorInfo[0]].ancestors[i - 1];
                node.ancestors[i][0] = higherAncestorInfo[0]; // Предок на 2^(i-1) шагов вверх от текущего
                node.ancestors[i][1] = Math.min(ancestorInfo[1], higherAncestorInfo[1]); // Мин вес на этом пути
            }
        }
        for (Pair next : adjList.get(v)) {
            if (next.vertex != parent) {
                dfs(next.vertex, v, next.weight, depth + 1);
            }
        }
        node.exitTime = timer++;
    }

    public int findMinEdgeOnPath(int u, int v) {
        if (nodes[u].depth < nodes[v].depth) {
            int temp = u;
            u = v;
            v = temp;
        }
        int minWeight = Integer.MAX_VALUE;
        for (int i = logN; i >= 0; i--) {
            if (nodes[u].depth - (1 << i) >= nodes[v].depth) {
                minWeight = Math.min(minWeight, nodes[u].ancestors[i][1]);
                u = nodes[u].ancestors[i][0];
            }
        }
        if (u == v) {
            return minWeight;
        }
        for (int i = logN; i >= 0; i--) {
            if (nodes[u].ancestors[i][0] != nodes[v].ancestors[i][0]) {
                minWeight = Math.min(minWeight, Math.min(nodes[u].ancestors[i][1], nodes[v].ancestors[i][1]));
                u = nodes[u].ancestors[i][0];
                v = nodes[v].ancestors[i][0];
            }
        }
        minWeight = Math.min(minWeight, Math.min(nodes[u].ancestors[0][1], nodes[v].ancestors[0][1]));
        return minWeight;
    }
}