//package Tink.less11;

import java.util.Scanner;

public class TaskC11LongestPalindromicSubstring {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String string = sc.nextLine();
        String result = longestPalindromicSubsequence(string);
        System.out.println(result.length());
        System.out.println(result);
    }

    public static String longestPalindromicSubsequence(String string) {
        StringBuilder result = new StringBuilder();
        int n = string.length();
        int[][] dp = new int[n][n];

        // каждый символ является палиндромом длины 1
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        // Заполнение таблицы динамического программирования
        for (int l = 2; l <= n; l++) {
            for (int i = 0; i < n - l + 1; i++) {
                int j = i + l - 1;
                if (string.charAt(i) == string.charAt(j)) {
                    dp[i][j] = l == 2 ? 2 : dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        // Восстановление подпалиндромной подпоследовательности из таблицы dp
        int i = 0, j = n - 1;
        while (i <= j) {
            if (string.charAt(i) == string.charAt(j)) {
                result.append(string.charAt(i));
                i++;
                j--;
            } else if (dp[i][j - 1] > dp[i + 1][j]) {
                j--;
            } else {
                i++;
            }
        }

        // Определение первой половины подпалиндрома
        String firstHalf = result.toString();
        if (firstHalf.length() * 2 < dp[0][n - 1]) {
            // Добавление центрального символа
            result.append(string.charAt(i - 1));
        }
        String secondHalf = new StringBuilder(firstHalf).reverse().toString();
        if (firstHalf.length() * 2 == dp[0][n - 1]) {
            return firstHalf + secondHalf;
        } else {
            return firstHalf + secondHalf.substring(1);
        }
    }
}