//package Tink.less7DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TaskC7RichGrasshopperAndDP {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder result = new StringBuilder("1 ");


    static class Pair {
        int index;
        int max;

        public Pair(int index, int max) {
            this.index = index;
            this.max = max;
        }
    }

    public static void main(String[] args) throws IOException {
        String[] firstLine = reader.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int k = Integer.parseInt(firstLine[1]);
        String[] data = reader.readLine().split(" ");
        int[] arrayOfMoney = new int[n-2];
        int[] jumps = new int[n];
        int[] dp = new int[n];
        dp[0] = 0;
        ArrayDeque<Pair> dequeWithMax = new ArrayDeque<>();
        dequeWithMax.addLast(new Pair(0,dp[0]));
        for (int i = 0; i < n-2; i++) {
            int money = Integer.parseInt(data[i]);
            arrayOfMoney[i] = money;
        }

        for (int i = 1; i < n; i++) {
            while (!dequeWithMax.isEmpty() && dequeWithMax.getFirst().index < i-k) {
                dequeWithMax.pollFirst(); // обновляем окно допустимых элементов
            }

            int currentMax = dequeWithMax.isEmpty() ? Integer.MIN_VALUE : dequeWithMax.getFirst().max;
            dp[i] = i < n - 1 ? currentMax + arrayOfMoney[i - 1] : currentMax;
            jumps[i] = dequeWithMax.getFirst().index;

            while (!dequeWithMax.isEmpty() && dequeWithMax.getLast().max <= dp[i]) {
                dequeWithMax.pollLast(); // поддерживаем максимум на деке
            }
            if (i < n-1) {
                dequeWithMax.addLast(new Pair(i,dp[i]));
            }

        }
        System.out.println(dp[n-1]);
        // Обратный проход для расчета количества прыжков для оптимального пути
        int i = n-1;
        List<Integer> path = new ArrayList<>();
        int numJumps = 0; // Для подсчета количества прыжков
        while (i > 0) {
            path.add(i+1); // путь с конца (n-1)
            i = jumps[i]; // возращаемся к предшественнвующему столбцу
            numJumps++;
        }
        System.out.println(numJumps);
        Collections.reverse(path);
        for (int el : path) {
            result.append(el).append(" ");
        }
        System.out.println(result.toString().trim());
    }
}