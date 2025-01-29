import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class TaskA6findCountOfComponentConnectivity {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder result = new StringBuilder();

    static void dfs(int v, int[] used, List<List<Integer>> listOfEdges, List<Integer> component) {
        used[v] = 1;
        component.add(v);
        for (int edge : listOfEdges.get(v)) {
            if (used[edge] != 1) {
                dfs(edge, used, listOfEdges, component);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String[] data = reader.readLine().split(" ");
        int n = Integer.parseInt(data[0]);
        int m = Integer.parseInt(data[1]);
        int[] used = new int[n + 1];
        List<List<Integer>> listOfEdges = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            listOfEdges.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            String[] pair = reader.readLine().split(" ");
            int edgeOne = Integer.parseInt(pair[0]);
            int edgeTwo = Integer.parseInt(pair[1]);
            listOfEdges.get(edgeOne).add(edgeTwo);
            listOfEdges.get(edgeTwo).add(edgeOne);
        }

        List<List<Integer>> listOfComponents = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (used[i] != 1) {
                List<Integer> component = new ArrayList<>();
                dfs(i, used, listOfEdges, component);
                Collections.sort(component);
                listOfComponents.add(component);
            }
        }
        result.append(listOfComponents.size()).append("\n");
        for (List<Integer> component : listOfComponents) {
            result.append(component.size()).append("\n");
            StringBuilder listOfEdgesInComponent = new StringBuilder();
            for (int v : component) {
                listOfEdgesInComponent.append(v + " ");
            }
            result.append(listOfEdgesInComponent.toString().trim()).append("\n");
        }
        System.out.println(result);
    }
}