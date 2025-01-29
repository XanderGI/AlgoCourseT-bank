import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class TaskG5RandomHashFromSet {
    static class HashCalculator {
        private static final Map<Integer, Long> numberToHash = new HashMap<>();
        private static final Random rand = new Random();

        /* генерирует хэш для числа, если он не был сгенерирован ранее*/
        private static long getHash(int number) {
            return numberToHash.computeIfAbsent(number, k -> (long) rand.nextInt() & 0xFFFFFFFFL);
        }

        public static long[] initSequenceHashes(String[] sequence) {
            long[] prefixHashes = new long[sequence.length + 1];
            for (int i = 0; i < sequence.length; i++) {
                int number = Integer.parseInt(sequence[i]);
                prefixHashes[i + 1] = prefixHashes[i] + getHash(number);
            }
            return prefixHashes;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int firstLength = Integer.parseInt(reader.readLine());
        long[] prefixHashFirst = HashCalculator.initSequenceHashes(reader.readLine().split(" "));

        int secondLength = Integer.parseInt(reader.readLine());
        long[] prefixHashSecond = HashCalculator.initSequenceHashes(reader.readLine().split(" "));
        for (int maxSeq = Math.min(firstLength, secondLength); maxSeq > 0; maxSeq--) { // min тк отрезок не может превышать мощность min множества.
            for (int i = 0; i <= firstLength - maxSeq; i++) {
                for (int j = 0; j <= secondLength - maxSeq; j++) {
                    if ((prefixHashFirst[firstLength - i] + prefixHashSecond[secondLength - maxSeq - j]) == (prefixHashSecond[secondLength - j] + prefixHashFirst[firstLength - maxSeq - i])) {
                        System.out.println(maxSeq);
                        return; // тк начинаем с макс. последовательности => первый найденный совпадающий подотрезок и есть максимальный.
                    }
                }
            }
        }
        System.out.println(0);
    }
}