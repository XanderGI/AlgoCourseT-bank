//package Tink.less11;

import java.util.*;

public class TaskF11MinCountMoneyAndBruteForce {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int[] coins = new int[M];
        long totalCoinsValue = 0;
        for (int i = 0; i < M; i++) {
            coins[i] = scanner.nextInt();
            totalCoinsValue += 2L * coins[i];
        }
        if (totalCoinsValue < N) {
            System.out.print(-1);
            return;
        }
        int[] bestCombination = null;
        int minCoins = Integer.MAX_VALUE;
        for (int mask = 0; mask < (1 << (2 * M)); mask++) {
            long sum = 0;
            int coinsCount = 0;
            int[] combination = new int[M];
            for (int i = 0; i < M; i++) {
                int use = (mask >> (2 * i)) & 3;
                if (use <= 2) {
                    sum += (long) coins[i] * use;
                    coinsCount += use;
                    combination[i] = use;
                }
            }
            if (sum == N && coinsCount < minCoins) {
                minCoins = coinsCount;
                bestCombination = combination.clone();
            }
        }
        if (bestCombination == null) {
            System.out.print(0);
        } else {
            System.out.println(minCoins);
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < bestCombination[i]; j++) {
                    System.out.print(coins[i] + " ");
                }
            }
        }
    }
}