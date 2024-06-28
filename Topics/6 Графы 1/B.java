//package Tink.less6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TaskB6FindCycle {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static int result = 0;

    static boolean dfs(int v, List<List<Integer>> listOfEdges, int[] colors) {
        colors[v] = 1;
        for (int edge : listOfEdges.get(v)) {
            if (colors[edge] == 0) {
                if (dfs(edge,listOfEdges,colors)) {
                    return true;
                }
            } else if (colors[edge] == 1) {
                return true;
            }
        }
        colors[v] = 2;
        return false;
    }

    public static void main(String[] args) throws IOException {
        String[] data = reader.readLine().split(" ");
        int n = Integer.parseInt(data[0]);
        int m = Integer.parseInt(data[1]);
        int[] colors = new int[n+1];
        List<List<Integer>> listOfEdges = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            listOfEdges.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            String[] pair = reader.readLine().split(" ");
            int edgeOne = Integer.parseInt(pair[0]);
            int edgeTwo = Integer.parseInt(pair[1]);
            listOfEdges.get(edgeOne).add(edgeTwo);
        }

        for (int i = 1; i <= n; i++) {
            if (colors[i] == 0) {
                if (dfs(i,listOfEdges,colors)) {
                    result = 1;
                    break;
                }
            }
        }
        System.out.println(result);
    }
}