import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TaskF10DijkstraAndBinarySearch {

    private static int n, m;
    private static List<List<int[]>> graph;

    private static int dijkstra(int cups) {
        int[] used = new int[n + 1];
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(e -> e[1]));
        priorityQueue.add(new int[]{1, 0});
        while (!priorityQueue.isEmpty()) {
            int[] current = priorityQueue.poll();
            int v = current[0];
            int time = current[1];
            if (used[v] == 1) {
                continue;
            }
            used[v] = 1;
            if (time > 1440) {
                continue;
            }
            if (v == n) {
                return time;
            }
            for (int[] edge : graph.get(v)) {
                if (used[edge[0]] == 0 && edge[2] >= cups) {
                    int tempTime = time + edge[1];
                    if (tempTime <= 1440) {
                        priorityQueue.add(new int[]{edge[0], tempTime});
                    }
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = reader.readLine().split(" ");
        n = Integer.parseInt(firstLine[0]);
        if (n == 1) {
            System.out.print(100000000);
            return;
        }
        m = Integer.parseInt(firstLine[1]);

        graph = new ArrayList<>(Collections.nCopies(n + 1, null));
        for (int i = 0; i <= n; i++) {
            graph.set(i, new ArrayList<>());
        }

        TreeSet<Integer> sortedCups = new TreeSet<>();
        for (int j = 0; j < m; j++) {
            String[] edge = reader.readLine().split(" ");
            int from = Integer.parseInt(edge[0]);
            int to = Integer.parseInt(edge[1]);
            int time = Integer.parseInt(edge[2]);
            int weight = Integer.parseInt(edge[3]);
            int countCups = (weight - 3000000) / 100;
            if (countCups <= 0) {
                continue;
            }
            graph.get(from).add(new int[]{to, time, countCups});
            graph.get(to).add(new int[]{from, time, countCups});
            sortedCups.add(countCups);
        }
        ArrayList<Integer> cups = new ArrayList<>(sortedCups);
        System.out.print(findMaxCountCups(cups));
    }

    static int findMaxCountCups(List<Integer> cups) {
        int left = 0;
        int right = cups.size() - 1;
        int result = 0;
        while (right >= left) {
            int middle = (right + left) / 2;
            int time = dijkstra(cups.get(middle));
            if (time != 0) {
                left = middle + 1;
                if (cups.get(middle) > result) {
                    result = cups.get(middle);
                }
            } else {
                right = middle - 1;
            }
        }
        return result;
    }
}