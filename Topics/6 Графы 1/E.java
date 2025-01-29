import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class TaskE6NumberTheoryAndBFS {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    static class Pair {
        int v;
        int w;

        public Pair(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void main(String... args) throws IOException {
        int k = Integer.parseInt(reader.readLine());
        int[] distance = new int[k]; //массив сумм где индексами являются остатки при делении на k [0,k-1]
        for (int i = 0; i < k; i++) {
            distance[i] = Integer.MAX_VALUE; // для нахожедния мин суммы в вершине, инициализируем макс. числом
        }
        distance[1] = 1; // начинаем с вершины 1 -> сумма = 1
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        arrayDeque.addLast(1);
        List<List<Pair>> listOfEdges = new ArrayList<>(k);

        for (int i = 0; i < k; i++) {
            listOfEdges.add(new ArrayList<>());
        }

        for (int i = 0; i < k; i++) {
            listOfEdges.get(i).add(new Pair((i + 1) % k, 1)); // вес ребра = 1 тк остаток увеличивается на 1 => вес ребра = 1
            listOfEdges.get(i).add(new Pair((10 * i) % k, 0)); // сумма не изменилась так как остаток остался прежним => вес ребра = 0
        }
        while (!arrayDeque.isEmpty()) { // используем bfs так как он обеспечивает нахождение кратчайшего пути ~ мин суммы.
            int edge = arrayDeque.pollFirst();
            for (Pair pair : listOfEdges.get(edge)) {
                if (distance[edge] + pair.w < distance[pair.v]) { // если нашли более минимальную сумму на текущ. итерации чем в массиве distance
                    distance[pair.v] = distance[edge] + pair.w;
                    arrayDeque.addLast(pair.v);
                }
            }
        }
        System.out.println(distance[0]);
    }
}