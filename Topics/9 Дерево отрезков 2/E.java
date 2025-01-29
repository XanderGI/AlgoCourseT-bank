import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TaskE9VIKA {
    private static long result = 0;

    static class Pair<U, V> {
        private final U first;
        private final V second;

        public Pair(U first, V second) {
            this.first = first;
            this.second = second;
        }

        public U getFirst() {
            return first;
        }

        public V getSecond() {
            return second;
        }
    }

    public static Pair<List<Integer>, Map<Integer, Integer>> compressCoordinates(Set<Integer> coordinates) {
        List<Integer> sortedCoordinates = new ArrayList<>(coordinates);
        Map<Integer, Integer> coordIndex = new HashMap<>();
        for (int i = 0; i < sortedCoordinates.size(); i++) {
            coordIndex.put(sortedCoordinates.get(i), i);
        }
        return new Pair<>(sortedCoordinates, coordIndex);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        Event[] requests = new Event[2 * n];
        Set<Integer> uniqueYCoordinates = new TreeSet<>();
        for (int i = 0, j = 0; i < n; i++) {
            String[] request = reader.readLine().split(" ");
            int x1 = Integer.parseInt(request[0]);
            int y1 = Integer.parseInt(request[1]);
            int x2 = Integer.parseInt(request[2]);
            int y2 = Integer.parseInt(request[3]);
            if (x1 > x2 || y1 > y2) { // swap
                x1 ^= x2;
                x2 ^= x1;
                x1 ^= x2;
                y1 ^= y2;
                y2 ^= y1;
                y1 ^= y2;
            }
            uniqueYCoordinates.add(y1);
            uniqueYCoordinates.add(++y2);
            requests[j++] = new Event(x1, y1, y2, 1);
            requests[j++] = new Event(++x2, y1, y2, -1);
        }

        Arrays.sort(requests, Comparator.comparingInt((Event o) -> o.x).thenComparingInt(o -> -o.type));

        Pair<List<Integer>, Map<Integer, Integer>> compressionResults = compressCoordinates(uniqueYCoordinates);
        List<Integer> sortedYCoordinates = compressionResults.getFirst();
        Map<Integer, Integer> yCoordinatesIndex = compressionResults.getSecond();
        long[] distances = new long[sortedYCoordinates.size() - 1];
        for (int i = 1; i < sortedYCoordinates.size(); i++) {
            distances[i - 1] = sortedYCoordinates.get(i) - sortedYCoordinates.get(i - 1);
        }
        long sumDistance = Arrays.stream(distances).sum();
        SegmentTree segmentTree = new SegmentTree(distances);
        long previousX = requests[0].x;
        for (Event event : requests) {
            long differenceX = (event.x - previousX);
            result += (sumDistance - segmentTree.requestMin()) * differenceX;
            segmentTree.update(yCoordinatesIndex.get(event.y1), yCoordinatesIndex.get(event.y2), event.type);
            previousX = event.x;
        }
        System.out.println(result);
    }
}

class Event {
    int x;
    int y1;
    int y2;
    int type;

    public Event(int x, int y1, int y2, int type) {
        this.y1 = y1;
        this.y2 = y2;
        this.x = x;
        this.type = type;
    }
}

class SegmentTree {
    private final int size;
    private final long[] min;
    private final long[] minLength;
    private final long[] promiseSum;

    public SegmentTree(long[] distances) {
        size = distances.length;
        min = new long[4 * size];
        minLength = new long[4 * size];
        promiseSum = new long[4 * size];
        build(1, 0, distances.length, distances);
    }

    private void build(int v, int left, int right, long[] dist) {
        if (right - left == 1) {
            minLength[v] = dist[left];
        } else {
            int middle = (right + left) >>> 1;
            build(2 * v, left, middle, dist);
            build(2 * v + 1, middle, right, dist);
            minLength[v] = minLength[2 * v] + minLength[2 * v + 1];
        }
    }


    private void push(int v) {
        int left = v * 2;
        int right = v * 2 + 1;
        promiseSum[left] += promiseSum[v];
        min[left] += promiseSum[v];
        promiseSum[right] += promiseSum[v];
        min[right] += promiseSum[v];
        promiseSum[v] = 0;
    }

    private void recalculateValues(int v) {
        int left = v * 2;
        int right = v * 2 + 1;
        if (min[left] > min[right]) {
            min[v] = min[right];
            minLength[v] = minLength[right];
        } else if (min[left] == min[right]) {
            min[v] = min[left];
            minLength[v] = minLength[left] + minLength[right];
        } else {
            min[v] = min[left];
            minLength[v] = minLength[left];
        }
    }

    private void update(int v, int leftTree, int rightTree, int leftReq, int rightReq, int value) {
        if (rightTree <= leftReq || rightReq <= leftTree) {
            return;
        }
        if (leftReq <= leftTree && rightTree <= rightReq) {
            min[v] += value;
            promiseSum[v] += value;
            return;
        }
        push(v);
        int middle = (leftTree + rightTree) >>> 1;
        update(2 * v, leftTree, middle, leftReq, rightReq, value);
        update(2 * v + 1, middle, rightTree, leftReq, rightReq, value);
        recalculateValues(v);
    }

    public long requestMin() {
        return min[1] == 0 ? minLength[1] : 0;
    }

    public void update(int leftReq, int rightReq, int value) {
        update(1, 0, size, leftReq, rightReq, value);
    }
}