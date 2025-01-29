import java.util.Scanner;

public class TaskE11MinRLE {
    public static String compress(String s) {
        int n = s.length();
        String[][] dp = new String[n][n];

        // Заполнение таблицы для одиночных символов.
        for (int i = 0; i < n; i++) {
            dp[i][i] = s.substring(i, i + 1);
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                dp[i][j] = s.substring(i, j + 1);
                // Поиск оптимального разделения подпоследовательности.
                for (int k = i; k < j; k++) {
                    String option = dp[i][k] + dp[k + 1][j];
                    if (option.length() < dp[i][j].length()) {
                        dp[i][j] = option;
                    }
                }
                // Поиск повторяющихся подпоследовательностей.
                for (int k = i; k < j; k++) {
                    String repeatStr = s.substring(i, k + 1);
                    if ((j - i + 1) % repeatStr.length() == 0 && s.substring(i, j + 1).replaceAll(repeatStr, "").length() == 0) {
                        String compressed = (j - i + 1) / repeatStr.length() + "(" + dp[i][k] + ")";
                        if (compressed.length() < dp[i][j].length()) {
                            dp[i][j] = compressed;
                        }
                    }
                }
            }
        }

        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print(compress(sc.nextLine()));
    }
}