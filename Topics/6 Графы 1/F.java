//package Tink.less6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskF6AlchemyAndBFS {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    static int bfs(HashMap<String,List<String>> map, String firstElement, String resultElement) {
        ArrayDeque<String> arrayDeque = new ArrayDeque<>();
        arrayDeque.add(firstElement);
        HashMap<String,Integer> mapForCheckVisited = new HashMap<>();
        mapForCheckVisited.put(firstElement,0);
        while (!arrayDeque.isEmpty()) {
            String currentElement = arrayDeque.pollFirst();
            if (currentElement.equals(resultElement)) {
                return mapForCheckVisited.get(currentElement);
            }
            if (map.containsKey(currentElement)) {
                for (String element : map.get(currentElement)) {
                    if (mapForCheckVisited.get(element) == null) {
                        mapForCheckVisited.put(element, mapForCheckVisited.get(currentElement) + 1);
                        arrayDeque.addLast(element);
                    }
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(reader.readLine());
        HashMap<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String[] line = reader.readLine().split(" -> ");
            String key = line[0];
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(line[1]);
        }
        String firstElement = reader.readLine();
        String resultElement = reader.readLine();
        System.out.println(bfs(map,firstElement,resultElement));
    }
}