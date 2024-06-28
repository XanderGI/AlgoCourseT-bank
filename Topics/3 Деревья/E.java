//package Tink.less3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task3ErealizeHeapSort {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static void heapify(int index, int[] array, int size) {
        int largest = index;
        int leftNode = index*2+1;
        int rightNode = index*2+2;
        if (leftNode < size && array[leftNode] < array[largest]) {
            largest = leftNode;
        }
        if (rightNode < size && array[rightNode] < array[largest]) {
            largest = rightNode;
        }

        if (largest != index) {
            int temp = array[largest];
            array[largest] = array[index];
            array[index] = temp;
            heapify(largest,array,size);
        }
    }

    public static void main(String[] args) throws IOException {
        br.readLine();
        StringBuilder result = new StringBuilder();
        String[] seqOfNumb = br.readLine().split(" ");
        int[] arrayForSort = new int[seqOfNumb.length];
        for (int i = 0; i < seqOfNumb.length; i++) {
            arrayForSort[i] = Integer.parseInt(seqOfNumb[i]);
        }
        for (int j = arrayForSort.length/2 - 1; j >= 0 ; j--) {
            heapify(j,arrayForSort,arrayForSort.length);
        }
        for (int i = arrayForSort.length - 1; i >= 0; i--) {
            int temp = arrayForSort[0];
            arrayForSort[0] = arrayForSort[i];
            arrayForSort[i] = arrayForSort[0];
            result.append(temp).append(" ");
            heapify(0,arrayForSort,i);
        }
        System.out.println(result.toString().trim());
    }
}