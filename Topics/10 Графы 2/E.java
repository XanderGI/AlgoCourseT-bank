//package Tink.less10;

import java.util.*;

public class Task10EDijkstraFindMinWay {
    public static int[] dijkstra(List<List<Edge>> graph, int start, int n) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        pq.add(new Edge(start, 0));
        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            if (current.weight > dist[current.to]) continue;
            for (Edge edge : graph.get(current.to)) {
                if (dist[current.to] + edge.weight < dist[edge.to]) {
                    dist[edge.to] = dist[current.to] + edge.weight;
                    pq.add(new Edge(edge.to, dist[edge.to]));
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        StringBuilder result = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        List<List<Edge>> graph = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for(int i = 0; i < m; i++) {
            int u = scanner.nextInt() - 1;
            int v = scanner.nextInt() - 1;
            int w = scanner.nextInt();
            graph.get(u).add(new Edge(v, w));
            graph.get(v).add(new Edge(u, w));
        }
        for (int el : dijkstra(graph, 0, n)) {
            result.append(el + " ");
        }
        System.out.print(result.toString().trim());
    }
}

class Edge {
    int to, weight;
    Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}