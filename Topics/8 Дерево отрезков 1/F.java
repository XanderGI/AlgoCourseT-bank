import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class TaskF8FindTripleInversion {
    private static void update(long[] fenwickTree, int index, long value) {
        while (index < fenwickTree.length) {
            fenwickTree[index] += value;
            index += index & -index;
        }
    }

    private static long query(long[] fenwickTree, int index) {
        long sum = 0;
        while (index > 0) {
            sum += fenwickTree[index];
            index -= index & -index;
        }
        return sum;
    }

    private static int[] compressArray(int[] arr) {
        TreeSet<Integer> sortedValues = new TreeSet<>();
        for (int value : arr) {
            sortedValues.add(value);
        }
        Map<Integer, Integer> valueToIndex = new HashMap<>();
        int index = 0;
        for (int value : sortedValues) {
            valueToIndex.put(value, ++index);
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = valueToIndex.get(arr[i]);
        }
        return arr;
    }

    public static long countTripleInversions(int[] array) {
        int[] compressedArray = compressArray(array.clone());
        int size = array.length;
        long[] fenwickTree2 = new long[size + 1];
        long[] fenwickTree3 = new long[size + 1];
        long inversions3 = 0;
        for (int i = array.length - 1; i >= 0; i--) {
            int val = compressedArray[i];
            long countDI = query(fenwickTree2, val - 1);
            update(fenwickTree2, val, 1);
            inversions3 += query(fenwickTree3, val - 1);
            update(fenwickTree3, val, countDI);
        }
        return inversions3;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        String[] data = reader.readLine().split(" ");
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(data[i]);
        }
        System.out.println(countTripleInversions(array));
    }
}