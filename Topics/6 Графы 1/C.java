//package Tink.less6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TaskC6TopologicalSort {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static boolean checkValidTopSort(int[] pos, List<Integer>[] adj) {
        for (int u = 0; u < adj.length; u++) {
            for (int v : adj[u]) {
                if (pos[u] > pos[v]) {
                    return false;
                }
            }
        }
        return true;
    }


    public static void main(String[] args) throws IOException {
        String[] data = reader.readLine().split(" ");
        int n = Integer.parseInt(data[0]);
        int m = Integer.parseInt(data[1]);
        List<Integer>[] listOfEdges = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            listOfEdges[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            String[] pair = reader.readLine().split(" ");
            int edgeOne = Integer.parseInt(pair[0]);
            int edgeTwo = Integer.parseInt(pair[1]);
            listOfEdges[edgeOne].add(edgeTwo);
        }
        String[] givenOrder = reader.readLine().split(" ");

        int[] pos = new int[n + 1];
        for (int i = 0; i < n; i++) {
            pos[Integer.parseInt(givenOrder[i])] = i;
        }

        if (checkValidTopSort(pos, listOfEdges)) {
            System.out.println("YES");
        }
        else {
            System.out.println("NO");
        }

    }
}