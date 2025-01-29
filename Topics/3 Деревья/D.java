import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task3DrealizeMaxHeap {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    /*https://habr.com/ru/articles/112222/*/
    static void heapify(int index, int[] array, int size) {
        int largest = index;
        int leftNode = index * 2 + 1;
        int rightNode = index * 2 + 2;
        if (leftNode < size && array[leftNode] > array[largest]) {
            largest = leftNode;
        }
        if (rightNode < size && array[rightNode] > array[largest]) {
            largest = rightNode;
        }

        if (largest != index) {
            int temp = array[largest];
            array[largest] = array[index];
            array[index] = temp;
            heapify(largest, array, size);
        }
    }

    static void bubbleUp(int index, int[] array) {
        while (index > 0 && array[index] > array[(index - 1) / 2]) {
            int parentIndex = (index - 1) / 2;
            int temp = array[index];
            array[index] = array[parentIndex];
            array[parentIndex] = temp;
            index = parentIndex;
        }
    }

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();
        int[] array = new int[n];
        for (int i = 0, j = 0; i < n; i++) {
            String[] pair = br.readLine().split(" ");
            switch (pair[0]) {
                case "1":
                    result.append(array[0]).append("\n");
                    array[0] = array[--j];
                    heapify(0, array, j);
                    break;
                case "0":
                    int value = Integer.parseInt(pair[1]);
                    array[j] = value;
                    bubbleUp(j, array);
                    j++;
                    break;
            }
        }
        System.out.println(result);
    }
}