import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;


public class TaskF9ParkAndFenwick {

    static class Plant {
        long x;
        long y;
        int id;

        public Plant(long _x, long _y, int _id) {
            this.x = _x;
            this.y = _y;
            this.id = _id;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().split(" ")[2]);
        List<Plant> Plants = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            double x = Double.parseDouble(input[0]);
            double y = Double.parseDouble(input[1]);
            Plants.add(new Plant((long) (x * 2), (long) (y * 2), i));
        }

        List<Long> y = Plants.stream()
                .map(Plant -> Plant.y)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        Plants.sort(Comparator.comparingLong(center -> center.x));
        long[] x = new long[y.size()];

        long[] ans = new long[n];
        for (Plant center : Plants) {
            int index = Collections.binarySearch(y, center.y);
            long dist = center.x - getSumUntil(x, index);
            ans[center.id] = dist;
            int first = findFirst(y, center.y - dist);
            int after = findFirst(y, center.y + dist);
            updateFenwickTreeRange(x, first, after, dist * 2);
        }

        StringBuilder output = new StringBuilder();
        for (long value : ans) {
            output.append(value).append(" ");
        }
        System.out.println(output.toString().trim());
    }

    private static long getSumUntil(long[] tree, int index) {
        long sum = 0;
        while (index >= 0) {
            sum += tree[index];
            index = (index & (index + 1)) - 1;
        }
        return sum;
    }

    private static void incrementFenwickTree(long[] tree, int index, long delta) {
        while (index < tree.length) {
            tree[index] += delta;
            index |= (index + 1);
        }
    }

    private static void updateFenwickTreeRange(long[] tree, int leftIndex, int rightIndex, long delta) {
        if (leftIndex != -1) {
            incrementFenwickTree(tree, leftIndex, delta);
        }
        if (rightIndex != -1) {
            incrementFenwickTree(tree, rightIndex, -delta);
        }
    }

    private static int findFirst(List<Long> list, long value) {
        int pos = Collections.binarySearch(list, value);
        if (pos < 0) {
            pos = ~pos;
            return pos < list.size() ? pos : -1;
        } else {
            return pos + 1 < list.size() ? pos + 1 : -1;
        }
    }
}