import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class TaskA10DSU {
    public static void main(String[] args) throws IOException {
        StringBuilder result = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = reader.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int m = Integer.parseInt(firstLine[1]);
        DSU dsu = new DSU(n + 1);
        int v;
        int u;
        for (int i = 0; i < m; i++) {
            String[] request = reader.readLine().split(" ");
            String operation = request[0];
            switch (operation) {
                case "union":
                    v = Integer.parseInt(request[1]);
                    u = Integer.parseInt(request[2]);
                    dsu.union(v, u);
                    break;
                case "get":
                    v = Integer.parseInt(request[1]);
                    int[] resultArray = dsu.get(v);
                    StringBuilder temp = new StringBuilder();
                    for (int element : resultArray) {
                        temp.append(element + " ");
                    }
                    result.append(temp.toString().trim()).append("\n");
                    break;
            }
        }
        System.out.println(result);
    }
}

class DSU {
    int[] parent;
    int[] rank;
    int[] min;
    int[] max;
    int[] size;

    public DSU(int n) {
        parent = new int[n];
        rank = new int[n];
        min = new int[n];
        max = new int[n];
        size = new int[n];

        for (int i = 1; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
            min[i] = i;
            max[i] = i;
            size[i] = 1;
        }

    }

    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    void union(int x, int y) {
        int v = find(x);
        int u = find(y);

        if (v != u) {
            if (rank[v] < rank[u]) {
                parent[v] = u;
                min[u] = Math.min(min[v], min[u]);
                max[u] = Math.max(max[v], max[u]);
                size[u] += size[v];
            } else {
                parent[u] = v;
                min[v] = Math.min(min[v], min[u]);
                max[v] = Math.max(max[v], max[u]);
                size[v] += size[u];
                if (rank[v] == rank[u]) {
                    rank[v]++;
                }
            }
        }
    }

    int[] get(int v) {
        int root = find(v);
        return new int[]{min[root], max[root], size[root]};
    }
}