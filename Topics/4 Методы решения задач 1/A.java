import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaskA4findPrefixSumAndXOR {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder result = new StringBuilder();

    public static long calcRequestSum(long[] prefixSum, int left, int right) {
        return prefixSum[right] - prefixSum[left - 1];
    }

    public static long calcRequestXOR(long[] prefixXOR, int left, int right) {
        return prefixXOR[right] ^ prefixXOR[left - 1];
    }

    public static void main(String[] args) throws IOException {
        reader.readLine();
        String[] stringArray = reader.readLine().split(" ");
        int lenOfArray = stringArray.length;
        long[] prefixSum = new long[lenOfArray + 1];
        long[] prefixXOR = new long[lenOfArray + 1];
        for (int i = 0; i < lenOfArray; i++) {
            int value = Integer.parseInt(stringArray[i]);
            prefixSum[i + 1] = prefixSum[i] + value;
            prefixXOR[i + 1] = prefixXOR[i] ^ value;
        }
        int m = Integer.parseInt(reader.readLine());
        for (int j = 0; j < m; j++) {
            String[] request = reader.readLine().split(" ");
            int leftIndex = Integer.parseInt(request[1]);
            int rightIndex = Integer.parseInt(request[2]);
            switch (request[0]) {
                case "1":
                    long value = calcRequestSum(prefixSum, leftIndex, rightIndex);
                    result.append(value).append("\n");
                    break;
                case "2":
                    value = calcRequestXOR(prefixXOR, leftIndex, rightIndex);
                    result.append(value).append("\n");
                    break;
            }
        }
        System.out.println(result);
    }
}