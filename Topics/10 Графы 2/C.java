//package Tink.less10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class TaskC10TwoDimensionalDSU {
    static int n;
    static int m;

    static int index(int i, int j) {
        return i * (m + 1) + j;
    }

    static class Point {
        int i, j, cost;

        public Point(int i, int j, int cost) {
            this.i = i;
            this.j = j;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return i + " " + j + " " + cost;
        }
    }

    public static void main(String[] args) throws IOException {
        StringBuilder result = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Point> points = new ArrayList<>();
        int totalCost = 0;
        String[] firstLine = reader.readLine().split(" ");
        n = Integer.parseInt(firstLine[0]);
        m = Integer.parseInt(firstLine[1]);
        DSU dsu = new DSU(((n + 1) * (m + 1)) + 1);
        for (int i = 1; i <= n; i++) {
            String[] row = reader.readLine().split(" ");
            for (int j = 1; j <= m; j++) {
                int value = Integer.parseInt(row[j - 1]);
                if ((value & 1) > 0) {
                    dsu.union(index(i, j), index(i + 1, j));
                }
                if ((value & 2) > 0) {
                    dsu.union(index(i, j), index(i, j + 1));
                }
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= m; j++) {
                if (dsu.union(index(i, j), index(i + 1, j))) {
                    points.add(new Point(i, j, 1));
                    totalCost++;
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < m; j++) {
                if (dsu.union(index(i, j), index(i, j + 1))) {
                    points.add(new Point(i, j, 2));
                    totalCost += 2;
                }
            }
        }
        result.append(points.size() + " " + totalCost).append("\n");
        for (Point point : points) {
            result.append(point.toString()).append("\n");
        }
        System.out.println(result);
    }
}

class DSU {
    int[] parent;

    public DSU(int size) {
        parent = new int[size];
        for (int i = 1; i < size; i++) {
            parent[i] = i;
        }
    }

    int findRoot(int v) {
        if (parent[v] != v) {
            parent[v] = findRoot(parent[v]);
        }
        return parent[v];
    }

    boolean union(int v, int u) {
        v = findRoot(v);
        u = findRoot(u);
        if (v == u) {
            return false;
        }
        parent[u] = v;
        return true;
    }
}