//package Tink.less10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TaskB10Kruskal {

    public static void main(String[] args) throws IOException {
        long answer = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = reader.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int m = Integer.parseInt(firstLine[1]);
        Graph graph = new Graph(n,m);
        for (int i = 0; i < m; i++) {
            String[] newEdge = reader.readLine().split(" ");
            int left = Integer.parseInt(newEdge[0]);
            int right = Integer.parseInt(newEdge[1]);
            int weight = Integer.parseInt(newEdge[2]);
            graph.edges[i] = new Edge(left,right,weight);
        }
        Arrays.sort(graph.edges, ((o1, o2) -> o1.weight - o2.weight));
        DSU dsu = new DSU(n+1);
        for (Edge edge : graph.edges) {
            if (dsu.findParent(edge.left) != dsu.findParent(edge.right)) {
                answer += edge.weight;
                dsu.union(edge.left,edge.right);
            }
        }
        System.out.println(answer);
    }
}

class DSU {
    int[] parent;
    int[] rank;

    public DSU(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    int findParent(int v) {
        if (parent[v] != v) {
            parent[v] = findParent(parent[v]);
        }
        return parent[v];
    }

    void union(int x, int y) {
        int v = findParent(x);
        int u = findParent(y);
        if (v != u) {
            if (rank[v] < rank[u]) {
                int temp = v;
                v = u;
                u = temp;
            }
            parent[v] = u;
            if (rank[v] == rank[u]) {
                rank[v]++;
            }
        }
    }

}

class Edge  {
    int left;
    int right;
    int weight;
    public Edge(int left, int right, int weight) {
        this.left = left;
        this.right = right;
        this.weight = weight;
    }
}

class Graph {
    int v;
    int e;
    Edge[] edges;

    public Graph(int v, int e) {
        this.v = v;
        this.e = e;
        edges = new Edge[e];
    }

}