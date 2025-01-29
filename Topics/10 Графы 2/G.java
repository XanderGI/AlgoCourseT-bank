import java.util.*;
import java.io.*;

public class TaskG10DijkstraFromManyPeaks {

    static final long MAX_VALUE = Long.MAX_VALUE;
    static long result = MAX_VALUE;
    static int n;
    static int m;

    static class Edge implements Comparable<Edge> {
        int v;
        long time;

        Edge(int v, long time) {
            this.v = v;
            this.time = time;
        }

        @Override
        public int compareTo(Edge other) {
            if (this.time != other.time) {
                return Long.compare(this.time, other.time);
            }
            return Integer.compare(this.v, other.v);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return v == edge.v && time == edge.time;
        }

        @Override
        public int hashCode() {
            return Objects.hash(v, time);
        }
    }

    public static long[] dijkstra(ArrayList<ArrayList<Edge>> graph, int start) {
        long[] distances = new long[n + 1];
        Arrays.fill(distances, Long.MAX_VALUE);
        distances[start] = 0;

        TreeSet<Edge> treeSet = new TreeSet<>();

        treeSet.add(new Edge(start, 0L));

        while (!treeSet.isEmpty()) {
            Edge current = treeSet.pollFirst();
            int v = current.v;
            long time = current.time;
            if (time > distances[v]) {
                continue;
            }
            for (Edge edge : graph.get(v)) {
                int u = edge.v;
                long nextDist = time + edge.time;

                if (distances[u] > nextDist) {
                    treeSet.remove(new Edge(u, distances[u]));
                    distances[u] = nextDist;
                    treeSet.add(new Edge(u, distances[u]));
                }
            }
        }
        return distances;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] line = reader.readLine().split(" ");

        n = Integer.parseInt(line[0]);
        m = Integer.parseInt(line[1]);

        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            String[] tempEdge = reader.readLine().split(" ");
            int u = Integer.parseInt(tempEdge[0]);
            int v = Integer.parseInt(tempEdge[1]);
            long t = Long.parseLong(tempEdge[2]);
            graph.get(u).add(new Edge(v, t));
            graph.get(v).add(new Edge(u, t));
        }
        String[] houses = reader.readLine().split(" ");

        int a = Integer.parseInt(houses[0]);
        int b = Integer.parseInt(houses[1]);
        int c = Integer.parseInt(houses[2]);

        long[] FromA = dijkstra(graph, a);
        long[] FromB = dijkstra(graph, b);
        long[] FromC = dijkstra(graph, c);

        for (int i = 1; i <= n; i++) {
            if (FromA[i] == MAX_VALUE || FromB[i] == MAX_VALUE || FromC[i] == MAX_VALUE) {
                continue;
            }
            long minDistance = Math.min(FromA[i], Math.min(FromB[i], FromC[i]));
            if (minDistance == FromA[i]) {
                result = Math.min(result, FromB[i] + FromC[i] + (2 * minDistance));
            } else if (minDistance == FromB[i]) {
                result = Math.min(result, FromA[i] + FromC[i] + (2 * minDistance));
            } else if (minDistance == FromC[i]) {
                result = Math.min(result, FromB[i] + FromA[i] + (2 * minDistance));
            }
        }
        System.out.print(result == MAX_VALUE ? -1 : result);
    }
}